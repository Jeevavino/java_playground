public final class Singelton {

    private static Singelton INSTANCE ;
    private Singelton(){

    }

    public synchronized  static Singelton getInstance() {
        if(INSTANCE == null){ //Lasy initialization.
            INSTANCE = new Singelton();
        }
        return  INSTANCE;
    }

}
class TestDemoSingle {
    public static void main(String[] ar){
        Singelton.getInstance();
    }

}