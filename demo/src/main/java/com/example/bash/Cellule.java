package com.example.bash;

/**
 * Représente une cellule individuelle avec son état (vivant ou mort).
 */
public class Cellule {

    private char phase;
    private int etat;
    public Cellule (int val) {
        this.etat = val;
    }

    public Cellule getCellule() {
        return this;
    }
    public int getEtat() {
        return this.etat;
    }
    public void setEtat(int val) {
        this.etat = val;
    }

    public boolean estVivant() {
        return this.etat == 1;
    }
    public Cellule setVivant() {
        this.etat = 1;
        return this;
    }
    public Cellule setMort() {
        this.etat = 0;
        return this;
    }

}
