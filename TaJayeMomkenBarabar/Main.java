import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PriorityQueue<Integer> hard = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return Integer.compare(t1, integer);
            }
        });
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long max = 0;
        int temp;
        for (int i = 0; i < n; i++) {
            temp = scanner.nextInt();
            hard.add(temp);
            max += temp;
        }
        boolean zero = max % 2 == 0 ? true : false;
        int[] sorted = new int[n];
        int i = n - 1;
        int min = 0;
        while (hard.size() > 0) {
            sorted[i--] = hard.poll();
        }
        max = (max / 2) + (max % 2);
        if (n >= 2)
            System.out.println(minDiffrence(sorted, sorted[n - 1], 0, max, n - 2, sorted[0], zero));
        else
            System.out.println(sorted[0]);
    }

    public static long minDiffrence(int[] hard, long number1, long number2, long max, int index, long min, boolean zero) {
        if (index == 0)
            return Math.abs(Math.abs(number1 - number2) - hard[index]);
        if (number1 == max)
            if (zero)
                return 0;
            else
                return 1;
        if (number1 + hard[index] >= max + hard[1] + 1)
            return minDiffrence(hard, Long.max(number1, number2 + hard[index]),
                    Long.min(number1, number2 + hard[index]), max, index - 1, min, zero);
        long n1 = minDiffrence(hard, number1 + hard[index], number2, max, index - 1, min, zero);
        if (n1 == 0 || n1 == 1)
            return n1;
        long n2 = minDiffrence(hard, Long.max(number1, number2 + hard[index]),
                Long.min(number1, number2 + hard[index]), max, index - 1, min, zero);
        return Long.min(n1, n2);
    }
}