package ds;

public interface Queue<T> {
    /**
     * Devuelve el número de elementos de la cola
     * @return int
     */
    public int size();
    /**
     * Indica si la cola está vacía o no
     * @return boolean
     */
    public boolean isEmpty();
    /**
     * Añade un nuevo elemento a la cola (al final de la colección)
     * @return void
     */
    public void enqueue(T obj);
    /**
     * Elimina el siguiente elemento de la cola y lo devuelve
     * @return T
     */
    public T dequeue();
    /**
     * Devuelve el siguiente elemento de la cola sin eliminarlo
     * @return T
     */
    public T peek();
}
