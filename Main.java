import java.util.Scanner;

public class Main {
  private static Scanner sc = new Scanner(System.in);
  public static void main(String[] args){
    System.out.println("Enter your Sequence to solve :");
    String s = sc.nextLine();
    newProb(s);
  }

  public static void newProb(String s){
    Calculator calc = new Calculator();
    calc.eval(s);
    System.out.println("Have another Sequence ? Y or N");
    char c = sc.nextLine().trim().toLowerCase().charAt(0);
    if(c=='y'){
      System.out.println("Enter your Sequence to solve :");
      String in = sc.nextLine();
      newProb(in);
    }
    else{
      System.out.println("Thank You...");
    }
  }
}
