import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void givenNullHorsesList_whenHippodromeConstructor_thenIllegalArgumentException() {
        //given
        List<Horse> horses = null;

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    void givenNullHorsesList_whenHippodromeConstructor_thenThrowsExceptionWithMessage() {
        //given
        List<Horse> horses = null;

        //when
        Throwable exception = assertThrows(Throwable.class, () -> new Hippodrome(horses));

        //then
        String expectedMessage = "Horses cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenEmptyHorsesList_whenHippodromeConstructor_thenIllegalArgumentException() {
        //given
        List<Horse> horses = new ArrayList<>();

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    void givenEmptyHorsesList_whenHippodromeConstructor_thenThrowsExceptionWithMessage() {
        //given
        List<Horse> horses = new ArrayList<>();

        //when
        Throwable exception = assertThrows(Throwable.class, () -> new Hippodrome(horses));

        //then
        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenNewHippodrome_whenGetHorses_thenHorsesListEqualFromConstructor() {
        //given
        List<Horse> expectedHorsesList = Stream.generate(() -> new Horse("ExampleName", Math.random(), 1))
                .limit(30)
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(expectedHorsesList);

        //when
        List<Horse> actualHorsesList = hippodrome.getHorses();

        //then
        assertEquals(expectedHorsesList, actualHorsesList);
    }

    @Test
    void givenHorsesList_whenMove_thenEachHorseMove() {
        //given
        List<Horse> horses = Stream.generate(() -> Mockito.mock(Horse.class))
                .limit(50)
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(horses);

        //when
        hippodrome.move();

        //then
        horses.forEach(horse -> Mockito.verify(horse).move());
    }

    @Test
    void givenHippodromeWithHorses_whenGetWinner_thenReturnHorseWithMaxDistance() {
        //given
        Horse expectedWinner = new Horse("Horse7", 1,7.2);
        List<Horse> horses = Arrays.asList(
                new Horse("Horse1", 1,2.0),
                new Horse("Horse2", 1,4.3),
                new Horse("Horse3", 1,7.199999999),
                new Horse("Horse4", 1,4.1),
                new Horse("Horse5", 1,3.3),
                new Horse("Horse6", 1,0.0),
                expectedWinner,
                new Horse("Horse8", 1,5.0)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        //when
        Horse actualWinner = hippodrome.getWinner();

        //then
        assertSame(expectedWinner, actualWinner);
    }

}