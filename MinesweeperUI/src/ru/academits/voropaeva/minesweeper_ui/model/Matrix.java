package ru.academits.voropaeva.minesweeper_ui.model;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Matrix implements Model {
    private final int rows = 9;
    private final int cols = 9;
    private final int bombs = 10;
    private final ImageIcon[][] lowerMatrix = new ImageIcon[rows][cols];
    private final ImageIcon[][] upperMatrix = new ImageIcon[rows][cols];

    public Matrix() {
        initializeLowerMatrix();
        initializeUpperMatrix();
    }

    private void initializeUpperMatrix() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                upperMatrix[y][x] = getIcon("closed");
            }
        }
    }

    @Override
    public ImageIcon[][] getUpperMatrix() {
        return upperMatrix;
    }

    @Override
    public ImageIcon[][] getLowerMatrix() {
        return lowerMatrix;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public int getRows() {
        return rows;
    }

    private void initializeLowerMatrix() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                lowerMatrix[y][x] = getIcon("empty");

            }
        }

        setBombsOnLowerMatrix();
        setNumbersOnLowerMatrix();
    }

    private void setNumbersOnLowerMatrix() {
        Image bomb = getIcon("bomb").getImage();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (!Objects.equals(lowerMatrix[y][x].getImage(), bomb)) {
                    int countBombsAround = 0;
                    int shift = 1;

                    for (int i = -shift; i <= shift; i++) {
                        for (int j = -shift; j <= shift; j++) {
                            try {
                                if (lowerMatrix[i + y][j + x].getImage().equals(bomb)) {
                                    ++countBombsAround;
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }

                    if (countBombsAround != 0) {
                        lowerMatrix[y][x] = getIcon("number" + countBombsAround);
                    }
                }
            }
        }
    }

    private void setBombsOnLowerMatrix() {
        ImageIcon bombIcon = getIcon("bomb");

        for (int i = 0; i < bombs; i++) {
            int randomX;
            int randomY;

            while (true) {
                if (!Objects.equals(
                        lowerMatrix[randomY = (int) (Math.random() * rows)][randomX = (int) (Math.random() * cols)].getImage(),
                        bombIcon.getImage())
                ) {
                    break;
                }
            }

            lowerMatrix[randomY][randomX] = bombIcon;
        }
    }

    public ImageIcon getIcon(String name) {
        return new ImageIcon("MinesweeperUI\\src\\ru\\academits\\voropaeva\\minesweeper_ui\\resources\\" + name.toLowerCase() + ".png");
    }
}
