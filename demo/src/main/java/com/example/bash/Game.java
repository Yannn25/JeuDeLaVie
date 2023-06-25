package com.example.bash;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Gère la gestion d'une partie
 */
public class Game {

    public static Scanner sc = new Scanner(System.in);

    /**
     * Liste tous les exemples contenu dans Examples de Configuration
     * @param rep le repertoire
     */
    public static void listerExemples(String rep) {
        Path cheminRepertoire = Paths.get(rep);
        int i = 0;
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

    /**
     * Permet de configurer une grille sans fichier
     * @return la grille en question
     */
    public static Grille gestionConfigPersonnaliser() {
        System.out.println("Combien de ligne pour votre configuration initial :\n");
        int lignes = sc.nextInt();
        System.out.println("Combien de colone :\n");
        int col = sc.nextInt();
        Grille grid = new Grille(lignes, col);
        System.out.println("Veuillez entrer les cellules de la grille (0 pour morte, 1 pour vivante(separer par une virgule)) :");
        sc.nextLine(); // Ignorer le retour à la ligne après la saisie de colonnes
        Cellule espace [][] = new Cellule[lignes][col];
        try {
            for (int i = 0; i < lignes; i++) {
                String ligne = sc.nextLine();
                String[] cellules = ligne.split(",");
                for (int j = 0; j < col; j++) {
                    int etatCellule = Integer.parseInt(cellules[j].trim());
                    etatCellule = etatCellule > 0 ? 1 : 0;
                    espace[i][j] = new Cellule(etatCellule);
                }
            }
            grid.setEspaceJeu(espace);
        } catch (Exception e) {
            System.out.println("Erreur de saisie. Veuillez reessayer.");
            gestionConfigPersonnaliser();
        }
        return grid;
    }

    public static Regles ReglesPersonnaliser() {
        System.out.println("Entrez les nombres de voisins necessaires pour une naissance (separes par des virgules) :");
        sc.nextLine(); // Vide la ligne précédente
        String naissanceInput = sc.nextLine();
        System.out.println("Entrez les nombres de voisins necessaires pour une survie (separes par des virgules) :");
        String survieInput = sc.nextLine();

        List<Integer> seuilNaissance = parseSeuils(naissanceInput);
        List<Integer> seuilsSurvie = parseSeuils(survieInput);
        System.out.println("Souhaitez-vous utiliser un voisinage classique (8 voisins) ? (Oui/Non)");
        String rep = sc.nextLine();
        if(rep.toLowerCase().equals("oui"))
            return new Regles(seuilNaissance,seuilsSurvie);
        System.out.println("Par rapport à une position (x, y) entrez les coordonnees des voisins à surveiller (sous la forme (x, y), separes par des virgules) :");
        String voisinageInput = sc.nextLine();
        List<int[]> voisinage = parseVoisinage(voisinageInput);
        return new Regles(voisinage,seuilNaissance, seuilsSurvie);
    }

    private static List<Integer> parseSeuils(String input) {
        List<Integer> seuils = new ArrayList<>();
        String[] seuilTokens = input.split(",");
        for (String token : seuilTokens) {
            try {
                int seuil = Integer.parseInt(token.trim());
                seuils.add(seuil);
            } catch (NumberFormatException e) {
                e.getMessage();
            }
        }
        return seuils;
    }
    private static List<int[]> parseVoisinage(String input) {
        List<int[]> voisinage = new ArrayList<>();
        String[] voisinageTokens = input.split("\\),");
        for (String token : voisinageTokens) {
            try {
                // Supprimer les parenthèses et séparer les coordonnées sur la virgule
                String[] coordTokens = token.replace("(", "").replace(")", "").trim().split(",");
                int x = Integer.parseInt(coordTokens[0].trim());
                int y = Integer.parseInt(coordTokens[1].trim());
                int[] coord = {x, y};
                voisinage.add(coord);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                // Ignorer les tokens qui ne sont pas des coordonnées valides
            }
        }
        System.out.println(voisinage);
        return voisinage;
    }


    /**
     *
     * @param repertoire
     * @param choix
     * @return
     */
    public static String obtenirNomFichier(String repertoire, int choix) {
        Path cheminRepertoire = Paths.get(repertoire);
        int i = 0;
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

    /**
     * Configuration d'une automate cellulaire avec des règles spécifiques
     * @param choix
     * @param grid
     * @return
     */
    public static AutomateCellulaire gestionRegle(int choix, Grille grid) {
        AutomateCellulaire automate = null;
        List<int[]> voisinage;
        Regles regles;
        voisinage = Regles.voisinageClassique();
        switch (choix) {
            case 1:
                regles = new Regles(voisinage,Arrays.asList(3),Arrays.asList(2, 3));
                automate = new AutomateCellulaire.Builder()
                        .setRegles(regles)
                        .setGrille(grid)
                        .build();
                sc.nextLine();
                return automate;
            case 2:
                regles = new Regles(voisinage,Arrays.asList(3,6),Arrays.asList(2, 3));
                automate = new AutomateCellulaire.Builder()
                        .setRegles(regles)
                        .setGrille(grid)
                        .build();
                sc.nextLine();
                return automate;
            case 3:
                regles = new Regles(voisinage,Arrays.asList(3,6,7,8),Arrays.asList(3,4,6,7,8));
                automate = new AutomateCellulaire.Builder()
                        .setRegles(regles)
                        .setGrille(grid)
                        .build();
                sc.nextLine();
                return automate;
            case 4:
                voisinage = Arrays.asList(
                        new int[] {0, -1},
                        new int[] {0, 1}
                );
                regles = new Regles(voisinage,Arrays.asList(2),Arrays.asList(1));
                automate = new AutomateCellulaire.Builder()
                        .setRegles(regles)
                        .setGrille(grid)
                        .build();
                sc.nextLine();
                return automate;
            case 5:
                regles = ReglesPersonnaliser();
                automate = new AutomateCellulaire.Builder()
                        .setRegles(regles)
                        .setGrille(grid)
                        .build();
                return automate;
            default:
                System.out.println("Plage non valide...\n");
                break;
        }
        return null;
    }

    /**
     * Lance une partie
     */
    public void start() {
        FichierGrille fic = new FichierGrille();
        Grille grid; AutomateCellulaire game;

        System.out.println("Hello World !\nChoisissez le numero de votre configuration initial :\n");
        String rep = System.getProperty("user.dir");
        rep = rep+"/demo/src/main/java/com/example/Exemples_de_configurations/";
        listerExemples(rep);
        System.out.println("99. Personnalisee");
        int choix = Integer.parseInt(sc.nextLine());
        if(choix == 99) {
            grid = gestionConfigPersonnaliser();
        } else {
            System.out.println("Configuration : " + obtenirNomFichier(rep, choix));
            rep = rep + obtenirNomFichier(rep, choix);
            try {
                List<String> lst = fic.lireFichier(rep);
                grid = lst.get(0).startsWith("!") ? Grille.init(fic.convertionCells(lst)) : Grille.init(fic.convertionFichier(lst));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\nA quelle variante vous souhaitez jouer : \n1.Jeu de la Vie,Conways(B3/S23)\n2.HighLife(B36/S23)\n3.Day & Night(B3678/S34678)\n4. Grille 1D(B2/S1)\n5. Personnalisee\n");
        choix = sc.nextInt();
        game = gestionRegle(choix, grid);
        while (true) {
            System.out.println("Entrez pour passer une etape ou 'q' pour quitter :");
            game.affichageGrille();
            String input = sc.nextLine();
            if (input.equals("q")) {
                System.out.println("Taper 'q' pour quitter l'application sinon entrez pour revenir au menu :");
                input = sc.nextLine();
                if(input.equals("q")){
                    sc.close();
                    System.exit(0);
                } else {
                    start();
                }
            }
            game.uneEtape();
        }

    }

}
