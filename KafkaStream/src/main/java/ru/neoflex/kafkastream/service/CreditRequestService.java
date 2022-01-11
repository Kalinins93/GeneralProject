package ru.neoflex.kafkastream.service;

import ru.neoflex.kafkastream.model.RestReceiveRequest;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreditRequestService {
    public static boolean isYounger(RestReceiveRequest request) {
        Calendar old = new GregorianCalendar();
        Calendar young = new GregorianCalendar();
        Calendar calDateBirth = new GregorianCalendar();
        calDateBirth.setTime(Date.valueOf(request.getBirthday()));
        System.out.printf(Date.valueOf(request.getBirthday()).toString());
        old.roll(Calendar.YEAR, -35);
        young.roll(Calendar.YEAR, -18);
        System.out.printf(String.valueOf(young.after(calDateBirth)&& old.before(calDateBirth)));
        return young.after(calDateBirth) && old.before(calDateBirth);
    }
}

