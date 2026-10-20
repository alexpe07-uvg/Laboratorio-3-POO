import java.util.List;
import java.util.ArrayList;

public class Jornada {
    private Cuadrilla cuadrilla;
    private List <Orden> ordenes;
    private EstadoJornada estado;

    public Jornada(Cuadrilla cuadrilla) {
        if (cuadrilla == null) {
            throw new IllegalArgumentException("Debe seleccionar una cuadrilla.");
        }
        if (cuadrilla.isTieneJornadaActiva()) {
            throw new IllegalArgumentException("Esta cuadrilla ya tiene una jornada activa.");
        }

        this.cuadrilla = cuadrilla;
        this.ordenes = new ArrayList<>();
        this.estado = EstadoJornada.EN_PREPARACION; // Inicializar el estado como EN_PREPARACION
        this.cuadrilla.setTieneJornadaActiva(true); // Marcar la cuadrilla como en jornada activa
    }

    //getters y setters
    public Cuadrilla getCuadrilla() {
        return cuadrilla;
    }
    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public EstadoJornada getEstado() {
        return estado;
    }

    // horas estimadas
    public float getHorasAsignadas() {
        float totalHoras = 0.00f; //se empieza con cero y luego se itera sobre la lista de órdenes para sumar las horas estimadas de trabajo
        for (Orden o : ordenes) {
            totalHoras += o.getHorasEstTrabajo();
        }
        return totalHoras;
    }

    public float getCapacidadDisponible() {
        return cuadrilla.getCapacidadMaxHoras() - getHorasAsignadas();
    }

    //asignación de órdenes (criterios)
    public void asignarOrden(Orden orden) {
        if (this.ordenes.contains(orden)) {
            throw new IllegalArgumentException("La orden ya fue asignada a esta jornada.");
        }
        if(this.estado != EstadoJornada.EN_PREPARACION) {
            throw new IllegalStateException("Solo se pueden asignar órdenes mientras la jornada esté en preparación.");
        }
        if (orden.getEstadoOrden() != EstadoOrden.PENDIENTE) {
            throw new IllegalArgumentException("Solo se pueden asignar órdenes que estén en estado PENDIENTE.");
        }
        if(getHorasAsignadas() + orden.getHorasEstTrabajo() > cuadrilla.getCapacidadMaxHoras()) {
            throw new IllegalArgumentException("No hay suficiente capacidad de horas en la cuadrilla para asignar esta orden.");
        }
        if(orden.requiereCuadrillaCertRefrigeracion() && !cuadrilla.isConCertRefrigeracion()) {
            throw new IllegalArgumentException("Esta orden requiere una cuadrilla con certificación en refrigeración.");
        }
        ordenes.add(orden);
    }

    public void retirarOrden(Orden orden) {
        if(this.estado != EstadoJornada.EN_PREPARACION) {
            throw new IllegalStateException("Solo se pueden retirar órdenes mientras la jornada esté en preparación.");
        }
        if(!this.ordenes.contains(orden)){
            throw new IllegalArgumentException("La orden no está asignada a esta jornada.");
        }
        this.ordenes.remove(orden);
    }

    public float iniciarJornada() {
        if(this.estado != EstadoJornada.EN_PREPARACION) {
            throw new IllegalStateException("La jornada ya ha sido iniciada o cerrada previamente.");
        }
        if(ordenes.isEmpty()) {
            throw new IllegalStateException("No se puede iniciar una jornada sin órdenes asignadas.");
        }
        float cargoVisitasExtra = 0.00f;
        for (Orden o : ordenes) {
            o.setEstadoOrden(EstadoOrden.EN_ATENCION);
            if(o.getVisitas() > 0) {
                cargoVisitasExtra += 40.00f;
            }
        }
        this.estado = EstadoJornada.EN_ATENCION;
        return cargoVisitasExtra;
    }


    //verifica las visitas de cada orden y actualiza su estado según corresponda
    public void registrarVisita(Orden orden, boolean exito) {
        if(this.estado != EstadoJornada.EN_ATENCION) {
            throw new IllegalStateException("Solo se pueden registrar visitas mientras la jornada esté en atención.");
        }
        if(!this.ordenes.contains(orden)){
            throw new IllegalArgumentException("La orden no está asignada a esta jornada.");
        }

        orden.registrarVisita(); // si la jornada está en atención y la orden si existe en la jornada, se registra la visita

        if(exito) {
            orden.setEstadoOrden(EstadoOrden.COMPLETADA);
        } else {
            if(orden.haAgotadoVisitas()) {
                orden.setEstadoOrden(EstadoOrden.CANCELADA);
            } else {
                orden.setEstadoOrden(EstadoOrden.PENDIENTE);
            }
        }
    }

    public boolean estanTodasLasOrdenesResueltas() {
        for (Orden o : ordenes) {
            if (o.getEstadoOrden() == EstadoOrden.EN_ATENCION) {
                return false;
            }
            
        }
        return true;
    }

    public void cerrarJornada() {
        if(this.estado != EstadoJornada.EN_ATENCION) {
            throw new IllegalStateException("Solo se puede cerrar una jornada que esté en atención.");
        }
        if(!estanTodasLasOrdenesResueltas()) {
            throw new IllegalStateException("No se puede cerrar la jornada mientras haya órdenes en atención y sin resolver.");
        }
        this.estado = EstadoJornada.CERRADA;
        this.cuadrilla.setTieneJornadaActiva(false); // Marcar la cuadrilla como disponible nuevamente para cualquier otro trabajo
    }

    @Override
    public String toString() {
        String estadoTexto = "";
        if (this.estado == EstadoJornada.EN_PREPARACION) {
            estadoTexto = "En Preparación";
        } else if (this.estado == EstadoJornada.EN_ATENCION) {
            estadoTexto = "En Atención";
        } else {
            estadoTexto = "Cerrada";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Jornada [Cuadrilla No. ").append(cuadrilla.getIDCuadrilla())
          .append(" | Estado: ").append(estadoTexto)
          .append(String.format(" | Horas de trabajo: %.2f / %.2f h", getHorasAsignadas(), cuadrilla.getCapacidadMaxHoras()))
          .append(String.format(" | Disponible: %.2f h", getCapacidadDisponible()))
          .append(" | Órdenes: ").append(ordenes.size()).append("]");
        return sb.toString();
    }

    
}
