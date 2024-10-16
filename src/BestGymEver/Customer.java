package BestGymEver;

public class Customer {
    private String name;
    private String personalNumber;
    private String lastPaymentDate;

    public Customer(String name, String personalNumber, String lastPaymentDate) {
        this.name = name;
        this.personalNumber = personalNumber;
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getName() {
        return this.name;
    }

    public String getPersonalNumber() {
        return this.personalNumber;
    }

    public String getLastPaymentDate() {
        return this.lastPaymentDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", lastPaymentDate='" + lastPaymentDate + '\'' +
                '}' + "\n";
    }
}
