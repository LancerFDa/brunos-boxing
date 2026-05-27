package edu.teamrocket.brunosboxing;

public enum Boxers {
    RED((byte)0), BUE((byte)1);
     
    private final byte corner;
     
    private Boxers(byte corner){
        this.corner = corner;
    }

    public byte corner(){
        return this.corner;
    }
}
