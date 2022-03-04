package Stages;
import Main.Helper;

public class Execute {
	public String ALUResult,branchAddressResult,readData2;
	public int zeroFlag,RegWrite,Branch,MemRead	,MemWrite,	MemReg,rd;
	public Execute() {
		ALUResult = branchAddressResult = readData2 = "0000000000000000";
	}
	public void execute(int ALUControl	,int ALUSrc,int	RegWrite,int Branch,int MemRead	,int MemWrite,int MemReg,
			String PC,String readData1,String readData2,String readBranch ,String signExtend,int rd){
		System.out.printf("Execute Stage: \n");
		
		if(PC.length()==0) {
			this.ALUResult="";
			return;
		}
		System.out.printf("Inputs: \n");
		
		System.out.printf("WB Controls: ");
		System.out.printf("MemReg= %d ",MemReg);
		System.out.printf("RegWrite= %d\n",RegWrite);
		
		System.out.printf("MEM Controls: ");
		System.out.printf("MemRead= %d ",MemRead);
		System.out.printf("MemWrite= %d ",MemWrite);
		System.out.printf("Branch= %d\n",Branch);

		System.out.printf("EX (This Stage) Controls: ");
		System.out.printf("ALU Control= %s(%d) ", Helper.get4bits(ALUControl), ALUControl);
		System.out.printf("ALUSrc= %d\n", ALUSrc);
		
		System.out.printf("PC= %s(%d)\n",PC,Integer.parseUnsignedInt(PC, 2));
		System.out.printf("Read Branch = %s(%d)\n",readBranch,Integer.parseUnsignedInt(readBranch,2));
		System.out.printf("Read Data 1 = %s(%d)\n",readData1,Helper.getDecimalRepresentation(readData1));
		System.out.printf("Read Data 2 = %s(%d)\n",readData2,Helper.getDecimalRepresentation(readData2));
		System.out.printf("Sign Extended = %s(%d)\n",signExtend, Helper.getDecimalRepresentation(signExtend));
		System.out.printf("Write Register = %s(%d)\n",get5bits(rd),rd);

		
		
		
//		ALU1stParameter=(MemRead==1||MemWrite==1)?reg1:readData1;
		//TODO: Check EQUIVELENCE
		String ALU1stParameter = readData1;
		String ALU2ndParameter=(ALUSrc==1)?readData2:signExtend;
		switch(ALUControl){
			case 0:this.ALUResult=addOp(ALU1stParameter,ALU2ndParameter);break;
			case 1:this.ALUResult=subOp(ALU1stParameter,ALU2ndParameter);break;
			case 2:this.ALUResult=ANDOp(ALU1stParameter,ALU2ndParameter);break;
			case 3:this.ALUResult=OROp(ALU1stParameter,ALU2ndParameter);break;
			case 4:this.ALUResult=sllOp(ALU1stParameter,ALU2ndParameter);break;
			case 5:this.ALUResult=srlOp(ALU1stParameter,ALU2ndParameter);break;
			case 6:this.ALUResult=sltOp(ALU1stParameter,ALU2ndParameter);break;
			case 7:this.ALUResult=multOp(ALU1stParameter,ALU2ndParameter);break;
			case 8:this.ALUResult=sgtOp(ALU1stParameter,ALU2ndParameter);break;
			
			case 9:this.ALUResult=pass2nd(ALU1stParameter,ALU2ndParameter);break;
			
			default:System.err.println("Wrong ALUControl in execution");
		}
		this.readData2=readData2;
		this.branchAddressResult=Helper.get10bits(Helper.getDecimalRepresentation(PC)+Helper.getDecimalRepresentation(readBranch));
		//TODO//		this.branchAddressResult=readBranch;
		System.out.printf("Outputs: \n");
		
		System.out.printf("WB Controls: ");
		System.out.printf("MemReg= %d ",this.MemReg=MemReg);
		System.out.printf("RegWrite= %d\n",this.RegWrite=RegWrite);
		
		System.out.printf("MEM Controls: ");
		System.out.printf("MemRead= %d ",this.MemRead=MemRead);
		System.out.printf("MemWrite= %d ",this.MemWrite=MemWrite);
		System.out.printf("Branch= %d\n",this.Branch=Branch);
		
		System.out.printf("Branch Address Result= %s(%d)\n",this.branchAddressResult,Integer.parseUnsignedInt(this.branchAddressResult, 2));
		
		System.out.printf("Zero Flag= %d\n",this.zeroFlag);
		System.out.printf("ALU Result= %s(%d)\n",this.ALUResult,((short)Integer.parseUnsignedInt(this.ALUResult,2)));
		
		System.out.printf("Read Data 2 (Value to write in memory if SW) = %s(%d)\n",this.readData2,Helper.getDecimalRepresentation(this.readData2));
		
		System.out.printf("Write Register = %s(%d)\n",get5bits(this.rd=rd),this.rd);
		
		
		

		System.out.println();
	}
	
	
	
	private String srlOp(String readData1, String aLU2ndParameter) {
		int x=Helper.getDecimalRepresentation(readData1);
		int y=Helper.getDecimalRepresentation(aLU2ndParameter);
		int r=x>>y;
		zeroFlag=(r==0)?1:0;
		return Helper.get16bit(r);
	}
	private String sllOp(String readData1, String aLU2ndParameter) {
		int x=Helper.getDecimalRepresentation(readData1);
		int y=Helper.getDecimalRepresentation(aLU2ndParameter);
		int r=x<<y;
		zeroFlag=(r==0)?1:0;
		return Helper.get16bit(r);
	}
	private String multOp(String readData1, String aLU2ndParameter) {
		int x=Helper.getDecimalRepresentation(readData1);
		int y=Helper.getDecimalRepresentation(aLU2ndParameter);
		int r=x*y;
		zeroFlag=(r==0)?1:0;
		return Helper.get16bit(r);
	}

	private String ANDOp(String xs,String ys){
		String rs="";
		for(int i=0;i<16;i++){
			char xsc=xs.charAt(i);
			char ysc=ys.charAt(i);
			if(xsc=='1'&&ysc=='1'){
				rs+="1";
			}else{
				rs+="0";
			}
		}
		int r=Helper.getDecimalRepresentation(rs);
		zeroFlag=(r==0)?1:0;
		return rs;
	}
	private String OROp(String xs,String ys){
		String rs="";
		for(int i=0;i<16;i++){
			char xsc=xs.charAt(i);
			char ysc=ys.charAt(i);
			if(xsc=='0'&&ysc=='0'){
				rs+="0";
			}else{
				rs+="1";
			}
		}
		int r=Helper.getDecimalRepresentation(rs);
		zeroFlag=(r==0)?1:0;
		return rs;
	}
	private String addOp(String xs,String ys){
		int x=Helper.getDecimalRepresentation(xs);
		int y=Helper.getDecimalRepresentation(ys);
		int r=x+y;
		zeroFlag=(r==0)?1:0;
		return Helper.get16bit(r);
	}
	private String subOp(String xs,String ys){
		int x=Helper.getDecimalRepresentation(xs);
		int y=Helper.getDecimalRepresentation(ys);
		int r=x-y;
		zeroFlag=(r==0)?1:0;
		return Helper.get16bit(r);
	}
	private String sltOp(String xs,String ys){
		int x=Helper.getDecimalRepresentation(xs);
		int y=Helper.getDecimalRepresentation(ys);
		int r=(x<y)?1:0;
		zeroFlag=(r==0)?1:0;
		return Helper.get16bit(r);
	}
	private String sgtOp(String xs,String ys){
		int x=Helper.getDecimalRepresentation(xs);
		int y=Helper.getDecimalRepresentation(ys);
		int r=(x>y)?1:0;
		zeroFlag=(r==0)?1:0;
		return Helper.get16bit(r);
	}
	private String pass2nd(String readData1, String aLU2ndParameter) {
//		int x=Helper.getDecimalRepresentation(readData1);
//		int y=Helper.getDecimalRepresentation(aLU2ndParameter);
//		int r=x>>y;
		int r = Helper.getDecimalRepresentation(aLU2ndParameter);
		zeroFlag=(r==0)?1:0;
		return Helper.get16bit(r);
	}
	
	
	public static void tstGet16bit() {
		System.out.println(Helper.get16bit(5));
		System.out.println(Helper.get16bit(100));
		System.out.println(Helper.get16bit(1000));
		System.out.println(Helper.get16bit(10000));
		System.out.println(Helper.get16bit(-50));
		System.out.println(Helper.get16bit(-500));
		System.out.println(Helper.get16bit(-1));
		System.out.println(Helper.get16bit(-2));
		System.out.println(Helper.get16bit(99999));
	}

	
	static String get5bits(int x) {
		String r = Integer.toBinaryString(x); while(r.length()<5) {r="0"+r;}return r;
	}
}
