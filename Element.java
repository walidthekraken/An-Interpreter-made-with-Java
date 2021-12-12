public class Element implements Calculatrice{
    private String element;//lelement sous forme de chaine de caracteres

    public Element (String s) throws ExecutionExceptions {
        element = s.trim();
        if (!estCorrecte()) throw new OperandeManquanteException();
    }

    public boolean estCorrecte() {
        return !element.isEmpty();
    }

    public double calculer() throws ExecutionExceptions {
            element = element.trim();
            if (element.trim().charAt(0) == '(') { //CEST UNE EXPRESSION ENTRE PARANTHESES
                if (element.trim().charAt(element.trim().length()-1)==')') {
                    String strExp = element.trim().substring(1, element.length() - 1);
                    if (strExp.trim().isEmpty()) throw new WrongExpressionException("L'expression entre parentheses est vide");
                    Expression expression = new Expression(strExp); //enlever premiere et derniere parantheses
                    return expression.calculer();
                }throw new WrongElementException(element);

            }else if (element.trim().contains("(")){//CEST UNE FONCTION STANDARD
                int i = 0 ;
                String name = "";
                String strExp = "";
                boolean cont = true;

                //EXTRAIRE CE QUI SE TROUVE AVANT LA PARANTHESE
                while (i<element.trim().length() && cont){
                    if (element.trim().charAt(i)!='(')
                    {
                        name = name + element.trim().charAt(i);

                    } else {
                        cont = false;
                    }
                    i++;
                }

                String[] parts = element.split("\\(",2); //on divise en fct de la premiere paranthese

                if (parts[1].trim().charAt(parts[1].trim().length()-1)==')') //si le dernier est aussi une parenthese fermante
                {
                    strExp = parts[1].trim().substring(0,parts[1].trim().length()-1);
                }else throw new WrongElementException(element);

                if (strExp.trim().isEmpty()) throw new WrongExpressionException("L'expression entre parentheses est vide.");

                double result;

                if (Character.isDigit(name.trim().charAt(0))) throw new FctWrongName(name);
                if (name != name.trim()) throw new WrongExpressionException("Espace avant les parantheses.");

                try {
                    result = Execution.rechFct(name).getValue(new Expression(strExp.trim()).calculer()); //retourne la valeur du resultat de l'expression selon la fonction standard
                }catch (NullPointerException e) {
                    throw new FctDoesNotExistException(name); //fonction nexiste pas
                }

                return result; //Retourner le resultat
            }else { //c'est une variable ou nombre
                double result;
                try {
                    Nombre number = new Nombre(element); //c'est un nombre
                    result = number.calculer(); //resultat
                }catch (WronNumberException e) { //ce n'est pas un nombre donc il ne reste que les variables
                    if (!element.isEmpty()) {
                        if (Variable.estCorrect(element)) {

                            Variable v = Execution.rechVar(element);
                            if (v != null) result = v.calculer(); //si la variable existe deja
                            else throw new VariableException("Variable '"+ element +"' n'a pas ete delcaree.");
                        }else throw new WrongElementException(element);
                    }else {
                        throw new OperandeManquanteException();
                    }
                }
                return result;
            }

    }

}
