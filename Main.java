import java.util.Scanner;

public class Main {
  private static Scanner sc = new Scanner(System.in);
  public static void main(String[] args){
    System.out.println("Enter your Expression to solve :");
    String s = sc.nextLine();
    valid(s);
  }

  public static void valid(String s){
    boolean valid = s.matches("^[0-9()*+-/\\s]+");
    if(valid){
      newProb(s);
    }
    else{
      System.out.println("Invalid Expression. Please enter a Valid Expression.");
      newProb("");
    }
  }

  public static void newProb(String s){
    if(s!=""){
      Calculator calc = new Calculator();
      calc.eval(s);
    }
    System.out.println("Have another Expression ? Y or N");
    String cont = sc.nextLine().trim().toLowerCase();
    if(cont.length()>1){
      System.out.println("Invalid Input");
      newProb("");
    }
    char c = cont.charAt(0);
    if(c=='y'){
      System.out.println("Enter your Expression to solve :");
      String in = sc.nextLine();
      valid(in);
    }
    else if(c=='n'){
      System.out.println("Thank You...");
    }
    else{
      System.out.println("Invalid Input");
      newProb("");
    }
  }
}
