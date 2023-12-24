package AoC2023.day9.src;

import java.io.*;
import java.util.*;

public class part1 {

    public static int[] findDifferences(int[] array) {
        int[] differences = new int[array.length - 1];

        int i = 0;
        int j = 1;
        for(; i < array.length - 1; i++, j++) {
            differences[i] = array[j] - array[i];
        }

        return differences;
    }

    public static String[] convertToStringArray(String line) {
        String[] stringArray = line.split(" ");
        return stringArray;
    }

    public static int[] convertToIntArray(String[] stringArray){
        int[] intArray = new int[stringArray.length];
        for(int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }


    public static void main(String[] args) throws IOException {

        File file = new File("day9/src/data.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        ArrayList<Integer> endingSums = new ArrayList<>();

        while(line != null) {
            ArrayList<int[]> nums = new ArrayList<>();

            nums.add(convertToIntArray(convertToStringArray(line)));

            ArrayList <int[]> differences = new ArrayList<>();

            //while the sum of the differences is not 0, find the next set of differences and add it to nums
            while(nums.get(nums.size() - 1).length != 1) {
                differences.add(findDifferences(nums.get(nums.size() - 1)));
                nums.add(differences.get(differences.size() - 1));
            }

            int tempSum = 0;

            //starting with the last array in nums, add the last element to the last element of the previous array and store in tempSum
            //then add tempSum to the last element in the array above it and update tempSum with that sum, continue until you reach the top of the array
            for(int i = nums.size() - 1; i > 0; i--) {
                tempSum = nums.get(i)[nums.get(i).length - 1] + nums.get(i - 1)[nums.get(i - 1).length - 1];
                nums.get(i - 1)[nums.get(i - 1).length - 1] = tempSum;
            }

            endingSums.add(tempSum);

            line = reader.readLine();
        }

        //sum all the endingSums
        int sum = 0;
        for(int i = 0; i < endingSums.size(); i++) {
            sum += endingSums.get(i);
        }
        System.out.println(sum);
    }
}