package ds;

import java.util.Arrays;
import java.util.Iterator;


/**
 * Esta clase​ nos permita crear colecciones dinámicas que almacenen valores de cualquier naturaleza. 
 * Así, utilizando la ​ misma clase​, podremos crear ​ diferentes objetos DynArray​ que nos permitan almacenar enteros, 
 * números flotantes, cadenas de texto o cualquier otro tipo de objeto. <p>
 * Se inicializará con un tamaño determinado, pero irá automáticamente incrementando su tamaño si es necesario.
 * 
 * @author jorgePicon
 * @version v1.0.0
 */
@SuppressWarnings("unchecked")
public class DynArray<E> implements DynList<E>, Iterable<E>{
    private static final int DEFAULT_SIZE = 10;
    private E[] dynArr; //Colección de elementos
    private int lastP;  //Primera posición vacía de la colección

    //Constructores:
    /**
     * Constructor por defecto.
     * <p> Tamaño por defecto = DEFAULT_SIZE
     */
    public DynArray() {
        this.dynArr = (E[]) new Object[DEFAULT_SIZE];
        this.lastP = 0;
    }
    /**
     * Constructor que inicializa la colección con un tamaño indicado por el parámetro de tipo entero
     * @param cap
     * @throws IllegalArgumentException si el argumento es null
     */
    public DynArray(int cap) throws IllegalArgumentException{
        if(cap<=0) throw new IllegalArgumentException("Tamaño no válido: " + cap);
        
        this.lastP = 0;
        this.dynArr = (E[]) new Object[cap];
    }
    /**
     * Constructor que inicializa la colección con otro array pasado como parámetro
     * @param arr
     * @throws IllegalArgumentException si el argumento es null
     */
    public DynArray(E[] arr) throws IllegalArgumentException{
        if(arr.equals(null)) throw new IllegalArgumentException("Argumento E[]: null");

        this.lastP = arr.length;
        // Hacemos una copia de los datos, arr y this.dynArr serán objetos independientes
        this.dynArr = Arrays.copyOf(arr, this.lastP);
    }
    /**
     * Constructor que inicializa la colección con otro objeto DynArray pasado como parámetro. <p>
     * La capacidad inicial será la del array suministrado.
     * 
     * @param arr colección a partir de la cual se crea
     * @throws IllegalArgumentException si el argumento es null
     */
    public DynArray(DynArray<E> arr) throws IllegalArgumentException{
        if(arr.equals(null)) throw new IllegalArgumentException("Argumento DynArray[]: null");
        
        this.lastP = arr.lastP;
        // Hacemos una copia de los datos, arr y this.dynArr serán objetos independientes
        // this.dynArr = (E[]) arr.clone(); (No es seguro)
        this.dynArr = Arrays.copyOf(arr.dynArr, arr.lastP);
    }

    //Métodos privados:

    /**
     * Metodo que incrementa el tamaño de la colección en un 50%
     */
    private void growArray(){
        this.dynArr = Arrays.copyOf(this.dynArr, (int)(this.lastP*1.5 + 1));
    }
    /**
     * Método que reordena los elementos de un array a partir de una posición dada
     * @param index la posición de referencia
     */
    private void relocateArray(int index) {
        for(int i=index; i<this.lastP-1; i++) 
            this.dynArr[i] = this.dynArr[i+1];
        this.dynArr[--this.lastP] = null;
    }

    //Métodos públicos:

    /**
     * Devuelve el número de elementos contenidos en la colección dinámico
     * 
     * @return el número (int) de elementos de la colección
     */
    public int size(){ return this.lastP; }

    /**
     * Indica si la colección está vacío o no
     * @return true si la colección está vacía, false de lo conrario.
     */
    public boolean isEmpty() { 
        return (this.lastP == 0);
    }

    /**
     * Vacía la colección.
     * Al poner la primera posición vacía a 0, el resto de elementos no serám accesibles.
     */
    public void clear(){
            this.lastP = 0;
    }

    /**
     * Devuelve el elemento de la posición indicada en la colección. 
     * 
     * @param index índice del elemento a devolver
     * @return el elemento en la posición indicada en la colección
     * @throws IndexOutOfBoundsException si el índice está fuera de rango <pre> (index < 0 || index >= size())
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if(index<0 || index>=this.lastP) throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);

        return this.dynArr[index];
    }

    /**
     * Añade el nuevo elemento al final de la colección. 
     * 
     * @param element nuevo elemento que se añadirá a la colección.
     */
    @Override
    public boolean add(E element) {
        this.add(this.lastP, element);
        return true;
    }

    /**
     * Método que inserta el nuevo elemento en la posición indicada. <p>
     * En caso de que no existiera espacio disponible, se incrementará automaticamente 
     * el tamaño de la colección en un 50%
     * 
     * @param index posición en la que se insertará el elemento
     * @param element el nuevo elemento que se añadirá a la colección
     * @throws IndexOutOfBoundsException si el índice está fuera de rango <pre> (index < 0 || index > size())
     */
    @Override
    public boolean add(int index, E element) throws IndexOutOfBoundsException{
        if(index<0 || index>this.lastP) throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        
        //Comprobamos si la colección está llena y si está, la ampliamos un 50%.
        if(this.lastP == this.dynArr.length) growArray();

        // desplazamos los elementos desde index hacia la derecha para tener espacio donde insertar el nuevo elemento
        for(int i=lastP; i>index; i--) this.dynArr[i]=this.dynArr[i-1];

        // Insertamos el nuevo elemento.
        this.dynArr[index]= element;
        // Actualizamos la nueva posición vacía
        this.lastP++;
        return true;
    }

    /**
     * Reemplaza el elemento en la posición especificada en esta lista con el elemento especificado. <br>
     * 
     * En caso de que la posición indicada sea incorrecta, lanzará una excepción 
     * 
     * @param index posición de la colección que se actualizará
     * @param element el nuevo elemento a insertar
     */
    public void set(int index, E element) throws IndexOutOfBoundsException {
        if(index<0 || index>=this.lastP) throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        
        this.dynArr[index] = element;
    }
    /**
     * Elimina la posición indicada de la colección. 
     * 
     * @param index posición de la colección que se eliminará
     * @return el valor de la posición eliminada
     * @throws IndexOutOfBoundsException si el índice está fuera de rango <pre> (index < 0 || index >= size())
     */
    public E remove(int index) throws IndexOutOfBoundsException{
        if(index<0 || index>=lastP) throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        // elemento a eliminar:
        E item = this.dynArr[index];

        //Eliminamos el elemento y reagrupamos la colección
        this.dynArr[index] = null;
        relocateArray(index);
        return item;
    }

    /**
     * Elimina la primera ocurrencia del elemento indicado si se encuentra en la colección. 
     * 
     * @param element elemento a eliminar
     * @return true si se encontró y borró, false de lo contrario.
     */
    public boolean delete(E element) {
        // Buscamos el elemento en la colección
        int index = this.indexOf(element);

        // Eliminamos el elemento 
        if(index!=-1) this.remove(index);
        return (index != -1);
    }

    /**
     * Devuelve la ​posición​ de la primera ocurrencia de elemento en la colección
     * 
     * @param element elemento que se quiere encontrar
     * @return la posición si el elemento está,​ -1​ en caso contrario.
     */
    public int indexOf(E element) {
        for(int i=0; i<lastP; i++){
            if(this.dynArr[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String str = "[";
        for(int i=0; i<lastP; i++){
            str += this.dynArr[i];
            if(i<lastP-1) str += ", ";
        }
        return str += "]";
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int p = 0;
            
            @Override
            public boolean hasNext() { return (p < lastP); }

            @Override
            public E next() { return dynArr[p++]; }

        };
    }
}
