package com.railvayticketiffice.factory;

import com.railvayticketiffice.web.command.*;
import com.railvayticketiffice.web.command.pages.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> getCommandMap = new HashMap<>();
    private static Map<String, Command> postCommandMap = new HashMap<>();
    private static Command defaultCommand = new NotFoundPageCommand();

    static {
        getCommandMap.put("/", new LandingPageCommand());
        getCommandMap.put("/404", defaultCommand);
        getCommandMap.put("/403", new ForbiddenPageCommand());
        getCommandMap.put("/profile", new ProfilePageCommand());
        getCommandMap.put("/tickets", new TicketsPageCommand());
        getCommandMap.put("/admin-flights", new AdminFlightsPageCommand());
        getCommandMap.put("/admin-stations", new AdminStationsPageComand());
        getCommandMap.put("/admin-wagons", new AdminWagonsPageCommand());
        getCommandMap.put("/admin-trains", new AdminTrainCommad());

        getCommandMap.put("/logout", new LogoutCommand());
        getCommandMap.put("/language", new LanguageCommand());
        getCommandMap.put("/seats", new SeatsCommand());
        getCommandMap.put("/flights", new FlightsCommand());


        postCommandMap.put("/", new LoginCommand());
        postCommandMap.put("/login", new LoginCommand());
        postCommandMap.put("/registration", new RegistrationCommand());
        postCommandMap.put("/flights", new FlightsCommand());
        postCommandMap.put("/order", new OrderCommand());
        postCommandMap.put("/stations", new StationCommand());
        postCommandMap.put("/wagon_type", new WagonTypeCommand());
        postCommandMap.put("/wagons", new WagonCommand());
        postCommandMap.put("/trains", new TrainCommand());


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
