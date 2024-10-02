package Ejercicio_2;

import java.util.LinkedList;
import java.util.Queue;

public class Buzon {
    public static final int MAX_MENSAJES = 10;
    private Queue<Object> mensajes = new LinkedList<>();

    public synchronized void escribir(Object msg) throws InterruptedException {
        while (mensajes.size() == MAX_MENSAJES) {
            wait();  // Si el buzón está lleno, el hilo espera
        }
        mensajes.add(msg);  // Añade el mensaje al buzón
        notifyAll();        // Notifica a los hilos que están esperando
    }

    public synchronized Object leer() throws InterruptedException {
        while (mensajes.isEmpty()) {
            wait();  // Si el buzón está vacío, el hilo espera
        }
        Object msg = mensajes.poll();  // Extrae el mensaje
        notifyAll();                   // Notifica a los hilos que están esperando
        return msg;
    }
}
