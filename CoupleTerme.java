public class CoupleTerme {
    private Terme terme; //contient le terme du couple terme
    private char signe; //contient le signe du terme +-

    public CoupleTerme(Terme t, char s){
        terme = t;
        signe = s;
    }

    public boolean isAddition() {
        return signe == '+'; //addition test
    }

    public double getValue() throws ExecutionExceptions{
        return terme.calculer(); //calcul de termes
    }
}
