import java.util.Scanner;
import java.util.List;
import java.util.InputMismatchException;

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

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner pr = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                printMenu();
                System.out.print("Ingrese el número de la opción deseada: ");
                int opcion = pr.nextInt();
                pr.nextLine(); // Limpiar el buffer del scanner

                switch (opcion) {
                    case 1: {
                        System.out.println("\n--- REGISTRAR ORDEN DE SERVICIO ---");
                        System.out.print("ID de la orden: ");
                        int id = pr.nextInt();
                        pr.nextLine();

                        System.out.print("Nombre del cliente: ");
                        String cliente = pr.nextLine();

                        System.out.print("Dirección del cliente: ");
                        String direccion = pr.nextLine();

                        System.out.print("Zona (1 = Metropolitana, 2 = Periférica): ");
                        int opZona = pr.nextInt();
                        pr.nextLine();
                        ZonaTrabajo zona = (opZona == 1) ? ZonaTrabajo.METROPOLITANA : ZonaTrabajo.PERIFERICA;

                        System.out.print("Horas estimadas de trabajo: ");
                        float horas = pr.nextFloat();
                        pr.nextLine();

                        System.out.println("Tipo de servicio:");
                        System.out.println("1. Diagnóstico");
                        System.out.println("2. Instalación estándar");
                        System.out.println("3. Instalación eléctrica certificada");
                        System.out.println("4. Mantenimiento de aire acondicionado");
                        System.out.print("Seleccione una opción: ");
                        int tipo = pr.nextInt();
                        pr.nextLine();

                        Orden orden = null;
                        if (tipo == 1) {
                            orden = new Diagnostico(id, cliente, direccion, horas, zona);
                        } else if (tipo == 2) {
                            System.out.print("Puntos a instalar: ");
                            int puntos = pr.nextInt();
                            pr.nextLine();
                            orden = new Instalacion(id, cliente, direccion, horas, zona, puntos);
                        } else if (tipo == 3) {
                            System.out.print("Puntos a instalar: ");
                            int puntos = pr.nextInt();
                            pr.nextLine();
                            System.out.print("Costo de materiales (Q): ");
                            float mat = pr.nextFloat();
                            pr.nextLine();
                            orden = new InstalacionCertificada(id, cliente, direccion, horas, zona, puntos, mat);
                        } else if (tipo == 4) {
                            System.out.print("Cantidad en BTU: ");
                            int btu = pr.nextInt();
                            pr.nextLine();
                            orden = new MantenimientoAC(id, cliente, direccion, horas, zona, btu);
                        } else {
                            System.out.println("Tipo de servicio no válido.");
                            break;
                        }

                        sistema.registrarOrden(orden);
                        System.out.println("\nOrden registrada con éxito.");
                        System.out.printf("Costo total facturado: Q%.2f\n", orden.calcularCosto());
                        break;
                    }

                    case 2: {
                        System.out.println("\n--- COTIZAR SERVICIO ---");
                        System.out.print("Zona (1 = Metropolitana, 2 = Periférica): ");
                        int opZona = pr.nextInt();
                        pr.nextLine();
                        ZonaTrabajo zona = (opZona == 1) ? ZonaTrabajo.METROPOLITANA : ZonaTrabajo.PERIFERICA;

                        System.out.print("Horas estimadas de trabajo: ");
                        float horas = pr.nextFloat();
                        pr.nextLine();

                        System.out.println("Tipo de servicio a cotizar:");
                        System.out.println("1. Diagnóstico");
                        System.out.println("2. Instalación estándar");
                        System.out.println("3. Instalación eléctrica certificada");
                        System.out.println("4. Mantenimiento de aire acondicionado");
                        System.out.print("Seleccione una opción: ");
                        int tipo = pr.nextInt();
                        pr.nextLine();

                        Orden cotizacion = null;
                        if (tipo == 1) {
                            cotizacion = new Diagnostico(1, "Cotización", "Ciudad", horas, zona);
                        } else if (tipo == 2) {
                            System.out.print("Puntos a instalar: ");
                            int puntos = pr.nextInt();
                            pr.nextLine();
                            cotizacion = new Instalacion(1, "Cotización", "Ciudad", horas, zona, puntos);
                        } else if (tipo == 3) {
                            System.out.print("Puntos a instalar: ");
                            int puntos = pr.nextInt();
                            pr.nextLine();
                            System.out.print("Costo de materiales (Q): ");
                            float mat = pr.nextFloat();
                            pr.nextLine();
                            cotizacion = new InstalacionCertificada(1, "Cotización", "Ciudad", horas, zona, puntos, mat);
                        } else if (tipo == 4) {
                            System.out.print("Cantidad en BTU: ");
                            int btu = pr.nextInt();
                            pr.nextLine();
                            cotizacion = new MantenimientoAC(1, "Cotización", "Ciudad", horas, zona, btu);
                        } else {
                            System.out.println("Tipo no válido.");
                            break;
                        }

                        System.out.println("\n--- RESULTADO DE COTIZACIÓN ---");
                        System.out.printf("Costo total estimado: Q%.2f\n", cotizacion.calcularCosto());
                        System.out.println("Tiempo estimado de atención: " + cotizacion.calcularTiempoEstAtencion() + " días hábiles");
                        break;
                    }

                    case 3: {
                        System.out.println("\n--- REGISTRAR CUADRILLA ---");
                        System.out.print("ID de la cuadrilla: ");
                        int id = pr.nextInt();
                        pr.nextLine();

                        System.out.print("Capacidad máxima de horas: ");
                        float cap = pr.nextFloat();
                        pr.nextLine();

                        System.out.print("¿Certificada en refrigeración? (1 = Sí, 2 = No): ");
                        int cert = pr.nextInt();
                        pr.nextLine();

                        Cuadrilla c = new Cuadrilla(id, cap, cert == 1);
                        sistema.registrarCuadrilla(c);
                        System.out.println("Cuadrilla No. " + id + " registrada exitosamente.");
                        break;
                    }

                    case 4: {
                        System.out.println("\n--- GESTIÓN DE JORNADAS ---");
                        System.out.println("1. Crear nueva jornada");
                        System.out.println("2. Asignar orden a jornada");
                        System.out.println("3. Retirar orden de jornada");
                        System.out.println("4. Iniciar jornada");
                        System.out.println("5. Registrar visita de orden");
                        System.out.println("6. Cerrar jornada");
                        System.out.print("Seleccione una opción: ");
                        int sub = pr.nextInt();
                        pr.nextLine();

                        if (sub == 1) {
                            System.out.println("\nCuadrillas disponibles:");
                            for (Cuadrilla c : sistema.getListaCuadrillas()) {
                                if (!c.isTieneJornadaActiva()) {
                                    System.out.print(c);
                                }
                            }
                            System.out.print("Ingrese ID de la cuadrilla: ");
                            int idCuad = pr.nextInt();
                            pr.nextLine();
                            sistema.crearJornada(idCuad);
                            System.out.println("Jornada creada en preparación con la cuadrilla No. " + idCuad);

                        } else if (sub == 2) {
                            List<Jornada> lista = sistema.getListaJornadas();
                            if (lista.isEmpty()) {
                                System.out.println("No hay jornadas creadas.");
                                break;
                            }
                            System.out.println("\nJornadas en preparación:");
                            for (int i = 0; i < lista.size(); i++) {
                                if (lista.get(i).getEstado() == EstadoJornada.EN_PREPARACION) {
                                    System.out.println("[" + (i + 1) + "] " + lista.get(i));
                                }
                            }
                            System.out.print("Seleccione el número de jornada: ");
                            int pos = pr.nextInt() - 1;
                            pr.nextLine();
                            if (pos < 0 || pos >= lista.size()) {
                                System.out.println("Jornada inválida.");
                                break;
                            }
                            Jornada j = lista.get(pos);

                            System.out.println("\nÓrdenes pendientes:");
                            for (Orden o : sistema.getListaOrdenes()) {
                                if (o.getEstadoOrden() == EstadoOrden.PENDIENTE) {
                                    System.out.println("ID: " + o.getIDOrden() + " | " + o.getCategoriaOrden() 
                                                       + " | Horas: " + o.getHorasEstTrabajo() + " h | Cliente: " + o.getNombreCliente());
                                }
                            }
                            System.out.print("ID de la orden a asignar: ");
                            int idOrd = pr.nextInt();
                            pr.nextLine();
                            Orden orden = sistema.buscarOrden(idOrd);
                            if (orden == null) {
                                System.out.println("Orden no encontrada.");
                                break;
                            }
                            j.asignarOrden(orden);
                            System.out.println("Orden asignada exitosamente.");

                        } else if (sub == 3) {
                            List<Jornada> lista = sistema.getListaJornadas();
                            if (lista.isEmpty()) {
                                System.out.println("No hay jornadas creadas.");
                                break;
                            }
                            System.out.println("\nJornadas en preparación:");
                            for (int i = 0; i < lista.size(); i++) {
                                if (lista.get(i).getEstado() == EstadoJornada.EN_PREPARACION) {
                                    System.out.println("[" + (i + 1) + "] " + lista.get(i));
                                }
                            }
                            System.out.print("Seleccione el número de jornada: ");
                            int pos = pr.nextInt() - 1;
                            pr.nextLine();
                            if (pos < 0 || pos >= lista.size()) {
                                System.out.println("Jornada inválida.");
                                break;
                            }
                            Jornada j = lista.get(pos);

                            if (j.getOrdenes().isEmpty()) {
                                System.out.println("Esta jornada no tiene órdenes asignadas.");
                                break;
                            }
                            System.out.println("\nÓrdenes asignadas:");
                            for (Orden o : j.getOrdenes()) {
                                System.out.println("ID: " + o.getIDOrden() + " | " + o.getCategoriaOrden() + " | " + o.getNombreCliente());
                            }
                            System.out.print("ID de la orden a retirar: ");
                            int idOrd = pr.nextInt();
                            pr.nextLine();
                            Orden orden = sistema.buscarOrden(idOrd);
                            if (orden == null) {
                                System.out.println("Orden no encontrada.");
                                break;
                            }
                            j.retirarOrden(orden);
                            System.out.println("Orden retirada de la jornada.");

                        } else if (sub == 4) {
                            List<Jornada> lista = sistema.getListaJornadas();
                            if (lista.isEmpty()) {
                                System.out.println("No hay jornadas creadas.");
                                break;
                            }
                            System.out.println("\nJornadas en preparación:");
                            for (int i = 0; i < lista.size(); i++) {
                                if (lista.get(i).getEstado() == EstadoJornada.EN_PREPARACION) {
                                    System.out.println("[" + (i + 1) + "] " + lista.get(i));
                                }
                            }
                            System.out.print("Seleccione el número de jornada: ");
                            int pos = pr.nextInt() - 1;
                            pr.nextLine();
                            if (pos < 0 || pos >= lista.size()) {
                                System.out.println("Jornada inválida.");
                                break;
                            }
                            Jornada j = lista.get(pos);

                            float cobroExtra = j.iniciarJornada();
                            if (cobroExtra > 0) {
                                sistema.sumarIngresoVisitasExtra(cobroExtra);
                                System.out.printf("Cobro extra por visitas recurrentes: Q%.2f\n", cobroExtra);
                            }
                            System.out.println("Jornada iniciada. Las órdenes pasaron a EN ATENCIÓN.");

                        } else if (sub == 5) {
                            List<Jornada> lista = sistema.getListaJornadas();
                            if (lista.isEmpty()) {
                                System.out.println("No hay jornadas creadas.");
                                break;
                            }
                            System.out.println("\nJornadas en atención:");
                            for (int i = 0; i < lista.size(); i++) {
                                if (lista.get(i).getEstado() == EstadoJornada.EN_ATENCION) {
                                    System.out.println("[" + (i + 1) + "] " + lista.get(i));
                                }
                            }
                            System.out.print("Seleccione el número de jornada: ");
                            int pos = pr.nextInt() - 1;
                            pr.nextLine();
                            if (pos < 0 || pos >= lista.size()) {
                                System.out.println("Jornada inválida.");
                                break;
                            }
                            Jornada j = lista.get(pos);

                            System.out.println("\nÓrdenes de esta jornada:");
                            for (Orden o : j.getOrdenes()) {
                                System.out.println("ID: " + o.getIDOrden() + " | Estado: " + o.getEstadoOrden() 
                                                   + " | Visitas: " + o.getVisitas() + "/" + o.getLimiteVisitas());
                            }
                            System.out.print("ID de la orden a reportar: ");
                            int idOrd = pr.nextInt();
                            pr.nextLine();
                            Orden orden = sistema.buscarOrden(idOrd);
                            if (orden == null) {
                                System.out.println("Orden no encontrada.");
                                break;
                            }
                            System.out.print("¿La visita fue completada con éxito? (1 = Sí, 2 = No): ");
                            int res = pr.nextInt();
                            pr.nextLine();
                            j.registrarVisita(orden, res == 1);
                            System.out.println("Visita registrada. Estado actual: " + orden.getEstadoOrden());

                        } else if (sub == 6) {
                            List<Jornada> lista = sistema.getListaJornadas();
                            if (lista.isEmpty()) {
                                System.out.println("No hay jornadas creadas.");
                                break;
                            }
                            System.out.println("\nJornadas en atención:");
                            for (int i = 0; i < lista.size(); i++) {
                                if (lista.get(i).getEstado() == EstadoJornada.EN_ATENCION) {
                                    System.out.println("[" + (i + 1) + "] " + lista.get(i));
                                }
                            }
                            System.out.print("Seleccione el número de jornada: ");
                            int pos = pr.nextInt() - 1;
                            pr.nextLine();
                            if (pos < 0 || pos >= lista.size()) {
                                System.out.println("Jornada inválida.");
                                break;
                            }
                            Jornada j = lista.get(pos);
                            j.cerrarJornada();
                            System.out.println("Jornada cerrada con éxito y cuadrilla disponible nuevamente.");
                        } else {
                            System.out.println("Opción no válida.");
                        }
                        break;
                    }

                    case 5: {
                        System.out.println("\n--- CONSULTAS DEL SISTEMA ---");
                        System.out.println("1. Listado general de órdenes");
                        System.out.println("2. Listado general de cuadrillas");
                        System.out.print("Seleccione una opción: ");
                        int sub = pr.nextInt();
                        pr.nextLine();

                        if (sub == 1) {
                            System.out.println("\n========== CATÁLOGO DE ÓRDENES ==========");
                            for (Orden o : sistema.getListaOrdenes()) {
                                System.out.println(o);
                            }
                        } else if (sub == 2) {
                            System.out.println("\n========== LISTA DE CUADRILLAS ==========");
                            for (Cuadrilla c : sistema.getListaCuadrillas()) {
                                System.out.print(c);
                            }
                        } else {
                            System.out.println("Opción no válida.");
                        }
                        break;
                    }

                    case 6: {
                        System.out.println(sistema.generarReporteEstados());
                        System.out.println(sistema.generarReporteFinanciero());

                        System.out.println("\n================ JORNADAS ACTIVAS ================");
                        int activas = 0;
                        for (Jornada j : sistema.getListaJornadas()) {
                            if (j.getEstado() != EstadoJornada.CERRADA) {
                                System.out.println(j);
                                activas++;
                            }
                        }
                        if (activas == 0) {
                            System.out.println("No hay jornadas activas en este momento.");
                        }
                        System.out.println("==================================================");
                        break;
                    }

                    case 7: {
                        salir = true;
                        System.out.println("Saliendo del programa...");
                        break;
                    }

                    default:
                        System.out.println("Opción inválida. Por favor, ingrese un número del 1 al 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número.");
                pr.nextLine(); // Limpiar el buffer del scanner en caso de error

            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } 
        pr.close();
    }
}