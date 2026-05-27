package edu.teamrocket.brunosboxing;

public enum Boxer {
    RED((byte)0), BLUE((byte)1);
     
    private final byte corner;
     
    private Boxer(byte corner){
        this.corner = corner;
    }

    public byte corner(){
        return this.corner;
    }
}
