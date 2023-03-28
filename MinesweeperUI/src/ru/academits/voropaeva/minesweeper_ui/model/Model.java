package ru.academits.voropaeva.minesweeper_ui.model;

import javax.swing.*;
import java.awt.*;

public interface Model {
    ImageIcon getIcon(String name);

    ImageIcon[][] getLowerMatrix();
    ImageIcon[][] getUpperMatrix();

    int getCols();

    int getRows();
}