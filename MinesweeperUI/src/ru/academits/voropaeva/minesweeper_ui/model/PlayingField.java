package ru.academits.voropaeva.minesweeper_ui.model;

import java.util.*;

import static ru.academits.voropaeva.minesweeper_ui.model.Cells.*;
import static ru.academits.voropaeva.minesweeper_ui.model.GameState.*;

public class PlayingField implements ModelMinesweeper {
    private static final int rows = 9;
    private static final int columns = 9;
    private static final int bombs = 10;
    private final Cells[][] cellsUnderButtons = new Cells[rows][columns];
    private final Cells[][] buttonCells = new Cells[rows][columns];
    private GameState gameState = GAME;
    private int closedCellsCount = rows * columns;

    public PlayingField() {
        initializeCellsUnderButtons();
        initializeButtonCells();
    }

    @Override
    public GameState checkGameState() {
        return gameState;
    }

    @Override
    public PlayingField restart() {
        return new PlayingField();
    }

    private void initializeButtonCells() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                buttonCells[y][x] = CLOSED;
            }
        }
    }

    @Override
    public Cells getCellButton(int y, int x) {
        return buttonCells[y][x];
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public int getRows() {
        return rows;
    }

    private boolean hasBombsAround(int y, int x) {
        int shift = 1;

        for (int i = -shift; i <= shift; i++) {
            for (int j = -shift; j <= shift; j++) {
                try {
                    if (cellsUnderButtons[i + y][j + x] == BOMB) {
                        return true;
                    }
                } catch (Exception ignored) {
                }
            }
        }

        return false;
    }

    @Override
    public void openCell(int y, int x) {
        Cells currentButton = buttonCells[y][x];
        Cells currentCells = cellsUnderButtons[y][x];

        //если первый клик бомба - меняем поле
        while ((closedCellsCount == columns * rows) && currentCells == BOMB) {
            initializeCellsUnderButtons();
            currentCells = cellsUnderButtons[y][x];
        }

        //если мы выиграли\проиграли или нажали на флажок - выходим
        if (gameState == LOSS || gameState == WIN || currentButton == FLAG) {
            return;
        }

        //переносим ячейку на кнопку
        buttonCells[y][x] = cellsUnderButtons[y][x];

        //уменьшаем кол-во закрытых ячеек
        --closedCellsCount;

        switch (buttonCells[y][x]) {
            case BOMB -> {
                openCellsWithBomb();

                gameState = LOSS;
                buttonCells[y][x] = BOMBED;

                return;
            }
            case EMPTY -> openEmptyCellsAround(y, x);
        }

        if (closedCellsCount == bombs) {
            gameState = WIN;
        }
        System.out.println(closedCellsCount);
    }

    private void openCellsWithBomb() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                if (buttonCells[y][x] == FLAG && cellsUnderButtons[y][x] != BOMB) {
                    buttonCells[y][x] = NOBOMB;
                } else if (buttonCells[y][x] != FLAG && cellsUnderButtons[y][x] == BOMB) {
                    buttonCells[y][x] = cellsUnderButtons[y][x];
                }
            }
        }
    }

    @Override
    public void openEmptyCellsAround(int y, int x) {
        Queue<String> cellsMap = new LinkedList<>();

        cellsMap.offer(y + ";" + x);

        while (!cellsMap.isEmpty()) {
            String[] coordinates = cellsMap.poll().split(";");

            int coordinateY = Integer.parseInt(coordinates[0]);
            int coordinateX = Integer.parseInt(coordinates[1]);

            if (buttonCells[coordinateY][coordinateX] == CLOSED) {
                buttonCells[coordinateY][coordinateX] = cellsUnderButtons[coordinateY][coordinateX];
                --closedCellsCount;
            }

            if (!hasBombsAround(coordinateY, coordinateX)) {
                int shift = 1;

                for (int i = -shift; i <= shift; i++) {
                    for (int j = -shift; j <= shift; j++) {
                        try {
                            if (buttonCells[i + coordinateY][j + coordinateX] == CLOSED) {
                                cellsMap.offer((i + coordinateY) + ";" + (j + coordinateX));
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            }


        }
    }

    @Override
    public int setFlag(int y, int x) {
        if (gameState == LOSS || gameState == WIN) {
            return 0;
        }

        if (buttonCells[y][x] == FLAG) {
            buttonCells[y][x] = CLOSED;
            return 1;
        }

        if (buttonCells[y][x] == CLOSED) {
            buttonCells[y][x] = FLAG;
            return -1;
        }

        return 0;
    }

    private void initializeCellsUnderButtons() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                cellsUnderButtons[y][x] = EMPTY;

            }
        }

        setBombsInCellsUnderButtons();
        setNumbersInCellsUnderButtons();
    }

    private void setNumbersInCellsUnderButtons() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                if (!Objects.equals(cellsUnderButtons[y][x], BOMB)) {
                    int countBombsAround = 0;
                    int shift = 1;

                    for (int i = -shift; i <= shift; i++) {
                        for (int j = -shift; j <= shift; j++) {
                            try {
                                if (cellsUnderButtons[i + y][j + x].equals(BOMB)) {
                                    ++countBombsAround;
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }

                    if (countBombsAround != 0) {
                        cellsUnderButtons[y][x] = Cells.valueOf("NUMBER" + countBombsAround);
                    }
                }
            }
        }
    }

    private void setBombsInCellsUnderButtons() {
        final Random random = new Random();

        for (int i = 0; i < bombs; i++) {
            int randomX;
            int randomY;

            do {
                randomY = random.nextInt(rows);
                randomX = random.nextInt(columns);

            } while (Objects.equals(cellsUnderButtons[randomY][randomX], BOMB));

            cellsUnderButtons[randomY][randomX] = BOMB;
        }
    }
}