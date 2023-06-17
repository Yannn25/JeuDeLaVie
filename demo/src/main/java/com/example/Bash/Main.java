package com.example.Bash;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void listerExemples(String rep) {
        Path cheminRepertoire = Paths.get(rep);
        int i = 1;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(cheminRepertoire)) {
            for (Path fichier : stream) {
                if (Files.isRegularFile(fichier)) {
                    System.out.println(i+". "+fichier.getFileName());
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String obtenirNomFichier(String repertoire, int choix) {
        Path cheminRepertoire = Paths.get(repertoire);
        int i = 1;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(cheminRepertoire)) {
            for (Path fichier : stream) {
                if (Files.isRegularFile(fichier)) {
                    if (i == choix) {
                        return fichier.getFileName().toString();
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FichierGrille fic = new FichierGrille();
        Grille grid; AutomateCellulaire game;
        System.out.println("hello world ! \n\nChoisissez le numéro de votre configuration initial :\n");
        String rep = System.getProperty("user.dir");
        rep = rep+"/demo/src/main/java/com/example/Exemples_de_configurations/";
        listerExemples(rep);
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        scanner.close();
        System.out.println("Configuration : "+obtenirNomFichier(rep, choix));
        rep = rep+obtenirNomFichier(rep, choix);
        try {
            List<String> lst = fic.lireFichier(rep);
            grid = Grille.init(fic.convertionFichier(lst));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        game = new JeuDeLaVie(grid);
        game.affichageGrille();
        game.uneEtape();
        game.affichageGrille();
        game.uneEtape();
        game.affichageGrille();

    }
}
