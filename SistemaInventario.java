import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
                    Producto producto = null;

                    if (categoria.equals("Bebida")) {
                        if (campos.length >= 8) {
                            int ml = Integer.parseInt(campos[7]);
                            if (campos.length >= 9) {
                                String tipo = campos[8];
                                producto = new Bebida(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, ml, tipo);
                            } else {
                                producto = new Bebida(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, ml, "");
                            }
                        }
                    } else if (categoria.equals("Snack") || categoria.equals("Cereales")) {
                        if (campos.length >= 10) {
                            int gramos = Integer.parseInt(campos[9]);
                            if (campos.length >= 11) {
                                String sabor = campos[10];
                                if (campos.length >= 12) {
                                    String tamaño = campos[11];
                                    producto = (categoria.equals("Snack")) ? new Snack(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, gramos, sabor, tamaño)
                                                                         : new Cereales(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, gramos, sabor, tamaño);
                                } else {
                                    producto = (categoria.equals("Snack")) ? new Snack(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, gramos, sabor, "")
                                                                         : new Cereales(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, gramos, sabor, "");
                                }
                            }
                        }
                    }

                    if (producto != null) {
                        productos.add(producto);
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

    public void agregarProducto(Producto producto) {
        inventario.add(producto);
        // También guarda los cambios en el archivo CSV
        guardarCSV("inventario.csv", inventario);
    }

    private void guardarCSV(String archivoCSV, List<Producto> productos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoCSV))) {
            // Escribir el encabezado del archivo CSV
            writer.println("id_producto,nombre,cantidad_disponible,cantidad_vendidos,estado,precio,categoria,ml,tipo,gramos,sabor,tamaño");

            // Escribir los datos de los productos
            for (Producto producto : productos) {
                writer.print(producto.getId() + ",");
                writer.print(producto.getNombre() + ",");
                writer.print(producto.getCantidadDisponible() + ",");
                writer.print(producto.getCantidadVendidos() + ",");
                writer.print(producto.getEstado() + ",");
                writer.print(producto.getPrecio() + ",");
                writer.print(producto.getCategoria() + ",");

                if (producto instanceof Bebida) {
                    Bebida bebida = (Bebida) producto;
                    writer.print(bebida.getMl() + ",");
                    writer.print(bebida.getTipo() + ",");
                    writer.print("0,0,"); // Campos no utilizados para Bebidas
                } else if (producto instanceof Snack) {
                    Snack snack = (Snack) producto;
                    writer.print("0,0,"); // Campos no utilizados para Bebidas
                    writer.print(snack.getGramos() + ",");
                    writer.print(snack.getSabor() + ",");
                    writer.print(snack.getTamaño() + ",");
                }

                writer.println(); // Nueva línea para el siguiente producto
            }

            System.out.println("Productos guardados exitosamente en el archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar productos en el archivo CSV.");
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
        // Implementa la logica para calcular la comision acumulada por categoria
        double comisionAcumulada = 0;
        for (Producto producto : inventario) {
            if (producto.getCategoria().equals(categoria)) {
                // Realiza el calculo de comision especifico para esta categoria
                comisionAcumulada += producto.getPrecio() * 0.20; // Comision del 20%
            }
        }
        System.out.println("Comisión acumulada para la categoria " + categoria + ": " + comisionAcumulada);
    }

    public void mostrarVentasYComision() {
        // Muestra las ventas totales y la comision acumulada por categoria
        double ventasTotales = 0;
        System.out.println("Listado de categorias con el total de productos:");
        for (Producto producto : inventario) {
            List<Producto> productosPorCategoria = listarProductosPorCategoria(producto.getCategoria());
            int totalProductos = productosPorCategoria.size();
            System.out.println(producto.getCategoria() + " - " + totalProductos);
            ventasTotales += productosPorCategoria.stream().mapToDouble(Producto::getPrecio).sum();
        }
        System.out.println("Total de ventas: Q" + ventasTotales);
    }
}
