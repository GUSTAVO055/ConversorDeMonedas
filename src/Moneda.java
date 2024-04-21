public class Moneda {
    private double cotizacion;

    public Moneda(double cotizacion) {
        this.cotizacion = cotizacion;
    }

    public double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(double cotizacion) {
        this.cotizacion = cotizacion;
    }

    public double convertir(double suma){
        double resultadoConversion=cotizacion * suma;
        return resultadoConversion;
    }
    public double cambiarADolares(double suma){
        double resultadoDolares=suma/cotizacion;
        return resultadoDolares;
    }
}
