public class LinkedListDS<T> {

    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data){
            this.data = data;
            this.next = null;
        }
    }
    Node<T> head;
    Node<T> tail;
    int size;

    public void add(T data) {
        var node = new Node<T>(data);
        if(this.head == null) {
            this.head = node;
            this.tail = node;
        }else {
            this.tail.next = node;
            this.tail = node;
        }
        size++;
    }

    public boolean remove(T data){
        if(this.head == null){
            return false;
        }
        if(this.head.data.equals(data)){
            this.head = this.head.next;
            if (this.head == null){
                this.tail = null;
            }
            size--;
            return true;
        }
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                // If removing tail, update tail pointer
                if (current.next == tail) {
                    tail = current;
                }
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Display the list
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node<T> current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Get size of the list
    public int size() {
        return size;
    }

    // Main method for testing
    public static void main(String[] args) {
        LinkedListDS<String> list = new LinkedListDS<String>();

        // Test add operations
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.display(); // Output: List: Apple -> Banana -> Cherry -> null

        // Test remove operations
        list.remove("Banana");
        list.display(); // Output: List: Apple -> Cherry -> null

        list.add("Date");
        list.display(); // Output: List: Apple -> Cherry -> Date -> null

        list.remove("Apple");
        list.display(); // Output: List: Cherry -> Date -> null

        LinkedListDS<Integer> integerlist = new LinkedListDS<Integer>();
        integerlist.add(1);
        integerlist.add(2);
        integerlist.add(3);
        integerlist.display();
        integerlist.remove(3);
        integerlist.display();
        integerlist.remove(2);
        integerlist.display();

        System.out.println("Size: " + list.size()); // Output: Size: 2

    }




}
