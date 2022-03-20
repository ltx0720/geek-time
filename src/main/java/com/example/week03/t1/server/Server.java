package com.example.week03.t1.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author ltx
 * @date 2022/3/20 3:48 下午
 */
public class Server {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("http://localhost", 8081));

        while (true) {
            Socket socket = serverSocket.accept();
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
            char[] buff = new char[1024];
            inputStreamReader.read(buff);
            String res = new String(buff);
        }

    }

}
