package Stages;
import Main.Helper;
import Modules.PC;
import Modules.RegisterFile;

public class InstructionDecode {
//	private String[] registerFile;
	public int opCode,rs,rt,rd,immediate; //,jAddress;
	public int ALUControl	,ALUSrc,	RegWrite,	Branch,	Jump	,MemRead	,MemWrite,	MemReg,	RegRead;
	public String readData1,readData2,readBranch,signExtend,pc;
	//**new
	public String jumpAddress="";
	public InstructionDecode(){
		readData1 = readData2 = signExtend;// = "0000000000000000";
		readBranch = pc ;//= "0000000000";
//		registerFile=new String[16];
		
	}
	
	public void instDecode(String instruction,String PCAddress){
		System.out.printf("Decode Stage: \n");
		if(instruction.length()==0||PCAddress.length()==0) {
			this.pc="";
			return;
		}
		System.out.printf("Inputs: \n");
		System.out.printf("Instruction: %s\n",instruction);
		System.out.printf("PC: %s(%d)\n",PCAddress,Integer.parseUnsignedInt(PCAddress,2));
		
		System.out.printf("Outputs: \n");
		
		
		//Decoding the instruction
		String opCode=instruction.substring(0,4);
		String rd=instruction.substring(4,8);
		String rs=instruction.substring(8,12);
		String rt=instruction.substring(12,16);
		String immediate=instruction.substring(8);
		String jAddress=instruction.substring(6);  //4);
		
		this.opCode=Integer.parseUnsignedInt(opCode,2);
		this.rs=Integer.parseUnsignedInt(rs,2);
		this.rt=Integer.parseUnsignedInt(rt,2);
		this.rd=Integer.parseUnsignedInt(rd,2);
		this.immediate=Helper.getDecimalRepresentation(immediate);
//		this.jAddress=getDecimalRepresentation(jAddress);
		System.out.printf("Jump Address: %s(%d)\n",this.jumpAddress=jAddress , Integer.parseUnsignedInt(this.jumpAddress, 2));
		PC.jAddress = this.jumpAddress;
		//TODO://st2.reg1New=this.reg1=registerFile[1];

		
		//Contol Unit
		contUnit();		//printing inside
		
		//Passing the PC
		setPC(PCAddress);
		
		//Register File:
		int ReadRegister1 = (MemRead==1|MemWrite==1)? (1): (RegRead==1)?this.rs:this.rd;
		int ReadRegister2 = (MemRead==1|MemWrite==1)?this.rd:this.rt;
		int RR3 = this.rd;
		RegisterFile.setReadRegister1(ReadRegister1);
		RegisterFile.setReadRegister2(ReadRegister2);
		RegisterFile.setRR3(RR3);
//		System.out.println(ReadRegister1);
//		System.out.println(ReadRegister2);
		System.out.printf("Read Data 1= %s(%d)\n",this.readData1=RegisterFile.getReadData1(),Helper.getDecimalRepresentation(this.readData1));
		System.out.printf("Read Data 2= %s(%d)\n",this.readData2=RegisterFile.getReadData2(),Helper.getDecimalRepresentation(this.readData2));
		System.out.printf("Read Branch= %s(%d)\n",this.readBranch=RegisterFile.getRR3().substring(6),Helper.getDecimalRepresentation(this.readBranch));
		
		//Sign Extend:
		signExtend();	//printing inside
		
		//Passing rd;
		System.out.printf("Write Register(rd) = %s(%d)\n",rd,Integer.parseUnsignedInt(rd,2));
		
				
		System.out.println();
	}
	
	public void signExtend(){
		System.out.printf("Sign Extended= %s (%d)\n",this.signExtend= Helper.get16bit(this.immediate),this.immediate);
	}
	static void tstSignExtend() {
		InstructionDecode d = new InstructionDecode();
		d.immediate = -5;
		System.out.println(d.immediate);
		System.out.println(d.signExtend);
		d.signExtend();
		System.out.println(d.immediate);
		System.out.println(d.signExtend);
	}
	public void contUnit(){
		switch(opCode){
			case 0:ALUControl=0;ALUSrc=1;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=1;break;//Add
			case 1:ALUControl=1;ALUSrc=1;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=1;break;//Sub
			case 2:ALUControl=7;ALUSrc=1;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=1;break;//Mult
			case 3:ALUControl=2;ALUSrc=1;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=1;break;//And
			case 4:ALUControl=8;ALUSrc=1;RegWrite=0;Branch=1;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=1;break;//BGT
			case 5:ALUControl=1;ALUSrc=1;RegWrite=0;Branch=1;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=1;break;//BNE
			case 6:ALUControl=6;ALUSrc=1;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=1;break;//Slt
			case 7:ALUControl=0;ALUSrc=0;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=0;break;//Addi
			case 8:ALUControl=3;ALUSrc=0;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=0;break;//Ori
			case 9:ALUControl=4;ALUSrc=1;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=0;break;//Sll
			case 10:ALUControl=5;ALUSrc=1;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=0;break;//Srl
			case 11:ALUControl=0;ALUSrc=0;RegWrite=1;Branch=0;Jump=0;MemRead=1;MemWrite=0;MemReg=1;RegRead=0;break;//Lw
			case 12:ALUControl=0;ALUSrc=0;RegWrite=0;Branch=0;Jump=0;MemRead=0;MemWrite=1;MemReg=0;RegRead=0;break;//Sw
			//TODO:
//			case 13:ALUControl=0;ALUSrc=0;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=1;break;//Li
			case 13:ALUControl=9;ALUSrc=0;RegWrite=1;Branch=0;Jump=0;MemRead=0;MemWrite=0;MemReg=0;RegRead=0;break;//Li
			case 14:ALUControl=0;ALUSrc=0;RegWrite=0;Branch=0;Jump=1;MemRead=0;MemWrite=0;MemReg=0;RegRead=0;break;//J
			
			
			default:System.err.println("opCode is incorrect in Instruction decode");
		}
		
		PC.Jump = Jump;
		
		System.out.printf("Jump= %d\n",Jump);
		System.out.printf("ID Controls: ");
		System.out.printf("RegRead=%d\n",RegRead);
		
		System.out.printf("EX Controls: ");
		System.out.printf("ALU Control= %s(%d) ", Helper.get4bits(ALUControl), ALUControl);
		System.out.printf("ALUSrc= %d\n", ALUSrc);
		
		System.out.printf("MEM Controls: ");
		System.out.printf("MemRead= %d ",MemRead);
		System.out.printf("MemWrite= %d ",MemWrite);
		System.out.printf("Branch= %d\n",Branch);
		
		System.out.printf("WB Controls: ");
		System.out.printf("MemReg= %d ",MemReg);
		System.out.printf("RegWrite= %d\n",RegWrite);
	}
	
	private void setPC(String pc){
		this.pc=pc;
	}
	
//	public void writeBackData(String data,int rd){
//		if(RegWrite==1){
//			registerFile[rd]=data;
//		}
//	}
//	
//	public void setRegister(int number,String data){
//		registerFile[number]=data;
//	}
//	
//	public String getData(int number){
//		return registerFile[number];
//	}
	
	
	
	
	
}
