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
            return 150.00f;
        }
        if(getZonaTrabajo() == ZonaTrabajo.PERIFERICA) {
            return 225.00f;
        }
        return 0;
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

    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Nota: Tarifa fija según zona. No importa la cantidad de horas\n");
        sb.append("========================================");
        return sb.toString();

    }
}
