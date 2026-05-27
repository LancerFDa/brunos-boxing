package edu.teamrocket.brunosboxing;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class ScoreCard {
    
    String color;
    String redCorner = "";
    String blueCorner = "";
    String[] judgeScoreCard;

    List<Round> round = new ArrayList<Round>();

    ScoreCard(String color){
        this.color = color;
    }

    void setRCorner(String redCorner) {
        this.redCorner = redCorner;
    }

    void setBCorner(String blueCorner) {
        this.blueCorner = blueCorner;
    }

    void setJudgeScoreCard(String[] scoreCard){
        this.judgeScoreCard = scoreCard;
    }

    void addRound(Round round) {
        this.round.add(round);
    }

    public void loadJudgeScoreCard(String[] judgeScoreCard){
        this.setJudgeScoreCard(judgeScoreCard);
        
        for(var roundScore : this.judgeScoreCard){
            var round = Optional.ofNullable(RoundFactory.getRound(roundScore));
            round.ifPresent(this::addRound);
        }
    }
}
