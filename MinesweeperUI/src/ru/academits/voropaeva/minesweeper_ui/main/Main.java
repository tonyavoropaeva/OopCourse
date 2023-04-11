package ru.academits.voropaeva.minesweeper_ui.main;

import ru.academits.voropaeva.minesweeper_ui.gui.SwingView;
import ru.academits.voropaeva.minesweeper_ui.gui.View;
import ru.academits.voropaeva.minesweeper_ui.model.PlayingField;
import ru.academits.voropaeva.minesweeper_ui.model.ModelMinesweeper;

public class Main {
    public static void main(String[] args) {
        ModelMinesweeper model = new PlayingField();
        View view = new SwingView(model);
        view.initialize();
    }
}