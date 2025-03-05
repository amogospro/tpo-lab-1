package com.amoguspro.task3;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель персонажа Простетника Вогона Джельца.
 */
public class ProstetnikVogonJeltz {

    private final SmileSequence smileSequence;     // Обычная улыбка
    private final ScreamSession screamSession;     // Сессия воплей
    private final RestState restState;             // Состояние отдохнувший / нет
    private final GnustnostState gnustnostState;   // Готов ли к небольшой гнусности
    private final SlowSmileBehavior slowSmileBehavior; // Поведение "очень медленной" улыбки

    // Храним, успешно ли персонаж "улыбается" (любой улыбкой) в данный момент.
    private boolean isSmiling;

    // Список мышц, которые Вогон пытается задействовать
    private final List<Muscle> currentAttemptedMuscleMovements = new ArrayList<>();

    public ProstetnikVogonJeltz() {
        this.smileSequence = new SmileSequence();
        this.screamSession = new ScreamSession();
        this.isSmiling = false;
        this.restState = new RestState();
        this.gnustnostState = new GnustnostState();
        this.slowSmileBehavior = new SlowSmileBehavior();
    }

    // -------------------------------------------------------------
    // Методы для обычной улыбки
    // -------------------------------------------------------------

    /**
     * Добавляем движение мышцы в текущую попытку (для обычной или медленной улыбки).
     */
    public void addMuscleMovement(Muscle muscle) {
        currentAttemptedMuscleMovements.add(muscle);
    }

    /**
     * Проверяем, удалось ли улыбнуться правильно (обычная улыбка).
     */
    public boolean tryToSmile() {
        boolean isCorrect = smileSequence.isCorrectSmileSequence(currentAttemptedMuscleMovements);
        if (isCorrect) {
            isSmiling = true;
        } else {
            isSmiling = false;
        }
        return isCorrect;
    }

    /**
     * Сбрасывает текущую попытку улыбнуться.
     */
    public void resetSmileAttempt() {
        currentAttemptedMuscleMovements.clear();
        isSmiling = false;
    }

    /**
     * Возвращает, улыбается ли Вогон в данный момент.
     */
    public boolean isSmiling() {
        return isSmiling;
    }

    // -------------------------------------------------------------
    // Методы для «медленной» улыбки
    // -------------------------------------------------------------

    /**
     * Проверяем, удалась ли "медленная" улыбка.
     */
    public boolean tryToSlowSmile() {
        boolean isCorrect = slowSmileBehavior.isCorrectSlowSmile(currentAttemptedMuscleMovements);
        if (isCorrect) {
            isSmiling = true; // Флаг "улыбается" общий
        } else {
            isSmiling = false;
        }
        return isCorrect;
    }

    // -------------------------------------------------------------
    // Методы для "серии воплей"
    // -------------------------------------------------------------

    /**
     * Добавить вопль в текущую серию.
     * @param screamText – описание вопля.
     */
    public void scream(String screamText) {
        screamSession.addScream(screamText);
    }

    /**
     * Количество воплей в текущей серии.
     */
    public int getScreamCount() {
        return screamSession.getScreamCount();
    }

    /**
     * Очистить текущую серию воплей.
     */
    public void clearAllScreams() {
        screamSession.clearScreams();
    }

    // -------------------------------------------------------------
    // Методы для состояний: «отдохнувший» и «готов к гнусности»
    // -------------------------------------------------------------

    /**
     * Вогон почувствовал себя отдохнувшим.
     */
    public void feelRested() {
        restState.setRested(true);
    }

    /**
     * Вогон устал / перестал быть отдохнувшим.
     */
    public void feelTired() {
        restState.setRested(false);
    }

    /**
     * Проверка, отдохнувший ли Вогон.
     */
    public boolean isRested() {
        return restState.isRested();
    }

    /**
     * Вогон стал готов к "небольшой гнусности".
     */
    public void becomeReadyForGnustnost() {
        gnustnostState.setReady(true);
    }

    /**
     * Вогон больше не готов к гнусности (остыл или передумал).
     */
    public void calmDownGnustnost() {
        gnustnostState.setReady(false);
    }

    /**
     * Проверка, готов ли он к небольшой гнусности.
     */
    public boolean isReadyForGnustnost() {
        return gnustnostState.isReady();
    }
}
