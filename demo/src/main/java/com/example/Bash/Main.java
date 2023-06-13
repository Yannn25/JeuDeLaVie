package com.example.Bash;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> tab = new LinkedList<>(List.of(4,4,0,0,0,1,0,1,1,0,0,1,1,0,0,0,0,0));
        Grille grid = Grille.init(tab);

        JeuDeLaVie Game = new JeuDeLaVie(grid);
        Game.affichageGrille();
        System.out.println("hello world ! \n\nchargement fichier:::::::\n\n");
        FichierGrille fic = new FichierGrille();
        //try {
            String filename="/demo/src/main/java/com/example/Exemples_de_configurations/clock-step0.life";
            Path pathToFile = Paths.get(filename);
            System.out.println(pathToFile.toAbsolutePath());
            //List<String> lst = fic.lireFichier(filename);
            //grid = Grille.init(fic.convertionFichier(lst));
        //} catch (IOException e) {
            //throw new RuntimeException(e);
        //}
        Game = new JeuDeLaVie(grid);
        Game.affichageGrille();
    }
}
