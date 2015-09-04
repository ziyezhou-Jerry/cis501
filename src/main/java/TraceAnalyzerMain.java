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
		for(int i = 1;i<=5;i++)
		System.out.println("fractionOfBranchTargetsLte "+ i +" bits is: " + ta.fractionOfBranchTargetsLteNBits(i));
		for(int i = 1;i<=5;i++)
		System.out.println("fractionOfInsnsLteNBytes "+ i +" bytes is: " + ta.fractionOfInsnsLteNBytes(i));
		
		System.out.println("the mostCommonUopCategory is "+ta.mostCommonUopCategory());
    }

}
