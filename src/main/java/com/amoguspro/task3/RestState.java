package com.amoguspro.task3;


/**
 * Отдельный класс, отвечающий за состояние «отдохнувший» / «не отдохнувший».
 */
public class RestState {
    private boolean isRested;

    public RestState() {
        this.isRested = false; // Изначально считаем, что Вогон не отдыхал
    }

    public boolean isRested() {
        return isRested;
    }

    public void setRested(boolean rested) {
        this.isRested = rested;
    }
}