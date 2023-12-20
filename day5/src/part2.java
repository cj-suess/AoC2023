package AoC2023.day5.src;

import java.io.*;
import java.util.*;

//Incomplete, output too low

public class part2 {
    public static void main(String[] args) throws IOException {

        double startTime = System.nanoTime();
        ArrayList<Long> inputs = new ArrayList<>();
        ArrayList<Long> seeds = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Long>>> maps = new ArrayList<>();
        ArrayList<ArrayList<Long>> currentBlock = new ArrayList<>();

        File file = new File("day5/src/test.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();

        while (line != null) {
            if (line.contains("seeds:")) {
                line = br.readLine();
                if (line != null) {
                    String[] nums = line.split(" ");
                    for (String num : nums) {
                        inputs.add(Long.parseLong(num));
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

        for (int i = 0; i < inputs.size() - 1; i += 2) {
            seeds.add(inputs.get(i));
            seeds.add(inputs.get(i) + inputs.get(i + 1));
        }

        for (ArrayList<ArrayList<Long>> map : maps) {

            ArrayList<ArrayList<Long>> ranges = new ArrayList<>();
            for(ArrayList<Long> l : map){
                ranges.add(l);
            }

            ArrayList<Long> newSeeds = new ArrayList<>();
            while(seeds.size() > 0){
                boolean found = false;
                long start = seeds.remove(0);
                long end = seeds.remove(0);
                for(ArrayList<Long> range : ranges){
                    long a = range.get(0);
                    long b = range.get(1);
                    long c = range.get(2);
                    long overlapStart = Math.max(start, b);
                    long overlapEnd = Math.min(end, b + c);
                    if(overlapStart < overlapEnd){
                        newSeeds.add(overlapStart - b + a);
                        newSeeds.add(overlapEnd - b + a);
                        found = true;
                        if(overlapStart > start){
                            newSeeds.add(start);
                            newSeeds.add(overlapStart);
                        }
                        if(end > overlapEnd){
                            newSeeds.add(overlapEnd);
                            newSeeds.add(end);
                        }
                        break;
                    }
                }
                if(!found){
                    newSeeds.add(start);
                    newSeeds.add(end);
                }
            }
            seeds = newSeeds;
        }

        double endTime = System.nanoTime();
        System.out.println(Collections.min(seeds));
        System.out.println("Took "+(endTime - startTime)/1000000 + " ms");
    }
}