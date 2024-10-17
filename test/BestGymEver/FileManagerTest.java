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

    @Test
    public void splitLinesFromCustomerFile() {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(customerDataShort))) {
            String line;
            while((line = fileReader.readLine()) != null){
                String[] attributes = line.split(", ");
                assertEquals(attributes.length, 2);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
  
}