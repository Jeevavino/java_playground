public class LambdaRunnableDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        newThread.start();

        Thread lambda = new Thread(() -> System.out.println("hello"));

    }
}

