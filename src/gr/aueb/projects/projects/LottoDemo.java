package gr.aueb.projects.projects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LottoDemo {
    public static void main(String[] args) throws FileNotFoundException {
        File inFile = new File("c:/temp1/lotto-in.txt");
        File outFile = new File("c:/temp1/lotto-out.txt");

        Scanner in = new Scanner(inFile);
        PrintStream ps = new PrintStream(outFile);

        final int N = 6;
        int[] row = new int[6];
        int counter = 0, lottoNum;

        ArrayList<Integer> inputNumbers = new ArrayList<>();

        while (in.hasNextInt()){
            lottoNum = in.nextInt();
            if (lottoNum != -1) {
                inputNumbers.add(lottoNum);
            } else {
                break;
            }
        }

        for (int i = 0; i <= inputNumbers.size() - N; i++){
            for (int j = i + 1; j <= inputNumbers.size() - N + 1; j++){
                for (int k = j + 1; k <= inputNumbers.size() - N + 2; k++){
                    for (int l = k + 1; l <= inputNumbers.size() - N + 3; l++){
                        for (int m = l + 1; m <= inputNumbers.size() - N + 4; m++){
                            for (int n = m + 1; n < inputNumbers.size(); n++){
                                row[0] = inputNumbers.get(i);
                                row[1] = inputNumbers.get(j);
                                row[2] = inputNumbers.get(k);
                                row[3] = inputNumbers.get(l);
                                row[4] = inputNumbers.get(m);
                                row[5] = inputNumbers.get(n);

                                Arrays.sort(row);

                                if (!isEven(row) && !isOdd(row)  && !isContiguous(row) && !isSameEnding(row) && !isSameTen(row)) {
                                    counter++;
                                    ps.printf("%d.\t%d\t%d\t%d\t%d\t%d\t%d\n", counter, row[0], row[1], row[2], row[3], row[4], row[5]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean isEven(int[] arr){
        int count = 0;

        for (int j : arr) {
            if (j % 2 == 0) {
                count++;
            }
        }

        return (count > 4);
    }

    public static boolean isOdd(int[] arr){
        int count = 0;

        for (int j : arr) {
            if (j % 2 != 0) {
                count++;
            }
        }

        return (count > 4);
    }

    public static boolean isContiguous(int[] arr){
        int count = 0;

        for (int i = 1; i <= arr.length; i++) {
            if (i == (arr.length -1)) break;
            if(arr[i] - arr[i-1] == 1){
                count++;
            }
        }

        return (count > 2);
    }

    public static boolean isSameEnding(int[] arr){
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i == (arr.length -1)) break;
            if ((arr[i] % 10) == (arr[i+1] % 10)) {
                count++;
            }
        }

        return (count > 3);
    }

    public static boolean isSameTen(int[] arr){
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
           if (i == (arr.length -1)) break;
            if ((arr[i] / 10) == (arr[i+1] / 10)) {
                count++;
            }
        }

        return (count > 3);
    }
}
