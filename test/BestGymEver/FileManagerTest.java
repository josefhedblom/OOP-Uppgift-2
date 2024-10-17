package BestGymEver;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class FileManagerTest {
    private String customerData = "./test/TestData/testData.txt";
    private String customerDataLog = "./test/TestData/testDataLog.txt";
    private String customerDataShort = "./test/TestData/testDataShort.txt";
    private String customerDataEmpty = "./test/TestData/testDataEmpty.txt";
    private String customerDataWrong = "./test/TestData/testDataWrong.txt";


    @Test
    public void readFromCustomerFile(){
        FileManager fileManager = new FileManager(customerData, customerDataLog);
        List<Customer> customers = fileManager.readCustomerFile();
        assertEquals(customers.size(), 3);
        assertEquals(customers.get(0).getName(),"Alhambra Aromes");
        assertEquals(customers.get(0).getPersonalNumber(),"7703021234");
        assertEquals(customers.get(0).getLastPaymentDate(),LocalDate.parse("2024-07-01"));
    }

    @Test
    public void readFromEmptyCustomerFile(){
        FileManager fileManager = new FileManager(customerDataEmpty, customerDataLog);
        List<Customer> customers = fileManager.readCustomerFile();
        assertTrue(customers.isEmpty());
    }

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

    @Test
    public void splitLinesFromCustomerFile() {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(customerDataShort))) {
            String line;
            while((line = fileReader.readLine()) != null){
                String[] attributes = line.split(",");
                assertEquals(2, attributes.length);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void createCustomerAttributesFromCustomerFile() {
        FileManager fileManager = new FileManager(customerData, customerDataLog);
        List<Customer> customers = fileManager.readCustomerFile();

        assertEquals("7703021234", customers.get(0).getPersonalNumber());
        assertEquals("Alhambra Aromes", customers.get(0).getName());
        assertEquals(LocalDate.parse("2024-07-01"), customers.get(0).getLastPaymentDate());
    }


    @Test
    public void handleInvalidDateFormat() {
        FileManager fileManager = new FileManager(customerDataWrong, customerDataLog);
        assertThrows(DateTimeParseException.class, fileManager::readCustomerFile);
    }


}