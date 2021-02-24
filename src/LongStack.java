


import java.util.ArrayList;

public class LongStack {
   ArrayList<Long> stack = new ArrayList<Long>();

   public static void main(String[] argum) {
      LongStack stack1 = new LongStack();
      stack1.push(15);
      stack1.push(5);
      System.out.println(stack1.stack.isEmpty());
      System.out.println(stack1.pop());
      System.out.println(stack1.stack.isEmpty());
      stack1.op("+");
      System.out.println(stack1.tos());

      LongStack m1 = new LongStack();
      m1.push(5);
      m1.push(4);
      LongStack m2 = null;
      try {
         m2 = (LongStack) m1.clone();

      } catch (CloneNotSupportedException e) {

      }
      System.out.println(m2.pop());
      // TODO!!! Your tests here!
   }

   LongStack() {


      // TODO!!! Your constructor here!
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      LongStack cloned = new LongStack();
      cloned.stack = (ArrayList<Long>) stack.clone();
      return (Object) cloned;

   }

   public boolean stEmpty() {
      return stack.isEmpty();

   }

   public void push(long a) {
      stack.add(a);
   }

   public long pop() {
      Long last_el = stack.get(stack.size() - 1);
      stack.remove(stack.size() - 1);
      return last_el;
   } // pop

   public void op(String s) {
      long result = 0, a, b;
      a = stack.get(stack.size() - 2);
      b = stack.get(stack.size() - 1);

      if (s == "+") {
         result = a + b;
      } else if (s == "-") {
         result = a - b;
      } else if (s == "*") {
         result = a * b;
      } else if (s == "/") {
         result = a / b;
      }
      stack.remove(stack.size() - 1);
      stack.remove(stack.size() - 1);
      stack.add(result);
   }

   public long tos() {
      return stack.get(stack.size() - 1); // TODO!!! Your code here!
   }

   @Override
   public boolean equals(Object o) {
      LongStack typecasted = (LongStack) o;
      return stack.equals(typecasted.stack);
   }

   @Override
   public String toString() {
      return stack.toString();
   }

   public static long interpret(String pol) {
      return 0; // TODO!!! Your code here!
   }

}

