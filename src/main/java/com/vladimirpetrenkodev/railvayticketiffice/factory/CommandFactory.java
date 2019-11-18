package com.vladimirpetrenkodev.railvayticketiffice.factory;

import com.vladimirpetrenkodev.railvayticketiffice.web.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> getCommandMap = new HashMap<>();
    private static Map<String, Command> postCommandMap = new HashMap<>();
    private static Command defaultCommand = new NotFoundCommand();

    static {
        getCommandMap.put("/", new LandingCommand());
        getCommandMap.put("/404", defaultCommand);
        getCommandMap.put("/403", new ForniddenCommand());
        getCommandMap.put("/profile", new ProfileCommand());
        getCommandMap.put("/tickets", new TicketsCommand());
        getCommandMap.put("/admin", new AdminCommand());
        getCommandMap.put("/language", new LanguageCommand());

        postCommandMap.put("/", new LandingCommand());
    }

    private CommandFactory() {
    }

    public static Command getCommand(String path, String type) {
        return "GET".equals(type) 
                ? getCommand(path) 
                : postCommand(path);
    }

    private static Command getCommand(String path) {
        return getCommandMap.getOrDefault(path, defaultCommand);
    }

    private static Command postCommand(String path) {
        return postCommandMap.getOrDefault(path, defaultCommand);
    }
}
