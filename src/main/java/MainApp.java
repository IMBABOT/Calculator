import Logic.*;


public class MainApp {

    public static void main(String[] args) {
        CalcLogic calcLogic = new CalcLogic("5*6-(56/98)-56*56-(85-65)");
        System.out.println(calcLogic.result());
    }
}