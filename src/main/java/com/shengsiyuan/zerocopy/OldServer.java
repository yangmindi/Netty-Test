package com.shengsiyuan.zerocopy;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OldServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte[] bytes = new byte[4096];
            while (true) {
                int read = dataInputStream.read(bytes, 0, bytes.length);
                if (read == -1) {
                    break;
                }
            }
        }
    }
}
