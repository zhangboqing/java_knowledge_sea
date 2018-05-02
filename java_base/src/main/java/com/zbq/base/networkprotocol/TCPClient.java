package com.zbq.base.networkprotocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @author zhangboqing
 * @date 2018/4/22
 */
public class TCPClient {

    public static void main(String[] args) throws IOException {

        // TODO Auto-generated method stub

        Socket client = new Socket("127.0.0.1", 20006);
        client.setSoTimeout(10000);
        // 获取键盘输入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); // 获取Socket的输出流，用来发送数据到服务端
        PrintStream out = new PrintStream(client.getOutputStream());
        // 获取Socket的输入流，用来接收从服务端发送过来的数据
        BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean flag = true;
        while (flag) {
            System.out.print("输入信息:");
            String str = input.readLine(); // 发送数据到服务端 out.println(str);
            if ("bye".equals(str)) {
                flag = false;
            } else {
                try {
                    String echo = buf.readLine();
                    System.out.println(echo);
                } catch (SocketTimeoutException e) {
                    System.out.println("Time out, No response");
                }
            }
            System.out.println("Time out, No response");

        }
    }
}