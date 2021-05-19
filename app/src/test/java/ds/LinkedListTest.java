package ds;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LinkedListTest {
    
    @Test
    public void nuevaLista(){
        LinkedList<String> l1 = new LinkedList<>();
        String str = ("l1[" + l1.size() + "]: " + l1);
        assertEquals("l1[0]: []", str);
        
        //Añadimos elementos:
        l1.add("hi");
        l1.add("bye");
        str = ("l1[" + l1.size() + "]: " + l1);
        assertEquals("l1[2]: [hi, bye]", str);
    
        //Recorremos con for-each:
        str = "";
        for (String s : l1)
            str += s;
        assertEquals("hibye", str);

        //Borramos la lista:
        l1.clear();
        str = ("l1[" + l1.size() + "]: " + l1);
        assertEquals("l1[0]: []", str);

        //Nueva lista:
        LinkedList<Integer> l2 = new LinkedList<Integer>();
        l2.add(3);
        l2.add(-5);
        l2.add(8);
        str = ("l2[" + l2.size() + "]: " + l2);
        assertEquals("l2[3]: [3, -5, 8]", str);

        //Añadimos 4 en la posición 2
        l2.add(2, 4);
        str = ("l2[" + l2.size() + "]: " + l2);
        assertEquals("l2[4]: [3, -5, 4, 8]", str);

        //Eliminamos el valor de la posición 1
        l2.remove(1);
        str = ("l2[" + l2.size() + "]: " + l2);
        assertEquals("l2[3]: [3, 4, 8]", str);

        //Eliminamos el valor 4
        l2.delete(4);
        str = ("l2[" + l2.size() + "]: " + l2);
        assertEquals("l2[3]: [3, 8]", str);
    }

    
    // @Test
    // public void name() {
        
    //     String str = ;
    //     assertEquals(, );
    // }
}
