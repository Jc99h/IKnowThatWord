package IKnowThatWord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * This class is used for ...
 *
 * @author Camilo Ordoñez 1827625-2711 juan.ordonez.hurtado@correounivalle.edu.co
 * @author Danny Andres Lopez 1941453-2711 danny.cardenas@gmail.edu.co
 * @version @version v.1.0.0 date:02/09/2021
 */

public class ModelIKnowThatWord {
    public class Nivel {
        public int nivel, numPalabrasParaRecordar, numPalabrasDelNivel, porcentajeAciertos;

        public Nivel(int nivel) {
            this.nivel = nivel;
        }
    }

    private String nombre;
    private int nivelNum;
    public int palabrasCorrectas;
    public ArrayList<String> listaNombres, listaPalabras, palabrasParaRecordar, palabrasDelNivel;
    private Random random;
    private FileManager fileManager;

    public ArrayList<Nivel> niveles;

    public ModelIKnowThatWord() {
        fileManager = new FileManager();
        listaNombres = fileManager.lecturaFile("nombres");
        listaPalabras = fileManager.lecturaFile("palabras");
        random = new Random();

        setValoresDeNiveles();

    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void nuevoUsuario() {
        fileManager.escribirTexto(nombre + "0", "nombres");
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

    public boolean validarEleccion(int index, boolean respuesta) {
        for (int i = 0; i < palabrasParaRecordar.size(); i++) {
            if (palabrasDelNivel.get(index) == palabrasParaRecordar.get(i)) {
                palabrasCorrectas++;
                return respuesta;
            }
        }
        return !respuesta;
    }

    public void initNivel() {
        listaPalabras = fileManager.lecturaFile("palabras");

        palabrasParaRecordar = new ArrayList<String>();
        palabrasDelNivel = new ArrayList<String>();
        Nivel nivelActual = niveles.get(nivelNum);

        //setea las palabras del nivel de un arraylist
        for (int i = 0; i < nivelActual.numPalabrasDelNivel; i++) {
            int numeroRandom = random.nextInt(0, listaPalabras.size() - 1);
            palabrasDelNivel.add(listaPalabras.get(numeroRandom));
            listaPalabras.remove(numeroRandom);
        }

        //copiando el anterior array
        ArrayList<String> palabrasDelNivelTemporal = new ArrayList<String>();
        for (int i = 0; i < palabrasDelNivel.size(); i++) {
            palabrasDelNivelTemporal.add(palabrasDelNivel.get(i));
        }

        //de las palabras del nivel que saca antes sacar las palabras a recordar
        for (int i = 0; i < nivelActual.numPalabrasParaRecordar; i++) {
            int numeroRandom = random.nextInt(0, palabrasDelNivelTemporal.size() - 1);
            palabrasParaRecordar.add(palabrasDelNivelTemporal.get(numeroRandom));
            palabrasDelNivelTemporal.remove(numeroRandom);
        }
    }

    public void setValoresDeNiveles() {
        niveles = new ArrayList<Nivel>();

        for (int i = 1; i < 11; i++) {
            Nivel nivel = new Nivel(i);

            switch (i) {

                case 1 -> {
                    nivel.numPalabrasParaRecordar = 10;
                    nivel.porcentajeAciertos = 70;
                }
                case 2 -> {
                    nivel.numPalabrasParaRecordar = 20;
                    nivel.porcentajeAciertos = 70;
                }
                case 3 -> {
                    nivel.numPalabrasParaRecordar = 25;
                    nivel.porcentajeAciertos = 75;
                }
                case 4 -> {
                    nivel.numPalabrasParaRecordar = 30;
                    nivel.porcentajeAciertos = 80;
                }
                case 5 -> {
                    nivel.numPalabrasParaRecordar = 35;
                    nivel.porcentajeAciertos = 80;
                }
                case 6 -> {
                    nivel.numPalabrasParaRecordar = 40;
                    nivel.porcentajeAciertos = 85;
                }
                case 7 -> {
                    nivel.numPalabrasParaRecordar = 50;
                    nivel.porcentajeAciertos = 90;
                }
                case 8 -> {
                    nivel.numPalabrasParaRecordar = 60;
                    nivel.porcentajeAciertos = 90;
                }
                case 9 -> {
                    nivel.numPalabrasParaRecordar = 70;
                    nivel.porcentajeAciertos = 95;
                }
                case 10 -> {
                    nivel.numPalabrasParaRecordar = 100;
                    nivel.porcentajeAciertos = 100;
                }
                default -> {

                }
            }

            nivel.numPalabrasDelNivel = nivel.numPalabrasParaRecordar * 2;
            niveles.add(nivel);
        }
    }
}
