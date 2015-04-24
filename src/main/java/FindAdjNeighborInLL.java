import java.util.LinkedList;

/**
 * Created by neeraj on 4/22/15.
 */
public class FindAdjNeighborInLL {

        public static class Node {
            char c;
            Node left;
            Node right;
            Node next;
        }

        public static void main(String[] args) {
            Node a = new Node();
            a.c = 'A';

            Node b = new Node();
            b.c = 'B';

            Node c = new Node();
            c.c = 'C';

            Node d = new Node();
            d.c = 'D';

            Node f = new Node();
            f.c = 'F';

            Node g = new Node();
            g.c = 'G';

            Node h = new Node();
            h.c = 'H';

            Node i = new Node();
            i.c = 'I';

            a.left = b;
            a.right = c;
            b.left = d;
            c.left = f;
            c.right =g;
            d.left = h;
            d.right = i;

            setNextNode(a);
            System.out.println(a.c + "->" + (a.next != null ? a.next.c : ""));
            System.out.println(b.c + "->" + (b.next != null ? b.next.c : ""));
            System.out.println(c.c + "->" + (c.next != null ? c.next.c : ""));
            System.out.println(d.c + "->" + (d.next != null ? d.next.c : ""));
            System.out.println(f.c + "->" + (f.next != null ? f.next.c : ""));
            System.out.println(g.c + "->" + (g.next != null ? g.next.c : ""));
            System.out.println(h.c + "->" + (h.next != null ? h.next.c : ""));
            System.out.println(i.c + "->" + (i.next != null ? i.next.c : ""));
        }

        public static void setNextNode(Node root) {
            if(root == null || (root.left == null && root.right == null)) return;

            LinkedList<Node> q = new LinkedList<Node>();
            q.add(root);
            q.add(null);

            Node prev = null;
            while(!q.isEmpty()) {
                Node n = q.remove();
                if(n != null) {
                    if(prev != null) prev.next=n;
                    if(prev != null) System.out.print(prev.c);
                    prev = n;
                    System.out.print(prev.c+"\n");
                    if(n.left != null) q.add(n.left);
                    if(n.right != null) q.add(n.right);
                } else {
                    prev = null;
                    if(!q.isEmpty()) q.add(null);
                }
            }
        }

}
