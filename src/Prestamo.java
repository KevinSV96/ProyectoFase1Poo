import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private int id;
    private Ejemplar ejemplar;
    private Usuario usuario; // Usa la clase Usuario de tu compañero
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;
    private String estado; // "ACTIVO", "DEVUELTO", "MORA"
    private double moraAcumulada;
    
    // Constructor adaptado
    public Prestamo(Ejemplar ejemplar, Usuario usuario) {
        this.ejemplar = ejemplar;
        this.usuario = usuario;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucionPrevista = calcularFechaDevolucion();
        this.estado = "ACTIVO";
        this.moraAcumulada = 0.0;
    }
    
    private LocalDate calcularFechaDevolucion() {
        // ADAPTACIÓN: usa los nombres de rol de tu compañero
        int diasPrestamo = switch(usuario.getTipoUsuario()) {
            case "Maestro" -> 15;    // Profesores: 15 días
            case "Estudiante" -> 7;  // Alumnos: 7 días
            case "Admin" -> 10;      // Admins: 10 días
            default -> 5;
        };
        return fechaPrestamo.plusDays(diasPrestamo);
    }
    
    // Método clave para validaciones - ADAPTADO
    public boolean usuarioPuedePrestar() {
        // Usa los métodos exactos de tu compañero
        return usuario.getEstadoCuenta().equals("Activo") && 
               !usuarioTieneMora() &&
               !usuarioAlcanzoLimite();
    }
    
    private boolean usuarioTieneMora() {
        // Esto se conectará con el compañero 4
        return usuario.getEstadoCuenta().equals("Bloqueado");
    }
    
    private boolean usuarioAlcanzoLimite() {
        // Lógica simplificada - puedes ajustar
        int limite = switch(usuario.getTipoUsuario()) {
            case "Maestro" -> 5;
            case "Estudiante" -> 3;
            case "Admin" -> 10;
            default -> 1;
        };
        // Aquí necesitarías acceso a la lista de préstamos activos del usuario
        return false; // Placeholder
    }
    
    // Getters y Setters (mantener igual)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Ejemplar getEjemplar() { return ejemplar; }
    public Usuario getUsuario() { return usuario; }
    
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucionPrevista() { return fechaDevolucionPrevista; }
    
    public LocalDate getFechaDevolucionReal() { return fechaDevolucionReal; }
    public void setFechaDevolucionReal(LocalDate fecha) { 
        this.fechaDevolucionReal = fecha; 
        this.estado = "DEVUELTO";
    }
    
    public String getEstado() { return estado; }
    public double getMoraAcumulada() { return moraAcumulada; }
    
    // Métodos de negocio (mantener igual)
    public boolean estaVencido() {
        return estado.equals("ACTIVO") && LocalDate.now().isAfter(fechaDevolucionPrevista);
    }
    
    public long diasVencido() {
        if (!estaVencido()) return 0;
        return ChronoUnit.DAYS.between(fechaDevolucionPrevista, LocalDate.now());
    }
    
    @Override
    public String toString() {
        return String.format("Préstamo #%d - %s para %s (Estado: %s)", 
            id, ejemplar.getTitulo(), usuario.getNombreCompleto(), estado);
    }
}
