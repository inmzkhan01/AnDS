package com.kmozaid.java.concurrency;

import java.io.File;
import java.io.IOException;

public class FolderSynchronozation {

    private File file;

    public FolderSynchronozation(File file) {
        this.file = file;
    }

    private class Job implements Runnable {

        private int suffix = 0;

        @Override
        public void run() {
            while (suffix < 100) {
                //System.out.println(Thread.currentThread().getName() + " is starting process");
                synchronized (file) {
                    try {
                        //Thread.sleep(500);
                        File.createTempFile("File", String.valueOf(suffix++), file);
                        System.out.println("File Created By: " + Thread.currentThread().getName());
                        //Thread.sleep(500);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        FolderSynchronozation folderSynchronozation = new FolderSynchronozation(new File("/Users/mohemmadzaid_khan/tmp/temp"));
        Thread t1 = new Thread(folderSynchronozation.new Job(), "thread1");
        Thread t2 = new Thread(folderSynchronozation.new Job(), "thread2");
        t1.start();
        t2.start();
    }

}
