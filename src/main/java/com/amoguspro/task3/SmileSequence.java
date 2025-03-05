package com.amoguspro.task3;

import java.util.List;

/**
 * Описывает корректную (каноническую) последовательность
 * движений мышц лица для правильной вогоньей улыбки.
 */
public class SmileSequence {

    // Фиксированная эталонная последовательность.
    private static final List<Muscle> CORRECT_SEQUENCE = List.of(
            Muscle.EYEBROW_LEFT,
            Muscle.EYEBROW_RIGHT,
            Muscle.UPPER_LIP,
            Muscle.LEFT_CHEEK,
            Muscle.RIGHT_CHEEK,
            Muscle.LOWER_LIP,
            Muscle.CHIN
    );

    /**
     * Проверяет, совпадает ли введённая последовательность движений
     * с эталонной.
     *
     * @param attemptedSequence – последовательность, которую применил Вогон
     * @return true, если последовательность точна и правильна
     */
    public boolean isCorrectSmileSequence(List<Muscle> attemptedSequence) {
        if (attemptedSequence.size() != CORRECT_SEQUENCE.size()) {
            return false;
        }
        for (int i = 0; i < CORRECT_SEQUENCE.size(); i++) {
            if (CORRECT_SEQUENCE.get(i) != attemptedSequence.get(i)) {
                return false;
            }
        }
        return true;
    }
}