package Ejercicio_3;

public class Main {

    public static void main(String[] args) {
        Buzon buzon = new Buzon();

        // Crear un hilo escritor
        Thread escritor = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buzon.escribir(1, "Mensaje " + i + " en ID 1");
                    System.out.println("Escrito: Mensaje " + i + " en ID 1");
                    Thread.sleep(500);  // Simula tiempo de escritura
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Crear un hilo lector específico de id
        Thread lectorEspecifico = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    Object msg = buzon.leer(1);
                    System.out.println("Leído desde ID 1: " + msg);
                    Thread.sleep(1000);  // Simula tiempo de lectura
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Crear un hilo lector para leer cualquier mensaje
        Thread lectorCualquier = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    Object msg = buzon.leer(0);
                    if (msg != null) {  // Evita imprimir null
                        System.out.println("Leído desde cualquier buzón: " + msg);
                    } else {
                        System.out.println("No hay mensajes disponibles en este momento.");
                    }
                    Thread.sleep(1500);  // Simula tiempo de lectura
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Iniciar los hilos
        escritor.start();
        lectorEspecifico.start();
        lectorCualquier.start();

        // Esperar a que los hilos terminen
        try {
            escritor.join();
            lectorEspecifico.join();
            lectorCualquier.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
