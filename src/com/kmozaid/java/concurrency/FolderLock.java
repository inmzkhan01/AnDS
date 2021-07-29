package com.kmozaid.java.concurrency;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

public class FolderLock {

    public static void main(String[] args) throws Exception {
        FileChannel channel = FileChannel.open(Paths.get("/Users/mohemmadzaid_khan/nonwork/coding/problem-solving-practice/"), StandardOpenOption.WRITE);
        FileLock lock = null;
        try {
            lock = channel.tryLock();
        } catch (final OverlappingFileLockException e) {
            channel.close();
        }
        RandomAccessFile src = new RandomAccessFile("/Users/mohemmadzaid_khan/nonwork/coding/problem-solving-practice/src/text.java", "rw");
        src.writeChars("zaid");
        TimeUnit.HOURS.sleep(1);
        lock.release();

        channel.close();
    }
}
