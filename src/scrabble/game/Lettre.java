package scrabble.game;

import java.io.*;
//Everything is all Right !!!
public class Lettre {
    private static int valeurLettres;
    private static int lettresUtilise = 0;

    //Récuperer la valeur Lettres utilisé
    public static int getLettresUtilise() {
        return lettresUtilise;
    }//OK
    //Ajoute une lettre utilisé
    public static void addLettresUtilise(){
        lettresUtilise += 1;
    }//OK
    //Vérifie si l'on peut piocher de nouvelle lettre
    public static boolean nouvellesLettresDisponible(){
        int maxLettres = 104;
        return getLettresUtilise() < maxLettres;
    }//OK
   //Vérifie à partir de la base de données des mots scrabble s'il existe le mot choisi par l'utilisateur
    public static boolean motValide(String pMot){
        String line, recherche = pMot, path="MotValideScrabble.txt";
        System.out.println(recherche);
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null){
                if (line.contains(recherche)){
                    return true;
                }
            }
        br.close();
        } catch (FileNotFoundException exc){
            System.out.println("File not found");
            return false;
        } catch (IOException ice){
            System.out.println("Erreur IO");
            return false;
        }
        System.out.println("Je ne sais pas ce qui se passe");
        return false;
    }// OK
}
