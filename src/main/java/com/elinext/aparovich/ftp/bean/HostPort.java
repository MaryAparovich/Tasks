package com.elinext.aparovich.ftp.bean;

public class HostPort {

    final private String host;
    final private int port;

    public HostPort(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
