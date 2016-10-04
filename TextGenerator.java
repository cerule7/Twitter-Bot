import java.io.*;
import java.util.*;
import java.util.Map.Entry;
public class TextGenerator {

	public StringBuilder text = new StringBuilder();
	
    /**
     * @param args when used with three arguments, the first represents the k-order of the Markov objects.
     * The second represents the number of characters to print out. The third represents the file to be read.
     * 
     * When used with two arguments, the first represents the k-order of the Markov objects, and
     * the second represents the file to be read. The generated text will be the same number of characters
     * as the original file.
     */
  public TextGenerator(int a, int b, String f) {
    int k = a;
	int M = b;
	
	File[] files = new File("C:\\Users\\Charlie\\Desktop\\material").listFiles();
	//If this pathname does not denote a directory, then listFiles() returns null. 
	ArrayList<String> results = new ArrayList<String>();

	
	for (File file : files) {
	    if (file.isFile()) {
	        results.add(file.getName());
	    }
	}

	int ss = (int) (Math.random() * results.size()); 
	String file = results.get(ss);
	
	FileReader reader = null;
	try {
	    reader = new FileReader("C:\\Users\\Charlie\\Desktop\\material\\" + file);
	} catch (FileNotFoundException e) {
	    System.out.println("File not found.");
	    e.printStackTrace();
	}

	MyHashMap<String, Markov> hash = new MyHashMap<String, Markov>();

	Character next = null;
	try {
	    next = (char) reader.read();
	} catch (IOException e1) {
	    System.out.println("IOException in stepping through the file");
	    e1.printStackTrace();
	}

	StringBuilder origFileBuffer = new StringBuilder();
	while (Character.isDefined(next)) {
	    Character.toString(next);
	    origFileBuffer.append(next);
	    try {
		next = (char) reader.read();
	    } catch (IOException e) {
		System.out.println("IOException in stepping through the file");
		e.printStackTrace();
	    }

	}
	String origFile = origFileBuffer.toString();
	String firstSub = origFile.substring(0, k);
	for (int i=0; i<origFile.length()-k; i++) {
	    String sub = origFile.substring(i,i+k);
	    Character suffix = origFile.charAt(i+k);

	    if (hash.containsKey(sub)) {
		Markov marvin = hash.get(sub);
		marvin.add(suffix);
		hash.put(sub, marvin);
	    }
	    else {
		Markov marvin = new Markov(sub, suffix);
		hash.put(sub, marvin);
	    }
	}
	if (M == 0)
	    M = origFile.length();
	for (int i=k; i<M; i++) {
	    if (i==k) {
		text.append(firstSub);
		if (text.length() > k)
		    i=text.length();
	    }
	    String sub = text.substring((i-k),(i));
	    Markov tmp = hash.get(sub);
	    if (tmp!=null) {
		Character nextChar = tmp.random();
		text.append(nextChar);
	    }
	    else {
		i = k-1;
	    }
	}
	if (hash.size() < 100) {
	    Iterator<String> keys = hash.keys();
	    while (keys.hasNext()) {
		String hashKey = keys.next();
		Markov hashValue = hash.get(hashKey);
		System.out.print(hashValue.count()+" "+hashKey+":");
		for (Entry<Character, Integer> entry : hashValue.getMap().entrySet()) {
		    char suffix = entry.getKey();
		    int frequencyCount = entry.getValue();
		    System.out.print(" "+frequencyCount+" "+suffix);
		}
		System.out.println();
	    }
	}
	
    }
  
  	public String returnString()
  	{
  		return (text.toString().substring(0, Math.min(140, text.length())));
  	}
  
}