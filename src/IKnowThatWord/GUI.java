package IKnowThatWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is used for ...
 *
 * @author Camilo Ordoñez 1827625-2711 juan.ordonez.hurtado@correounivalle.edu.co
 * @author Danny Andres Lopez 1941453-2711 danny.cardenas@gmail.edu.co
 * @version @version v.1.0.0 date:02/09/2021
 */
public class GUI extends JFrame {

    private static final String MENSAJE_INICIO = "Se te presentaran una secuencia de palabras de una en una, aparecera una palabra durante \n" +
            "5 segundos en pantalla, luego desaparece y aparece la siguiente, debes memorizar las \n" +
            "palabras que van apareciendo. Tras la serie de palabras a memorizar, se te presentara \n" +
            "un listado con el doble de palabras que se mostraron y por cada palabra debes indicar \n" +
            "si la palabras estaba o no contenida en el listado a memorizar y tendras 7 segundos\n" +
            "para responder, en caso de no hacerlo se tomara como un error";
    private ModelIKnowThatWord modelIKnowThatWord;
    private Header headerProject;
    private JButton ayuda, salir, botonSi, botonNo;
    private JTextArea nombrePersona, nivel, mensajes;
    private JTextField palabras;
    private Escucha escucha;
    public boolean mostrarPalabra, respondido, respuesta, enJuego, noRespondido, validandoPuntaje;

    /**
     * Constructor of GUI class
     */
    public GUI() {
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
        String nombreEntrada = JOptionPane.showInputDialog("Cual es tu nombre?");
        if (nombreEntrada == null || "".equals(nombreEntrada)) {
            System.exit(0);
        }
        modelIKnowThatWord.setNombre(nombreEntrada);
        if (modelIKnowThatWord.validarUsuario() == true) {
            if (modelIKnowThatWord.getNivelNum() != 0) {
                JOptionPane.showMessageDialog(null, "El usuario " + modelIKnowThatWord.getNombre() + " ya existe\n" + "y el ultimo nivel aprobado fue el " + modelIKnowThatWord.getNivelNum());
            } else {
                JOptionPane.showMessageDialog(null, "El usuario " + modelIKnowThatWord.getNombre() + " ya existe\n" + "y no ha superado el primer nivel");
            }
        } else {
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
        nivel.setText(modelIKnowThatWord.getNivelNum() + 1 + "");
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

        botonSi = new JButton("Si");
        botonSi.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(botonSi, constraints);

        botonNo = new JButton("No");
        botonNo.addActionListener(escucha);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(botonNo, constraints);

        mensajes = new JTextArea(5, 25);
        mensajes.setBorder(BorderFactory.createTitledBorder("Mensajes:"));
        mensajes.setText("El juego consiste en memorizar palabras\n" + "si necesitas mas ayuda presiona el boton '?'");
        mensajes.setEditable(false);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(mensajes, constraints);

        modelIKnowThatWord.initNivel();
        mostrarPalabrasParaRecordar();
    }

    /**
     * Muestra en pantalla las palabras a recordar
     */
    public void mostrarPalabrasParaRecordar() {
        modelIKnowThatWord.palabrasCorrectas = 0;

        int fps = 1000;
        java.util.Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int miliseconds = fps;
            int contador = 3;
            int numPalabra = 0;

            @Override
            public void run() {
                if (miliseconds <= 4000) {
                    palabras.setText(contador + "");
                    contador--;

                } else if (miliseconds == 5000 + (fps * 2 * (numPalabra))) {
                    if (numPalabra >= modelIKnowThatWord.palabrasParaRecordar.size()) {
                        enJuego = false;
                        mostrarTodasLasPalabras();
                        timer.cancel();
                        return;
                    }

                    palabras.setText(modelIKnowThatWord.palabrasParaRecordar.get(numPalabra).toUpperCase(Locale.ROOT));
                    mensajes.setText(numPalabra + 1 + "");
                    revalidate();
                    repaint();
                    numPalabra++;
                }
                System.out.println(miliseconds);
                miliseconds += fps;
            }

        }, 0, fps);
    }

    /**
     * Muestra en pantalla todas las palabras del nivel
     */
    public void mostrarTodasLasPalabras() {
        System.out.flush();
        int fps = 1000;
        java.util.Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int miliseconds = fps;
            int numPalabra = 0;

            @Override
            public void run() {
                if (miliseconds <= 3000 && !enJuego) {
                    palabras.setText("comienza el juego".toUpperCase(Locale.ROOT));
                } else if (!enJuego) {
                    enJuego = true;
                    mostrarPalabra = true;
                    miliseconds = 0;
                }

                //muestra la palabra si aun queda en la lista
                if (mostrarPalabra && enJuego) {
                    if (numPalabra >= modelIKnowThatWord.palabrasDelNivel.size()) {
                        enJuego = false;
                        palabras.setBackground(Color.WHITE);
                        JOptionPane.showMessageDialog(null, "Tu porcentaje fue: " + modelIKnowThatWord.getMostrarPorcentajeAciertos());
                        if (modelIKnowThatWord.nuevoNivel()) {
                            modelIKnowThatWord.initNivel();
                            mostrarPalabrasParaRecordar();
                        }
                        timer.cancel();
                        return;
                    }
                    palabras.setBackground(Color.WHITE);
                    palabras.setText(modelIKnowThatWord.palabrasDelNivel.get(numPalabra).toUpperCase(Locale.ROOT));
                    mensajes.setText(numPalabra + 1 + "\nPalabras correctas: " + modelIKnowThatWord.palabrasCorrectas);
                    mostrarPalabra = false;
                    respondido = false;
                    miliseconds = 0;
                    revalidate();
                    repaint();
                }

                //valida si ya paso el tiempo para responder
                if (miliseconds >= 7000 && !respondido && enJuego) {
                    noRespondido = true;
                    validandoPuntaje = true;
                    miliseconds = 0;
                }

                //valida la respuesta y da paso a la siguiente palabra
                if (noRespondido || (respondido && enJuego)) {
                    //aqui es donde se evalua si el jugador está en lo correcto o no
                    if (validandoPuntaje) {
                        if (noRespondido) {
                            mensajes.setText("no ha respondido");
                            palabras.setBackground(Color.RED);
                        } else {
                            boolean correcto = modelIKnowThatWord.validarEleccion(numPalabra, respuesta);
                            if (correcto) {
                                mensajes.setText("correcto");
                                palabras.setBackground(Color.GREEN);
                            } else {
                                mensajes.setText("equivocado");
                                palabras.setBackground(Color.RED);
                            }
                        }
                        validandoPuntaje = false;
                    }

                    if (miliseconds >= 3000) {
                        numPalabra++;
                        respondido = false;
                        noRespondido = false;
                        mostrarPalabra = true;
                    }
                }

                System.out.println(miliseconds);
                miliseconds += fps;
            }

        }, 0, fps);
    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent objectEvent) {
            if (objectEvent.getSource() == ayuda) {
                JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
            }
            if (objectEvent.getSource() == salir) {
                System.exit(0);
            }

            if (!respondido) {
                if (objectEvent.getSource() == botonSi) {
                    validandoPuntaje = true;
                    respondido = true;
                    respuesta = true;
                } else if (objectEvent.getSource() == botonNo) {
                    validandoPuntaje = true;
                    respondido = true;
                    respuesta = false;
                }
            }

            revalidate();
            repaint();
        }
    }
}