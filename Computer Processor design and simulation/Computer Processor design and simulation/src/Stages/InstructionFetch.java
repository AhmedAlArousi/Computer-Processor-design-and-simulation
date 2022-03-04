package Stages;
import Modules.PC;

public class InstructionFetch {
	public String pc,instruction;
	private String[] instructions;
	
	public InstructionFetch(){
		instructions=new String[1024];
		for (int i=0;i<instructions.length;i++)
			instructions[i]="0000000000000000"; //NOP ; add $0,$0,$0
	}
	
	public void instFetch( ) {//String PCAddress,int Jump,int PCSrc,String jAddress,String branchAddressResult){
		System.out.printf("Fetch Stage:\n");
		String PCAddress = PC.calculatePC();	//Current pc to fetch the instruction
		PC.incrementPC();				//increment PC 
		if(PCAddress.length()==0)return;
		int pc=Integer.parseUnsignedInt(PCAddress,2);
		System.out.printf("*Inputs:\n");
		System.out.printf("PC= %s(%d)\n",PCAddress,pc);
//		System.out.printf("PCSrc= %d\n",PCSrc);
//		System.out.printf("Branch Address = %s(%d)\n",branchAddressResult,getDecimalRepresentation(branchAddressResult));
//		System.out.printf("Jump= %d\n",Jump);
//		System.out.printf("Jump Address = %s(%d)\n",jAddress,getDecimalRepresentation(jAddress));
		
		
		
		if(instructions.length<=pc){
			//error message
			//System.err.println("No Instruction at this address");
			this.pc="";
			this.instruction="";
		}else {
			this.instruction=instructions[pc];
			this.pc=PC.getPC();	//NEXT PC ; Output
		}
		System.out.printf("Outputs: \n");
		System.out.printf("Instruction: %s\n",this.instruction);
		System.out.printf("Next PC: %s(%d)\n",this.pc,Integer.parseUnsignedInt(this.pc,2));
		//updating pipeline regs
//		st1.instrNew=this.instruction;
//		st1.pcNew=this.pc;
		System.out.println();
	}
	
//	public String progCount(String PCAddress){
//		return get32bit(getDecimalRepresentation(PCAddress)+4);
//	}
//	
	public void addInstruction(String data,int i){
		instructions[i]=data;
	}
}
