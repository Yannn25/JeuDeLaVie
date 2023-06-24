package com.example.bash;


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
    public static Grille gestionConfigPersonnaliser(Scanner sc) {
        System.out.println("Combien de ligne pour votre configuration initial :\n");
        int lignes = sc.nextInt();
        System.out.println("Combien de colone :\n");
        int col = sc.nextInt();
        Grille grid = new Grille(lignes, col);
        System.out.println("Veuillez entrer les cellules de la grille (0 pour morte, 1 pour vivante) :");
        sc.nextLine(); // Ignorer le retour à la ligne après la saisie de colonnes
        Cellule espace [][] = new Cellule[lignes][col];

        for (int i = 0; i < lignes; i++) {
            String ligne = sc.nextLine();
            String[] cellules = ligne.split(",");
            for (int j = 0; j < col; j++) {
                int etatCellule = Integer.parseInt(cellules[j].trim());
                espace[i][j] = new Cellule(etatCellule);
            }
        }
        grid.setEspaceJeu(espace);
        return grid;
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

    public static AutomateCellulaire gestionRegle(int choix, Grille grid) {
        AutomateCellulaire game;

        switch (choix) {
            case 1:
                game = new JeuDeLaVie(grid);
                break;
            case 2:
                game = new HighLife(grid);
                break;
            case 3:
                game = new NightDay(grid);
                break;
            case 4:
                System.out.println("Non géré pour le moment...\n");
                game = null;
                break;
            default:
                game = new JeuDeLaVie(grid);
                break;
        }
        return game;
    }

    public static void main(String[] args) {
        FichierGrille fic = new FichierGrille();
        Grille grid; AutomateCellulaire game = null;

        System.out.println("Hello World !\nChoisissez le numéro de votre configuration initial :\n");
        String rep = System.getProperty("user.dir");
        rep = rep+"/demo/src/main/java/com/example/Exemples_de_configurations/";
        listerExemples(rep);
        System.out.println("99. Personnalisée");
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        if(choix == 99) {
            grid = gestionConfigPersonnaliser(scanner);
        } else {
            System.out.println("Configuration : " + obtenirNomFichier(rep, choix));
            rep = rep + obtenirNomFichier(rep, choix);
            try {
                List<String> lst = fic.lireFichier(rep);
                grid = Grille.init(fic.convertionFichier(lst));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\n\nHello World !\nA quelle variante vous souhaitez jouer : \n1.Jeu de la Vie,Conways(B2/S23)\n2.HighLife(B36/S23)\n3.Day & Night(B3678/S34678)\n4. Personnalisée\n");
        choix = scanner.nextInt();
        game = gestionRegle(choix, grid);
        if (game != null) {
            game.affichageGrille();
            game.uneEtape();
            game.affichageGrille();
            game.uneEtape();
            game.affichageGrille();
        } else {
            System.out.println("Variante non gérée pour le moment.");
        }
        scanner.close();

    }


}
