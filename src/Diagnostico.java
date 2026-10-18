public class Diagnostico extends Orden {
    public Diagnostico (int IDOrden, String nombreCliente, String dirreccionCliente, float horasEstTrabajo, ZonaTrabajo zona) {
        super(IDOrden, nombreCliente, dirreccionCliente, horasEstTrabajo, zona);
    

    //validacion horas
    if (horasEstTrabajo > 1.5) {
        throw new IllegalArgumentException("El diagnóstico no puede exceder las 1.5 horas de trabajo.");
    }
}

    @Override
    public float calcularCosto() {
        if (getZonaTrabajo() == ZonaTrabajo.METROPOLITANA) {
            return 150;
        }
        if(getZonaTrabajo() == ZonaTrabajo.PERIFERICA) {
            return 225;
        }
        return 0;
    }

    @Override
    public float calcularCostoExtra() {
        return 0; // Tarifa fija para este tipo de servicio
    }

    @Override
    public int calcularTiempoEstAtencion() {
        if (getZonaTrabajo() == ZonaTrabajo.METROPOLITANA) {
            return 1;
        }
        if(getZonaTrabajo() == ZonaTrabajo.PERIFERICA) {
            return 2;
        }
        return 0;
    }

    @Override
    public int getLimiteVisitas() {
            return 3;
        }
    

    @Override
    public boolean requiereCuadrillaCertRefrigeracion() {
        return false;
    }

    @Override
    public String getCategoriaOrden() {
        return "Diagnóstico";
    }

}
