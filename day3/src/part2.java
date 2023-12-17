package AoC2023.day3.src;

import java.util.*;
import java.io.*;
import java.util.regex.*;

public class part2 {

    // Class to represent a tuple of row and column
    public static class RowColumnTuple {
        int row;
        int column;

        public RowColumnTuple(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + column + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RowColumnTuple that = (RowColumnTuple) o;
            return row == that.row && column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }




    public static void main(String[] args) throws IOException {
        File file = new File("day3/src/data.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        char[][] schematic = new char[140][140];

        // fill schematic with each character in file
        int row = 0;
        while (line != null) {
            for (int i = 0; i < line.length(); i++) {
                schematic[row][i] = line.charAt(i);
            }
            row++;
            line = br.readLine();
        }

        // creat map with tuples that represent coordinates for symbols and an empty arraylist
        Map<RowColumnTuple, ArrayList<Integer>> map = new HashMap<>();

        // fill map with all coordinates of any symbol that isn't 0-9 or a '.' and map to an empty arraylist
        String regex = "[^0-9.]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        for (int i = 0; i < schematic.length; i++) {
            for (int j = 0; j < schematic[i].length; j++) {
                matcher = pattern.matcher(String.valueOf(schematic[i][j]));
                if (matcher.find()) {
                    map.put(new RowColumnTuple(i, j), new ArrayList<>());
                }
            }
        }

        // fill map with numbers that are adjacent to the symbols in the map
        for (int i = 0; i < schematic.length; i++) {
            for (int j = 0; j < schematic[i].length; j++) {
                if (Character.isDigit(schematic[i][j])) {
                    // Start of a number
                    StringBuilder numberBuilder = new StringBuilder();
                    int startColumn = j;

                    // Read the entire number
                    while (j < schematic[i].length && Character.isDigit(schematic[i][j])) {
                        numberBuilder.append(schematic[i][j]);
                        j++;
                    }

                    int endColumn = j - 1;
                    int number = Integer.parseInt(numberBuilder.toString());

                    // Check adjacent positions around the entire number
                    for (int dj = startColumn - 1; dj <= endColumn + 1; dj++) {
                        for (int di = -1; di <= 1; di++) {
                            if (di == 0 && (dj >= startColumn && dj <= endColumn)) continue; // Skip the number itself

                            int ni = i + di;
                            int nj = dj;

                            // Check bounds
                            if (ni >= 0 && ni < schematic.length && nj >= 0 && nj < schematic[ni].length) {
                                if (!Character.isDigit(schematic[ni][nj])) {
                                    RowColumnTuple adjacentPosition = new RowColumnTuple(ni, nj);

                                    // Check if this position is already in the map
                                    if (map.containsKey(adjacentPosition)) {
                                        map.get(adjacentPosition).add(number);
                                    }
                                    // If it's not in the map, we just continue to the next number
                                }
                            }
                        }
                    }
                }
            }
        }

        int sum = 0;
        // for each key in map, if it has two values, multiply them together and add to sum
        for (RowColumnTuple key : map.keySet()) {
            if (map.get(key).size() == 2) {
                sum += map.get(key).get(0) * map.get(key).get(1);
            }
        }
        System.out.println(sum);
    }
}
