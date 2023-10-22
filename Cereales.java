public class Cereales extends Producto {
    private int gramos; // Gramos
    private String sabor; // Sabor de los cereales (natural, dulce)
    private String tamaño; // Tamaño del paquete de cereales (pequeño, mediano, grande)

    public Cereales(int id, String nombre, int cantidadDisponible, int cantidadVendidos, String estado, double precio, int gramos, String sabor, String tamaño) {
        super(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, "Cereales");
        this.gramos = gramos;
        this.sabor = sabor;
        this.tamaño = tamaño;
    }

    public int getGramos() {
        return gramos;
    }

    public void setGramos(int gramos) {
        this.gramos = gramos;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }
}
