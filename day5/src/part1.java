package AoC2023.day5.src;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.Map;

public class part1 {

    public static String readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        return line;
    }


    public static ArrayList<ArrayList<Integer>> getNums(File file) throws IOException {
        ArrayList<ArrayList<Integer>> nums = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while((line = br.readLine()) != null){
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(line);
            ArrayList<Integer> temp = new ArrayList<>();

            while(m.find()){
                temp.add(Integer.parseInt(m.group()));
            }
            if (!temp.isEmpty()) {
                nums.add(temp);
            }
        }
        br.close();
        return nums;
    }

    public static Map<Integer, Integer> buildMap(ArrayList<ArrayList<Integer>> nums) {
        Map<Integer, Integer> map = new LinkedHashMap<>();

        // Initial mapping from 0 up to the first number in the first sequence
        int firstNumber = nums.get(0).get(0);
        for (int i = 0; i < firstNumber; i++) {
            map.put(i, i);
        }

        // Process each sequence in nums
        for (int s = 0; s < nums.size(); s++) {
            ArrayList<Integer> sequence = nums.get(s);
            if (sequence.size() < 3) continue; // Skip if the sequence does not have at least three elements

            int start = sequence.get(0);
            int end = sequence.get(1);
            int value = sequence.get(2);

            // Calculate how many times the mapping should increment
            int incrementCount = end - start + 1;

            for (int i = 0; i < incrementCount; i++) {
                map.put(start + i, value + i);
            }
        }

        return map;
    }





    public static void main(String[] args) throws IOException {

        ArrayList<Integer> seedList = new ArrayList<>();
        Map<Integer,Integer> seedToSoil = new LinkedHashMap<>();
        Map<Integer,Integer> soilToFertilizer = new LinkedHashMap<>();
        Map<Integer,Integer> fertilizerToWater = new LinkedHashMap<>();
        Map<Integer,Integer> waterToLight = new LinkedHashMap<>();
        Map<Integer,Integer> lightToTemp = new LinkedHashMap<>();
        Map<Integer,Integer> tempToHumidity = new LinkedHashMap<>();
        Map<Integer,Integer> humidityToLocation = new LinkedHashMap<>();

        ArrayList<ArrayList<Integer>> temp = getNums(new File("day5/src/tests/seedToSoilTest.txt"));
        seedToSoil = buildMap(temp);
        System.out.println(seedToSoil);

    }
}