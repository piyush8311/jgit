package myowngit;

import org.ini4j.Wini;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * This class represents a repository and all the branches it has.
 */
public class Repository {
    private String worktree;
    private String gitdir;
    private Wini configReader;

    public Repository(String path, boolean force) throws Exception {
        this.worktree = path;
        initializeAndValidateGitDir(path, force);
        initializeAndValidateConfig(path, force);
    }

    private void initializeAndValidateGitDir(String path, boolean force) {
        this.gitdir = Paths.get(path).resolve(".mygit").toString();
        File file = new File(this.gitdir);
        if (!force && !file.isDirectory()) {
            throw new RuntimeException("Not a git repository " + this.gitdir);
        }
    }

    private void initializeAndValidateConfig(String path, boolean force) throws Exception {
        Optional<String> configPath = joinFileWithRepoPath("config", false);

        if (configPath.isPresent() && new File(configPath.get()).exists()) {
            this.configReader = new Wini(new File(configPath.get()));
        } else if (!force) {
            throw new RuntimeException("Config file does not exist in .mygit");
        }

        if (!force) {
            int ver = this.configReader.get("core", "repositoryformatversion", int.class);
            if (ver != 0) {
                throw new RuntimeException("Unsupported repositoryformatversion " + ver);
            }
        }
    }

    private Optional<String> joinFileWithRepoPath(String path, boolean shouldCreateDir) {
        return Optional.of(joinWithRepoPath(path));
    }

    private Optional<String> joinDirWithRepoPath(String path, boolean shouldCreateDir) {
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
            if(file.mkdir()) {
                System.out.println("Created dir successfully " + dirPath);
                return Optional.of(dirPath);
            } else {
                throw new RuntimeException("Could not create dir " + dirPath);
            }
        } else {
            return Optional.empty();
        }
    }

    private String joinWithRepoPath(String path) {
        return Paths.get(gitdir).resolve(path).toString();
    }
}
