package AoC2023.day7.src;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class part1 {

    //Map<String, ArrayList<String>> hands = new HashMap<String, ArrayList<String>>();
    //map the hands to an arraylist that holds the type of hand that it is and its bid as a string
    //then sort map based on each hand's hand type

    //Need method to determine hand type
    //Need method to determine which of two hands are stronger

    // 1. Map the hands to an arrayList that holds the bid at first index and the hand type at second index
    // 2. Sort the arrayList based on the hand type using a method to determine the strength of two hands
    // 3. Iterate through the map and multiply the bid by the rank determined by the size of the map
        // Strongest would be the size of the map itself and the lowest would be 1

    public static ArrayList<String> handTypes = new ArrayList<>(Arrays.asList("High Card", "One Pair", "Two of a kind", "Three of a kind", "Full House", "Four of a kind", "Five of a kind"));

    public static String determineHandType(String hand) {

        String result = "";
        int distinctCards = 0;
        Map<Character, Integer> foundCards = new HashMap<>();
        //creat regex for all letters and numbers
        String regex = "[a-zA-Z0-9]+";

        for (int i = 0; i < hand.length(); i++) {
            Matcher m = Pattern.compile(regex).matcher(hand);
            while (m.find()) {
                if (!foundCards.containsKey(hand.charAt(i))) {
                    foundCards.put(hand.charAt(i), 1);
                    distinctCards++;
                } else {
                    foundCards.put(hand.charAt(i), foundCards.get(hand.charAt(i)) + 1);
                }
            }
        }

        if (distinctCards == 5) {result = "High Card";}
        else if (distinctCards == 4) {result = "One Pair";}
        else if (distinctCards == 3) {
            if (foundCards.containsValue(3) && !foundCards.containsValue(2)) {result = "Three of a kind";}
            else if (foundCards.containsValue(2) && foundCards.containsValue(1)) {result = "Two of a kind";}
        } else if (distinctCards == 2) {
            if (foundCards.containsValue(4)) {result = "Four of a kind";}
            else if (foundCards.containsValue(3)) {result = "Full House";}
        } else if (distinctCards == 1) {result = "Five of a kind";}

        return result;
    }
    public static String determineHandRank(String hand1, String hand2) {

        String strongest = "";

        if(handTypes.indexOf(determineHandType(hand1)) > handTypes.indexOf(determineHandType(hand2))) {
            strongest = hand1;
        } else if (handTypes.indexOf(determineHandType(hand1)) < handTypes.indexOf(determineHandType(hand2))) {
            strongest = hand2;
        } else {
            int i = 0;
            int j = 0;
            for(; i < hand1.length() && j < hand2.length(); i++, j++) {
                if(Character.isLetter(hand1.charAt(i)) && Character.isLetter(hand2.charAt(j))) {
                    if(hand1.charAt(i) < hand2.charAt(j)) {
                        strongest = hand1;
                        break;
                    } else if (hand1.charAt(i) > hand2.charAt(j)) {
                        strongest = hand2;
                        break;
                    }
                } else if (Character.isDigit(hand1.charAt(i)) && Character.isDigit(hand2.charAt(j))) {
                    if(hand1.charAt(i) > hand2.charAt(j)) {
                        strongest = hand1;
                        break;
                    } else if (hand1.charAt(i) < hand2.charAt(j)) {
                        strongest = hand2;
                        break;
                    }
                } else if (Character.isLetter(hand1.charAt(i)) && Character.isDigit(hand2.charAt(j))) {
                    strongest = hand1;
                    break;
                } else if (Character.isDigit(hand1.charAt(i)) && Character.isLetter(hand2.charAt(j))) {
                    strongest = hand2;
                    break;
                }
            }
        }
        return strongest;
    }

    public static Map<String, ArrayList<String>> sortHands(Map<String, ArrayList<String>> hands) {

        // Convert the map entries to a list for sorting
        List<Map.Entry<String, ArrayList<String>>> entries = new ArrayList<>(hands.entrySet());

        // Sort the list based on the hand rank
        entries.sort((entry1, entry2) -> {
            String hand1 = entry1.getKey();
            String hand2 = entry2.getKey();
            String strongerHand = determineHandRank(hand1, hand2);

            // If hand1 is stronger, sort it before hand2, otherwise after
            return hand1.equals(strongerHand) ? -1 : 1;
        });

        // Create a new LinkedHashMap to maintain the order
        Map<String, ArrayList<String>> sortedHands = new LinkedHashMap<>();
        for (Map.Entry<String, ArrayList<String>> entry : entries) {
            sortedHands.put(entry.getKey(), entry.getValue());
        }

        return sortedHands;
    }




    public static void swap(Map<String, ArrayList<String>> hands, String key1, String key2) {
        //swap by keys
        ArrayList<String> temp = hands.get(key1);
        hands.put(key1, hands.get(key2));
        hands.put(key2, temp);
    }

    public static void main(String[] args) throws IOException {

        File file = new File("day7/src/test2.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        Map<String, ArrayList<String>> hands = new HashMap<>();

        while(line != null){
            //fill hands
            String[] hand = line.split(" ");
            ArrayList<String> handInfo = new ArrayList<>();
            //first index is the hand type and second index is the bid
            handInfo.add(determineHandType(hand[0]));
            handInfo.add(hand[1]);
            hands.put(hand[0], handInfo);
            line = br.readLine();
        }

        //sort hands
        hands = sortHands(hands);

        //iterate through map and multiply bid by rank
        int rank = hands.size();
        long total = 0;
        for (Map.Entry<String, ArrayList<String>> entry : hands.entrySet()) {
            total += Integer.parseInt(entry.getValue().get(1)) * rank;
            rank--;
        }

        System.out.println(total);


        for (Map.Entry<String, ArrayList<String>> entry : hands.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().get(0) + " " + entry.getValue().get(1));
        }

        System.out.println(determineHandRank("A7774 ", "2AAA4 "));

    }

}