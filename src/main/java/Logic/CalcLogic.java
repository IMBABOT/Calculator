package Logic;

import Stacks.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CalcLogic {

    private StringStack characterStack;
    private String string;


    private String[] arr;
    private String expression;


    private Map<String, Integer> priorities;


    public CalcLogic(String expression) {
        this.expression = expression;
        this.string = new String();
        characterStack = new StringStack();
        priorities = new HashMap<>();
        addPriorities();
        convertString();

    }

    private void addPriorities() {
        priorities.put("*", 3);
        priorities.put("/", 3);
        priorities.put("+", 2);
        priorities.put("-", 2);
        priorities.put("(", 1);
        priorities.put(")", -1);
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


    public void convertString() {
        arr = expression.split("(?<=[\\d.])(?=[^\\d.])|(?<=[^\\d.])(?=[^\\d.])|(?<=[^\\d.])(?=[\\d.])");

        for (int i = 0; i < arr.length ; i++) {
            if (arr[i].matches(("[0-9]*\\.?[0-9]*"))) {
                string += arr[i];
            }else if (arr[i].matches(("[+=\\-*()/^]+")) && characterStack.isEmpty()){
                characterStack.push(arr[i]);
            }else if (arr[i].matches(("[+=\\-*()/^]+")) && !characterStack.isEmpty() && priorities.get(arr[i]) > priorities.get(characterStack.peek())){
                while (!characterStack.isEmpty() && priorities.get(arr[i]) > priorities.get(characterStack.peek())){
                    characterStack.push(arr[i]);
                }
            }else if (arr[i].matches(("[+=\\-*()/^]+")) && !characterStack.isEmpty() && priorities.get(arr[i]) < priorities.get(characterStack.peek())){
                    characterStack.push(arr[i]);
            }
        }
        while (!characterStack.isEmpty()){
            string += characterStack.pop();
        }

    }
}


//
//    private double result(){
//        double result = 0;
//        if (digitStack.getSize() > 1) {
//             result = Double.valueOf(digitStack.pop()) + Double.valueOf(digitStack.pop());
//        }else{
//            return Double.valueOf(digitStack.pop());
//        }
//        return result;
//    }
//
//}