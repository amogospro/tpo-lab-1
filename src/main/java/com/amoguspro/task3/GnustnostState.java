package com.amoguspro.task3;


/**
 * Класс для отслеживания состояния «готов ли Вогон к небольшой гнусности».
 */
public class GnustnostState {
    private boolean isReadyForGnustnost;

    public GnustnostState() {
        this.isReadyForGnustnost = false; // По умолчанию не готов
    }

    public boolean isReady() {
        return isReadyForGnustnost;
    }

    public void setReady(boolean ready) {
        this.isReadyForGnustnost = ready;
    }
}