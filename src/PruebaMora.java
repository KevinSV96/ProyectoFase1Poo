import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class CalculadoraMora {

    // mora diaria por año (puedes modificar estos valores)
    private Map<Integer, Double> moraPorAnio = new HashMap<>();

    public CalculadoraMora() {
        // valores de ejemplo
        moraPorAnio.put(2024, 0.25); // $0.25 por día en 2024
        moraPorAnio.put(2025, 0.30); // $0.30 por día en 2025
    }

    // configurar/actualizar la mora de un año
    public void configurarMoraDiaria(int anio, double montoPorDia) {
        moraPorAnio.put(anio, montoPorDia);
    }

    // calcula mora a partir de fechas
    public double calcularMora(LocalDate fechaPrevista, LocalDate fechaReal) {
        if (!fechaReal.isAfter(fechaPrevista)) {
            return 0.0; // sin retraso
        }

        long diasRetraso = ChronoUnit.DAYS.between(fechaPrevista, fechaReal);
        int anio = fechaPrevista.getYear();
        double moraDiaria = moraPorAnio.getOrDefault(anio, 0.0);

        return diasRetraso * moraDiaria;
    }
}
