package BestGymEver;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class FileManagerTest {
    private String customerData = "./test/TestData/testData.txt";
    private String customerDataLog = "./test/TestData/testDataLog.txt";
    private String customerDataShort = "./test/TestData/testDataShort.txt";


    @Test
    public void countLinesFromCustomerFile() {
        int lineCount = 0;
        try(BufferedReader fileReader = new BufferedReader(new FileReader(customerData))) {
            while(fileReader.readLine() != null) {
                lineCount++;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        assertEquals(lineCount, 6);
    }

    @Test //FIXA
    public void splitLinesFromCustomerFile() {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(customerDataShort))) {
            String line;
            while((line = fileReader.readLine()) != null){
                String[] attributes = line.split(", ");
                //assertEquals(attributes.length, 1);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void createCustomerAttributesFromCustomerFile() {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(customerData))){
            String line;
            while((line = fileReader.readLine()) != null){

                String[] attributes = line.split(", ");
                String personalNumber = attributes[0];
                String name = attributes[1];

                String paymentDate = fileReader.readLine();
                LocalDate lastPaymentDate = LocalDate.parse(paymentDate);

                assertEquals(personalNumber, attributes[0]);
                assertEquals(name, attributes[1]);
                assertEquals(lastPaymentDate, LocalDate.parse(paymentDate));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void createCustomerFromCustomerFileLog() {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(customerDataShort))){
            String line;
            while((line = fileReader.readLine()) != null){

                String[] attributes = line.split(", ");
                String personalNumber = attributes[0];
                String name = attributes[1];

                String paymentDate = fileReader.readLine();
                LocalDate lastPaymentDate = LocalDate.parse(paymentDate);


                Customer customer = new Customer(name, personalNumber, lastPaymentDate);

                assertEquals(customer.getPersonalNumber(), attributes[0]);
                assertEquals(customer.getLastPaymentDate(), LocalDate.parse(paymentDate));
                assertEquals(customer.getName(), "Alhambra Aromes");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
  
}