package IKnowThatWord;

import java.io.*;
import java.util.ArrayList;

/**
 * This class is used for ...
 *
 * @author Camilo Ordoñez 1827625-2711 juan.ordonez.hurtado@correounivalle.edu.co
 * @author Danny Andres Lopez 1941453-2711 danny.cardenas@gmail.edu.co
 * @version @version v.1.0.0 date:02/09/2021
 */

public class FileManager {
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;


    public ArrayList<String> lecturaFile(String cualArchivo) {
        ArrayList<String> nombres = new ArrayList<String>();
        ArrayList<String> palabras = new ArrayList<String>();

        try {
            fileReader = new FileReader("src/IKnowThatWord/files/" + cualArchivo + ".txt");
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null) {
                if (cualArchivo == "nombres") {
                    nombres.add(line);
                } else {
                    palabras.add(line);
                }

                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (cualArchivo == "nombres") {
            return nombres;
        }
        return palabras;
    }

    public void escribirTexto(String linea, String cualArchivo, boolean nuevoUsuario) {
        try {
            fileWriter = new FileWriter("src/IKnowThatWord/files/" + cualArchivo + ".txt", nuevoUsuario);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}