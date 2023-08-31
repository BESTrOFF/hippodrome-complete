import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;



import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void when_nameIsNull_throwIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 10));

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t"})
    void when_nameIsEmptySpaceTab_throwIllegalArgumentException(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 10));

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void when_speedIsNegative_throwIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("name", -10));

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void when_distanceIsNegative_throwIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("name", 10, -10));

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        String name = "Name";
        Horse horse = new Horse(name, 10);

        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed() {
        double speed = 10;
        Horse horse = new Horse("Name", speed);

        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        double distance = 10;
        Horse horse1 = new Horse("Name", 2, distance);
        Horse horse2 = new Horse("Name", 2);

        assertAll(
                () -> assertEquals(distance, horse1.getDistance()),
                () -> assertEquals(0, horse2.getDistance())
        );
    }

    @Test
    void moveCallGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Name", 10);

            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 0.2, 0.5})
    void move_returnsTheCorrectValue(double random) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Name", 10, 10);

            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            double result = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);

            horse.move();

            assertEquals(result, horse.getDistance());
        }
    }

    @Test
    void getRandomDouble() {
    }
}