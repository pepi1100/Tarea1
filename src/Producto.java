import java.util.HashSet;
import java.util.Set;

public abstract class Producto {
    private static final Set<String> usedIds = new HashSet<>();
    private String id;
    private String descripc;
    private int cantidad;
    private float precioUnid;
    private float gananciaPorcentual;
    private boolean disponibleVenta;
    private float descuento;
    private float precioFinal;

    public Producto(int idNum, String descripc, int cantidad, float precioUnid) {
        this.id = generateUniqueId(idNum);
        this.descripc = descripc;
        this.cantidad = cantidad;
        this.precioUnid = precioUnid;
        setDescuento(descuento); // Aplicar el descuento correctamente
        actualizarPrecioFinal(); // Actualizar el precio final
    }

    private String generateUniqueId(int number) {
        String formattedNumber = String.format("%03d", number);
        String id = formattedNumber;

        while (usedIds.contains(id)) {
            number++;
            formattedNumber = String.format("%03d", number);
            id = formattedNumber;
            if (number > 999) {
                throw new IllegalStateException("No se pueden generar más IDs únicos.");
            }
        }

        usedIds.add(id);
        return id;
    }

    public  String getId(){
        return id;
    };

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
        if (descuento >= 0 && descuento < 100) {
            this.descuento = descuento;
            actualizarPrecioFinal();
        } else {
            System.out.println("INGRESE UN DESCUENTO VALIDO");
        }
    }

    public float getDescuento() {
        return descuento;
    }

    private void actualizarPrecioFinal() {
        this.precioFinal = precioUnid - (precioUnid * descuento / 100);
    }

    public String getFormattedId(String prefix) {
        return String.format("%s%s", prefix, id);
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getGananciaPorcentual() {
        return gananciaPorcentual;
    }

    public void setDisponibleVenta(boolean disponibleVenta) {
        this.disponibleVenta = disponibleVenta;
    }

    public boolean getDisponibleVenta() {
        return disponibleVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(float precioFinal) {
        this.precioFinal = precioFinal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(getId())
                .append(", Descripción: ").append(getDescripc())
                .append(", Cantidad: ").append(getCantidad())
                .append(", Precio Unidad: ").append(getPrecioUnid())
                .append(", Precio Final: ").append(getPrecioFinal());
        return sb.toString();
    }
}
