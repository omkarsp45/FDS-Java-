import java.util.*;

public class MatchingTags{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter HTML Document : ");
        String HTML = sc.nextLine(); // Takes input as a HTML Code

        Stack<Character> S = new Stack(32); // Create a stack to hold binary digits

        for(int i = 0 ; i < HTML.length()-1 ; i++){
            if(HTML.charAt(i)=='<' && HTML.charAt(i+1)!='/'){
                S.push('<');
            }
            else if(HTML.charAt(i)=='<' && HTML.charAt(i+1)=='/'){
                if(!(S.isEmpty())){
                    S.pop();
                }
                else{
                    System.out.println("HTML Code is not properly Formatted");
                    break;
                }
            }
        }
        if(S.isEmpty()){
            System.out.print("HTML Code is properly Formatted");
        }
        else{
            System.out.print("HTML Code is not properly Formatted");
        }
    }
}