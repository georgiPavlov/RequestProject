/**
 * Created by georgipavlov on 05.01.16.
 */
public class Request {
    private String name;
    private double mark;
    private double moneyFamily;
     private String fac;
    private int type;

    public Request(String name, double mark, double moneyFamily, String fac, int type) {
        this.name = name;
        this.mark = mark;
        this.moneyFamily = moneyFamily;
        this.fac = fac;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void setMoneyFamily(double moneyFamily) {
        this.moneyFamily = moneyFamily;
    }

    public void setFac(String fac) {
        this.fac = fac;
    }

    public void setType(int type) {
        this.type = type;
    }
}
