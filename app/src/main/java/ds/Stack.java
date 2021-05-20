package ds;

public class Stack<T> {

    private LinkedList<T> stackList;
    
    public Stack(){ stackList = new LinkedList<>(); }

    /**
     * Devuelve el número de elementos de la pila
     * @return int
     */
    public int size(){ return stackList.size(); };

    /**
     * Indica si la pila está vacía o no
     * @return boolean
     */
    public boolean isEmpty(){ return stackList.isEmpty(); };

    /**
     * Añade un nuevo elemento a la pila (al final de la colección)
     * 
     */
    public void push(T data){ stackList.add(data); };

    /**
     * Elimina el siguiente elemento de la pila y lo devuelve
     * @return T
     */
    public T pop(){ return stackList.remove(this.size()-1); };

    /**
     * Devuelve el siguiente elemento de la pila sin eliminarlo
     * @return T
     */
    public T peek(){ return stackList.get(this.size()-1); };

}
