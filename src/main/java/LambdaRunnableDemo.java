public class LambdaRunnableDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        newThread.start();

    }
}

class Task implements Runnable {
    public void run(){}

}

