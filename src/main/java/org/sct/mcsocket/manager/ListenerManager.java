package org.sct.mcsocket.manager;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.sct.mcsocket.MCSocket;
import org.sct.mcsocket.listener.SocketComeLIstener;

public class ListenerManager {
    private static void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, MCSocket.getInstance());
    }

    /**
     * 注册监听器
     */
    public static void register() {
        register(new SocketComeLIstener());
    }

}
