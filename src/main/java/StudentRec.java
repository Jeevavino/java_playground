public record StudentRec(int id, String name) {
}
class TestDemoRecord {
    public static void main(String[] ar){
        StudentRec std = new StudentRec(1, "Bala");
        StudentRec std1 = new StudentRec(1, "Bala");
        System.out.println();
    }

}

