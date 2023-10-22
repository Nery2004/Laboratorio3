import java.util.Scanner;

public class Mercado {
    public static void main(String[] args) {
        SistemaInventario sistema = new SistemaInventario();
        sistema.cargarProductosDesdeCSV("inventario.csv"); // Carga el archivo "inventario.csv" automaticamente
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
                    // La opcion 1 ya carga automaticamente el archivo "inventario.csv"
                    System.out.println("Productos cargados exitosamente.");
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
                    System.out.print("Ingrese la categoria para calcular la comision: ");
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
