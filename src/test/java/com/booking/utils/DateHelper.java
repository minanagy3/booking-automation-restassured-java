package com.booking.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    private static final DateTimeFormatter DISPLAY_FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private static final DateTimeFormatter API_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String formatDate(LocalDate date) {
        return date.format(DISPLAY_FORMATTER);
    }

    public static String formatDateForAPI(LocalDate date) {
        return date.format(API_FORMATTER);
    }

    public static String formatDateForDisplay(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return date.format(formatter);
    }

    public static boolean areDatesEqual(LocalDate date1, LocalDate date2) {
        return date1.equals(date2);
    }
}

