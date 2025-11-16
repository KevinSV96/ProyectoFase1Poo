public class Documento {
    private String idDocumento;
    private String titulo;
    private String autor;
    private int anio;
    private String editorial;
    private String categoria;
    private String isbn;
    private int idTipo;
    private String descripcion;
    private String ubicacion;

    public Documento() {
    }

    public Documento(String idDocumento, String titulo, String autor, int anio, String editorial, String categoria, String isbn, int idTipo, String descripcion, String ubicacion) {
        this.idDocumento = idDocumento;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.editorial = editorial;
        this.categoria = categoria;
        this.isbn = isbn;
        this.idTipo = idTipo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
