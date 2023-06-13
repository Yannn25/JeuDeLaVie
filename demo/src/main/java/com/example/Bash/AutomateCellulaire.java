package com.example.Bash;

/**
 * Gère l'affichage des étapes
 */
public abstract class AutomateCellulaire {

    private Grille grille;
    public AutomateCellulaire(Grille grille) {
        this.grille = grille;
    }
    public void affichageGrille() {
        System.out.println("-------");
        for (int i = 0; i < grille.getLigne(); i++) {
            String line = "|";
            for (int j = 0; j < grille.getColone(); j++) {
                line += grille.getCellulePos(i, j) == 1 ? "*" : ".";
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("-------\n");
    }
}
