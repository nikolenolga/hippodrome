import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void givenNullName_whenHorseConstructor_thenIllegalArgumentException() {
        // given
            String name = null;
            double speed = 1;
            double distance = 1;

        // when, then
            assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    void givenNullName_whenHorseConstructor_thenThrowsExceptionWithMessage() {
        // given
        String name = null;
        double speed = 1;
        double distance = 1;

        // when
        Throwable exception = assertThrows(Throwable.class, () -> new Horse(name, speed, distance));

        // then
        String expectedMessage = "Name cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "\t", "\n", "\r", "\f", "\t\n\r  \f"})
    void givenBlankName_whenHorseConstructor_thenIllegalArgumentException(String name) {
        // given
        double speed = 1;
        double distance = 1;

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "\t", "\n", "\r", "\f", "\t\n\r  \f"})
    void givenBlankName_whenHorseConstructor_thenThrowsExceptionWithMessage(String name) {
        // given
        double speed = 1;
        double distance = 1;

        // when
        Throwable exception = assertThrows(Throwable.class, () -> new Horse(name, speed, distance));

        // then
        String expectedMessage = "Name cannot be blank.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenNegativeSpeed_whenHorseConstructor_thenIllegalArgumentException() {
        // given
        String name = "ExampleName";
        double speed = -5;
        double distance = 1;

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    void givenNegativeSpeed_whenHorseConstructor_thenThrowsExceptionWithMessage() {
        // given
        String name = "ExampleName";
        double speed = -5;
        double distance = 1;

        // when
        Throwable exception = assertThrows(Throwable.class, () -> new Horse(name, speed, distance));

        // then
        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenNegativeDistance_whenHorseConstructor_thenIllegalArgumentException() {
        // given
        String name = "ExampleName";
        double speed = 1;
        double distance = -5;

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    void givenNegativeDistance_whenHorseConstructor_thenThrowsExceptionWithMessage() {
        // given
        String name = "ExampleName";
        double speed = 1;
        double distance = -5;

        // when
        Throwable exception = assertThrows(Throwable.class, () -> new Horse(name, speed, distance));

        // then
        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenNewHorse_whenGetName_thenNameFromConstructor() {
        // given
        String expectedName = "ExampleName";
        double speed = 1;
        double distance = 1;
        Horse horse = new Horse(expectedName, speed, distance);

        // when
        String actualName = horse.getName();

        // then
        assertEquals(expectedName, actualName);
    }

    @Test
    void givenNewHorse_whenGetSpeed_thenSpeedFromConstructor() {
        // given
        String name = "ExampleName";
        double expectedSpeed = 2.7;
        double distance = 1;
        Horse horse = new Horse(name, expectedSpeed, distance);

        // when
        double actualSpeed = horse.getSpeed();

        // then
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    void givenNewHorseWith3ArgsConstr_whenGetDistance_thenDistanceFromConstructor() {
        // given
        String name = "ExampleName";
        double speed = 1;
        double expectedDistance = 30.4;
        Horse horse = new Horse(name, speed, expectedDistance);

        // when
        double actualDistance = horse.getDistance();

        // then
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void givenNewHorseWith2ArgsConstr_whenGetDistance_thenDistanceIsZero() {
        // given
        String name = "ExampleName";
        double speed = 1;
        Horse horse = new Horse(name, speed);

        // when
        double actualDistance = horse.getDistance();

        // then
        double expectedDistance = 0;
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void givenHorse_whenMove_thenGetRandomDoubleMethodWithArgsIsCalled() {
        //given
        Horse horse = new Horse("ExampleName", 1, 1);

        try(MockedStatic<Horse> randHorseStep = Mockito.mockStatic(Horse.class)) {

            //when
            horse.move();

            //then
            randHorseStep.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.4, 6.7, 454.6, 0.44443333, 0.0})
    void givenHorse_whenMove_thenDistanceFormulaCheck(double methodResult) {
        //given
        double speed = 10;
        double distance = 7;
        Horse horse = new Horse("ExampleName", speed, distance);

        try(MockedStatic<Horse> randHorseStep = Mockito.mockStatic(Horse.class)) {
            randHorseStep.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(methodResult);
            double expectedDistance = distance + speed * Horse.getRandomDouble(0.2, 0.9);

            //when
            horse.move();

            //then
            double actualDistance = horse.getDistance();
            assertEquals(expectedDistance, actualDistance);
        }
    }
}