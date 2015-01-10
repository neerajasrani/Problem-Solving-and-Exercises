import com.twitter.hbc.twitter4j.handler.StatusStreamHandler;
import com.twitter.hbc.twitter4j.handler.UserstreamHandler;
import com.twitter.hbc.twitter4j.message.DisconnectMessage;
import com.twitter.hbc.twitter4j.message.StallWarningMessage;
import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by neeraj on 1/9/15.
 * <p/>
 * <p/>
 * Use Twitter's sample streaming API to show the top 10 retweeted tweets
 * (note the retweeted_status field) in a rolling window of time,
 * where the window's start is n minutes ago (where n is defined by the user) and the window's end is the current time.
 * <p/>
 * Output should continuously update and include the tweet text and number of times
 * retweeted in thretweeted in the current rolling window. You should not use the retweet_count field,e current rolling window. You should not use the retweet_count field,
 * but instead count the retweets that your program actually processes.
 */
public class Retweet {

    private static final String consumerKey = "8AqQCy7umStCyNN356v7fw";
    private static final String secretKey = "vOvKV1QwuS1AeKPMIvJqErBxW7i1N12OL4UY2tNMs0c";
    private static final String accessToken = "29463499-9Og6hxW4HqFxcQyIrAdmLpbAnrwIk290ghOE0ez5f";
    private static final String accessTokenSecret = "elXVYJRFmFFit3PiVTmI9eU0IvHqqD7H4yeEmClJ8c";

    // Stores Retweets as long as the program runs.
    private static Map<Long, RetweetData> retweetStore = new HashMap<Long, RetweetData>(3000);

    // Stores highest frequency retweets.
    private static Map<Long, RetweetData> topTenRetweets = new HashMap<Long, RetweetData>(10);

    // Lowest in Top 10 Tweet ID
    private static Long MIN_ID = Long.MIN_VALUE;

    // Lowest in Top 10 Tweet Count
    private static BigInteger MIN = BigInteger.valueOf(Integer.MAX_VALUE);

    // Number of minutes for the Sample stream to run.
    private static long numOfMin;


    public static void main(String[] args) throws TwitterException {

            if (args.length > 0 && args[0].length() > 0) {
                numOfMin = Integer.parseInt(args[0]);
            }
            else {
                numOfMin = 1;
            }
            TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
            AccessToken accessToken1 = new AccessToken(accessToken, accessTokenSecret);
            StatusListener listener = new StatusStreamHandler() {
                @Override
                public void onDisconnectMessage(DisconnectMessage disconnectMessage) {

                }

                @Override
                public void onStallWarningMessage(StallWarningMessage stallWarningMessage) {

                }

                @Override
                public void onUnknownMessageType(String s) {

                }

                @Override
                public void onStatus(Status status) {

                    Status retweetStatus = status.getRetweetedStatus();

                    if (retweetStatus != null) {

                        // Tweet ID
                        long id = retweetStatus.getId();

                        // Tweet text
                        String text = retweetStatus.getText();

                        // Retweet Data if present in top 10 most frequently occurring retweets
                        RetweetData retDt = topTenRetweets.get(id);

                        // Get the tweet ID (MIN_ID) and lowest count (MIN) amongst the top 10
                        for (Map.Entry<Long, RetweetData> e : topTenRetweets.entrySet()) {
                            RetweetData rd = e.getValue();
                            BigInteger count = rd.getCount();
                            if (0 > MIN.compareTo(rd.getCount())) {
                                MIN_ID = e.getKey();
                                MIN = rd.getCount();
                            }
                        }

                        // Check if Retweet Data is present for this tweet in the top 10.
                        if (retDt != null) {

                            // Increment count in both Hash Maps.
                            topTenRetweets.put(id, new RetweetData(text, retDt.getCount().add(BigInteger.ONE)));
                            retweetStore.put(id, new RetweetData(text, retDt.getCount().add(BigInteger.ONE)));

                            // Update MIN and MIN_ID
                            if (0 > MIN.compareTo(retDt.getCount())) {
                                MIN_ID = id;
                                MIN = retDt.getCount();
                            }
                        }
                        // Check if Retweet Data is present in Store.
                        else {
                            retDt = retweetStore.get(id);
                            if (retDt != null) {

                                // Update MIN and MIN_ID
                                if (0 > MIN.compareTo(retDt.getCount())) {
                                    // Increment count in both Hash Maps.
                                    topTenRetweets.remove(MIN_ID);
                                    topTenRetweets.put(id, new RetweetData(text, retDt.getCount().add(BigInteger.ONE)));
                                    retweetStore.put(id, new RetweetData(text, retDt.getCount().add(BigInteger.ONE)));

                                    MIN_ID = id;
                                    MIN = retDt.getCount();
                                } else {
                                    retweetStore.put(id, new RetweetData(text, retDt.getCount().add(BigInteger.ONE)));
                                }
                            } else {
                                retweetStore.put(id, new RetweetData(text, BigInteger.ONE));
                                if(topTenRetweets.size() < 10) {
                                    topTenRetweets.put(id, new RetweetData(text, BigInteger.ONE));
                                }
                            }
                        }
                    }
                }

                @Override
                public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

                }

                @Override
                public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

                }

                @Override
                public void onScrubGeo(long userId, long upToStatusId) {

                }

                @Override
                public void onStallWarning(StallWarning warning) {

                }

                @Override
                public void onException(Exception ex) {
                    ex.printStackTrace();
                }
            };

            twitterStream.addListener(listener);
            twitterStream.setOAuthConsumer(consumerKey, secretKey);
            twitterStream.setOAuthAccessToken(accessToken1);

            twitterStream.addConnectionLifeCycleListener(new ConnectionLifeCycleListener() {
                @Override
                public void onConnect() {

                }

                @Override
                public void onDisconnect() {

                }

                @Override
                public void onCleanUp() {
                    System.out.println(" Top 10 Retweets of highest frequency and their counts - \n\n");

                    for (Map.Entry<Long, RetweetData> e : topTenRetweets.entrySet()) {
                        RetweetData rd = e.getValue();
                        BigInteger count = rd.getCount();
                        String text = rd.getText();
                        System.out.println("Text - " + text + "Count - " + count);
                    }

                    System.out.print("\n ---- END OF PROGRAM ----");
                    System.exit(0);
                }
            });

            // Process the Sample/ Random tweets
            twitterStream.sample();

            // Logic for running the program and stream for "n" minutes
            try {
                // Put the Class thread to sleep for the number of minutes specified by user. Defaults to "1" minute.
                Thread.sleep(numOfMin * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            twitterStream.cleanUp();
            twitterStream.shutdown();

    }
}
