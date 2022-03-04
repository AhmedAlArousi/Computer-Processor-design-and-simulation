package Modules;

import Main.Helper;

public class MainMemory {
	private static String[] dataMemory = new String[1024];
	public static void initialize() {
		for (int i=0;i<dataMemory.length;i++) {
			dataMemory[i] = "0000000000000000";
		}
	}
	public static String read(int address)
	{
		if(address >=0 && address < 1024)
			return dataMemory[address];
		System.err.println("invalid address");
		return null;
	}
	public static void write(int address,String value)
	{
		System.out.println("I am the Data Memory | Write Operation");
		if(address >=0 && address < 1024) {
			dataMemory[address] = value;
			System.out.printf("dataMemory[%d]=%s\n",address,dataMemory[address]);
			return;
		}
		
		System.err.println("invalid address \nNo Data has been Written");
	}
	
	public static void showAll() {
		for (int i=0;i<dataMemory.length;i+=10) {
			System.out.printf("[%d: %d]= ",(i),i+9);
			for (int j=i;j<i+10&&j<dataMemory.length;j++) {
				System.out.printf("%d, ",Helper.getDecimalRepresentation(dataMemory[j]));
			}
			System.out.println();
			
		}
	}
}
