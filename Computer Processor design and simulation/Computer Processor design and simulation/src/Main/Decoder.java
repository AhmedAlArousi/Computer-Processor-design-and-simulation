package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class Decoder {
	
	public static void main(String[] args) {
		int n=0;
		ArrayList<String> prog = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		String x;
		while ( !(x=sc.nextLine()).equals("-1")) {
			prog.add(x);n++;
		}
		for (int i=0;i<n;i++){
			System.out.println(decode(prog.get(i)));
		}
	}
	
	static String decode(String instruction) {
		StringBuilder decode = new StringBuilder();
		String opcode = instruction.substring(0,4);
		String inst = "";
		boolean R = false;
		switch(opcode) {
		case "0000": inst="ADD";R=true;break;
		case "0001": inst="SUB";R=true;break;
		case "0010": inst="MULT";R=true;break;
		case "0011": inst="AND";R=true;break;
		case "0100": inst="BGT";R=true;break;
		case "0101": inst="BNE";R=true;break;
		case "0110": inst="SLT";R=true;break;
		case "0111": inst="ADDI";break;
		case "1000": inst="ORI";break;
		case "1001": inst="SLL";break;
		case "1010": inst="SRL";break;
		case "1011": inst="LW";break;
		case "1100": inst="SW";break;
		case "1101": inst="LI";break;
		case "1110": inst="J";break;
		}
		
		if (R) {
			String rd = instruction.substring(4,8);
			String rs = instruction.substring(8,12);
			String rt = instruction.substring(12,16);
			decode.append(inst);
			decode.append(' ');
			decode.append('$');
			decode.append(Integer.parseUnsignedInt(rd, 2));
			decode.append(',');
			decode.append('$');
			decode.append(Integer.parseUnsignedInt(rs, 2));
			decode.append(',');
			decode.append('$');
			decode.append(Integer.parseUnsignedInt(rt, 2));
		}
		else if (!inst.equals("J")) {//I-Type
			String rd = instruction.substring(4,8);
			String imm = instruction.substring(8,16);
			decode.append(inst);
			decode.append(' ');
			decode.append('$');
			decode.append(Integer.parseUnsignedInt(rd, 2));
			decode.append(',');
			decode.append((byte)Integer.parseInt(imm, 2));
		}
		else {//J
			String add = instruction.substring(4,16);
			decode.append(inst);
			decode.append(' ');
			decode.append((Integer.parseUnsignedInt(add,2)));
		}
		return decode.toString();
	}
}
