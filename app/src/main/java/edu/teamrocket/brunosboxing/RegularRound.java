package edu.teamrocket.brunosboxing;

record RegularRound(String roundScore, byte redBoxerScore, byte blueBoxerScore) implements Round{
    
    RegularRound {
        if (roundScore == null) throw new IllegalArgumentException("roundScore null");
    }

    RegularRound(String roundScore){
        this (
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
