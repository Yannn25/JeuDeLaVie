package com.example.Bash;

/**
 *
 */
public class HighLife extends AutomateCellulaire {
    private Grille grille;
    public HighLife(Grille grille) {
        super(grille);
    }

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
                    if(nbVoisin == 3 | nbVoisin == 6)
                        res.getCellulePos(i,j).setVivant();
                }
            }
        }
        this.grille.setEspaceJeu(res.getEspaceJeu());
    }
}
