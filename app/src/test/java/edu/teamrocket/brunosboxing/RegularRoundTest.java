package edu.teamrocket.brunosboxing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RegularRoundTest {

    @Test
    public void replaceTest() {
        RegularRound round = new RegularRound("10 - 9");
        assertEquals(round.roundScore(), "10-9");
    }

    @Test
    public void roundScoreToIntTest() {
        // record pattern matching
        // https://openjdk.org/jeps/440
        Round round = new RegularRound("10 - 9");
        assertTrue(round instanceof RegularRound rr
                && rr.roundScore().equals("10-9")
                && rr.redBoxerScore() == 10
                && rr.blueBoxerScore() == 9);
    }

    @Test // este para el alumnado
    public void boxerRoundScoreToIntTest() {
        Round round = new RegularRound("10 - 9");
        assertEquals(10, round.redBoxerScore());
        assertEquals(9, round.blueBoxerScore());
    }
}