import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestTienda {
    public static void main(String[] args) {

        Tienda tienda = new Tienda("SuperTienda", 1000, 15000.0f);


        Bebidas bebida1 = new Bebidas(1, "Cerveza", 10, 50.0f, 15.0f, true, 4.0f, LocalDate.of(2024, 12, 31), 100);
        Bebidas bebida2 = new Bebidas(2, "Cerveza", 10, 50.0f, 15.0f, true, 4.0f, LocalDate.of(2024, 12, 31), 100);

        Envasados envasado1 = new Envasados(3, "Aceite", 15, 100.0f, 20.0f, true, false, "Plástico", LocalDate.of(2025, 1, 31), 900);
        Envasados envasado2 = new Envasados(4, "Mermelada", 5, 150.0f, 15.0f, true, true, "Vidrio", LocalDate.of(2025, 1, 31), 200);

        ProdLimp limpieza1 = new ProdLimp(5, "Detergente", 25, 80.0f, 15.0f, true, "COCINA");
        ProdLimp limpieza2 = new ProdLimp(6, "Lavandina", 30, 50.0f, 10.0f, true, "MULTIUSO");


        tienda.comprarProducto(bebida1, ItemType.BEBIDA);
        tienda.comprarProducto(bebida2, ItemType.BEBIDA);
        tienda.comprarProducto(envasado1, ItemType.ENVASADO);
        tienda.comprarProducto(envasado2, ItemType.ENVASADO);
        tienda.comprarProducto(limpieza1, ItemType.LIMPIEZA);
        tienda.comprarProducto(limpieza2, ItemType.LIMPIEZA);


        System.out.println("Estado inicial de la tienda:");
        System.out.println("Saldo en caja: " + tienda.getSaldoCaja());
        imprimirEstadoGondola(tienda);


        System.out.println("\nVenta 1: Vender 5 unidades de Cerveza y 10 unidades de Refresco");
        tienda.venderProducto(Arrays.asList(bebida1, bebida2), Arrays.asList(5, 10));
        System.out.println("Saldo en caja: " + tienda.getSaldoCaja());
        imprimirEstadoGondola(tienda);

        System.out.println("\nVenta 2: Intentar vender 20 unidades de Mermelada (excediendo stock)");
        tienda.venderProducto(List.of(envasado2), List.of(20));
        System.out.println("Saldo en caja: " + tienda.getSaldoCaja());
        imprimirEstadoGondola(tienda);

        System.out.println("\nVenta 3: Intentar vender Detergente (producto no disponible)");
        limpieza1.setDisponibleVenta(false);
        tienda.venderProducto(List.of(limpieza1), List.of(5));
        System.out.println("Saldo en caja: " + tienda.getSaldoCaja());
        imprimirEstadoGondola(tienda);


        System.out.println("\nObtener comestibles no importados con descuento menor al 15%");
        List<String> comestiblesConMenorDescuento = tienda.obtenerComestiblesConMenorDescuento(15.0f);
        System.out.println(comestiblesConMenorDescuento);
    }

    private static void imprimirEstadoGondola(Tienda tienda) {
        System.out.println("Bebidas en góndola:");
        tienda.getGondola(ItemType.BEBIDA).forEach(System.out::println);

        System.out.println("Envasados en góndola:");
        tienda.getGondola(ItemType.ENVASADO).forEach(System.out::println);

        System.out.println("Productos de limpieza en góndola:");
        tienda.getGondola(ItemType.LIMPIEZA).forEach(System.out::println);
    }
}
