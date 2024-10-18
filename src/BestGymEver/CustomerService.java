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

    public String checkMembership(Customer customer, LocalDate date) {
        if(customer.getLastPaymentDate().isAfter(date.minusYears(1)) || customer.getLastPaymentDate().isEqual(date.minusYears(1))) {
            return MembershipStatus.CURRENT_MEMBER.getStatus();
        } else if (customer.getLastPaymentDate().isBefore(date.minusYears(1))) {
            return MembershipStatus.FORMER_MEMBER.getStatus();
        } else {
            return MembershipStatus.NON_MEMBER.getStatus();
        }
    }

    public void registerTraining(Customer customer) {
        fileManager.customerWriteToTraningLog(customer);
    }

    public void readTrainingLog() {
        fileManager.customerReadFromTraningLog();
    }
}
