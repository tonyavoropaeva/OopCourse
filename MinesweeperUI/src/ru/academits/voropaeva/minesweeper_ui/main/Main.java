package ru.academits.voropaeva.minesweeper_ui.main;

import ru.academits.voropaeva.minesweeper_ui.controller.Controller;
import ru.academits.voropaeva.minesweeper_ui.controller.MinesweeperController;
import ru.academits.voropaeva.minesweeper_ui.model.Matrix;
import ru.academits.voropaeva.minesweeper_ui.model.Model;

public class Main {
    public static void main(String[] args) {
        Model model = new Matrix();
        Controller controller = new MinesweeperController(model);
    }
}
