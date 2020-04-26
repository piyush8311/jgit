package utils;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Directory {
    private final String directoryPath;

    public Directory(String path) {
        this.directoryPath = path;
        assert(new File(path).isDirectory());
    }

    public Optional<String> joinFileWithRepoPath(boolean shouldCreateDir, String... path) {
        List<String> p = new ArrayList<>(Arrays.asList(path));
        p.remove(p.size() - 1);
        if (joinDirWithRepoPath(shouldCreateDir, p.toArray(new String[0])).isPresent())
            return Optional.of(joinWithRepoPath(path));
        else
            return Optional.empty();
    }

    public Optional<String> joinDirWithRepoPath(boolean shouldCreateDir, String... path) {
        String dirPath = joinWithRepoPath(path);
        File file = new File(dirPath);
        if(file.exists()) {
            if (file.isDirectory()) {
                return Optional.of(dirPath);
            } else {
                throw new RuntimeException("Not a directory " + dirPath);
            }
        }

        if (shouldCreateDir) {
            if(file.mkdirs()) {
                System.out.println("Created dir successfully " + dirPath);
                return Optional.of(dirPath);
            } else {
                throw new RuntimeException("Could not create dir " + dirPath);
            }
        } else {
            return Optional.empty();
        }
    }

    String joinWithRepoPath(String... path) {
        return Paths.get(this.directoryPath, path).toString();
    }
}
