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
    // 2. Sort the arrayList based on the hand type
    // 3. Iterate through the map and multiply the bid by the rank determined by the size of the map
            // Strongest would be the size of the map itself and the lowest would be 1

    public static String determineHandType(String hand){

        String result = "";
        int distinctCards = 0;
        Map<Character, Integer> foundCards = new HashMap<>();
        //creat regex for all letters and numbers
        String regex = "[a-zA-Z0-9]+";

        for(int i = 0; i < hand.length(); i++){
            Matcher m = Pattern.compile(regex).matcher(hand);
            while(m.find()){
                if(!foundCards.containsKey(hand.charAt(i))){
                    foundCards.put(hand.charAt(i), 1);
                    distinctCards++;
                } else {
                    foundCards.put(hand.charAt(i), foundCards.get(hand.charAt(i)) + 1);
                }
            }
        }

        if(distinctCards == 5){
            result = "High Card";
        } else if(distinctCards == 4){
            result = "One Pair";
        } else if(distinctCards == 3){
            if(foundCards.containsValue(3) && !foundCards.containsValue(2)){
                result = "Three of a kind";
            } else if(foundCards.containsValue(2) && foundCards.containsValue(1)){
                result = "Two of a kind";
            }
        } else if(distinctCards == 2){
            if(foundCards.containsValue(4)){
                result = "Four of a kind";
            } else if(foundCards.containsValue(3)){
                result = "Full House";
            }
        } else if(distinctCards == 1){
            result = "Five of a kind";
        }

        System.out.println(hand);
        System.out.println(foundCards);
        System.out.println(distinctCards);
        return result;
    }

    public static void main(String[] args) {

        System.out.println(determineHandType("555pp"));

    }

}