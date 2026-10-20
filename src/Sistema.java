import java.util.List;
import java.util.ArrayList;

public class Sistema {
    private List<Orden> listaOrdenes;
    private List<Cuadrilla> listaCuadrillas;
    private List<Jornada> listaJornadas;

    // atributos para los reportes gerenciales
    private float ingresosPorRegistro;
    private float ingresosPorVisitasExtra;

    public Sistema() {
        this.listaOrdenes = new ArrayList<>();
        this.listaCuadrillas = new ArrayList<>();
        this.listaJornadas = new ArrayList<>();
        this.ingresosPorRegistro = 0.00f; //se inicializa sin ingresos
        this.ingresosPorVisitasExtra = 0.00f; //se inicializa sin ingresos

        precargarDatos();
    }

    //getters y setters
    public List<Orden> getListaOrdenes() {
        return listaOrdenes;
    }

    public List<Cuadrilla> getListaCuadrillas() {
        return listaCuadrillas;
    }

    public List<Jornada> getListaJornadas() {
        return listaJornadas;
    }

    //plantilla base
    private void precargarDatos() {
        // 1. Cuadrillas base
        listaCuadrillas.add(new Cuadrilla(1, 8.0f, false));
        listaCuadrillas.add(new Cuadrilla(2, 6.5f, false));
        listaCuadrillas.add(new Cuadrilla(3, 10.0f, true));

        // 2. Órdenes base
        // Diagnóstico
        listaOrdenes.add(new Diagnostico(101, "Carlos Morales", "Zona 10, Ciudad", 1.0f, ZonaTrabajo.METROPOLITANA));
        listaOrdenes.add(new Diagnostico(102, "Ana Lucía Gómez", "Carretera a El Salvador, Km 18", 1.5f, ZonaTrabajo.PERIFERICA));

        // Instalación (Caso 201 prueba la regla de Math.ceil donde puntos superan horas estimadas)
        listaOrdenes.add(new Instalacion(201, "Roberto Fuentes", "Zona 14, Ciudad", 2.5f, ZonaTrabajo.METROPOLITANA, 5));
        listaOrdenes.add(new Instalacion(202, "Brenda Castillo", "Mixco, Zona 1", 4.0f, ZonaTrabajo.PERIFERICA, 3));

        // Instalación Eléctrica Certificada
        listaOrdenes.add(new InstalacionCertificada(301, "Empresa Textil S.A.", "Zona 12, Ciudad", 2.5f, ZonaTrabajo.METROPOLITANA, 5, 2000.00f));
        listaOrdenes.add(new InstalacionCertificada(302, "Farmacia La Salud", "San Lucas Sacatepéquez", 2.0f, ZonaTrabajo.PERIFERICA, 2, 1200.00f));

        // Mantenimiento de Aire Acondicionado
        listaOrdenes.add(new MantenimientoAC(401, "Oficinas Centrales", "Zona 9, Ciudad", 2.0f, ZonaTrabajo.METROPOLITANA, 12000));
        listaOrdenes.add(new MantenimientoAC(402, "Clínica Dental", "Zona 15, Ciudad", 3.5f, ZonaTrabajo.METROPOLITANA, 24000));
    }

    // búsquedas por ID
    public Orden buscarOrden(int IDOrden) {
        for (Orden o : listaOrdenes) {
            if (o.getIDOrden() == IDOrden) {
                return o;
            }
        }
        return null;
    }

    // búsquedas por ID
    public Cuadrilla buscarCuadrilla(int IDCuadrilla) {
        for (Cuadrilla c : listaCuadrillas) {
            if (c.getIDCuadrilla() == IDCuadrilla) {
                return c;
            }
        }
        return null;
    }

    // Registro de nueva orden: cobra inmediatamente al registrar, verificacion de ID no duplicado
    public void registrarOrden(Orden orden) {
        if (buscarOrden(orden.getIDOrden()) != null) {
            throw new IllegalArgumentException("Ya existe una orden registrada con el ID No. " + orden.getIDOrden());
        }
        listaOrdenes.add(orden);
        this.ingresosPorRegistro += orden.calcularCosto();
    }

    // Registro de cuadrilla y verificacion ID no duplicado
    public void registrarCuadrilla(Cuadrilla cuadrilla) {
        if (buscarCuadrilla(cuadrilla.getIDCuadrilla()) != null) {
            throw new IllegalArgumentException("Ya existe una cuadrilla con el ID No. " + cuadrilla.getIDCuadrilla());
        }
        listaCuadrillas.add(cuadrilla);
    }

    // Creación de nueva jornada
    public Jornada crearJornada(int IDCuadrilla) {
        Cuadrilla c = buscarCuadrilla(IDCuadrilla);
        if (c == null) {
            throw new IllegalArgumentException("No se encontró la cuadrilla #" + IDCuadrilla);
        }
        Jornada nuevaJornada = new Jornada(c);
        listaJornadas.add(nuevaJornada);
        return nuevaJornada;
    }

    // Acumulador de cobro por visitas recurrentes
    public void sumarIngresoVisitasExtra(float monto) {
        this.ingresosPorVisitasExtra += monto;
    }

     
    // APARTADO PARA REPORTES DE GERENCIA
    

    public String generarReporteEstados() {
        //se inicializan todo en 0
        int pendientes = 0;
        int enAtencion = 0;
        int completadas = 0;
        int canceladas = 0;

        for (Orden o : listaOrdenes) {
            if (o.getEstadoOrden() == EstadoOrden.PENDIENTE) {
                pendientes++;
            } else if (o.getEstadoOrden() == EstadoOrden.EN_ATENCION) {
                enAtencion++;
            } else if (o.getEstadoOrden() == EstadoOrden.COMPLETADA) {
                completadas++;
            } else if (o.getEstadoOrden() == EstadoOrden.CANCELADA) {
                canceladas++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n================ RESUMEN DE ÓRDENES ================\n");
        sb.append("Total de órdenes registradas: ").append(listaOrdenes.size()).append("\n");
        sb.append("Pendientes: ").append(pendientes).append("\n");
        sb.append("En Atención: ").append(enAtencion).append("\n");
        sb.append("Completadas: ").append(completadas).append("\n");
        sb.append("Canceladas: ").append(canceladas).append("\n");
        sb.append("====================================================\n");
        return sb.toString();
    }

    public String generarReporteFinanciero() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n================ REPORTE FINANCIERO ================\n");
        sb.append(String.format("Ingresos por Registro de Servicios: Q%.2f\n", ingresosPorRegistro));
        sb.append(String.format("Ingresos por Visitas Extra (Q40):    Q%.2f\n", ingresosPorVisitasExtra));
        sb.append("----------------------------------------------------\n");
        sb.append(String.format("TOTAL INGRESOS PERCIBIDOS:          Q%.2f\n", (ingresosPorRegistro + ingresosPorVisitasExtra)));
        sb.append("====================================================\n");
        return sb.toString();
    }

}