public abstract class Producto {
    private String id;
    private String descripc;
    private int cantidad;
    private float precioUnid;
    private float gananciaPorcentual;
    private boolean disponibleVenta;
    private float descuento;
    private float precioFinal;

    public Producto(String id,String descripc, int cantidad, float precioUnid){
        this.id = id;
        this.descripc = descripc;
        this.cantidad = cantidad;
        this.precioUnid = precioUnid;
        this.precioFinal = precioUnid - (precioUnid * descuento /100);
    }

    public void setGananciaPorcentual(float gananciaPorcentual) {
        this.gananciaPorcentual = gananciaPorcentual;
    }

    public String getDescripc() {
        return descripc;
    }

    public float getDesc() {
        return descuento;
    }

    public float getPrecioUnid() {
        return precioUnid;
    }

    public void setDescuento(float descuento) {
        if (descuento < 100){
            this.descuento = descuento;
        } else {
            System.out.println("INGRESE UN DESCUENTO VALIDO");
        }

    }
    public float getDescuento() {
        return descuento;
    }

    public float getGananciaPorcentual() {
        return gananciaPorcentual;
    }

    public void setDisponibleVenta(boolean disponibleVenta) {
        this.disponibleVenta = disponibleVenta;
    }

    public boolean getDisponibleVenta(){
        return disponibleVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }
}
