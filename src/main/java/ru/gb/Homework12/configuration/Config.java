package ru.gb.Homework12.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class Config {
    // принимает текст
    @Bean
    public MessageChannel textInputChannel() {
        return new DirectChannel();
    }

    // записывает в файл
    @Bean
    public MessageChannel fileWriterChannel() {
        return new DirectChannel();
    }

    // преобразует из текста в файл
    @Bean
    @Transformer(inputChannel = "textInputChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> mainTransformer() {
        return text -> {text = text.toUpperCase();
            return text;
        };
    }

    // запускает процесс
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler myHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(
                "ru/gb/Homework12/src/main/resources"));

        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);

        return handler;
    }
}
