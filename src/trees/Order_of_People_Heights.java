package trees;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order_of_People_Heights {

    /**
     * Solved using special binary search tree. O(nlogn)
     */
    static class Stackoverflow {

        private static class Node {
            Node left, right;
            int value;
            public final Person person;

            public Node(Person person) {
                this.value = 1;
                this.person = person;
            }
        }

        private static class Person {
            public final int height;
            public final int frontCount;

            Person(int height, int frontCount) {
                this.height = height;
                this.frontCount = frontCount;
            }
        }

        public List<Integer> order(List<Integer> heights, List<Integer> frontCounts) {
            Person[] persons = new Person[heights.size()];

            for (int i = 0; i < persons.length; i++)
                persons[i] = new Person(heights.get(i), frontCounts.get(i));

            Arrays.sort(persons, (p1, p2) -> Integer.compare(p2.height, p1.height));

            Node root = new Node(persons[0]);

            for (int i = 1; i < persons.length; i++) {
                insert(root, persons[i]);
            }

            List<Integer> order = new ArrayList<>();
            inOrderPrint(root, order);
            return order;
        }


        private void insert(Node root, Person p) {
            insert(root, p, p.frontCount);
        }

        private void insert(Node root, Person p, int value) {
            if (value < root.value) { // should insert to the left
                if (root.left == null) {
                    root.left = new Node(p);
                } else {
                    insert(root.left, p, value);
                }
                root.value++; // Increase the current Person value while descending left!
            } else { // insert to the right
                if (root.right == null) {
                    root.right = new Node(p);
                } else {
                    insert(root.right, p, value - root.value);
                }
            }
        }

        private void inOrderPrint(Node root, List<Integer> order) {
            if (root == null)
                return;

            inOrderPrint(root.left, order);

            order.add(root.person.height);

            inOrderPrint(root.right, order);
        }
    }

    /**
     * O(n2) and simple.
     */
    class Lightweight {

        class Person {
            int height, infront;

            Person(int height, int infront) {
                this.height = height;
                this.infront = infront;
            }
        }

        public ArrayList<Integer> order(ArrayList<Integer> A, ArrayList<Integer> B) {

            ArrayList<Person> persons = new ArrayList<Person>();

            for (int i = 0; i < A.size(); i++) {
                Person person = new Person(Integer.valueOf(A.get(i)), Integer.valueOf(B.get(i)));
                persons.add(person);
            }

            Collections.sort(persons, new Comparator<Person>() {
                        public int compare(Person p1, Person p2) {
                            if (p1.height < p2.height) {
                                return -1;
                            } else if (p1.height == p2.height) {
                                if (p1.infront < p2.infront) {
                                    return -1;
                                } else {
                                    return 1;
                                }
                            } else {
                                return 1;
                            }
                        }
                    }
            );

            ArrayList<Integer> result = new ArrayList<>();

            for (int i = 0; i < A.size(); i++) {
                result.add(-1);
            }

            for (int i = 0; i < persons.size(); i++) {
                Person person = persons.get(i);
                int count = person.infront;
                for (int j = 0; j < result.size(); j++) {
                    if (count == 0 && result.get(j).equals(-1)) {
                        result.set(j, person.height);
                        break;
                    } else if (count != 0 && result.get(j).equals(-1)) {
                        count--;
                    }
                }
            }

            return result;

        }
    }

    /**
     * Solved using SegmentTree. O(nlogn)
     */
    static class Editorial {

        public static List<Integer> order(List<Integer> A, List<Integer> B) {
            Person[] arr = new Person[A.size()];
            for (int i = 0; i < A.size(); i++)
                arr[i] = new Person(A.get(i), B.get(i));

            Arrays.sort(arr);

            SegTree segTree = new SegTree(A.size());

            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < A.size(); i++)
                ans.add(0);

            for (int i = 0; i < A.size(); i++) {
                int ind = segTree.query(arr[i].infront + 1);
                ans.set(ind, arr[i].height);
                segTree.update(ind, 0);
            }
            return ans;
        }

        private static class SegTree {
            private int[] tree;
            private int n;

            SegTree(int n) {
                this.n = n;
                //The max size of segment tree array is about 2 * 2 ^ (log2(n) + 1)
                int maxSize = (int) (2 * Math.pow(2.0, Math.floor((Math.log((double) n) / Math.log(2.0)) + 1)));
                this.tree = new int[maxSize];
                build(0, n - 1, 0);
            }

            int left(int i) {
                return 2 * i + 1;
            }

            int right(int i) {
                return 2 * i + 2;
            }

            int build(int ss, int se, int si) {
                if (ss == se) {
                    tree[si] = 1;
                    return tree[si];
                }
                int mid = ss + (se - ss) / 2;
                tree[si] = build(ss, mid, left(si)) + build(mid + 1, se, right(si));
                return tree[si];
            }

            int query(int spaces) {
                return query(0, n - 1, 0, spaces);
            }

            private int query(int ss, int se, int si, int spaces) {
                if (ss == se)
                    return ss;

                int mid = ss + (se - ss) / 2;
                int leftSpace = tree[left(si)];
                if (leftSpace >= spaces)
                    return query(ss, mid, left(si), spaces);
                else
                    return query(mid + 1, se, right(si), spaces - leftSpace);
            }

            void update(int i, int value) {
                update(0, n - 1, 0, i, value);
            }

            private void update(int ss, int se, int si, int i, int value) {
                if (ss == se) {
                    tree[si] = value;
                    return;
                }

                int mid = ss + (se - ss) / 2;

                if (i <= mid)
                    update(ss, mid, left(si), i, value);
                else
                    update(mid + 1, se, right(si), i, value);

                tree[si] = tree[left(si)] + tree[right(si)];
            }
        }

        private static class Person implements Comparable<Person> {
            int height;
            int infront;

            Person(int a, int b) {
                this.height = a;
                this.infront = b;
            }

            public int compareTo(Person u) {
                return height - u.height;
            }
        }

    }

    public static void main(String[] args) {
        List<Integer> heights = Stream.of(5, 3, 2, 6, 1, 4).collect(Collectors.toList());
        List<Integer> infronts = Stream.of(0, 1, 2, 0, 3, 2).collect(Collectors.toList());
        List<Integer> order = Editorial.order(heights, infronts);

        System.out.println("Actual order is: " + order);

        heights = Stream.of(5, 1, 3).collect(Collectors.toList());
        infronts = Stream.of(0, 2, 1).collect(Collectors.toList());
        order = Editorial.order(heights, infronts);

        System.out.println("Actual order is: " + order);
    }


}
