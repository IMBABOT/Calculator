package Logic;

import Stacks.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CalcLogic {

    private StringStack characterStack;
    private StringStack digitStack;


    private String[] arr;
    private String expression;



    private Map<String, Integer> priorities;


    public CalcLogic(String expression) {
        this.expression = expression;
        characterStack = new StringStack();
        digitStack = new StringStack();
        priorities = new HashMap<>();
        addPriorities();
        convertString();
        getSymbol();
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
            if (arr[i].matches(("[0-9]*\\.?[0-9]*"))) {
                digitStack.push(arr[i]);
                if (i == arr.length - 1) {
                    sym = characterStack.pop();
                    first = Double.valueOf(digitStack.pop());
                    second = Double.valueOf(digitStack.pop());


                    if (sym.equals("+")) {
                        result = second + first;
                        digitStack.push(String.valueOf(result));
                    }
                    if (sym.equals("-")) {
                        result = second - first;
                        digitStack.push(String.valueOf(result));
                    }
                    if (sym.equals("*")) {
                        result = second * first;
                        digitStack.push(String.valueOf(result));
                    }
                    if (sym.equals("/")) {
                        result = second / first;
                        digitStack.push(String.valueOf(result));
                    }
                }
            } else if (arr[i].equals(")") && characterStack.peek().equals("(")) {
                characterStack.pop();
                characterStack.push(arr[i]);
                characterStack.pop();
            } else if (arr[i].equals(")")) {
                while (!characterStack.peek().equals("(")) {
                    sym = characterStack.pop();
                    first = Double.valueOf(digitStack.pop());
                    second = Double.valueOf(digitStack.pop());

                    if (sym.equals("+")) {
                        result = second + first;
                        digitStack.push(String.valueOf(result));
                    }
                    if (sym.equals("-")) {
                        result = second - first;
                        digitStack.push(String.valueOf(result));

                    }
                    if (sym.equals("*")) {
                        result = second * first;
                        digitStack.push(String.valueOf(result));
                    }
                    if (sym.equals("/")) {
                        result = second / first;
                        digitStack.push(String.valueOf(result));
                    }
                    i--;
                }
            } else if (arr[i].matches(("[+=\\-*/)(^]+")) && characterStack.isEmpty()) {
                characterStack.push(arr[i]);
            } else if (characterStack.peek().equals("(") || characterStack.peek().equals(")")) {
                characterStack.push(arr[i]);
            } else if ((arr[i].matches(("[+=\\-*/^]+")) && priorities.get(arr[i]) > priorities.get(characterStack.peek()))) {
                characterStack.push(arr[i]);
            } else if (arr[i].matches("[()]")) {
                characterStack.push(arr[i]);
            } else if (arr[i].matches(("[+=\\-*/)(^]+")) && priorities.get(arr[i]) < priorities.get(characterStack.peek())) {
                sym = characterStack.pop();
                first = Double.valueOf(digitStack.pop());
                second = Double.valueOf(digitStack.pop());

                if (sym.equals("+")) {
                    result = second + first;
                    digitStack.push(String.valueOf(result));
                }
                if (sym.equals("-")) {
                    result = second - first;
                    digitStack.push(String.valueOf(result));
                }
                if (sym.equals("*")) {
                    result = second * first;
                    digitStack.push(String.valueOf(result));
                }
                if (sym.equals("/")) {
                    result = second / first;
                    digitStack.push(String.valueOf(result));
                }
                i--;
            } else if (arr[i].matches(("[+=\\-*/)(^]+")) && priorities.get(arr[i]) == priorities.get(characterStack.peek())) {
                sym = characterStack.pop();
                first = Double.valueOf(digitStack.pop());
                second = Double.valueOf(digitStack.pop());

                if (sym.equals("+")) {
                    result = second + first;
                    digitStack.push(String.valueOf(result));
                }
                if (sym.equals("-")) {
                    result = second - first;
                    digitStack.push(String.valueOf(result));
                }
                if (sym.equals("*")) {
                    result = second * first;
                    digitStack.push(String.valueOf(result));
                }
                if (sym.equals("/")) {
                    result = second / first;
                    digitStack.push(String.valueOf(result));
                }
                i--;

            }
        }

    }

    private String getSymbol(){
        String sym = "";
        double temp = 0;
        for (int i = 0; i < arr.length ; i++) {
            if (arr[0].equals("(")){
                while (!digitStack.isEmpty()){
                    temp += Double.valueOf(digitStack.pop());
                }
            }  else if (arr[i].equals("(")){
                sym = arr[i - 1];
            }
        }
        sym = String.valueOf(temp);
        return sym;
    }

    public String result() {
        double result = 0;
        String temp = "";

        System.out.println(getSymbol());

        if (digitStack.getSize() > 1) {
            double first = Double.valueOf(digitStack.pop());
            double second = Double.valueOf(digitStack.peek());
            String sym = getSymbol();
            if (sym.equals("+")) {
                result = second + first;
            }
            if (sym.equals("-")) {
                result = second - first;
            }
            if (sym.equals("*")) {
                result = second * first;
            }
            if (sym.equals("-")) {
                result = second - first;
            }

        } else if (digitStack.getSize() == 1) {
            result = Double.valueOf(digitStack.pop());
            temp = String.valueOf(result);
        }
        return temp;
    }
}