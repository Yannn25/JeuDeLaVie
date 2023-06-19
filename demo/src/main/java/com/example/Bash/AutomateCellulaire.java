package com.example.Bash;

/**
 * Automate Cellulaire
 */
public abstract class AutomateCellulaire {

    private Grille grille;
    public AutomateCellulaire(Grille grille) {
        this.grille = grille;
    }

    /**
     * Affichage d'une grille en fonction des etats des cellules présente dessus
     */
    public void affichageGrille() {
        System.out.println("-------");
        for (int i = 0; i < grille.getLigne(); i++) {
            String line = "|";
            for (int j = 0; j < grille.getColone(); j++) {
                line += grille.getCellulePos(i, j).estVivant() ? "*" : ".";
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("-------\n");
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
        cpt += compteCell(x-1,y-1);
        cpt += compteCell(x-1,y);
        cpt += compteCell(x-1,y+1);

        cpt += compteCell(x,y-1);
        cpt += compteCell(x,y+1);

        cpt += compteCell(x+1,y-1);
        cpt += compteCell(x+1,y);
        cpt += compteCell(x+1,y+1);

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
                if(this.grille.getCellulePos(i,j).estVivant()) {
                    if(nbVoisin == 2 || nbVoisin == 3)
                        res.getCellulePos(i,j).setVivant();
                    else
                        res.getCellulePos(i,j).setMort();
                } else {
                    if(nbVoisin == 3)
                        res.getCellulePos(i,j).setVivant();
                }
            }
        }
        this.grille.setEspaceJeu(res.getEspaceJeu());
    }
}
