import java.util.Scanner;

public class RoundRobinScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input Number Of Processes : ");
        int pro = sc.nextInt();
        Queue<Integer> Q = new Queue<>(pro);  // Create a Queue with a capacity of 5
        
        // Enqueue processes into the queue
        
        for (int i = 1; i <= pro; i++) {
            Q.enqueue(i);
        }

        System.out.print("Enter Time Slice : ");
        int timeSlice = sc.nextInt();

        System.out.println();
        System.out.println("Round Robin Scheduler:");
        System.out.println();
        while (!Q.isEmpty()) {
            int currentProcess = Q.dequeue();
            System.out.println("Running process " + currentProcess);

            // Check if the process is complete

            if (currentProcess - timeSlice <= 0) {
                System.out.println("Process " + currentProcess + " completed.");
            } else {
                System.out.println("Process " + currentProcess + " needs more time.");
                currentProcess -= timeSlice;
                Q.enqueue(currentProcess); // Re-enqueue the process with remaining time
            }
            System.out.println();
        }
        sc.close();
    }
}