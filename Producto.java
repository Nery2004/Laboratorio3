public class Producto {
    private int id; // ID único del producto
    private String nombre;
    private int cantidadDisponible;
    private int cantidadVendidos;
    private String estado; // "disponible" o "no disponible"
    private double precio;
    private String categoria; // Categoría del producto (por ejemplo, "Bebida" o "Snack")

    public Producto(int id, String nombre, int cantidadDisponible, int cantidadVendidos, String estado, double precio, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadVendidos = cantidadVendidos;
        this.estado = estado;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Getters y Setters para los atributos

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadVendidos() {
        return cantidadVendidos;
    }

    public void setCantidadVendidos(int cantidadVendidos) {
        this.cantidadVendidos = cantidadVendidos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public double calcularComision() {
        if (this.categoria.equals("Bebida")) {
            return this.precio * 0.10;
        } else if (this.categoria.equals("Snack")) {
            return this.precio * 0.05;
        } else if (this.categoria.equals("Cereales")) {
            return this.precio * 0.07;
        } 
        return 0;
    }

    @Override
    public String toString() {
        // Representacion del producto como cadena de texto
        return "ID: " + id + "\nNombre: " + nombre + "\nCantidad Disponible: " + cantidadDisponible +
                "\nCantidad Vendidos: " + cantidadVendidos + "\nEstado: " + estado +
                "\nPrecio: " + precio + "\nCategoria: " + categoria;
    }
}