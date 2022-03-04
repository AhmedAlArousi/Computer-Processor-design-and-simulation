package Modules;

import Main.Helper;

public class PC {
	public static String branchAddressResult, jAddress;
	public static int PCSrc,Jump;
	public static int PC;
	

	public static String calculatePC() {
		String r="";
		r=(PCSrc==1)?branchAddressResult:Helper.get10bits(PC);
		r=(Jump==1)?jAddress:r;
		PC = Integer.parseUnsignedInt(r,2);
		return r;
//		int pc=InstructionFetch.getDecimalRepresentation(r);
	}
	public static void incrementPC() {
		PC ++;
	}
	public static String getPC() {
		return Helper.get10bits(PC);
	}
}
