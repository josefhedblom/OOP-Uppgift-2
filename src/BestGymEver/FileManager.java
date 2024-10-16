package BestGymEver;

import java.io.*;

public class FileManager {
    private String fileNamePath;
    private String fileLogPath;

    public FileManager(String fileNamePath, String fileLogPath) {
        this.fileNamePath = fileNamePath;
        this.fileLogPath = fileLogPath;
    }

    public void readFromFile(){
        try(BufferedReader fileReader = new BufferedReader(new FileReader(this.fileNamePath))){
            String line;
            while((line = fileReader.readLine()) != null){
                System.out.println(line);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeToFile(String text){
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.fileLogPath))){
            fileWriter.write(String.join("\n", text));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
