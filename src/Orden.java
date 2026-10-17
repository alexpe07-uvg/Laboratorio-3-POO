public class Orden {
    private int IDOrden;
    private String nombreCliente;
    private String dirreccionCliente;
    private float horasEstTrabajo;
    private ZonaTrabajo zonaTrabajo;
    private EstadoOrden estado;
    private int visitas;

    public Orden(String nombreCliente, String dirreccionCliente, float horasEstTrabajo, ZonaTrabajo zonaTrabajo, EstadoOrden estado) {
      setIDOrden(IDOrden); 
        this.nombreCliente = nombreCliente;
        this.dirreccionCliente = dirreccionCliente;
        this.horasEstTrabajo = horasEstTrabajo;
        this.zonaTrabajo = zonaTrabajo;
        this.estado = EstadoOrden.PENDIENTE; // Inicializar el estado como PENDIENTE
        this.visitas = 0; // se inicia en 0 visitas
    }


    public int getIDOrden() {
        return IDOrden;
    }

    public void setIDOrden(int IDOrden) {
        this.IDOrden = IDOrden;
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
        return zonaTrabajo;
    }

    public EstadoOrden getEstadoOrden() {
        return estado;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }
}
