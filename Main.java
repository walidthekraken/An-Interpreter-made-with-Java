import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in); //lecteur du clavier
        int i=0;
        new Execution(); //creer un interpreteur = preparer les fonctions standards
        PageDeGarde();
        while (i==0) {
            System.out.print("\n>   ");
            String message = sc.nextLine() ; //ligne de commande
            if ((!message.equals("end")) && (!message.equals("sym"))) {
                try {
                    LigneCommande ligneCommande = LigneCommande.creerLigneCommande(message.trim()); //creer une commande
                    ligneCommande.printResult(); //afficher le resultat de la commande
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Veuillez entrer une commande et une expression.");

                } catch (ExecutionExceptions e) {
                    System.out.println(e.getMessage()); //afficher le message d'erreur
                }
            }else if (message.equals("sym")){
                Execution.showSymboles(); //afficher la liste
            }else {
                System.out.println("Fin du programme.");
                i = 1; //quitter
            }
        }
    }

    public static void PageDeGarde(){
        System.out.println("  _______ _____    _____   ____   ____    ___   _____ _____  \n" +
                " |__   __|  __ \\  |  __ \\ / __ \\ / __ \\  |__ \\ / ____|  __ \\ \n" +
                "    | |  | |__) | | |__) | |  | | |  | |    ) | |    | |__) |\n" +
                "    | |  |  ___/  |  ___/| |  | | |  | |   / /| |    |  ___/ \n" +
                "    | |  | |      | |    | |__| | |__| |  / /_| |____| |     \n" +
                "    |_|  |_|      |_|     \\____/ \\____/  |____|\\_____|_|     \n" +
                "Annee 2020 - 2021 | G01 | Realise par Azizi Walid, Bensalma Ibrahim");
        System.out.println("  _____ _   _ _______ ______ _____  _____  _____  ______ _______ ______ _    _ _____  \n" +
                " |_   _| \\ | |__   __|  ____|  __ \\|  __ \\|  __ \\|  ____|__   __|  ____| |  | |  __ \\ \n" +
                "   | | |  \\| |  | |  | |__  | |__) | |__) | |__) | |__     | |  | |__  | |  | | |__) |\n" +
                "   | | | . ` |  | |  |  __| |  _  /|  ___/|  _  /|  __|    | |  |  __| | |  | |  _  / \n" +
                "  _| |_| |\\  |  | |  | |____| | \\ \\| |    | | \\ \\| |____   | |  | |____| |__| | | \\ \\ \n" +
                " |_____|_| \\_|  |_|  |______|_|  \\_\\_|    |_|  \\_\\______|  |_|  |______|\\____/|_|  \\_\\n");
    }

}
