
public  class/*class/abstract class/interface*/ BookContent implements Hashable /*implements/extends*/{
	private /*static/final*/ String type;
	private /*static/final*/ int prev;
	
	/*Declare/Implement the missing methods here*/
	
	public int getPrev(){
		return prev;
	}
	
	public BookContent getPrevContent(){
		if(prev < 0 || prev >= Book.addrSize){
			System.out.println("Prev value was set out of bounds: "+prev);
			return null;
		}
		return Book.nodeAddrs[prev];
	}
	
	public String getType(){
		return type;
	}
	
	
	public BookContent(String type) {
		super();
		this.type = type;
		this.prev = 0;
	}

	public void setPrev(int newPrev){
		if(newPrev < 0 || newPrev >= Book.addrSize){
			System.out.println("You are trying to set prev an invalid value!");
			return;
		}
		prev = newPrev;
		
		return;
	}

	@Override
	public String toString() {
		return type;
	}
	public  boolean  verifyContent() {
		return false;
	}
	

	
	int bsdCheckSum(String s){
			int checksum = 0;
			for(int i=0; i<s.length(); i++){
			checksum = (checksum >> 1) + ((checksum & 1) << 15);
			checksum += s.charAt(i);
			checksum &= 0xffff;
			}
		return checksum;
	}

	@Override
	public int hashMe() {
		return bsdCheckSum(toString());
	
	}
	
}
