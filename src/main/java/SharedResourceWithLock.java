import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class SharedResourceWithLock {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition dataAvailable = lock.newCondition();
    private final Condition spaceAvailable = lock.newCondition();

    private String data;
    private boolean hasData = false;

    public String consume() {
        lock.lock();
        try {
            while (!hasData) {
                dataAvailable.await(); // Wait for data
            }
            String result = data;
            hasData = false;
            spaceAvailable.signal(); // Signal producer
            return result;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock(); // Must unlock in finally
        }
    }

    public void produce(String newData) {
        lock.lock();
        try {
            while (hasData) {
                spaceAvailable.await(); // Wait for space
            }
            this.data = newData;
            hasData = true;
            dataAvailable.signal(); // Signal consumer
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock(); // Must unlock in finally
        }
    }
}
