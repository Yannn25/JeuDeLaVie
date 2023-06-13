package com.example.Bash;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class FichierGrille {
    public List<String> lireFichier(String cheminFichier) throws IOException {
        return Files.readAllLines(Paths.get(cheminFichier).toAbsolutePath());
    }

    public LinkedList<Integer> convertionFichier(List<String> lst) {
        LinkedList<Integer> result = new LinkedList<>();

        for (String ligne : lst) {
            for (char c : ligne.toCharArray()) {
                int etatCellule = Character.getNumericValue(c);
                result.add(etatCellule);
            }
        }

        return result;
    }
}
