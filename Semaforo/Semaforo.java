package Semaforo;
public class Semaforo {
    private boolean estado = true;  // Estado del semáforo: true significa que está "subido".

    public synchronized void bajar() throws InterruptedException {
        while (!estado) {
            wait();  // Si está "bajado", espera.
        }
        estado = false;  // Baja el semáforo.
        notifyAll();     // Despierta a los hilos que esperan.
    }

    public synchronized void subir() {
        estado = true;   // Sube el semáforo.
        notifyAll();     // Notifica a los hilos que estaban esperando.
    }
}



