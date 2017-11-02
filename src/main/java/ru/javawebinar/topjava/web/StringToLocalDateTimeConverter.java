package ru.javawebinar.topjava.web;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by j on 02.11.2017.
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    public LocalDateTime convert(String source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return LocalDateTime.parse(source, formatter);
    }
}
