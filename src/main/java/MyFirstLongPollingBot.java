import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MyFirstLongPollingBot extends TelegramLongPollingBot {
    ;
    private final Girls girls = new Girls();
    private final Map<User, UserState> statesMap = new ConcurrentHashMap<>(16, 0.75F, 1);
    private final Map<User, Date> subsMap = new HashMap<>();
    private final SubscribersHandler handler = new SubscribersHandler(this);
    private boolean workerInProgress = false;

    @Override
    public String getBotUsername() {
        return "LolAndKekBot";
    }

    @Override
    public String getBotToken() {
        return "1556025595:AAFRhvGAe_tqmHxHkhZKSqD2nfv3N2uQWes";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // All implementation here
        if (update.hasMessage() && update.getMessage().hasText()) {

            // get message and its sender
            Message message = update.getMessage();
            User user = message.getFrom();
            // High-level logging!
            System.out.println("[LOG] event: new message\n\t  from: " + user.getUserName() + "\n\t  text: "
                    + message.getText() + "\n\t  time: " + LocalDateTime.now().toString());
            // Serving new message
            processNewMessage(user, message);
            // if worker is not started and there is first subscriber, bot will start processing the subsMap
            if (!workerInProgress && !subsMap.isEmpty()) {
                workerInProgress = true;
                handler.start();
            }
        }
    }

    private void processNewMessage(User user, Message message) {
        if (!statesMap.containsKey(user)) {
            statesMap.put(user, UserState.START);
            String helloText = "Привет! Если хочешь узнать, что я умею делать, напиши мне \"Привет\"";
            sendText(user, helloText);
        } else {
            switch (statesMap.get(user)) {
                case START:
                    if (message.getText().equalsIgnoreCase("Привет")) {
                        statesMap.put(user, UserState.WAIT_FOR_SUBSCRIBE);
                        String text = "Отлично! Если хочешь," +
                                " я буду присылать тебе пикчи с аниме тян каждые 2 часа." +
                                " Для подписки напиши мне \"Хочу подписаться\"";
                        sendText(user, text);
                    } else {
                        String text = "Я тебя не понимаю! Напиши \"Привет\"," +
                                " чтобы узнать мои функции!";
                        sendText(user, text);
                    }
                    break;
                case WAIT_FOR_SUBSCRIBE:
                    if (message.getText().equalsIgnoreCase("Хочу подписаться")) {
                        statesMap.put(user, UserState.SUBSCRIBED);
                        subsMap.put(user, new Date());
                        String text = "Спасибо за подписку и лови первую тянку!";
                        sendText(user, text);
                        String starterImg = girls.getRandomGirlUrl();
                        sendImageFromUrl(starterImg, user);
                        String anotherText = "P.S. Ты всегда можешь отписаться, написав мне \"Отписка\"," +
                                " или запросить новую девочку немедленно, написав \"Хочу девочку\"";
                        sendText(user, anotherText);
                    } else {
                        String text = "Ничего не понял! Напиши \"Хочу подписаться\"," +
                                " если хочешь получать от меня красивых девочек из аниме каждые два часа";
                        sendText(user, text);
                    }
                    break;
                case SUBSCRIBED:
                    if (message.getText().equalsIgnoreCase("Хочу девочку")) {
                        sendText(user, "Лови!");
                        sendImageFromUrl(girls.getRandomGirlUrl(), user);
                    } else if (message.getText().equalsIgnoreCase("Отписка")) {
                        statesMap.put(user, UserState.WAIT_FOR_SUBSCRIBE);
                        subsMap.remove(user);
                        String text = "Подписка отменена! Помни: ты всегда можешь подписаться снова." +
                                " Просто снова напиши мне \"Хочу подписаться\"";
                        sendText(user, text);
                    } else {
                        String text = "Неверная команда! Напиши \"Отписка\"," +
                                " если хочешь отменить подписку или \"Хочу девочку\"," +
                                " чтобы получить новую картинку немедленно";
                        sendText(user, text);
                    }
                    break;
            }
        }
    }

    public void sendText(User recipient, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(recipient.getId()));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendImageFromUrl(String imageUrl, User user) {
        System.out.println("[LOG] event: sending photo\n\t  to: " + user.getUserName() + "\n\t  time: " + LocalDateTime.now().toString());
        SendPhoto imageSender = new SendPhoto();
        imageSender.setChatId(String.valueOf(user.getId()));
        imageSender.setPhoto(new InputFile(imageUrl));
        try {
            execute(imageSender);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public Girls getGirls() {
        return girls;
    }

    public Map<User, Date> getSubsMap() {
        return subsMap;
    }
}
