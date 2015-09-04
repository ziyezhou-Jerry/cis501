package cis501.submission;

import cis501.ITraceAnalyzer;
import cis501.Uop;


public class TraceAnalyzer<T extends Uop> implements ITraceAnalyzer<T> {
	
	private String[] category = {"Loads","Stores","Unconditional branches","Conditional branches","Other"};
    private int[] num_category = new int[5]; //corresponde to the category above 
    private double total_size = 0; 
    private int num_insn = 0;
    private int num_brachmicro = 0;
    private int[] bits_array = new int[32]; // bits in the range of [1,32]
    private int[] bytes_array = new int[32]; //bytes in the range of [1,32]
    
    @Override
    public String author() {
        return "Ziye Zhou";
    }

    @Override
    public void run(Iterable<T> uiter) {
        for (Uop uop : uiter) {
            // TODO: your code here
            
             
            if(uop.uopId == 1) //the first one in a insn
            {
                //evaluate the size
                int cur_bytes = (int)(uop.fallthroughPC-uop.instructionAddress);
                total_size+= cur_bytes;
                num_insn += 1;
                
                //evaluate the bytes
                if(cur_bytes>=1 && cur_bytes<=32)
                {
                    bytes_array[cur_bytes-1]+=1;
                }
                
                
                
            }
            
            
            
             //evaluate the bits
                if(uop.targetAddressTakenBranch!=0)
                {
                    num_brachmicro+=1;
					int cur_bits = 2 + (int)Math.floor(Math.log(Math.abs((uop.instructionAddress - uop.targetAddressTakenBranch)))/Math.log(2.0));
                    if(cur_bits>=1 && cur_bits<=32)
                    {
                        bits_array[cur_bits-1]+=1;
                    }
           
                }
                
                //evaluate the type
                if(uop.mem == Uop.MemoryOp.Load)
                {
                    num_category[0]+=1;
                }
                else if(uop.mem == Uop.MemoryOp.Store)
                {
                    num_category[1]+=1;
                }
                else if(uop.targetAddressTakenBranch!=0 && uop.flags==Uop.Flags.IgnoreFlags)
                {
                    num_category[2]+=1;
                }
                 else if(uop.targetAddressTakenBranch!=0 && uop.flags==Uop.Flags.ReadFlags)
                {
                    num_category[3]+=1;
                }
                 else
                 {
                     num_category[4]+=1;
                 }
                
                  
                
                
		}
	}
	
    @Override
    public double avgInsnSize()
    {
        return total_size/num_insn;
    }

    @Override
    public double fractionOfBranchTargetsLteNBits(int bits)
    {
        double subtotal = 0;
        for(int i = 0;i<bits;i++)
        {
            subtotal+= bits_array[i];
        }
        
        return subtotal/(double)num_brachmicro;
    }

    @Override
    public double fractionOfInsnsLteNBytes(int bytes) 
    {
        double subtotal = 0;
        for(int i = 0;i<bytes;i++)
        {
            subtotal+= bytes_array[i];
        }
        
        return subtotal/(double)num_insn;
    }

    @Override
    public String mostCommonUopCategory()
    {
        
        int index = 0;
        int max_num = num_category[0];
        
        for(int i = 1;i<5;i++)
        {
            if(num_category[i]>max_num)
            {
                index = i;
                max_num = num_category[i];
            }
        }
        
        return category[index];
    }

}
