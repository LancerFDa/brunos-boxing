package edu.teamrocket.brunosboxing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class KnockdownTest {
    
    @Test
    public void replaceTest() {
        KnockdownRound round = new KnockdownRound("10 - 8");
        assertEquals(round.roundScore(), "10-8");
    }

    @Test
    public void roundScoreToIntTest() {
        // record pattern matching
        // https://openjdk.org/jeps/440
        Round round = new KnockdownRound("10 - 8");
        assertTrue(round instanceof KnockdownRound kr
                && kr.roundScore().equals("10-8")
                && kr.redBoxerScore() == 10
                && kr.blueBoxerScore() == 8);
    }
}