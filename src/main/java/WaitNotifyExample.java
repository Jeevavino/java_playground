class SharedResource {
    private String data;
    private boolean hasData = false;

    // Consumer method - waits for data
    public synchronized String consume() {
        while (!hasData) {
            try {
                System.out.println("Consumer waiting for data...");
                wait(); // Releases lock and waits
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        String result = data;
        hasData = false;
        System.out.println("Consumer consumed: " + result);
        notify(); // Wake up producer
        return result;
    }

    // Producer method - produces data
    public synchronized void produce(String newData) {
        while (hasData) {
            try {
                System.out.println("Producer waiting to produce...");
                wait(); // Wait until data is consumed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        this.data = newData;
        hasData = true;
        System.out.println("Producer produced: " + newData);
        notify(); // Wake up consumer
    }
}

public class WaitNotifyExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.consume();
                sleep(1000); // Simulate processing time
            }
        }, "Consumer");

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.produce("Data-" + i);
                sleep(1500); // Simulate production time
            }
        }, "Producer");

        consumer.start();
        producer.start();

        try {
            consumer.join();
            producer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All done!");
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
