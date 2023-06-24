package com.example.bash;

/**
 *
 */
public class NightDay extends AutomateCellulaire {
    private Grille grille;
    public NightDay(Grille grille) {
        super(grille);
    }

    public void uneEtape() {
        Grille res = new Grille(this.grille);
        for(int i = 0; i < grille.getLigne(); i++) {
            for(int j = 0; j < grille.getColone(); j++) {
                int nbVoisin = compteVoisin(i,j);
                if(this.grille.getCellulePos(i,j).estVivant()) {
                    if(nbVoisin == 3 || nbVoisin == 4 | nbVoisin == 6 | nbVoisin == 7 | nbVoisin == 8)
                        res.getCellulePos(i,j).setVivant();
                    else
                        res.getCellulePos(i,j).setMort();
                } else {
                    if(nbVoisin == 3 || nbVoisin == 6 | nbVoisin == 7 | nbVoisin == 8)
                        res.getCellulePos(i,j).setVivant();
                }
            }
        }
        this.grille.setEspaceJeu(res.getEspaceJeu());
    }
}
