import java.util.*;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number To Convert To Binary Representation : ");
        int decimalNumber = sc.nextInt(); // Change this to your desired decimal number

        Stack<Integer> stack = new Stack(32); // Create a stack to hold binary digits

        if (decimalNumber == 0) {
            System.out.println("Binary Representaion : 0");
        } 
        else {
            while (decimalNumber > 0) {
                int remainder = decimalNumber % 2;
                stack.push(remainder);
                decimalNumber = decimalNumber / 2;
            }

            System.out.print("Binary representation: ");
            while (!stack.isEmpty()) {
                System.out.print(stack.pop());
            }
        }
        sc.close();
    }
}