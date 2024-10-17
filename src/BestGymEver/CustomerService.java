package BestGymEver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class CustomerService {
    FileManager fileManager;
    public CustomerService(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public Customer findCustomer(String customerIdentifier) {
        for(Customer customer : fileManager.readCustomerFile()){
            if(customer.getName().equalsIgnoreCase(customerIdentifier) || customer.getPersonalNumber().equals(customerIdentifier)){
                return customer;
            }
        }
        return null;
    }

    public void checkMembership(Customer customer) {// Assign date to check
        LocalDate today = LocalDate.now();

        if (customer.getLastPaymentDate().isBefore(today.minusYears(1))) {
            System.out.println("Paydate is older than 1 year");
        } else {
            System.out.println("Paydate is less than 1 year");
        }
    }

    public void registerTraining(Customer customer) {
        fileManager.customerTraningLog();
    }
}
