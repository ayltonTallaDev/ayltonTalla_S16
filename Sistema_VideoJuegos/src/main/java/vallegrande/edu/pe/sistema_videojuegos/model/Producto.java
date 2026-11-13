package vallegrande.edu.pe.sistema_videojuegos.model;

import java.time.LocalDate;

public class Producto {

    private int idProducto;
    private String titulo;
    private String plataforma;
    private String genero;
    private String clasificacion;
    private double precio;
    private int stock;
    private LocalDate fechaLanzamiento;

    public Producto() {
    }

    public Producto(int idProducto, String titulo,
                    String plataforma, String genero,
                    String clasificacion, double precio,
                    int stock, LocalDate fechaLanzamiento) {

        this.idProducto = idProducto;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.precio = precio;
        this.stock = stock;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}

