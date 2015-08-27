package cis501;

import java.util.StringTokenizer;

/** Class representing a single micro-op. */
public class Uop {

    public enum Flags {ReadFlags, WriteFlags, IgnoreFlags}

    public enum BranchOp {Taken, NotTaken, NotABranch}

    public enum MemoryOp {Load, Store, NotAMemOp}

    /** Index of the uop within the macro-op */
    public final int uopId;

    /** Input 1 for ALU ops. Unused for loads, address for stores. */
    public final short srcReg1;

    /** Input 2 for ALU ops. Address for loads, value for stores. */
    public final short srcReg2;

    public final short dstReg;
    public final MemoryOp mem;

    public final long instructionAddress;
    public final BranchOp branch;
    public final long fallthroughPC;
    public final long targetAddressTakenBranch;

    public final long dataAddress;

    public final long immediate;
    public final Flags flags;
    public final String microOperation;
    public final String macroOperation;


    protected Flags FlagsOfChar(char c) {
        switch (c) {
            case 'R':
                return Flags.ReadFlags;
            case 'W':
                return Flags.WriteFlags;
            case '-':
                return Flags.IgnoreFlags;
            default:
                throw new IllegalArgumentException("Invalid flag type: " + c);
        }
    }

    protected BranchOp BranchOfChar(char c) {
        switch (c) {
            case 'T':
                return BranchOp.Taken;
            case 'N':
                return BranchOp.NotTaken;
            case '-':
                return BranchOp.NotABranch;
            default:
                throw new IllegalArgumentException("Invalid branch type: " + c);
        }
    }

    protected MemoryOp MemOfChar(char c) {
        switch (c) {
            case 'L':
                return MemoryOp.Load;
            case 'S':
                return MemoryOp.Store;
            case '-':
                return MemoryOp.NotAMemOp;
            default:
                throw new IllegalArgumentException("Invalid mem op: " + c);
        }
    }

    public Uop(String line) {
        StringTokenizer st = new StringTokenizer(line);
        assert 14 == st.countTokens();

        this.uopId = Integer.parseInt(st.nextToken());
        this.instructionAddress = Long.parseLong(st.nextToken(), 16);
        this.srcReg1 = Short.parseShort(st.nextToken());
        this.srcReg2 = Short.parseShort(st.nextToken());
        this.dstReg = Short.parseShort(st.nextToken());
        this.flags = FlagsOfChar(st.nextToken().charAt(0));
        this.branch = BranchOfChar(st.nextToken().charAt(0));
        this.mem = MemOfChar(st.nextToken().charAt(0));
        this.immediate = Long.parseLong(st.nextToken());
        this.dataAddress = Long.parseLong(st.nextToken(), 16);
        this.fallthroughPC = Long.parseLong(st.nextToken(), 16);
        this.targetAddressTakenBranch = Long.parseLong(st.nextToken(), 16);
        this.macroOperation = st.nextToken();
        this.microOperation = st.nextToken();
    }

    /** Special constructor used for testing the pipeline simulator */
    public Uop(int sr1, int sr2, int dr, MemoryOp mop) {
        this.srcReg1 = (short) sr1;
        this.srcReg2 = (short) sr2;
        this.dstReg = (short) dr;
        this.mem = mop;

        this.uopId = -1;
        this.instructionAddress = 0;
        this.flags = Flags.IgnoreFlags;
        this.branch = BranchOp.NotABranch;
        this.immediate = 0;
        this.dataAddress = 0;
        this.fallthroughPC = 0;
        this.targetAddressTakenBranch = 0;
        this.macroOperation = "mock uop";
        this.microOperation = "mock uop";
    }

}
