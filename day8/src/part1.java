package AoC2023.day8.src;

import java.util.*;
import java.io.*;
import java.util.regex.*;

public class part1 {

    public static Node root;
    public static Map<String, Node> nodeMap = new HashMap<>();

    public static class Node {

        public String value;
        public Node left;
        public Node right;

        public Node(String value) {
            this.value = value;
        }

        public void addLeft(Node left) {
            this.left = left;
        }

        public void addRight(Node right) {
            this.right = right;
        }

        public String toString() {
            return this.value;
        }
    }

    public int traverseTree(ArrayList<String> instructions) {
        String target = "ZZZ";
        int accumulator = 0;
        int index = 0;
        Node current = nodeMap.get("AAA");

        // Repeat instructions until the target is found
        while (true) {
            // Cycle back to the beginning of the instructions if we reach the end
            if (index >= instructions.size()) {
                index = 0;
            }

            String instruction = instructions.get(index);
            if (instruction.equals("R")) {
                current = current.right;
            } else if (instruction.equals("L")) {
                current = current.left;
            }

            // Increment accumulator for each node visited
            accumulator++;

            // Check if the target is found
            if (current != null && current.value.equals(target)) {
                System.out.println(accumulator);
                break;
            }

            index++;
        }

        return accumulator;
    }

    public static void main(String[] args) throws IOException {
        part1 p = new part1();
        ArrayList<String> instructions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("day8/src/data.txt"))) {
            String line;
            String regex = "R|L";
            line = br.readLine();
            Matcher m = Pattern.compile(regex).matcher(line);

            while (m.find()) {
                instructions.add(m.group());
            }

            regex = "[A-Z]{3}";
            while ((line = br.readLine()) != null) {
                m = Pattern.compile(regex).matcher(line);
                ArrayList<String> temp = new ArrayList<>();
                while (m.find()) {
                    temp.add(m.group());
                }
                if (!temp.isEmpty()) {
                    String key = temp.remove(0);
                    Node currentNode = nodeMap.getOrDefault(key, new Node(key));
                    nodeMap.putIfAbsent(key, currentNode);

                    for (int i = 0; i < temp.size(); i++) {
                        Node childNode = nodeMap.getOrDefault(temp.get(i), new Node(temp.get(i)));
                        nodeMap.putIfAbsent(temp.get(i), childNode);
                        if (i == 0) {
                            currentNode.addLeft(childNode);
                        } else {
                            currentNode.addRight(childNode);
                        }
                    }

                    if (root == null) {
                        root = currentNode;
                    }
                }
            }
        }

        p.traverseTree(instructions);
    }
}
