package BestGymEver;

import java.time.LocalDate;

public class HuvudProgram {
    private String customerData = "./src/Data/customerData.txt";
    private String customerDataLog = "./src/Data/customerDataLog.txt";

    public HuvudProgram() {
        FileManager fileManager = new FileManager(customerData, customerDataLog);
        CustomerService customerService = new CustomerService(fileManager);
        Customer customer = customerService.findCustomer("Alhambra Aromes");

        if(customer == null) {
            System.out.println("Customer not found");
        } else {
            System.out.println("Customer found | Name: " + customer.getName() + " | " + "personalNumber: " + customer.getPersonalNumber());
        }

        String membership = customerService.checkMembership(customer, LocalDate.now());
        System.out.println(membership);
        customerService.registerTraining(customer); // klockslag?
        fileManager.customerReadFromTraningLog();
    }

    public static void main(String[] args) {
        new HuvudProgram();
    }
}
