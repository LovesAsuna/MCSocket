package org.sct.mcsocket.data;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketData {

    static {
        pool = Executors.newFixedThreadPool(5);
        playerList = new ArrayList<>();

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
    @Getter @Setter private static ServerSocket serverSocket;

    /*线程池*/
    @Getter @Setter private static ExecutorService pool;

    /*监视模式*/
    @Getter @Setter private static Boolean spyMode = false;

    /*聊天内容*/
    @Getter @Setter private static String chatString = null;

    /*玩家列表*/
    @Getter private static List<Player> playerList;
}
