import java.time.LocalDate;

public class PruebaMora {

    // ====== Clases simples solo para la prueba ======
    static class Usuario {
        int id;
        String nombre;
        double moraTotal;

        Usuario(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
            this.moraTotal = 0.0;
        }
    }

    static class Prestamo {
        int id;
        Usuario usuario;
        String tituloLibro;
        LocalDate fechaPrevista;
        LocalDate fechaReal;
        double mora;

        Prestamo(int id, Usuario usuario, String tituloLibro,
                 LocalDate fechaPrevista, LocalDate fechaReal) {
            this.id = id;
            this.usuario = usuario;
            this.tituloLibro = tituloLibro;
            this.fechaPrevista = fechaPrevista;
            this.fechaReal = fechaReal;
        }
    }

    public static void main(String[] args) {

        CalculadoraMora calcMora = new CalculadoraMora();

        // Usuarios por defecto
        Usuario u1 = new Usuario(1, "Juan Pérez");
        Usuario u2 = new Usuario(2, "María López");

        // Préstamos por defecto relacionados a los usuarios
        Prestamo p1 = new Prestamo(
                101,
                u1,
                "El Quijote",
                LocalDate.of(2024, 11, 1),   // fecha prevista
                LocalDate.of(2024, 11, 5)    // fecha real (4 días tarde)
        );

        Prestamo p2 = new Prestamo(
                102,
                u1,
                "Cien años de soledad",
                LocalDate.of(2024, 10, 20),
                LocalDate.of(2024, 10, 20)   // sin retraso
        );

        Prestamo p3 = new Prestamo(
                201,
                u2,
                "Introducción a Java",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 18)    // 8 días tarde
        );

        // Calcular mora de cada préstamo
        calcularYMostrarMora(calcMora, p1);
        calcularYMostrarMora(calcMora, p2);
        calcularYMostrarMora(calcMora, p3);

        // Mostrar mora total por usuario
        System.out.println("\n=== Resumen por usuario ===");
        System.out.println(u1.nombre + " - Mora total: $" + u1.moraTotal);
        System.out.println(u2.nombre + " - Mora total: $" + u2.moraTotal);
    }

    private static void calcularYMostrarMora(CalculadoraMora calcMora, Prestamo p) {
        p.mora = calcMora.calcularMora(p.fechaPrevista, p.fechaReal);
        p.usuario.moraTotal += p.mora;

        System.out.println("Préstamo ID: " + p.id);
        System.out.println("Usuario   : " + p.usuario.nombre);
        System.out.println("Libro     : " + p.tituloLibro);
        System.out.println("Prevista  : " + p.fechaPrevista);
        System.out.println("Real      : " + p.fechaReal);
        System.out.println("Mora      : $" + p.mora);
        System.out.println("------------------------");
    }
}
