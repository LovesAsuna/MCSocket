package org.sct.mcsocket.util;

import org.bukkit.command.CommandSender;

import java.io.IOException;

public interface SubCommand {
    /**
     * 指令调用接口
     *
     * @param sender
     * @param args
     * @return
     */
    boolean execute(CommandSender sender, String[] args);

}