package AoC2023.day5.src;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.Map;

//My first solution was brute force and did not work for the actual data set

//This plan inspired by a python solution, had to improvise for the for/else loop using for/if with a boolean

//for each arraylist, create a ranges list
    //for each line in the arraylist, add each line of nums to ranges list
    //create a new list
    //for each seed in seeds list
        //for each num in ranges list
            //if b <= seed < (b+c)
                //append seed - b + a
                //break
        //else
            //append seed to new

    //seed = new (which will now be locations)

//print min of seed (locations)

public class part1 {
    public static void main(String[] args) throws IOException {
        ArrayList<Long> seeds = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Long>>> maps = new ArrayList<>();
        ArrayList<ArrayList<Long>> currentBlock = new ArrayList<>();

        File file = new File("day5/src/data.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();

        while (line != null) {
            if (line.contains("seeds:")) {
                line = br.readLine();
                if (line != null) {
                    String[] nums = line.split(" ");
                    for (String num : nums) {
                        seeds.add(Long.parseLong(num));
                    }
                }
            } else if (!line.matches(".*\\d+.*")) {
                if (!currentBlock.isEmpty()) {
                    maps.add(currentBlock);
                    currentBlock = new ArrayList<>();
                }
            } else {
                ArrayList<Long> lineData = new ArrayList<>();
                String[] nums = line.split(" ");
                for (String num : nums) {
                    lineData.add(Long.parseLong(num));
                }
                currentBlock.add(lineData);
            }
            line = br.readLine();
        }

        if (!currentBlock.isEmpty()) {
            maps.add(currentBlock);
        }
        br.close();

        for (ArrayList<ArrayList<Long>> map : maps) {

            ArrayList<ArrayList<Long>> ranges = new ArrayList<>();
            for(ArrayList<Long> l : map){
                ranges.add(l);
            }

            ArrayList<Long> newSeeds = new ArrayList<>();
            for(Long seed : seeds){
                boolean found = false;
                for(ArrayList<Long> range : ranges){
                    long a = range.get(0);
                    long b = range.get(1);
                    long c = range.get(2);
                    if((b <= seed) && (seed < (b+c))) {
                        newSeeds.add((seed - b) + a);
                        found = true;
                        break;
                    }
                }
                if(!found){
                    newSeeds.add(seed);
                }
            }
            seeds = newSeeds;
        }

        System.out.println(Collections.min(seeds));
    }
}