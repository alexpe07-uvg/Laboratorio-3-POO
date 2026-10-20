public class InstalacionCertificada extends Instalacion { //clase hija de instalación
    private float costoMateriales;

    public InstalacionCertificada(int IDOrden, String nombreCliente, String dirreccionCliente, float horasEstTrabajo, ZonaTrabajo zona, int puntosAInstalar, float costoMateriales) {
        super(IDOrden, nombreCliente, dirreccionCliente, horasEstTrabajo, zona, puntosAInstalar);
        if (costoMateriales < 0) {
            throw new IllegalArgumentException("El costo de materiales no puede ser negativo.");
        }
        this.costoMateriales = costoMateriales;
    }

    //getters y setters
    public float getCostoMateriales() {
        return costoMateriales;
    }

    @Override 
    public float calcularCosto() {
        //usa toda la logica de la clase Instalacion y le suma la garantía del 5% del costo de los materiales y del certificado
        float costoBase = super.calcularCosto();
        float costoCertificado = 120.00f;
        float garantia = this.costoMateriales * 0.05f; // 5% del costo de materiales
        return costoBase + costoCertificado + garantia;
    }

    @Override
    public int calcularTiempoEstAtencion() {
        // Dos días más que una instalación normal en la misma zona
        return super.calcularTiempoEstAtencion() + 2;
    }

    @Override
    public int getLimiteVisitas() {
        return 2; 
    }

    @Override 
    public boolean requiereCuadrillaCertRefrigeracion() {
        return false; 
    }

    @Override
    public String getCategoriaOrden() {
        return "Instalación Eléctrica Certificada";
    }

    @Override
    public String toString() {
        String base = super.toString().replace("========================================", "");
        StringBuilder sb = new StringBuilder(base);
        sb.append(String.format("Valor declarado de materiales: Q%.2f\n ", costoMateriales));
        sb.append("Certificado emitido: Q120.00 incluidos\n");
        sb.append(String.format("Garantía: Q%.2f  incluida ", (costoMateriales * 0.05f)));
        sb.append("========================================\n");
        return sb.toString();
    }
}
