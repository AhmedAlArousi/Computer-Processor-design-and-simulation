package Modules;
import java.util.Arrays;

public class Cache {
	private CacheBlock[] cache = new CacheBlock[32];
	
	public Cache()
	{
		for (int i=0;i<cache.length;i++) {
			cache[i]=new CacheBlock();
		}
	}
	
	public String read(int address)
	{
		System.out.println("I am the cache | READ OPERATION");
		int tag = address/32;
		int offset = address%32;
		CacheBlock b = cache[offset];
		if(b.validBit && b.tag == tag)
		{
			System.out.printf("HIT |tag=%d, offset=%d, GOT value=%s\n",tag,offset,b.value);
			//System.out.println("cache hit");
			return b.value;
		}
		//System.out.println("cache miss");
		System.out.println("Miss");
		b.tag = tag;
		b.validBit = true;
		b.value = MainMemory.read(address);
		return b.value;
	}
	
	public void write(int address,String value)
	{
		System.out.println("I am the cache; Write Operation | ");
		int tag = address/32;
		int offset = address%32;
//		CacheBlock b = cache[offset];
		cache[offset].validBit = true;
		cache[offset].tag = tag;
		cache[offset].value = value;
		System.out.printf("offset=%d, tag=%d, data=%s\n",offset,tag,cache[offset].value);
		MainMemory.write(address, value);
		
	}
	static class CacheBlock {
		public String value;
		public boolean validBit;
		public int tag;
	}
}
