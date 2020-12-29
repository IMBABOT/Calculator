import Logic.*;


public class MainApp {

    public static void main(String[] args) {
        CalcLogic calcLogic = new CalcLogic("(5*6)+(6*5)");
        System.out.println(calcLogic.result());
    }
}