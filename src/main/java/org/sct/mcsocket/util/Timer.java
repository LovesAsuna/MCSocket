package org.sct.mcsocket.util;

import org.bukkit.Bukkit;
import org.sct.mcsocket.MCSocket;
import org.sct.mcsocket.data.SocketData;

public class Timer {
    public static void timer() {
        /*循环检测是否执行命令*/
        Bukkit.getScheduler().runTaskTimer(MCSocket.getInstance(), () -> {

            if (SocketData.getExecute()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), SocketData.getCommand());
                SocketData.setExecute(false);
            }

        }, 0L, 5L);


    }
}
