package com.amoguspro.task3;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.Set;

/**
 * Главный герой нашей истории — Простетник Вогон Джельц.
 */
@Data
public class Vogon {
    private String name;

    // Текущее "качество" улыбки (аналог уровня шума)
    private SmileSpeed smileSpeed = SmileSpeed.NOT_SMILING;

    // Текущий уровень гнусности
    private NastyLevel nastyLevel = NastyLevel.LOW;

    // Текущий «запас» сил или настроения для издевательств (аналог топлива)
    private int vigor = 100;

    // Пленники, над которыми можно издеваться
    private final Set<Prisoner> prisoners = new HashSet<>();

    public Vogon(String name) {
        this.name = name;
    }

    /**
     * Добавить пленника.
     * @param prisoner пленник
     * @throws Exception если такой пленник уже есть
     */
    @SneakyThrows
    public void capturePrisoner(Prisoner prisoner) {
        if (prisoners.contains(prisoner)) {
            throw new Exception("Этот пленник уже в неволе!");
        }
        prisoners.add(prisoner);
    }

    /**
     * Удалить пленника (выпустить или уничтожить — на ваше усмотрение).
     * @param prisonerName имя пленника
     * @throws Exception если такого нет
     */
    @SneakyThrows
    public void removePrisoner(String prisonerName) {
        Prisoner found = prisoners.stream()
                .filter(p -> p.getName().equals(prisonerName))
                .findAny()
                .orElseThrow(() -> new Exception("Нет такого пленника!"));
        prisoners.remove(found);
    }

    /**
     * Медленно (или быстро) улыбнуться. Аналог increaseSound() / decreaseSound().
     */
    @SneakyThrows
    public void changeSmileSpeedUp() {
        // Пытаемся перейти к следующему состоянию
        switch (smileSpeed) {
            case NOT_SMILING -> this.smileSpeed = SmileSpeed.SLOW;
            case SLOW -> this.smileSpeed = SmileSpeed.FAST;
            case FAST -> throw new Exception("Вогон не может улыбаться быстрее, чем FAST!");
        }
    }

    @SneakyThrows
    public void changeSmileSpeedDown() {
        // Пытаемся перейти к предыдущему состоянию
        switch (smileSpeed) {
            case FAST -> this.smileSpeed = SmileSpeed.SLOW;
            case SLOW -> this.smileSpeed = SmileSpeed.NOT_SMILING;
            case NOT_SMILING -> throw new Exception("Вогон и так не улыбается, дальше уже некуда!");
        }
    }

    /**
     * Побаловать себя серией воплей на пленников (тратит энергию, но повышает настроение).
     * @param energyNeeded сколько потратить «сил»
     * @throws Exception если энергии не хватает
     */
    @SneakyThrows
    public void screamAtPrisoners(int energyNeeded) {
        if (energyNeeded <= 0) {
            throw new Exception("Неверная величина энергии для воплей!");
        }
        if (vigor < energyNeeded) {
            throw new Exception("Вогону не хватает сил, чтобы покричать!");
        }
        this.vigor -= energyNeeded;
        // При удачном крике улучшим уровень гнусности:
        switch (nastyLevel) {
            case LOW -> this.nastyLevel = NastyLevel.MEDIUM;
            case MEDIUM -> this.nastyLevel = NastyLevel.HIGH;
            case HIGH -> this.nastyLevel = NastyLevel.EXTREME;
            case EXTREME -> {
                // Уровень гнусности уже максимальный, можно кинуть исключение
                // или просто оставить как есть (на ваше усмотрение)
                throw new Exception("Уровень гнусности уже на максимуме! Дальше только читать стихи!");
            }
        }
    }

    /**
     * Совершить «небольшую гнусность».
     * @param value условная «цена» гнусности: чем выше, тем больше расходов (аналог flight)
     * @throws Exception если value <= 0 или не хватает «энергии»
     */
    @SneakyThrows
    public void performNastiness(int value) {
        if (value <= 0) {
            throw new Exception("Нельзя совершать гнусность с неположительной \"стоимостью\"!");
        }
        if (this.vigor - value < 0) {
            throw new Exception("Вогону не хватает сил на эту гнусность!");
        }
        // Допустим, при гнусности он и улыбается, и потом перестаёт
        changeSmileSpeedUp();     // улыбнулись (повысили скорость улыбки)
        this.vigor -= value;      // потратили энергию
        changeSmileSpeedDown();   // вернулись к предыдущему уровню улыбки
    }
}
