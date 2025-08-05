public record Student(int id, String name) {
}
class TestDemoRecord {
    public static void main(String[] ar){
        Student std = new Student(1, "Bala");
        Student std1 = new Student(1, "Bala");
        System.out.println();
    }

}

