package com.amoguspro.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VogonTest {

    private Vogon vogon;

    @BeforeEach
    void init() {
        vogon = new Vogon("Простетник Вогон Джельц");
    }

    @Nested
    @DisplayName("Smile tests")
    class SmileTests {

        @Test
        @DisplayName("Check normal smile change")
        void checkSmileChange() {
            // Начинаем с NOT_SMILING
            assertEquals(SmileSpeed.NOT_SMILING, vogon.getSmileSpeed());

            vogon.changeSmileSpeedUp();
            assertEquals(SmileSpeed.SLOW, vogon.getSmileSpeed());

            vogon.changeSmileSpeedUp();
            assertEquals(SmileSpeed.FAST, vogon.getSmileSpeed());
        }

        @Test
        @DisplayName("Check too fast smile")
        void checkTooFastSmile() {
            // Дойдём до FAST
            vogon.changeSmileSpeedUp();
            vogon.changeSmileSpeedUp();
            // Теперь попробуем ещё раз
            Exception e = assertThrows(Exception.class, () -> vogon.changeSmileSpeedUp());
            assertEquals("Вогон не может улыбаться быстрее, чем FAST!", e.getMessage());
        }

        @Test
        @DisplayName("Check negative smile (down from NOT_SMILING)")
        void checkNegativeSmile() {
            Exception e = assertThrows(Exception.class, () -> vogon.changeSmileSpeedDown());
            assertEquals("Вогон и так не улыбается, дальше уже некуда!", e.getMessage());
        }
    }

    @Nested
    @DisplayName("Prisoners tests")
    class PrisonersTests {

        @Test
        @DisplayName("Capture prisoner ok")
        void capturePrisonerOk() {
            assertDoesNotThrow(() -> vogon.capturePrisoner(new Prisoner("Форд")));
            assertEquals(1, vogon.getPrisoners().size());
        }

        @Test
        @DisplayName("Capture already existing prisoner")
        void captureExistingPrisoner() {
            vogon.capturePrisoner(new Prisoner("Форд"));
            Exception e = assertThrows(Exception.class, () -> vogon.capturePrisoner(new Prisoner("Форд")));
            assertEquals("Этот пленник уже в неволе!", e.getMessage());
        }

        @Test
        @DisplayName("Remove existing prisoner")
        void removeExistingPrisoner() {
            vogon.capturePrisoner(new Prisoner("Артур"));
            assertDoesNotThrow(() -> vogon.removePrisoner("Артур"));
            assertTrue(vogon.getPrisoners().isEmpty());
        }

        @Test
        @DisplayName("Remove non-existing prisoner")
        void removeNonExisting() {
            Exception e = assertThrows(Exception.class, () -> vogon.removePrisoner("Триллиан"));
            assertEquals("Нет такого пленника!", e.getMessage());
        }
    }

    @Nested
    @DisplayName("Screaming tests")
    class ScreamingTests {

        @Test
        @DisplayName("Normal screaming at prisoners")
        void normalScreaming() {
            // Исходно vigor=100, nastyLevel=LOW
            assertDoesNotThrow(() -> vogon.screamAtPrisoners(10));
            assertEquals(90, vogon.getVigor());
            // Уровень гнусности стал MEDIUM
            assertEquals(NastyLevel.MEDIUM, vogon.getNastyLevel());
        }

        @Test
        @DisplayName("Not enough vigor to scream")
        void notEnoughVigor() {
            vogon.setVigor(5);
            Exception e = assertThrows(Exception.class, () -> vogon.screamAtPrisoners(10));
            assertEquals("Вогону не хватает сил, чтобы покричать!", e.getMessage());
        }

        @Test
        @DisplayName("Wrong energy (<=0)")
        void wrongEnergy() {
            Exception e = assertThrows(Exception.class, () -> vogon.screamAtPrisoners(0));
            assertEquals("Неверная величина энергии для воплей!", e.getMessage());
        }

        @Test
        @DisplayName("Max nasty level reached")
        void maxNastyLevel() {
            // Доведём уровень гнусности до EXTREME
            vogon.setNastyLevel(NastyLevel.HIGH);
            assertDoesNotThrow(() -> vogon.screamAtPrisoners(10));
            assertEquals(NastyLevel.EXTREME, vogon.getNastyLevel());

            // Теперь пробуем снова
            Exception e = assertThrows(Exception.class, () -> vogon.screamAtPrisoners(10));
            assertEquals("Уровень гнусности уже на максимуме! Дальше только читать стихи!", e.getMessage());
        }
    }

    @Nested
    @DisplayName("Nastiness tests")
    class NastinessTests {

        @Test
        @DisplayName("Perform normal nastiness")
        void normalNastiness() {
            // Исходно vigor=100, smile=NOT_SMILING
            assertDoesNotThrow(() -> vogon.performNastiness(10));
            // Проверяем, что был вызов changeSmileSpeedUp() и потом changeSmileSpeedDown()
            // Если всё ок, то вернулись к исходному состоянию
            assertEquals(SmileSpeed.NOT_SMILING, vogon.getSmileSpeed());
            assertEquals(90, vogon.getVigor());
        }

        @Test
        @DisplayName("Try to do nastiness with 0 or negative value")
        void checkZeroOrNegativeNastiness() {
            Exception e = assertThrows(Exception.class, () -> vogon.performNastiness(0));
            assertEquals("Нельзя совершать гнусность с неположительной \"стоимостью\"!", e.getMessage());
        }

        @Test
        @DisplayName("Try to do nastiness without enough vigor")
        void checkNotEnoughVigor() {
            vogon.setVigor(5);
            Exception e = assertThrows(Exception.class, () -> vogon.performNastiness(10));
            assertEquals("Вогону не хватает сил на эту гнусность!", e.getMessage());
        }
    }
}
