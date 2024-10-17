package BestGymEver;

import java.time.LocalDate;

public class Customer {
    private String name;
    private String personalNumber;
    private LocalDate lastPaymentDate;

    public Customer(String name, String personalNumber, LocalDate lastPaymentDate) {
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

    public LocalDate getLastPaymentDate() {
        return this.lastPaymentDate;
    }
}
