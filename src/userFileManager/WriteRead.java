package userFileManager;

import java.nio.file.Path;

public interface WriteRead {
    String reader(Path path);

    String writer(String data, Path path);
}