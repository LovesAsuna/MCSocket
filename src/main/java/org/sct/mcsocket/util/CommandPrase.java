package org.sct.mcsocket.util;

public class CommandPrase {

    public static String commandPrase(String[] commands) {
        /* /runservercommand say hello world*/
        int length = commands.length;

        String command = "";
        for (int i = 1; i < length; i++) {
            command = command + commands[i] + " ";
        }
        return command;
    }
}
