import java.util.*;

public class Expression implements Calculatrice{

    private List<CoupleTerme> coupleTermes = new ArrayList<CoupleTerme>(); //terme extraits de l'expression avec le signe de chaque terme

    private String strExp; //expression extraite de la ligne de commande sous forme de chaine de chars

    public Expression (String strExp) throws ExecutionExceptions{

        this.strExp = strExp.trim();
        if (!strExp.trim().equals("")) //si l'expression est vide
        {
            if (estCorrecte()){ //si il n'ya pas de probleme de parantheses
                creerCoupleTermes(strExp.trim()); //diviser l'expression sous forme de termes selon le signe de chaque terme
            }
        }else throw new WrongExpressionException("L'expression est vide.");

    }


    public double calculer() throws ExecutionExceptions{
        double sum = 0;
        for (CoupleTerme ct : coupleTermes)
        {
            if (ct.isAddition()) //si le signe du terme est '+'
            {
                sum = sum + ct.getValue(); //rajouter la valeur du terme
            }else {
                sum = sum - ct.getValue(); //la soustraire
            }
        }
        return sum; //retourner la somme
    }

    public boolean estCorrecte() throws ExecutionExceptions{
        Deque<Character> pileParantheses = new LinkedList<Character>(); //pile des parantheses
        int i = 0;
        for (i=0 ; i<strExp.length(); i++)
        {
            if (strExp.charAt(i)=='('){
                pileParantheses.add('('); //empiler les paratheses ouvrantes
            }else if (strExp.charAt(i) == ')'){
                if (pileParantheses.isEmpty()) { //si la pile est vide
                    throw new ParentheseOuvranteException(); //paranthese ouvrante manquante
                }else {
                    pileParantheses.removeFirst(); //depiler une paranthese
                }
            }
        }

        if (!pileParantheses.isEmpty()) //si pile non vide
        {
            throw new ParentheseFermanteException(); //parantheses fermantes manquantes
        }
        return true;
    }

    //Diviser l'expression en plusieurs termes avec le signe de chaque terme
    public boolean creerCoupleTermes (String s) throws ExecutionExceptions{

        Deque<Character> pileParantheses = new LinkedList<Character>(); //pile des parantheses

        char signe = '-'; //signe par defaut du premier terme

        if (s.charAt(0) != '-') //si le premier caractere est different de '-'
        {
            if (s.charAt(0) == '+') { //si le premier caractere est '+'
                signe = '+'; //signe du premier terme
            }else {
                s = "+" + s; //rajouter le plus au debut de l'expression
                signe = '+'; //le signe du premier terme
            }
        }

        int i;

        String strTerm = ""; //contient la chaine de caracteres d'un terme

        for (i=0;i<s.length();i++)
        {

            if (s.charAt(i) == '('){
                pileParantheses.add('('); //empiler la parenthese ouvrante
            }
            if (s.charAt(i) == ')'){
                pileParantheses.removeFirst(); //depiler la paranthese fermante
            }

            if (s.charAt(i) == '+' && pileParantheses.isEmpty()) { //si le caractere courant est un plus et il ne se trouve pas a l'interieur d'une parenthese
                if (i != 0) {
                    //si ce n'est pas le premier plus
                    if (strTerm!="") {
                        Terme terme = new Terme(strTerm); //creer un terme
                        CoupleTerme ct = new CoupleTerme(terme, signe); //creer un couple terme avec son signe
                        coupleTermes.add(ct); //rajouter le couple au tableau
                        signe = '+'; //le nouveau signe du prochain terme
                        strTerm = ""; //commencer le prochain terme
                    }
                }
            }
            else if (s.charAt(i) == '-' && pileParantheses.isEmpty()) { //si le signe est negatif
                if (i != 0) {
                    if (strTerm!="") {
                        //si ce nest pas le premier signe
                        Terme terme = new Terme(strTerm); //creer un nouveau terme
                        CoupleTerme ct = new CoupleTerme(terme, signe); //creer un couple terme avec son signe
                        coupleTermes.add(ct); //le rajouter au tableau
                        signe = '-'; //signe du prochain terme
                        strTerm = ""; //
                    }else if (signe == '-') signe ='+'; //-*-=+
                    else signe ='-';

                }
            }else {
                strTerm = strTerm + s.charAt(i); //rajouter le caractere au terme
            }



        }

        if (!strTerm.equals("")) //si le dernier terme n'est pas vide
        {
            Terme terme = new Terme(strTerm); //creer le terme
            CoupleTerme ct = new CoupleTerme(terme , signe); //creer un couple terme avec son signe
            coupleTermes.add(ct); //le rajouter au tableau
        }else throw new OperandeManquanteException(); //operande manquante car le dernier terme est vide

        return true;
    }
}
