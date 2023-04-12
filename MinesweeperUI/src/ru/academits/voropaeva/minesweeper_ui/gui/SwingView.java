package ru.academits.voropaeva.minesweeper_ui.gui;

import ru.academits.voropaeva.minesweeper_ui.model.Cells;
import ru.academits.voropaeva.minesweeper_ui.model.GameState;
import ru.academits.voropaeva.minesweeper_ui.model.ModelMinesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

import static ru.academits.voropaeva.minesweeper_ui.model.Cells.*;

public class SwingView implements View {
    private ModelMinesweeper model;
    private final JFrame frame;
    private JPanel gameElementsPanel;
    private JPanel cellsPanel;
    private static final int ICON_SIZE = 50;
    private int seconds = 1;
    private int flagsUsedCount = 10;
    private JLabel labelFlagsCount;
    private JButton buttonRestart;
    private Timer timer;
    JLabel labelTimer;
    private final JButton[][] buttons;
    private final ImageIcon bomb = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/bomb.png");
    private final ImageIcon bombed = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/bombed.png");
    private final ImageIcon closed = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/closed.png");
    private final ImageIcon dead = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/dead.png");
    private final ImageIcon empty = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/empty.png");
    private final ImageIcon flag = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/flag.png");
    private final ImageIcon icon = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/icon.png");
    private final ImageIcon nobomb = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/nobomb.png");
    private final ImageIcon number1 = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/number1.png");
    private final ImageIcon number2 = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/number2.png");
    private final ImageIcon number3 = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/number3.png");
    private final ImageIcon number4 = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/number4.png");
    private final ImageIcon number5 = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/number5.png");
    private final ImageIcon number6 = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/number6.png");
    private final ImageIcon number7 = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/number7.png");
    private final ImageIcon number8 = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/number8.png");
    private final ImageIcon smile = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/smile.png");
    private final ImageIcon iconMini = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/iconMini.png");
    private final ImageIcon win = new ImageIcon("MinesweeperUI/src/ru/academits/voropaeva/minesweeper_ui/resources/win.png");

    public SwingView(ModelMinesweeper model) {
        this.model = model;
        buttons = new JButton[model.getRows()][model.getColumns()];
        frame = new JFrame("Сапёр");
    }

    @Override
    public void initialize() {
        SwingUtilities.invokeLater(() -> {
            initializeFrame();
            initializeGameElementsPanel();
            initializeCellsPanel();
            initializeMenu();
        });
    }

    private void initializeFrame() {
        frame.setSize(model.getColumns() * ICON_SIZE + 25, model.getRows() * ICON_SIZE + 127);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setIconImage(icon.getImage());
        frame.setLayout(new FlowLayout());
    }

    private void initializeGameElementsPanel() {
        gameElementsPanel = new JPanel(new GridBagLayout());
        gameElementsPanel.setSize(450, 50);

        labelFlagsCount = new JLabel(String.valueOf(flagsUsedCount), JLabel.CENTER);
        labelFlagsCount.setFont(new Font("TimesRoman", Font.BOLD, 30));
        labelFlagsCount.setPreferredSize(new Dimension(100, 50));
        labelFlagsCount.setForeground(Color.red);

        gameElementsPanel.add(labelFlagsCount);

        buttonRestart = new JButton(smile);
        buttonRestart.setPreferredSize(new Dimension(50, 50));
        buttonRestart.setBackground(Color.white);

        buttonRestart.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        restart();
                    }
                });
            }
        });

        gameElementsPanel.add(buttonRestart);
        initializeTimer();
        gameElementsPanel.setBackground(Color.BLACK);
        frame.add(gameElementsPanel);
    }

    private void restart() {
        model = model.restart();

        for (int y = 0; y < model.getRows(); y++) {
            for (int x = 0; x < model.getColumns(); x++) {
                buttons[y][x].setIcon(getIcon(model.getCellButton(y, x)));
                buttons[y][x].setEnabled(true);
            }
        }

        buttonRestart.setIcon(smile);

        flagsUsedCount = 10;
        labelFlagsCount.setText(String.valueOf(flagsUsedCount));

        labelTimer.setText("000");
        seconds = 1;
        timer.start();

        cellsPanel.updateUI();
        gameElementsPanel.updateUI();
    }

    private void initializeTimer() {
        labelTimer = new JLabel("000", JLabel.CENTER);
        labelTimer.setFont(new Font("TimesRoman", Font.BOLD, 30));
        labelTimer.setForeground(Color.red);

        timer = new Timer(1000, e -> {
            labelTimer.setText(String.format("%03d", seconds));
            ++seconds;
        });

        timer.start();
        labelTimer.setPreferredSize(new Dimension(100, 50));
        gameElementsPanel.add(labelTimer);
    }

    private void initializeMenu() {
        Font font = new Font("Verdana", Font.PLAIN, 13);

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Игра");
        menu.setFont(font);

        JMenuItem newGame = new JMenuItem("Новая");
        newGame.addActionListener(e -> restart());
        newGame.setFont(font);

        JMenuItem about = new JMenuItem("О программе");
        about.addActionListener(e -> JOptionPane.showMessageDialog(
                frame,
                "Сапёр классический" + System.lineSeparator() +
                        "Разработала: Воропаева Тоня" + System.lineSeparator() +
                        "E-mail: voropaevatonya@gmail.com",
                "О программе",
                JOptionPane.INFORMATION_MESSAGE,
                iconMini
        ));
        about.setFont(font);

        JMenuItem highScores = new JMenuItem("Таблица рекордов");
        highScores.setFont(font);

        JMenuItem exit = new JMenuItem("Выход");
        exit.addActionListener(e -> System.exit(0));
        exit.setFont(font);

        menu.add(newGame);
        menu.addSeparator();
        menu.add(about);
        menu.addSeparator();
        menu.add(highScores);
        menu.addSeparator();
        menu.add(exit);

        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
    }

    private void initializeCellsPanel() {
        cellsPanel = new JPanel(new GridLayout(model.getRows(), model.getColumns(), 0, 0));
        frame.add(cellsPanel);
        paintPanel();
    }

    private void paintPanel() {
        for (int y = 0; y < model.getRows(); y++) {
            for (int x = 0; x < model.getColumns(); x++) {
                JButton button = new JButton(getIcon(model.getCellButton(y, x)));
                buttons[y][x] = button;
                button.setPreferredSize(new Dimension(ICON_SIZE, ICON_SIZE));
                cellsPanel.add(button);

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        SwingUtilities.invokeLater(() -> {
                            int x = button.getX() / ICON_SIZE;
                            int y = button.getY() / ICON_SIZE;

                            if (e.getButton() == MouseEvent.BUTTON1) {
                                model.openCell(y, x);

                                for (int coordinateY = 0; coordinateY < model.getRows(); coordinateY++) {
                                    for (int coordinateX = 0; coordinateX < model.getColumns(); coordinateX++) {
                                        Cells currentCell = model.getCellButton(coordinateY, coordinateX);

                                        if (
                                                (currentCell != CLOSED && currentCell != FLAG)
                                                        || model.checkGameState() == GameState.LOSS
                                                        || model.checkGameState() == GameState.WIN
                                        ) {
                                            buttons[coordinateY][coordinateX].setEnabled(false);
                                            buttons[coordinateY][coordinateX].setDisabledIcon(getIcon(model.getCellButton(coordinateY, coordinateX)));
                                        }
                                    }
                                }

                                switch (model.checkGameState()) {
                                    case LOSS -> {
                                        buttonRestart.setIcon(dead);
                                        timer.stop();
                                    }
                                    case WIN -> {
                                        buttonRestart.setIcon(win);
                                        timer.stop();

                                        JOptionPane.showMessageDialog(
                                                frame,
                                                "Вы победили! Поздравляю!",
                                                "Победа",
                                                JOptionPane.PLAIN_MESSAGE
                                        );

                                    }
                                }
                            } else if (e.getButton() == MouseEvent.BUTTON3) {
                                flagsUsedCount += model.setFlag(y, x);
                                labelFlagsCount.setText(String.valueOf(flagsUsedCount));

                                button.setIcon(getIcon(model.getCellButton(y, x)));
                            }

                            cellsPanel.updateUI();
                        });
                    }
                });
            }
        }
    }

    public ImageIcon getIcon(Cells cell) {
        return switch (cell) {
            case BOMB -> bomb;
            case BOMBED -> bombed;
            case CLOSED -> closed;
            case DEAD -> dead;
            case EMPTY -> empty;
            case FLAG -> flag;
            case ICON -> icon;
            case NOBOMB -> nobomb;
            case NUMBER1 -> number1;
            case NUMBER2 -> number2;
            case NUMBER3 -> number3;
            case NUMBER4 -> number4;
            case NUMBER5 -> number5;
            case NUMBER6 -> number6;
            case NUMBER7 -> number7;
            case NUMBER8 -> number8;
            case SMILE -> smile;
        };
    }
}