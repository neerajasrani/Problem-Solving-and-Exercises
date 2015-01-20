/**
 * Created by neeraj on 1/12/15.
 *
 * There is a trie like following. Please search a string in the given trie and return if it exists or not.

    $

 *a         r            s      t
 a   i    o         a      i
 h k     b   h        m      m
 u  e       i  a
 l   s      t  n
 h
 *
 * Please define the Node class and if you need more classes please define them too*/


public class MarketoInterview {

    public static class Node {
        public Character getC() {
            return c;
        }

        private Character c;

        public Node[] getChilds() {
            return childs;
        }

        public void setChilds(Node[] childs) {
            this.childs = childs;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        private Node[] childs;
        private Node parent;

        public Node(Character c, Node[] childs, Node parent) {
            this.c = c;
            this.childs = childs;
            this.parent = parent;
        }

        public Node(Character c) {
            this.c = c;

        }

        public Node() {
            this.c = null;
            this.childs = null;
            this.parent = null;

        }
    }

    public static boolean isKeyExists(Node root, String key) {

        // Write implementation
        if(key == null) return false;


        // Find Key
        return findKey(root, key, 0, 0);
    }

    private static boolean findKey(Node root, String key, int num, int depth) {

        if(key.length() == num) return true;
        if(root == null) return false;

        char d = key.charAt(num);

        try {

            // Find if key exists on the same level

            for (int i = 0; i < root.getChilds().length; i++) {
                    if (root.getChilds()[i].getC().equals(d)) {
                        root = root.getChilds()[i];
                        ++num;
                        ++depth;
                        findKey(root, key, num, depth);
                    } else if (depth == 0) {

                    } else if (depth > 0) {
                        for (int j = 0; j < depth; j++) {
                            root = root.getParent();
                        }
                        findKey(root, key, 0, 0);
                    }
                    else {
                        root = root.getChilds()[i];
                        findKey(root, key, 0, 0);
                    }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static Node insertIntoTrie() {
        Node l11, l12, l13, l14, l21, l22, l23, l24, l25, l31, l32, l33, l34, l35, l36, l41, l42, l43, l44, l51, l52, l53, l54, l61;
        l11 = l12 = l13 = l14 = l21 = l22 = l23 = l24 = l25 = l31 = l32 = l33 = l34 = l35 = l36 = l41 = l42 = l43 = l44 = l51 = l52 = l53 = l54 = l61 = null;

        Node root = new Node('$');
        l51 = new Node('l', new Node[] {l61}, l42);

        l41 = new Node('u', new Node[]{l51}, l31);
        l42 = new Node('e', new Node[]{l52}, l32);
        l31 = new Node('h', new Node[]{l41}, l21);
        l32 = new Node('k', new Node[]{l42}, l22);

        l21 = new Node('a', new Node[]{l31}, l11);
        l22 = new Node('i', new Node[]{l32}, l11);

        // First level children
        l11 = new Node('a', new Node[] {l21, l22}, root);
        l12 = new Node('r', new Node[] {l23}, root);
        l13 = new Node('s', new Node[]{l24}, root);
        l14 = new Node('t', new Node[]{l25}, root);

        // Second level children
        l23 = new Node('o', new Node[]{l33, l34}, l12);
        l24 = new Node('a', new Node[]{l35}, l13);
        l25 = new Node('i', new Node[]{l36}, l13);


        l33 = new Node('b', new Node[]{l43}, l23);
        l34 = new Node('h', new Node[]{l43}, l23);
        l35 = new Node('m', null, l24);
        l36 = new Node('m', null, l25);



        // Sixth level children
        l61 = new Node('h', null, l51);

        // Fifth level children
        l52 = new Node('s', null, l42);
        l53 = new Node('t', null, l43);
        l54 = new Node('n', null, l44);

        // Fourth level children

        l43 = new Node('i', new Node[]{l53}, l33);
        l44 = new Node('a', new Node[]{l54}, l34);

        // Third level children
        Node [] child = new Node[4];
        root.childs = child;

        root.childs[0] = l11;
        root.childs[1] = l12;
        root.childs[2] = l13;
        root.childs[3] = l14;

        return root;

    }

    public static void main (String[] args) {
        try {
            Node root = insertIntoTrie();
            boolean result = isKeyExists(root, "rohit");

            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}

