package cis501.submission;

import cis501.IUopFactory;
import cis501.Uop;

/** If you subclass cis501.Uop, update this factory to generate instances of your subclass */
public class UopFactory implements IUopFactory {

    @Override
    public Uop create(String line) {
        return new Uop(line);
    }

    @Override
    public Uop create(int sr1, int sr2, int dr, Uop.MemoryOp mop) {
        return new Uop(sr1, sr2, dr, mop);
    }

}
