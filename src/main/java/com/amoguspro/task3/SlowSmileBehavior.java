package com.amoguspro.task3;

import java.util.List;

/**
 * Класс, описывающий поведение медленной улыбки.
 * Логика проверки последовательности схожа с обычной,
 * но вынесена в отдельный класс.
 */
public class SlowSmileBehavior {

    private static final List<Muscle> CORRECT_SLOW_SEQUENCE = List.of(
            Muscle.EYEBROW_LEFT,
            Muscle.EYEBROW_RIGHT,
            Muscle.UPPER_LIP,
            Muscle.LEFT_CHEEK,
            Muscle.RIGHT_CHEEK,
            Muscle.LOWER_LIP,
            Muscle.CHIN
    );

    /**
     * Проверяет, правильна ли «медленная» улыбка.
     */
    public boolean isCorrectSlowSmile(List<Muscle> attemptedSequence) {
        if (attemptedSequence.size() != CORRECT_SLOW_SEQUENCE.size()) {
            return false;
        }
        for (int i = 0; i < CORRECT_SLOW_SEQUENCE.size(); i++) {
            if (CORRECT_SLOW_SEQUENCE.get(i) != attemptedSequence.get(i)) {
                return false;
            }
        }
        return true;
    }
}
