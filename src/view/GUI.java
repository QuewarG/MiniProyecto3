/*
 *  Archivo: GUI.java
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


import java.awt.EventQueue;

public class GUI {
    /**
     * Constructor que no recibe ningun atributo privado
     */
    public GUI() {
    }
    /**
     * Funcion principal que invoca la clase tableroprosicion
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableroPosicion();
            }
        });
    }
}
