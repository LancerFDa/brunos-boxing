package edu.teamrocket.brunosboxing;



record  KnockdownRound (String roundScore, byte redBoxerScore, byte blueBoxerScore) implements Round{

    KnockdownRound{
        if (roundScore == null) throw new IllegalStateException("roundScore null");
    }

    KnockdownRound(String roundScore){
        this(
            roundScore.replaceAll("\\s", ""),
            parseBoxerRoundScore(roundScore, Boxer.RED),
            parseBoxerRoundScore(roundScore, Boxer.BLUE)
        );
    }

    private static Byte parseBoxerRoundScore(String roundScore, Boxer boxer){
        String[] score = roundScore.replaceAll("\\s", "").split("-", 2);
        return Byte.parseByte(score[boxer.corner()]);
    }
}