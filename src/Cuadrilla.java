public class Cuadrilla {
    private int IDCuadrilla;
    private float capacidadMaxHoras;
    private boolean conCertRefrigeracion;
    private boolean tieneJornadaActiva;

    public Cuadrilla(int IDCuadrilla, float capacidadMaxHoras, boolean conCertRefrigeracion) {
        if(IDCuadrilla <= 0) {
            throw new IllegalArgumentException("El ID de la cuadrilla debe ser un número positivo.");
        }
        if(capacidadMaxHoras <= 0) {
            throw new IllegalArgumentException("La capacidad máxima de horas debe ser un número positivo.");
        }
        this.IDCuadrilla = IDCuadrilla;
        this.capacidadMaxHoras = capacidadMaxHoras;
        this.conCertRefrigeracion = conCertRefrigeracion;
        this.tieneJornadaActiva = false; // Inicialmente, la cuadrilla no tiene jornada activa
    }

    // Getters y setters
    public int getIDCuadrilla() {
        return IDCuadrilla;
    }

    public float getCapacidadMaxHoras() {
        return capacidadMaxHoras;
    }

    public boolean isConCertRefrigeracion() {
        return conCertRefrigeracion;
    }

    public boolean isTieneJornadaActiva() {
        return tieneJornadaActiva;
    }

    public void setTieneJornadaActiva(boolean tieneJornadaActiva) {
        this.tieneJornadaActiva = tieneJornadaActiva;
    }

    @Override 
    public String toString() {
        String certTexto;
        if (this.conCertRefrigeracion) {
            certTexto = "Sí";
        } else {
            certTexto = "No";
        }

        String estadoTexto;
        if (this.tieneJornadaActiva) {
            estadoTexto = "En Jornada Activa";
        } else {
            estadoTexto = "Disponible";
        }

    //luego de establecer opciones, se arma el texto con StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("Cuadrilla No. ").append(IDCuadrilla).append(" | Capacidad horas: ").append(capacidadMaxHoras).append(" h").append(" | Certificada en refrigeración: ").append(certTexto).append(" | Estado: ").append(estadoTexto).append("\n");
        return sb.toString();
    }

}
