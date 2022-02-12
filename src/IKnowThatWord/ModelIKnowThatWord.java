package IKnowThatWord;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is used for ...
 *
 * @author Camilo Ordoñez 1827625-2711 juan.ordonez.hurtado@correounivalle.edu.co
 * @version @version v.1.0.0 date:02/09/2021
 */

public class ModelIKnowThatWord {
    public class Nivel {
        public int nivel, palabrasParaRecordar, palabrasDelNivel, porcentajeAciertos;

        public Nivel(int nivel) {
            this.nivel = nivel;
        }
    }

    private String nombre;
    private int nivelNum;
    private ArrayList<String> listaNombres;
    private FileManager fileManager;

    public ArrayList<Nivel> niveles;

    public ModelIKnowThatWord() {
        fileManager = new FileManager();
        listaNombres = fileManager.lecturaFile();

        setValoresDeNiveles();

    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void nuevoUsuario() {
        fileManager.escribirTexto(nombre + "0");
    }

    public boolean validarUsuario() {
        for (int i = 0; i < listaNombres.size(); i++) {
            String valida = new String();
            for (int j = 0; j < listaNombres.get(i).length() - 1; j++) {
                valida += listaNombres.get(i).charAt(j);
            }
            if (Objects.equals(nombre, valida)) {
                nivelNum = Character.getNumericValue(listaNombres.get(i).charAt(listaNombres.get(i).length() - 1));
                return true;
            }
        }
        return false;
    }

    public int getNivelNum() {

        return nivelNum;
    }

    public void setNivelNum(int nivelNum) {

        this.nivelNum = nivelNum;
    }

    public void initNivel() {

    }

    public void setValoresDeNiveles() {
        for (int i = 0; i < 10; i++) {
            Nivel nivel = new Nivel(i);

            switch (i) {

                case 0 -> {
                    nivel.palabrasParaRecordar = 10;
                    nivel.porcentajeAciertos = 70;
                }
                case 1 -> {
                    nivel.palabrasParaRecordar = 20;
                    nivel.porcentajeAciertos = 70;
                }
                case 2 -> {
                    nivel.palabrasParaRecordar = 25;
                    nivel.porcentajeAciertos = 75;
                }
                case 3 -> {
                    nivel.palabrasParaRecordar = 30;
                    nivel.porcentajeAciertos = 80;
                }
                case 4 -> {
                    nivel.palabrasParaRecordar = 35;
                    nivel.porcentajeAciertos = 80;
                }
                case 5 -> {
                    nivel.palabrasParaRecordar = 40;
                    nivel.porcentajeAciertos = 85;
                }
                case 6 -> {
                    nivel.palabrasParaRecordar = 50;
                    nivel.porcentajeAciertos = 90;
                }
                case 7 -> {
                    nivel.palabrasParaRecordar = 60;
                    nivel.porcentajeAciertos = 90;
                }
                case 8 -> {
                    nivel.palabrasParaRecordar = 70;
                    nivel.porcentajeAciertos = 95;
                }
                case 9 -> {
                    nivel.palabrasParaRecordar = 100;
                    nivel.porcentajeAciertos = 100;
                }
                default -> {

                }
            }

            nivel.palabrasDelNivel = nivel.palabrasParaRecordar * 2;
            niveles.add(nivel);
        }
    }
}
