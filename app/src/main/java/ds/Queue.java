package ds;

public class Queue<T> {

    private LinkedList<T> queueList;
    
    public Queue(){ queueList = new LinkedList<>(); }
    
    /**
     * Devuelve el número de elementos de la cola
     * @return int
     */
    public int size(){ return queueList.size(); };
    
    /**
     * Indica si la cola está vacía o no
     * @return boolean
     */
    public boolean isEmpty(){ return queueList.isEmpty(); };
    
    /**
     * Añade un nuevo elemento a la cola (al final de la colección)
     * 
     */
    public void enqueue(T data){ queueList.add(data); };
    
    /**
     * Elimina el siguiente elemento de la cola y lo devuelve
     * 
     */
    public T dequeue(){ return queueList.remove(0); };
    
    /**
     * Devuelve el siguiente elemento de la cola sin eliminarlo
     * @return T
     */
    public T peek(){ return queueList.get(0); };
    
}
