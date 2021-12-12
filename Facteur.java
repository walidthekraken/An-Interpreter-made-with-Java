import java.util.*;

public class Facteur implements Calculatrice {

    private String strFact; //le facteur sous forme de chaine de caracteres

    private List<Element> elements = new ArrayList<Element>(); //tableau d'elements
    private Deque<Element> elements_pile = new ArrayDeque<Element>();

    public Facteur(String s) throws ExecutionExceptions {
        strFact = s.trim();
       if (estCorrecte()) {
           creerElements(s.trim()); //creer tableau des elements
       }else throw new OperandeManquanteException();
    }

    @Override
    public boolean estCorrecte() {
        return !strFact.trim().equals("");
    }

    public double calculer() throws ExecutionExceptions{

        double result = elements_pile.pop().calculer(); //depiler
        while (!elements_pile.isEmpty()){
            double k = elements_pile.pop().calculer();
            if (k==0 && result<0) throw new PowerZeroException(); //error
            result = Math.pow(k,result); //calcul puissance
        }
        return result;
    }

    public boolean creerElements(String s) throws ExecutionExceptions{
        Deque<Character> pileParantheses = new LinkedList<Character>(); //pile des parantheses

        char signe = '^'; //l'operateur des operations a effectuer

        int i;

        s = "^"+s; //operateur du premier element

        String strElement = ""; //element courant

        for (i=0;i<s.length();i++)
        {
            if (s.charAt(i) == '('){
                pileParantheses.add('('); //empiler parenthese ouvrante
            }
            if (s.charAt(i) == ')'){
                pileParantheses.removeFirst(); //depiler parantheses fermantes
            }

            if (s.charAt(i) == '^' && pileParantheses.isEmpty()) { //si l'operateur ne se trouver pas a linterieure des parantheses
                if (i != 0) {
                    Element element = new Element(strElement); //l'element a creer
                    elements.add(element); //ajouter l'element de la liste
                    elements_pile.push(element);
                    signe = '^'; //le signe du prochaine operateur
                    strElement = ""; //prochain element
                }
            }
            else {
                strElement = strElement + s.charAt(i); //rajouter a l'element courant
            }

        }
        if (!strElement.equals("")) //lelement qui n'a pas encore ete enregistre
        {
            Element element = new Element(strElement); //dernier element
            elements.add(element); //le rajouter dans la liste
            elements_pile.push(element);
        }else throw new OperandeManquanteException();

        return true;
    }
}
