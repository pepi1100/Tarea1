import java.util.HashSet;
import java.util.Set;

public class ProdLimp extends Producto {
    private static final Set<String> usedIds = new HashSet<>();
    private static final String PREFIX = "AZ";
    private String id;
    private String uso;

    public ProdLimp(int idNum, String descripc, int cantidad, float precioUnid, float gananciaPorcentual, boolean disponibleVenta, String uso) {
        super(idNum, descripc, cantidad, precioUnid);
        this.setDisponibleVenta(disponibleVenta);
        this.id = generateUniqueId(idNum);
        setUso(uso);
        setGananciaPorcentual(gananciaPorcentual);
    }



    private String generateUniqueId(int number) {
        String formattedNumber = String.format("%03d", number);
        String id = PREFIX + formattedNumber;

        if (usedIds.contains(id)) {
            throw new IllegalArgumentException("El ID ya está en uso. Intente con otro número.");
        }
        usedIds.add(id);
        return id;
    }

    @Override
    public void setGananciaPorcentual(float gananciaPorcentual) {
        if (uso.equals("COCINA") || uso.equals("MULTIUSO")) {
            if (gananciaPorcentual > 25) {
                System.out.println("La ganancia para productos de limpieza no puede superar el 25%.");
                super.setGananciaPorcentual(25);
            } else {
                super.setGananciaPorcentual(gananciaPorcentual);
            }
        } else {
            if (gananciaPorcentual < 10) {
                System.out.println("La ganancia para productos de limpieza no puede ser menor al 10%.");
                super.setGananciaPorcentual(10);
            } else if (gananciaPorcentual > 25) {
                System.out.println("La ganancia para productos de limpieza no puede superar el 25%.");
                super.setGananciaPorcentual(25);
            } else {
                super.setGananciaPorcentual(gananciaPorcentual);
            }
        }
    }

    @Override
    public void setDescuento(float descuento) {
        if (descuento > 20) {
            System.out.println("El descuento para productos de limpieza no puede superar el 20%.");
            super.setDescuento(20);
        } else {
            super.setDescuento(descuento);
        }
    }

    public void setUso(String usoDef) {
        switch (usoDef) {
            case "COCINA":
            case "BANIO":
            case "ROPA":
            case "MULTIUSO":
                this.uso = usoDef;
                break;
            default:
                throw new IllegalArgumentException("DEFINA UN USO DE PRODUCTO VÁLIDO (COCINA, BANIO, ROPA o MULTIUSO)");
        }
    }

    public String getUso() {
        return uso;
    }

    @Override
    public String toString() {
        String baseInfo = super.toString();

        //Info específica de ProdLimp
        StringBuilder sb = new StringBuilder(baseInfo);
        sb.append(", Uso: ").append(uso);

        return sb.toString();
    }
}
