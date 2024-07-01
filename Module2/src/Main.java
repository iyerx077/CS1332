public class Main {
    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        System.out.println("INitial head is: " + linkedList.getHead());
        for (int i = 0; i<9; i++){
            linkedList.addToFront(i + "a");
            //System.out.println(linkedList.getHead().getData());
        }
        linkedList.addAtIndex(4, "index at 4");
        for (int i = 0; i<10; i++){
            System.out.println(linkedList.removeFromFront());
        }
        linkedList.addToFront("2a");
        linkedList.addToFront("3a");
        linkedList.addToFront("4a");
        System.out.println(linkedList.getHead().getData());
        System.out.println(linkedList.getTail().getData());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.getTail().getData());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.getTail().getData());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.getTail().getData());
        System.out.println(linkedList.getHead().getData());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.size());
        linkedList.addToBack("1a");
        linkedList.addToBack("2a");
        linkedList.addToBack("3a");
        linkedList.addToFront("4a");
        System.out.println(linkedList.getTail().getData());
        System.out.println(linkedList.getHead().getData());
        System.out.println(linkedList.size());
        System.out.println(linkedList.getHead().getNext());
        System.out.println(linkedList.getTail().getData());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.removeFromFront());
        System.out.println("Tail is: " + linkedList.getTail().getData());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.getHead().getNext());
        System.out.println(linkedList.getHead().getData());
        //System.out.println(linkedList.removeFromBack());
        System.out.println("Size is: " + linkedList.size());
        System.out.println(linkedList.removeFromBack());
        System.out.println("Size is: " + linkedList.size());
        System.out.println(linkedList.removeFromBack());
        System.out.println(linkedList.getTail());
        System.out.println(linkedList.getHead());
        linkedList.addToBack("CHeck");
        System.out.println(linkedList.getTail().getData());
        System.out.println(linkedList.getHead().getData());
    }
}