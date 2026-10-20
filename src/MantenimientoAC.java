public class MantenimientoAC extends Orden {
    private int cantidadBTU;

    public MantenimientoAC(int IDOrden, String nombreCliente, String direccion, float horasEstTrabajo, ZonaTrabajo zona, int cantidadBTU) {
        super(IDOrden, nombreCliente, direccion, horasEstTrabajo, zona);
        
        //validaciones específicas solo para el servicio MantenimientoAC
        if (zona != ZonaTrabajo.METROPOLITANA) {
            throw new IllegalArgumentException("El mantenimiento de aire acondicionado solo está disponible en la zona METROPOLITANA.");
        }

        if (cantidadBTU < 9000 || cantidadBTU > 60000) {
            throw new IllegalArgumentException("La cantidad de BTU debe ser positiva y mayor a cero.");
        }
        this.cantidadBTU = cantidadBTU;
    }

    //getters y setters
    public int getCantidadBTU() {
        return cantidadBTU;
    }

    @Override
    public float calcularCosto() {
        float tarifaBase = 100.00f; // solo zona metropolitana
        float costoXHora = 110.00f;
        float cargoGas = 80.00f;
        return tarifaBase + costoXHora + cargoGas;
    }

    @Override 
    public int calcularTiempoEstAtencion() {
        return 2; //siempre son 2 días para realizar la visita
    }

    @Override 
    public int getLimiteVisitas() {
        return 1; //solo una visita porque el gas refrigerante reservado debe devolverse al proovedor
    }

    @Override
    public boolean requiereCuadrillaCertRefrigeracion() {
        return true; // unico que lo exige como requisito previo
    }
    
    @Override
    public String getCategoriaOrden() {
        return "Mantenimiento de Aire Acondicionado";
    }

    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Capacidad del equipo: ").append(getCantidadBTU()).append(" BTU\n");
        sb.append("Cargo fijo por gas refrigerante: Q80.00 incluido\n");
        sb.append("========================================\n");
        return sb.toString();
    }
}
