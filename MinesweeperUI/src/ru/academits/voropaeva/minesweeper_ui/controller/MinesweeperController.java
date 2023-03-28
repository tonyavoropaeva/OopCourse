package ru.academits.voropaeva.minesweeper_ui.controller;

import ru.academits.voropaeva.minesweeper_ui.gui.SwingView;
import ru.academits.voropaeva.minesweeper_ui.gui.View;
import ru.academits.voropaeva.minesweeper_ui.model.Model;

public class MinesweeperController implements Controller {
    Model model;
    View view;

    public MinesweeperController(Model model) {
        view = new SwingView(this, model);

        this.model = model;
        view.initialize();
    }
}
