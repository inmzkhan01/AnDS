package com.mozaid.java.concurrency.cancellation;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReaderThread extends Thread {

    private Socket socket;
    private InputStream in;

    public ReaderThread(Socket socket, InputStream in) {
        this.socket = socket;
        this.in = in;
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {
            // e.printStackTrace();
        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[10];
            while (true) {
                in.read(buf);
            }
        } catch (IOException e) {
            // Allow thread to exit.
        }

    }

}
