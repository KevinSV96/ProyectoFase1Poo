import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorPrestamos {
    private List<Prestamo> prestamos;
    private int contadorId;
    
    public GestorPrestamos() {
        this.prestamos = new ArrayList<>();
        this.contadorId = 1;
    }
    
    public Prestamo realizarPrestamo(Ejemplar ejemplar, Usuario usuario) {
        // Validaciones previas
        if (!ejemplar.estaDisponible()) {
            throw new IllegalStateException("El ejemplar no está disponible");
        }
        
        if (usuario.getEstadoCuenta().equals("Bloqueado")) {
            throw new IllegalStateException("Usuario tiene mora pendiente - Cuenta Bloqueada");
        }
        
        // Crear préstamo
        Prestamo nuevoPrestamo = new Prestamo(ejemplar, usuario);
        nuevoPrestamo.setId(contadorId++);
        
        // Actualizar estados
        ejemplar.prestar();
        prestamos.add(nuevoPrestamo);
        
        return nuevoPrestamo;
    }
    
    public void devolverPrestamo(int idPrestamo) {
        Prestamo prestamo = buscarPrestamo(idPrestamo);
        if (prestamo != null) {
            prestamo.setFechaDevolucionReal(LocalDate.now());
            prestamo.getEjemplar().devolver();
            System.out.println("   [DEBUG] Préstamo #" + idPrestamo + " marcado como devuelto");
        } else {
            throw new IllegalArgumentException("Préstamo no encontrado: #" + idPrestamo);
        }
    }
    
    public Prestamo buscarPrestamo(int id) {
        return prestamos.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
    }
    
    // Método adicional para pruebas
    public List<Prestamo> getPrestamosActivos() {
        return prestamos.stream()
            .filter(p -> p.getEstado().equals("ACTIVO"))
            .collect(Collectors.toList());
    }
    
    // Método para ver todos los préstamos
    public List<Prestamo> getTodosLosPrestamos() {
        return new ArrayList<>(prestamos);
    }
}
