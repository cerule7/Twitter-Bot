import simplenlg.features.Feature;
import simplenlg.features.InterrogativeType;
import simplenlg.features.Tense;
import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class generator {
	
	 private static ArrayList<String> verbs = new ArrayList<String>(); 
	 private static ArrayList<String> nouns = new ArrayList<String>(); 
	 private static ArrayList<String> names = new ArrayList<String>(); 
	 private static ArrayList<String> adverbs = new ArrayList<String>(); 
	 private static ArrayList<String> specs = new ArrayList<String>(); 
	 private static ArrayList<String> adjs = new ArrayList<String>(); 
	 private static ArrayList<String> preps = new ArrayList<String>(); 
	 
	 public static String generate() throws FileNotFoundException 
	 {
		 lists(); 
         Lexicon lexicon = Lexicon.getDefaultLexicon();
         NLGFactory nlgFactory = new NLGFactory(lexicon);
         Realiser realiser = new Realiser(lexicon);
         SPhraseSpec p = nlgFactory.createClause();
         p.setSubject(names());
         p.setVerb(verbs());
         Random r = new Random(); 
         if(r.nextBoolean())
         {
        	 p.setFeature(Feature.NEGATED, true);
         }
         if(r.nextBoolean())
         {
        	 p.addModifier(preps());
         }
         if(r.nextInt(10) < 3)
         {
        	 p.setFeature(Feature.TENSE, Tense.FUTURE);
         } else if(r.nextInt(10) < 5)
         {
        	 p.setFeature(Feature.TENSE, Tense.PAST); 
         } else if(r.nextInt(100) > 50)
         {
        	p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.YES_NO);
         }
         p.setObject(specs() + " " + adjs() + " " + nouns().toLowerCase());
         p.addModifier(adverbs());
         String output2 = realiser.realiseSentence(p); 
         return output2;
         
	 }
	 
	 public static void lists() throws FileNotFoundException
	 {
		Scanner inFile = new Scanner(new File("C:\\Users\\Charlie\\Desktop\\MyBot\\verbs.txt"));
		while(inFile.hasNextLine())
		{
			verbs.add(inFile.nextLine());
		}
		inFile = new Scanner(new File("C:\\Users\\Charlie\\Desktop\\MyBot\\nouns.txt"));
		while(inFile.hasNextLine())
		{
			nouns.add(inFile.nextLine());
		}
		inFile = new Scanner(new File("C:\\Users\\Charlie\\Desktop\\MyBot\\names.txt"));
		while(inFile.hasNextLine())
		{
			names.add(inFile.nextLine());
		}
		inFile = new Scanner(new File("C:\\Users\\Charlie\\Desktop\\MyBot\\adver.txt"));
		while(inFile.hasNextLine())
		{
			adverbs.add(inFile.nextLine());
		}
		inFile = new Scanner(new File("C:\\Users\\Charlie\\Desktop\\MyBot\\specs.txt"));
		while(inFile.hasNextLine())
		{
			specs.add(inFile.nextLine());
		}
		inFile = new Scanner(new File("C:\\Users\\Charlie\\Desktop\\MyBot\\adjs.txt"));
		while(inFile.hasNextLine())
		{
			adjs.add(inFile.nextLine());
		}
		inFile = new Scanner(new File("C:\\Users\\Charlie\\Desktop\\MyBot\\preps.txt"));
		while(inFile.hasNextLine())
		{
			preps.add(inFile.nextLine());
		}
	 }
	 
	public static String adverbs()
	{
		int i = (int) (Math.random() * adverbs.size());
		return adverbs.get(i);
	}
	
	public static String verbs()
	{
		int i = (int) (Math.random() * verbs.size());
		return verbs.get(i);
	}
	
	public static String specs()
	{
		int i = (int) (Math.random() * specs.size());
		return specs.get(i);
	}
	
	public static String names()
	{
		int i = (int) (Math.random() * names.size());
		return names.get(i);
	}
	
	public static String preps()
	{
		int i = (int) (Math.random() * preps.size());
		return preps.get(i);
	}
	
	public static String nouns()
	{
		int i = (int) (Math.random() * nouns.size());
		return nouns.get(i);
	}
	
	public static String adjs()
	{
		int i = (int) (Math.random() * adjs.size());
		return adjs.get(i);
	}


}
