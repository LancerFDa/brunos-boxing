package edu.teamrocket.brunosboxing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PointsDeductedTest {

    @Test
    public void replaceTest() {
        PointsDeducted round = new PointsDeducted("10 - 8 ,1");
        assertEquals(round.roundScore(), "10-8,1");
        round = new PointsDeducted("1, 8 - 10");
        assertEquals(round.roundScore(), "1,8-10");
    }

    @Test
    public void roundScoreToIntBlueTest() {
        // record pattern matching
        // https://openjdk.org/jeps/440
        Round round = new PointsDeducted("10 - 8 ,1");
        assertTrue(round instanceof PointsDeducted pd 
                    && pd.roundScore().equals("10-8,1") 
                    && pd.redBoxerScore() == 10 
                    && pd.blueBoxerScore() == 8);
    }

    @Test
    public void roundScoreToIntRedTest() {
        Round round = new PointsDeducted("1, 8 - 10");
        assertTrue(round instanceof PointsDeducted pd 
                && pd.roundScore().equals("1,8-10") 
                && pd.redBoxerScore() == 8 
                && pd.blueBoxerScore() == 10);
    }
}