import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SistemaInventario {
    private List<Producto> inventario;

    public SistemaInventario() {
        inventario = new ArrayList<>();
    }

    public void cargarProductosDesdeCSV(String archivoCSV) {
        inventario = cargarCSV(archivoCSV);
    }

    private List<Producto> cargarCSV(String archivoCSV) {
        List<Producto> productos = new ArrayList<>();
        String linea;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoCSV))) {
            // Omitimos la primera línea que generalmente contiene encabezados
            reader.readLine();

            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length >= 7) {
                    int id = Integer.parseInt(campos[0]);
                    String nombre = campos[1];
                    int cantidadDisponible = Integer.parseInt(campos[2]);
                    int cantidadVendidos = Integer.parseInt(campos[3]);
                    String estado = campos[4];
                    double precio = Double.parseDouble(campos[5]);
                    String categoria = campos[6];

                    if (categoria.equals("Bebida") && campos.length >= 9) {
                        int ml = Integer.parseInt(campos[7]);
                        String tipo = campos[8];
                        productos.add(new Bebida(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, ml, tipo));
                    } else if ((categoria.equals("Snack") || categoria.equals("Cereales")) && campos.length >= 11) {
                        int gramos = Integer.parseInt(campos[9]);
                        String sabor = campos[10];
                        String tamaño = campos[11];
                        if (categoria.equals("Snack")) {
                            productos.add(new Snack(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, gramos, sabor, tamaño));
                        } else if (categoria.equals("Cereales")) {
                            productos.add(new Cereales(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, gramos, sabor, tamaño));
                        }
                    }
                }
            }
            System.out.println("Productos cargados exitosamente desde el archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar productos desde el archivo CSV.");
        }

        return productos;
    }

    public void mostrarProductos() {
        for (Producto producto : inventario) {
            System.out.println(producto);
        }
    }

    public Producto buscarProductoPorID(int id) {
        for (Producto producto : inventario) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null; // Devuelve null si no se encuentra el producto
    }

    public List<Producto> listarProductosPorCategoria(String categoria) {
        List<Producto> productosPorCategoria = new ArrayList<>();
        for (Producto producto : inventario) {
            if (producto.getCategoria().equals(categoria)) {
                productosPorCategoria.add(producto);
            }
        }
        return productosPorCategoria;
    }

    public void calcularComisionPorCategoria(String categoria) {
        // Implementa la lógica para calcular la comisión acumulada por categoría
        double comisionAcumulada = 0;
        for (Producto producto : inventario) {
            if (producto.getCategoria().equals(categoria)) {
                // Realiza el cálculo de comisión específico para esta categoría
                comisionAcumulada += producto.getPrecio() * 0.20; // Comisión del 20%
            }
        }
        System.out.println("Comisión acumulada para la categoría " + categoria + ": " + comisionAcumulada);
    }

    public void mostrarVentasYComision() {
        // Muestra las ventas totales y la comisión acumulada por categoría
        double ventasTotales = 0;
        System.out.println("Listado de categorías con el total de productos:");
        for (Producto producto : inventario) {
            List<Producto> productosPorCategoria = listarProductosPorCategoria(producto.getCategoria());
            int totalProductos = productosPorCategoria.size();
            System.out.println(producto.getCategoria() + " - " + totalProductos);
            ventasTotales += productosPorCategoria.stream().mapToDouble(Producto::getPrecio).sum();
        }
        System.out.println("Total de ventas: Q" + ventasTotales);
    }
}