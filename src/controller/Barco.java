/*
 *  Archivo: Barco.java
 *	Proyecto: MiniProyecto3
 *	Autor(es): Kevin Andrés Girón Villegas (2180450) -- Sarahy Gisselle Caicedo Betancourth (2180695)
 * -- Stiven Castro Sanchez(2177771) -- Miguel Angel Caicedo Mosquera (2177619)
 *	Email(s): Kevin.giron@correounivalle.edu.co, sarahy.caicedo@correounivalle.edu.co,
 * stiven.castro@correounivalle.edu.co, miguel.caicedo.mosquera@correounivalle.edu.co
 *	Fecha creaci�n: 2023-07-01
 *	Fecha �ltima modificaci�n: 2023-07-05
 *	Versi�n: 1.0
 */
package controller;

import model.Tablero;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




public class Barco extends JPanel {

    public static final String rutaFile = "src/resources/tablero.png";

    private int boxSize;


    private int maxShips;


    private int gridSize;

    private Tablero[][] aBoard;


    private BufferedImage bufferImage = null;

    private MouseListener listener;


    public Barco(int boxSize, int maxShips, int gridSize, MouseListener listener) {

        try {
            bufferImage = ImageIO.read(new File(rutaFile));

            this.boxSize = boxSize;
            this.maxShips = maxShips;
            this.gridSize = gridSize;
            this.listener = listener;
            aBoard = new Tablero[gridSize][gridSize];
            Tablero.setBoxSize(boxSize);

            setLayout(new GridLayout(gridSize,gridSize));

            splitImage(listener);
            spawnShips();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo tablero.png");
        }
    }

    private void splitImage(MouseListener listener) {
        for(int row = 0; row < gridSize; row++) {
            for(int col = 0; col < gridSize; col++) {
                int x = col * boxSize;
                int y = row * boxSize;
                BufferedImage subImage = bufferImage.getSubimage(x, y, boxSize, boxSize);
                ImageIcon buttonImage = new ImageIcon(subImage);
                aBoard[row][col] = new Tablero(buttonImage, row, col, 0);
                if(row == 0 || col == 0) {
                }
                else {
                    aBoard[row][col].addMouseListener(listener);
                }
                add(aBoard[row][col]);
            }
        }
    }

    private void spawnShips() {
        Random random = new Random();
        int row, col, orientation;
        boolean aux;
        int[] ships = new int[maxShips];
        for (int i = 0; i < 4; i++) {
            if(i < 1) {
                ships[i] = 4;
            }
            if (i < 2) {
                ships[1 + i] = 3;
            }
            if (i < 3) {
                ships[3 + i] = 2;
            }
            ships[6 + i] = 1;
        }

        for(int i = 0; i < maxShips; i++) {
            orientation = random.nextInt(2);
            if(orientation == 0) {
                aux = true;
                switch(ships[i]) {
                    case 1:
                        while(aux) {
                            row = random.nextInt(gridSize - 1) + 1;
                            col = random.nextInt(gridSize - 1) + 1;
                            if(aBoard[row][col].getKindBox() == 0) {
                                aBoard[row][col].setHead(true);
                                aBoard[row][col].setOrientation(orientation);
                                aBoard[row][col].setKind(ships[i]);
                                aux = false;
                                ImageIcon image = new ImageIcon("src/resources/fragataH.png");
                                aBoard[row][col].setImage(image);

                            }
                        }
                        break;
                    case 2:
                        while(aux) {
                            row = random.nextInt(gridSize - 1) + 1;
                            col = random.nextInt(gridSize - 2) + 1;
                            if(aBoard[row][col].getKindBox() == 0 && aBoard[row][col + 1].getKindBox() == 0) {
                                aBoard[row][col].setHead(true);
                                aBoard[row][col].setOrientation(orientation);
                                aBoard[row][col].setKind(ships[i]);
                                aBoard[row][col + 1].setKind(ships[i]);
                                aux = false;
                                try {
                                    bufferImage = ImageIO.read(new File("src/resources/destructorH.png"));
                                    BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                                    ImageIcon image = new ImageIcon(aImage);
                                    aBoard[row][col].setImage(image);
                                    aImage = bufferImage.getSubimage(boxSize, 0, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row][col + 1].setImage(image);
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo destructorH.png");
                                }
                            }
                        }
                        break;
                    case 3:
                        while(aux) {
                            row = random.nextInt(gridSize - 1) + 1;
                            col = random.nextInt(gridSize - 3) + 1;
                            if (aBoard[row][col].getKindBox() == 0 && aBoard[row][col + 1].getKindBox() == 0 && aBoard[row][col + 2].getKindBox() == 0) {
                                aBoard[row][col].setHead(true);
                                aBoard[row][col].setOrientation(orientation);
                                aBoard[row][col].setKind(ships[i]);
                                aBoard[row][col + 1].setKind(ships[i]);
                                aBoard[row][col + 2].setKind(ships[i]);
                                aux = false;
                                try {
                                    bufferImage = ImageIO.read(new File("src/resources/submarinoH.png"));
                                    BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                                    ImageIcon image = new ImageIcon(aImage);
                                    aBoard[row][col].setImage(image);
                                    aImage = bufferImage.getSubimage(boxSize, 0, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row][col + 1].setImage(image);
                                    aImage = bufferImage.getSubimage(2 * boxSize, 0, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row][col + 2].setImage(image);
                                } catch (IOException e) {

                                    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo submarinoH.png");
                                }
                            }
                        }
                        break;
                    case 4:
                        while(aux) {
                            row = random.nextInt(gridSize - 1) + 1;
                            col = random.nextInt(gridSize - 4) + 1;
                            if(aBoard[row][col].getKindBox() == 0 && aBoard[row][col + 1].getKindBox() == 0 && aBoard[row][col + 2].getKindBox() == 0 && aBoard[row][col + 3].getKindBox() == 0) {
                                aBoard[row][col].setHead(true);
                                aBoard[row][col].setOrientation(orientation);
                                aBoard[row][col].setKind(ships[i]);
                                aBoard[row][col + 1].setKind(ships[i]);
                                aBoard[row][col + 2].setKind(ships[i]);
                                aBoard[row][col + 3].setKind(ships[i]);
                                aux = false;
                                try {
                                    bufferImage = ImageIO.read(new File("src/resources/portaavionesH.png"));
                                    BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                                    ImageIcon image = new ImageIcon(aImage);
                                    aBoard[row][col].setImage(image);
                                    aImage = bufferImage.getSubimage(boxSize, 0, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row][col + 1].setImage(image);
                                    aImage = bufferImage.getSubimage(2 * boxSize, 0, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row][col + 2].setImage(image);
                                    aImage = bufferImage.getSubimage(3 * boxSize, 0, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row][col + 3].setImage(image);
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo portaavionesH.png");
                                }
                            }
                        }
                        break;
                }

            }
            else {
                aux = true;
                switch(ships[i]) {
                    case 1:
                        while(aux) {
                            row = random.nextInt(gridSize - 1) + 1;
                            col = random.nextInt(gridSize - 1) + 1;
                            if(aBoard[row][col].getKindBox() == 0) {
                                aBoard[row][col].setHead(true);
                                aBoard[row][col].setOrientation(orientation);
                                aBoard[row][col].setKind(ships[i]);
                                ImageIcon image = new ImageIcon("src/resources/fragataV.png");
                                aBoard[row][col].setImage(image);
                                aux = false;
                            }
                        }
                        break;
                    case 2:
                        while(aux) {
                            row = random.nextInt(gridSize - 2) + 1;
                            col = random.nextInt(gridSize - 1) + 1;
                            if(aBoard[row][col].getKindBox() == 0 && aBoard[row + 1][col].getKindBox() == 0) {
                                aBoard[row][col].setHead(true);
                                aBoard[row][col].setOrientation(orientation);
                                aBoard[row][col].setKind(ships[i]);
                                aBoard[row + 1][col].setKind(ships[i]);
                                aux = false;
                                try {
                                    bufferImage = ImageIO.read(new File("src/resources/destructorV.png"));
                                    BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                                    ImageIcon image = new ImageIcon(aImage);
                                    aBoard[row][col].setImage(image);
                                    aImage = bufferImage.getSubimage(0, boxSize, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row + 1][col].setImage(image);
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo destructorV.png");
                                }
                            }
                        }
                        break;
                    case 3:
                        while(aux) {
                            row = random.nextInt(gridSize - 3) + 1;
                            col = random.nextInt(gridSize - 1) + 1;
                            if(aBoard[row][col].getKindBox() == 0 && aBoard[row + 1][col].getKindBox() == 0 && aBoard[row + 2][col].getKindBox() == 0) {
                                aBoard[row][col].setHead(true);
                                aBoard[row][col].setOrientation(orientation);
                                aBoard[row][col].setKind(ships[i]);
                                aBoard[row + 1][col].setKind(ships[i]);
                                aBoard[row + 2][col].setKind(ships[i]);
                                aux = false;
                                try {
                                    bufferImage = ImageIO.read(new File("src/resources/submarinoV.png"));
                                    BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                                    ImageIcon image = new ImageIcon(aImage);
                                    aBoard[row][col].setImage(image);
                                    aImage = bufferImage.getSubimage(0, boxSize, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row+ 1][col].setImage(image);
                                    aImage = bufferImage.getSubimage(0, 2 * boxSize, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row+ 2][col].setImage(image);
                                } catch (IOException e) {

                                    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo submarinoV.png");
                                }
                            }
                        }
                        break;
                    case 4:
                        while(aux) {
                            row = random.nextInt(gridSize - 4) + 1;
                            col = random.nextInt(gridSize - 1) + 1;
                            if(aBoard[row][col].getKindBox() == 0 && aBoard[row + 1][col].getKindBox() == 0 && aBoard[row + 2][col].getKindBox() == 0 && aBoard[row + 3][col].getKindBox() == 0) {
                                aBoard[row][col].setHead(true);
                                aBoard[row][col].setOrientation(orientation);
                                aBoard[row][col].setKind(ships[i]);
                                aBoard[row + 1][col].setKind(ships[i]);
                                aBoard[row + 2][col].setKind(ships[i]);
                                aBoard[row + 3][col].setKind(ships[i]);
                                aux = false;
                                try {
                                    bufferImage = ImageIO.read(new File("src/resources/portaavionesV.png"));
                                    BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                                    ImageIcon image = new ImageIcon(aImage);
                                    aBoard[row][col].setImage(image);
                                    aImage = bufferImage.getSubimage(0, boxSize, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row+ 1][col].setImage(image);
                                    aImage = bufferImage.getSubimage(0, 2 * boxSize, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row+ 2][col].setImage(image);
                                    aImage = bufferImage.getSubimage(0, 3 * boxSize, boxSize, boxSize);
                                    image = new ImageIcon(aImage);
                                    aBoard[row + 3][col].setImage(image);
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo portaavionesV.png");
                                }
                            }
                        }
                        break;
                }
            }
        }
    }




    public void hideShips() {
        for(int row = 1; row < gridSize; row++) {
            for(int col = 1; col < gridSize; col++) {
                ImageIcon image = new ImageIcon("src/resources/agua.png");
                aBoard[row][col].setImage(image);
            }
        }

    }

    public int clickedShoot(Tablero aTablero, int mod) {
        for(int row = 1; row < gridSize; row++) {
            for(int col = 1; col < gridSize; col++) {
                if(aBoard[row][col] == aTablero) {
                    if(aBoard[row][col].shoot()) {
                        if(aBoard[row][col].getKindBox() == 5) {
                            ImageIcon image = new ImageIcon("src/resources/equis.png");
                            aBoard[row][col].setIcon(image);
                            if(mod == 1) {
                                mod = 2;
                            } else {
                                mod = 1;
                            }
                        }
                        else {
                            ImageIcon image = new ImageIcon("src/resources/bomba.png");
                            aBoard[row][col].setIcon(image);
                        }
                    }
                }
            }
        }
        return mod;
    }

    private boolean belongBox(Tablero aTablero) {
        for(int row = 1; row < gridSize; row++) {
            for(int col = 1; col < gridSize; col++) {
                if(aBoard[row][col] == aTablero) {
                    return true;
                }
            }
        }
        return false;
    }

    public Tablero randomBox() {
        Random random = new Random();
        int row = random.nextInt(gridSize -1) + 1;
        int col = random.nextInt(gridSize -1) + 1;
        return aBoard[row][col];
    }

    public void killShips() {
        ImageIcon image = new ImageIcon("src/resources/explosion.png");
        for(int row = 1; row < gridSize; row++) {
            for(int col = 1; col < gridSize; col++) {
                if(aBoard[row][col].isHead()) {
                    if(aBoard[row][col].getOrientation() == 0) {
                        switch(aBoard[row][col].getKindBox()){
                            case -1:
                                if(aBoard[row][col].getKindBox() == -1) {
                                    aBoard[row][col].setKind(6);
                                    aBoard[row][col].setImage(image);
                                }
                                break;
                            case -2:
                                if(aBoard[row][col].getKindBox() == -2 && aBoard[row][col + 1].getKindBox() == -2) {
                                    aBoard[row][col].setKind(6);
                                    aBoard[row][col].setImage(image);
                                    aBoard[row][col + 1].setKind(6);
                                    aBoard[row][col + 1].setImage(image);
                                }
                                break;
                            case -3:
                                if(aBoard[row][col].getKindBox() == -3 && aBoard[row][col + 1].getKindBox() == -3 && aBoard[row][col + 2].getKindBox() == -3) {
                                    aBoard[row][col].setKind(6);
                                    aBoard[row][col].setImage(image);
                                    aBoard[row][col + 1].setKind(6);
                                    aBoard[row][col + 1].setImage(image);
                                    aBoard[row][col + 2].setKind(6);
                                    aBoard[row][col + 2].setImage(image);
                                }
                                break;
                            case -4:
                                if(aBoard[row][col].getKindBox() == -4 && aBoard[row][col + 1].getKindBox() == -4 && aBoard[row][col + 2].getKindBox() == -4 && aBoard[row][col + 3].getKindBox() == -4) {
                                    aBoard[row][col].setKind(6);
                                    aBoard[row][col].setImage(image);
                                    aBoard[row][col + 1].setKind(6);
                                    aBoard[row][col + 1].setImage(image);
                                    aBoard[row][col + 2].setKind(6);
                                    aBoard[row][col + 2].setImage(image);
                                    aBoard[row][col + 3].setKind(6);
                                    aBoard[row][col + 3].setImage(image);
                                }
                                break;
                        }
                    }
                    else {
                        switch(aBoard[row][col].getKindBox()){
                            case -1:
                                if(aBoard[row][col].getKindBox() == -1) {
                                    aBoard[row][col].setKind(6);
                                    aBoard[row][col].setImage(image);
                                }
                                break;
                            case -2:
                                if(aBoard[row][col].getKindBox() == -2 && aBoard[row + 1][col].getKindBox() == -2) {
                                    aBoard[row][col].setKind(6);
                                    aBoard[row][col].setImage(image);
                                    aBoard[row + 1][col].setKind(6);
                                    aBoard[row + 1][col].setImage(image);
                                }
                                break;
                            case -3:
                                if(aBoard[row][col].getKindBox() == -3 && aBoard[row + 1][col].getKindBox() == -3 && aBoard[row + 2][col].getKindBox() == -3) {
                                    aBoard[row][col].setKind(6);
                                    aBoard[row][col].setImage(image);
                                    aBoard[row + 1][col].setKind(6);
                                    aBoard[row + 1][col].setImage(image);
                                    aBoard[row + 2][col].setKind(6);
                                    aBoard[row + 2][col].setImage(image);
                                }
                                break;
                            case -4:
                                if(aBoard[row][col].getKindBox() == -4 && aBoard[row + 1][col].getKindBox() == -4 && aBoard[row + 2][col].getKindBox() == -4 && aBoard[row + 3][col].getKindBox() == -4) {
                                    aBoard[row][col].setKind(6);
                                    aBoard[row][col].setImage(image);
                                    aBoard[row + 1][col].setKind(6);
                                    aBoard[row + 1][col].setImage(image);
                                    aBoard[row + 2][col].setKind(6);
                                    aBoard[row + 2][col].setImage(image);
                                    aBoard[row + 3][col].setKind(6);
                                    aBoard[row + 3][col].setImage(image);
                                }
                                break;
                        }
                    }
                }
            }
        }
    }


    public void moveShip(Tablero startTablero, Tablero finalTablero) {
        int row = startTablero.getRow();
        int col = startTablero.getCol();
        int finalRow = finalTablero.getRow();
        int finalCol = finalTablero.getCol();

        boolean aux = true;
        if(belongBox(startTablero) && belongBox(finalTablero)) {
            if(startTablero.getOrientation() == 0) {
                switch(startTablero.getKindBox()) {
                    case 1:
                        if(aBoard[row][col] == aBoard[finalRow][finalCol]) {
                            moveOrientation(startTablero);
                            aux = false;
                        }
                        if(aBoard[row][col] != aBoard[finalRow][finalCol] && aux && aBoard[finalRow][finalCol].getKindBox() == 0) {
                            movePosition(startTablero, finalTablero);
                            aux = false;
                        }

                        break;
                    case 2:
                        if(aBoard[row][col] == aBoard[finalRow][finalCol] && finalRow < 10) {
                            if(aBoard[finalRow + 1][finalCol].getKindBox() == 0) {
                                moveOrientation(startTablero);
                                aux = false;
                            }
                        }
                        if(aBoard[row][col] != aBoard[finalRow][finalCol] && aux && aBoard[finalRow][finalCol].getKindBox() == 0 && finalCol < 10) {
                            if(aBoard[finalRow][finalCol + 1].getKindBox() == 0) {
                                movePosition(startTablero, finalTablero);
                                aux = false;
                            }
                        }
                        break;

                    case 3:
                        if(aBoard[row][col] == aBoard[finalRow][finalCol] && finalRow < 9) {
                            if(aBoard[finalRow + 1][finalCol].getKindBox() == 0 && aBoard[finalRow + 2][finalCol].getKindBox() == 0) {
                                moveOrientation(startTablero);
                                aux = false;
                            }
                        }
                        if(aBoard[row][col] != aBoard[finalRow][finalCol] && aux && aBoard[finalRow][finalCol].getKindBox() == 0 && finalCol < 9) {
                            if(aBoard[finalRow][finalCol + 1].getKindBox() == 0 && aBoard[finalRow][finalCol + 2].getKindBox() == 0) {
                                movePosition(startTablero, finalTablero);
                                aux = false;
                            }
                        }
                        break;
                    case 4:
                        if(aBoard[row][col] == aBoard[finalRow][finalCol] && finalRow < 8) {
                            if(aBoard[finalRow + 1][finalCol].getKindBox() == 0 && aBoard[finalRow + 2][finalCol].getKindBox() == 0 && aBoard[finalRow + 3][finalCol].getKindBox() == 0) {
                                moveOrientation(startTablero);
                                aux = false;
                            }
                        }
                        if(aBoard[row][col] != aBoard[finalRow][finalCol] && aux && aBoard[finalRow][finalCol].getKindBox() == 0 && finalCol < 8) {
                            if(aBoard[finalRow][finalCol + 1].getKindBox() == 0 && aBoard[finalRow][finalCol + 2].getKindBox() == 0 && aBoard[finalRow][finalCol + 3].getKindBox() == 0) {
                                movePosition(startTablero, finalTablero);
                                aux = false;
                            }
                        }
                        break;
                }
            }
            else {
                switch(startTablero.getKindBox()) {
                    case 1:
                        if(aBoard[row][col] == aBoard[finalRow][finalCol]) {
                            moveOrientation(startTablero);
                            aux = false;
                        }
                        if(aBoard[row][col] != aBoard[finalRow][finalCol] && aux && aBoard[finalRow][finalCol].getKindBox() == 0) {
                            movePosition(startTablero, finalTablero);
                            aux = false;
                        }
                        break;
                    case 2:
                        if(aBoard[row][col] == aBoard[finalRow][finalCol] && finalCol < 10) {
                            if(aBoard[finalRow][finalCol + 1].getKindBox() == 0) {
                                moveOrientation(startTablero);
                                aux = false;
                            }
                        }
                        if(aBoard[row][col] != aBoard[finalRow][finalCol] && aux && aBoard[finalRow][finalCol].getKindBox() == 0 && finalRow < 10) {
                            if(aBoard[finalRow + 1][finalCol].getKindBox() == 0) {
                                movePosition(startTablero, finalTablero);
                                aux = false;
                            }
                        }
                        break;
                    case 3:
                        if(aBoard[row][col] == aBoard[finalRow][finalCol] && finalCol < 9) {
                            if(aBoard[finalRow][finalCol + 1].getKindBox() == 0 && aBoard[finalRow][finalCol + 2].getKindBox() == 0) {
                                moveOrientation(startTablero);
                                aux = false;
                            }
                        }
                        if(aBoard[row][col] != aBoard[finalRow][finalCol] && aux && aBoard[finalRow][finalCol].getKindBox() == 0 && finalRow < 9) {
                            if(aBoard[finalRow + 1][finalCol].getKindBox() == 0 && aBoard[finalRow + 2][finalCol].getKindBox() == 0) {
                                movePosition(startTablero, finalTablero);
                                aux = false;
                            }
                        }
                        break;
                    case 4:
                        if(aBoard[row][col] == aBoard[finalRow][finalCol] && finalCol < 8) {
                            if(aBoard[finalRow][finalCol + 1].getKindBox() == 0 && aBoard[finalRow][finalCol + 2].getKindBox() == 0 && aBoard[finalRow][finalCol + 3].getKindBox() == 0) {
                                moveOrientation(startTablero);
                                aux = false;
                            }
                        }
                        if(aBoard[row][col] != aBoard[finalRow][finalCol] && aux && aBoard[finalRow][finalCol].getKindBox() == 0 && finalRow < 8) {
                            if(aBoard[finalRow + 1][finalCol].getKindBox() == 0 && aBoard[finalRow + 2][finalCol].getKindBox() == 0 && aBoard[finalRow + 3][finalCol].getKindBox() == 0) {
                                movePosition(startTablero, finalTablero);
                                aux = false;
                            }
                        }
                        break;
                }
            }
        }
    }

    private void movePosition(Tablero startTablero, Tablero finalTablero) {
        int row = startTablero.getRow();
        int col = startTablero.getCol();
        int finalRow = finalTablero.getRow();
        int finalCol = finalTablero.getCol();

        if(startTablero.getOrientation() == 0) {
            switch(startTablero.getKindBox()) {
                case 1:
                    aBoard[row][col].moveBox(aBoard[finalRow][finalCol]);
                    break;
                case 2:
                    aBoard[row][col].moveBox(aBoard[finalRow][finalCol]);
                    aBoard[row][col + 1].moveBox(aBoard[finalRow][finalCol + 1]);
                    break;
                case 3:
                    aBoard[row][col].moveBox(aBoard[finalRow][finalCol]);
                    aBoard[row][col + 1].moveBox(aBoard[finalRow][finalCol + 1]);
                    aBoard[row][col + 2].moveBox(aBoard[finalRow][finalCol + 2]);
                    break;
                case 4:
                    aBoard[row][col].moveBox(aBoard[finalRow][finalCol]);
                    aBoard[row][col + 1].moveBox(aBoard[finalRow][finalCol + 1]);
                    aBoard[row][col + 2].moveBox(aBoard[finalRow][finalCol + 2]);
                    aBoard[row][col + 3].moveBox(aBoard[finalRow][finalCol + 3]);
                    break;
            }
        }
        else {
            switch(startTablero.getKindBox()) {
                case 1:
                    aBoard[row][col].moveBox(aBoard[finalRow][finalCol]);
                    break;
                case 2:
                    aBoard[row][col].moveBox(aBoard[finalRow][finalCol]);
                    aBoard[row + 1][col].moveBox(aBoard[finalRow + 1][finalCol]);
                    break;
                case 3:
                    aBoard[row][col].moveBox(aBoard[finalRow][finalCol]);
                    aBoard[row + 1][col].moveBox(aBoard[finalRow + 1][finalCol]);
                    aBoard[row + 2][col].moveBox(aBoard[finalRow + 2][finalCol]);
                    break;
                case 4:
                    aBoard[row][col].moveBox(aBoard[finalRow][finalCol]);
                    aBoard[row + 1][col].moveBox(aBoard[finalRow + 1][finalCol]);
                    aBoard[row + 2][col].moveBox(aBoard[finalRow + 2][finalCol]);
                    aBoard[row + 3][col].moveBox(aBoard[finalRow + 3][finalCol]);
                    break;
            }
        }
    }

    private void moveOrientation(Tablero startTablero) {
        int row = startTablero.getRow();
        int col = startTablero.getCol();


        if(startTablero.getOrientation() == 0) {
            switch(startTablero.getKindBox()) {
                case 1:
                    aBoard[row][col].setOrientation(1);
                    ImageIcon image1 =  new ImageIcon("src/resources/fragataV.png");
                    aBoard[row][col].setImage(image1);
                    break;
                case 2:
                    aBoard[row][col].setOrientation(1);
                    aBoard[row][col].setKind(2);
                    aBoard[row + 1][col].setKind(2);
                    aBoard[row][col + 1].setKind(0);
                    try {
                        bufferImage = ImageIO.read(new File("src/resources/destructorV.png"));
                        BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                        ImageIcon image = new ImageIcon(aImage);
                        aBoard[row][col].setImage(image);
                        aImage = bufferImage.getSubimage(0, boxSize, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row + 1][col].setImage(image);
                        image =  new ImageIcon("src/resources/agua.png");
                        aBoard[row][col + 1].setImage(image);
                    } catch (IOException e) {

                        JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo destructorV.png");
                    }
                    break;
                case 3:
                    aBoard[row][col].setOrientation(1);
                    aBoard[row][col].setKind(3);
                    aBoard[row + 1][col].setKind(3);
                    aBoard[row + 2][col].setKind(3);
                    aBoard[row][col + 1].setKind(0);
                    aBoard[row][col + 2].setKind(0);
                    try {
                        bufferImage = ImageIO.read(new File("src/resources/submarinoV.png"));
                        BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                        ImageIcon image = new ImageIcon(aImage);
                        aBoard[row][col].setImage(image);
                        aImage = bufferImage.getSubimage(0, boxSize, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row + 1][col].setImage(image);
                        aImage = bufferImage.getSubimage(0, 2 * boxSize, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row + 2][col].setImage(image);
                        image =  new ImageIcon("src/resources/agua.png");
                        aBoard[row][col + 1].setImage(image);
                        aBoard[row][col + 2].setImage(image);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo submarinoV.png");
                    }
                    break;
                case 4:
                    aBoard[row][col].setOrientation(1);
                    aBoard[row][col].setKind(4);
                    aBoard[row + 1][col].setKind(4);
                    aBoard[row + 2][col].setKind(4);
                    aBoard[row + 3][col].setKind(4);
                    aBoard[row][col + 1].setKind(0);
                    aBoard[row][col + 2].setKind(0);
                    aBoard[row][col + 3].setKind(0);
                    try {
                        bufferImage = ImageIO.read(new File("src/resources/portaavionesV.png"));
                        BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                        ImageIcon image = new ImageIcon(aImage);
                        aBoard[row][col].setImage(image);
                        aImage = bufferImage.getSubimage(0, boxSize, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row+ 1][col].setImage(image);
                        aImage = bufferImage.getSubimage(0, 2 * boxSize, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row+ 2][col].setImage(image);
                        aImage = bufferImage.getSubimage(0, 3 * boxSize, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row + 3][col].setImage(image);
                        image =  new ImageIcon("src/resources/agua.png");
                        aBoard[row][col + 1].setImage(image);
                        aBoard[row][col + 2].setImage(image);
                        aBoard[row][col + 3].setImage(image);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo portaavionesV.png");
                    }
                    break;
            }
        }
        else {
            switch(startTablero.getKindBox()) {
                case 1:
                    aBoard[row][col].setOrientation(0);
                    ImageIcon image1 =  new ImageIcon("src/resources/fragataH.png");
                    aBoard[row][col].setImage(image1);
                    break;
                case 2:
                    aBoard[row][col].setOrientation(0);
                    aBoard[row][col].setKind(2);
                    aBoard[row][col + 1].setKind(2);
                    aBoard[row + 1][col].setKind(0);
                    try {
                        bufferImage = ImageIO.read(new File("src/resources/destructorH.png"));
                        BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                        ImageIcon image = new ImageIcon(aImage);
                        aBoard[row][col].setImage(image);
                        aImage = bufferImage.getSubimage(boxSize, 0, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row][col + 1].setImage(image);
                        image =  new ImageIcon("src/resources/agua.png");
                        aBoard[row + 1][col].setImage(image);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo destructorH.png");
                    }
                    break;
                case 3:
                    aBoard[row][col].setOrientation(0);
                    aBoard[row][col].setKind(3);
                    aBoard[row][col + 1].setKind(3);
                    aBoard[row][col + 2].setKind(3);
                    aBoard[row + 1][col].setKind(0);
                    aBoard[row + 2][col].setKind(0);
                    try {
                        bufferImage = ImageIO.read(new File("src/resources/submarinoH.png"));
                        BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                        ImageIcon image = new ImageIcon(aImage);
                        aBoard[row][col].setImage(image);
                        aImage = bufferImage.getSubimage(boxSize, 0, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row][col + 1].setImage(image);
                        aImage = bufferImage.getSubimage(2 * boxSize, 0, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row][col + 2].setImage(image);
                        image =  new ImageIcon("src/resources/agua.png");
                        aBoard[row + 1][col].setImage(image);
                        aBoard[row + 2][col].setImage(image);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo submarinoH.png");
                    }
                    break;
                case 4:
                    aBoard[row][col].setOrientation(0);
                    aBoard[row][col].setKind(4);
                    aBoard[row][col + 1].setKind(4);
                    aBoard[row][col + 2].setKind(4);
                    aBoard[row][col + 3].setKind(4);
                    aBoard[row + 1][col].setKind(0);
                    aBoard[row + 2][col].setKind(0);
                    aBoard[row + 3][col].setKind(0);
                    try {
                        bufferImage = ImageIO.read(new File("src/resources/portaavionesH.png"));
                        BufferedImage aImage = bufferImage.getSubimage(0, 0, boxSize, boxSize);
                        ImageIcon image = new ImageIcon(aImage);
                        aBoard[row][col].setImage(image);
                        aImage = bufferImage.getSubimage(boxSize, 0, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row][col + 1].setImage(image);
                        aImage = bufferImage.getSubimage(2 * boxSize, 0, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row][col + 2].setImage(image);
                        aImage = bufferImage.getSubimage(3 * boxSize, 0, boxSize, boxSize);
                        image = new ImageIcon(aImage);
                        aBoard[row][col + 3].setImage(image);
                        image =  new ImageIcon("src/resources/agua.png");
                        aBoard[row + 1][col].setImage(image);
                        aBoard[row + 2][col].setImage(image);
                        aBoard[row + 3][col].setImage(image);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo portaavionesH.png");
                    }
                    break;
            }
        }
    }


    public boolean winGame() {
        int counter = 0;
        for(int row = 1; row < gridSize; row++) {
            for(int col = 1; col < gridSize; col++) {
                if(aBoard[row][col].getKindBox() == 6) {
                    counter = counter + 1;
                }
            }
        }
        if(counter == 20) {
            return true;
        }
        return false;

    }


    public void reset() {
        ImageIcon image =  new ImageIcon("src/resources/agua.png");
        for(int row = 1; row < gridSize; row++) {
            for(int col = 1; col < gridSize; col++) {
                aBoard[row][col].setKind(0);
                aBoard[row][col].setImage(image);
                aBoard[row][col].setOrientation(-1);
                aBoard[row][col].setHead(false);
            }
        }

        spawnShips();
    }


    public Tablero getBox(int row, int col) {
        if(row < gridSize && col < gridSize && row >= 0 && col >= 0 ) {
            return aBoard[row][col];
        }
        return null;
    }


    public void setBox(int row, int col, Tablero aTablero) {
        if(row < gridSize && col < gridSize && row >= 0 && col >= 0 ) {
            aBoard[row][col].setHead(aTablero.isHead());
            aBoard[row][col].setImage(aTablero.getImage());
            aBoard[row][col].setKind(aTablero.getKindBox());
            aBoard[row][col].setOrientation(aTablero.getOrientation());

        }
    }
}