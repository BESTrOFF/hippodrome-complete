import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    Hippodrome hippodrome;

    @Test
    void when_nullParamInConstructor_throwIllegalException() {

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void when_emptyListInConstructor_throwIllegalException() {
        List<Horse> horses = new ArrayList<>();

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i, i));
        }

        hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }

    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Winner", 10, 10));
        horses.add(new Horse("NotWinner",10, 9));
        horses.add(new Horse("NotWinner", 10, 8));

        Horse winner = horses.get(0);

        for (int i =0; i < horses.size(); i++){
            if (winner.getDistance() < horses.get(i).getDistance()){
                winner = horses.get(i);
            }
        }

        hippodrome = new Hippodrome(horses);

        assertEquals(winner, hippodrome.getWinner());
    }
}