import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), k = scanner.nextInt();
        PriorityQueue<MessageTime> messages = new PriorityQueue<>(new Comparator<MessageTime>() {
            @Override
            public int compare(MessageTime messageTime, MessageTime t1) {
                if (messageTime.start == t1.start)
                    return Integer.compare(messageTime.end, t1.end);
                return Integer.compare(messageTime.start, t1.start);
            }
        });
        MessageTime message;
        for (int i = 0; i < n; i++) {
            messages.add(new MessageTime(scanner.nextInt(), scanner.nextInt()));
        }
        boolean status = true;
        int counter;
        int today = messages.peek().start;
        outer:
        while (messages.size() > 0) {
            counter = 0;
            while (counter < k) {
                if (messages.size() == 0)
                    break outer;
                if (messages.peek().start > today)
                    break;
                message = messages.poll();
                if (message.end < today) {
                    status = false;
                    break outer;
                }
                counter++;
            }
            while (messages.size() > 0 && messages.peek().start == today){
                messages.peek().start++;
                if (messages.peek().start > messages.peek().end){
                    status = false;
                    break outer;
                }
                messages.add(messages.poll());
            }
            today++;
        }
        if (status)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}

class MessageTime {
    int start;
    int end;

    public MessageTime(int start, int end) {
        this.start = start;
        this.end = end;
    }
}