package Semaforo;
public class Main {
    public static void main(String[] args) {
        // Instancia del semáforo
        Semaforo semaforo = new Semaforo();

        // Hilo que intentará bajar el semáforo
        Thread hilo1 = new Thread(() -> {
            try {
                System.out.println("Hilo 1: Intentando bajar el semáforo...");
                semaforo.bajar();
                System.out.println("Hilo 1: Semáforo bajado.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Hilo que intentará subir el semáforo
        Thread hilo2 = new Thread(() -> {
            try {
                Thread.sleep(1000);  // Simular alguna operación
                System.out.println("Hilo 2: Subiendo el semáforo.");
                semaforo.subir();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Otro hilo que intentará bajar el semáforo
        Thread hilo3 = new Thread(() -> {
            try {
                System.out.println("Hilo 3: Intentando bajar el semáforo...");
                semaforo.bajar();
                System.out.println("Hilo 3: Semáforo bajado.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Iniciamos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
