/*
 *  Archivo: TableroPosición.java
 *	Proyecto: MiniProyecto3
 *	Autor(es): Kevin Andrés Girón Villegas (2180450) -- Sarahy Gisselle Caicedo Betancourth (2180695)
 * -- Stiven Castro Sanchez(2177771) -- Miguel Angel Caicedo Mosquera (2177619)
 *	Email(s): Kevin.giron@correounivalle.edu.co, sarahy.caicedo@correounivalle.edu.co,
 * stiven.castro@correounivalle.edu.co, miguel.caicedo.mosquera@correounivalle.edu.co
 *	Fecha creacion: 2023-07-01
 *	Fecha ultima modificacion: 2023-07-05
 *	Version: 1.0
 */

package view;

import controller.Barco;
import model.Tablero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TableroPosicion extends JFrame{

    public static final String rutaFile = "src/resources/tablero.png";

    private static int boxSize = 40;

    private static int maxShips = 10;

    private static int gridSize = 11;

    private int turnMod = 0; // 0 organizar flota, 1 dispara usuario, 2 dispara maquina

    private Barco panelPlayer, panelMachine, panelAux, panelAux2;

    private JLabel title, titlePlayer, titleMachine, space;

    private JPanel panelButton, panelTitles;

    private JButton indications, exit, start;

    private TableroPrincipal boardOpponent = new TableroPrincipal(this);

    private JFrame myself = this;

    private Escucha escucha;


    public TableroPosicion() {

        initGUI();

        //defaul window configuation
        this.setUndecorated(true);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void initGUI() {

        panelTitles = new JPanel(new BorderLayout());
        panelTitles.setBackground(new Color(245, 245, 220));
        add(panelTitles, BorderLayout.NORTH);

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 35);

        title = new JLabel("Battle Ship");
        title.setFont(font);
        title.setForeground(new Color(150, 10, 150));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        panelTitles.add(title, BorderLayout.NORTH);

        titlePlayer = new JLabel("Tablero Jugador");

        titlePlayer.setFont(font);
        titlePlayer.setForeground(new Color(150, 10, 150));
        titlePlayer.setPreferredSize(new Dimension( boxSize * gridSize , boxSize));
        titlePlayer.setHorizontalAlignment(JLabel.CENTER);
        titlePlayer.setVerticalAlignment(JLabel.CENTER);

        panelTitles.add(titlePlayer, BorderLayout.WEST);

        titleMachine = new JLabel("Tablero Maquina");

        titleMachine.setFont(font);
        titleMachine.setForeground(new Color(150, 10, 150));
        titleMachine.setPreferredSize(new Dimension( boxSize * gridSize , boxSize));
        titleMachine.setHorizontalAlignment(JLabel.CENTER);
        titleMachine.setVerticalAlignment(JLabel.CENTER);

        panelTitles.add(titleMachine, BorderLayout.EAST);

        space = new JLabel();
        space.setPreferredSize(new Dimension(2 * boxSize, boxSize * gridSize));
        space.setBackground(new Color(245, 245, 220));
        space.setOpaque(true);
        add(space, BorderLayout.CENTER);

        // escucha

        escucha = new Escucha();

        // boards - playZone
        panelPlayer = new Barco(boxSize, maxShips, gridSize, escucha);
        panelMachine = new Barco(boxSize, maxShips, gridSize, escucha);
        panelAux = new Barco(boxSize, maxShips, gridSize, null);
        panelAux2 = new Barco(boxSize, maxShips, gridSize, null);

        for(int row = 1; row < gridSize; row++) {
            for(int col = 1; col < gridSize; col++) {
                panelAux.setBox(row, col, panelMachine.getBox(row, col));
            }
        }

        panelMachine.hideShips();
        add(panelMachine, BorderLayout.EAST);
        add(panelPlayer, BorderLayout.WEST);
        boardOpponent.setBoards(panelAux, panelAux2);

        // panelButton

        panelButton = new JPanel();
        panelButton.setBackground(new Color(245, 245, 220));
        start = new JButton("Iniciar");
        start.addActionListener(escucha);
        panelButton.add(start);
        indications = new JButton("Indicaciones");
        indications.addActionListener(escucha);
        panelButton.add(indications);
        exit = new JButton("Salir");
        exit.addActionListener(escucha);
        panelButton.add(exit);

        add(panelButton, BorderLayout.SOUTH);

    }


    public void reset() {
        turnMod = 0;
        panelPlayer.reset();
        panelMachine.reset();
        if(turnMod == 0) {
            for(int row = 1; row < gridSize; row++) {
                for(int col = 1; col < gridSize; col++) {
                    panelAux.setBox(row, col, panelMachine.getBox(row, col));
                }
            }
        }
        panelMachine.hideShips();
    }

    private class Escucha extends MouseAdapter implements ActionListener{

        Tablero startTablero = null;
        Tablero finalTablero = null;

        @Override
        public void actionPerformed(ActionEvent evenAction) {

            if(evenAction.getSource() == exit) {
                System.exit(0);
            }
            if(evenAction.getSource() == indications) {

                ImageIcon image = new ImageIcon("src/pictures/instrucciones.png");
                String text = " -Organiza tu flota \n - Presiona iniciar para comenzar a jugar \n - Dispara a la flota enemiga, si aciertas puedes volver a disparar si no el enemigo te disparar� \n "
                        + "- Ganas cuando derribas todos sus barcos";
                JOptionPane.showMessageDialog(null, text, "Indicaciones", JOptionPane.DEFAULT_OPTION, image);
            }
            if(evenAction.getSource() == start) {
                if(turnMod == 0) {
                    Random random = new Random();
                    turnMod = random.nextInt(2) + 1;
                    if(turnMod == 1) {
                        JOptionPane.showMessageDialog(null, "Comienzas tu primero");
                    }
                    if(turnMod == 2) {
                        JOptionPane.showMessageDialog(null, "La maquina comienza primero");
                    }
                    while(turnMod == 2) {
                        turnMod = panelPlayer.clickedShoot(panelPlayer.randomBox(), turnMod);
                    }
                }
            }
        }


        public void mouseClicked(MouseEvent eventMouse) {

            Tablero tableroClick = (Tablero)eventMouse.getSource();

            if(turnMod == 0) {
                if(startTablero != null) {
                    finalTablero = tableroClick;
                    panelPlayer.moveShip(startTablero, finalTablero);
                    finalTablero = null;
                    startTablero.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }

                if(startTablero == null && tableroClick.isHead()) {
                    startTablero = tableroClick;
                    startTablero.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                else {
                    startTablero = null;
                }
            }
            if(turnMod == 1) {
                turnMod = panelMachine.clickedShoot(tableroClick, turnMod);
            }
            while(turnMod == 2) {
                turnMod = panelPlayer.clickedShoot(panelPlayer.randomBox(), turnMod);
            }

            panelMachine.killShips();
            panelPlayer.killShips();

            if(panelMachine.winGame()) {
                int option = JOptionPane.showConfirmDialog(null, "Ganaste!! �Juegas otra vez?", "Ganaste", JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION) {
                    int aOption = JOptionPane.showConfirmDialog(null, "�Quieres ver el tablero del oponente anterior?", "Jugar", JOptionPane.YES_NO_OPTION);
                    if(aOption == JOptionPane.YES_OPTION) {
                        for(int row = 1; row < gridSize; row++) {
                            for(int col = 1; col < gridSize; col++) {
                                panelAux2.setBox(row, col, panelMachine.getBox(row, col));
                            }
                        }
                        boardOpponent.setVisible(true);
                        myself.setEnabled(false);
                    }
                    else {
                        if(aOption == JOptionPane.NO_OPTION) {
                            reset();
                        }
                    }
                }
                else {
                    if(option == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                }
            }
            if(panelPlayer.winGame()) {
                int option = JOptionPane.showConfirmDialog(null, "Perdistes!! �Juegas otra vez?", "Perdiste", JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION) {
                    int aOption = JOptionPane.showConfirmDialog(null, "�Quieres ver el tablero del oponente anterior?", "Jugar", JOptionPane.YES_NO_OPTION);
                    if(aOption == JOptionPane.YES_OPTION) {
                        for(int row = 1; row < gridSize; row++) {
                            for(int col = 1; col < gridSize; col++) {
                                panelAux2.setBox(row, col, panelMachine.getBox(row, col));
                            }
                        }
                        boardOpponent.setVisible(true);
                        myself.setEnabled(false);
                    }
                    else {
                        if(aOption == JOptionPane.NO_OPTION) {
                            reset();
                        }
                    }
                }
                else {
                    if(option == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                }
            }
        }
    }
}
