public enum Color  {

    RED(0.1), BLUE(0.2), YELLOW(0.3);

    private final double value;
    Color(double value){
        this.value = value;
    }
    public double getType () {
        return value;
    }

}

class TestDemoEnum {
    public static void main(String[] ar){
        Color type = Color.BLUE;
       for( Color cType : Color.values()){
           System.out.println(cType.getType());
       }
    }

}

class Demo2 {
    public static void main(String[] args) {
        Color color = Color.BLUE;

        // Switch in client code
        switch (color) {
            case RED:
                System.out.println("Selected RED");
                break;
            case BLUE:
                System.out.println("Selected BLUE");
                break;
            case YELLOW:
                System.out.println("Selected YELLOW");
                break;
            default:
                // Default helps if enum evolves (new constants added)
                System.out.println("Unknown color");
        }


        // Switch-like logic encapsulated in enum method
        System.out.println("Description: " + color.getType());
    }
}
