package Main;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;


public class Encoder {
/// method to encode assembly language to machine code	
	public static String encode(String instuction) {
		if (instuction.toLowerCase().equals("nop")){
			return "0000000000000000";
		}
		String[] terms = instuction.split(",");
		StringBuilder encode = new StringBuilder();
		String rs = "";
		String rt = "";
		String rd = "";
		String immediate = "";
		String address = "";
		boolean Itype = false;
		if (terms[0].toUpperCase().equals("ADD"))  encode.append("0000");			
		if (terms[0].toUpperCase().equals("SUB"))  encode.append("0001");
		if (terms[0].toUpperCase().equals("MULT")) encode.append("0010");
		if (terms[0].toUpperCase().equals("AND"))  encode.append("0011");
		if (terms[0].toUpperCase().equals("BGT"))  encode.append("0100");	
		if (terms[0].toUpperCase().equals("BNE"))  encode.append("0101");
		if (terms[0].toUpperCase().equals("SLT"))  encode.append("0110");
		
		if (terms[0].toUpperCase().equals("ADDI")) {encode.append("0111");Itype = true;}
		if (terms[0].toUpperCase().equals("ORI"))  {encode.append("1000");Itype = true;}
		if (terms[0].toUpperCase().equals("SLL"))  {encode.append("1001");Itype = true;}
		if (terms[0].toUpperCase().equals("SRL"))  {encode.append("1010");Itype = true;}
		if (terms[0].toUpperCase().equals("LW"))   {encode.append("1011");Itype = true;}
		if (terms[0].toUpperCase().equals("SW"))   {encode.append("1100");Itype = true;}
		if (terms[0].toUpperCase().equals("LI"))   {encode.append("1101");Itype = true;}
		
		if (terms[0].toUpperCase().equals("J")) {
			encode.append("1110");
			address = Integer.toBinaryString(Integer.parseInt(terms[1]));
			if(address.length() == 32)// if the value is negative -> the toBiaryString() method returns a 32 bit 2's complement number
				address = address.substring(20);
			else
				address = ExtendTo(address, 12);
			encode.append(address);
			
			return encode.toString();
		}
		
		if(!Itype)
		{
			rd = Integer.toBinaryString(Integer.parseInt(terms[1].substring(1)));
			rs = Integer.toBinaryString(Integer.parseInt(terms[2].substring(1)));
			rt = Integer.toBinaryString(Integer.parseInt(terms[3].substring(1)));
			encode.append(ExtendTo(rd, 4));
			encode.append(ExtendTo(rs, 4));
			encode.append(ExtendTo(rt, 4));
		}
		else
		{
			rd = Integer.toBinaryString(Integer.parseInt(terms[1].substring(1)));
			int essoImm = Integer.parseInt(terms[2]);
			immediate = Integer.toBinaryString(essoImm);
			if (immediate.length()>8)
				immediate = immediate.substring(immediate.length()-8);
			immediate = ExtendTo(immediate, 8);
			encode.append(ExtendTo(rd, 4));
			encode.append(immediate);
		}
		
		return encode.toString();
	}
	
	public static String ExtendTo(String str , int n)
	{
		while(str.length() < n)
			str="0"+str;
		return str;
	}
	
	public static void main(String[] args) {
//		System.out.println(encode("add,$5,$3,$7"));
//		System.out.println(encode("add,$15,$0,$14"));
//		System.out.println(encode("SUb,$5,$3,$7"));
//		System.out.println(encode("sUb,$15,$0,$14"));
//		System.out.println(encode("addi,$9,-15"));
//		System.out.println(encode("addi,$8,-15"));
//		System.out.println(encode("addi,$4,-1"));
//		System.out.println(encode("addi,$9,-255"));
//		System.out.println(encode("addi,$9,-256"));
//		System.out.println(encode("addi,$9,256"));
//		System.out.println(encode("addi,$9,255"));
//		System.out.println(encode("addi,$9,6"));
		int n=0;
		ArrayList<String> prog = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		String x;
		while ( !(x=sc.nextLine()).equals("-1")) {
			prog.add(x);n++;
		}
//		prog.add(encode(""));n++;
//		prog.add(encode(""));n++;
//		prog.add(encode(""));n++;
//		prog.add(encode(""));n++;
//		prog.add(encode(""));n++;
//		prog.add(encode(""));n++;
//		prog.add(encode(""));n++;
		for (int i=0;i<n;i++) {
			System.out.printf("ifs.addInstruction(\"%s\", n++);\n",encode(prog.get(i)),i);
		}
		
	}
	
	
	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		String s ;
//		while(!(s=sc.nextLine()).equals("-1")) {
//			System.out.println(encode(s));
//		}
//		sc.close();
//	}

}
