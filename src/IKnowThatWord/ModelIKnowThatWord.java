package IKnowThatWord;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is used for ...
 * @author Camilo Ordoñez 1827625-2711 juan.ordonez.hurtado@correounivalle.edu.co
 * @version @version v.1.0.0 date:02/09/2021
 */

public class ModelIKnowThatWord
{
  private String nombre;
	private int nivel;
	private ArrayList <String> listaNombres;
	private FileManager fileManager;

	public ModelIKnowThatWord()
	{
		fileManager = new FileManager();
		listaNombres = fileManager.lecturaFile();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void nuevoUsuario(){
		fileManager.escribirTexto(nombre+"0");
	}

	public boolean validarUsuario()
	{
		for(int i=0; i<listaNombres.size(); i++)
		{
			String valida = new String();
			for(int j=0; j<listaNombres.get(i).length()-1; j++)
			{
				valida += listaNombres.get(i).charAt(j);
			}
			if(Objects.equals(nombre, valida))
			{
				nivel = Character.getNumericValue(listaNombres.get(i).charAt(listaNombres.get(i).length()-1));
				return true;
			}
		}
		return false;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
}
