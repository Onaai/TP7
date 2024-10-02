package Ejercicio_3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Buzon {
    public static final int MAX_MENSAJES = 10;
    public static final int ID_MAX = 100;
    private Map<Integer, Queue<Object>> mensajes = new HashMap<>();

    public Buzon() {
        for (int i = 1; i <= ID_MAX; i++) {
            mensajes.put(i, new LinkedList<>());
        }
    }

    public synchronized void escribir(int id, Object msg) throws InterruptedException {
        while (mensajes.get(id).size() == MAX_MENSAJES) {
            wait();  // Si la cola para el id está llena, espera
        }
        mensajes.get(id).add(msg);  // Añade el mensaje al buzón bajo el id
        notifyAll();                // Notifica a los hilos que están esperando
    }

    public synchronized Object leer(int id) throws InterruptedException {
        while (id != 0 && mensajes.get(id).isEmpty()) {
            wait();  // Si no hay mensajes para ese id, espera
        }
        if (id == 0) {  // Leer cualquier mensaje
            for (Map.Entry<Integer, Queue<Object>> entry : mensajes.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    Object msg = entry.getValue().poll();
                    notifyAll();  // Notifica que se ha liberado espacio
                    return msg;
                }
            }
            wait();  // Espera si todas las colas están vacías
        } else {
            Object msg = mensajes.get(id).poll();
            notifyAll();  // Notifica a los hilos que están esperando
            return msg;
        }
        return null;  // Devuelve null si no hay mensajes
    }
}
