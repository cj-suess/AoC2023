package AoC2023.day7.src;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class part2 {

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

    public static ArrayList<Character> cardRankings = new ArrayList<>(Arrays.asList('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T' ,'Q', 'K', 'A'));


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

        String stronger = "";

        ArrayList<Character> cardOptions = new ArrayList<>(Arrays.asList('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T' ,'Q', 'K', 'A'));

        String newHand1 = "";
        String newHand2 = "";

        int hand1TypeIndex = handTypes.indexOf(determineHandType(hand1));
        int hand2TypeIndex = handTypes.indexOf(determineHandType(hand2));
        int newHand1TypeIndex = -1;
        int newHand2TypeIndex = -1;

        //determine if either hand has a J in it
        boolean hand1HasJ = hand1.contains("J");
        boolean hand2HasJ = hand2.contains("J");

        //if either has a J then loop through cardOptions replacing J with the next card until the highest card rank is obtained
        if (hand1HasJ) {
            for (int i = 0; i < cardOptions.size(); i++) {
                newHand1 = hand1.replace("J", String.valueOf(cardOptions.get(i)));
                newHand1TypeIndex = handTypes.indexOf(determineHandType(newHand1));
                if (newHand1TypeIndex > hand1TypeIndex) {
                    break;
                }
            }
        }
        if (hand2HasJ) {
            for (int i = 0; i < cardOptions.size(); i++) {
                newHand2 = hand2.replace("J", String.valueOf(cardOptions.get(i)));
                newHand2TypeIndex = handTypes.indexOf(determineHandType(newHand2));
                if (newHand2TypeIndex > hand2TypeIndex) {
                    break;
                }
            }
        }

        //compare the new cards and set strongest to the stronger hand
        if (newHand1TypeIndex > newHand2TypeIndex) {
            stronger = hand1;
        } else if (newHand2TypeIndex > newHand1TypeIndex) {
            stronger = hand2;
        } else {
                int i = 0;
                int j = 0;
                //if the hand types are the same then determine if both original cards contained a J
                if (hand1HasJ && hand2HasJ) {
                    //if both hands contained a J then compare the hands by looping through each card until on hand has a higher card first
                    for(; i < hand1.length() && j < hand2.length(); i++, j++) {
                        if (cardRankings.indexOf(hand1.charAt(i)) > cardRankings.indexOf(hand2.charAt(j))) {
                            stronger = hand1;
                            break;
                        } else if (cardRankings.indexOf(hand2.charAt(j)) > cardRankings.indexOf(hand1.charAt(i))) {
                            stronger = hand2;
                            break;
                        }
                    }
                }
                //else if only one original hand contains a J then compare its original hand with the new hand of the other hand
                else if(hand1HasJ && !hand2HasJ){
                    for(; i < hand1.length() && j < newHand2.length(); i++, j++) {
                        if (cardRankings.indexOf(hand1.charAt(i)) > cardRankings.indexOf(newHand2.charAt(j))) {
                            stronger = hand1;
                            break;
                        } else if (cardRankings.indexOf(newHand2.charAt(j)) > cardRankings.indexOf(hand1.charAt(i))) {
                            stronger = hand2;
                            break;
                        }
                    }
                }
                else if(!hand1HasJ && hand2HasJ){
                    for(; i < newHand1.length() && j < hand2.length(); i++, j++) {
                        if (cardRankings.indexOf(newHand1.charAt(i)) > cardRankings.indexOf(hand2.charAt(j))) {
                            stronger = hand1;
                            break;
                        } else if (cardRankings.indexOf(hand2.charAt(j)) > cardRankings.indexOf(newHand1.charAt(i))) {
                            stronger = hand2;
                            break;
                        }
                    }
                }
                else if(!hand1HasJ && !hand2HasJ){
                    for(; i < newHand1.length() && j < newHand2.length(); i++, j++) {
                        if (cardRankings.indexOf(newHand1.charAt(i)) > cardRankings.indexOf(newHand2.charAt(j))) {
                            stronger = hand1;
                            break;
                        } else if (cardRankings.indexOf(newHand2.charAt(j)) > cardRankings.indexOf(newHand1.charAt(i))) {
                            stronger = hand2;
                            break;
                        }
                    }
                }
            }
//        System.out.println("Original Hand 1: " + hand1);
//        System.out.println("New Hand 1: " + newHand1);
//        System.out.println("Original Hand 2: " + hand2);
//        System.out.println("New Hand 2: " + newHand2);
//        System.out.println("Stronger Hand: " + stronger);

        return stronger;
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

    public static void main(String[] args) throws IOException {

//        File file = new File("day7/src/data.txt");
//        FileReader fr = new FileReader(file);
//        BufferedReader br = new BufferedReader(fr);
//        String line = br.readLine();
//
//        Map<String, ArrayList<String>> hands = new HashMap<>();
//
//        while(line != null){
//            //fill hands
//            String[] hand = line.split(" ");
//            ArrayList<String> handInfo = new ArrayList<>();
//            //first index is the hand type and second index is the bid
//            handInfo.add(determineHandType(hand[0]));
//            handInfo.add(hand[1]);
//            hands.put(hand[0], handInfo);
//            line = br.readLine();
//        }
//
//        //sort hands
//        hands = sortHands(hands);
//
//        //iterate through map and multiply bid by rank
//        int rank = hands.size();
//        long total = 0;
//        for (Map.Entry<String, ArrayList<String>> entry : hands.entrySet()) {
//            total += Integer.parseInt(entry.getValue().get(1)) * rank;
//            rank--;
//        }
//
//        System.out.println(total);
//
//
//        for (Map.Entry<String, ArrayList<String>> entry : hands.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue().get(0) + " " + entry.getValue().get(1));
//        }

        System.out.println(determineHandType("5A643"));
    }

}
