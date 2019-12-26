package org.sct.mcsocket.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.sct.mcsocket.data.SocketData;
import org.sct.mcsocket.event.SocketComeEvent;
import org.sct.mcsocket.util.SocketThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketComeLIstener implements Listener {
    @EventHandler
    public void onSocketCome(SocketComeEvent e) {
        ServerSocket serverSocketg = e.getServerSocket();

        /*将等待客户端响应放入线程池，以免影响bukkit主线程*/
        SocketData.getPool().submit(() -> {
            try {
                while (true) {
                    Socket incoming = serverSocketg.accept();
                    SocketData.getPool().submit(() -> {
                        SocketThread socketThread = new SocketThread(incoming);
                        socketThread.run();
                    });
                }


            } catch (IOException ex) {
                /*不做处理*/
            }
        });

    }
}
