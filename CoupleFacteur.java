public class CoupleFacteur {

    private Facteur facteur; //contient le facteur
    private char operateur; //contient operateur

    public CoupleFacteur(Facteur f, char o){
        facteur = f;
        operateur = o;
    }

    public boolean isProduct() {
        return operateur == '*'; //test produit
    }

    public double getValue() throws ExecutionExceptions{
        return facteur.calculer(); //calcule des facteurs
    }
}
