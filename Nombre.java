public class Nombre implements Calculatrice {

    private String value; //la valeur du nombre en string

    public Nombre(String value){
        this.value=value;
    }
    public void setValue(String v){
        value=v;
    }
    public double calculer() throws ExecutionExceptions{
        if (estCorrecte()) {
            return Double.parseDouble(value); //retourne la valeur en double
        }
        throw new WronNumberException();
    }
    public boolean estCorrecte() throws ExecutionExceptions{
        try{
            Double.parseDouble(value); //test si il n'y a pas d'erreur lors du transformation
            return true;
        }catch (NumberFormatException e){
            throw new WronNumberException();

        }
    }


}
