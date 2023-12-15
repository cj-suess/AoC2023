package AoC2023.day2.src;

import java.util.*;
import java.io.*;
import java.util.regex.*;
class part2 {
    public static void main(String[] args) throws IOException {
        File file = new File("day2/src/data.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        ArrayList<Integer> powerArray = new ArrayList<>();

        while(line != null){

            int redMax = 0;
            int greenMax = 0;
            int blueMax = 0;

            String colorNumRegex = "(\\d+)(\\s+)(\\w+)";
            Pattern pattern = Pattern.compile(colorNumRegex);
            Matcher matcher = pattern.matcher(line);

            while(matcher.find()) {
                int num = Integer.parseInt(matcher.group(1));
                String color = matcher.group(3);

                if(color.equals("red") && num > redMax){
                    redMax = num;
                } else if(color.equals("green") && num > greenMax){
                    greenMax = num;
                } else if(color.equals("blue")  && num > blueMax){
                    blueMax = num;
                }
            }

            int power = redMax * greenMax * blueMax;

            powerArray.add(power);

            line = br.readLine();
        }
        //sum powerArray
        int sum = 0;
        for(int i = 0; i < powerArray.size(); i++){
            sum += powerArray.get(i);
        }
        System.out.println(sum);
    }
}
