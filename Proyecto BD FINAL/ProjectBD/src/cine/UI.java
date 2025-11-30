package cine;

import java.util.Scanner;

public class UI {

    private final Scanner sc = new Scanner(System.in);
    private final Cine cine = new Cine();
    private final Extra extra = new Extra();

    public void iniciar() {

        int opcion;

        do {
            System.out.println("\n====== CINE APP ======");
            System.out.println("1. Insertar película");
            System.out.println("2. Modificar película");
            System.out.println("3. Eliminar película");
            System.out.println("4. Buscar película");
            System.out.println("5. Extras");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> insertar();
                case 2 -> modificar();
                case 3 -> eliminar();
                case 4 -> buscar();
                case 5 -> menuExtras();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción incorrecta.");
            }

        } while (opcion != 6);
    }

    private void insertar() {
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Género: ");
        String genero = sc.nextLine();

        System.out.print("Duración: ");
        int duracion = sc.nextInt(); sc.nextLine();

        System.out.print("Clasificación: ");
        String clas = sc.nextLine();

        System.out.println(cine.insertarPelicula(id, titulo, genero, duracion, clas));
    }

    private void modificar() {
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();

        System.out.print("Nuevo título: ");
        String titulo = sc.nextLine();

        System.out.print("Nuevo género: ");
        String genero = sc.nextLine();

        System.out.print("Nueva duración: ");
        int duracion = sc.nextInt(); sc.nextLine();

        System.out.print("Nueva clasificación: ");
        String clas = sc.nextLine();

        System.out.println(cine.modificarPelicula(id, titulo, genero, duracion, clas));
    }

    private void eliminar() {
        System.out.print("ID: ");
        int id = sc.nextInt();

        System.out.println(cine.eliminarPelicula(id));
    }

    private void buscar() {
        System.out.print("ID: ");
        int id = sc.nextInt();

        System.out.println(cine.buscarPelicula(id));
    }

    // ======================
    // MENÚ EXTRAS
    // ======================
    private void menuExtras() {
        int op;

        do {
            System.out.println("\n--- EXTRAS ---");
            System.out.println("1. Mostrar vista cartelera");
            System.out.println("2. Mostrar info del trigger");
            System.out.println("3. Probar SP vender boleto");
            System.out.println("4. Volver");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1 -> System.out.println(extra.mostrarVistaCartelera());
                case 2 -> System.out.println(extra.mostrarTriggerCliente());
                case 3 -> venderSP();
                case 4 -> {}
                default -> System.out.println("Opción inválida.");
            }

        } while (op != 4);
    }

    private void venderSP() {
        System.out.print("ID función: ");
        int f = sc.nextInt();

        System.out.print("ID cliente: ");
        int c = sc.nextInt();

        System.out.print("Asiento: ");
        int a = sc.nextInt();

        System.out.println(extra.venderBoleto(f, c, a));
    }
}
