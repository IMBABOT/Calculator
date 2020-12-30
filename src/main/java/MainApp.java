import Logic.*;


public class MainApp {

    public static void main(String[] args) {
        CalcLogic calcLogic = new CalcLogic("-3-8-(8-9-5-3-4-6)-6-9-3-2-8-(5-6-(-8-9-9*6+10/3))");
        System.out.println(calcLogic.result());
    }
}