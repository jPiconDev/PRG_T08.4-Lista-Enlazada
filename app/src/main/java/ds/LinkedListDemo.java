package ds;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> l1 = new LinkedList<>();
        System.out.println("l1[" + l1.size() + "]: " + l1);

        System.out.println("Añadimos elementos:");
        l1.add("hi");
        l1.add("bye");
        System.out.println("l1[" + l1.size() + "]: " + l1);

        System.out.println("Recorremos con for-each:");
        for (String s : l1)
            System.out.println(s);

        System.out.println("Borramos la lista:");
        l1.clear()
        System.out.println("l1[" + l1.size() + "]: " + l1);

        System.out.println("Nueva lista:");
        LinkedList<Integer> l2 = new LinkedList<Integer>();
        l2.add(3);
        l2.add(-5);
        l2.add(8);
        System.out.println("l2[" + l2.size() + "]: " + l2);

        System.out.println("Añadimos 4 en la posición 2");
        l2.add(2, 4);
        System.out.println("l2[" + l2.size() + "]: " + l2);

        System.out.println(
        "Eliminamos el valor de la posición 1");
        l2.remove(1);
        System.out.println("l2[" + l2.size() + "]: " + l2);

        System.out.println("Eliminamos el valor 4");
        l2.delete(4);
        System.out.println("l2[" + l2.size() + "]: " + l2);

        System.out.println("Cambiamos el segundo elemento");
        l2.set(1, 99);
        System.out.println("l2[" + l2.size() + "]: " + l2);

        System.out.println("Generamos excepción");
        try { l2.set(100, 0); }
        catch(Exception e) { System.out.println(e);}
    }
}
