import java.time.LocalDate;

public class PrestamoPrueba {

    public static void main(String[] args) {
        System.out.println("*** PRUEBAS DEL SISTEMA DE PRÉSTAMOS - ADAPTADO ***");
        System.out.println("==================================================\n");

        // Crear usuarios usando la clase de tu compañero
        Usuario admin = new Usuario(1, "Admin Principal", "admin@escuela.com", "clave123", "Admin");
        Usuario maestro = new Usuario(2, "Profesor García", "garcia@escuela.com", "clave456", "Maestro");
        Usuario estudiante = new Usuario(3, "Estudiante López", "lopez@escuela.com", "clave789", "Estudiante");
        Usuario estudianteBloqueado = new Usuario(4, "Estudiante Mora", "mora@escuela.com", "clave000", "Estudiante");
        estudianteBloqueado.setEstadoCuenta("Bloqueado"); // Simula mora

        // Crear ejemplares usando la CLASE REAL de tu compañero
        Ejemplar libro1 = new Ejemplar("E001", "DOC001", "LIB-001", "Disponible");
        Ejemplar libro2 = new Ejemplar("E002", "DOC002", "LIB-002", "Disponible");
        Ejemplar libro3 = new Ejemplar("E003", "DOC003", "LIB-003", "Disponible");
        Ejemplar libroPrestado = new Ejemplar("E004", "DOC004", "LIB-004", "Prestado");

        // Crear gestor de préstamos
        GestorPrestamos gestor = new GestorPrestamos();

        System.out.println("--- PRUEBA 1: Préstamo exitoso a Maestro ---");
        try {
            Prestamo prestamo1 = gestor.realizarPrestamo(libro1, maestro);
            System.out.println("✅ ÉXITO: " + prestamo1);
            System.out.println("   Estado ejemplar: " + libro1.getEstado());
            System.out.println("   Fecha devolución: " + prestamo1.getFechaDevolucionPrevista());
        } catch (IllegalStateException e) {
            System.out.println("❌ FALLA: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA 2: Préstamo exitoso a Estudiante ---");
        try {
            Prestamo prestamo2 = gestor.realizarPrestamo(libro2, estudiante);
            System.out.println("✅ ÉXITO: " + prestamo2);
            System.out.println("   Estado ejemplar: " + libro2.getEstado());
            System.out.println("   Fecha devolución: " + prestamo2.getFechaDevolucionPrevista());
        } catch (IllegalStateException e) {
            System.out.println("❌ FALLA: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA 3: Usuario con mora (debe fallar) ---");
        try {
            Prestamo prestamo3 = gestor.realizarPrestamo(libro3, estudianteBloqueado);
            System.out.println("✅ ÉXITO: " + prestamo3);
        } catch (IllegalStateException e) {
            System.out.println("❌ FALLA ESPERADA: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA 4: Ejemplar ya prestado (debe fallar) ---");
        try {
            Prestamo prestamo4 = gestor.realizarPrestamo(libroPrestado, maestro);
            System.out.println("✅ ÉXITO: " + prestamo4);
        } catch (IllegalStateException e) {
            System.out.println("❌ FALLA ESPERADA: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA 5: Devolución exitosa ---");
        try {
            // Devolver el primer préstamo
            gestor.devolverPrestamo(1);
            System.out.println("✅ ÉXITO: Préstamo #1 devuelto correctamente");
            System.out.println("   Estado ejemplar después de devolución: " + libro1.getEstado());
        } catch (Exception e) {
            System.out.println("❌ FALLA: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA 6: Búsqueda de préstamo ---");
        Prestamo prestamoEncontrado = gestor.buscarPrestamo(2);
        if (prestamoEncontrado != null) {
            System.out.println("✅ ÉXITO: Préstamo encontrado - " + prestamoEncontrado);
            System.out.println("   Días vencido: " + prestamoEncontrado.diasVencido());
            System.out.println("   Está vencido: " + prestamoEncontrado.estaVencido());
        } else {
            System.out.println("❌ FALLA: Préstamo no encontrado");
        }

        System.out.println("\n*** RESUMEN FINAL ***");
        System.out.println("Total préstamos en sistema: " + gestor.getTodosLosPrestamos().size());
        System.out.println("Préstamos activos: " + gestor.getPrestamosActivos().size());
        
        // Mostrar todos los préstamos
        System.out.println("\n--- LISTA COMPLETA DE PRÉSTAMOS ---");
        gestor.getTodosLosPrestamos().forEach(System.out::println);
    }
}
