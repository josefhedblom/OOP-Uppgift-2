package BestGymEver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String fileNamePath;
    private String fileLogPath;

    public FileManager(String fileNamePath, String fileLogPath) {
        this.fileNamePath = fileNamePath;
        this.fileLogPath = fileLogPath;
    }

    public List<Customer> readCustomerFile() {
        List<Customer> customers = new ArrayList<>();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(this.fileNamePath))){
            String line;
            while((line = fileReader.readLine()) != null){

                String[] attributes = line.split(", ");
                String personalNumber = attributes[0];
                String name = attributes[1];

                String paymentDate = fileReader.readLine();
                String lastPaymentDate = paymentDate;


                Customer customer = new Customer(name, personalNumber, lastPaymentDate);
                customers.add(customer);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return customers;
    }

    public void saveToDataBase(List<Customer> customers) {
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.fileLogPath))){
            for(Customer customer : customers){
                fileWriter.write(String.join("\n", customer.toString()));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
