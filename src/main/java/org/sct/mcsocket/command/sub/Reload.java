package org.sct.mcsocket.command.sub;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.sct.mcsocket.data.SocketData;
import org.sct.mcsocket.event.SocketComeEvent;
import org.sct.mcsocket.util.SubCommand;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Reload implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (sender.isOp()) {

            try {
                SocketData.getPool().shutdownNow();
                sender.sendMessage("§7[§eMCSocket§7]§c已关闭线程池");
                SocketData.getServerSocket().close();
                sender.sendMessage("§7[§eMCSocket§7]§c已关闭服务端Socket");

                SocketData.setPool(Executors.newFixedThreadPool(5));
                sender.sendMessage("§7[§eMCSocket§7]§2已创建线程池");
                ServerSocket serverSocket = new ServerSocket(1234);
                SocketData.setServerSocket(serverSocket);
                sender.sendMessage("§7[§eMCSocket§7]§2已创建serversocket");
                Bukkit.getPluginManager().callEvent(new SocketComeEvent(SocketData.getServerSocket()));
                sender.sendMessage("§7[§eMCSocket§7]§b插件重载成功");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return true;
    }
}
