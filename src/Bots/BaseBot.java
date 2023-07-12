package Bots;

public interface BaseBot {
    void deactiveBot(String botname);
    void activeBot(String botname);
    boolean isBotActive(String botname);
}
