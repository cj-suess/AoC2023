package AoC2023.day2.src;

import java.util.*;
import java.io.*;
import java.util.regex.*;
public class part1 {
    public static void main(String[] args) throws IOException {
        File file = new File("day2/src/test.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        Map<String, Integer> colors = new HashMap<>();
        colors.put("red", 12);
        colors.put("blue", 14);
        colors.put("green", 13);
        ArrayList<Integer> possibleGames = new ArrayList<>();

        while(line != null){

                int foundMatches = 0;
                int counter = 0;
                int gameNumber = 0;

                String gameNumberRegex = "(\\d+)(?=:)";
                Pattern gameNumberPattern = Pattern.compile(gameNumberRegex);
                Matcher gameNumberMatcher = gameNumberPattern.matcher(line);

                String colorNumRegex = "(\\d+)(\\s+)(\\w+)";
                Pattern pattern = Pattern.compile(colorNumRegex);
                Matcher matcher = pattern.matcher(line);

                if(gameNumberMatcher.find()) {
                    gameNumber = Integer.parseInt(gameNumberMatcher.group(1));
                }

                while(matcher.find()) {
                    int num = Integer.parseInt(matcher.group(1));
                    String color = matcher.group(3);

                    if(colors.get(color) >= num){
                        counter++;
                    }

                    foundMatches++;
                }

                if(counter == foundMatches){
                    possibleGames.add(gameNumber);
                }

            line = br.readLine();
        }

        System.out.println(possibleGames);

        //sum possible games
        int sum = 0;
        for(int i = 0; i < possibleGames.size(); i++){
            sum += possibleGames.get(i);
        }
        System.out.println(sum);
    }
}