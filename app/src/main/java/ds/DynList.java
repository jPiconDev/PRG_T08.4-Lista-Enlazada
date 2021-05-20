package ds;


/**
 * El interfaz DynList nos permite manejar el contenido de colecciones de objetos.
 */
public interface DynList<E> extends Iterable<E>{
    /**
     * Método que devuelve el número de elementos de la lista
     * @return
     */
    int size();
    /**
     * Método que devuelve true si la lista está vacía.
     * @return
     */
    boolean isEmpty();
    /**
     * Método que vacía la lista de objetos
     */
    void clear();
    /**
     * Método que devuelve el elemento de in índice de la lista.
     * @param index
     * @return el elemento en la posición indicada de la lista
     * @throws IndexOutOfBoundsException si el índice está fuera de la lista
     */
    E get(int index) throws IndexOutOfBoundsException;
    /**
     * Método que añade un elemento al final de la lista
     * @param element nuevo elemento para ser añadido
     * @return true
     */
    boolean add(E element);
    /**
     * Método que que añade un elemento en una posición indicada y desplaza a los contiguos a la derecha
     * @param index posición de la lista donde será añadido el elemento
     * @param element para ser añadido en la lista
     * @throws IndexOutOfBoundsException si el índice está fuera del rango de la lista
     */
    boolean add(int index, E element) throws IndexOutOfBoundsException;
    /**
     * Método que sustituye un elemento de la lista en una posición indicada
     * @param index posición de la lista donde será insertado el elemento
     * @param element elemento a insertar en la lista
     * @throws IndexOutOfBoundsException si el índice está fuera del rango de la lista
     */
    void set(int index, E element) throws IndexOutOfBoundsException;
    /**
     * Método que elimina un elemento de la lista dada una posición.
     * Desplaza todos los elementos contiguos una posición a la izquierda
     * @param index posición de la lista donde será removido el elemento
     * @return el elemento removido
     * @throws IndexOutOfBoundsException si el índice está fuera del rango de la lista
     */
    E remove(int index) throws IndexOutOfBoundsException;
    /**
     * Método que elimina la primera coincidencia de un elemento de la lista
     * @param element a remover
     * @return true si y solo si el elemento se elimina correctamente; false de lo contrario
     */
    boolean delete(E element);
    /**
     * Método que devuelve el índice de la primera aparición del elemento especificado en esta lista, 
     * o -1 si esta lista no contiene el elemento. <p>
     * Más formalmente, devuelve el índice i más bajo tal que (o == null? Get (i) == null: o.equals (get (i))), 
     * o -1 si no existe tal índice.
     * @param element - elemento para buscar
     * @return el índice de la primera aparición del elemento especificado en esta lista, 
     * o -1 si esta lista no contiene el elemento
     */
    int indexOf(E element);
}