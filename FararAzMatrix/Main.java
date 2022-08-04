import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> numbers;
    static int[] max;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        numbers = new ArrayList<>();
        int current;
        scanner.nextInt();
        for (int i = 0; i < n - 1; i++) {
            current = scanner.nextInt();
            if (current != 1)
                numbers.add(current);
        }
        if (numbers.size() == 0) {
            System.out.println(n + n - 2);
            System.exit(0);
        }
        max = new int[numbers.size()];
        System.out.println(n + n - 2 - findMax());
    }

    public static int findMax() {
        int maximum = 0, index;
        max[0] = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > max[maximum]) {
                max[++maximum] = numbers.get(i);
            } else {
                index = binarySearch(numbers.get(i), 0, maximum);
                if (max[index] < numbers.get(i))
                    index++;
                if (index == -1)
                    index = 0;
                    max[index] = numbers.get(i);
            }
        }
        return maximum + 1;
    }

    static int binarySearch(int value, int start, int end) {
        if (end <= start)
            return end;
        int mid = (end + start) / 2;
        if (max[mid] < value)
            return binarySearch(value, mid + 1, end);
        return binarySearch(value, start, mid);
    }
}