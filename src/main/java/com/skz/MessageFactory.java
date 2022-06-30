package com.skz;

import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageFactory {
    private final MessageSender sender; //используется для отправки сообщений обратно пользователю
    SendMessage message;
    SendMessage message2;
    public MessageFactory(MessageSender sender) {
        this.sender = sender;
    }

    public void sendAnswerToGuest(long chatId, String textOfMessage, ReplyKeyboard answerButtons) {
        try {
            message = new SendMessage(String.valueOf(chatId), textOfMessage);
            message.setReplyMarkup(answerButtons);
            sender.execute(message);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendAnswerToGuest(long chatId, String textOfMessage) {
        try {
            message = new SendMessage(String.valueOf(chatId), textOfMessage);
            sender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}