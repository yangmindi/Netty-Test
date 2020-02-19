package com.shengsiyuan.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NioTest10 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        FileLock lock = channel.lock(3, 6, true);

        System.out.println(lock.isValid());
        System.out.println(lock.isShared());

        lock.release();

        randomAccessFile.close();
    }
}
