package org.sct.mcsocket.command.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.mcsocket.data.SocketData;
import org.sct.mcsocket.util.CommandPrase;
import org.sct.mcsocket.util.SocketThread;
import org.sct.mcsocket.util.SubCommand;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Chat implements SubCommand {
    private PrintWriter writer;
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length >= 1 && "qqchat".equalsIgnoreCase(args[0])) {
            if (SocketData.getSpyMode()) {
                OutputStream outputStream = null;
                try {
                    outputStream = SocketThread.getIncoming().getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    SocketData.setChatString("[" + player.getName() + "]" + CommandPrase.commandPrase(args));
                } else {
                    SocketData.setChatString("[console]" + CommandPrase.commandPrase(args));
                }
                writer = new PrintWriter(outputStream, true);
                writer.println(SocketData.getChatString());
                writer.flush();

            }

        }
        return true;
    }
}
