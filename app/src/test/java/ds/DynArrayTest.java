//-----------------------------------------------------------------------------------------------
//  Unit Tests:
//-----------------------------------------------------------------------------------------------
// Test 1: Constructor por defecto
// Test 2: Constructor con tamaño 20
// Test 3: Constructor desde array (también usa get)
// Test 4: Constructor desde dinámico (también usa get)
// Test 5: clear()
// Test 6: add()
// Test 7: get()
// Test 8: get() --> IndexOutOfBoundsException
// Test 9: add() --> IndexOutOfBoundsException
// Test 10: add() con redimensión
// Test 11: set() at index 
// Test 12: set() --> IndexOutOfBoundsException
// Test 13: remove() at index 
// Test 14: remove() --> IndexOutOfBoundsException
// Test 15: delete() de elemento del array
// Test 16: delete() de elemento que no está en el array
// Test 17: indexOf() de elemento del array
// Test 18: indexOf() de elemento que no está en el array
// Test 19: toString()
// Test 20: toString() con múltiples operaciones
//-----------------------------------------------------------------------------------------------

package ds;

import org.junit.*;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class DynArrayTest  {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    //-----------------------------------------------------------------------------------------------
    // Set-Up

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    //-----------------------------------------------------------------------------------------------
    // Tests
    
    // Test 1: Constructor por defecto
    @Test
    public <E> void  test_Constr() {
        DynArray<E> dynArr = new DynArray<>(); // Creamos el array dinámico 
        assertEquals(dynArr.size(), 0);         // Comprobamos ocupación =0
        assertTrue(dynArr.isEmpty());           // Comprobamos vacío =true
    }

    // Test 2: Constructor con tamaño 20
    @Test 
    public <E> void test_Constr_20() {
        DynArray<E> dynArr = new DynArray<>(20);   // Creamos el array dinámico 
        assertEquals(dynArr.size(), 0);             // Comprobamos ocupación =0
        assertTrue(dynArr.isEmpty());               // Comprobamos vacío =true
    }

    // Test 3: Constructor desde array (también usa get)
    @Test 
    public void test_Constr_Arr() {
        String[] testset = {"cero", "uno", "dos"};
        String[] testset_cpy = {"cero", "uno", "dos"};

        DynArray<String> dynArr = new DynArray<>(testset);  // Creamos el array dinámico con los datos de prueba

        // Borramos los datos del array de prueba
        for(int i=0; i<testset.length; i++) testset[i] = null;

        assertEquals(dynArr.size(), 3);             // Comprobamos ocupación =3
        assertFalse(dynArr.isEmpty());              // Comprobamos vacío =false

        assertTrue(compareArr(dynArr, testset_cpy));    // Comprobamos que ambos arrays contienen lo mismo
    }    

    // Test 4: Constructor desde array dinámico (también usa get)
    @Test 
    public void test_Constr_DynArr() {
        String[] testset = {"cero", "uno", "dos"};

        DynArray<String> dynArr1 = new DynArray<>(testset); // Creamos el array dinámico con los datos de prueba

        // Borramos los datos del array de prueba
        for(int i=0; i<testset.length; i++) testset[i] = null;

        DynArray<String> dynArr2 = new DynArray<>(dynArr1); // Creamos el 2do. array dinámico a partir del 1ro.
        
        assertEquals(dynArr2.size(), 3);            // Comprobamos ocupación =3
        assertFalse(dynArr2.isEmpty());             // Comprobamos vacío =false
        
        assertTrue(compareDynArr(dynArr1, dynArr2));    // Comprobamos que ambos arrays contienen lo mismo
    }

    // Test 5: clear()
    @Test 
    public void test_clear() {
        String[] testset = {"cero", "uno", "dos"};

        DynArray<String> dynArr = new DynArray<>(testset);  // Creamos el array dinámico con los datos de prueba

        // Borramos los datos del array de prueba
        for(int i=0; i<testset.length; i++) testset[i] = null;

        assertEquals(dynArr.size(), 3);             // Comprobamos ocupación =3
        assertFalse(dynArr.isEmpty());              // Comprobamos vacío =false

        dynArr.clear(); // Borramos el array dinámico

        assertEquals(dynArr.size(), 0);            // Comprobamos ocupación =0
        assertTrue(dynArr.isEmpty());              // Comprobamos vacío =true
    }

    // Test 6: add()
    @Test
    public void test_add() {
        DynArray<String> dynArr = new DynArray<>();     // Creamos el array dinámico
        
        dynArr.add("test");                         // Añadimos un dato de prueba

        assertEquals(dynArr.size(), 1);             // Comprobamos ocupación =1
        assertFalse(dynArr.isEmpty());              // Comprobamos vacío =false        
    }

    // Test 7: get()
    @Test
    public void test_get() {
        DynArray<String> dynArr = new DynArray<>();     // Creamos el array dinámico
        
        dynArr.add("test");                         // Añadimos un dato de prueba

        assertEquals(dynArr.get(0), "test");        // Comprobamos el dato de prueba
    }

    // Test 8: get() --> IndexOutOfBoundsException
    @Test(expected = IndexOutOfBoundsException.class) 
    public <E> void test_get_IndexOutOfBoundsException() {
        DynArray<E> dynArr = new DynArray<>();     // Creamos el array dinámico
        
        dynArr.get(0);                              // Debe lanzar la excepción
    }

    // Test 9: add() --> IndexOutOfBoundsException
    @Test(expected = IndexOutOfBoundsException.class) 
    public void test_add_IndexOutOfBoundsException() {
        DynArray<String> dynArr = new DynArray<>();     // Creamos el array dinámico
        
        dynArr.add(1, "test");                      // Debe lanzar la excepción
    }

    // Test 10: add() resize
    @Test
    public void test_add_resize() {
        final int SIZE = 12;

        DynArray<String> dynArr = new DynArray<>();     // Creamos el array dinámico
        
        for(int i=1; i<=SIZE; i++) dynArr.add(String.valueOf(i));   // Añadimos datos de prueba
        
        assertEquals(dynArr.size(), SIZE);          // Comprobamos ocupación =12
        assertFalse(dynArr.isEmpty());              // Comprobamos vacío =false

        // Comprobamos contenido del array dinámico
        assertTrue(compareArr(dynArr, new String[] {
                    "1", "2", "3","4", "5", "6","7", "8", "9","10", "11", "12"
                }));
    }

    // Test 11: set() at index 
    @Test
    public void test_set() {
        String[] testset = {"cero", "uno", "dos"};  

        DynArray<String> dynArr = new DynArray<>(testset);  // Creamos el array dinámico con los datos de prueba

        // Borramos los datos del array de prueba
        for(int i=0; i<testset.length; i++) testset[i] = null;

        dynArr.set(1, "1");                         // Modificamos con un dato de prueba

        assertEquals(dynArr.size(), 3);             // Comprobamos ocupación =3
        assertFalse(dynArr.isEmpty());              // Comprobamos vacío =false
        assertEquals(dynArr.get(1), "1");           // Comprobamos el dato de prueba
    }      
    
    // Test 12: set() --> IndexOutOfBoundsException
    @Test(expected = IndexOutOfBoundsException.class) 
    public void test_set_IndexOutOfBoundsException() {
        DynArray<String> dynArr = new DynArray<>();     // Creamos el array dinámico
        
        dynArr.set(0, "test");                      // Debe lanzar la excepción
    }    

    // Test 13: remove() at index 
    @Test
    public void test_remove() {
        String[] testset = {"cero", "uno", "dos"};  

        DynArray<String> dynArr = new DynArray<>(testset);  // Creamos el array dinámico con los datos de prueba

        // Borramos los datos del array de prueba
        for(int i=0; i<testset.length; i++) testset[i] = null;

        String value = dynArr.remove(1);            // Eliminamos el dato de la posición 1

        assertEquals(value, "uno");                 // Comprobamos el valor devuelto ="uno"
        assertEquals(dynArr.size(), 2);             // Comprobamos ocupación =2
        assertFalse(dynArr.isEmpty());              // Comprobamos vacío =false
        assertEquals(dynArr.get(1), "dos");         // Comprobamos el dato actual en la posición
    }      

    // Test 14: remove() --> IndexOutOfBoundsException
    @Test(expected = IndexOutOfBoundsException.class) 
    public void test_remove_IndexOutOfBoundsException() {
        DynArray<String> dynArr = new DynArray<>();     // Creamos el array dinámico

        dynArr.add("test");                         // Añadimos un dato de prueba
        dynArr.remove(0);                           // Borramos dato de prueba (no hay datos)

        dynArr.remove(0);                           // Debe lanzar la excepción 
    }    

    // Test 15: delete() de elemento del array
    @Test
    public void test_delete_present() {
        String[] testset = {"cero", "uno", "dos"};  

        DynArray<String> dynArr = new DynArray<>(testset);  // Creamos el array dinámico con los datos de prueba

        // Borramos los datos del array de prueba
        for(int i=0; i<testset.length; i++) testset[i] = null;

        boolean deleted = dynArr.delete("uno");     // Eliminamos el dato "uno"

        assertTrue(deleted);                        // Comprobamos el valor devuelto =true
        assertEquals(dynArr.size(), 2);             // Comprobamos ocupación =2
        assertFalse(dynArr.isEmpty());              // Comprobamos vacío =false
        assertEquals(dynArr.get(1), "dos");         // Comprobamos el dato actual en la posición
    }      

    // Test 16: delete() de elemento que no está en el array
    @Test
    public void test_delete_not_present() {
        DynArray<String> dynArr = new DynArray<>();     // Creamos el array dinámico

        boolean deleted = dynArr.delete("test");    // Eliminamos el dato "test"
        assertFalse(deleted);                       // Comprobamos el valor devuelto =false
    }    

    // Test 17: indexOf() de elemento del array
    @Test
    public void test_indexOf_present() {
        String[] testset = {"cero", "uno", "dos"};  

        DynArray<String> dynArr = new DynArray<>(testset);  // Creamos el array dinámico con los datos de prueba

        // Borramos los datos del array de prueba
        for(int i=0; i<testset.length; i++) testset[i] = null;

        int index = dynArr.indexOf("uno");          // Buscamos el dato "uno"
        assertEquals(index, 1);                     // Comprobamos la posición devuelta =1
    }      

    // Test 18: indexOf() de elemento que no está en el array
    @Test
    public void test_indexOf_not_present() {
        DynArray<String> dynArr = new DynArray<>();     // Creamos el array dinámico

        int index = dynArr.indexOf("test");         // Buscamos el dato "test"
        assertEquals(index, -1);                    // Comprobamos la posición devuelta =-1
    }        

    // Test 19: toString()
    @Test
    public void test_toString() {
        String[] testset = {"cero", "uno", "dos"};  
        String testset_prt = "[cero, uno, dos]";

        DynArray<String> dynArr = new DynArray<>(testset);  // Creamos el array dinámico con los datos de prueba

        // Borramos los datos del array de prueba
        for(int i=0; i<testset.length; i++) testset[i] = null;

        assertEquals(testset_prt, dynArr.toString());   // Comprobamos el valor devuelto por el método toString()
    }       

    // Test 20: toString() con múltiples operaciones
    @Test
    public void test_toString_multiple() {
        String testset_prt = "[uno, dos, tres, cuatro]";

        DynArray<String> dynArr = new DynArray<>();         // Creamos el array dinámico

        dynArr.add("2");
        dynArr.add("3");
        dynArr.add(0, "uno");
        dynArr.add(3, "cuatro");
        dynArr.remove(1);
        dynArr.set(1, "tres");
        dynArr.add(1, "dos");

        assertEquals(testset_prt, dynArr.toString());   // Comprobamos el valor devuelto por el método toString()
    }    
    
    // Test 21: for-each
    @Test
    public void test_forEach() {
        String testset_out = "Sensor: temp1, Sensor: temp2, Sensor: hum1, Sensor: press1, ";
        Sensor sen1 = new Sensor("temp1");
        Sensor sen2 = new Sensor("temp2");
        Sensor sen3 = new Sensor("hum1");
        Sensor sen4 = new Sensor("press1");
        Sensor[] arr = new Sensor[]{sen1, sen2, sen3, sen4};
        DynArray<Sensor> dynArr = new DynArray<>(arr);  // Creamos el array dinámico a partir de un array de sensores

        String out = "";
        for(Sensor s: dynArr) out += "Sensor: " + s.getName() + ", "; //Recorremos el array que nos devuelve el nombre de cada sensor
        assertEquals(testset_out, out);   // Comprobamos el valor devuelto por el método toString()
    }

    // Test 22: for-each-add/remove
    @Test
    public void test_forEach_add_rem() {
        String testset_out = "Sensor: temp1, Sensor: temp2, Sensor: hum2, Sensor: press1, ";

        DynArray<Sensor> dynArr = new DynArray<>();  // Creamos el array dinámico a partir de un array de sensores
        dynArr.add( new Sensor("temp1"));
        dynArr.add( new Sensor("temp2"));
        dynArr.add( new Sensor("hum1"));
        dynArr.add( new Sensor("press2"));
        dynArr.remove(2);
        dynArr.add(2, new Sensor("hum2"));
        dynArr.set(3, new Sensor("press1"));

        String out = "";
        for(Sensor s: dynArr) out += "Sensor: " + s.getName() + ", "; //Recorremos el array que nos devuelve el nombre de cada sensor
        assertEquals(testset_out, out);   // Comprobamos el valor devuelto por el método toString()
    }       

    public class Sensor {
        private String name;
        public Sensor(String name) { this.name = name; }
        public String getName() { return this.name; };
    }


    //-----------------------------------------------------------------------------------------------
    // Utility methods

    private boolean compareArr(DynArray<String> dyn, String[] arr) {
        for(int i=0; i<arr.length; i++)
            if(!dyn.get(i).equals(arr[i])) return false;
        return true;
    }

    private boolean compareDynArr(DynArray<String> dynArr1, DynArray<String> dynArr2) {
        if(dynArr1.size() != dynArr2.size()) return false;

        for(int i=0; i<dynArr1.size(); i++)
            if(!dynArr1.get(i).equals(dynArr2.get(i))) return false;
        
        return true;
    }



    /*
    LinkedList<String> l1 = new LinkedList<>();
    System.out.println("l1[" + l1.size() + "]: " + l1);

    l1[0]: []

    System.out.println("Añadimos elementos:");
    l1.add("hi");
    l1.add("bye");
    System.out.println("l1[" + l1.size() + "]: " + l1); 
    
    Añadimos elementos:
    l1[2]: [hi, bye]

    System.out.println("Recorremos con for-each:");
    for (String s : l1)
        System.out.println(s); 
    
    Recorremos con for-each:
    hi
    bye

    System.out.println("Borramos la lista:");
    l1.clear()
    System.out.println("l1[" + l1.size() + "]: " + l1); 
    
    Borramos la lista:
    l1[0]: []

    System.out.println("Nueva lista:");
    LinkedList<Integer> l2 = new LinkedList<Integer>();
    lista.add(3);
    lista.add(-5);
    lista.add(8);
    System.out.println("l2[" + l2.size() + "]: " + l2); 
    
    Nueva lista:
    l2[3]: [3, -5, 8]

    System.out.println("Añadimos 4 en la posición 2");
    l2.add(2, 4);
    System.out.println("l2[" + l2.size() + "]: " + l2); 
    
    Añadimos 4 en la posición 2
    l2[4]: [3, -5, 4, 8]

    System.out.println("Eliminamos el valor de la posición 1");
    l2.remove(1);
    System.out.println("l2[" + l2.size() + "]: " + l2);
    
    
    Eliminamos el valor de la posición 1
    l2[3]: [3, 4, 8]

    System.out.println("Eliminamos el valor 4");
    l2.delete(4);
    System.out.println("l2[" + l2.size() + "]: " + l2); 
    
    Eliminamos el valor 4
    l2[3]: [3, 8]

    System.out.println("Cambiamos el segundo elemento");
    l2.set(1, 99);
    System.out.println("l2[" + l2.size() + "]: " + l2); 
    
    Cambiamos el segundo elemento
    l2[2]: [3, 99]

    System.out.println("Generamos excepción");
    try { l2.set(100, 0); }
    catch(Exception e) { System.out.println(e);} 
    
    Generamos una excepción
    java.lang.IndexOutOfBoundsException:
    Índice fuera de rango: 100    
    */
}
