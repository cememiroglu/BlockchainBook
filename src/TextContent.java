
public class /*class/abstract class/interface*/ TextContent extends /*implements/extends*/BookContent {
	private /*static/final*/ String text;
	private /*static/final*/ int limitSize = 250;
	
	/*Declare/Implement the missing methods here*/
	
	public TextContent(String text4) {
		// TODO Auto-generated constructor stub
		super("text");
		this.text=text4;
	}
	public String getText(){
		return text;
	}
	public boolean verifySize() {
		String[] result = text.split("\\s");
	    if (result.length>250) {
	    	return false;
	    }else {
	    	return true;
	    }
	}
	public Boolean findCitation(String citation) {
		BookContent ref =getPrevContent();
		while(ref!=null) {
			if(ref.getType().equals("reference")) {
				ReferenceContent referenceContent= (ReferenceContent)ref;
				if(referenceContent.getRefID().equals(citation)) {
					return true;
				}
				
			}
			ref=ref.getPrevContent();
		}
		return false;

		
	}
	public boolean verifyCitations() {
			
				String[]w =text.split("\\[");
				boolean verify=false;
				for(int i=1; i<w.length; i++) {
					String w2= w[i];
					int index= w2.indexOf("]");
					
					String citation= w2.substring(0, index);
					if(findCitation(citation)) {
						verify=true;
					}
					if(w2.substring(index+1).contains("]")) {
						verify=false;;
					}
				}
				return verify;
		}
			
				
				
			
			
			
			
			
		
		
	
	public boolean verifyContent() {
		if(verifySize()==true&& verifyCitations()==true) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public String toString() {
		super.toString();
		return "Type: text previous: " +getPrev()+" text "+text;
	}
	
	
	
	
	
	
}
