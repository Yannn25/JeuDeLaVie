package com.example.bash;

import java.util.List;

/**
 * Automate Cellulaire
 */
public class AutomateCellulaire {

    private Grille grille;
    private Regles regles;

    private AutomateCellulaire(){
        super();
    }
    protected AutomateCellulaire(Grille grille) {
        this.grille = grille;
    }

    /**
     * Affichage d'une grille en fonction des etats des cellules présente dessus
     */
    public void affichageGrille() {
        for (int i = 0; i < grille.getLigne(); i++) {
            String line = "| ";
            for (int j = 0; j < grille.getColone(); j++) {
                line += grille.getCellulePos(i, j).estVivant() ? ColorText.RED+"* "+ColorText.RESET : ". ";
            }
            line += "|";
            System.out.println(line);
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public int compteCell(int x, int y) {
        if(x < 0 || x >= grille.getLigne())
            return 0;
        if(y < 0 || y >= grille.getColone())
            return 0;
        return this.grille.getCellulePos(x,y).getEtat();
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public int compteVoisin(int x, int y) {
        int cpt = 0;
        for (int[] pos : regles.getVoisinage()) {
            int voisinX = x + pos[0];
            int voisinY = y + pos[1];
            cpt += compteCell(voisinX, voisinY);
        }
        return cpt;
    }

    /**
     *
     */
    public void uneEtape() {
        Grille res = new Grille(this.grille);
        for(int i = 0; i < grille.getLigne(); i++) {
            for(int j = 0; j < grille.getColone(); j++) {
                int nbVoisin = compteVoisin(i,j);
                if (this.grille.getCellulePos(i, j).estVivant()) {
                    if (regles.doitSurvivre(nbVoisin)) {
                        res.getCellulePos(i, j).setVivant();
                    } else {
                        res.getCellulePos(i, j).setMort();
                    }
                } else {
                    if (regles.doitNaitre(nbVoisin)) {
                        res.getCellulePos(i, j).setVivant();
                    }
                }
            }
        }
        this.grille.setEspaceJeu(res.getEspaceJeu());
    }


    /**
     * Classe Builder interne
     */
    public static class Builder {
        private Grille grille;
        private Regles regles;

        public Builder() {

        }

        public Builder setGrille(Grille grille) {
            this.grille = grille;
            return this;
        }

        public Builder setRegles(Regles regles) {
            this.regles = regles;
            return this;
        }


        public AutomateCellulaire build() {
            AutomateCellulaire automate = new AutomateCellulaire();
            automate.grille = this.grille;
            automate.regles = this.regles;
            return automate;
        }
    }

}
