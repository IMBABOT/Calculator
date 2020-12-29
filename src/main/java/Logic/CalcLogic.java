package Logic;

import Stacks.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CalcLogic {

    private StringStack characterStack;
    private StringStack resultStack;
    private String string;

    private ArrayList<String> list;


    private String[] arr;
    private String expression;


    private Map<String, Integer> priorities;


    public CalcLogic(String expression) {
        this.expression = expression;
        this.string = new String();
        characterStack = new StringStack();
        resultStack = new StringStack();
        priorities = new HashMap<>();
        list = new ArrayList<>();
        addPriorities();
        convertString();
        System.out.println(result());
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
            if (arr[i].equals("(")){
                characterStack.push(arr[i]);
            }
            if (arr[i].matches("[0-9]*\\.?[0-9]*")){
                string += arr[i];
            }
            if (!characterStack.isEmpty() && arr[i].matches(("[+=\\-*()/^]+")) && priorities.get(arr[i]) > priorities.get(characterStack.peek())){
                while (priorities.get(arr[i]) > priorities.get(characterStack.peek())){
                    characterStack.push(arr[i]);
                }
            }else if (arr[i].matches(("[+=\\-*()/^]+")) && characterStack.isEmpty()){
                characterStack.push(arr[i]);
            }


            if (arr[i].equals(")")){
                while (!characterStack.peek().equals("(")){
                    string += characterStack.pop();
                    if (characterStack.peek().equals("(")){
                        characterStack.pop();
                        break;
                    }
                }
            }
        }

        while (!characterStack.isEmpty()){
            string += characterStack.pop();
        }

        result();
    }

    private double result(){
        double first = 0;
        double second = 0;
        String sym = "";
        double answer = 0;

        for (int i = 0; i < string.length() ; i++) {
            list.add(String.valueOf(string.charAt(i)));
        }

        while (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).matches("[0-9]*\\.?[0-9]*")) {
                    while (list.get(i).matches("[0-9]*\\.?[0-9]*")) {
                        resultStack.push(list.remove(i));
                    }
                }else if (list.get(i).matches(("[+=\\-*()/^]+"))) {
                    sym = list.remove(i);
                    first = Double.valueOf(resultStack.pop());
                    second = Double.valueOf(resultStack.pop());

                    if (sym.equals("+")) {
                        answer = second + first;
                        resultStack.push(String.valueOf(answer));
                    }
                    if (sym.equals("*")) {
                        answer = second * first;
                        resultStack.push(String.valueOf(answer));
                    }
                    if (sym.equals("-")) {
                        answer = second - first;
                        resultStack.push(String.valueOf(answer));
                    }
                    if (sym.equals("/")) {
                        answer = second / first;
                        resultStack.push(String.valueOf(answer));
                    }
                }
                }
            }
       // answer = Double.valueOf(resultStack.pop());

        return answer;
    }

}
