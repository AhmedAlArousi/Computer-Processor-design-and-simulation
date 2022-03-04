package Stages;
import Main.Helper;
import Modules.RegisterFile;

public class WriteBack {
//	public String writeData;
//	public int rd,registerWrite;
	
	public void writeBack(String ALUResult,String readMemData,int memToReg,int registerWrite,int rd){
		System.out.printf("Write Back Stage: \n");
		if(ALUResult.length()==0)return;
		
		System.out.printf("Inputs: \n");
		
		System.out.printf("WB Controls: ");
		System.out.printf("RegWrite= %d\n",registerWrite);
		System.out.printf("MemReg= %d ",memToReg);
		
		System.out.printf("ALU Result= %s(%d)\n",ALUResult,(short)Integer.parseUnsignedInt(ALUResult, 2));
		System.out.printf("Read Data (from memory): %s(%d)\n",readMemData,Helper.getDecimalRepresentation(readMemData));
		
		System.out.printf("Write Register = %s(%d)\n",Execute.get5bits(rd),rd);
		
		
		
		System.out.printf("Outputs: \n");
	    String writeData = memToReg==0?ALUResult:readMemData;
	    RegisterFile.setWriteFlag(registerWrite);
	    RegisterFile.setWriteRegister(rd);
	    RegisterFile.setWriteData(writeData);
	    RegisterFile.write();

	    System.out.println();
	}
}
