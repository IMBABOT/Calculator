package Stacks;

public class StringStack {

    public double getSize() {
        return size;
    }

    private int size;
    private String[] stack;
    private int top = 0;
    private boolean isEmpty;


    public StringStack() {
        size = 4;
        this.stack = new String[size];
        isEmpty = true;
    }

    public StringStack(int size) {
        this.size = size;
        this.stack = new String[size];
        isEmpty = true;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void push(String value){
        if (top == stack.length){
            String[] temp = new String[stack.length * 2];
            System.arraycopy(stack, 0, temp, 0, stack.length);
            stack = temp;
        }

        stack[top++] = value;
        size = top;
    }

    public String pop(){
        String temp;
        if (top == 0){
            throw new RuntimeException("Stack is empty");
        }else {
            temp = stack[--top];
        }

        size = top;
        return temp;
    }

    public String peek(){
        String temp;
        if (top == 0) {
            throw new RuntimeException("Stack is empty");
        }else {
            temp = stack[--top];
            ++top;
        }
        return temp;
    }

    public String[] reverse(){
        String[] result = new String[top];

        for (int i = 0; i <result.length ; i++) {
            result[i] = pop();
        }

        return result;

    }



    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < top ; i++) {
            result += " " + stack[i];
        }

      //  result = result.substring(1);

        return result;
    }
}

