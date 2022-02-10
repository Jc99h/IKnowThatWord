package IKnowThatWord;

import java.io.*;
import java.util.ArrayList;

/**
 * This class is used for ...
 * @author Camilo Ordoñez 1827625-2711 juan.ordonez.hurtado@correounivalle.edu.co
 * @version @version v.1.0.0 date:02/09/2021
 */

public class FileManager {
	private FileReader fileReader;
	private BufferedReader input;
	private FileWriter fileWriter;
	private BufferedWriter output;


	public ArrayList <String> lecturaFile()
	{
		ArrayList <String> frases = new ArrayList <String>();

		try
		{
			fileReader = new FileReader("src/IKnowThatWord/files/nombres.txt");
			input = new BufferedReader(fileReader);
			String line = input.readLine();
			while(line!=null){
				frases.add(line);
				line=input.readLine();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				input.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return frases;
	}

	public void escribirTexto(String linea)
	{
		try
		{
			fileWriter = new FileWriter("src/IKnowThatWord/files/nombres.txt",true);
			output = new BufferedWriter(fileWriter);
			output.write(linea);
			output.newLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				output.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}