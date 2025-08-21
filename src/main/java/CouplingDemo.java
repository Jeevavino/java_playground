// ========================================
// TIGHT COUPLING (BAD)
// ========================================

// Concrete email subscriber class
class EmailSubscriber {
    private String email;

    public EmailSubscriber(String email) { this.email = email; }

    public void receiveNews(String news) {
        System.out.println("Email to " + email + ": " + news);
    }

    public void bankAccount() {
        //bank information
    }

}

// Concrete SMS subscriber class
class SMSSubscriber {
    private String phone;

    public SMSSubscriber(String phone) { this.phone = phone; }

    public void receiveNews(String news) {
        System.out.println("SMS to " + phone + ": " + news);
    }
}

class TVSubscriber {
    private String phone;

    public TVSubscriber(String phone) { this.phone = phone; }

    public void receiveNews(String news) {
        System.out.println("SMS to " + phone + ": " + news);
    }
}

// PROBLEM: Newspaper depends on concrete classes
// - Must maintain separate lists and methods for each subscriber type
// - Adding new types requires modifying this class (violates Open/Closed Principle)
class PoorNewspaper {
    private java.util.List<EmailSubscriber> emailSubs = new java.util.ArrayList<>();
    private java.util.List<SMSSubscriber> smsSubs = new java.util.ArrayList<>();

    // Need separate methods for each type
    public void addEmail(EmailSubscriber sub) { emailSubs.add(sub); }
    public void addSMS(SMSSubscriber sub) { smsSubs.add(sub); }

    // Must loop through each type separately - code duplication
    public void publishNews(String news) {
        System.out.println("Publishing: " + news);
        for (EmailSubscriber sub : emailSubs) {
            sub.receiveNews(news);

        }
        for (SMSSubscriber sub : smsSubs) sub.receiveNews(news);
        // Adding PushNotificationSubscriber would require another loop!
    }
}

// ========================================
// LOOSE COUPLING (GOOD)
// ========================================

// Common interface for all observers - key to loose coupling
interface Observer {
    void update(String news);
}

// Email implementation of Observer
class EmailObserver implements Observer {
    private String email;

    public EmailObserver(String email) { this.email = email; }

    @Override
    public void update(String news) {
        System.out.println("Email to " + email + ": " + news);
    }
}

// SMS implementation of Observer
class SMSObserver implements Observer {
    private String phone;

    public SMSObserver(String phone) { this.phone = phone; }

    @Override
    public void update(String news) {
        System.out.println("SMS to " + phone + ": " + news);
    }
}

class TVObserver implements Observer {
    private String phone;

    public TVObserver(String phone) { this.phone = phone; }

    @Override
    public void update(String news) {
        System.out.println("SMS to " + phone + ": " + news);
    }
}

// SOLUTION: Newspaper depends only on Observer interface
// - Single list holds all observer types through polymorphism
// - Adding new types requires NO changes to this class
class LooseNewspaper {
    private java.util.List<Observer> observers = new java.util.ArrayList<>();

    // One method handles all observer types
    public void addObserver(Observer observer) { observers.add(observer); }
    public void removeObserver(Observer observer) { observers.remove(observer); }

    // Single loop works for all observer types via polymorphism
    public void publishNews(String news) {
        System.out.println("Publishing: " + news);
        for (Observer observer : observers) {
            observer.update(news); // Runtime determines actual behavior
        }
    }
}

// ========================================
// DEMO
// ========================================

public class CouplingDemo {
    public static void main(String[] args) {
        System.out.println("=== TIGHT COUPLING ===");
        PoorNewspaper tightNews = new PoorNewspaper();
        tightNews.addEmail(new EmailSubscriber("john@mail.com"));
        tightNews.addSMS(new SMSSubscriber("123-456-7890"));
        tightNews.publishNews("Breaking News!");

        System.out.println("\n=== LOOSE COUPLING ===");
        LooseNewspaper looseNews = new LooseNewspaper();
        looseNews.addObserver(new EmailObserver("jane@mail.com"));
        looseNews.addObserver(new SMSObserver("987-654-3210"));
        looseNews.addObserver(new TVObserver("88343"));
        // Easy to add new types: looseNews.addObserver(new PushNotificationObserver("device123"));
        looseNews.publishNews("Breaking News!");

        System.out.println("\n=== KEY DIFFERENCES ===");
        System.out.println("Tight: Depends on concrete classes, hard to extend");
        System.out.println("Loose: Depends on interface, easy to extend");
    }
}