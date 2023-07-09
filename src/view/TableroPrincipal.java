/*
 *  Archivo: TableroPrincipal.java
 *	Proyecto: MiniProyecto3
 *	Autor(es): Kevin Andrés Girón Villegas (2180450) -- Sarahy Gisselle Caicedo Betancourth (2180695)
 * -- Stiven Castro Sanchez(2177771) -- Miguel Angel Caicedo Mosquera (2177619)
 *	Email(s): Kevin.giron@correounivalle.edu.co, sarahy.caicedo@correounivalle.edu.co,
 * stiven.castro@correounivalle.edu.co, miguel.caicedo.mosquera@correounivalle.edu.co
 *	Fecha creaci�n: 2023-07-01
 *	Fecha �ltima modificaci�n: 2023-07-05
 *	Versi�n: 1.0
 */
package view;

import controller.Barco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TableroPrincipal extends JFrame {

    /**
     * Atributos de la clase TableroPrincipal
     */
    private JLabel title;


    private JButton exit;


    private Escucha escucha;


    private TableroPosicion battleShip;


    private Barco aBarco, bBarco;


    private JPanel panelBoard;


    public TableroPrincipal(TableroPosicion battleShip) {
        this.battleShip = battleShip;
        /**
         * Valores por defectos en la ventana, este es el metodo
         * constructor
         * Tablero posicion, recibe los dos paneles
         */
        initGUI();
        this.setUndecorated(true);
        this.setResizable(false);
        this.pack();
        this.setVisible(false);
        this.setLocationRelativeTo(null);

    }
    /**
     * Funcion con los valores iniciales de las caracteristicas de la ventana
     */

    private void initGUI() {

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 25);

        title = new JLabel("Tablero Maquina");
        title.setFont(font);
        title.setForeground(new Color(150, 10, 150));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        add(title,BorderLayout.NORTH);

        escucha = new Escucha();

        exit = new JButton("Salir");
        exit.addActionListener(escucha);
        add(exit,BorderLayout.SOUTH);

        panelBoard = new JPanel();
        panelBoard.setPreferredSize(new Dimension(40 * 12 * 2, 40 * 11));
        add(panelBoard, BorderLayout.CENTER);
    }

    /**
     * Funcion que permite remover del panel los barcos afectados
     * @param barco
     * @param nboard
     */
    public void setBoards(Barco barco, Barco nboard) {
        this.aBarco = barco;
        panelBoard.remove(aBarco);
        panelBoard.add(aBarco);
        this.bBarco = nboard;
        panelBoard.remove(bBarco);
        panelBoard.add(bBarco);
    }

    /**
     * Función capturar las opciones realizadas
     */
    private class Escucha implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent arg0) {

            battleShip.setEnabled(true);
            setVisible(false);
            battleShip.reset();

        }

    }
}