package Modules;
import java.util.Arrays;

import Main.Helper;

public class RegisterFile {
	private static String[] registerFile;
	private static int writeFlag;
	private static String ReadData1, ReadData2,ReadBranch, WriteData;
	private static int ReadRegister1, ReadRegister2, RR3, WriteRegister;
//	private static int WriteRegister;
	public static void initialize() {
		registerFile= new String[16];
		
		for (int i=0;i<registerFile.length;i++) {
			registerFile[i]="0000000000000000";
		}
	}
	
//	public RegisterFile() {
//		this.registerFile = new String[16];
//	}

	public static String getReadData1() {
		ReadData1 = registerFile[ReadRegister1];
		return ReadData1 ;
	}

	public static String getReadData2() {
		ReadData2 = registerFile[ReadRegister2];
		return ReadData2 ;
	}
	public static String getRR3() {
		ReadBranch = registerFile[RR3];
		return ReadBranch ;
	}
//		public void operate() {
//			this.ReadData1=registers[ReadRegister1];
//			this.ReadData2=registers[ReadRegister2];
//		}

	public static void setReadRegister1(int RR1) {
		ReadRegister1 = RR1;
	}

	public static void setReadRegister2(int RR2) {
		ReadRegister2 = RR2;
	}
	
	public static void setRR3(int rr3) {
		RR3 = rr3;
	}

	public static void setWriteRegister(int WR) {
		WriteRegister = WR;
	}

	public static void setWriteData(String WD) {
		WriteData = WD;
	}

	public static void setWriteFlag(int WF) {
		writeFlag = WF;
	}

	public static void write() {
		System.out.println("RegFile Before Writing: " + Arrays.toString(registerFile));
		if (writeFlag==1) {
			if (WriteRegister==0)
				return;
			registerFile[WriteRegister] = WriteData;
		}
		System.out.println("RegFile After  Writing: " + Arrays.toString(registerFile));
	}

	
	

	public static void show() {
		System.out.printf("Register File:\n");
//		System.out.printf("$0 (Hard-Wired Zero):%s (%d)\n",registerFile[0],(short)Integer.parseUnsignedInt(registerFile[0], 2));
//		System.out.printf("$1 (Designated to contain the base address for LW&SW): %s(%d)\n",registerFile[1],(short)Integer.parseUnsignedInt(registerFile[1], 2));
//		for (int i=2;i<16;i++) {
		for (int i=0;i<16;i++) {
			System.out.printf("$%d: %s(%d)\t",i,registerFile[i],(short)Integer.parseUnsignedInt(registerFile[i++], 2));	if(i>=16) {System.out.println(); break;}
			System.out.printf("$%d: %s(%d)\t",i,registerFile[i],(short)Integer.parseUnsignedInt(registerFile[i++], 2));	if(i>=16) {System.out.println(); break;}
			System.out.printf("$%d: %s(%d)\t",i,registerFile[i],(short)Integer.parseUnsignedInt(registerFile[i++], 2));	if(i>=16) {System.out.println(); break;}
			System.out.printf("$%d: %s(%d)\n",i,registerFile[i],(short)Integer.parseUnsignedInt(registerFile[i], 2));
//			System.out.printf("$%d: %s(%d)\n",i,registerFile[i],registerFile[i].length()>0?Integer.parseUnsignedInt(registerFile[i], 2):0);
		}
	}
	public static void main(String[] args) {
		initialize();
		String k;
//		k = Helper.get16bit(-24);
		k="1111111111101000";
//		k="1000000000000000";
		registerFile[0]=k;
		show();
	}
}
