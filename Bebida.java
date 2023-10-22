public class Bebida extends Producto {
    private int ml; // Mililitros
    private String tipo; // Tipo de bebida (energetica, con licor, natural, agua pura)

    public Bebida(int id, String nombre, int cantidadDisponible, int cantidadVendidos, String estado, double precio, int ml, String tipo) {
        super(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, "Bebida");
        this.ml = ml;
        this.tipo = tipo;
    }

    public int getMl() {
        return ml;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
