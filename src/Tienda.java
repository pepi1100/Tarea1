import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tienda {
    private String nombre;
    private int prodMax;
    private int prodNumStat;
    private List<Producto> envasadosGondola = new ArrayList<>();
    private List<Producto> bebidasGondola = new ArrayList<>();
    private List<Producto> limpiezaGondola = new ArrayList<>();
    private float saldoCaja;

    public Tienda(String nombre, int prodMax, float saldoCaja) {
        this.nombre = nombre;
        this.prodMax = prodMax;
        this.saldoCaja = saldoCaja;
    }

    public void comprarProducto(Producto item, ItemType itemType, boolean disponibleVenta) {
        if (this.prodMax < this.prodNumStat + item.getCantidad()) {
            throw new IllegalArgumentException("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock");
        }
        if ((item.getPrecioUnid() * item.getCantidad() > this.saldoCaja)) {
            throw new IllegalArgumentException("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja");
        }

        this.prodNumStat += item.getCantidad();
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
        getSaldoCaja();
    }

    public void comprarProducto(Producto item, ItemType itemType) {
        comprarProducto(item, itemType, true); // Booleano predeterminado del método
    }

    public List<Producto> getGondola(ItemType itemType) {
        switch (itemType) {
            case ENVASADO:
                return List.copyOf(envasadosGondola);
            case BEBIDA:
                return List.copyOf(bebidasGondola);
            case LIMPIEZA:
                return List.copyOf(limpiezaGondola);
            default:
                System.out.println("PRODUCTO NO RECONOCIDO");
                return new ArrayList<>();
        }
    }

    public void venderProducto(List<Producto> productos, List<Integer> cantidades) {
        boolean hayStockMenor = false;

        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            int cantidad = cantidades.get(i);

            // Verificar si el producto está habilitado para la venta
            if (!producto.getDisponibleVenta()) {
                System.out.println("El producto " + producto.getId() + " " + producto.getDescripc() + " no se encuentra disponible.");
                continue;
            }

            // Verificar si hay suficiente stock
            if (cantidad > producto.getCantidad()) {
                hayStockMenor = true;
                cantidad = producto.getCantidad(); // Vender solo la cantidad disponible
            }

            // Actualizar stock y caja
            producto.setCantidad(producto.getCantidad() - cantidad);
            this.saldoCaja += producto.getPrecioFinal() * cantidad;

            // Eliminar producto de la góndola si ya no tiene stock
            if (producto.getCantidad() == 0) {
                removerProductoDeGondola(producto);
            }

            // Imprimir detalle de la venta
            System.out.println(producto.getId() + " " + producto.getDescripc() + " " + cantidad + " x " + producto.getPrecioFinal());
        }

        // Mensaje informativo si hubo productos con stock menor al solicitado
        if (hayStockMenor) {
            System.out.println("Hay productos con stock disponible menor al solicitado.");
        }
    }

    private void removerProductoDeGondola(Producto producto) {
        if (producto instanceof Envasados) {
            envasadosGondola.remove(producto);
        } else if (producto instanceof Bebidas) {
            bebidasGondola.remove(producto);
        } else if (producto instanceof ProdLimp) {
            limpiezaGondola.remove(producto);
        }
    }

    public List<String> obtenerComestiblesConMenorDescuento(float porcentajeDescuento) {
        List<Comestible> comestibles = new ArrayList<>();
        comestibles.addAll(envasadosGondola.stream().filter(p -> p instanceof Comestible).map(p -> (Comestible) p).toList());
        comestibles.addAll(bebidasGondola.stream().filter(p -> p instanceof Comestible).map(p -> (Comestible) p).toList());

        return comestibles.stream()
                .filter(c -> !(c instanceof Envasados && ((Envasados) c).isImportado()))
                .filter(c -> c.getDescuento() < porcentajeDescuento)
                .sorted((c1, c2) -> Float.compare(c1.getPrecioFinal(), c2.getPrecioFinal()))
                .map(c -> c.getDescripc().toUpperCase())
                .collect(Collectors.toList());
    }

    public void imprimirGondola(ItemType itemType) {
        List<Producto> gondola;
        switch (itemType) {
            case ENVASADO:
                gondola = envasadosGondola;
                break;
            case BEBIDA:
                gondola = bebidasGondola;
                break;
            case LIMPIEZA:
                gondola = limpiezaGondola;
                break;
            default:
                System.out.println("TIPO DE PRODUCTO NO RECONOCIDO");
                return;
        }

        System.out.println(itemType + " en góndola:");
        for (Producto producto : gondola) {
            System.out.println(producto);
        }
    }

    public List<Producto> getEnvasadosGondola() {
        return List.copyOf(envasadosGondola);
    }

    public List<Producto> getLimpiezaGondola() {
        return List.copyOf(limpiezaGondola);
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

    public String getSaldoCaja() {
        return "Saldo actual en caja es de: " + saldoCaja;
    }
}
