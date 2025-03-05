package com.amoguspro.task3;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для ведения учёта серии воплей.
 */
public class ScreamSession {
    private final List<String> screams = new ArrayList<>();

    /**
     * Добавляет в сессию вопль с каким-то описанием или текстом.
     *
     * @param screamText – описание или текст вопля
     */
    public void addScream(String screamText) {
        screams.add(screamText);
    }

    /**
     * Возвращает общее количество воплей в текущей сессии.
     */
    public int getScreamCount() {
        return screams.size();
    }

    /**
     * Очищает текущую серию воплей.
     */
    public void clearScreams() {
        screams.clear();
    }
}