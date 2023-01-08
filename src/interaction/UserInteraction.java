package interaction;


import userFileManager.DataImport;

import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static userData.UserData.*;

public class UserInteraction {
    private static final Scanner scanner = new Scanner(System.in);

    private UserInteraction() {
    }

    public static String getPathToLogsGame() {
        System.out.println("Введите путь до папки с логами игры: ");
        Path path = Paths.get(scanner.nextLine());

        while (!Files.isDirectory(path) || !path.getFileName().toString().equals("chatlogs")) {
            System.out.println("Введен не корректный путь!"
                    + System.lineSeparator()
                    + "Укажите корректный путь к истории чата: ");
            path = Paths.get(scanner.nextLine());
        }
        return path.toString();
    }

    public static Path choiceFile(Map<Byte, Path> files) {
        final byte choice;

        System.out.println("Выберите файл для чтения: ");
        for (Map.Entry<Byte, Path> element : files.entrySet()) {
            System.out.printf("%s - %s%n", element.getKey(), element.getValue().getFileName());
        }
        System.out.println("0 - Выход");
        choice = scanner.nextByte();
        if (choice == 0) {
            return null;
        }
        return files.get(choice);
    }

    public static void getHealAndDamage() {
        String[] fields = new String[]{"|Нанесенный урон| ", "|Полученный урон| ", "|Полученное исцеление|"};
        String length = "_".repeat(fields[0].length() + fields[1].length() + fields[2].length());
        System.out.printf(length + "%n" + fields[0] + fields[1] + fields[2] + "%n" + length + "%n");
        System.out.println(getDealtDamage() + getSpace(getDealtDamage(), fields[0])
                + getRecvdDamage() + getSpace(getRecvdDamage(), fields[1])
                + getHeal() + getSpace(getHeal(), fields[2]));
    }

    public static DataImport getDataFromFile() {
        return new DataImport();
    }

    private static String getSpace(long number, String field) {
        String space = String.valueOf(number);
        return " ".repeat(field.length() - space.length());
    }
}