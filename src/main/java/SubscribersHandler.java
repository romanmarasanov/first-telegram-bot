import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Date;

public class SubscribersHandler extends Thread {

    private final MyFirstLongPollingBot bot;
    private static final long TWO_HOURS_IN_MILLIS = 7_200_000;

    public SubscribersHandler(MyFirstLongPollingBot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        while (true) {
            for (User user : bot.getSubsMap().keySet()) {
                if (System.currentTimeMillis() - bot.getSubsMap().get(user).getTime() >= TWO_HOURS_IN_MILLIS) {
                    bot.getSubsMap().put(user, new Date(System.currentTimeMillis()));
                    String randGirlUrl = bot.getGirls().getRandomGirlUrl();
                    bot.sendImageFromUrl(randGirlUrl, user);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
