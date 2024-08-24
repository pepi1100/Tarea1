import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Bebidas extends Comestible {
    private static final Set<String> usedIds = new HashSet<>();
    private final String id;  // ID con prefijo
    private float graduacionAlcohol;
    private LocalDate fechaVenc;
    private int calorias;

    public Bebidas(int idNum, String descripc, int stock, float precioUnid, float gananciaPorcentual, boolean disponibleVenta,
                   float graduacionAlcohol, LocalDate fechaVenc, int calorias) {
        super(idNum, descripc, stock, precioUnid, gananciaPorcentual, disponibleVenta, fechaVenc, calorias);
        this.id = generateUniqueId(idNum);
        this.graduacionAlcohol = graduacionAlcohol;
        this.calorias = calcularCalorias(graduacionAlcohol);
        this.fechaVenc = fechaVenc;
        calcularPrecioFinal();
    }

    private String generateUniqueId(int number) {
        String id;
        do {
            id = "AC" + String.format("%03d", number);  // Prefijo "AC" para bebidas
        } while (usedIds.contains(id));

        usedIds.add(id);
        return id;
    }

    private static int calcularCalorias(float graduacionAlcohol) {
        if (graduacionAlcohol >= 0 && graduacionAlcohol <= 2) {
            return (int) graduacionAlcohol;
        } else if (graduacionAlcohol > 2 && graduacionAlcohol <= 4.5) {
            return (int) (graduacionAlcohol * 1.25f);
        } else if (graduacionAlcohol > 4.5) {
            return (int) (graduacionAlcohol * 1.5f);
        }
        return 0;
    }

    @Override
    public void setDescuento(float descuento) {
        if (descuento > 10) {
            System.out.println("El descuento no puede superar el 10% para bebidas.");
            super.setDescuento(10);
        } else {
            super.setDescuento(descuento);
        }
    }

    @Override
    public void calcularPrecioFinal() {
        float precioConDescuento = getPrecioUnid() - (getPrecioUnid() * Math.min(getDescuento(), 10) / 100);
        setPrecioFinal(precioConDescuento);
    }



    public float getGraduacionAlcohol() {
        return graduacionAlcohol;
    }

    public void setGraduacionAlcohol(float graduacionAlcohol) {
        this.graduacionAlcohol = graduacionAlcohol;
        this.calorias = calcularCalorias(graduacionAlcohol);
        calcularPrecioFinal();
    }

    public int getCalorias() {
        return calorias;
    }

    public LocalDate getFechaVenc() {
        return fechaVenc;
    }

    public void setFechaVenc(LocalDate fechaVenc) {
        this.fechaVenc = fechaVenc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", Graduación: ").append(graduacionAlcohol)
                .append(", Fecha de Vencimiento: ").append(fechaVenc)
                .append(", Calorías: ").append(calorias);
        return sb.toString();
    }
}
