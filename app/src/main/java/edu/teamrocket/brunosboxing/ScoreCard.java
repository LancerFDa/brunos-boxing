package edu.teamrocket.brunosboxing;

import java.util.List;
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
}
