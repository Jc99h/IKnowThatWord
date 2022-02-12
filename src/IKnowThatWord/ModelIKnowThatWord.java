package IKnowThatWord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * This class is used for ...
 *
 * @author Camilo Ordoñez 1827625-2711 juan.ordonez.hurtado@correounivalle.edu.co
 * @version @version v.1.0.0 date:02/09/2021
 */

public class ModelIKnowThatWord {
    public class Nivel {
        public int nivel, numPalabrasParaRecordar, numPalabrasDelNivel, porcentajeAciertos;

        public Nivel(int nivel) {
            this.nivel = nivel;
        }
    }

    public ArrayList<String> palabrasTemporales = new ArrayList<String>(Arrays.asList(
            "hola",
            "como",
            "estas",
            "yo",
            "bien",
            "humanidad",
            "humano",
            "persona",
            "gente",
            "rodilla",
            "cabeza",
            "ojo",
            "campo",
            "naturaleza",
            "perro",
            "gato",
            "rio",
            "playa",
            "verdura",
            "tomate",
            "sandia"));

    private String nombre;
    private int nivelNum;
    public ArrayList<String> listaNombres, palabrasParaRecordar, palabrasDelNivel;
    private Random random;
    private FileManager fileManager;

    public ArrayList<Nivel> niveles;

    public ModelIKnowThatWord() {
        fileManager = new FileManager();
        listaNombres = fileManager.lecturaFile();
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
        palabrasParaRecordar = new ArrayList<String>();
        palabrasDelNivel = new ArrayList<String>();
        Nivel nivelActual = niveles.get(nivelNum);

        //setea las palabras del nivel de un arraylist (por ahora palabras temporales)
        for (int i = 0; i < nivelActual.numPalabrasDelNivel; i++) {
            palabrasDelNivel.add(palabrasTemporales.get(random.nextInt(0, palabrasTemporales.size() - 1)));
        }

        //de las palabras del nivel que saca antes sacar las palabras a recordar
        for (int i = 0; i < nivelActual.numPalabrasParaRecordar; i++) {
            palabrasParaRecordar.add(palabrasDelNivel.get(random.nextInt(0, palabrasDelNivel.size() - 1)));
        }
    }

    public void setValoresDeNiveles() {
        niveles = new ArrayList<Nivel>();

        for (int i = 0; i < 10; i++) {
            Nivel nivel = new Nivel(i);

            switch (i) {

                case 0 -> {
                    nivel.numPalabrasParaRecordar = 10;
                    nivel.porcentajeAciertos = 70;
                }
                case 1 -> {
                    nivel.numPalabrasParaRecordar = 20;
                    nivel.porcentajeAciertos = 70;
                }
                case 2 -> {
                    nivel.numPalabrasParaRecordar = 25;
                    nivel.porcentajeAciertos = 75;
                }
                case 3 -> {
                    nivel.numPalabrasParaRecordar = 30;
                    nivel.porcentajeAciertos = 80;
                }
                case 4 -> {
                    nivel.numPalabrasParaRecordar = 35;
                    nivel.porcentajeAciertos = 80;
                }
                case 5 -> {
                    nivel.numPalabrasParaRecordar = 40;
                    nivel.porcentajeAciertos = 85;
                }
                case 6 -> {
                    nivel.numPalabrasParaRecordar = 50;
                    nivel.porcentajeAciertos = 90;
                }
                case 7 -> {
                    nivel.numPalabrasParaRecordar = 60;
                    nivel.porcentajeAciertos = 90;
                }
                case 8 -> {
                    nivel.numPalabrasParaRecordar = 70;
                    nivel.porcentajeAciertos = 95;
                }
                case 9 -> {
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
