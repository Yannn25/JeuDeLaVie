package com.example.Bash;

/**
 *  Implémente les règles spécifiques du Jeu de la Vie
 *  en héritant de la classe abstraite AutomateCellulaire
 */
public class JeuDeLaVie extends AutomateCellulaire {

    private Grille grille;
   public JeuDeLaVie(Grille grille) {
       super(grille);
   }
}
