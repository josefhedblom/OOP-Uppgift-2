package BestGymEver;

public class HuvudProgram {
    private String customerData = "./src/Data/customerData.txt";
    private String customerDataLog = "./src/Data/customerDataLog.txt";

    public HuvudProgram() {
        FileManager fileManager = new FileManager(customerData, customerDataLog);
    }

    public static void main(String[] args) {
        new HuvudProgram();
    }
}
