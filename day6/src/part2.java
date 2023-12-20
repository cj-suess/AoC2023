package AoC2023.day6.src;


import java.io.IOException;
import java.math.BigInteger;


public class part2{


    public static BigInteger calculateNumOfWaysToWin(BigInteger time, BigInteger distance){
        BigInteger result = BigInteger.ZERO;

        for(BigInteger i = BigInteger.ONE; i.compareTo(time) < 0; i = i.add(BigInteger.ONE)){
            if(i.multiply(time.subtract(i)).compareTo(distance) > 0){
                result = result.add(BigInteger.ONE);
            }
        }

        return result;
    }

    public static void main(String[]args) throws IOException {
        BigInteger time = BigInteger.valueOf(46807866);
        BigInteger distance = BigInteger.valueOf(214117714021024L);
        System.out.println(calculateNumOfWaysToWin(time, distance));

    }
}