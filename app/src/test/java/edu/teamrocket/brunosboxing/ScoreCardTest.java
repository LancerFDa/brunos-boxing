package edu.teamrocket.brunosboxing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreCardTest {

    private ScoreCard card;

    private String[] whiteScoreCard =  {"9 - 10", 
                                        "9 - 10", 
                                        "9 - 10", 
                                        "9 - 10",
                                        "10 - 9",
                                        "10 - 9",
                                        "10 - 9", 
                                        "9 - 10", 
                                        "10 - 9", 
                                        "9 - 10"};

    private String[] pinkScoreCard = {"9 - 10", 
                                        "9 - 10", 
                                        "9 - 10", 
                                        "1, 8 - 10", // referee point deduction
                                        "10 - 8",    // knockdown
                                        "10 - 8 ,1", // referee point deduction
                                        "10 - 9", 
                                        "9 - 10", 
                                        "10 - 9", 
                                        "10 - 8"}; // knockdown

    @BeforeEach
    public void setup() {
        card = new ScoreCard("white");
    }
    
    @Test
    public void loadJudgeScoreCardRoundFactoryRegularTest() {

        card.loadJudgeScoreCard(whiteScoreCard);
        assertEquals(10, card.getNumRounds());
        assertTrue(card.getRounds()
                        .stream()
                        .allMatch(r -> r instanceof RegularRound));
    }

    @Test
    public void loadJudgeScoreCardRoundFactoryNullTest() {
        card.loadJudgeScoreCard(new String[]{null, null});
        assertEquals(0, card.getNumRounds());
    }

    @Test
    public void loadJudgeScoreCardRoundFactoryPointsDeductedTest() {
        // ejemplos de pattern matching for instanceof
        // https://docs.oracle.com/en/java/javase/17/language/pattern-matching-instanceof.html
        card.loadJudgeScoreCard(pinkScoreCard);
        assertEquals(10, card.getNumRounds());
        assertEquals(2, card.getRounds()
                                .stream()
                                .filter(r -> r instanceof PointsDeducted pd 
                                        && pd.roundScore().contains(","))
                                .count());
    }


    // gradle test --tests "ScoreCardTest"
    // gradle test --tests edu.badpals.brunosbox.ScoreCardTest
    @Test
    public void loadJudgeScoreCardRoundFactoryKnockDownTest() {
        // ejemplos de pattern matching for instanceof
        // https://docs.oracle.com/en/java/javase/17/language/pattern-matching-instanceof.html
        card.loadJudgeScoreCard(pinkScoreCard);
        assertEquals(10, card.getNumRounds());
        assertEquals(2, card.getRounds()
                                .stream()
                                .filter(r -> r instanceof KnockdownRound kd 
                                        && (kd.redBoxerScore() == 8 || kd.blueBoxerScore() == 8))
                                .count());
    }

    @Test
    public void getBoxerFinalScoreTest() {
        assertEquals(0, card.getRedBoxerFinalScore());
        assertEquals(0, card.getBlueBoxerFinalScore());
        card.loadJudgeScoreCard(whiteScoreCard);
        assertEquals(94, card.getRedBoxerFinalScore());
        assertEquals(96, card.getBlueBoxerFinalScore());
    }
}
