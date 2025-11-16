public class Usuario {

// Datos
    private int idUsuario;
    private String nombreCompleto;
    private String correo;
    private String contrasena;

    // Control de acceso para el usuario
    private String tipoUsuario; //  validaremos si es *Administrator*, *Maestro*, o *Estudiante*
    private String estadoCuenta; // activo o bloqueado **si tiene pagos vencidos**


    // **Constructor** * **Se crea una ficha nueva**

    public Usuario(int id, String nombre, String mail, String clave, String tipo) {
        this.idUsuario = id;
        this.nombreCompleto = nombre;
        this.correo = mail;
        this.contrasena = clave;
        this.tipoUsuario = tipo;
        this.estadoCuenta = "Activo"; // estado inicial
    }


    //   **Préstamos** y **Mora**
    public String getTipoUsuario() {
        return tipoUsuario; // nos devolverá el nivel de acceso al usuario
    }

    public String getEstadoCuenta() {
        return estadoCuenta; // nos devolverá el estado de la cuenta
    }

    // este atributo permite a la lógica de mora actualizar el estado a bloqueado si no cumple con la devolución
    public void setEstadoCuenta(String nuevoEstado) {
        this.estadoCuenta = nuevoEstado;
    }


    // Métodos y Validaciones

    /**
     * Paso 1: Actualizar el rol de un usuario
     * @param nuevoRol el nuevo nivel de acceso **ejemplo maestro**
     * @param usuarioDeControl el usuario que hace clic en el botón **tiene que ser el **administrator**
     */
    public boolean asignarRol(String nuevoRol, Usuario usuarioDeControl) {

        // Validación importantísima ¿Tienes los permisos?
        if (usuarioDeControl.getTipoUsuario().equals("Admin")) {

            // Si sos el administrador, validaremos que el nuevo rol sea uno de los 3 que existen
            if (nuevoRol.equals("Admin") || nuevoRol.equals("Maestro") || nuevoRol.equals("Estudiante")) {

                this.tipoUsuario = nuevoRol; // el cambio ah sido realizado
                System.out.println("[OK] Rol de " + this.nombreCompleto + " actualizado a " + nuevoRol);
                return true;
            } else {
                // El Administrator intentó poner un rol que no existe
                System.out.println("X ERROR: El sistema solo acepta los roles Admin, Maestro y Alumno '" 
                        + nuevoRol + "' no es válido.");
            } else {
                return false;
            }

           
            // **Alerta de seguridad** **La persona no es el Administrator**
            System.out.println("Acceso Denegado: Solo un Administrator puede cambiar los roles.");
            return false;
        }
    }

    /**
     * Paso 2: Permitir al usuario cambiar su contraseña que se le olvidó
     * @param nuevaClave  clave temporal que se le asignara
     * @param usuarioDeControl el usuario que da clic en el boton tiene que ser un **Administrator**
     */
    public boolean restablecerContrasena(String nuevaClave, Usuario usuarioDeControl) {

        // validación de la clave: ¿Tienes los permisos?
        if (usuarioDeControl.getTipoUsuario().equals("Admin")) {

            this.contrasena = nuevaClave; // asignará una clave temporal **Nota:en la vida real tiene que ir encriptado**
            System.out.println("Contraseña de " + this.nombreCompleto + " restablecida");
            return true;
        } else {
            // **Alerta de seguridad** No es un Administrator
            System.out.println("Acceso Denegado: Solo un Administrator puede restablecer las contraseñas");
            return false;
        }
    }
}
