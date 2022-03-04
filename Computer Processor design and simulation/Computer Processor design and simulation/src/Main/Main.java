package Main;

import Modules.MainMemory;
import Modules.RegisterFile;
import PipelineRegisters.EXMEM;
import PipelineRegisters.IDEX;
import PipelineRegisters.IFID;
import PipelineRegisters.MEMWB;
import Stages.Execute;
import Stages.InstructionDecode;
import Stages.InstructionFetch;
import Stages.MemoryAccess;
import Stages.WriteBack;

public class Main {
	static InstructionFetch ifs = new InstructionFetch();
	static InstructionDecode ids = new InstructionDecode();
	static Execute ex = new Execute();
	static MemoryAccess mem = new MemoryAccess();
	static WriteBack wb = new WriteBack();
	static IFID st1 = new IFID();
	static IDEX st2 = new IDEX();
	static EXMEM st3 = new EXMEM();
	static MEMWB st4 = new MEMWB();
	static String pc = "0000000000";
	
	static int n=0;
	static void test1_2load() {
//		LI,$7,33
//		LI,$8,127
//		LI,$9,-127
//		LI,$2,-2
//		LI,$3,5
//		ADD,$5,$7,$8
//		ADD,$4,$8,$9
//		BGT,$4,$0,$5
//		nop
//		BNE,$5,$0,$2
		ifs.addInstruction("1101011100100001", n++);
		ifs.addInstruction("1101100001111111", n++);
		ifs.addInstruction("1101100110000001", n++);
		ifs.addInstruction("1101001011111110", n++);
		ifs.addInstruction("1101001100000101", n++);
		ifs.addInstruction("0000010101111000", n++);
		ifs.addInstruction("0000010010001001", n++);
		ifs.addInstruction("0100010000000101", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0101010100000010", n++);

	}
	public static void test1load() {
//		LI $7,33
//		LI $8,127
//		LI $9,-127
//		LI $2,-2
//		LI $3,5
//		ADD $5,$7,$8
//		ADD $4,$8,$9
//		BGT $4,$0,$5
//		BNE $5,$0,$2
		ifs.addInstruction("1101011100100001", n++);
		ifs.addInstruction("1101100001111111", n++);
		ifs.addInstruction("1101100110000001", n++);
		ifs.addInstruction("1101001011111110", n++);
		ifs.addInstruction("1101001100000101", n++);
		ifs.addInstruction("0000010101111000", n++);
		ifs.addInstruction("0000010010001001", n++);
		ifs.addInstruction("0100010000000101", n++);
		ifs.addInstruction("0101010100000010", n++);
		
		
	}
	public static void test2_1load() {
//		li,$10,1000
//		add,$0,$0,$0
//		add,$0,$0,$0
//		add,$0,$0,$0
//		bne,$10,$0,$10
		ifs.addInstruction("1101101011101000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0101101000001010", n++);
	}
	public static void test2_2load() {
//		li,$10,-24			//(-24 (signed(immediate)) = 1000 (unsigned (address))
//		add,$0,$0,$0
//		add,$0,$0,$0
//		add,$0,$0,$0
//		bgt,$10,$0,$10
		ifs.addInstruction("1101101011101000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0100101000001010", n++);
	}
	public static void test2_3load() {
//		li,$10,1000
//		add,$0,$0,$0
//		add,$0,$0,$0
//		add,$0,$0,$0
//		bgt,$10,$10,$0
		ifs.addInstruction("1101101011101000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0100101010100000", n++);
	}
	public static void test3load() {
//		addi $1, 1
//		addi $2, 2
//		nop
//		nop
//		nop
//		add $3, $1, $2
		ifs.addInstruction("0111000100000001", n++);
		ifs.addInstruction("0111001000000010", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000001100010010", n++);
		
		//final expected result:
		//$1 = 1,	$2=2,	$3=3
		
	}
	static void test4load() {
//		li,$11,127
//		ori,$12,7
//		li,$13,30
//		nop
//		nop
//		nop
//		nop
//		add,$3,$11,$12
//		mult,$4,$12,$13
//		sub,$5,$11,$12
//		and,$6,$12,$13
//		slt,$7,$11,$12
//		slt,$8,$12,$13
//		slt,$9,$12,$12
		ifs.addInstruction("1101101101111111", n++);
		ifs.addInstruction("1000110000000111", n++);
		ifs.addInstruction("1101110100011110", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000001110111100", n++);
		ifs.addInstruction("0010010011001101", n++);
		ifs.addInstruction("0001010110111100", n++);
		ifs.addInstruction("0011011011001101", n++);
		ifs.addInstruction("0110011110111100", n++);
		ifs.addInstruction("0110100011001101", n++);
		ifs.addInstruction("0110100111001100", n++);
		
		
		//final expected result:
//		$3=134, $4=210, $5=120, $6=6, $7=0, $8=1, 
//		$11=127, $12=7, $13=30
		
	}
	

	static void test6load() {
//		j,7
		ifs.addInstruction("1110000000000111", n++);
	}
	static void test7_1load() {
//		li,$3,50
//		nop
//		nop
//		nop
//		sw,$3,99
		ifs.addInstruction("1101001100110010", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("1100001101100011", n++);
	}
	static void test7_2load() {
//		li,$3,50
//		addi,$1,-99
//		nop
//		nop
//		nop
//		sw,$3,99
		ifs.addInstruction("1101001100110010", n++);
		ifs.addInstruction("0111000101000001", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("1100001101100011", n++);
	}
	static void test7_3load() {
//		li,$3,50
//		addi,$1,-99
//		nop
//		nop
//		nop
//		sw,$3,99
		ifs.addInstruction("1101001100110010", n++);
		ifs.addInstruction("0111000101000001", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("1100001101100011", n++);
	}
	
	static void test8load() {
//		addi,$5,127
//		nop
//		nop
//		nop
//		addi,$5,127
//		nop
//		nop
//		nop
//		addi,$5,127
//		nop
//		nop
//		nop
//		addi,$5,127
//		nop
//		nop
//		nop
//		addi,$5,127
//		nop
//		nop
//		nop
//		addi,$5,127
//		nop
//		nop
//		nop
		ifs.addInstruction("0111010101111111", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0111010101111111", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0111010101111111", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0111010101111111", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0111010101111111", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0111010101111111", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);

	}
	static void test9load() {
//		ori,$2,112
//		addi,$3,-48
//		nop
//		nop
//		li,$1,-15
//		nop
//		nop
//		nop
//		sw,$3,60
//		sw,$2,61
//		nop
//		nop
//		lw,$7,61
//		lw,$8,60
		ifs.addInstruction("1000001001110000", n++);
		ifs.addInstruction("0111001111010000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("1101000111110001", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("1100001100111100", n++);
		ifs.addInstruction("1100001000111101", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("0000000000000000", n++);
		ifs.addInstruction("1011011100111101", n++);
		ifs.addInstruction("1011100000111100", n++);
		//Expected final Result
//		$1= -15
//		$2=112,	$3=-48,	$7=112,	$8=-48
//		mem[45]=-48,	mem[46]=112
	}
	
	public static void main(String[] args) {
		RegisterFile.initialize();
		MainMemory.initialize();

		test9load();
		
		for (int i = 0; i < n+4;i++) {//n+4; i++) {
			System.out.printf("\nCycle: %d\n\n", (i + 1));
			ifs.instFetch();// (pc, ids.Jump, mem.PCSrc, ids.jumpAddress, st3.branchAddressResult);
			ids.instDecode(st1.instr, st1.pc);
			ex.execute(st2.aluc, st2.ALUSrc, st2.RegWrite, st2.branch, st2.MemRead, st2.MemWrite, st2.MemReg, st2.pc,
					st2.readData1, st2.readData2, st2.readBranch, st2.signEx, st2.rd);
			mem.memAccess(st3.ALUResult, st3.readData2, st3.MemReg, st3.RegisterWrite, st3.branchAddressResult,
					st3.zeroFlag, st3.memRead, st3.memWrite, st3.Branch, st3.rd);
			wb.writeBack(st4.ALUResult, st4.readMemData, st4.memToReg, st4.registerWrite, st4.rd);
			updateThoseBabies(st1, st2, st3, st4);
			System.out.printf("~~~         Cycle %d Ended         ~~~\n",(i+1));
			RegisterFile.show();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		MainMemory.showAll();
		
	}

	private static void updateThoseBabies(IFID st1, IDEX st2, EXMEM st3, MEMWB st4) {
		// st1
		st1.instr = ifs.instruction; // st1.instrNew;
		st1.pc = ifs.pc;// st1.pcNew;
		// st2
		st2.aluc = ids.ALUControl;// st2.alucNew;
		st2.ALUSrc = ids.ALUSrc;// st2.ALUSrcNew;
		st2.branch = ids.Branch;// st2.branchNew;
		st2.MemRead = ids.MemRead;// st2.MemReadNew;
		st2.MemReg = ids.MemRead;// st2.MemRegNew;
		st2.MemWrite = ids.MemWrite;// st2.MemWriteNew;
		st2.pc = ids.pc;// st2.pcNew;
		st2.rd = ids.rd;// st2.rdNew;
		st2.readData1 = ids.readData1;// st2.readData1New;
		st2.readData2 = ids.readData2;// st2.readData2New;
		st2.readBranch = ids.readBranch;
		//	st2.reg1=ids.reg1;//st2.reg1New;		//TODO
		st2.RegWrite = ids.RegWrite;// st2.RegWriteNew;
		st2.signEx = ids.signExtend;// st2.signExNew;
		// st3
		st3.ALUResult = ex.ALUResult;// st3.ALUResultNew;
		st3.branchAddressResult = ex.branchAddressResult;// st3.branchAddressResultNew;
		st3.Branch = ex.Branch;// st3.BranchNew;
		st3.memRead = ex.MemRead;// st3.memReadNew;
		st3.MemReg = ex.MemRead;// st3.MemRegNew;
		st3.memWrite = ex.MemWrite;// st3.memWriteNew;
		st3.rd = ex.rd;// st3.rdNew;
		st3.readData2 = ex.readData2;// st3.readData2New;
		st3.zeroFlag = ex.zeroFlag;// st3.zeroFlagNew;
		st3.RegisterWrite = ex.RegWrite;// st3.RegisterWriteNew;
		// st4
		st4.ALUResult = mem.ALUResult;// st4.ALUResultNew;
		st4.memToReg = mem.MemReg;// st4.memToRegNew;
		st4.rd = mem.rd;// st4.rdNew;
		st4.readMemData = mem.readMemData;// st4.readMemDataNew;
		st4.registerWrite = mem.RegWrite;// st4.registerWriteNew;
	}

}
