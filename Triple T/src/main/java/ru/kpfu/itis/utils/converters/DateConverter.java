package ru.kpfu.itis.utils.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class DateConverter implements Converter<LocalDate, String> {

    @Override
    public String convert(LocalDate source) {
        return source.getDayOfMonth() + " " + source.getMonth() + " " + source.getYear();
    }
}
