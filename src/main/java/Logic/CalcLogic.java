package Logic;

import Stacks.*;
import java.util.HashMap;
import java.util.Map;


public class CalcLogic {

    private StringStack characterStack;
    private StringStack digitStack;
    private StringStack resultStack;


    private String[] arr;
    private String expression;



    private Map<String, Integer> priorities;


    public CalcLogic(String expression) {
        this.expression = expression;
        characterStack = new StringStack();
        digitStack = new StringStack();
        priorities = new HashMap<>();
        resultStack = new StringStack();
        addPriorities();
        convertString();
    }

    private void addPriorities() {
        priorities.put("*", 3);
        priorities.put("/", 3);
        priorities.put("+", 2);
        priorities.put("-", 2);
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

        String sym = "";
        double first = 0;
        double second = 0;
        double result = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].matches(("[0-9]*\\.?[0-9]*"))){
                digitStack.push(arr[i]);
            } else if (arr[i].matches(("[+=\\-*/)(^]+")) && characterStack.isEmpty()){
                characterStack.push(arr[i]);
            }else if (characterStack.peek().equals("(") || characterStack.peek().equals(")")){
                characterStack.push(arr[i]);
            } else if ((arr[i].matches(("[+=\\-*/^]+")) && priorities.get(arr[i]) > priorities.get(characterStack.peek()))){
                characterStack.push(arr[i]);
            } else if (arr[i].matches("[()]")) {
                characterStack.push(arr[i]);
            }else if (arr[i].matches(("[+=\\-*/)(^]+")) && priorities.get(arr[i]) < priorities.get(characterStack.peek())){
                sym = characterStack.pop();
                first = Double.valueOf(digitStack.pop());
                second = Double.valueOf(digitStack.pop());

                if (sym.equals("+")){
                    result = second + first;
                    digitStack.push(String.valueOf(result));
                }
                if (sym.equals("-")){
                    result = second - first;
                    digitStack.push(String.valueOf(result));
                }
                if (sym.equals("*")){
                    result = second * first;
                    digitStack.push(String.valueOf(result));
                }
                if (sym.equals("/")){
                    result = second / first;
                    digitStack.push(String.valueOf(result));
                }

            }
        }

    }

}