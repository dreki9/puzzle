import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle {
    private static final ArrayList<String> S = new ArrayList<>();
    private static final ArrayList<String> U = new ArrayList<>();
    private static final ArrayList<String> letters = new ArrayList<>();
    private static String allInputs;
    private static String first;
    private static String second;
    private static String output;
    private static int counter;
    
    public static void check(ArrayList<String> S){
        String tempInputs = allInputs;
        for (int i = 0; i < S.size(); i++) {
            tempInputs = tempInputs.replaceAll(letters.get(i), S.get(i));
        }
        String firstInput = tempInputs.substring(0, first.length());
        tempInputs = tempInputs.substring(firstInput.length());
        String secondInput = tempInputs.substring(0, second.length());
        tempInputs = tempInputs.substring(secondInput.length());
        String outputInput = tempInputs;

        int a = Integer.parseInt(firstInput);
        int b = Integer.parseInt(secondInput);
        int c = Integer.parseInt(outputInput);

        if ((a + b) == c ){
            counter++;
            System.out.println("Found..");
            System.out.println(first + ":\t" + firstInput);
            System.out.println(second + ":\t" + secondInput);
            System.out.println(output + ":\t" + outputInput);
        }
    }

    public static void puzzleSolver(int k, ArrayList<String> S,
                               ArrayList<String> U ){
        for (int i = 0; i < U.size(); i++) {
            String s = U.get(i);
            S.add(s);
            U.remove(s);
            if (k == 1){
                check(S);
            }
            else {
                puzzleSolver(k-1,S,U);
            }
            U.add(i , s);
            S.remove(s);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isTrue;
        do {
            System.out.print("Enter 1st input value: ");
            first = sc.nextLine();
            System.out.print("Enter 2nd input value: ");
            second = sc.nextLine();
            System.out.print("Enter Output value: ");
            output = sc.nextLine();
            allInputs = first + second + output;
            for (int i = 0; i < allInputs.length(); i++) {
                if (!letters.contains(String.valueOf(allInputs.charAt(i)))){
                    letters.add(String.valueOf(allInputs.charAt(i)));
                }
            }
            isTrue =
                    2 <= first.length() && first.length() <=6 && 2 <= second.length()
                    && second.length() <=6&&2 <= output.length() && output.length() <=6 && letters.size() <= 10;
            if (!isTrue){
                letters.clear();
            }
        }while (!isTrue);
        for (int i = 0; i < 10; i++) {
            U.add(String.valueOf(i));
        }
        System.out.println("searching...");
        puzzleSolver(letters.size(),S,U);
        if (counter < 1){
            System.out.println("Solution not found..");
        }
    }


}
