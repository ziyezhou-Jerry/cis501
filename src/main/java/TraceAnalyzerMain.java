import cis501.ITraceAnalyzer;
import cis501.Uop;
import cis501.UopIterator;
import cis501.submission.MyProperties;
import cis501.submission.TraceAnalyzer;
import cis501.submission.UopFactory;

import java.io.IOException;

public class TraceAnalyzerMain {

    public static void main(String[] args) throws IOException {
        ITraceAnalyzer<Uop> ta = new TraceAnalyzer<>();
        UopIterator uiter = new UopIterator(MyProperties.traceFile(), MyProperties.uopLimit(), new UopFactory());
        ta.run(uiter);
        System.out.println("Avg insn size is: " + ta.avgInsnSize());
    }

}
