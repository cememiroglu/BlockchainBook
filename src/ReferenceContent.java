import java.net.*;
import java.util.Arrays;
import java.io.*;

public class /*class/abstract class/interface*/ ReferenceContent extends BookContent /*implements/extends*/ {
	private /*static/final*/ String refID;
	private /*static/final*/ String title;
	private /*static/final*/ String[] authors;
	
	/*Declare/Implement the missing methods here*/
	
	
	
	public String getRefID(){
		return refID;
	}
	public String getAuthors(){
		String res = "";
		for(String aut:authors) {
			res += " "+aut;
		}
		return res;
	}
	public ReferenceContent(String refID, String title, String[] authors) {
		super("reference");
		this.refID = refID;
		this.title = title;
		this.authors = authors;
	}

	
	public boolean verifyContent() {
		boolean verify=false;
		for(int i=0; i<authors.length; i++) {
			verify=false;
			StringBuffer url= new StringBuffer("http://dblp.uni-trier.de/pers/hd/");
			String name = null;
			String surname = null;
			String middleName= null;
			String author=authors[i];
		    name= author.substring(0, author.indexOf(" ")); 
		    middleName= author.substring((author.indexOf(" ")), author.lastIndexOf(" "));
		    surname=author.substring(author.lastIndexOf(" ")+1,(author.length()));
		    //lowercase surname
		    String a= surname.toLowerCase();
		    url.append(a.charAt(0)+"/");
		    //now appending rest
		    url.append(surname+":"+name);
		    if(middleName.contains(".")) {
		    	url.append("_"+middleName.charAt(1)+"=");
		    }else if(middleName.equals("")) {
		    	middleName=null;
			}else  {
				url.append("_"+middleName.substring(1));
			}
		    String link=url.toString();
		    System.out.println("Connecting to URL: "+ link);
		    
		    //reading  url connection

	        try {

	        		URL url1 = new URL(link);
	        		 BufferedReader br = new BufferedReader(new InputStreamReader(url1.openStream()));

	            	String line;

	            	StringBuilder sb = new StringBuilder();

	            	while ((line = br.readLine()) != null) {

	            		sb.append(line);
	            		sb.append(System.lineSeparator());
	            	}
	            	String check= sb.toString();
	            	if(check.contains(title)) {
	            		verify=true;
	            		
	            	}
	        } catch (Exception e) {
	        	// TODO Auto-generated catch block
	        	e.printStackTrace();

	        }
	        //return verify;
		}
		
		return verify;
				
				
	}
		
	
	
	
		
		
	
		
	
	
	public String getTitle(){
		return title;
	}
	
	
	
	public void setTitle(String newTitle){
		title = newTitle;
	}
	@Override
	public String toString() {
		super.toString();
		String str= "Type: reference previous: "+getPrev()+ " ID:"+refID+" title:"+ title +" Authors: "+ authors.toString();
		for(String aut: authors) {
			str+=aut;
		}
		return str;
	}
	
	
	
}
