package com.example.dingran.sockettest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by dingran on 2016/5/26.
 */
public class Client {

    public void start() {

        int SERVER_PORT = 2024;

        try {
            Socket socket = new Socket("localhost", SERVER_PORT);

            // 获取输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 生成输出流
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // 生成输出内容
            String outMsg = "TCP connect to " + SERVER_PORT + System.getProperty("line.separator");

            // 写入
            out.write(outMsg);

            out.flush();

            // 获取输入流
            String inMsg = in.readLine() + System.getProperty("line.separator");

            Log.i("TCP Client", "received : " + inMsg);

            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
