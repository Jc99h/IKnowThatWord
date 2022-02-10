package IKnowThatWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @author Camilo Ordoñez 1827625-2711 juan.ordonez.hurtado@correounivalle.edu.co
 * @version @version v.1.0.0 date:02/09/2021
 */
public class GUI extends JFrame {

	private static final String MENSAJE_INICIO = "Ayuda";
	private ModelIKnowThatWord modelIKnowThatWord;
	private Header headerProject;
	private JButton ayuda, salir, si, no;
	private JTextArea nombrePersona, nivel, mensajes;
	private JTextField palabras;
	private Escucha escucha;

	/**
	 * Constructor of GUI class
	 */
	public GUI(){
		initGUI();

		//Default JFrame configuration
		this.setTitle("I Know That Word");
		this.pack();
		this.setResizable(true);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method is used to set up the default JComponent Configuration,
	 * create Listener and control Objects used for the GUI class
	 */
	private void initGUI() {
		//Set up JFrame Container's Layout
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		//Create Listener Object and Control Object
		modelIKnowThatWord = new ModelIKnowThatWord();
		escucha = new Escucha();

		//Set up JComponents
		modelIKnowThatWord.setNombre(JOptionPane.showInputDialog("Cual es tu nombre?"));
		if(modelIKnowThatWord.validarUsuario()==true)
		{
			JOptionPane.showMessageDialog(null, "Usuario existente");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Usuario nuevo");
			modelIKnowThatWord.nuevoUsuario();
		}

		headerProject = new Header("Mesa de juego I Know That Word", Color.BLACK);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(headerProject, constraints);

		ayuda = new JButton("  ?  ");
		ayuda.addActionListener(escucha);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(ayuda, constraints);

		nombrePersona = new JTextArea(1, 15);
		nombrePersona.setBorder(BorderFactory.createTitledBorder("Nombre"));
		nombrePersona.setText(modelIKnowThatWord.getNombre());
		nombrePersona.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(nombrePersona, constraints);

		nivel = new JTextArea(1, 15);
		nivel.setBorder(BorderFactory.createTitledBorder("Nivel"));
		nivel.setText("0");
		nivel.setEditable(false);
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(nivel, constraints);

		salir = new JButton("Salir");
		salir.addActionListener(escucha);
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(salir, constraints);

		palabras = new JTextField(null, 35);
		palabras.setBorder(BorderFactory.createTitledBorder("Palabras"));
		palabras.setPreferredSize(new Dimension(200, 150));
		palabras.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		palabras.setText("PALABRAS");
		palabras.setEditable(false);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 4;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		palabras.setHorizontalAlignment(palabras.CENTER);
		this.add(palabras, constraints);

		si = new JButton("Si");
		//si.addActionListener(escucha);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(si, constraints);

		no = new JButton("No");
		//no.addActionListener(escucha);
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(no, constraints);

		mensajes = new JTextArea(5, 25);
		mensajes.setBorder(BorderFactory.createTitledBorder("Mensajes:"));
		mensajes.setText("El juego consiste en memorizar palabras\n"+"si necesitas mas ayuda presiona el boton '?'");
		mensajes.setEditable(false);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(mensajes, constraints);
	}

	/**
	 * Main process of the Java program
	 * @param args Object used in order to send input data from command line when
	 *             the program is execute by console.
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(() -> {
			GUI miProjectGUI = new GUI();
		});
	}

	/**
	 * inner class that extends an Adapter Class or implements Listeners used by GUI class
	 */
	private class Escucha implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent objectEvent) {
			if (objectEvent.getSource() == ayuda) {
				JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
			}
			if (objectEvent.getSource() == salir) {
				System.exit(0);
			}
			revalidate();
			repaint();
		}
	}
}