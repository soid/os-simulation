package edu.deanza.cis31.instructions;

public class CPUBurstInstruction extends SimulatedInstruction {

    public CPUBurstInstruction(int unitsNeed) {
        super(unitsNeed);
    }

    public boolean cycle() {
        unitsNeed--;
        return unitsNeed<=0;
    }

}
