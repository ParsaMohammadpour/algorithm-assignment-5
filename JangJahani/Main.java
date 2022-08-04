import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String s1, s2;
    static int max;
    static int[][] found;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            s1 = reader.readLine();
            s2 = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        max = Integer.max(s1.length(), s2.length());
        found = new int[max][max];
        System.out.println(findMinChanges(0, 0, 0));
    }

    private static int findMinChanges(int start1, int start2, int current) {
        if (current > max)
            return 3000;
        if (start2 == s2.length() || start1 == s1.length())
            return s1.length() - start1 + s2.length() - start2;
        if (found[start1][start2] != 0)
            return found[start1][start2];
        if (s1.charAt(start1) == s2.charAt(start2))
            return found[start1][start2] = findMinChanges(start1 + 1, start2 + 1, current);
        int number1 = findMinChanges(start1 + 1, start2 + 1, current + 1);
        int number2 = findMinChanges(start1 + 1, start2, current + 1);
        int number3 = findMinChanges(start1, start2 + 1, current + 1);
        return found[start1][start2] = 1 + Integer.min(Integer.min(number1, number2), number3);
    }
}