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

        String split = "";
        String sym = "";
        double first = 0;
        double second = 0;
        double result = 0;


        if (checkNegative(arr)){
            arr = negativeNumbers(arr);
            for (int i = 0; i < arr.length ; i++) {
                split += arr[i];
            }
            arr = split.split("(?<=[\\d.])(?=[^\\d.])|(?<=[^\\d.])(?=[^\\d.])|(?<=[^\\d.])(?=[\\d.])");
        }



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


    private String[] negativeNumbers(String[] arr){
        StringBuilder sb = new StringBuilder();
        String result = "";
        String[] array = new String[]{};

        for (int i = 0; i < arr.length ; i++) {
            sb.append(arr[i]);
        }

        for (int i = 0; i < sb.length() ; i++) {
            if (sb.charAt(0) == '-'){
                sb.insert(0, "0");
            }else if (sb.charAt(i) == '('){
                sb.insert(i + 1, "0");
            }
        }
        result = sb.toString();
        array = result.split("");

        return array;
    }

    private boolean checkNegative(String[] arr){
        boolean isNegative = false;
        for (int i = 0; i < arr.length ; i++) {
            if (arr[0].equals("-") || (arr[i].equals("(") && arr[i + 1].equals("-"))){
                isNegative = true;
            }
        }
        return isNegative;
    }



    public String result() {
        double result = 0;
        double first = 0;
        double second = 0;
        String sym = "";

        if (digitStack.getSize() == 1) {
            result = Double.valueOf(digitStack.pop());
        }

        while (!characterStack.isEmpty()) {
            digitStack.push(characterStack.pop());

            sym = digitStack.pop();
            first = Double.valueOf(digitStack.pop());
            second = Double.valueOf(digitStack.peek());

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

        }

        return String.valueOf(result);

    }

}