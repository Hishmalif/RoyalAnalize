package userFileManager;

import java.util.*;

import static userData.UserData.*;

public class DataImport {
    final String file;
    Map<String, String> colors;

    public DataImport() {
        file = FilesReader.getFile();
        colors = parserColors(FilesReader.getGameConfig());
        parserLogs();
    }

    /**
     * Return map from config
     */
    private Map<String, String> parserColors(List<String> colors) {
        Map<String, String> type = new HashMap<>();
        for (String s : colors) {
            String[] colorType = s.split(" = ");
            long hex = Long.parseLong(colorType[1]);
            type.put(colorType[0], "#" + Long.toHexString(hex).toUpperCase().substring(2));
        }
        return type;
    }

    private void parserLogs() {
        String[] lines = file.split(System.lineSeparator());
        long heal = 0;
        long recvdDamage = 0;
        long dealtDamage = 0;
        for (String s : lines) {
            String[] value = substringRecursions(new StringBuilder(s)).toString().split(" ");
            if (s.contains(colors.get("HEAL")) || (s.contains("восстановили") && s.contains("здоровья"))) {
                heal += Long.parseLong(value[2]); // Исцеление
            } else if ((s.contains(colors.get("RECVD_DAMAGE")) && s.contains("наносит")) || s.contains("наносит")) {
                recvdDamage += Long.parseLong(value[value.length - 2]); // Полученный урон
            } else if (s.contains(colors.get("DEALT_DAMAGE")) || s.contains("нанесли")) {
                dealtDamage += Long.parseLong(value[2]); // Нанесенный урон
            }
        }
        setHeal(heal);
        setRecvdDamage(recvdDamage);
        setDealtDamage(dealtDamage);
    }

    public StringBuilder substringRecursions(StringBuilder s) {
        while (s.indexOf("<") != -1 && s.indexOf(">") != -1) {
            int first = s.indexOf("<");
            int last = s.indexOf(">") + 1;
            s = substringRecursions(s.replace(first, last, ""));
        }
        return s;
    }
}