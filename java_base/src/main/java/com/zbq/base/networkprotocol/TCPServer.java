package com.zbq.base.networkprotocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class TCPServer {


    public static void main(String[] args) throws Exception {
        //服务端socket
        ServerSocket server = new ServerSocket(20006);
        Socket client = null;

        boolean flag = true;
        while (flag) {
            client = server.accept();
            System.out.println("与客户端连接成功!");

            new Thread(new MyServerThread(client)).start();

        }

    }

}

class MyServerThread implements Runnable {

    //客户端socket
    private Socket socket = null;

    public MyServerThread(Socket socket) {

        this.socket = socket;

    }

    public void run() {

        try {

            PrintStream out = new PrintStream(socket.getOutputStream());

            BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            boolean flag = true;

            while (flag) {

                String str = buf.readLine();

                if (str == null || "".equals(str)) {

                    flag = false;

                } else {

                    if ("bye".equals(str)) {

                        flag = false;

                    } else if (str.startsWith("http")) {

                        URL url = new URL(str);

                        URLConnection yc = url.openConnection();

                        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                        String inputLine;

                        StringBuffer sb = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {

                            sb.append(inputLine);

                        }

                        System.out.println(sb.toString());

                        out.println("echo:" + sb.toString());

                        in.close();

                    } else {

                        out.println("Invalid URL:" + str);

                    }

                }

            }

            out.close();

            socket.close();

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }


}