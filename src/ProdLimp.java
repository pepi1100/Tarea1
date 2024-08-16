import java.util.HashSet;
import java.util.Set;

public class ProdLimp extends Producto{
    private static final Set<String> usedIds = new HashSet<>();
    private final String id;
    private String uso;

    public ProdLimp(String id, int idNum, String descripc, int cantidad, float precioUnid, float gananciaPorcentual, boolean disponibleVenta, String uso) {
        super(id, descripc, cantidad, precioUnid);
        this.setDisponibleVenta(disponibleVenta);
        this.id = generateUniqueId(idNum);
        this.uso= uso;
        setGananciaPorcentual(gananciaPorcentual);
    }

    @Override
    public void setGananciaPorcentual(float gananciaPorcentual) {
        super.setGananciaPorcentual(gananciaPorcentual);
    }

    private String generateUniqueId(int number) {
        String id;
        do {
            String formattedNumber = String.format("%03d", number);
            id = "AB" + formattedNumber;
        } while (usedIds.contains(id));
        usedIds.add(id);
        return id;
    }
    public void setUso(String usoDef) {
        switch (usoDef){
            case "COCINA":
            case "BANIO":
            case "ROPA":
            case "MULTIUSO":
                this.uso = usoDef;
                break;
            default:
                throw new IllegalArgumentException("DEFINA UN USO DE PRODUCTO VALIDO (COCINA, BANIO, ROPA o MULTIUSO)");
        }
    }
    public String getUso() {
        return uso;
    }

    public String getId() {
        return id;
    }
}
