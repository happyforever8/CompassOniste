(1)
Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23
  
  class Solution {
    int i = 0;
    
    public int calculate(String s) {
    //recursion
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        char operator = '+';
        
       // stack.push(operator);
        int num = 0;
        while (i < s.length()){
            char ch = s.charAt(i++);
            
            
            if (ch >= '0' && ch <= '9'){
                num = num * 10 + (ch - '0');
            }
            
            if (ch == '('){
                  num = calculate(s);
            }
            if (i == s.length() || ch == '+' || ch == '-' ||  ch == ')'){
                if (operator == '+'){
                    stack.push(num);
                } else if (operator == '-'){
                    stack.push(-num);
                } 
                operator = ch;
                num = 0;
            }
            if (ch == ')'){
                break;
            }
            
        }
        
        
        while (!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
}
(2)Input: s = "3+2*2"
    Output: 7
      
      
      class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int num = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        
        int sign = '+';
        
        int index = 0;
        int sum = 0;
        
        
        while (index < s.length()){
            char ch = s.charAt(index++);
            
            if (ch >= '0' && ch <= '9'){
                num = num * 10 + (ch - '0');
            }
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || index == s.length()){
                if (sign == '+'){
                    stack.push(num);
                } else if (sign == '-'){
                    stack.push(-num);
                } else if (sign == '*'){
                    stack.push(stack.pop() * num);
                } else if (sign == '/'){
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = ch;
            }
        }
        while (!stack.isEmpty()){
            sum += stack.pop();
        }
        return sum;
    }
}

(3)Input: s = "(2+6*3+5-(3*14/7+2)*5)+3"
  Output: -12
    
    
    class Solution {
    int i = 0;
    
    public int calculate(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int sum = 0;
        int num = 0;
        char sign = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        
        while (i < s.length()){
            char ch = s.charAt(i++);
            
            if (ch >= '0' && ch <= '9'){
                num = num * 10 + (ch - '0');
            }
            
            if (ch == '('){
                num = calculate(s);
            }
            
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ')' || i == s.length()){
                if (sign == '+'){
                    stack.push(num);
                } else if (sign == '-'){
                    stack.push(-num);
                } else if (sign == '*'){
                    stack.push(stack.pop() * num);
                } else if (sign == '/'){
                    stack.push(stack.pop() / num);
                }
                sign = ch;
                num = 0;
            }
            if (ch == ')'){
                break;
            }
        }
        
        while (!stack.isEmpty()){
            sum += stack.pop();
        }
        return sum;
    }
}


(3)
Given an expression such as expression = "e + 8 - a + 5" and an evaluation map such as {"e": 1} (given in terms of evalvars = ["e"] and evalints = [1]), return a list of tokens representing the simplified expression, such as ["-1*a","14"]

An expression alternates chunks and symbols, with a space separating each chunk and symbol.
A chunk is either an expression in parentheses, a variable, or a non-negative integer.
A variable is a string of lowercase letters (not including digits.) Note that variables can be multiple letters, and note that variables never have a leading coefficient or unary operator like "2x" or "-x".
Expressions are evaluated in the usual order: brackets first, then multiplication, then addition and subtraction.

For example, expression = "1 + 2 * 3" has an answer of ["7"].
The format of the output is as follows:

For each term of free variables with a non-zero coefficient, we write the free variables within a term in sorted order lexicographically.
For example, we would never write a term like "b*a*c", only "a*b*c".
Terms have degrees equal to the number of free variables being multiplied, counting multiplicity. We write the largest degree terms of our answer first, breaking ties by lexicographic order ignoring the leading coefficient of the term.
For example, "a*a*b*c" has degree 4.
The leading coefficient of the term is placed directly to the left with an asterisk separating it from the variables (if they exist.) A leading coefficient of 1 is still printed.
An example of a well-formatted answer is ["-2*a*a*a", "3*a*a*b", "3*b*b", "4*a", "5*c", "-6"].
Terms (including constant terms) with coefficient 0 are not included.
For example, an expression of "0" has an output of [].
 

Example 1:

Input: expression = "e + 8 - a + 5", evalvars = ["e"], evalints = [1]
Output: ["-1*a","14"]
Example 2:

Input: expression = "e - 8 + temperature - pressure", evalvars = ["e", "temperature"], evalints = [1, 12]
Output: ["-1*pressure","5"]
Example 3:

Input: expression = "(e + 8) * (e - 8)", evalvars = [], evalints = []
Output: ["1*e*e","-64"]
Example 4:

Input: expression = "a * b * c + b * a * c * 4", evalvars = [], evalints = []
Output: ["5*a*b*c"]
Example 5:

Input: expression = "((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))", evalvars = [], evalints = []
Output: ["-1*a*a*b*b","2*a*a*b*c","-1*a*a*c*c","1*a*b*b*b","-1*a*b*b*c","-1*a*b*c*c","1*a*c*c*c","-1*b*b*b*c","2*b*b*c*c","-1*b*c*c*c","2*a*a*b","-2*a*a*c","-2*a*b*b","2*a*c*c","1*b*b*b","-1*b*b*c","1*b*c*c","-1*c*c*c","-1*a*a","1*a*b","1*a*c","-1*b*c"]
 

Constraints:

1 <= expression.length <= 250
expression consists of lowercase English letters, digits, '+', '-', '*', '(', ')', ' '.
expression does not contain any leading or trailing spaces.
All the tokens in expression are separated by a single space.
0 <= evalvars.length <= 100
1 <= evalvars[i].length <= 20
evalvars[i] consists of lowercase English letters.
evalints.length == evalvars.length
-100 <= evalints[i] <= 100

  
  
class Solution {
    class Term implements Comparable<Term>{
        int coef=1;
        List<String> vars= new LinkedList<>();
        public Term(int coef){ this.coef=coef; }
        public Term(String s){ vars.add(s); }
        public String varString(){ 
            Collections.sort(vars);
            return vars.isEmpty()?"":("*"+String.join("*", vars)); 
        }
        public String toString(){ return coef+varString(); }
        public boolean equals(Term o){return varString().equals(o.varString()); }
        public int compareTo(Term t){return vars.size()==t.vars.size()?varString().compareTo(t.varString()):t.vars.size()-vars.size(); }
        public Term multi(Term t){
            Term res= new Term(coef*t.coef);
            res.vars.addAll(vars);
            res.vars.addAll(t.vars);
            return res;
        }
    }
    class Sequence{
        List<Term> terms= new LinkedList<>();
        public Sequence(){}
        public Sequence(int n){terms.add(new Term(n));}
        public Sequence(String s){terms.add(new Term(s));}
        public Sequence(Term t){terms.add(t);}
        public Sequence add(Sequence sq){
            for (Term t2: sq.terms){
                if (t2.coef==0) continue;
                boolean found=false;
                for (Term t1: terms){
                    if (t1.equals(t2)){
                        t1.coef+=t2.coef;
                        if (t1.coef==0) terms.remove(t1);
                        found=true;
                        break;
                    }
                }
                if (!found) terms.add(t2);
            }
            return this;
        }
        public Sequence multi(Sequence sq){
            Sequence res= new Sequence();
            for (Term t1: terms)
                for (Term t2: sq.terms)
                    res.add(new Sequence(t1.multi(t2)));
            return res;
        }
    }
    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        List<String> res= new LinkedList<>();
        Map<String, Integer> map= new HashMap<>();
        for (int i=0; i<evalvars.length; i++) map.put(evalvars[i], evalints[i]);
        Sequence sq= helper(expression, map);
        Collections.sort(sq.terms);
        for (Term t: sq.terms) 
            if (!t.toString().equals("0"))
                res.add(t.toString());
        return res;
    }
    private int idx;
    private Sequence helper(String e, Map<String, Integer> map){
        Stack<Sequence> stack=new Stack<>();
        stack.push(new Sequence(0));
        int flag=1;
        while (idx<e.length()){
            char c= e.charAt(idx++);
            if (c==' ') continue;
            else if (c=='('){
                Sequence sq= helper(e, map);
                addToStack(stack, sq, flag);
            }else if (c==')') break;
            else if (c=='+' || c=='-' || c=='*') flag= c=='+'?1:c=='-'?-1:0;
            else if (Character.isDigit(c)){
                int coef=c-'0';
                while (idx<e.length() && Character.isDigit(e.charAt(idx))) coef=coef*10+e.charAt(idx++)-'0';
                addToStack(stack, new Sequence(coef), flag);
            }else{
                StringBuilder sb=new StringBuilder(c+"");
                while (idx<e.length() && Character.isLetter(e.charAt(idx))) sb.append(e.charAt(idx++));
                String var= sb.toString();
                if (map.containsKey(var)) addToStack(stack, new Sequence(map.get(var)), flag);
                else addToStack(stack, new Sequence(var), flag);
            }
        }
        Sequence res= new Sequence();
        while (!stack.isEmpty()) res.add(stack.pop());
        return res;
    }
    private void addToStack(Stack<Sequence> stack, Sequence sq, int flag){
        if (flag==0) stack.add(stack.pop().multi(sq));
        else stack.add(new Sequence(flag).multi(sq));
    }
}
