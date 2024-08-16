import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Tienda pulperia = new Tienda("La Pulperia",9999, 100000);// TODO HACER UN JSON PARA CREAR PRODUCTOS

        Bebidas jugoNaranja = new Bebidas(
                "B001",      // id
                1,           // idNum
                "Jugo de Naranja Natural",  // descripc
                50,          // stock
                1.50f,       // precioUnid
                20.0f,       // gananciaPorcentual
                true,        // disponibleVenta
                0.0f,        // graduacionAlchol (sin alcohol)
                LocalDate.of(2025, 12, 31), // fechaVenc
                120          // calorias
        );

        // Instancia de bebida 2: Cerveza
        Bebidas cerveza = new Bebidas(
                "B002",      // id
                2,           // idNum
                "Cerveza Rubia", // descripc
                200,         // stock
                2.50f,       // precioUnid
                30.0f,       // gananciaPorcentual
                true,        // disponibleVenta
                5.0f,        // graduacionAlchol
                LocalDate.of(2025, 6, 15), // fechaVenc
                150          // calorias
        );

        // Instancia de bebida 3: Agua Mineral
        Bebidas aguaMineral = new Bebidas(
                "B003",      // id
                3,           // idNum
                "Agua Mineral", // descripc
                100,         // stock
                1.00f,       // precioUnid
                15.0f,       // gananciaPorcentual
                true,        // disponibleVenta
                0.0f,        // graduacionAlchol (sin alcohol)
                LocalDate.of(2026, 3, 30), // fechaVenc
                0            // calorias
        );
        pulperia.comprarProducto(jugoNaranja, ItemType.BEBIDA, true);
        pulperia.getBebidasGondola();

//        pulperia.agregarProducto(Jamon);
//        pulperia.agregarProducto(Lisoform);
//        System.out.println(pulperia.getProductos());
    }
}