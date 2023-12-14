import java.util.*;
import java.io.*;
import java.util.regex.*;
public class Main {
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
        }
    }
}