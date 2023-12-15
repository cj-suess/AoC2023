package AoC2023.day1.src;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class part2 {

    public static int wordToNum(String word){
        switch(word){
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            default:
                return -1;
        }
    }


    public static Map<Integer, Integer> findDigits(String line){

        Map<Integer, Integer> digits = new HashMap<>();

        for(int i = 0; i < line.length(); i++){
            if(Character.isDigit(line.charAt(i))){
                digits.put(i, Character.getNumericValue(line.charAt(i)));
            }
        }

        return digits;
    }

    public static Map<Integer, String> findWords(String line){

        int startingIndex = 0;

        Map<Integer, String> words = new HashMap<>();

        Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine)", Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(line);

        while(matcher.find(startingIndex)){
            startingIndex = matcher.start() + 1;
            words.put(matcher.start(), matcher.group());
        }

        return words;
    }

    public static void main(String[] args) throws IOException {

        File file = new File("day1/src/data.txt");
        FileReader fReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fReader);
        String line = bufferedReader.readLine();

        //make string arrayList called concatenatedDigits with a length equal to the number of lines in the file
        ArrayList<String> concatenatedDigits = new ArrayList<>();

        while (line != null) {

            Map<Integer,Integer> digits = findDigits(line);
            Map<Integer,String> words = findWords(line);

            int firstDigit = -1;
            int secondDigit = -1;
            int lowestDigitIndex = -1;
            int lowestWordIndex = -1;
            int highestDigitIndex = -1;
            int highestWordIndex = -1;

            //find the lowest index of the digits from both maps
            if(!digits.isEmpty()){
                lowestDigitIndex = Collections.min(digits.keySet());
            }
            if(!words.isEmpty()){
                lowestWordIndex = Collections.min(words.keySet());
            }

            if(lowestDigitIndex == -1 && lowestWordIndex != -1){
                firstDigit = wordToNum(words.get(lowestWordIndex));
            } else if(lowestDigitIndex != -1 && lowestWordIndex == -1) {
                firstDigit = digits.get(lowestDigitIndex);
            } else if(lowestDigitIndex != -1 && lowestWordIndex != -1){
                if(lowestDigitIndex < lowestWordIndex){
                    firstDigit = digits.get(lowestDigitIndex);
                } else {
                    firstDigit = wordToNum(words.get(lowestWordIndex));
                }
            }

            //find the highest index of the digits from both maps
            if(!digits.isEmpty()){
                highestDigitIndex = Collections.max(digits.keySet());
            }
            if(!words.isEmpty()){
                highestWordIndex = Collections.max(words.keySet());
            }

            if(highestDigitIndex == -1 && highestWordIndex != -1){
                secondDigit = wordToNum(words.get(highestWordIndex));
            } else if(highestDigitIndex != -1 && highestWordIndex == -1) {
                secondDigit = digits.get(highestDigitIndex);
            } else if(highestDigitIndex != -1 && highestWordIndex != -1){
                if(highestDigitIndex > highestWordIndex){
                    secondDigit = digits.get(highestDigitIndex);
                } else {
                    secondDigit = wordToNum(words.get(highestWordIndex));
                }
            }

            //concatenate the digits
            concatenatedDigits.add(Integer.toString(firstDigit) + Integer.toString(secondDigit));

            line = bufferedReader.readLine();
        }

        //sum the concatenated digits
        int sum = 0;
        for(int i = 0; i < concatenatedDigits.size(); i++){
            sum += Integer.parseInt(concatenatedDigits.get(i));
        }

        System.out.println(sum);
    }
}