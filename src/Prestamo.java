import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private int id;
    private Ejemplar ejemplar;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;
    private String estado; // "ACTIVO", "DEVUELTO", "MORA"
    private double moraAcumulada;
    
    // Constructor
    public Prestamo(Ejemplar ejemplar, Usuario usuario) {
        this.ejemplar = ejemplar;
        this.usuario = usuario;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucionPrevista = calcularFechaDevolucion();
        this.estado = "ACTIVO";
        this.moraAcumulada = 0.0;
    }
    
    private LocalDate calcularFechaDevolucion() {
        // ADAPTADO: usa los nombres de rol de tu compañero
        int diasPrestamo = switch(usuario.getTipoUsuario()) {
            case "Maestro" -> 15;    // Profesores: 15 días
            case "Estudiante" -> 7;  // Alumnos: 7 días
            case "Admin" -> 10;      // Admins: 10 días
            default -> 5;
        };
        return fechaPrestamo.plusDays(diasPrestamo);
    }
    
    // Método para verificar si el ejemplar está disponible (ADAPTADO)
    private boolean ejemplarDisponible() {
        return "Disponible".equals(ejemplar.getEstado());
    }
    
    // Método para prestar ejemplar (ADAPTADO)
    private void prestarEjemplar() {
        ejemplar.setEstado("Prestado");
    }
    
    // Método para devolver ejemplar (ADAPTADO)
    private void devolverEjemplar() {
        ejemplar.setEstado("Disponible");
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Ejemplar getEjemplar() { return ejemplar; }
    public void setEjemplar(Ejemplar ejemplar) { this.ejemplar = ejemplar; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucionPrevista() { return fechaDevolucionPrevista; }
    
    public LocalDate getFechaDevolucionReal() { return fechaDevolucionReal; }
    public void setFechaDevolucionReal(LocalDate fecha) { 
        this.fechaDevolucionReal = fecha; 
        this.estado = "DEVUELTO";
    }
    
    public String getEstado() { return estado; }
    public double getMoraAcumulada() { return moraAcumulada; }
    public void setMoraAcumulada(double mora) { this.moraAcumulada = mora; }
    
    // Métodos de negocio
    public boolean estaVencido() {
        return estado.equals("ACTIVO") && LocalDate.now().isAfter(fechaDevolucionPrevista);
    }
    
    public long diasVencido() {
        if (!estaVencido()) return 0;
        return ChronoUnit.DAYS.between(fechaDevolucionPrevista, LocalDate.now());
    }
    
    @Override
    public String toString() {
        return String.format("Préstamo #%d - Ejemplar %s para %s (Estado: %s)", 
            id, ejemplar.getCodigoEjemplar(), usuario.getNombreCompleto(), estado);
    }
}
