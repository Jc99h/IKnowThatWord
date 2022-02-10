package IKnowThatWord;

/**
 * This class is used for ...
 * @author Camilo Ordoñez 1827625-2711 juan.ordonez.hurtado@correounivalle.edu.co
 * @version @version v.1.0.0 date:02/09/2021
 */

public class ModelIKnowThatWord
{
  private String nombre;
	private FileManager fileManager;

	public ModelIKnowThatWord()
	{
		fileManager = new FileManager();
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
}
