package IKnowThatWord;

import javax.swing.*;
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
		fileManager.escribirTexto(nombre);
	}

	public boolean validarUsuario()
	{
		for(int i=0; i<listaNombres.size(); i++)
		{
			if(Objects.equals(nombre, listaNombres.get(i)))
			{
				return true;
			}
		}
		return false;
	}
}
