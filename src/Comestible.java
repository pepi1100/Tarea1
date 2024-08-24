import java.time.LocalDate;


public abstract class Comestible extends Producto {
    private LocalDate fechaVenc;
    private int calorias;

    public Comestible(int idNum, String descripc, int cantidad, float precioUnid, float gananciaPorcentual, boolean disponibleVenta, LocalDate fechaVenc, int calorias) {
        super(idNum, descripc, cantidad, precioUnid);
        if (fechaVenc.isAfter(LocalDate.now())) {
            this.fechaVenc = fechaVenc;
        } else {
            throw new IllegalArgumentException("La fecha de vencimiento debe ser una fecha futura.");
        }
        this.calorias = calorias;
    }




    public LocalDate getFechaVenc() {
        return fechaVenc;
    }

    public int getCalorias() {
        return calorias;
    }

    public abstract void calcularPrecioFinal();
}
