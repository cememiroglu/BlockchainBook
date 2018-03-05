
public class Test {
    public static void main(String[] args){
        
    	String[] autC1 = new String[1];
    	autC1[0] = "Martin T. Vechev";
        BookContent c1 = new ReferenceContent("vechev10", "Computer-aided construction of concurrent systems", autC1);
        
        String[] autC2 = new String[2];
    	autC2[0] = "Suha Orhun Mutluergil";
    	autC2[1] = "Serdar Tasiran";
        BookContent c2 = new ReferenceContent("mutluergil16", "A Mechanized Refinement Proof of the Chase-Lev Deque Using a Proof System", autC2);
        
        String[] autC3 = new String[2];
    	autC3[0] = "Serdar Tasiran";
    	autC3[1] = "Martin T. Vechev";
        BookContent c3 = new ReferenceContent("mutluergil16", "A Mechanized Refinement Proof of the Chase-Lev Deque Using a Proof System", autC3);
       
       String text1 = "Computer systems can be built as described in [vechev10].";
       BookContent c4 = new TextContent(text1);
       
       String text2 = "However, verification of these systems requires considerable effort. [mutluergil16] proposes a novel approach that combines abstraction and refinement techniques.";
       BookContent c5 = new TextContent(text2);
       
       String text3 = "Semantic model described in [lamport79] is the de facto standard accepted by the academic community.";
       BookContent c6 = new TextContent(text3);
       
       String text4 = "Some people argue that the techniques described in [mutluergil16] are not applicable to the systems in [vechev10]].";
       BookContent c7 = new TextContent(text4);
       
       Book b1 = new Book();
       b1.addContent(c1);
       b1.addContent(c2);
       b1.addContent(c3);
       b1.addContent(c4);
       b1.addContent(c5);
       b1.addContent(c6);
       b1.addContent(c7);
       
       b1.printBook();
       System.out.println("Book verification status after adding contents: "+b1.verifyChain());
       
       ((ReferenceContent) c2).setTitle("I want to change things");
       
       System.out.println("Book verification status after modifying a reference content: "+b1.verifyChain());
       
    }
}
