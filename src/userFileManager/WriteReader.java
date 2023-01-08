package userFileManager;

import java.io.*;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class WriteReader implements WriteRead {
    /**
     * @param path to file for read
     * @return string from file
     */
    @Override
    public String reader(Path path) {
        StringBuilder builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(path.toString(), StandardCharsets.UTF_8)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                builder.append(bufferedReader.readLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

    /**
     * @param data need data to write in file
     * @param path where write data
     */
    public String writer(String data, Path path) {
        try (FileWriter fileWriter = new FileWriter(path.toString(), StandardCharsets.UTF_8, false)) {
            fileWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}