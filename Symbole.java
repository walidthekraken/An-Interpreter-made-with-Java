abstract class Symbole implements Comparable<Symbole>{

    private String nom; //le nom du symbole


    public Symbole(String n){
        nom = n;
    }
    public void setNom(String nom){
        this.nom=nom;
    }
    public String getNom(){
        return nom;
    }

    @Override
    public int compareTo(Symbole o) { //comparer les symboles selon le nom
        return nom.compareTo(o.nom);
    }
}