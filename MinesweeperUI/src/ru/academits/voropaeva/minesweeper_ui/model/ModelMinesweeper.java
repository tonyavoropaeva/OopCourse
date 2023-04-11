package ru.academits.voropaeva.minesweeper_ui.model;

public interface ModelMinesweeper {
    Cells getCellButton(int y, int x);

    int getColumns();

    int getRows();

    void openEmptyCellsAround(int y, int x);

    void openCell(int y, int x);

    int setFlag(int y, int x);

    GameState checkGameState();

    PlayingField restart();
}