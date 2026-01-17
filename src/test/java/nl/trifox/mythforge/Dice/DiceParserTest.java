package nl.trifox.mythforge.Dice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DiceParserTest {
    @Test
    void diceparser_should_roll_normal_dice() {
        DiceResult result = DiceParser.roll("2d6");
        assertEquals(2, result.rolls().length);
        int expectedTotal = Arrays.stream(result.rolls()).sum();
        assertEquals(expectedTotal, result.total());
    }

    @Test
    void diceparser_should_handle_advantage() {
        DiceResult result = DiceParser.roll("1d20adv");
        assertEquals(2, result.rolls().length);
        int max = Arrays.stream(result.rolls()).max().orElseThrow();
        assertEquals(max, result.total());
    }

    @Test
    void diceparser_should_handle_disadvantage() {
        DiceResult result = DiceParser.roll("1d20dis");
        assertEquals(2, result.rolls().length);
        int min = Arrays.stream(result.rolls()).min().orElseThrow();
        assertEquals(min, result.total());
    }

    @Test
    void diceparser_should_handle_single_d20() {
        DiceResult result = DiceParser.roll("1d20");
        assertEquals(1, result.rolls().length);
        assertEquals(result.rolls()[0], result.total());
    }

    @Test
    void diceparser_should_handle_complex_dice() {
        DiceResult result = DiceParser.roll("3d6+2");
        assertEquals(3, result.rolls().length);
        int expectedTotal = Arrays.stream(result.rolls()).sum() + 2;
        assertEquals(expectedTotal, result.total());
    }

    @Test
    void diceparser_should_handle_pick_lowest_dice() {
        DiceResult result = DiceParser.roll("3d6+2kl2");
        assertEquals(2, result.rolls().length);
        int expectedTotal = Arrays.stream(result.rolls()).sum() + 2;
        assertEquals(expectedTotal, result.total());
    }

    @Test
    void diceparser_should_handle_pick_highest_dice() {
        DiceResult result = DiceParser.roll("5d6+2kh3");
        assertEquals(3, result.rolls().length);
        int expectedTotal = Arrays.stream(result.rolls()).sum() + 2;
        assertEquals(expectedTotal, result.total());
    }

    @Test
    void diceparser_should_throw_on_invalid_input() {
        assertThrows(IllegalArgumentException.class, () -> {
            DiceParser.roll("abc");
        });
    }
}