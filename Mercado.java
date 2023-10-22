import java.util.Scanner;
import java.util.List;

public class Mercado {
    public static void main(String[] args) {
        SistemaInventario sistema = new SistemaInventario();
        sistema.cargarProductosDesdeCSV("inventario.csv"); // Carga el archivo "inventario.csv" automa6ticamente
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu de Opciones:");
            System.out.println("1. Cargar productos desde archivo CSV");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Listar productos por categoria");
            System.out.println("4. Calcular comision por categoria");
            System.out.println("5. Mostrar ventas y comisiones");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume la linea en blanco

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el ID del nuevo producto: ");
                    int nuevoId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nombre del nuevo producto: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese la cantidad disponible: ");
                    int nuevaCantidadDisponible = scanner.nextInt();
                    scanner.nextLine(); // Consumir la linea en blanco

                    System.out.print("Ingrese la cantidad vendida: ");
                    int nuevaCantidadVendidos = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese el estado (disponible o no disponible): ");
                    String nuevoEstado = scanner.nextLine();

                    System.out.print("Ingrese el precio: ");
                    double nuevoPrecio = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Ingrese la categoria (Bebida, Snack o Cereales): ");
                    String nuevaCategoria = scanner.nextLine();

                    Producto nuevoProducto = null;

                    if (nuevaCategoria.equals("Bebida")) {
                        System.out.print("Ingrese los mililitros (ml): ");
                        int nuevoMl = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese el tipo (energetica, con licor, natural, agua pura): ");
                        String nuevoTipo = scanner.nextLine();
                        nuevoProducto = new Bebida(nuevoId, nuevoNombre, nuevaCantidadDisponible, nuevaCantidadVendidos, nuevoEstado, nuevoPrecio, nuevoMl, nuevoTipo);
                    } else if (nuevaCategoria.equals("Snack")) {
                        System.out.print("Ingrese los gramos (gr): ");
                        int nuevoGramos = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese el sabor (barbacoa, picante, natural, etc.): ");
                        String nuevoSabor = scanner.nextLine();
                        System.out.print("Ingrese el tamaño (individual, familiar, pequeño, etc.): ");
                        String nuevoTamaño = scanner.nextLine();
                        nuevoProducto = new Snack(nuevoId, nuevoNombre, nuevaCantidadDisponible, nuevaCantidadVendidos, nuevoEstado, nuevoPrecio, nuevoGramos, nuevoSabor, nuevoTamaño);
                    } else if (nuevaCategoria.equals("Cereales")) {
                        System.out.print("Ingrese los gramos (gr): ");
                        int nuevoGramos = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese el sabor (barbacoa, picante, natural, etc.): ");
                        String nuevoSabor = scanner.nextLine();
                        System.out.print("Ingrese el tamaño (individual, familiar, pequeño, etc.): ");
                        String nuevoTamaño = scanner.nextLine();
                        nuevoProducto = new Cereales(nuevoId, nuevoNombre, nuevaCantidadDisponible, nuevaCantidadVendidos, nuevoEstado, nuevoPrecio, nuevoGramos, nuevoSabor, nuevoTamaño);
                    } else {
                        System.out.println("Categoria no valida. No se pudo crear el producto.");
                    }

                    if (nuevoProducto != null) {
                        sistema.agregarProducto(nuevoProducto);
                        System.out.println("Nuevo producto agregado exitosamente.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el ID del producto a buscar: ");
                    int idBuscado = scanner.nextInt();
                    scanner.nextLine(); // Consume la linea en blanco
                    Producto productoEncontrado = sistema.buscarProductoPorID(idBuscado);
                    if (productoEncontrado != null) {
                        System.out.println("Producto encontrado: " + productoEncontrado.getNombre());
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese la categoria de productos a listar: ");
                    String categoria = scanner.nextLine();
                    sistema.listarProductosPorCategoria(categoria).forEach(producto -> System.out.println(producto.getNombre()));
                    break;
                case 4:
                    System.out.print("Ingrese la categoría para calcular la comisión: ");
                    String categoriaComision = scanner.nextLine();
                    sistema.calcularComisionPorCategoria(categoriaComision);
                    System.out.println("Comisión calculada para la categoria: " + categoriaComision);
                    break;
                case 5:
                    sistema.mostrarVentasYComision();
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
                    break;
            }
        }
    }
}
