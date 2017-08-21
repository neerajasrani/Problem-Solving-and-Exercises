import java.util.*;

/**
 * Created by nickamac on 8/16/17.
 *
 * A  10X10 Crossword grid is provided to you, along with a set of words (or names of places) which need to be filled into the grid.
 The cells in the grid are initially, either + signs or - signs.
 Cells marked with a + have to be left as they are. Cells marked with a - need to be filled up with an appropriate character.

 Input Format

 The input contains  lines, each with  characters (which will be either + or - signs).
 After this follows a set of words (typically nouns and names of places), separated by semi-colons (;).

 Constraints

 There will be no more than ten words. Words will only be composed of upper-case A-Z characters. There will be no punctuation (hyphen, dot, etc.) in the words.

 Output Format

 Position the words appropriately in the  grid, and then display the  grid as the output. So, your output will consist of  lines with  characters each.

 Sample Input 0

 +-++++++++
 +-++++++++
 +-++++++++
 +-----++++
 +-+++-++++
 +-+++-++++
 +++++-++++
 ++------++
 +++++-++++
 +++++-++++
 LONDON;DELHI;ICELAND;ANKARA
 Sample Output 0

 +L++++++++
 +O++++++++
 +N++++++++
 +DELHI++++
 +O+++C++++
 +N+++E++++
 +++++L++++
 ++ANKARA++
 +++++N++++
 +++++D++++
 Sample Input 1

 +-++++++++
 +-++++++++
 +-------++
 +-++++++++
 +-++++++++
 +------+++
 +-+++-++++
 +++++-++++
 +++++-++++
 ++++++++++
 AGRA;NORWAY;ENGLAND;GWALIOR
 Sample Output 1

 +E++++++++
 +N++++++++
 +GWALIOR++
 +L++++++++
 +A++++++++
 +NORWAY+++
 +D+++G++++
 +++++R++++
 +++++A++++
 ++++++++++
 */
public class CrosswordPuzzle {

    private static List<String> words = new java.util.LinkedList<>();


    private static Set<Tuple> intersectingCharacters = new HashSet<>(10);
    private static List<Tuple> availableTuples = new LinkedList<>();


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int i;
        char[][] m = new char[10][10];

        // First input
//        String ip;
//        String ip = "+-++++++++";
//        m[0] = ip.toCharArray();
//        String ip1 = "+-++++++++";
//        m[1] = ip1.toCharArray();
//        String ip2 = "+-------++";
//        m[2] = ip2.toCharArray();
//        String ip3 = "+-++++++++";
//        m[3] = ip3.toCharArray();
//        String ip4 = "+-++++++++";
//        m[4] = ip4.toCharArray();
//        String ip5 = "+------+++";
//        m[5] = ip5.toCharArray();
//        String ip6 = "+-+++-++++";
//        m[6] = ip6.toCharArray();
//        String ip7 = "+++++-++++";
//        m[7] = ip7.toCharArray();
//        String ip8 = "+++++-++++";
//        m[8] = ip8.toCharArray();
//        String ip9 = "++++++++++";
//        m[9] = ip9.toCharArray();
//        ip = "AGRA;NORWAY;ENGLAND;GWALIOR";

        // Second input
        String ip = "+-++++++++";
        m[0] = ip.toCharArray();
        String ip1 = "+-++++++++";
        m[1] = ip1.toCharArray();
        String ip2 = "+-++++++++";
        m[2] = ip2.toCharArray();
        String ip3 = "+-----++++";
        m[3] = ip3.toCharArray();
        String ip4 = "+-+++-++++";
        m[4] = ip4.toCharArray();
        String ip5 = "+-+++-++++";
        m[5] = ip5.toCharArray();
        String ip6 = "+++++-++++";
        m[6] = ip6.toCharArray();
        String ip7 = "++------++";
        m[7] = ip7.toCharArray();
        String ip8 = "+++++-++++";
        m[8] = ip8.toCharArray();
        String ip9 = "+++++-++++";
        m[9] = ip9.toCharArray();
        ip = "LONDON;DELHI;ICELAND;ANKARA";

        words = new java.util.LinkedList<>(Arrays.asList(ip.split(";")));

        char [][] m2 = new char[m.length][m.length];
        for(i = 0; i < m.length; i++)
            m2[i] = m[i].clone();

        // Call the function to find available tuples to write to
        for (i=0; i < 10; i++) {
            for (int j=0; j < 10; j++) {
                if (m2[i][j] == '-') {
                    findAvailableTuples(i, j, m2);
                }
            }
        }
        for (Tuple t: availableTuples) {
            System.out.println(t.toString());
        }

        System.out.println(" Now let us print the intersecting characters\n");
        for (Tuple t: intersectingCharacters) {
            System.out.println(t.toString());
        }

        Map<Tuple, String> matchedWordsWithTuples = matchWordsInCrosswordPuzzle();

        writeToMatrix(m, matchedWordsWithTuples);

        // Print the matrix
        for (i=0; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j < 10; j++) {
                sb.append(m[i][j]);
            }
            System.out.println(sb.toString());
        }
    }

    private static void findAvailableTuples(int x, int y, char[][] m) {

        int minx, maxx, miny, maxy;

        // Find minx
        int iter = x - 1;
        int count = 0;
        while (iter >= 0 && m[iter][y] != '+') {
            if (m[iter][y] == 'V') { intersectingCharacters.add(new Tuple(iter, y)); }
            if (m[iter][y] == 'V' || m[iter][y] == '-') {
                m[iter][y] = 'V';
                iter--;
                count++;
            }
        }
        minx = x - count;

        // Find maxx
        iter = x + 1;
        count = 0;
        while (iter <= 9 && m[iter][y] != '+') {
            if (m[iter][y] == 'V') { intersectingCharacters.add(new Tuple(iter, y)); }
            if (m[iter][y] == 'V' || m[iter][y] == '-') {
                m[iter][y] = 'V';
                iter++;
                count++;
            }
        }
        maxx = x + count;

        // Find miny
        iter = y - 1;
        count = 0;
        while (iter >= 0 && m[x][iter] != '+') {
            if (m[x][iter] == 'V') { intersectingCharacters.add(new Tuple(x, iter)); }
            if (m[x][iter] == 'V' || m[x][iter] == '-') {
                m[x][iter] = 'V';
                iter--;
                count++;
            }
        }
        miny = y - count;

        // Find maxy
        iter = y + 1;
        count = 0;
        while (iter <= 9 && m[x][iter] != '+') {
            if (m[x][iter] == 'V') { intersectingCharacters.add(new Tuple(x, iter)); }
            if (m[x][iter] == 'V' || m[x][iter] == '-') {
                m[x][iter] = 'V';
                iter++;
                count++;
            }
        }
        maxy = y + count;
        if (minx != maxx) {
            availableTuples.add(new Tuple(minx, y, maxx, y));
        }
        if (miny != maxy) {
            availableTuples.add(new Tuple(x, miny, x, maxy));
        }
    }

    private static Map<Tuple, String> matchWordsInCrosswordPuzzle() {

        Map<Tuple, String> result = new HashMap<>(availableTuples.size());
        Tuple t1 = null, t2 = null;
        int t1Index = 0, t2Index = 0;

        // Intersecting Tuple coodinates
        for (Tuple t : intersectingCharacters) {
            int ix = t.getStartX();
            int iy = t.getStartY();

            for (Tuple at : availableTuples) {
                if (at.getStartX() == ix && at.getEndX() == ix) {
                    t1 = at;
                    t1Index = iy - t1.getStartY();
                }
                if (at.getStartY() == iy && at.getEndY() == iy) {
                    t2 = at;
                    t2Index = ix - at.getStartX();
                }
            }

            for (String firstWord : words) {
                assert t1 != null;
                if (firstWord.length() == t1.getEndY() - t1.getStartY()) {
                    for (String secondWord : words) {
                        assert t2 != null;
                        if (secondWord.length() == t2.getEndX() - t2.getStartX() + 1) {
                            if (firstWord.charAt(t1Index) == secondWord.charAt(t2Index)) {
                                result.put(t1, firstWord);
                                result.put(t2, secondWord);
                                words.remove(firstWord);
                                words.remove(secondWord);
                                availableTuples.remove(t1);
                                availableTuples.remove(t2);
                            }
                        }
                    }
                }
                assert t2 != null;
                if (firstWord.length() == t2.getEndX() - t2.getStartX()) {
                    for (String secondWord : words) {
                        if (secondWord.length() == t1.getEndY() - t1.getStartY() + 1) {
                            if (firstWord.charAt(t1Index) == secondWord.charAt(t2Index)) {
                                result.put(t1, firstWord);
                                result.put(t2, secondWord);
                                words.remove(firstWord);
                                words.remove(secondWord);
                                availableTuples.remove(t1);
                                availableTuples.remove(t2);
                            }
                        }
                    }
                }
            }
            // Remaining tuple coordinates
            ListIterator<Tuple> listIter = availableTuples.listIterator();
            while(listIter.hasNext()) {
                Tuple at = listIter.next();
                int tuplelength = 0;
                if (at.getStartX() == at.getEndX()) {
                    tuplelength = at.getEndY() - at.getStartY() + 1;
                } else if (at.getStartY() == at.getEndY()) {
                    tuplelength = at.getEndX() - at.getStartX() + 1;
                }
                for (String w : words) {
                    if (w.length() == tuplelength) {
                        result.put(at, w);
                        words.remove(w);
                        listIter.remove();
                        break;
                    }
                }
            }
        }
        return result;
    }

    private static void writeToMatrix(char[][] m, Map<Tuple, String> matchedWordsWithTuples) {

        int wordCount;
        for (Map.Entry<Tuple, String> e: matchedWordsWithTuples.entrySet()) {
            Tuple t = e.getKey();
            String word = e.getValue();
            wordCount = 0;
            if (t.getStartX() == t.getEndX()) {
                for (int i = t.getStartY() ; i <= t.getEndY(); i++) {
                    m[t.getStartX()][i] = word.charAt(wordCount);
                    wordCount++;
                }
            }
            else if (t.getStartY() == t.getEndY()) {
                for (int i = t.getStartX() ; i <= t.getEndX(); i++) {
                    m[i][t.getStartY()] = word.charAt(wordCount);
                    wordCount++;
                }
            }
        }
    }

    static class Tuple {
        int startX;
        int startY;
        int endX;
        int endY;

        Tuple(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.endX = endX;
            this.startY = startY;
            this.endY = endY;
        }

        Tuple(int startX, int startY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = -1;
            this.endY = -1;
        }

        int getStartX() {
            return startX;
        }
        int getStartY() {
            return startY;
        }
        int getEndX() {
            return endX;
        }
        int getEndY() {
            return endY;
        }

        @Override
        public String toString() {
            return "Start X : " + String.valueOf(this.getStartX()) +
                    " Start Y : " + String.valueOf(this.getStartY()) +
                    " End X : " + String.valueOf(this.getEndX()) +
                    " End Y : " + String.valueOf(this.getEndY());
        }

        @Override
        public boolean equals(Object obj) {
            if (this.getClass() == obj.getClass()) {
                Tuple newObj = (Tuple) obj;
                return this.getStartX() == newObj.getStartX()
                        && this.getEndX() == newObj.getEndX()
                        && this.getStartY() == newObj.getStartY()
                        && this.getEndY() == newObj.getEndY();
            }
            return false;
        }
    }
}
