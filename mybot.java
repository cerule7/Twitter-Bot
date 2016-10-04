import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
 
public class mybot {	
	
	static ArrayList<String> results = new ArrayList<String>();
	static String stat = ""; 
	
	
	    public static void main(String... args) throws TwitterException, InterruptedException, FileNotFoundException{
	    	//input your tokens 
	    	String consumerkey = "";
	    	String consumersecret = "";
	    	String accesstoken = "";
	    	String accesstokensecret = "";
	    	
	    	ConfigurationBuilder twitterConfigBuilder = new ConfigurationBuilder();
	    	
	    	twitterConfigBuilder.setDebugEnabled(true);
	    	twitterConfigBuilder.setOAuthConsumerKey(consumerkey);
	    	twitterConfigBuilder.setOAuthConsumerSecret(consumersecret);
	    	twitterConfigBuilder.setOAuthAccessToken(accesstoken);
	    	twitterConfigBuilder.setOAuthAccessTokenSecret(accesstokensecret);
	    	
	    	Twitter twitter = new TwitterFactory(twitterConfigBuilder.build()).getInstance();
	    	
	    	makeTweet(twitter);
    		twitter.updateStatus(stat);
    		System.out.println(stat);
    		return;
	    }   
	    
	    public static void makeTweet(Twitter twitter) throws TwitterException
	    {
	    	String file = randomFile(); 
	  	    int k = (int) (Math.random() * 6) + 4; 
	  	    TextGenerator t = new TextGenerator(k, (int) (Math.random() * 140 + 75), file);
	  	    stat = t.returnString(); 
	  	  	stat = truncate(stat);
	    }
	    
	    public static String randomFile()
	    {
	    	//input directory where your material is kept
	    	File[] files = new File("").listFiles();
	    
	    	for (File file : files) {
	    	    if (file.isFile()) {
	    	        results.add(file.getName());
	    	    }
	    	}
	    	
	    	int i = (int) (Math.random() * results.size()); 
	    	return results.get(i);
	    }
	    
	    public static String truncate(String s)
	    {
	    	while(s.charAt(s.length() -1) != ' ')
	    	{
	    		s = s.substring(0, s.length() - 2);
	    	}
	    	return s;
	    }
	
}
