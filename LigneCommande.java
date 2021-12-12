public abstract class LigneCommande {

    private String userCode; //La ligne de commande entree par l'utilisateur
    private Expression expression; //Expression extraite de la ligne de commande

    //Message entre correcte ou pas
    abstract public boolean estCorrecte();

    //Afficher le resultat de l'execution
    abstract public boolean printResult() throws Exception;

    //Extraire l'expression a partir de la ligne de commande
    abstract public Expression extraireExpression(String expStr) throws Exception;

    //Creer un objet de type print ou let
    static public LigneCommande creerLigneCommande(String message) throws Exception {

        String[] parts = message.split(" ",2);

        String firstWord = parts[0]; //la commande entrée

        if (firstWord.equals("print"))
        {
            return new PrintLigneCommande(message);
        }else if (firstWord.equals("let")){

            return new LetLigneCommande(message);
        }else{
            throw new ExecutionExceptions();
        }
    }

    //La ligne de commande de l'utilisateur
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    //La ligne de commande de l'utilisateur
    public String getUserCode() {
        return userCode;
    }
    //Modifier l'expression extraite
    public void setExpression(Expression expression) {
        this.expression = expression;
    }
    //
    public Expression getExpression() {
        return expression;
    }

}
