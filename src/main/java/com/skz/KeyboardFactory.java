package com.skz;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class KeyboardFactory {
    public static ReplyKeyboard answerRuAdultsButtons() {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton[] inlineKeyboardButton1 = new InlineKeyboardButton[8];
        for (int i = 0; i < inlineKeyboardButton1.length; i++) {
            inlineKeyboardButton1[i] = new InlineKeyboardButton((i + 1) + "");
            inlineKeyboardButton1[i].setCallbackData((i + 1) + " adultsru");
            rowInline1.add(inlineKeyboardButton1[i]);
        }
        rowsInline.add(rowInline1);
        inlineKeyboard.setKeyboard(rowsInline);
        return inlineKeyboard;
    }

    public static ReplyKeyboard answerRuChildButtons() {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton[] inlineKeyboardButton1 = new InlineKeyboardButton[8];
        inlineKeyboardButton1[0] = new InlineKeyboardButton(new String("Нет".getBytes(), StandardCharsets.UTF_8));
        inlineKeyboardButton1[0].setCallbackData(0 + " childrenru");
        rowInline1.add(inlineKeyboardButton1[0]);
        for (int i = 1; i < inlineKeyboardButton1.length; i++) {
            inlineKeyboardButton1[i] = new InlineKeyboardButton(i + "");
            inlineKeyboardButton1[i].setCallbackData(i + " childrenru");
            rowInline1.add(inlineKeyboardButton1[i]);
        }
        rowsInline.add(rowInline1);
        inlineKeyboard.setKeyboard(rowsInline);
        return inlineKeyboard;
    }
}
