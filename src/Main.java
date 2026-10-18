import java.util.Scanner;


public class Main {

    private static void printMenu() { 
        System.out.println("Bienvenido al menú principal de gestión de órdenes de servicio de Tecni Hogar. ¿Qué tipo de operación desea realizar? \n");
        System.out.println("""
        ==========TECNI HOGAR PRINCIPAL==========
        1. Registrar orden de servicio
        2. Cotizar servicio
        3. Registrar cuadrilla
        4. Gestión de jornadas
        5. Consultas (Órdenes y Cuadrillas)
        6. Reportes gerenciales
        7. Salir
        ===================================
        """);

    }


    public static void main (String [] args) {
        Scanner pr = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                printMenu();
                System.out.print("Ingrese el número de la opción deseada: ");
                int opcion = pr.nextInt();
                pr.nextLine(); // Limpiar el buffer del scanner

                switch (opcion) {
                    case 1:
                        System.out.println("Registrar orden de servicio");
                        // Lógica para registrar orden de servicio
                        break;
                    case 2:
                        System.out.println("Cotizar servicio");
                        // Lógica para cotizar servicio
                        break;
                    case 3:
                        System.out.println("Registrar cuadrilla");
                        // Lógica para registrar cuadrilla
                        break;
                    case 4:
                        System.out.println("Gestión de jornadas");
                        // Lógica para gestión de jornadas
                        break;
                    case 5:
                        System.out.println("Consultas (Órdenes y Cuadrillas)");
                        // Lógica para consultas
                        break;
                    case 6:
                        System.out.println("Reportes");
                        // Lógica para reportes
                        break;
                    case 7:
                        salir = true;
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese un número del 1 al 7.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                pr.nextLine(); // Limpiar el buffer del scanner en caso de error
            }

        } 
        pr.close();


    }
    
}
