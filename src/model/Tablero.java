/*
 *  Archivo: Tablero.java
 *	Proyecto: MiniProyecto3
 *	Autor(es): Kevin Andrés Girón Villegas (2180450) -- Sarahy Gisselle Caicedo Betancourth (2180695)
 * -- Stiven Castro Sanchez(2177771) -- Miguel Angel Caicedo Mosquera (2177619)
 *	Email(s): Kevin.giron@correounivalle.edu.co, sarahy.caicedo@correounivalle.edu.co,
 * stiven.castro@correounivalle.edu.co, miguel.caicedo.mosquera@correounivalle.edu.co
 *	Fecha creación: 2023-07-01
 *	Fecha última modificación: 2023-07-05
 *	Versión: 1.0
 */


package model;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tablero extends JButton {
    /**
    * Atributo de la clase tablero
    */
    private static int boxSize = 0;

    private int kindBox;

    private int row, col, orientation ;

    private ImageIcon image;

    private boolean head;

    /**
     * Contructor de la clase tablero el cual recibe cuatro parametros
     */
    public Tablero(ImageIcon image, int row, int col, int kind) {

        this.row = row;
        this.col = col;
        this.kindBox = kind;
        this.head = false;
        this.orientation = -1;
        setImage(image);


        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(boxSize, boxSize));
        //this.setBorder(null);
        this.setFocusPainted(false);
    }

    /**
     * Funcion que permite colocar la imagen correspondiente a la acción.
     */

    public void setImage(ImageIcon image) {
        this.image = image;
        setIcon(image);
    }

    /**
     * Funcion que permite cambiar el estado del atributo privado Head
     */

    public void setHead(boolean isHead) {
        this.head = isHead;

    }

    /**
     * Funcion que permite validar de acuerdo al parametro que recibe si la orientacion cumple con una
     * de las dos condiciones y cambia el valor del atributo privado orientation
     */

    public void setOrientation(int orientation) {
        if( -2 < orientation && orientation < 2) {
            this.orientation = orientation;
        }

    }

    /**
     * Funcion que permite validar de acuerdo al parametro que recibe si el tipo cumple con una
     * de las dos condiciones y cambia el valor del atributo privado kindBox
     */

    public void setKind(int kind) {
        if(kind > -5 && kind < 7) {
            this.kindBox = kind;
        }
    }

    /**
     * Funcion que permite asignar el nuevo valor al atributo boxSize
     */

    public static void setBoxSize (int size) {
        boxSize = size;
    }

    /**
     * Funcion que permite validar el valor del parametro kindBox a través de dos condiciones
     * sino cumple con ninguna de las dos retornara false.
     */

    public boolean shoot() {
        if(kindBox == 0) {
            kindBox = 5;
            return true;
        }
        if(kindBox > 0 && kindBox < 5 ) {
            kindBox = (-1)*kindBox;
            return true;
        }
        return false;
    }

    /**
     * Funcion que permite asignar y colocar nuevos valores sobre el tablero, de acuerdo
     * a las funciones que invica.
     */

    public void moveBox(Tablero aTablero) {
        ImageIcon AImage = aTablero.getImage();
        boolean aHead = aTablero.isHead();
        int aKind = aTablero.getKindBox();
        int aOrientation = aTablero.getOrientation();
        aTablero.setImage(image);
        aTablero.setHead(head);
        aTablero.setKind(kindBox);
        aTablero.setOrientation(orientation);
        this.setImage(AImage);
        this.setHead(aHead);
        this.setKind(aKind);
        this.setOrientation(aOrientation);
    }

    /**
     * Funcion que permite obtener valores de los atributos
     */


    public int getKindBox() {
        return kindBox;
    }


    public int getRow() {
        return row;
    }


    public int getCol() {
        return col;
    }


    public int getOrientation() {
        return orientation;
    }


    public ImageIcon getImage() {
        return image;
    }


    public boolean isHead() {
        return head;
    }


}