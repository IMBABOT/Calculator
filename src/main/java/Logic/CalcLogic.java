package Logic;

import Stacks.*;
import java.util.HashMap;
import java.util.Map;


public class CalcLogic {

    private StringStack characterStack;
    private StringStack digitStack;
    private StringStack resultStack;

    private StringStack temp;
    private StringStack result;



    private Map<String, Integer> priorities;


    public CalcLogic() {
        characterStack = new StringStack();
        digitStack = new StringStack();
        priorities = new HashMap<>();
        resultStack = new StringStack();
        result = new StringStack();
        temp = new StringStack();
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
            if (arr[i].matches(("[0-9]*\\.?[0-9]*"))) {
                digitStack.push(arr[i]);
            }else  if (arr[i].matches("[(]")) {
                characterStack.push(arr[i]);
            }else if (arr[i].matches("[)]")){
                while (!characterStack.peek().matches("[(]")){
                    digitStack.push(characterStack.pop());
                    if (characterStack.peek().matches("[(]")){
                        characterStack.pop();
                        break;
                    }
                }
            } else if (arr[i].matches("[+=\\-*/^]+")) {
                characterStack.push(arr[i]);
            }
        }
        unionRevers();
    }

    private void unionRevers() {
        while (!characterStack.isEmpty()) {
            digitStack.push(characterStack.pop());
        }

        while (!digitStack.isEmpty()) {
            temp.push(digitStack.pop());
        }
        answer();
    }

    private void answer() {
        double first = 0;
        double second = 0;
        double res = 0;
        String sym = "";



        while (!temp.isEmpty()){
            if (temp.peek().matches(("[0-9]*\\.?[0-9]*"))){
                result.push(temp.pop());
            }
            if (temp.peek().matches("[+=\\-*/^)]+")) {
                first = Double.valueOf(result.pop());
                second = Double.valueOf(result.pop());
                sym = temp.pop();

                if (sym.equals("+")) {
                    res = second + first;
                    result.push(String.valueOf(res));
                } else if (sym.equals("-")) {
                    res = second - first;
                    result.push(String.valueOf(res));
                } else if (sym.equals("*")) {
                    res = second * first;
                    result.push(String.valueOf(res));
                } else if (sym.equals("/")) {
                    res = second / first;
                    result.push(String.valueOf(res));
                }
            }
            }
        System.out.println(result);
        }
    }
