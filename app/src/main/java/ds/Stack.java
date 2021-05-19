package ds;

public interface Stack<T> {
    /**
     * Devuelve el número de elementos de la pila
     * @return int
     */
    public int size();
    /**
     * Indica si la pila está vacía o no
     * @return boolean
     */
    public boolean isEmpty();
    /**
     * Añade un nuevo elemento a la pila (al final de la colección)
     * @return void
     */
    public void push(T obj);
    /**
     * Elimina el siguiente elemento de la pila y lo devuelve
     * @return T
     */
    public T pop();
    /**
     * Devuelve el siguiente elemento de la pila sin eliminarlo
     * @return T
     */
    public T peek();
}
