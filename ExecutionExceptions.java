public class ExecutionExceptions  extends Exception{
    @Override
    public String getMessage() {
        return "Veuillez entrer une commande valide, Indice: les commandes disponibles sont let et print.";
    }
}

class VariableException extends ExecutionExceptions {
    private String details = "";
    public VariableException(String s)
    {
        details = s;
    }

    public VariableException(){}

    @Override
    public String getMessage() {
        return "Veuillez entrer une variable valide. "+details;
    }
}

class FctDoesNotExistException extends VariableException {
    private String s = "";
    public FctDoesNotExistException(String s){
        this.s = s;
    }
    @Override
    public String getMessage() {
        return "Erreur : La fonction '"+s+"' n'existe pas.";
    }
}

class FctWrongName extends VariableException {
    private String s = "";
    public FctWrongName(String s){
        this.s = s;
    }
    @Override
    public String getMessage() {
        return "Erreur : Nom de fonction '"+s+"' est interdit.";
    }
}

class NotExistException extends VariableException {
    @Override
    public String getMessage() {
        return "Erreur: Variable inexistante.";
    }
}

class ReservedNameException extends VariableException {
    @Override
    public String getMessage() {
        return "Erreur: nom de variable reserve.";
    }
}

class OperationException extends ExecutionExceptions {
    @Override
    public String getMessage() {
        return "Erreur: fausse operation.";
    }
}

class WrongExpressionException extends ExecutionExceptions {
    private String details="";

    public WrongExpressionException(String s)
    {
        details = s;
    }
    public WrongExpressionException(){
    }
    @Override
    public String getMessage() {
        return "Expression erronee. "+details;
    }
}

class WrongElementException extends ExecutionExceptions {
    private String details="";

    public WrongElementException(String s)
    {
        details = s;
    }

    @Override
    public String getMessage() {
        return "Erreur: l'element '"+details+"' pose probleme.";
    }
}

class LogarithmicException extends OperationException {
    @Override
    public String getMessage() {
        return "Erreur : La fonction Log n'accepte pas un parametre negatif ou nul";
    }
}

class TanException extends OperationException {
    @Override
    public String getMessage() {
        return "Erreur : La fonction Tan n'accepte pas un parametre egale a k*pi/2";
    }
}

class RootException extends OperationException {
    @Override
    public String getMessage() {
        return "Erreur : La fonction Sqrt n'accepte pas un parametre negatif";
    }
}

class DevideZeroException extends OperationException {
    @Override
    public String getMessage() {
        return "Erreur arithmetique: Division par zero interdite.";
    }
}

class PowerZeroException extends OperationException {
    @Override
    public String getMessage() {
        return "Erreur arithmetique: Zero à la puissance négative est interdit.";
    }
}

class ParentheseException extends  WrongExpressionException {
    @Override
    public String getMessage() {
        return "Erreur: Parenthese manquante";
    }
}

class ParentheseOuvranteException extends ParentheseException {
    @Override
    public String getMessage() {
        return "Erreur: Parenthese ouvrante manquante";
    }
}
class ParentheseFermanteException extends ParentheseException {
    @Override
    public String getMessage() {
        return "Erreur: Parenthese fermante manquante";
    }
}

class WronNumberException extends ExecutionExceptions{
    @Override
    public String getMessage() {
        return "Faux nombre.";
    }
}

class OperandeManquanteException extends  ExecutionExceptions {
    @Override
    public String getMessage() {
        return "Erreur: Operande manquante.";
    }
}

