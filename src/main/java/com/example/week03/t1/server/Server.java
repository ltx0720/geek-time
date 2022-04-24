package com.example.week03.t1.server;

import com.example.week07.Order;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        new Server().listen();
        new Order();

    }


    private String listen(

    ) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8001);
        while (true) {
            try {
                Socket accept = serverSocket.accept();
                server(accept);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    private static void server(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        byte[] res = new byte[1024];
        inputStream.read(res);
        String data = new String(res, "utf-8");
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type:text/html;charset=utf-8");
        writer.println("Content-Length:" + data.getBytes().length);
        writer.println();
        writer.write(data);
        writer.flush();
        socket.close();
    }

}
