package Stages;
import Main.Helper;
import Modules.Cache;
import Modules.PC;

public class MemoryAccess {
	private Cache cache;
	public String ALUResult,readMemData,branchAddressResult; //,readData2;
	public int MemReg,RegWrite,PCSrc,rd;
	
	public MemoryAccess(){
		cache = new Cache();
		ALUResult = readMemData = branchAddressResult = "0000000000000000";
	}
	
	public void memAccess(String ALUResult,String readData2,int MemReg,int RegWrite,
			String branchAddressResult,int zeroFlag,int memRead,int memWrite,int Branch,int rd){
		System.out.printf("Memory Acess Stage: \n");
		if(ALUResult.length()==0) {
			this.ALUResult="";
			return;
		}
		System.out.printf("Inputs: \n");
		
		System.out.printf("WB Controls: ");
		System.out.printf("MemReg= %d ",MemReg);
		System.out.printf("RegWrite= %d\n",RegWrite);
		
		System.out.printf("MEM Controls: ");
		System.out.printf("MemRead= %d ",memRead);
		System.out.printf("MemWrite= %d ",memWrite);
		System.out.printf("Branch= %d\n",Branch);
		
		System.out.printf("Branch Address Result= %s(%d)\n",this.branchAddressResult,Integer.parseUnsignedInt(this.branchAddressResult, 2));
		System.out.printf("Zero Flag= %d\n",zeroFlag);
		
		System.out.printf("ALU Result= %s(%d)\n",ALUResult,(short)Integer.parseUnsignedInt(ALUResult, 2));
		System.out.printf("Read Data 2 (Value to write in memory if SW) = %s(%d)\n",readData2,Helper.getDecimalRepresentation(readData2));
		
		System.out.printf("Write Register = %s(%d)\n",Execute.get5bits(rd),rd);
		
		System.out.printf("Outputs: \n");
		
		System.out.printf("Branch Address Result= %s(%d)\n",this.branchAddressResult=branchAddressResult,Integer.parseUnsignedInt(this.branchAddressResult, 2));
		PC.branchAddressResult = this.branchAddressResult;
		
		System.out.printf("PCSrc:%d\n",this.PCSrc = (Branch==1 & zeroFlag!=1)?1:0);
		PC.PCSrc = this.PCSrc;
		
		
		if(memRead==1){
//			this.readMemData=cache.read(Helper.getDecimalRepresentation(ALUResult));
			this.readMemData=cache.read(Integer.parseUnsignedInt(ALUResult,2));
		}
		if(memWrite==1){
//			cache.write(Helper.getDecimalRepresentation(ALUResult), readData2);
			cache.write(Integer.parseUnsignedInt(ALUResult,2), readData2);
			
		}
		
		
		System.out.printf("WB Controls: ");
		System.out.printf("MemReg= %d ",this.MemReg=MemReg);
		System.out.printf("RegWrite= %d\n",this.RegWrite=RegWrite);
		
		System.out.printf("Read Data (from memory): %s(%d)\n",this.readMemData,Helper.getDecimalRepresentation(this.readMemData));
		
		System.out.printf("ALU Result= %s(%d)\n",this.ALUResult=ALUResult,(short)Integer.parseUnsignedInt(this.ALUResult, 2));
		
		System.out.printf("Write Register = %s(%d)\n",Execute.get5bits(this.rd=rd),this.rd);
		
		
		
		System.out.println();
	}
	
	}
