package BestGymEver;

import java.io.*;
import java.time.LocalDate;
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
                LocalDate lastPaymentDate = LocalDate.parse(paymentDate);


                Customer customer = new Customer(name, personalNumber, lastPaymentDate);
                customers.add(customer);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return customers;
    }

    public void readCustomerTraningLogFile() {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(this.fileLogPath))){
            String line;
            while((line = fileReader.readLine()) != null){
                System.out.println(line);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void customerWriteToTraningLog(Customer customer) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileLogPath, true))){
            String line = customer.getPersonalNumber() + ", " + customer.getName() + ", " + LocalDate.now();
            writer.write(line);
            writer.newLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
