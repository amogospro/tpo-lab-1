package com.amoguspro.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для проверки работы Простетника Вогона Джельца:
 * - Корректная последовательность движений мышц для обычной улыбки
 * - Серия воплей
 * - Дополнительные базовые сценарии
 */
public class ProstetnikVogonJeltzTest {

    private ProstetnikVogonJeltz vogon;

    @BeforeEach
    public void setUp() {
        vogon = new ProstetnikVogonJeltz();
    }

    @Test
    public void testCorrectSmileSequence() {
        // Добавляем движения мышц в правильном порядке
        vogon.addMuscleMovement(Muscle.EYEBROW_LEFT);
        vogon.addMuscleMovement(Muscle.EYEBROW_RIGHT);
        vogon.addMuscleMovement(Muscle.UPPER_LIP);
        vogon.addMuscleMovement(Muscle.LEFT_CHEEK);
        vogon.addMuscleMovement(Muscle.RIGHT_CHEEK);
        vogon.addMuscleMovement(Muscle.LOWER_LIP);
        vogon.addMuscleMovement(Muscle.CHIN);

        boolean result = vogon.tryToSmile();
        Assertions.assertTrue(result, "Ожидалось, что правильная последовательность позволит успешно улыбнуться");
        Assertions.assertTrue(vogon.isSmiling(), "Вогон должен улыбаться после успешной последовательности");
    }

    @Test
    public void testIncorrectSmileSequence() {
        // Перепутаем местами некоторые мышцы
        vogon.addMuscleMovement(Muscle.EYEBROW_RIGHT);
        vogon.addMuscleMovement(Muscle.EYEBROW_LEFT);
        vogon.addMuscleMovement(Muscle.UPPER_LIP);
        vogon.addMuscleMovement(Muscle.LEFT_CHEEK);
        vogon.addMuscleMovement(Muscle.CHIN);
        vogon.addMuscleMovement(Muscle.RIGHT_CHEEK);
        vogon.addMuscleMovement(Muscle.LOWER_LIP);

        boolean result = vogon.tryToSmile();
        Assertions.assertFalse(result, "Ожидалось, что неправильная последовательность не даст улыбнуться");
        Assertions.assertFalse(vogon.isSmiling(), "Вогон не должен улыбаться, если мышцы задействованы неверно");
    }

    @Test
    public void testResetSmileAttempt() {
        // Добавляем несколько движений
        vogon.addMuscleMovement(Muscle.EYEBROW_LEFT);
        vogon.addMuscleMovement(Muscle.CHIN);

        // Сбрасываем и проверяем
        vogon.resetSmileAttempt();

        Assertions.assertFalse(vogon.isSmiling(), "После сброса улыбка должна быть недействительной");
        // Попробуем улыбнуться без движений – должно вернуться false
        boolean result = vogon.tryToSmile();
        Assertions.assertFalse(result, "Без движений мышц улыбка невозможна");
    }

    @Test
    public void testScreamSeries() {
        // Добавляем несколько воплей
        vogon.scream("Первый вопль – раздражённый");
        vogon.scream("Второй вопль – чуть громче");
        vogon.scream("Третий вопль – с особыми непарламентскими выражениями");

        int count = vogon.getScreamCount();
        Assertions.assertEquals(3, count, "Ожидалось, что будет три вопля");

        // Очищаем вопли
        vogon.clearAllScreams();
        Assertions.assertEquals(0, vogon.getScreamCount(), "После очистки не должно остаться воплей");
    }

    @Test
    public void testSmileThenScream() {
        // Проверим общую логику: сначала вогон пытается улыбнуться,
        // потом устраивает серию воплей.
        vogon.addMuscleMovement(Muscle.EYEBROW_LEFT);
        vogon.addMuscleMovement(Muscle.EYEBROW_RIGHT);
        vogon.addMuscleMovement(Muscle.UPPER_LIP);
        vogon.addMuscleMovement(Muscle.LEFT_CHEEK);
        vogon.addMuscleMovement(Muscle.RIGHT_CHEEK);
        vogon.addMuscleMovement(Muscle.LOWER_LIP);
        vogon.addMuscleMovement(Muscle.CHIN);

        vogon.tryToSmile();
        Assertions.assertTrue(vogon.isSmiling(), "Вогон должен улыбаться");

        vogon.scream("Перезагружаем пленников в ужасе");
        vogon.scream("Повышаем громкость");
        Assertions.assertEquals(2, vogon.getScreamCount(), "Должно быть два вопля");
    }

    @Test
    public void testRestState() {
        // По умолчанию Вогон не отдохнувший
        Assertions.assertFalse(vogon.isRested(), "В начале Вогон не должен быть отдохнувшим");

        // Теперь он отдохнул
        vogon.feelRested();
        Assertions.assertTrue(vogon.isRested(), "Ожидалось, что Вогон будет отдохнувшим");

        // Может снова устать
        vogon.feelTired();
        Assertions.assertFalse(vogon.isRested(), "После feelTired() Вогон опять не отдохнувший");
    }

    @Test
    public void testGnustnostState() {
        // Сначала Вогон не готов к гнусности
        Assertions.assertFalse(vogon.isReadyForGnustnost(), "Изначально не готов к гнусности");

        // Становится готовым
        vogon.becomeReadyForGnustnost();
        Assertions.assertTrue(vogon.isReadyForGnustnost(), "Теперь должен быть готов к гнусности");

        // Может «остыть»
        vogon.calmDownGnustnost();
        Assertions.assertFalse(vogon.isReadyForGnustnost(), "После calmDownGnustnost() Вогон снова не готов к гнусности");
    }

    @Test
    public void testSlowSmileCorrectSequence() {
        // Добавляем движения мышц в правильном порядке
        vogon.addMuscleMovement(Muscle.EYEBROW_LEFT);
        vogon.addMuscleMovement(Muscle.EYEBROW_RIGHT);
        vogon.addMuscleMovement(Muscle.UPPER_LIP);
        vogon.addMuscleMovement(Muscle.LEFT_CHEEK);
        vogon.addMuscleMovement(Muscle.RIGHT_CHEEK);
        vogon.addMuscleMovement(Muscle.LOWER_LIP);
        vogon.addMuscleMovement(Muscle.CHIN);

        boolean result = vogon.tryToSlowSmile();
        Assertions.assertTrue(result, "Ожидалось, что при правильной последовательности медленная улыбка получится");
        Assertions.assertTrue(vogon.isSmiling(), "Вогон должен 'медленно' улыбаться после успеха");
    }

    @Test
    public void testSlowSmileIncorrectSequence() {
        // Специально нарушаем порядок
        vogon.addMuscleMovement(Muscle.EYEBROW_RIGHT);
        vogon.addMuscleMovement(Muscle.EYEBROW_LEFT);
        vogon.addMuscleMovement(Muscle.UPPER_LIP);
        vogon.addMuscleMovement(Muscle.LEFT_CHEEK);
        vogon.addMuscleMovement(Muscle.RIGHT_CHEEK);
        vogon.addMuscleMovement(Muscle.CHIN);
        vogon.addMuscleMovement(Muscle.LOWER_LIP);

        boolean result = vogon.tryToSlowSmile();
        Assertions.assertFalse(result, "При неправильном порядке медленная улыбка не должна сработать");
        Assertions.assertFalse(vogon.isSmiling(), "Неудачная попытка медленно улыбнуться не даёт флаг smiling");
    }

    @Test
    public void testFullScenario() {
        // Вогон только что отдохнул и готов к небольшой гнусности
        vogon.feelRested();
        vogon.becomeReadyForGnustnost();
        Assertions.assertTrue(vogon.isRested(), "Вогон должен быть отдохнувшим");
        Assertions.assertTrue(vogon.isReadyForGnustnost(), "Вогон должен быть готов к гнусности");

        // Пробуем сначала обычную улыбку
        vogon.addMuscleMovement(Muscle.EYEBROW_LEFT);
        vogon.addMuscleMovement(Muscle.EYEBROW_RIGHT);
        vogon.addMuscleMovement(Muscle.UPPER_LIP);
        vogon.addMuscleMovement(Muscle.LEFT_CHEEK);
        vogon.addMuscleMovement(Muscle.RIGHT_CHEEK);
        vogon.addMuscleMovement(Muscle.LOWER_LIP);
        vogon.addMuscleMovement(Muscle.CHIN);
        boolean smileResult = vogon.tryToSmile();

        Assertions.assertTrue(smileResult, "При правильной последовательности обычная улыбка должна удаться");
        Assertions.assertTrue(vogon.isSmiling(), "Вогон должен улыбаться");

        // Сбросим и проверим медленную улыбку
        vogon.resetSmileAttempt();
        vogon.addMuscleMovement(Muscle.EYEBROW_LEFT);
        vogon.addMuscleMovement(Muscle.EYEBROW_RIGHT);
        vogon.addMuscleMovement(Muscle.UPPER_LIP);
        vogon.addMuscleMovement(Muscle.LEFT_CHEEK);
        vogon.addMuscleMovement(Muscle.RIGHT_CHEEK);
        vogon.addMuscleMovement(Muscle.LOWER_LIP);
        vogon.addMuscleMovement(Muscle.CHIN);

        boolean slowSmileResult = vogon.tryToSlowSmile();
        Assertions.assertTrue(slowSmileResult, "Медленная улыбка должна тоже сработать");
        Assertions.assertTrue(vogon.isSmiling(), "Вогон снова улыбается (но теперь медленно)");

        // И завершить всё это серией воплей
        vogon.scream("Злобный, но неторопливый вопль");
        vogon.scream("Чуть более угрожающий вопль");
        Assertions.assertEquals(2, vogon.getScreamCount(), "Два вопля в серии");

        // Допустим, Вогон устал и передумал быть гнусным
        vogon.feelTired();
        vogon.calmDownGnustnost();
        Assertions.assertFalse(vogon.isRested(), "Больше не отдохнувший");
        Assertions.assertFalse(vogon.isReadyForGnustnost(), "И не готов к гнусности");
    }
}

