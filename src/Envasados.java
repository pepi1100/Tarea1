import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Envasados extends Comestible{

    private static final Set<String> usedIds = new HashSet<>();
    private final String id;
    public boolean importado;
    public String tipoEnvase;
    public LocalDate fechaVenc;


    public Envasados(String id, int idNum, String descripc, int stock, float precioUnid, float gananciaPorcentual, boolean disponibleVenta, boolean importado, String tipoEnvase, LocalDate fechaVenc, int calorias) {
        super(id, descripc, stock, precioUnid, gananciaPorcentual, disponibleVenta, fechaVenc,calorias);
        this.id = generateUniqueId(idNum);
        this.tipoEnvase = tipoEnvase;
        this.fechaVenc = fechaVenc;
        this.importado = importado;
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

    public String getTipoEnvase() {
        return tipoEnvase;
    }
    public String getId() {
        return id;
    }
}
