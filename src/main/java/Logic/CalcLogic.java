package Logic;

import Stacks.*;
import java.util.HashMap;
import java.util.Map;


public class CalcLogic {

    private StringStack characterStack;
    private DigitStack digitStack;
    private StringStack stringStack;


    private Map<String, Integer> priorities;


    private StringStack reverseString;
    private DigitStack reverseDigit;

    public CalcLogic() {
        characterStack = new StringStack();
        digitStack = new DigitStack();
        stringStack = new StringStack();
        priorities = new HashMap<>();
        reverseString = new StringStack();
        reverseDigit = new DigitStack();
        addPriorities();
    }

    private void addPriorities() {
        priorities.put("*", 3);
        priorities.put("/", 3);
        priorities.put("+", 2);
        priorities.put("-", 2);
        priorities.put("(", 1);
        priorities.put(")", 1);
        priorities.put("0", 0);
        priorities.put("1", 0);
        priorities.put("2", 0);
        priorities.put("3", 0);
        priorities.put("4", 0);
        priorities.put("5", 0);
        priorities.put("6", 0);
        priorities.put("7", 0);
        priorities.put("8", 0);
        priorities.put("9", 0);
    }


    public void convertString(String s) {
        String[] arr = s.split("(?<=[\\d.])(?=[^\\d.])|(?<=[^\\d.])(?=[^\\d.])|(?<=[^\\d.])(?=[\\d.])");

        for (int i = 0; i < arr.length; i++) {
            stringStack.push(arr[i]);
        }

        while (!stringStack.isEmpty()) {
            if ((!stringStack.isEmpty()) && stringStack.peek().matches("[0-9]*\\.?[0-9]*")) {
                digitStack.push(Double.valueOf(stringStack.pop()));
            }
            if ((!stringStack.isEmpty()) && stringStack.peek().matches("[(+=\\-*/^)]+")) {
                characterStack.push(stringStack.pop());
            }
        }
        reverse();
    }


    private void reverse(){
        while (!digitStack.isEmpty()) {
            reverseDigit.push(digitStack.pop());
        }

        while (!characterStack.isEmpty()){
            reverseString.push(characterStack.pop());
        }

        System.out.println(digitStack);
        System.out.println(characterStack);


    }
}





