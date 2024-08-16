
import java.util.ArrayList;
import java.util.List;


public class Tienda {
    private String nombre;
    private int prodMax;
    private int prodNumStat;
    private List<Object> envasadosGondola = new ArrayList<>();
    private List<Object> bebidasGondola = new ArrayList<>();
    private List<Object> limpiezaGondola = new ArrayList<>();

    private float saldoCaja;
    public Tienda(String nombre, int prodMax, float saldoCaja) {
        this.nombre = nombre;
        this.prodMax = prodMax;
        this.saldoCaja = saldoCaja;
    }

    public void comprarProducto(Producto item, ItemType itemType,boolean disponibleVenta) {

        if (this.prodMax < this.prodNumStat + item.getCantidad()) {
            throw new IllegalArgumentException("SUPERA EL LIMITE DE STOCK");
        }
        if ((item.getPrecioUnid() * item.getCantidad() > this.saldoCaja)){
            throw new IllegalArgumentException("El producto no podrá ser agregado a la  tienda por saldo insuficiente en la caja");
        }
        this.prodNumStat+= item.getCantidad();
        this.saldoCaja -= item.getPrecioUnid() * item.getCantidad();
        item.setDisponibleVenta(disponibleVenta);

        switch (itemType) {
            case ENVASADO:
                envasadosGondola.add(item);
                break;
            case BEBIDA:
                bebidasGondola.add(item);
                break;
            case LIMPIEZA:
                limpiezaGondola.add(item);
                break;
            default:
                System.out.println("PRODUCTO NO RECONOCIDO");
        }
    }

    public void comprarProducto(Producto item,ItemType itemType ){
        comprarProducto(item, itemType, true); // Booleano predeterminado del metodo
    }

    public List<Object> getBebidasGondola() {
        System.out.println(bebidasGondola);
        return bebidasGondola;
    }

    public List<Object> getEnvasadosGondola() {
        return envasadosGondola;
    }

    public List<Object> getLimpiezaGondola() {
        return limpiezaGondola;
    }

    public String getNombre() {
        return nombre;
    }

    public int getProdMax() {
        return prodMax;
    }

    public int getProdNumStat() {
        return prodNumStat;
    }

    public float getSaldoCaja() {
        return saldoCaja;
    }

}