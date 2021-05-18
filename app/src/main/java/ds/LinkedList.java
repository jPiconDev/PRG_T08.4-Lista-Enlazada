package ds;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements DynList<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int len;

    public LinkedList(){
        head = null;
        tail = null;
        len = 0;
    }

    public LinkedList(Collection<? extends T> c){
        //Creamos una la lista y añadimos cada elemento de la colección:
        LinkedList<T> list = new LinkedList<>();
        for (T t : c) {
            list.add(t);
        }
    }

    /**
     * Agrega el elemento especificado al final de esta lista.
     * 
     * @param data El elemento a añadir
     * @return 
     */
    @Override
    public boolean add(T data){
        //Creamos un nuevo nodo con el parámetro recibido
        ListNode<T> newNode = new ListNode<>(data);

        // Si el parámetro es null:
        if(data == null) return false;

        // Si la lista está vacía:
        if(len==0) { 
            tail = head;
            head = newNode;
            len++;
            return true;
        }

        // Si la lista no está vacía (len!=0) añadimos al final el nuevo nodo:
        newNode.prev = tail; 
        newNode.next = null;  
        tail = newNode;
        len++;
        return true;
    }

    @Override
    public boolean add(int index, T data) throws IndexOutOfBoundsException {
        //Comprobamos que el parámetro recicbido no sea null:
        if(head == null || data == null) return false; 

        //Si el índice está fuera del tamaño de la lista, lanzamos la excepción
        if(index < 0 || index > len) throw new IndexOutOfBoundsException();

        //Creamos un nuevo nodo a pertir del parámetro recibido
        ListNode<T> newNode = new ListNode<>(data);

         //Si el índice es el primero de la lista:
         if(index == 1) {
            newNode.next = head;
            head = newNode;
            len++;
            return true;
        } 

        //Si el índice es el último de la lista:
        if(index == len) {
            newNode.prev = tail;
            tail = newNode;
            len++;
            return true;
        }

        //Si el índice está en medio de la lista:
        ListNode<T> node = head;
        int cont = 2;
        while(node.getNext() != null) {
            node = node.getNext();
            if(cont == index) {
                newNode.prev = node.prev;
                newNode.next = node;
                node.getPrev().next = newNode;
                node.prev = newNode;
                len++;
                return true;
            }
            cont++;
        }
        return false;
    }

    /**
     * Elimina la primera aparición del elemento especificado de esta lista, 
     * si está presente. Si esta lista no contiene el elemento, no se modifica. <p>
     * Más formalmente, elimina el elemento con el índice más bajo i tal que 
     * (data == null? Get (i) == null: data.equals (get (i))) (si tal elemento existe). 
     * Devuelve verdadero si esta lista contiene el elemento especificado 
     * (o de manera equivalente, si esta lista cambió como resultado de la llamada).
     * 
     * @param data
     * @return boolean
     */
    @Override
    public boolean delete(T data) throws NoSuchElementException {
        //Creamos un nuevo nodo a pertir del parámetro recibido
        ListNode<T> newNode = new ListNode<>(data);

        // Si la lista está vacía:
        if(len == 0) throw new NoSuchElementException();

        // Si head o el parámetro es null:
        if(data == null) return false;

        // Si el nodo a eliminar está a principio de la lista;
        if(head.equals(newNode)) {
            head = head.next;
            head.prev = null;
            return true;
        }

        // Si el nodo a eliminar está a final de la lista;
        if(tail.equals(newNode)) {
            tail = tail.prev;
            tail.next = null;
            return true;
        }

        // Si el nodo a eliminar está en medio de la lista la recorremos;
        ListNode<T> node = head;
        while(node.next != null){
            node = node.getNext();
            if(node.equals(newNode)) {
                node.getPrev().next = node.next;
                node.getNext().prev = node.prev;
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException{
        //Si el índice está fuera del tamaño de la lista, lanzamos la excepción
        if(index < 0 || index > len) throw new IndexOutOfBoundsException();

        // Recorremos la lista;
        ListNode<T> node = head;
        int cont = 1;
        while(node != null){
            if(cont == index) {
                node.getPrev().next = node.next;
                node.getNext().prev = node.prev;
                return node.data;
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException{
        //Si el índice está fuera del tamaño de la lista, lanzamos la excepción
        if(index < 0 || index > len) throw new IndexOutOfBoundsException();

        ListNode<T> node = head;
        int cont = 1;
        while(node != null){
            if(cont == index) {
                return node.data;
            }
            cont++;
        }
        return null;
    }

    public int indexOf(T data){
        // Si el parámetro es null:
        if(data == null) return -1;

        //Creamos un ListNode con los datos del parámetro:
        ListNode<T> newNode = new ListNode<>(data);
        ListNode<T> node = head;
        int cont = 1;
        while(node != null){
            if(node.equals(newNode)) {
                return cont;
            }
            cont++;
        }
        return -1;
    }
    

    /**
     * Devuelve true si la lista está vacía, false de lo contrario.
     * 
     * @return boolean
     */
    public boolean isEmpty(){
        return head==null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            ListNode<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if(hasNext()){
                    T data = node.data;
                    node = node.next;
                    return data;
                }
                return null;
            }
        };
    }
    
    /**
     * Devuelve el número de objetos {@link ListNode} que contiene la lista.
     * 
     * @return len El número de elementos de la lista
     */
    public int size(){
        return len;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        len = 0;
    }

    @Override
    public void set(int index, T data) throws IndexOutOfBoundsException {
        //Comprobamos que el parámetro recicbido no sea null:
        if(data == null) return; 

        //Si el índice está fuera del tamaño de la lista, lanzamos la excepción
        if(index < 0 || index > len) throw new IndexOutOfBoundsException();

        // Creamos un nuevo nodo
        ListNode<T> newNode = new ListNode<T>(data); 

        //Si el índice es el primero de la lista:
        if(index == 1) {
            newNode.next = head.next;
            head = newNode;
            return;
        } 

        //Si el índice es el último de la lista:
        if(index == len) {
            newNode.prev = tail.prev;
            tail = newNode;
            return;
        }

        //Si el índice está en medio de la lista:
        ListNode<T> node = head;
        int cont = 2;
        while(node.getNext() != null) {
            node = node.getNext();
            if(cont == index) {
                newNode.prev = node.prev;
                newNode.next = node.next;
                node.getPrev().next = newNode;
                node.getNext().getNext().prev = newNode;
            }
            cont++;
        }
    }

    @Override
    public String toString() {
        String str = "";
        ListNode<T> node = head;
        // if(node.data != null) {
        //     str += node.toString();
        //     return "[" + str + "]";
        // } 

        while(node != null) {
            str += node.toString();
            if(node.getNext() != null) {
                str += ", ";
                node = node.getNext();
            }
        }
        return "[" + str + "]";
    }

    // Subclase ListNode ##################################################

    public class ListNode<E> {
        private E data;
        private ListNode<E> next;
        private ListNode<E> prev;
    
        public ListNode(){
            data = null;
            next = null;
            prev = null;
        }
        public ListNode(E data){
            this.data = data;
        }
    
        public E getData() {
            return data;
        }
        public ListNode<E> getNext() {
            return next;
        }
        public void setNext(ListNode<E> next) {
            this.next = next;
        }
        public ListNode<E> getPrev() {
            return prev;
        }
        public void setPrev(ListNode<E> prev) {
            this.prev = prev;
        }
    
        @Override
        public String toString() {
            return  data.toString();
        }
    }
}