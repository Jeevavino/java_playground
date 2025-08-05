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