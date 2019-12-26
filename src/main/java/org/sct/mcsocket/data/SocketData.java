package org.sct.mcsocket.data;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketData {

    static {
        pool = Executors.newFixedThreadPool(5);


        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*是否发送命令*/
    @Getter @Setter private static Boolean execute = false;

    /*命令内容*/
    @Getter @Setter private static String command = null;

    /*ServerSocket*/
    @Getter private static ServerSocket serverSocket;

    /*线程池*/
    @Getter private static ExecutorService pool;

    /*监视模式*/
    @Getter @Setter private static Boolean spyMode = false;

    /*聊天内容*/
    @Getter @Setter private static String chatString = null;
}
