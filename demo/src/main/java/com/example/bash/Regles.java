package com.example.bash;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Regles {

    //private List<Character> etat;
    private List<Integer> seuilNaissance;
    private List<Integer> seuilsSurvie;
    private List<int[]> voisinage;

    public Regles(List<int[]> voisinage, List<Integer> seuilNaissance, List<Integer> seuilsSurvie) {
        this.voisinage = voisinage;
      //  this.etat = etat;
        this.seuilNaissance = seuilNaissance;
        this.seuilsSurvie = seuilsSurvie;
    }
    public Regles(List<Integer> seuilNaissance, List<Integer> seuilsSurvie) {

    }
    public Regles() {
        this.voisinage = voisinageClassique();
        this.seuilNaissance = Arrays.asList(3);
        this.seuilsSurvie = Arrays.asList(2,3);
    }

    /**
     * Le voisinage classique pour un automate 2d de 8 voisin
     * @return un voisinage
     */
    protected static List<int[]> voisinageClassique() {
        return Arrays.asList(
                new int[] {-1, -1},
                new int[] {-1, 0},
                new int[] {-1, 1},
                new int[] {0, -1},
                new int[] {0, 1},
                new int[] {1, -1},
                new int[] {1, 0},
                new int[] {1, 1}
        );
    }


    public List<Character> getEtat() {
        return null;
    }

    public List<Integer> getSeuilNaissance() {
        return seuilNaissance;
    }

    public List<int[]> getVoisinage() {
        return voisinage;
    }

    public List<Integer> getSeuilsSurvie() {
        return seuilsSurvie;
    }

    public boolean doitSurvivre(int nbVoisin) {
        return seuilsSurvie.contains(nbVoisin);
    }

    public boolean doitNaitre(int nbVoisin) {
        return seuilNaissance.contains(nbVoisin);
    }
}
