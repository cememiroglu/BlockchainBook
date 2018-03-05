
public class Book {
	private int last;
	public static final int addrSize = 65536;
	public static BookContent[] nodeAddrs = new BookContent[addrSize];
	
	public Book(){
		last = 0;
	}
	
	public void addContent(BookContent c){
		c.setPrev(last);
		if(c instanceof ReferenceContent){
			ReferenceContent ref= ((ReferenceContent) c);
			System.out.println("Trying to add a new reference content: title = "+ ref.getTitle()+ " authors ="+ref.getAuthors());
		}
		else if(c instanceof TextContent){
			TextContent t = ((TextContent) c);
			System.out.println("Trying to add a new text content: text = "+t.getText());
		}
		if(c.verifyContent()){
			int hashVal = c.hashMe();
			if(hashVal == 0){
				System.out.println("Content hash value is 0 which is reserved for null! Cannot add the content.\n");
				return;
			}
			if(nodeAddrs[hashVal] != null){
				System.out.println("Content hash value collides with a previous content. Cannot add the content.\n");
				return;
			}
			nodeAddrs[hashVal] = c;
			last = hashVal;
			System.out.println("Content is successfully added to the book.\n");
			
		}
		else
			System.out.println("Content could not be verified. We cannot add it to the book.\n");
	}
	
	public void printBook(){
		printBookRec(last);
		System.out.println("===Book Ends===\n");
	}
	
	private void printBookRec(int node){
		if(node == 0)
			System.out.println("\n===Book Begins===");
		else{
			printBookRec(nodeAddrs[node].getPrev());
			System.out.println(nodeAddrs[node]);
		}
	}
	
	public boolean verifyChain(){
		if(last == 0)
			return true;
		else
			return last == nodeAddrs[last].hashMe() && verifyChainRec(last);
	}
	
	private boolean verifyChainRec(int node){
		int prev = nodeAddrs[node].getPrev();
		if(prev == 0)
			return true;
		else
			return prev == nodeAddrs[prev].hashMe() && verifyChainRec(prev);
	}
}
