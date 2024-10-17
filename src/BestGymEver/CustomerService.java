package BestGymEver;

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

    public boolean checkMembership(Customer customer, LocalDate date) {
        return customer.getLastPaymentDate().isAfter(date.minusDays(1)) ||
                customer.getLastPaymentDate().isEqual(date.minusDays(1));
    }

    public void registerTraining(Customer customer) {
        fileManager.customerWriteToTraningLog(customer);
    }

    public void readRegisterTrainingLog(Customer customer) {
        fileManager.customerWriteToTraningLog(customer);
    }
}
