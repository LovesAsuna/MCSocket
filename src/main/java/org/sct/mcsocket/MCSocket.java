package org.sct.mcsocket;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.mcsocket.command.CommandHandler;
import org.sct.mcsocket.data.SocketData;
import org.sct.mcsocket.event.SocketComeEvent;
import org.sct.mcsocket.listener.SocketComeLIstener;
import org.sct.mcsocket.manager.ListenerManager;
import org.sct.mcsocket.util.Timer;


public final class MCSocket extends JavaPlugin {
    private static JavaPlugin Instance;

    @Override
    public void onEnable() {
        Instance = this;

        /*保存配置*/
        saveDefaultConfig();

        /*启用timer*/
        Timer.timer();

        /*注册监听器*/
        ListenerManager.register();

        /*注册命令*/
        Bukkit.getPluginCommand("mcsocket").setExecutor(new CommandHandler());

        Bukkit.getPluginManager().callEvent(new SocketComeEvent(SocketData.getServerSocket()));
        getServer().getConsoleSender().sendMessage("§7[§eMCSocket§7]§2插件已加载");
    }

    @Override
    public void onDisable() {
        /*关闭Socket*/
        try {
            SocketData.getServerSocket().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*关闭线程池*/
        SocketData.getPool().shutdownNow();
        getServer().getConsoleSender().sendMessage("§7[§eMCSocket§7]§c插件已卸载");
    }

    public static JavaPlugin getInstance() {
        return Instance;
    }
}
