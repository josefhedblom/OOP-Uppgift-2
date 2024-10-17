package BestGymEver;

public class HuvudProgram {
    private String customerData = "./src/Data/customerData.txt";
    private String customerDataLog = "./src/Data/customerDataLog.txt";

    public HuvudProgram() {
        FileManager fileManager = new FileManager(customerData, customerDataLog);
        CustomerService customerService = new CustomerService(fileManager);
        Customer customer = customerService.findCustomer("7512166544");

        if(customer == null) {
            System.out.println("Customer not found");
        } else {
            System.out.println("Customer found | Name: " + customer.getName() + " | " + "personalNumber: " + customer.getPersonalNumber());
        }

        //customerService.checkMembership(customer);
        customerService.registerTraining(customer);
        fileManager.readCustomerTraningLogFile();
    }

    public static void main(String[] args) {
        new HuvudProgram();
    }
}
