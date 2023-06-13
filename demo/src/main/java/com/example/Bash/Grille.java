package com.example.Bash;

import java.util.LinkedList;

/**
 * : Représente la grille qui contient les cellules et gère leur mise à jour
 */
public class Grille {

    private int ligne, colone;
    private Cellule[][] espaceJeu;

    public Grille(int l, int c) {
        this.ligne = l;
        this.colone = c;
        this.espaceJeu = new Cellule[l][c];
    }

    public int getLigne() {
        return ligne;
    }
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }
    public int getColone() {
        return colone;
    }
    public void setColone(int colone) {
        this.colone = colone;
    }
    public Cellule[][] getEspaceJeu() {
        return this.espaceJeu;
    }
    public void setEspaceJeu(Cellule[][] grille) {
        this.espaceJeu = grille;
    }

    public int getCellulePos(int x, int y) {
        return this.espaceJeu[x][y].getVivant();
    }

    public static Grille init(LinkedList<Integer> l) {
        int col = l.pop(), ligne = l.pop();
        Grille grid = new Grille(ligne,col);
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < col; j++) {
               grid.espaceJeu[i][j] = new Cellule(l.pop());
            }
        }
        return grid;
    }
}
