public class SimpleLinkedList<T> {

   static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    int size;
    Node<T> head;
    Node<T> tail;

    void add(T data) {
        var node = new Node<T>(data);
        if(this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }
    T removeHead() {
        if (this.head == null) {
            return null;
        }
        T data = this.head.data;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;
        }
        this.size--;
        return data;
    }

    // Remove element by value (removes first occurrence)
    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        // If head needs to be removed
        if (head.data.equals(data)) {
            head = head.next;
            if (head == null) {
                tail = null;
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
        SimpleLinkedList<String> list = new SimpleLinkedList<String>();

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

        System.out.println("Size: " + list.size()); // Output: Size: 2
    }
}
