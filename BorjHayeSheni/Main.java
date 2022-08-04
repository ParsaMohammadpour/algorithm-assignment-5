import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Block> blocks = new Stack<>();
        int n = scanner.nextInt();
        long current = scanner.nextLong(), min, max;
        Block block;
        blocks.add(new Block(current, current));
        outer:
        for (int i = 1; i < n; i++) {
            current = scanner.nextLong();
            if (current >= blocks.peek().max) {
                blocks.add(new Block(current, current));
                continue;
            }
            if (blocks.size() == 1) {
                blocks.peek().min = Long.min(current, blocks.peek().min);
                continue;
            }
            max = blocks.peek().max;
            while (blocks.size() > 1) {
                blocks.pop();
                block = blocks.peek();
                if (block.max <= current){
                    blocks.add(new Block(current, max));
                    continue outer;
                }
                if (current >= block.min){
                    block.max = max;
                    continue outer;
                }
            }
            block = blocks.peek();
            block.min = Long.min(current, block.min);
            block.max = max;
        }
        System.out.println(blocks.size());
    }
}

class Block {
    long min;
    long max;

    public Block(long min, long max) {
        this.min = min;
        this.max = max;
    }
}