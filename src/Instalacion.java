public class Instalacion extends Orden {
    private int puntosAInstalar;



public Instalacion(int IDOrden, String nombreCliente, String dirreccionCliente, float horasEstTrabajo, ZonaTrabajo zona, int puntosAInstalar) {
    super(IDOrden, nombreCliente, dirreccionCliente, horasEstTrabajo, zona);
    if (puntosAInstalar <= 0) {
        throw new IllegalArgumentException("Los puntos a instalar deben ser positivos y mayores a cero.");
    }
    this.puntosAInstalar = puntosAInstalar;
}

@Override
public float calcularCosto() {
    if (getZonaTrabajo() == ZonaTrabajo.METROPOLITANA) {
        return 100;
    }
    if(getZonaTrabajo() == ZonaTrabajo.PERIFERICA) {
        return 175;
    }
    return 0;
}

@Override 
public float calcularCostoExtra() {

}

@Override 
public int calcularTiempoEstAtencion() {

}

@Override 
public int getLimiteVisitas() {
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
    StringBuilder sb = new StringBuilder();
    sb.append("========================================\n");
    sb.append()
}

}
