package com.example.dingran.sockettest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dingran on 2016/5/26.
 */
public class Server {

    public void run() {
        ServerSocket serverSocket = null;
        int SERVER_PORT = 2024;

        try {
            // 绑定2024端口，端口0到1023是系统预留的，所以必须大于1023才行
            serverSocket = new ServerSocket(SERVER_PORT);

            // 监听连接请求
            Socket socket = serverSocket.accept();
            Log.i(Server.class.getSimpleName(), "server accepted");

            // 写入读Buffer中
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 放入写Buffer中
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // 读取接收信息,转化为字符串
            String incomingMsg = in.readLine() + System.getProperty("line.separator");

            // 生成发送字符串
            String outgoingMsg = "goodbye from port " + SERVER_PORT + System.getProperty("line.separator");

            // 将发送字符串写入上面定义的BufferedWriter中
            out.write(outgoingMsg);

            // 刷新，发送
            out.flush();

            // 关闭
            socket.close();
        } catch (InterruptedIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // TODO 这句话是什么意思呢？
            // 判定是否初始化ServerSocket对象，如果初始化则关闭serverSocket
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
