package com.elinext.aparovich.ftp;

import com.elinext.aparovich.ftp.bean.HostPort;
import com.elinext.aparovich.ftp.utils.FtpUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FtpClient implements IFtpClient {
    private final Logger logger = LogManager.getLogger(FtpClient.class.getName());

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public FtpClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
        logger.info(getResponse(reader));
    }

    public void connect(String user, String password) throws IOException {
        logger.info(sendActiveCommandAndGetResponse("USER " + user));
        logger.info(sendActiveCommandAndGetResponse("PASS " + password));
    }

    @Override
    public String[] getDirectoryContent() throws IOException {
        String response = getPassiveResponse("NLST");
        return response.split("\r");
    }

    @Override
    public boolean moveToDirectory(String name) throws IOException {
        String response = sendActiveCommandAndGetResponse("CWD " + name);
        if (FtpUtils.getResponseType(response) == FtpUtils.RESPONSE_TYPE_COMMAND_UNSUPPORTED) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean moveUpDirectory() throws IOException {
        String response = sendActiveCommandAndGetResponse("XCUP");
        return true;
    }

    @Override
    public boolean makeDirectory(String name) throws IOException {
        String response = sendActiveCommandAndGetResponse("MKD " + name);
        if (FtpUtils.getResponseType(response) != FtpUtils.RESPONSE_TYPE_COMMAND_UNSUPPORTED) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDirectory(String name) throws IOException {
        String response = sendActiveCommandAndGetResponse("RMD " + name);
        if (FtpUtils.getResponseType(response) != FtpUtils.RESPONSE_TYPE_COMMAND_UNSUPPORTED) {
            return true;
        }
        return false;    }

    @Override
    public boolean isDirectoryExists(String name) throws IOException {
        boolean isDirectoryMade = makeDirectory(name);
        if (isDirectoryMade) {
            deleteDirectory(name);
            return false;
        }
        return true;
    }

    public void disconnect() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }

    private void sendCommand(String command) {
        writer.write(command + "\r\n");
        writer.flush();
    }

    private String getResponse(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();

        do {
            String line = reader.readLine();
            if (!line.isEmpty() || !line.equals("")) {
                sb.append(line).append("\r");
            }
            if (line == null) {
                throw new IOException("Empty response");
            }
        }
        while (reader.ready());

        return sb.toString();
    }

    private String sendActiveCommandAndGetResponse(String command) throws IOException {
        sendCommand(command);
        return getResponse(reader);
    }

    private String getPassiveResponse(String command) throws IOException {
        HostPort hostPort = getPassiveHostAndPort();

        Socket passiveSocket = new Socket(hostPort.getHost(), hostPort.getPort());
        BufferedReader passiveReader = new BufferedReader(new InputStreamReader(passiveSocket.getInputStream()));

        String activeResponse = sendActiveCommandAndGetResponse(command);
        String passiveResponse = getResponse(passiveReader);

        passiveReader.close();
        passiveSocket.close();

        return passiveResponse;
    }

    private HostPort getPassiveHostAndPort() throws IOException {
        String pasvResponse = sendActiveCommandAndGetResponse("PASV");
        logger.info(pasvResponse);

        if (FtpUtils.getResponseCode(pasvResponse) != 227) {
            throw new IOException("Cannot enter in passive mode");
        }

        Matcher matcher = Pattern.compile("\\((\\d+)+,(\\d+)+,(\\d+)+,(\\d+)+,(\\d+),(\\d+)\\)").matcher(pasvResponse);
        matcher.find();

        String host = matcher.group(1) + "." + matcher.group(2) + "." + matcher.group(3) + "." + matcher.group(4);
        int port = Integer.parseInt(matcher.group(5)) * 256 + Integer.parseInt(matcher.group(6));

        return new HostPort(host, port);
    }
}
