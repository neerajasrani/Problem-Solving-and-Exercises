package apple.cisco;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;


/**
 * 1. Sorted order of items
 * 2. Customer transaction API,
 */

public class Restaurant {
  private static List<Item> menu = new ArrayList<>();
  private static volatile int updated = 0;

  public static synchronized void initialize() throws Exception {

    File fileMenu= null;
    BufferedReader br = null ;
    try {

      fileMenu = new File("/Users/nickamac/IdeaProjects/Retweet/src/main/java/apple/cisco/menu.properties");
      br = new BufferedReader(new FileReader(fileMenu));
      String st;

      while ((st = br.readLine()) != null) {
        String[] split = st.split("-");
        Item item = new Item();
        item.setName(split[0]);
        item.setPrice(Double.parseDouble(split[1]));
        menu.add(item);
      }
      menu.sort(Comparator.comparing(Item::getPrice));

      for (Item item : menu) {
        System.out.println(item.getName() + " - " + item.getPrice());
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new RestaurantException(e.getMessage());
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) throws Exception{
    initialize();
  }
}
