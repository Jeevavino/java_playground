import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Student Class for demonstrations
class Student implements Comparable<Student> {
    private String name;
    private int age;
    private double marks;
    
    public Student(String name, int age, double marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }
    
    // Comparable implementation for TreeSet/TreeMap
    @Override
    public int compareTo(Student other) {
        return Double.compare(this.marks, other.marks);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return age == student.age && 
               Double.compare(student.marks, marks) == 0 && 
               Objects.equals(name, student.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age, marks);
    }
    
    @Override
    public String toString() {
        return String.format("Student{name='%s', age=%d, marks=%.1f}", name, age, marks);
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getMarks() { return marks; }
}

// Custom Comparator for different sorting
class StudentAgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getAge(), s2.getAge());
    }
}

class StudentNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}

// List Interface Demonstration
class ListDemo {
    public void demonstrateArrayList() {
        System.out.println("=== ARRAYLIST DEMO ===");
        ArrayList<String> fruits = new ArrayList<>();
        
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple"); // Duplicates allowed
        
        System.out.println("ArrayList: " + fruits);
        System.out.println("Size: " + fruits.size());
        System.out.println("Element at index 1: " + fruits.get(1));
        
        // Inserting at specific position
        fruits.add(1, "Mango");
        System.out.println("After inserting Mango at index 1: " + fruits);
        
        // Removing elements
        fruits.remove("Apple"); // Removes first occurrence
        System.out.println("After removing first Apple: " + fruits);
        
        // Iterating
        System.out.print("Iterating using enhanced for: ");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println("\n");
    }
    
    public void demonstrateLinkedList() {
        System.out.println("=== LINKEDLIST DEMO ===");
        LinkedList<Integer> numbers = new LinkedList<>();
        
        // Adding elements
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        
        // LinkedList specific methods
        numbers.addFirst(5);
        numbers.addLast(40);
        
        System.out.println("LinkedList: " + numbers);
        System.out.println("First element: " + numbers.getFirst());
        System.out.println("Last element: " + numbers.getLast());
        
        // Remove from ends
        numbers.removeFirst();
        numbers.removeLast();
        System.out.println("After removing first and last: " + numbers);
        System.out.println();
    }
    
    public void demonstrateVector() {
        System.out.println("=== VECTOR DEMO (Thread-Safe) ===");
        Vector<String> colors = new Vector<>();
        
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        
        System.out.println("Vector: " + colors);
        System.out.println("Capacity: " + colors.capacity());
        System.out.println("Size: " + colors.size());
        
        // Using Enumeration (old way)
        System.out.print("Using Enumeration: ");
        Enumeration<String> e = colors.elements();
        while (e.hasMoreElements()) {
            System.out.print(e.nextElement() + " ");
        }
        System.out.println("\n");
    }
}

// Set Interface Demonstration
class SetDemo {
    public void demonstrateHashSet() {
        System.out.println("=== HASHSET DEMO ===");
        HashSet<String> cities = new HashSet<>();
        
        cities.add("Mumbai");
        cities.add("Delhi");
        cities.add("Bangalore");
        cities.add("Mumbai"); // Duplicate - won't be added
        
        System.out.println("HashSet: " + cities);
        System.out.println("Size: " + cities.size());
        System.out.println("Contains Mumbai: " + cities.contains("Mumbai"));
        
        // No guaranteed order
        System.out.print("Iteration order: ");
        for (String city : cities) {
            System.out.print(city + " ");
        }
        System.out.println("\n");
    }
    
    public void demonstrateLinkedHashSet() {
        System.out.println("=== LINKEDHASHSET DEMO ===");
        LinkedHashSet<String> orderedCities = new LinkedHashSet<>();
        
        orderedCities.add("Mumbai");
        orderedCities.add("Delhi");
        orderedCities.add("Bangalore");
        orderedCities.add("Chennai");
        
        System.out.println("LinkedHashSet (maintains insertion order): " + orderedCities);
        System.out.println();
    }
    
    public void demonstrateTreeSet() {
        System.out.println("=== TREESET DEMO ===");
        TreeSet<Integer> sortedNumbers = new TreeSet<>();
        
        sortedNumbers.add(50);
        sortedNumbers.add(20);
        sortedNumbers.add(80);
        sortedNumbers.add(10);
        sortedNumbers.add(30);
        
        System.out.println("TreeSet (sorted): " + sortedNumbers);
        System.out.println("First element: " + sortedNumbers.first());
        System.out.println("Last element: " + sortedNumbers.last());
        System.out.println("Elements less than 30: " + sortedNumbers.headSet(30));
        System.out.println("Elements greater than 30: " + sortedNumbers.tailSet(30));
        System.out.println();
    }
    
    public void demonstrateStudentSet() {
        System.out.println("=== TREESET WITH CUSTOM OBJECTS ===");
        TreeSet<Student> studentsByMarks = new TreeSet<>();
        
        studentsByMarks.add(new Student("Alice", 20, 85.5));
        studentsByMarks.add(new Student("Bob", 21, 92.0));
        studentsByMarks.add(new Student("Charlie", 19, 78.5));
        
        System.out.println("Students sorted by marks (Comparable):");
        for (Student s : studentsByMarks) {
            System.out.println(s);
        }
        
        // Using custom comparator
        TreeSet<Student> studentsByAge = new TreeSet<>(new StudentAgeComparator());
        studentsByAge.addAll(studentsByMarks);
        
        System.out.println("\nStudents sorted by age (Comparator):");
        for (Student s : studentsByAge) {
            System.out.println(s);
        }
        System.out.println();
    }
}

// Map Interface Demonstration
class MapDemo {
    public void demonstrateHashMap() {
        System.out.println("=== HASHMAP DEMO ===");
        HashMap<String, Integer> studentMarks = new HashMap<>();
        
        // Adding key-value pairs
        studentMarks.put("Alice", 85);
        studentMarks.put("Bob", 92);
        studentMarks.put("Charlie", 78);
        studentMarks.put("Diana", 88);
        
        System.out.println("HashMap: " + studentMarks);
        System.out.println("Alice's marks: " + studentMarks.get("Alice"));
        System.out.println("Contains key 'Bob': " + studentMarks.containsKey("Bob"));
        System.out.println("Contains value 78: " + studentMarks.containsValue(78));
        
        // Iterating over keys, values, and entries
        System.out.println("\nAll keys: " + studentMarks.keySet());
        System.out.println("All values: " + studentMarks.values());
        
        System.out.println("Key-Value pairs:");
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();
    }
    
    public void demonstrateLinkedHashMap() {
        System.out.println("=== LINKEDHASHMAP DEMO ===");
        LinkedHashMap<String, String> countries = new LinkedHashMap<>();
        
        countries.put("IN", "India");
        countries.put("US", "United States");
        countries.put("UK", "United Kingdom");
        countries.put("JP", "Japan");
        
        System.out.println("LinkedHashMap (maintains insertion order): " + countries);
        System.out.println();
    }
    
    public void demonstrateTreeMap() {
        System.out.println("=== TREEMAP DEMO ===");
        TreeMap<String, Double> productPrices = new TreeMap<>();
        
        productPrices.put("Laptop", 50000.0);
        productPrices.put("Mobile", 15000.0);
        productPrices.put("Tablet", 25000.0);
        productPrices.put("Desktop", 40000.0);
        
        System.out.println("TreeMap (sorted by keys): " + productPrices);
        System.out.println("First key: " + productPrices.firstKey());
        System.out.println("Last key: " + productPrices.lastKey());
        System.out.println("Keys starting from 'M': " + productPrices.tailMap("M"));
        System.out.println();
    }
    
    public void demonstrateHashtable() {
        System.out.println("=== HASHTABLE DEMO (Thread-Safe) ===");
        Hashtable<String, String> capitals = new Hashtable<>();
        
        capitals.put("India", "New Delhi");
        capitals.put("USA", "Washington DC");
        capitals.put("UK", "London");
        capitals.put("France", "Paris");
        
        System.out.println("Hashtable: " + capitals);
        System.out.println("Capital of India: " + capitals.get("India"));
        System.out.println();
    }
}

// Queue Interface Demonstration
class QueueDemo {
    public void demonstratePriorityQueue() {
        System.out.println("=== PRIORITYQUEUE DEMO ===");
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        pq.offer(30);
        pq.offer(10);
        pq.offer(50);
        pq.offer(20);
        
        System.out.println("PriorityQueue: " + pq);
        System.out.println("Head element (smallest): " + pq.peek());
        
        System.out.print("Polling elements in priority order: ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println("\n");
    }

}

// Collections Utility Class Demonstration
class CollectionsUtilityDemo {
    public void demonstrateCollectionsClass() {
        System.out.println("=== COLLECTIONS UTILITY CLASS DEMO ===");
        List<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 45, 12, 78, 34, 89, 23);
        
        System.out.println("Original list: " + numbers);
        
        // Sorting
        Collections.sort(numbers);
        System.out.println("After sorting: " + numbers);
        
        // Reversing
        Collections.reverse(numbers);
        System.out.println("After reversing: " + numbers);
        
        // Shuffling
        Collections.shuffle(numbers);
        System.out.println("After shuffling: " + numbers);
        
        // Binary search (list must be sorted first)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 45);
        System.out.println("Index of 45 using binary search: " + index);
        
        // Min and Max
        System.out.println("Minimum element: " + Collections.min(numbers));
        System.out.println("Maximum element: " + Collections.max(numbers));
        
        // Frequency
        numbers.add(45);
        numbers.add(45);
        System.out.println("Frequency of 45: " + Collections.frequency(numbers, 45));
        System.out.println();
    }
}

// Iterator Demonstration
class IteratorDemo {
    public void demonstrateIterators() {
        System.out.println("=== ITERATOR DEMO ===");
        List<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("JavaScript");
        languages.add("C++");
        
        // Iterator
        System.out.print("Using Iterator: ");
        Iterator<String> iterator = languages.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // ListIterator (bidirectional)
        System.out.print("Using ListIterator (backward): ");
        ListIterator<String> listIterator = languages.listIterator(languages.size());
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println();
        
        // Enhanced for loop
        System.out.print("Using enhanced for loop: ");
        for (String lang : languages) {
            System.out.print(lang + " ");
        }
        System.out.println("\n");
    }
    
    public void demonstrateFailFastIterator() {
        System.out.println("=== FAIL-FAST ITERATOR DEMO ===");
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String element = it.next();
                System.out.println("Processing: " + element);
                if ("B".equals(element)) {
                    // This will cause ConcurrentModificationException
                    list.add("D"); // Modifying during iteration
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException caught: " + e.getMessage());
        }
        System.out.println();
    }
}

// Thread-Safe Collections Demo
class ThreadSafeCollectionsDemo {
    public void demonstrateThreadSafeCollections() {
        System.out.println("=== THREAD-SAFE COLLECTIONS DEMO ===");
        
        // Synchronized wrappers
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
        Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());
        
        syncList.add("Thread-safe list item");
        syncSet.add("Thread-safe set item");
        syncMap.put("key", "Thread-safe map value");
        
        System.out.println("Synchronized List: " + syncList);
        System.out.println("Synchronized Set: " + syncSet);
        System.out.println("Synchronized Map: " + syncMap);
        
        // Concurrent collections
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("concurrent", 100);
        concurrentMap.put("hashmap", 200);
        
        System.out.println("ConcurrentHashMap: " + concurrentMap);
        System.out.println();
    }
}

// Generic Collections Demo
class GenericCollectionsDemo {
    public void demonstrateGenerics() {
        System.out.println("=== GENERICS DEMO ===");
        
        // Type-safe collections
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", 20, 85.0));
        students.add(new Student("Jane", 21, 90.0));
        
        System.out.println("Type-safe Student list:");
        for (Student student : students) {
            System.out.println(student);
        }
        
        // Wildcard usage
        List<? extends Number> numbers = Arrays.asList(1, 2.5, 3, 4.7f);
        System.out.println("Wildcard list of numbers: " + numbers);
        System.out.println();
    }
}

// Main Application Class
class CollectionsFrameworkApp {
    public void runAllDemos() {
        System.out.println("🎓 COMPLETE JAVA COLLECTIONS FRAMEWORK TUTORIAL");
        System.out.println("=" .repeat(60));
        
        // List Interface Demos
        ListDemo listDemo = new ListDemo();
        listDemo.demonstrateArrayList();
        listDemo.demonstrateLinkedList();
        listDemo.demonstrateVector();
        
        // Set Interface Demos
        SetDemo setDemo = new SetDemo();
        setDemo.demonstrateHashSet();
        setDemo.demonstrateLinkedHashSet();
        setDemo.demonstrateTreeSet();
        setDemo.demonstrateStudentSet();
        
        // Map Interface Demos
        MapDemo mapDemo = new MapDemo();
        mapDemo.demonstrateHashMap();
        mapDemo.demonstrateLinkedHashMap();
        mapDemo.demonstrateTreeMap();
        mapDemo.demonstrateHashtable();
        
        // Queue Interface Demos
        QueueDemo queueDemo = new QueueDemo();
        queueDemo.demonstratePriorityQueue();

        // Collections Utility
        CollectionsUtilityDemo utilityDemo = new CollectionsUtilityDemo();
        utilityDemo.demonstrateCollectionsClass();
        
        // Iterator Demos
        IteratorDemo iteratorDemo = new IteratorDemo();
        iteratorDemo.demonstrateIterators();
        iteratorDemo.demonstrateFailFastIterator();
        
        // Thread-Safe Collections
        ThreadSafeCollectionsDemo threadSafeDemo = new ThreadSafeCollectionsDemo();
        threadSafeDemo.demonstrateThreadSafeCollections();
        
        // Generics Demo
        GenericCollectionsDemo genericsDemo = new GenericCollectionsDemo();
        genericsDemo.demonstrateGenerics();
        
        // Summary
        printSummary();
    }
    
    public void printSummary() {
        System.out.println("🎯 KEY CONCEPTS DEMONSTRATED:");
        System.out.println("━" .repeat(50));
        System.out.println("📋 LIST INTERFACE:");
        System.out.println("  • ArrayList - Resizable array, fast random access");
        System.out.println("  • LinkedList - Doubly-linked list, fast insertion/deletion");
        System.out.println("  • Vector - Thread-safe, legacy class");
        
        System.out.println("\n🔗 SET INTERFACE:");
        System.out.println("  • HashSet - No duplicates, no order guarantee");
        System.out.println("  • LinkedHashSet - No duplicates, maintains insertion order");
        System.out.println("  • TreeSet - No duplicates, sorted order");
        
        System.out.println("\n🗂️ MAP INTERFACE:");
        System.out.println("  • HashMap - Key-value pairs, no order guarantee");
        System.out.println("  • LinkedHashMap - Key-value pairs, maintains insertion order");
        System.out.println("  • TreeMap - Key-value pairs, sorted by keys");
        System.out.println("  • Hashtable - Thread-safe, legacy class");
        
        System.out.println("\n⏳ QUEUE INTERFACE:");
        System.out.println("  • PriorityQueue - Priority-based ordering");
        System.out.println("  • ArrayDeque - Double-ended queue");
        
        System.out.println("\n🛠️ UTILITY FEATURES:");
        System.out.println("  • Collections class utility methods");
        System.out.println("  • Iterator and ListIterator");
        System.out.println("  • Comparable and Comparator interfaces");
        System.out.println("  • Thread-safe collections");
        System.out.println("  • Generic type safety");
        System.out.println("  • Fail-fast iterators");
    }
}

// Main Class
public class CompleteCollectionsFramework {
    public static void main(String[] args) {
        CollectionsFrameworkApp app = new CollectionsFrameworkApp();
        app.runAllDemos();
    }
}
