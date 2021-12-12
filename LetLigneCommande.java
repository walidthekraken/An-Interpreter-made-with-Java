public class LetLigneCommande extends LigneCommande{

    private Variable variable; //variable de laffectation

    public LetLigneCommande(String message) throws Exception{
        super.setUserCode(message); //ligne de commande
        super.setExpression(extraireExpression(message)); //expression extraite a partir de la ligne de commande
    }

    @Override
    public boolean estCorrecte() {
        return !getUserCode().equals("");
    }

    @Override
    public boolean printResult() throws ExecutionExceptions{
        Execution.letSymbole(variable,getExpression().calculer()); //ajouter la variable dans le tableau
        System.out.println("OK! Affectation correcte.");
        return true;
    }

    @Override
    public Expression extraireExpression(String expStr) throws Exception{

        String[] parts = expStr.split("let",2); //diviser avec 'let' comme separateur

        if (parts[0].trim().equals("")){ //avant le let, rien ne doit apparaitre
            if (!parts[1].equals("")){ //apres le let, il doit y' avoir quelquechose
                parts = parts[1].trim().split("=",2); //diviser avec le egale comme separateur.
                if (!parts[0].trim().equals("")) //si le nom de la variable avant le egale n'est pas vide
                {
                    try {
                        if (!parts[1].trim().equals("")) { //trouver l'expression apres le egale
                            setVariable(new Variable(parts[0].trim())); //creer une variable a partir du nom extrait de la ligne de commande
                            if (parts[1].trim().isEmpty()) throw new WrongExpressionException("Expression a affecter est vide.");
                            return new Expression(parts[1].trim()); //retourner l'expression extraite de la ligne de commande
                        } else throw new WrongExpressionException("L'expression apres l'affectation est vide.");
                    }catch (IndexOutOfBoundsException e){ //la division selon le separateur '=' n'a pas ete effectuee, donc le signe introuvable
                        throw new WrongExpressionException("Il n'y a pas une affectation. Veuillez rajouter le signe '=' suivi d'une expression.");
                    }
                }else throw new WrongExpressionException("Veuillez specifier le nom de la variable a creer ou modifier.");
            }else
            {
                throw new WrongExpressionException("Veuillez specifier le nom de la variable a creer ou modifier.");
            }
        }else {
            throw new WrongExpressionException(" a ");
        }

    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }
}
