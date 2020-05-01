package objects;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;
import org.apache.commons.codec.digest.DigestUtils;
import repository.Repository;
import utils.Directory;

public class GitObjectAccessor {
    public static GitObject readObject(Repository repository, String hash) throws Exception {
        String fileData = readFromHashFile(new Directory(repository.getGitdir()), hash);

        String objectData = validateAndGetObjectData(fileData, hash);

        return GitObjectFactory.ofType(fileData.substring(0, fileData.indexOf(' ')), repository, objectData);
    }

    private static String readFromHashFile(Directory directory, String hash) throws Exception {
        String path = directory
                .joinFileWithRepoPath(false, "objects", hash.substring(0, 2), hash.substring(2)).get();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        inputToOutput(new FileInputStream(path), new InflaterOutputStream(outputStream));
        outputStream.close();

        return outputStream.toString();
    }

    private static String validateAndGetObjectData(String fileData, String hash) {
        int spaceCharIndex = fileData.indexOf(' ');
        int nullCharIndex = fileData.indexOf('\0');

        int size = Integer.parseInt(fileData.substring(spaceCharIndex + 1, nullCharIndex));
        String objectData = fileData.substring(nullCharIndex + 1);
        if (size != objectData.length()) {
            throw new RuntimeException(String.format("Malformed object %s: bad length", hash));
        }

        return objectData;
    }

    public static String writeObject(GitObject gitObject, boolean shouldActuallyWrite)
            throws Exception {
        String data = gitObject.serialize();
        String result = gitObject.getType() + " " + data.length() + "\0" + data;
        String hash = DigestUtils.sha1Hex(result);
        Directory directory = new Directory(gitObject.getRepository().getGitdir());
        String path = directory
            .joinFileWithRepoPath(shouldActuallyWrite, "objects", hash.substring(0, 2), hash.substring(2))
            .get();

        inputToOutput(new ByteArrayInputStream(result.getBytes()), new DeflaterOutputStream(new FileOutputStream(path)));

        return hash;
    }

    private static String inputToOutput(InputStream inputStream, OutputStream outputStream) throws Exception {
        int contents;
        while ((contents=inputStream.read())!=-1){
            outputStream.write(contents);
        }
        inputStream.close();
        outputStream.close();

        return outputStream.toString();
    }
}
