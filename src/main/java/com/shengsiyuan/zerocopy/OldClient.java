package com.shengsiyuan.zerocopy;

import java.io.*;
import java.net.Socket;

public class OldClient {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 8899);
        String filename = "/Users/didi/zhanglong_Netty/out2.txt";
        InputStream inputStream = new FileInputStream(filename);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] bytes = new byte[4096];
        long read = 0;
        long total = 0;
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        while((read = dataInputStream.read(bytes)) > 0){
            total += read;
            dataOutputStream.write(bytes);
        }
        System.out.println("发送总数：" + total);
        inputStream.close();
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();

    }
}
