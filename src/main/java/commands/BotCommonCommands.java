package commands;

public class BotCommonCommands {
    @AppBotCommand(name = "/hello", description = "when request hello", showInHelp = true)
    String hello () {
        return "Hello, User";
    }

    @AppBotCommand(name = "/bye", description = "when request bye", showInHelp = true)
    String bye () {
        return "Goodbye, User";
    }
    @AppBotCommand(name = "/help", description = "when request help", showInKeyBoard = true)
    String help () {
        return "Heeeeelp, User";
    }
}
