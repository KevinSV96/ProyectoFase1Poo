public class UsuarioPrueba {

    public static void main(String[] args) {

        // ***Configuración de los usuarios de Prueba***

        // El autorizador con permisos ***tiene que ser el único que puede hacer cambios***
        Usuario adminPrincipal = new Usuario(1, "Administrador Principal ",
                "administrator@escuela.com", "superprincipal", "Admin");

        // Los usuarios sin permisos que intentarán hacer cambios
        Usuario maestroJohn = new Usuario(2, "John Maestro", "jonh@escuela.com", "contrama",
                "Maestro");
        Usuario estudianteSofia = new Usuario(3, "Sofia Estudiante", "sofia@escuela.com", "clavealum",
                "Estudiante");

        System.out.println("*** INICIO DE PRUEBAS DE LA CLASE USUARIO ***");
        System.out.println("*******************************************\n");


        // *** Prueba 1: cambiar el rol *éxito*
        // Verificamos si el Admin puede cambiar el rol de un Alumno a Maestro
        System.out.println(" Prueba 1: el administrator cambia rol de Sofia (Éxito esperado):");
        estudianteSofia.asignarRol("Maestro", adminPrincipal);
        System.out.println("   Rol actual de Sofia: " + estudianteSofia.getTipoUsuario() + "\n");

        // *** Prueba 2: cambiar rol **Falla por Permisos** ***
        // Verificamos si un Maestro puede cambiar el rol de Sofia
        System.out.println("-> Prueba 2: Maestro intenta cambiar rol de Sofia (Falla esperada):");
        // Pedro (Maestro) intenta cambiar el rol, pero no tiene el permiso de "Admin"
        estudianteSofia.asignarRol("Admin", maestroJohn);
        System.out.println("   Rol actual de Sofia: " + estudianteSofia.getTipoUsuario() + "\n");

        // *** Prueba 3: Cambio de Contraseña **Éxito**) ***
        // Verificamos si el Admin puede restablecer la contraseña de Pedro
        System.out.println("-> Prueba 3: administrator restablece clave de John (Éxito esperado):");
        maestroJohn.restablecerContrasena("Temporal123", adminPrincipal);
        System.out.println("   Nueva clave para John (simulación de restablecimiento).\n");



        // *** Prueba 4: estado de la cuenta (Conexión con la Persona 4) ***
        // Simulación: La lógica de mora (Persona 4) detecta que John tiene un libro vencido.
        System.out.println("\n-> Prueba 4: Simulación de bloqueo por Mora:");
        maestroJohn.setEstadoCuenta("Bloqueado");
        System.out.println("   Estado de la cuenta de John: " + maestroJohn.getEstadoCuenta());
    }
}