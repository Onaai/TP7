package Ejercicio_2;

public class Main {
    public static void main(String[] args) {
        // Crear un buzón con una capacidad máxima de 2 mensajes
        Buzon buzon = new Buzon();

        // Hilo escritor: intenta escribir mensajes en el buzón
        Thread escritor = new Thread(() -> {
            try {
                for (int i = 1; i <= 4; i++) {
                    System.out.println("Escritor: Escribiendo mensaje " + i);
                    buzon.escribir("Mensaje " + i);
                    System.out.println("Escritor: Mensaje " + i + " escrito.");
                    Thread.sleep(500);  // Simula algún tiempo entre escrituras
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Hilo lector: intenta leer mensajes del buzón
        Thread lector = new Thread(() -> {
            try {
                for (int i = 1; i <= 4; i++) {
                    System.out.println("Lector: Intentando leer un mensaje...");
                    Object mensaje = buzon.leer();
                    System.out.println("Lector: Mensaje leído: " + mensaje);
                    Thread.sleep(1000);  // Simula algún tiempo entre lecturas
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Iniciar ambos hilos
        escritor.start();
        lector.start();
    }
}
