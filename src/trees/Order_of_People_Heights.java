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

        public static class SegTree {
            public int[] tree;
            public int size;

            public SegTree(int n) {
                this.size = n;
                tree = new int[4 * n + 2];
                build(0, n - 1, 0);
            }

            public int left(int i) {
                return 2 * i + 1;
            }

            public int right(int i) {
                return 2 * i + 2;
            }

            public int build(int start, int end, int index) {
                if (start > end)
                    return 0;
                if (start == end) {
                    tree[index] = 1;
                    return tree[index];
                }
                int mid = start + (end - start) / 2;
                tree[index] = build(start, mid, left(index)) + build(mid + 1, end, right(index));
                return tree[index];
            }

            public int query(int spaces, int start, int end, int index) {
                if (start > end)
                    return 0;
                if (start == end)
                    return start;

                int mid = start + (end - start) / 2;
                int leftSpace = tree[left(index)];
                if (leftSpace >= spaces)
                    return query(spaces, start, mid, left(index));
                else
                    return query(spaces - leftSpace, mid + 1, end, right(index));
            }

            public int update(int i, int value, int start, int end, int index) {
                if (start > end)
                    return 0;
                if (start == end) {
                    tree[index] = value;
                    return value;
                }
                int mid = start + (end - start) / 2;

                if (i <= mid)
                    update(i, value, start, mid, left(index));
                else
                    update(i, value, mid + 1, end, right(index));
                return tree[index] = tree[left(index)] + tree[right(index)];
            }
        }

        public static class Person implements Comparable<Person> {
            int height;
            int infront;

            public Person(int a, int b) {
                this.height = a;
                this.infront = b;
            }

            public int compareTo(Person u) {
                return height - u.height;
            }
        }

        public static List<Integer> order(List<Integer> A, List<Integer> B) {
            Person[] arr = new Person[A.size()];
            for (int i = 0; i < A.size(); i++)
                arr[i] = new Person(A.get(i), B.get(i));

            Arrays.sort(arr);

            SegTree s = new SegTree(A.size());

            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < A.size(); i++)
                ans.add(0);

            for (int i = 0; i < A.size(); i++) {
                int ind = s.query(arr[i].infront + 1, 0, A.size() - 1, 0);
                ans.set(ind, arr[i].height);
                s.update(ind, 0, 0, A.size() - 1, 0);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        List<Integer> heights = Stream.of(5, 3, 2, 6, 1, 4).collect(Collectors.toList());
        List<Integer> infronts = Stream.of(0, 1, 2, 0, 3, 2).collect(Collectors.toList());
        List<Integer> order = Editorial.order(heights, infronts);
        System.out.println("Actual order is: " + order);
    }


}
