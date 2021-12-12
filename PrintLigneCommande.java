public class PrintLigneCommande extends LigneCommande{

    public PrintLigneCommande(String message) throws Exception{
        super.setUserCode(message);
        super.setExpression(extraireExpression(message));
    }

    @Override
    public boolean estCorrecte() {
        return true;
    }

    @Override
    public boolean printResult() throws ExecutionExceptions {

        System.out.println("Resultat du 'print' : "+ getExpression().calculer() );

        return true;
    }

    @Override
    public Expression extraireExpression(String expStr) throws Exception{
        String[] parts = expStr.split("print",2);

        if (parts[0].equals("")) //premiere partie avant le print sera vide
        {
            if (!parts[1].equals("")) //la deuxieme partie apres le print contiendera l'expression
            {
                return new Expression(parts[1].trim()); //creer une nouvelle expression
            }else
            {
                    throw new WrongExpressionException("L'expression est vide.");
            }
        }else {
            throw new ExecutionExceptions();
        }

    }
}
