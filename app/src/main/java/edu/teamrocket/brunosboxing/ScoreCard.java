package edu.teamrocket.brunosboxing;

import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.ArrayList;

public class ScoreCard {
    
    String color;
    String redCorner = "";
    String blueCorner = "";
    String[] judgeScoreCard;
    byte redBoxerFinalScore = 0;
    byte blueBoxerFinalScore = 0;

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

    public List<Round> getRounds() {
        return Collections.unmodifiableList(this.round);
    }

    public void loadJudgeScoreCard(String[] judgeScoreCard){
        this.setJudgeScoreCard(judgeScoreCard);
        
        for(var roundScore : this.judgeScoreCard){
            var round = Optional.ofNullable(RoundFactory.getRound(roundScore));
            round.ifPresent(this::addRound);
        }
    }

    public int getNumRounds(){
        return this.round.size();
    }

    public byte getRedBoxerFinalScore() {
        if (this.redBoxerFinalScore == 0) {
            this.redBoxerFinalScore =
                this.getRounds()
                    .stream()
                    .map(Round::redBoxerScore)
                    .map(Byte::intValue)
                    .reduce(0, Integer::sum)
                    .byteValue();
        }
        return this.redBoxerFinalScore;
    }

    public int getBlueBoxerFinalScore() {
        if (this.blueBoxerFinalScore == 0) {
            this.blueBoxerFinalScore =
                this.getRounds()
                    .stream()
                    .map(Round::blueBoxerScore)
                    .map(Byte::intValue)
                    .reduce(0, Integer::sum)
                    .byteValue();
        }
        return this.blueBoxerFinalScore;
    }
        
        public String viewRounds(){
        StringBuilder roundsView = new StringBuilder();
        roundsView.append("""
            \tRound \t Score \t Round \t Score \t Round
            \tScore \t Total \t       \t Total \t Score""");

        byte roundNum = 1;
        
        byte redBoxerScoreTotal = 0;
        byte blueBoxerScoreTotal = 0;
        // la definición de this.rounds esta demasiado lejos
        // para inferir tipo mediante var y que la programadora 
        // deduzca de qué tipo es la variable local round del loop.
        // https://openjdk.org/projects/amber/guides/lvti-style-guide
        for(Round round : this.round) {
            roundsView.append("""
                \n\t%s\t %s\t  %s\t %s\t %s"""
                .formatted(
                    round.redBoxerScore(),
                    redBoxerScoreTotal += round.redBoxerScore(),
                    roundNum++,
                    blueBoxerScoreTotal += round.blueBoxerScore(),
                    round.blueBoxerScore()
                ));
        }
        return roundsView.toString();
        }

        @Override
    public String toString() {
        // Programmer's Guide to Text Blocks
        // https://docs.oracle.com/en/java/javase/15/text-blocks/index.html
        return """
                
                \t\t\t   %s
                \t\t%s\t%s
                \t\t\t%s rounds
                %s
                \t   FINAL SCORE: %s - %s FINAL SCORE"""
                .formatted(
                    this.color,
                    this.blueCorner,
                    this.redCorner,
                    this.getNumRounds(),
                    this.viewRounds(),
                    this.getRedBoxerFinalScore(),
                    this.getBlueBoxerFinalScore()
                );
    }
    
}
