
public interface Calculatrice {

    //Permet de vérifier si l’objet implémentant l’interface est ecrit correctement
    boolean estCorrecte() throws ExecutionExceptions;

    //Retourne la valeur de l’objet implémentant l’interface
    double calculer() throws ExecutionExceptions;
}
