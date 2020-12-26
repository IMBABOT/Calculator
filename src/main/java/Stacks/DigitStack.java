package Stacks;

public class DigitStack {


    public double getSize() {
        return size;
    }

    private int size;
    private double[] stack;
    private int top = 0;


    public DigitStack() {
        size = 4;
        this.stack = new double[size];

    }

    public DigitStack(int size) {
        this.size = size;
        this.stack = new double[size];
    }

    public void push(double value){
        if (top == stack.length){
            double[] temp = new double[stack.length * 2];
            System.arraycopy(stack, 0, temp, 0, stack.length);
            stack = temp;
        }

        stack[top++] = value;
        size = top;
    }

    public double pop(){
        double temp;
        if (top == 0){
            throw new RuntimeException("Stack is empty");
        }else {
            temp = stack[--top];
        }

        size = top;
        return temp;
    }

    public double peek(){
        double temp;
        if (top == 0) {
            throw new RuntimeException("Stack is empty");
        }else {
            temp = stack[--top];
            ++top;
        }
        return temp;
    }

    public double[] reverse(){
        double[] result = new double[top];

        for (int i = 0; i <result.length ; i++) {
            result[i] = pop();
        }

        return result;

    }


    public boolean isEmpty(){
        return size == 0;
    }



    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < top ; i++) {
            result += " " + stack[i];
        }

        ///   result = result.substring(1);

        return result;
    }
}

