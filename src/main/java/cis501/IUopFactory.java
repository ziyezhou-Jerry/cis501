package cis501;

public interface IUopFactory {

    /** Create a Uop from a line of the trace file. */
    public Uop create(String line);

    /** Used for testing the pipeline simulator */
    public Uop create(int sr1, int sr2, int dr, Uop.MemoryOp mop);

}
