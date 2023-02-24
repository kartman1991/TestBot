package com.project.TestBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;

@Component
public class TestBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
//        System.out.println(update);
        if (Objects.equals(update.getMessage().getText(), "/start")) {
            sendMessage(update.getMessage().getFrom().getId() , "Привет " + update.getMessage().getFrom().getFirstName());
        } else {
            sendMessage(update.getMessage().getFrom().getId() ,update.getMessage().getText());
        }

    }

    @Override
    public String getBotToken() {
        return "6005163855:AAFFjt9kBNCWIIpAldBm3HFJV43pxPW_nyE";
    }

    @Override
    public String getBotUsername() {
        return "TestBot";
    }

    public void sendMessage(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
