import java.util.Scanner;

public class Main {
    static long[] pre;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n < 5) {
            switch (n) {
                case 1: {
                    System.out.println(2);
                }
                break;
                case 2: {
                    System.out.println(4);
                }
                break;
                case 3: {
                    System.out.println(4);
                }
                break;
                case 4: {
                    System.out.println(6);
                }
                break;
            }
            System.exit(0);

        }
        pre = new long[n + 1];
        pre[3] = 4;
        pre[4] = 6;
        pre[5] = 9;
        for (int i = 6; i < n + 1; i++) {
            pre[i] = pre[i - 3] + pre[i - 1];
            if (pre[i] >= 1000000007)
                pre[i] -= 1000000007;
        }
        System.out.println(pre[n]);
    }
}