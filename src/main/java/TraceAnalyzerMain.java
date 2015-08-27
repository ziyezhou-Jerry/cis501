import cis501.ITraceAnalyzer;
import cis501.Uop;
import cis501.UopIterator;
import cis501.submission.TraceAnalyzer;
import cis501.submission.UopFactory;

import java.io.IOException;

public class TraceAnalyzerMain {

    public static void main(String[] args) throws IOException {
        ITraceAnalyzer<Uop> ta = new TraceAnalyzer<>();
        UopIterator uiter = new UopIterator(args[0], -1/*no limit*/, new UopFactory());
        ta.run(uiter);
        System.out.println("Avg insn size is: " + ta.avgInsnSize());
    }

}
