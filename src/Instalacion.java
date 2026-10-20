public class Instalacion extends Orden {
    private int puntosAInstalar;



public Instalacion(int IDOrden, String nombreCliente, String dirreccionCliente, float horasEstTrabajo, ZonaTrabajo zona, int puntosAInstalar) {
    super(IDOrden, nombreCliente, dirreccionCliente, horasEstTrabajo, zona);
    if (puntosAInstalar <= 0) {
        throw new IllegalArgumentException("Los puntos a instalar deben ser positivos y mayores a cero.");
    }
    this.puntosAInstalar = puntosAInstalar;
}

//getters y setters
public int getPuntosAInstalar() {
    return puntosAInstalar;
}

public int getHorasFacturables() {
        double horasPorPuntos = this.puntosAInstalar * 0.75;
        double maxHoras = Math.max(getHorasEstTrabajo(), horasPorPuntos);
        return (int) Math.ceil(maxHoras); // Redondeo hacia el entero siguiente para facturar horas completas sin importar si fue fracción de hora
    }

@Override
public float calcularCosto() {
    float tarifaBase = 0;
    if (getZonaTrabajo() == ZonaTrabajo.METROPOLITANA) {
        tarifaBase = 100.00f;
    }
    if (getZonaTrabajo() == ZonaTrabajo.PERIFERICA) {
        tarifaBase = 175.00f;
    }
    return tarifaBase + (getHorasFacturables() * 90.00f);
}

@Override 
public int calcularTiempoEstAtencion() {
    if (getZonaTrabajo() == ZonaTrabajo.METROPOLITANA) {
        return 3;
    }
    if(getZonaTrabajo() == ZonaTrabajo.PERIFERICA) {
        return 5;
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
    return "Instalación";
}

@Override 
public String toString(){
    StringBuilder sb = new StringBuilder(super.toString());
    sb.append("========================================\n");
    sb.append("Puntos a instalar: ").append(getPuntosAInstalar()).append("\n");
    sb.append("Horas facturables calculadas: ").append(getHorasFacturables()).append("\n");
    return sb.toString();
}
}
