package edu.teamrocket.brunosboxing;

record PointsDeducted(String roundScore, byte redBoxerScore, byte blueBoxerScore) implements Round {
    
    PointsDeducted{
        if (roundScore == null) throw new IllegalStateException("roundScore null");
    }

    PointsDeducted(String roundScore){
        this(
            roundScore.replaceAll("\\s", ""),
            parseBoxerRoundScore(roundScore, Boxer.RED),
            parseBoxerRoundScore(roundScore, Boxer.BLUE)
        );
    }

    private static byte parseBoxerRoundScore(String roundScore, Boxer boxer){
        String[] score = roundScore.replaceAll("\\s", "").split("-", 2);
        String redBoxerScore = score[Boxer.RED.corner()];
        String blueBoxerScore = score[Boxer.BLUE.corner()];

        return switch (boxer) {
            case RED -> redBoxerScore.contains(",") ? parseComa(redBoxerScore) : Byte.parseByte(redBoxerScore);
            case BLUE -> blueBoxerScore.contains(",") ? parseComa(blueBoxerScore) : Byte.parseByte(blueBoxerScore);
        };
    }

    private static byte parseComa(String score){
        return Byte.parseByte(score.substring(score.indexOf(",") + 1));
    }
}
