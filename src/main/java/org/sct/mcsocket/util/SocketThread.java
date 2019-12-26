package org.sct.mcsocket.util;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.sct.mcsocket.MCSocket;
import org.sct.mcsocket.data.SocketData;
import org.sct.mcsocket.enumeration.ConfigType;
import org.sct.mcsocket.file.Config;

import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class SocketThread {
    @Getter private static Socket incoming;

    public SocketThread(Socket incoming) {
        this.incoming = incoming;
    }

    public void run() {
        try (InputStream inputStream= incoming.getInputStream();
             OutputStream outputStream = incoming.getOutputStream()) {
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "GBK"), true);

            boolean done = false;
            while (!done && scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String debase64 = new String(Base64.getDecoder().decode(line.getBytes()));
                if (Config.getBoolean(ConfigType.DEBUG)) {
                    Bukkit.getConsoleSender().sendMessage("§e[debug]§b收到客户端信息: §2" + debase64);
                }
                if (debase64.trim().equals("!close!")) {
                    done = true;
                    if (Config.getBoolean(ConfigType.DEBUG)) {
                        Bukkit.getConsoleSender().sendMessage("§e[debug]§c与客户端断开连接");
                    }
                }

                /*如果包含监视的信息*/
                if (debase64.trim().equals("/spy on")) {
                    SocketData.setSpyMode(true);
                    Bukkit.getConsoleSender().sendMessage("§e[spy]§b监视模式已启用!");
                    writer.println("监视模式已启用!");
                    writer.flush();
                } else if (debase64.trim().equals("/spy off")) {
                    Bukkit.getConsoleSender().sendMessage("§e[spy]§c监视模式已关闭!");
                    SocketData.setSpyMode(false);
                }


                /*如果包含执行命令的信息*/
                if (debase64.trim().contains("/runservercommand")) {

                    /*设置发送命令为true*/
                    SocketData.setExecute(true);

                    /*设置命令*/
                    SocketData.setCommand(CommandPrase.commandPrase(debase64.split(" ")));

                    /*返回成功消息*/
                    writer.println("服务器已接受到命令发送请求!");
                    writer.flush();

                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
