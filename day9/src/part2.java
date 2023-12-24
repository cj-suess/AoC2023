package AoC2023.day9.src;

import java.io.*;
import java.util.*;

public class part2 {

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

        ArrayList<Integer> beginningSums = new ArrayList<>();

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

            //starting with the bottom array in nums, subtract the first element from the last array from the first element of the array above it and store in tempSum
            //then subtract tempSum from the first element in the array above it and update tempSum with that difference, continue until you reach the top of the array
            for(int i = nums.size() - 1; i > 0; i--) {
                tempSum = nums.get(i - 1)[0] - nums.get(i)[0];
                nums.get(i - 1)[0] = tempSum;
            }

            beginningSums.add(tempSum);

            line = reader.readLine();
        }

        //sum all the elements in beginningSums
        int sum = 0;
        for(int i = 0; i < beginningSums.size(); i++) {
            sum += beginningSums.get(i);
        }

        System.out.println(sum);
    }
}