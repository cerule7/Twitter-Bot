import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
 
public class mybot {
	
	    public static void main(String... args) throws TwitterException, InterruptedException, FileNotFoundException{

	        //access the twitter API using your twitter4j.properties file
	    	
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
	    	
	    	while(true)
	    	{
	    	String stat = generator.generate();
	        //send a tweet
	    	while(stat.length() <= 140)
	    	{
	    		String t = generator.generate();
	    		if(t.length() + stat.length() <= 140) stat += " " + t;
	    		else break; 
	    	}
	    	stat = truncate(stat);
	    	Status status = twitter.updateStatus(stat);
	        System.out.println(stat);
	        Thread.sleep(60*20);
	    	}
	    }   
	    
	    public static String truncate(String s)
	    {
	    	while(s.length() > 140)
	    	{
	    		s = s.substring(0, s.length() - 2);
	    	}
	    	return s;
	    }
	
}
