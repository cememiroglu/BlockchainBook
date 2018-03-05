# BlockchainBook
For this programming assignment in Java, you are supposed to simulate an online book framework by
using the inheritance and polymorphism concepts introduced in the class. In order to prevent malevolent
intruders from tampering with the data in the book, you will implement a simple blockchain structure
which constitutes the basis of the crypto-currencies like Bitcoin.
With this assignment, you are provided a template Java project with some classes and methods already
implemented.
Our online book framework will enable its users to create documents as in Wikipedia. A book in our
framework will correspond to a Wikipedia page in which different types of information about a subject
can be gathered and different users can contribute to it. We can think of a book as a sequence of different
kind of contents. For instance, a book might begin with some text, and followed by a figure. Then, there
could be some references and it might end with some more text. Each content type has different
properties and our framework should provide different functionalities accordingly.
Assume that there exist two kinds of contents: text and reference. A text content is a string that provides
information. A reference keeps information about a scientific publication like its title and authors. Users
can cite references inside the text. In Figure 1, you see a simple book schema. Beginning of the book is
marked with a null (empty) content. Then, the following two reference contents provide information
about two publications. Finally, a text content gives some definitions by citing previous references. Order
of the book and the connection of contents is established via prev links. The end of the document is
shown by the last link.

Figure 1. A sample book containing two reference contents and a text content.
Different from Wikipedia, we allow users to add new content only to the end of the book. Each user keeps
the last link of the book and they modify it when they add new content. Moreover, we want to make
sure that the newly provided content obeys some correctness criteria before adding it. Once the new
content is approved and appended to the end, we want to prevent any modification on this content since
the changes might violate our correctness conditions.
Our conditions on the new content depend on the content type. If the content is a reference, we want to
make sure that the provided publication really exists by checking the online computer science publication
database DBLP. By browsing through the DBLP website, you can search publications of an author or
publications from a journal or a conference. Given a set of authors and a title, our restriction is to look at
DBLP page of each author and check whether they have any publications with the given title. More
information on how to do this is provided later on this document.
We enforce two conditions on the text content. First, each text content could contain at most 250 words.
Second, every citation should come from a prior reference. We name string fragments inside square
brackets as citations. If we encounter a citation inside a text content, we need to look all of the prior
reference contents and check whether the citation corresponds to a reference ID. For instance,
[Lamport79] in the text content of Figure 1 is a citation. It corresponds to the reference ID of the first
reference content. More details about text conditions will be also provided later.
To make sure that no one can change the approved content, we will use a very simple blockchain
mechanism. In Figure 1, each link is obtained from the function 𝐻 and its input is shown with a green
arrow. Here, 𝐻 is called a hash function. It takes an object (all of the previous content object in our case)
as an input and produces an integer (address of the previous content object in our case). Address of each
content object in the memory is provided by the hash function 𝐻. We assume that 𝐻 is almost one-toone:
If there are two distinct content objects 𝑥 and 𝑦, the probability of 𝐻(𝑥) = 𝐻(𝑦) is negligibly small.
This property is very important for ensuring that the contents of the book are not modified. For instance,
suppose that someone changed the author of the first reference in Figure 1 from “Leslie Lamport” to
“Suha Orhun Mutluergil”. Since 𝐻 is one-to-one, hashing the modified reference node will yield a new
address for this content. Hence, the modified content will be transferred to the new address. This will
require changing prev link of the second reference content since it will become inconsistent after the
address change. Since the data inside the second reference object is modified, its hash value will also
change and consequently the second reference content object will be relocated. Similarly, this will cause
a modification in the prev field and address of the last text content object. As a result of this, last links
of the users will become inconsistent and they will realize that there is a content modification.
To realize the described online book framework, you are going to implement the types described in the
following pages. You are provided a template project and some of the classes and methods are already
implemented inside the template. Using your knowledge on Java inheritance, you need to decide whether
the types are classes, abstract classes or interfaces and establish a type hierarchy. Moreover, you need to
decide which methods will be abstract and will be delegated to subclasses and which methods need to be
overwritten using Java polymorphism. Also, you need to decide whether the fields of the classes will be
static, final etc. It is for sure that all of the fields you will implement would be private and all the methods
you will implement would be public.
Book
This type generates the book instances. It is fully implemented and provided to you in the project
template. YOU MUST NOT MODIFY OR ADD ANYTHING TO THIS CLASS. In this part, we provide basic
information about its fields and methods to explain its functionality.
- The private field last keeps address of the last content of the book. Static array nodeAddrs
represents the memory. When a new content is added to the book, it is placed to the address
corresponding to its hash value in this array. Since the size of the array is 65536 bytes, we assume
that the addresses are 16-bits and the hash function returns a 16 bit integer.
- void addContent(BookContent c): Adds the content c to the end of the book if (i) c is
verified and (ii) location in the memory corresponding to hash value of c is empty i.e., there is no
other content object with the same hash value.
- void printBook() : Prints the book to the console.
- void verifyChain() : Checks for every content in the book whether the address kept in the
prev field really corresponds to the hash of the content at that address.
Hashable
This type represents objects that can be hashed. It does not have any fields. If a type is hashable, it should
implement hashMe() function defined by this type.
- int hashMe() : Implementation of this method depends on the type. Depending on the
structure of its fields, a hashable type should return a 16-bit integer for all of its instances.
BookContent
It is a generic type for representing content objects. It is a hashable type.
- type : This field keeps the content type. Its value is either “reference” or “text”.
- prev : This field keeps the address of the previous BookContent instance.
- BookContent(String typeName) : Constructor. Initializes type to its argument and sets
prev to 0.
- string toString() : Concatenates its fields together as a string. If this is a subclass, fields
of the all of its super-classes should be included in the resulting string.
- boolean verifyContent(): Implementation of this method depends on the type of the
content.
- int hashme() : Since this type is hashable, it implements this function. The hashing
mechanism described here is called the BSD checksum algorithm. Given a string, this algorithm
produces a 16-bit integer by adding up all the bytes (chars) of the given string and keeps the result
within the bounds using modulus operation. To be able to use this hash function, you need to turn
your content instances into strings first by using toString() method. The algorithm you will
use is as follows:
int bsdCheckSum(String s){
int checksum = 0;
for(in i=0; i<s.length(); i++){
checksum = (checksum >> 1) + ((checksum & 1) << 15);
checksum += s.charAt(i);
checksum &= 0xffff;
}
- Necessary getter and setter methods are provided to you. Note that there are two getter methods
for prev. getPrev() returns the address of the previous content whereas
getPrevContent() returns the previous object.
ReferenceContent
A type for representing reference content instances.
- String refID, String title, String[] authors : Fields corresponding to
reference ID, title and authors of the reference, correspondingly.
- ReferenceContent(String id, String ttl, String[] auts) : Three argument
constructor. It is advised that the auts array is not directly assigned to the authors but a new
array is initialized for authors and elements of the input are copied to it one by one.
- boolean verifyContent() : To verify the reference, you need to check whether the
reference exists in the DBLP database. To achieve this, you need to obtain a URL (web address)
for the web page that shows the publications of the author for each author in authors, first.
The URL will be obtained from the author’s name with string manipulations. A URL starts with the
prefix “http://dblp.uni-trier.de/pers/hd/”. Then, the first letter of the author’s
surname (in lower case) is followed by a “/”. Rest of the URL is in the form of
“surname:name”. If the author has more than one names, they are separated with a “_”. For
instance, if the author’s name is Suha Orhun Mutluergil, then the desired URL is:
http://dblp.uni-trier.de/pers/hd/m/Mutluergil:Suha_Orhun
Lastly, if the author’s name is abbreviated with a dot, then this dot should be replaced with a “=”.
For instance, if the author’s name is Suha O. Mutluergil, then the URL becomes as follows:
http://dblp.uni-trier.de/pers/hd/m/Mutluergil:Suha_O=
We assume that all the other characters in the author’s name are from English alphabet.
After you obtain the URL, you should print following to the console: “Connecting to URL:
<URL>” where <URL> is the URL you constructed as above.
Once you obtain the URL and print, you can connect to this address and read all the HTML content
of the page using URL and URLConnection classes. To understand how to use them, you can
read this tutorial. While using these classes, you need to be careful about exceptions. To
overcome the exception problems, either your method should throw an exception as the main
method in the tutorial or you should write your code inside a try/catch block like:
try{
//Your code for connection and reading
} catch(Exception e) {
e.printStackTrace();
}
After you read the HTML page into a string variable, you can verify the reference by checking if
the title of the reference exists in this string.
- string toString() : Concatenates its fields together as a string. If this is a subclass, fields
of the all of its super-classes should be included in the resulting string.
- Necessary getter and setter methods are provided to you.
TextContent
A type for representing text content instances.
- String text : Field keeping the text content.
- int limitSize : Field that keeps the limit on the number of the words inside the text. It is the
same value (250) for every instance and determined before the program is executed. So, it should
not be allowed to change even by the constructor.
- boolean verifySize() : A method that checks whether a number of words exceeds 250 in
the text using string manipulation methods. Each word is separated by a blank space (‘ ’)
character.
- Boolean findCitation(String citation) : A method that checks whether given
citation in the text is added to the book before the current text. You need to check whether there
exists a previous reference content with a refID field same as the given citation. To check this,
you need to traverse previous contents using the provided getPrevContent() method of the
BookContent class. Note that, the first content of the book points to null. i.e., its
getPrevContent() returns null. So, you need to call getPrevContent()until you reach
null or a reference content (type field is “reference”) with refID as the given citation.
- boolean verifyCitations() : A method that extracts citations from the text and checks
whether they occur previously in the book utilizing the findCitation() method explained
previously. Every string within square brackets (i.e. [miller15]) inside the text content is
considered to be a citation. You need to find every citation inside the text and check whether they
correspond to refID of a previous reference content object. To find citations you should find
positions of every ‘[’ and following ‘]’ characters inside the text and extract the string in
between these characters as a reference. If the text contains correct citations, when we project
the text into ‘[’ and ‘]’ characters, we should obtain an alternating sequence of ‘[’s and
‘]’s, starting with a ‘[’ and ending with a ‘]’. For some texts, citations may not be formed
correctly. If a ‘[’ is not followed by a ‘]’ or ‘[’ is followed by another ‘[’ before a ‘]’,
this text has incorrect citations. Similarly, if a ‘]’ is not preceded by a ‘[’ or ‘]’ is preceded
by another ‘]’ after a ‘[’, this text has incorrect citations. If there is an ill-formed citation, your
verifyCitations() method should return false. Following are some texts with correct and
incorrect citations:
Quick brown fox – Correct
Jumps [over a lazy] dog – Correct
Quick [brown] []fox – Correct
Jumps [over [a] lazy] dog – Incorrect
Quick [brown [fox] – Incorrect
Jumps [over a] lazy] dog – Incorrect
Hint. If you plan to extract citations with split method of the String class, you should be
careful about using square brackets as input strings. They have special meanings as an input and
considered to be meta-characters. To make split recognize them as delimiter characters, you need
to use “\\[” and “\\]”.
- verifyContent : A method that verifies text content instances. It returns true if and only of
verifySize() and verifyCitations() methods return true.
- string toString() : Concatenates its fields together as a string. If this is a subclass, fields
of the all of its super-classes should be included in the resulting string.
- Necessary getter and setter methods are provided to you
