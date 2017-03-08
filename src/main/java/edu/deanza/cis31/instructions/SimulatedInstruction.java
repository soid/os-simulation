package edu.deanza.cis31.instructions;

public abstract class SimulatedInstruction {
    public int unitsNeed;

    public SimulatedInstruction(int unitsNeed) {
        this.unitsNeed = unitsNeed;
    }

    /**
     * returns whether the whole instructions has been finished
     */
    public abstract boolean cycle();
}
