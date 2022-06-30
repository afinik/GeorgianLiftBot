package com.skz;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.skz.Constants.ERROR_MESSAGE;

public class TelegramBot extends AbilityBot {
    MessageFactory messageFactory;

    public TelegramBot() {
        this(Constants.BOT_TOKEN, Constants.BOT_USERNAME);
    }

    private TelegramBot(String botToken, String botUsername) {
        super(botToken, botUsername);
        messageFactory = new MessageFactory(sender);
    }

    @Override
    public long creatorId() {
        return Constants.CREATOR_ID;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public void onUpdateReceived(Update update) {
        super.onUpdateReceived(update);
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String messageText = message.getText();
                long telegramId = message.getChatId();
                if (messageText.equals("/start")) {
                    new MessageFactory(sender).sendAnswerToGuest(telegramId, messageForSent(1));
                    System.out.println();
                } else if (messageText.equals("/gen")) {
                    new MessageFactory(sender).sendAnswerToGuest(telegramId, messageForSent(2) + Generator.getCode());
                } else {
                    System.out.println("Некто с id " + telegramId + " прислал сообщение с ошибкой: ");
                    messageFactory.sendAnswerToGuest(telegramId, messageForSent(400));
                }
            }

        }
    }

    @Override
    public void onClosing() {
        super.onClosing();
    }

    @Override
    public String getBotUsername() {
        return Constants.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return Constants.BOT_TOKEN;
    }

    protected void sendMyMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId + "", text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected String messageForSent(int numberMessage) {
        String messToSend;
        switch (numberMessage) {
            case 1:
                messToSend = "Привет! Бот для закваски простокваши. Нажми /gen для закваски";
                break;
            case 2:
                messToSend = "Твой код: ";
                break;
            default:
                messToSend = ERROR_MESSAGE + " TB1";
                break;
        }
        messToSend = new String(messToSend.getBytes(), StandardCharsets.UTF_8);
        return messToSend;
    }
}

