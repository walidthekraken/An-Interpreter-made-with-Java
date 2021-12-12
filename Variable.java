class Variable extends Symbole implements Calculatrice {
    private double valeur; //Contient la valeur numérique de la variable
    public Variable(String n) throws ExecutionExceptions{
        super(n);
        if (n.isEmpty()) throw new OperandeManquanteException();
        estCorrecte();
    }
    public double getValue() {
        return valeur;
    }

    public void setValeur(double valeur){
        this.valeur = valeur;
    }
    public double calculer(){
        return valeur; //Contient la valeur numérique de la variable
    }
//retourne vrai si le nom de variable est valide, sinon lance une
//exception de type VariableException

    public boolean estCorrecte() throws ExecutionExceptions {
        int i=0;
        boolean correct = true;
        //test si le nom du variable est reserve
        if (getNom().equals("sin") || getNom().equals("cos") || getNom().equals("tan") || getNom().equals("sqrt") || getNom().equals("abs") || getNom().equals("log") || getNom().equals("let") || getNom().equals("print")){
            throw new ReservedNameException();
        }else {
            while (i < getNom().length() && correct) {
                int ascii = (int) getNom().charAt(i); //get code ascii of each char
                if (i == 0) { //test si le premier character est un character interdit
                    if (ascii >= 32 && ascii <= 64) { //si c'est un numero le premier caractere
                        correct = false;
                        break;
                    } else {
                        i++; //move to next character
                    }
                } else {
                    //test si le character n'est pas une lettre ou un numero
                    if ((ascii >= 32 && ascii <= 47) || (ascii >= 58 && ascii <= 64) || (ascii >= 91 && ascii <= 96) || ascii >= 123) //si c'est un caractere special (different des nums et lettres)
                    {
                        correct = false;

                        break;
                    } else {
                        //test si le character est une lettre ou un numero
                        if ((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
                            i++; //move to next character
                        }
                    }
                }
            }

            if (!correct) {
                throw new VariableException("L'element "+getNom()+" est errone.");
            } else {
                return true;
            }
        }
    }

    static public boolean estCorrect(String s){
        int i=0;
        boolean correct = true;

        while (i < s.length() && correct) {
            int ascii = (int) s.charAt(i);
            if (i == 0) {
                if (ascii >= 32 && ascii <= 64) { //si c'est un numero le premier caractere
                    correct = false;
                    break;
                } else {
                    i++;
                }
            } else {
                if ((ascii >= 32 && ascii <= 47) || (ascii >= 58 && ascii <= 64) || (ascii >= 91 && ascii <= 96) || ascii >= 123) //si c'est un caractere special (different des nums et lettres)
                {
                    correct = false;

                    break;
                } else {
                    if ((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
                        i++;
                    }
                }
            }
        }
        return correct;
    }

}