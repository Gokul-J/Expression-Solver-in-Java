import java.util.ArrayDeque;
import java.util.InputMismatchException;

public class Calculator {

  private ArrayDeque<Character> stack = new ArrayDeque<Character>();
  private ArrayDeque<Integer> num = new ArrayDeque<Integer>();
  private int mod = 1;

  public void eval(String s){
    Main m = new Main();
    String s1 = s.replaceAll("\\s","");
    char[] ch = s1.toCharArray();
    for(int i=0; i<ch.length; i++){
      if(ch[i] >='0' && ch[i] <='9'){
        int n = i;
        while(i!=ch.length && (ch[i]>='0' && ch[i] <='9')){
          i++;
        }
        String sub = s1.substring(n, i);
        i--;
        num.push(Integer.parseInt(sub)*mod);
        mod=1;
      }
      else if(ch[i]=='('){
        if(i!=0 && (ch[i-1]>='0' && ch[i-1]<='9')){
          stack.push(';');
        }
        else{
          stack.push(ch[i]);
        }
      }
      else if(ch[i]!=')'){
        if(i!=0 && (ch[i-1]==')' || (ch[i-1]>='0' && ch[i-1]<='9'))){
          validChecker(ch[i]);
        }
        else{
          if(ch[i]=='+'){
            mod = 1;
          }
          else if(ch[i]=='-'){
            mod = -1;
          }
          else{
            // throw new InputMismatchException("Invalid Expression");
            System.out.println("Invalid Expression. Please enter a Valid Expression.");
            m.newProb("");
          }
        }
      }
      else{
        operate();
      }
    }
    while(!stack.isEmpty() && num.size()>=2){
      singleOperate();
    }
    System.out.println(num.pop());
  }

  public void validChecker(char ch){
    if(!stack.isEmpty() && ch!='('){
      char c = stack.peek();
      int h1 = getHeirarchy(c);
      int h2 = getHeirarchy(ch);
      if(h1<=h2){
        singleOperate();
      }
    }
    stack.push(ch);
  }

  public void singleOperate(){
    int n1 = num.pop();
    int n2 = num.pop();
    char c = stack.pop();
    int result = arithmetic(c, n1, n2);
    num.push(result);
    return;
  }

  public void operate(){
    while(stack.peek()!='(' && stack.peek()!=';'){
      int n1 = num.pop();
      int n2 = num.pop();
      char c = stack.pop();
      int result = arithmetic(c, n1, n2);
      num.push(result);
    }
    if(stack.pop()=='('){
      validChecker('*');
    }
    stack.pop();
    return;
  }

  public int arithmetic(char c, int n1, int n2){
    switch(c){
      case '/' :
        return n2/n1;
      case '*' :
        return n2*n1;
      case '+' :
        return n2+n1;
      case '-' :
        return n2-n1;
      default :
        return 0;
    }
  }

  public int getHeirarchy(char c){
    switch(c){
      case '/' :
      case '*' :
        return 1;
      case '+' :
      case '-' :
        return 2;
      default :
        return 3;
    }
  }
}