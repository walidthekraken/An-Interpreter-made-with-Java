import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Terme implements Calculatrice {
    List<CoupleFacteur> coupleFacteurs = new ArrayList<CoupleFacteur>(); //tableau de facteurs avec leur operateur (* ou /)
    private String strTerm; //terme sous forme de chaine de caracteres

    public Terme(String strTerm) throws ExecutionExceptions{

        this.strTerm = strTerm; //terme sous forme de chaine de cars
        if (estCorrecte()) { //si elle est correcte
            creerCoupleFacteurs(strTerm.trim()); //remplir le tableau
        }else throw new OperandeManquanteException();
    }

    @Override
    public boolean estCorrecte() throws ExecutionExceptions {
        return !strTerm.equals("");
    }

    public double calculer() throws ExecutionExceptions{ //retourne la valeur numerique du terme
        double prod = 1;
        for (CoupleFacteur cf : coupleFacteurs)
        {
            if (cf.isProduct()) //si c'est produit
            {
                prod = prod * cf.getValue(); //effectuer le produit
            }else {
                double k = cf.getValue();
                if (k!=0) {
                    prod = prod / k; //effectuer la division
                }else throw new DevideZeroException();
            }
        }
        return prod;
    }

    public boolean creerCoupleFacteurs (String s) throws ExecutionExceptions{

        Deque<Character> pileParantheses = new LinkedList<Character>(); //pile des parantheses

        char signe = '*'; //le signe du facteur par defaut

        int i;

        s = "*"+s; //le rajouter au debut

        String strFact = ""; //string du facteur courant

        for (i=0;i<s.length();i++)
        {
            if (s.charAt(i) == '('){
                pileParantheses.add('('); //rajouter la paranthese
            }
            if (s.charAt(i) == ')'){
                pileParantheses.removeFirst(); //depiler la paranthese
            }

            if (s.charAt(i) == '*' && pileParantheses.isEmpty()) { //si la pile n'est pas vide sinon ignorer l'operateur car il se trouve a linterieur d'une paranthese
                if (i != 0) { //si ce nest pas le premier operateur
                    Facteur facteur = new Facteur(strFact); //creer le facteur
                    CoupleFacteur cf = new CoupleFacteur(facteur, signe); //creer le couple facteur avec son operateur
                    coupleFacteurs.add(cf); //le rajouter a la liste
                    signe = '*'; //l'operateur du prochain facteur
                    strFact = ""; //le prochain facteur
                }
            }
            else if (s.charAt(i) == '/' && pileParantheses.isEmpty()) { //si c'est une division a l'exterieur des parantheses
                if (i != 0) {//si ce nest pas le premier operateur
                    Facteur facteur = new Facteur(strFact); //creer le facteur
                    CoupleFacteur cf = new CoupleFacteur(facteur, signe);//creer le couple facteur avec son operateur
                    coupleFacteurs.add(cf);//le rajouter a la liste
                    signe = '/';//l'operateur du prochain facteur
                    strFact = "";//le prochain facteur
                }
            }else {

                strFact = strFact + s.charAt(i);//rajouter au facteur courant

            }

        }

        if (!strFact.equals("")) //si le dernier facteur non vide
        {
            Facteur facteur = new Facteur(strFact); //creer le facteur
            CoupleFacteur cf = new CoupleFacteur(facteur, signe); //le couple facteur et oprateur
            coupleFacteurs.add(cf); //ajouter a la liste
        }else throw new OperandeManquanteException();

        return true;
    }
}
