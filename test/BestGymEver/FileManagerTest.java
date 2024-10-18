package BestGymEver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class FileManagerTest {
    private String customerData = "./test/TestData/testData.txt";
    private String customerDataLog = "./test/TestData/testDataLog.txt";
    private String customerDataShort = "./test/TestData/testDataShort.txt";
    private String customerDataEmpty = "./test/TestData/testDataEmpty.txt";
    private String customerDataWrong = "./test/TestData/testDataWrong.txt";

    private FileManager fileManager;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("training_log_test", ".txt");
        fileManager = new FileManager(customerData, tempFile.toString());
    }


    @Test
    public void readFromCustomerFile(){
        List<Customer> customers = fileManager.readCustomerFile();
        assertEquals(customers.size(), 14);
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
        assertEquals(lineCount, 28);
    }

    @Test
    public void splitLinesFromCustomerFile() {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(customerDataShort))) {
            String line;
            while((line = fileReader.readLine()) != null){
                if(line.contains(",")){
                    String[] attributes = line.split(",");
                    assertEquals(2, attributes.length);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void createCustomerAttributesFromCustomerFile() {
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

    @Test
    public void testCustomerWriteToTraningLog() throws IOException {
        Customer customer = new Customer("Alhambra Aromes", "7703021234", LocalDate.parse("2024-07-01"));

        LocalDate nowDate = LocalDate.now();
        String expectedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String expectedLog = "7703021234, Alhambra Aromes, " + nowDate + ", " + expectedTime;

        fileManager.customerWriteToTraningLog(customer);
        List<String> lines = Files.readAllLines(tempFile);

        assertEquals(1, lines.size());
        assertEquals(expectedLog, lines.get(0));
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

}