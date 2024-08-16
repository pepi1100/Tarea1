import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Bebidas extends Comestible{
    private static final Set<String> usedIds = new HashSet<>();
    private final String id;
    private float graduacionAlchol;
    private LocalDate fechaVenc;

    public Bebidas(String id, int idNum, String descripc, int stock, float precioUnid, float gananciaPorcentual, boolean disponibleVenta, float graduacionAlchol, LocalDate fechaVenc,int calorias ) {
        super(id, descripc, stock, precioUnid, gananciaPorcentual, disponibleVenta,fechaVenc,calorias);
        this.id = generateUniqueId(idNum);
        this.graduacionAlchol = graduacionAlchol;
        this.fechaVenc = fechaVenc;

    }

    private String generateUniqueId(int number) {
        String id;
        do {
            String formattedNumber = String.format("%03d", number);
            id = "AC" + formattedNumber;
        } while (usedIds.contains(id));

        usedIds.add(id);
        return id;
    }

    public String getId() {
        return id;
    }

    public void setGraduacionAlchol(float graduacionAlchol) {
        this.graduacionAlchol = graduacionAlchol;
    }
    public float getGraduacionAlchol() {
        return graduacionAlchol;
    }
}
