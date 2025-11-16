public class Ejemplar {
    private String idEjemplar;
    private String idDocumento;
    private String codigoEjemplar;
    private String estado;  // Disponible, Prestado, Dañado, Perdido

    public Ejemplar() {
    }

    public Ejemplar(String idEjemplar, String idDocumento, String codigoEjemplar, String estado) {
        this.idEjemplar = idEjemplar;
        this.idDocumento = idDocumento;
        this.codigoEjemplar = codigoEjemplar;
        this.estado = estado;
    }

    public String getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(String idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }

    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
