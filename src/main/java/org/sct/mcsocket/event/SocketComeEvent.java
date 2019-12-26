package org.sct.mcsocket.event;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import java.net.ServerSocket;


/**
 * @author icestar
 */
public class SocketComeEvent extends Event {
    @Getter private static final HandlerList handlers = new HandlerList();

    @Getter ServerSocket serverSocket;

    /**
     * 构造 hanshu
     * @param serverSocket 服务端Socket
     */
    public SocketComeEvent(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

