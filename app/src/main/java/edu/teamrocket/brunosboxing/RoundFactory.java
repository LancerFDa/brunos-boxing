package edu.teamrocket.brunosboxing;

public class RoundFactory {
    
    public static Round getRound(String roundScore){
        
        return switch (roundScore){
            case null -> null;
            case "10 - 9", "9 - 10" -> new RegularRound(roundScore);
            default -> null;
        };
    }
}
