package ftp;

import java.io.IOException;

public interface IFtpClient {

    void connect(String userName, String password) throws IOException;

    String[] getDirectoryContent() throws IOException;

    boolean moveToDirectory(String name) throws IOException;

    boolean moveUpDirectory() throws IOException;

    boolean makeDirectory(String name) throws IOException;

    boolean deleteDirectory(String name) throws IOException;

    boolean isDirectoryExists(String name) throws IOException;

    void disconnect() throws IOException;
}
