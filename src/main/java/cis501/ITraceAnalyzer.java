package cis501;

public interface ITraceAnalyzer<T extends Uop> {

    /** @return the name of the author of this assignment. */
    public String author();

    /**
     * Run over the trace of uops and compute various summary data. The summary data can be queried
     * via the other methods in this interface.
     */
    public void run(Iterable<T> ui);

    /**
     * @return the average size of the insns in the trace, in bytes.
     */
    public double avgInsnSize();

    /**
     * Returns the fraction of all branch targets that can be encoded with <= the given number of
     * bits. E.g., with 5 bits, return the fraction of all branch targets that can be encoded with 5
     * or fewer bits.
     *
     * @param bits a value in the range [1,32]
     * @return a fraction in the range [0,1.0]
     */
    public double fractionOfBranchTargetsLteNBits(int bits);

    /**
     * Returns the fraction of all insns that are <= the given number of bytes. E.g., with 5 bytes,
     * return the fraction of all insns that are <= 5 bytes in size.
     *
     * @param bytes a value in the range [1,32]
     * @return a fraction in the range [0,1.0]
     */
    public double fractionOfInsnsLteNBytes(int bytes);

    /**
     * Returns the most common category of uops found in the trace, as one of the strings "loads",
     * "stores", "unconditionalbranches", "conditionalbranches" or "other".
     */
    public String mostCommonUopCategory();
}
