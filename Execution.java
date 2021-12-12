import java.util.ArrayList;
import java.util.List;

public class Execution {

    static private List<Symbole> symboles = new ArrayList<Symbole>(); //liste des symboles

    static public boolean executer(LigneCommande ligneCommande) throws Exception{
        ligneCommande.printResult(); //affiche le resultat de l'execution
        return true;
    }

    public Execution() throws ExecutionExceptions{
        //creation des fonctions standards
        symboles.add(new Sinus("sin",fctcodes.sin));
        symboles.add(new Cosinus("cos",fctcodes.cos));
        symboles.add(new Log("log",fctcodes.log));
        symboles.add(new Tan("tan",fctcodes.tan));
        symboles.add(new Abs("abs",fctcodes.abs));
        symboles.add(new Sqrt("sqrt",fctcodes.sqrt));
        Variable v =  new Variable("pi");
        v.setValeur(Math.PI);
        symboles.add(v);
    }

    static public boolean letSymbole(String name, double value) throws ExecutionExceptions{

        Symbole s = rechSymbole(name);
        if (name=="cos" || name=="sin" || name=="tan" || name=="log" || name=="abs" || name=="sqrt") throw new ReservedNameException();
        if (s!=null) {
            ((Variable)s).setValeur(value);
        }else{
            Variable var = new Variable(name);
            var.setValeur(value);
            symboles.add(var);
        }

        return false;
    }

    static public boolean letSymbole(Variable v, double value) throws ExecutionExceptions{

        Symbole s = rechSymbole(v.getNom());

        if (s!=null) {
            if (s.getNom()=="cos" || s.getNom()=="sin" || s.getNom()=="tan" || s.getNom()=="log" || s.getNom()=="abs" || s.getNom()=="sqrt") throw new ReservedNameException();
            ((Variable)s).setValeur(value);
        }else{
            Variable var = new Variable(v.getNom());
            var.setValeur(value);
            symboles.add(var);
        }

        return false;
    }

    static public boolean letSymbole(Symbole s, double value) throws ExecutionExceptions{

        Symbole s2 = rechSymbole(s);

        if (s2!=null) {
            ((Variable)s).setValeur(value);
        }else{
            Variable var = new Variable(s.getNom());
            var.setValeur(value);
            symboles.add(var);
        }

        return false;
    }

    static public Variable rechVar(String name) throws ExecutionExceptions{
        if (name=="cos" || name=="sin" || name=="tan" || name=="log" || name=="abs" || name=="sqrt") throw new ReservedNameException();
        for (Symbole s : symboles){
            if (name.equals(s.getNom())){
                if (s.getNom()=="cos" || s.getNom()=="sin" || s.getNom()=="tan" || s.getNom()=="log" || s.getNom()=="abs" || s.getNom()=="sqrt") throw new ReservedNameException();
                return (Variable)s;
            }
        }
        return null;
    }

    static public FonctionsStandards rechFct(String name){
        for (Symbole symbole : symboles){
            if (name.equals(symbole.getNom())){
                return (FonctionsStandards)symbole;
            }
        }
        return null;
    }

    static public Symbole rechSymbole(String name) throws ExecutionExceptions{
        if (name=="cos" || name=="sin" || name=="tan" || name=="log" || name=="abs" || name=="sqrt") throw new ReservedNameException();
        for (Symbole symbole : symboles){
            if (name.equals(symbole.getNom())){
                return symbole;
            }
        }
        return null;
    }

    static public Symbole rechSymbole(Symbole s) throws ExecutionExceptions{
        if (s.getNom()=="cos" || s.getNom()=="sin" || s.getNom()=="tan" || s.getNom()=="log" || s.getNom()=="abs" || s.getNom()=="sqrt") throw new ReservedNameException();
        for (Symbole symbole : symboles){
            if (s.getNom().equals(symbole.getNom())){
                return symbole;
            }
        }
        return null;
    }

    static public Symbole rechSymbole(Variable s){

        for (Symbole symbole : symboles){
            if (s.getNom().equals(symbole.getNom())){
                return symbole;
            }
        }
        return null;
    }

    static public void showSymboles(){
        if (!symboles.isEmpty()) {
            System.out.println("Affichage des symboles :");
            for (Symbole s : symboles) {
                if (s instanceof Variable) {
                    System.out.println("Nom : " + s.getNom() + " - Valeur : " + ((Variable) s).getValue());
                }else{
                    System.out.println("Nom : "+s.getNom());
                }
            }

        }
    }

}
