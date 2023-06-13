package com.example.Bash;

/**
 * Représente une cellule individuelle avec son état (vivant ou mort) et ses coordonnées dans la grille.
 */
public class Cellule {

    private int Vivant;
    public Cellule (int val) {
        this.Vivant = val;
    }

    public int getVivant() {
        return this.Vivant;
    }
    public Cellule setVivant() {
        this.Vivant = 1;
        return this;
    }
    public Cellule setMort() {
        this.Vivant = 0;
        return this;
    }

}
