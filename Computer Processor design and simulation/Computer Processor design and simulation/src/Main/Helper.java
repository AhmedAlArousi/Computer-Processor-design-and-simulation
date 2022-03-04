package Main;



public class Helper {

	public static int getDecimalRepresentationFET(String binary) {//InstructionFetch Class
		int result=0;
		if (binary.length()<1) return -1;
		if(binary.charAt(0)=='1'){
			char[] onesComplement=new char[binary.length()];
			for(int i=0;i<binary.length();i++){
				char b=(binary.charAt(i)=='1')?'0':'1';
				onesComplement[i]=b;
			}
			for(int i=onesComplement.length-1;i>=0;i--){
				if(onesComplement[i]=='1'){
					onesComplement[i]='0';
				}else{
					onesComplement[i]='1';
					break;
				}
			}
			for(int i=onesComplement.length-1;i>=0;i--){
				if(onesComplement[i]=='1'){
					result+=Math.pow(2,binary.length()-1-i);
				}
			}
			result*=-1;
		}else{
			for(int i=binary.length()-1;i>=0;i--){
				if(binary.charAt(i)=='1'){
					result+=Math.pow(2,binary.length()-1-i);
				}
			}
		}
		return result;
	}
	
	
	
	public static int getDecimalRepresentation(String binary) {//INSTRUCTION DECODE
		int result=0;
		//immediate and address can be negative
		if(binary.length()>=8&&binary.charAt(0)=='1'){
			char[] onesComplement=new char[binary.length()];
			for(int i=0;i<binary.length();i++){
				char b=(binary.charAt(i)=='1')?'0':'1';
				onesComplement[i]=b;
			}
			for(int i=onesComplement.length-1;i>=0;i--){
				if(onesComplement[i]=='1'){
					onesComplement[i]='0';
				}else{
					onesComplement[i]='1';
					break;
				}
			}
			for(int i=onesComplement.length-1;i>=0;i--){
				if(onesComplement[i]=='1'){
					result+=Math.pow(2,binary.length()-1-i);
				}
			}
			result*=-1;
		}else{
			for(int i=binary.length()-1;i>=0;i--){
				if(binary.charAt(i)=='1'){
					result+=Math.pow(2,binary.length()-1-i);
				}
			}
		}
		return result;
	}
	
	public static int getDecimalRepresentationEXEC(String binary) {//EXECUTE CLASS
		int result=0;
		if(binary.charAt(0)=='1'){
			char[] onesComplement=new char[binary.length()];
			for(int i=0;i<binary.length();i++){
				char b=(binary.charAt(i)=='1')?'0':'1';
				onesComplement[i]=b;
			}
			for(int i=onesComplement.length-1;i>=0;i--){
				if(onesComplement[i]=='1'){
					onesComplement[i]='0';
				}else{
					onesComplement[i]='1';
					break;
				}
			}
			for(int i=onesComplement.length-1;i>=0;i--){
				if(onesComplement[i]=='1'){
					result+=Math.pow(2,binary.length()-1-i);
				}
			}
			result*=-1;
		}else{
			for(int i=binary.length()-1;i>=0;i--){
				if(binary.charAt(i)=='1'){
					result+=Math.pow(2,binary.length()-1-i);
				}
			}
		}
		return result;
	}

	private static int getDecimalRepresentationMEM(String binary) {//Memory Access Class
		int result=0;
		if(binary.charAt(0)=='1'){
			char[] onesComplement=new char[binary.length()];
			for(int i=0;i<binary.length();i++){
				char b=(binary.charAt(i)=='1')?'0':'1';
				onesComplement[i]=b;
			}
			for(int i=onesComplement.length-1;i>=0;i--){
				if(onesComplement[i]=='1'){
					onesComplement[i]='0';
				}else{
					onesComplement[i]='1';
					break;
				}
			}
			for(int i=onesComplement.length-1;i>=0;i--){
				if(onesComplement[i]=='1'){
					result+=Math.pow(2,binary.length()-1-i);
				}
			}
			result*=-1;
		}else{
			for(int i=binary.length()-1;i>=0;i--){
				if(binary.charAt(i)=='1'){
					result+=Math.pow(2,binary.length()-1-i);
				}
			}
		}
		return result;
	}

	
	public static String get10bits(int x) {
		String r = Integer.toBinaryString(x);
		while(r.length()<10) {
			r = "0"+r;
		}
		if (r.length()>10) {
//			while(true);
			return r.substring(r.length()-10);
		}
		return r;
	}
//	private static String get10bitEXECUTE(int i) {
//		String r=Integer.toBinaryString(i);
//		while (r.length()<10) {
//			r= "0"+r;
//		}
//		if (r.length()>10) {
//			return r.substring(r.length()-10);
//		}
//		return r;
//	}
//	public static void tstGet10bit() {
//		System.out.println(get10bit(5));
//		System.out.println(get10bit(100));
//		System.out.println(get10bit(1000));
//		System.out.println(get10bit(10000));
//		System.out.println(get10bit(-50));
//		System.out.println(get10bit(-500));
//		System.out.println(get10bit(-1));
//		System.out.println(get10bit(-2));
//		System.out.println(get10bit(99999));
//	}

	public static String get16bit(int i) {
		String r=Integer.toBinaryString(i);
		int a=16-r.length();
		String rr="";
		for(int j=0;j<a;j++){
			if(i>=0)
				rr+='0';
			else
				rr+='1';
		}
		if (a<0) {
			return r.substring(r.length()-16);
		}
		return rr+r;
	}
	public static String get4bits(int x) {
		String r = Integer.toBinaryString(x);
		while (r.length()<4) {
			r= "0"+r;
		}
		return r;
	}
	static void tstGet4bits() {
		for (int i=0;i<20;i++) {
			System.out.println(i+"-->"+get4bits(i));
		}
	}
}
