import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Envasados extends Comestible {

    private static final Set<String> usedIds = new HashSet<>();
    private final String id;
    private final boolean importado;
    private final String tipoEnvase;
    private final LocalDate fechaVenc;

    public Envasados(int idNum, String descripc, int stock, float precioUnid, float gananciaPorcentual, boolean disponibleVenta, boolean importado, String tipoEnvase, LocalDate fechaVenc, int calorias) {
        super(idNum, descripc, stock, precioUnid, gananciaPorcentual, disponibleVenta, fechaVenc, calorias);
        this.id = generateUniqueId(idNum);
        this.tipoEnvase = tipoEnvase;
        this.fechaVenc = fechaVenc;
        this.importado = importado;
        calcularPrecioFinal();
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


    @Override
    public void setDescuento(float descuento) {
        if (descuento > 15) {
            System.out.println("El descuento no puede superar el 15% para productos envasados.");
            super.setDescuento(15);
        } else {
            super.setDescuento(descuento);
        }
        calcularPrecioFinal();
    }

    @Override
    public void calcularPrecioFinal() {
        float precioFinal =  getPrecioUnid() - (getPrecioUnid() * Math.min(getDescuento(), 15) / 100);

        if (importado) {
            precioFinal *= 1.12f;
        }

        setPrecioFinal(precioFinal);
    }

    public String getTipoEnvase() {
        return tipoEnvase;
    }

    public String getId() {
        return id;
    }

    public boolean isImportado() {
        return importado;
    }

    public LocalDate getFechaVenc() {
        return fechaVenc;
    }

    @Override
    public String toString() {
        String baseInfo = super.toString();

        //Info específica de Envasados
        StringBuilder sb = new StringBuilder(baseInfo);
        sb.append(", Tipo de Envase: ").append(tipoEnvase)
                .append(", Fecha de Vencimiento: ").append(fechaVenc)
                .append(", Importado: ").append(importado ? "Sí" : "No");

        return sb.toString();
    }
}
