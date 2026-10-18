public abstract class Orden {
    private int IDOrden;
    private String nombreCliente;
    private String dirreccionCliente;
    private float horasEstTrabajo;
    private ZonaTrabajo zona;
    private EstadoOrden estado;
    private int visitas;

    public Orden(int IDOrden, String nombreCliente, String dirreccionCliente, float horasEstTrabajo, ZonaTrabajo zona) {
        if (IDOrden <= 0) {
        throw new IllegalArgumentException("El ID de la orden debe ser positivo y mayor a cero.");
      } 
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede ser nulo o vacío.");
        }
        this.dirreccionCliente = dirreccionCliente;
        if (horasEstTrabajo <= 0) {
            throw new IllegalArgumentException("Las horas estimadas de trabajo deben ser positivas y mayores a cero.");
        }
        
        this.IDOrden = IDOrden;
        this.nombreCliente = nombreCliente;
        this.dirreccionCliente = dirreccionCliente;
        this.horasEstTrabajo = horasEstTrabajo;
        this.zona = zona;
        this.estado = EstadoOrden.PENDIENTE; // Inicializar el estado como PENDIENTE en toda orden
        this.visitas = 0; // se inicia en 0 visitas
    }


    //getters y setters
    public int getIDOrden() {
        return IDOrden;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDirreccionCliente() {
        return dirreccionCliente;
    }

    public float getHorasEstTrabajo() {
        return horasEstTrabajo;
    }

    public ZonaTrabajo getZonaTrabajo() {
        return zona;
    }

    public EstadoOrden getEstadoOrden() {
        return estado;
    }

    public void setEstadoOrden(EstadoOrden estado) {
        this.estado = estado;
    }

    public int getVisitas() {
        return visitas;
    }

    // métodos para clases hija

    public abstract float calcularCosto();

    public abstract float calcularCostoExtra();

    public abstract int calcularTiempoEstAtencion();

    public abstract int getLimiteVisitas();

    public abstract boolean requiereCuadrillaCertRefrigeracion();

    public abstract String getCategoriaOrden();

    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("========================================\n");
    sb.append("Categoría: ").append(getCategoriaOrden()).append("\n");
    sb.append("ID: ").append(IDOrden).append("\n");
    sb.append("Cliente: ").append(nombreCliente).append("\n");
    sb.append("Dirección: ").append(dirreccionCliente).append("\n");
    sb.append("Zona: ").append(zona).append("\n");
    sb.append("Horas estimadas: ").append(horasEstTrabajo).append(" h\n");
    sb.append("Estado: ").append(estado).append("\n");
    sb.append("Visitas realizadas: ").append(visitas).append(" / ").append(getLimiteVisitas()).append("\n");
    sb.append("Tiempo estimado de atención: ").append(calcularTiempoEstAtencion()).append(" días hábiles\n");
    sb.append(String.format("Costo total: Q%.2f\n", calcularCosto()));
    return sb.toString();
}


    
    //métodos verificar cantidad visitas
    public void registrarVisita() {
        this.visitas++;
    }

    public boolean haAgotadoVisitas() {
        return this.visitas >= getLimiteVisitas();
    }
}
