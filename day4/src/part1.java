package AoC2023.day4.src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class part1 {

    public static void main(String[] args) throws IOException {

        File file = new File("day4/src/data.txt");

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            int totalPoints = 0;

            Map<Integer, Integer> map = new HashMap<>();

            while(line != null){

                int counter = 0;
                String cardNumber = null;

                String cardNum = line.substring(0, line.indexOf(":"));
                String winningNums = line.substring(line.indexOf(":"), line.indexOf("|"));
                String scratchNums = line.substring(line.indexOf("|") + 1);

                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(cardNum);

                if(matcher.find()){
                    cardNumber = matcher.group();
                }

                matcher = pattern.matcher(winningNums);

                Set<String> winningNumsSet = new HashSet<>();

                while(matcher.find()){
                    winningNumsSet.add(matcher.group());
                }

                matcher = pattern.matcher(scratchNums);
                while(matcher.find()){
                    if(winningNumsSet.contains(matcher.group())){
                        counter++;
                    }
                }

                map.put(Integer.parseInt(cardNumber), counter);

                int points;

                if(counter == 0){
                    points = 0;
                } else {
                    points = 1;
                }

                for(int i = 1; i < counter; i++){
                    points *= 2;
                }

                totalPoints += points;
                line = br.readLine();
            }

            System.out.println(totalPoints);
    }
}
