package com.mozaid.java.concurrency.cancellation;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class SocketTask<T> implements CancellableTask<T> {

    private final Socket socket;

    public SocketTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public T call() throws Exception {
        return null;
    }

    @Override
    public synchronized void cancel() {
        try {
            if (socket != null)
                socket.close();
        } catch (IOException e) {
        }
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}
