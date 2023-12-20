package AoC2023.day6.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part1{


    public static int calculateNumOfWaysToWin(int time, int distance){
        int result = 0;

        for(int i = 1; i < time; i++){
            if(i * (time -i) > distance){
                result++;
            }
        }

        return result;
    }

    public static void main(String[]args) throws IOException {

        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<Integer> times = new ArrayList<>();
        ArrayList<Integer> distances = new ArrayList<>();

        File file = new File("day6/src/data.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        String regex = ("\\d+");

        while(line != null){

            Matcher matcher = Pattern.compile(regex).matcher(line);

            if(line.startsWith("Time:")){
                while(matcher.find()){
                    times.add(Integer.parseInt(matcher.group()));
                }
            }
            if(line.startsWith("Distance:")){
                while(matcher.find()){
                    distances.add(Integer.parseInt(matcher.group()));
                }
            }
            line = br.readLine();
        }

        for(int i = 0; i < times.size(); i++){
            nums.add(calculateNumOfWaysToWin(times.get(i), distances.get(i)));
        }

        int result = 1;
        for(int i = 0; i < nums.size(); i++){
            result *= nums.get(i);
        }
        System.out.println(result);
    }
}