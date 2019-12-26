package org.sct.mcsocket.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.sct.mcsocket.data.SocketData;
import org.sct.mcsocket.util.SocketThread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class AsyncPlayerChatListener implements Listener {
    private PrintWriter writer;
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) throws IOException {
        if (SocketData.getSpyMode()) {
            OutputStream outputStream = SocketThread.getIncoming().getOutputStream();
            SocketData.setChatString("[" + e.getPlayer().getName() + "]" + e.getMessage());
            writer = new PrintWriter(outputStream, true);
            writer.println(SocketData.getChatString());
            writer.flush();
        }
    }
}
