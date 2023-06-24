package com.example.bash;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class FichierGrille {
    public List<String> lireFichier(String cheminFichier) throws IOException {
        return Files.readAllLines(Paths.get(cheminFichier));
    }

    public LinkedList<Integer> convertionFichier(List<String> lst) {
        LinkedList<Integer> result = new LinkedList<>();

        // Extraire les deux premiers éléments de la liste lst
        int ligne = Integer.parseInt(lst.get(0));
        int colonne = Integer.parseInt(lst.get(1));
        result.add(ligne);
        result.add(colonne);

        // Parcourir les lignes à partir de l'indice 2
        for (int i = 2; i < lst.size(); i++) {
            String ligneCourante = lst.get(i);
            for (char c : ligneCourante.toCharArray()) {
                int etatCellule = Character.getNumericValue(c);
                result.add(etatCellule);
            }
        }

        return result;
    }
}
