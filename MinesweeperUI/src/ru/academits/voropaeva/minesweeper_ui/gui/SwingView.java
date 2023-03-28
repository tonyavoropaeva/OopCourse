package ru.academits.voropaeva.minesweeper_ui.gui;


import ru.academits.voropaeva.minesweeper_ui.controller.Controller;
import ru.academits.voropaeva.minesweeper_ui.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingView implements View {
    private final Model model;
    private final Controller controller;
    private JFrame frame;
    private JPanel gameElementsPanel;
    private JPanel cellsPanel;
    private final int iconsSize = 50;

    public SwingView(Controller controller, Model model) {
        this.model = model;
        this.controller = controller;
    }

    @Override
    public void initialize() {
        SwingUtilities.invokeLater(() -> {
            initializeFrame();
            initializeGameElementsPanel();
            initializeCellsPanel();
        });
    }

    private void initializeFrame() {
        frame = new JFrame("Сапёр");
        frame.setSize(model.getCols() * iconsSize + 25, model.getRows() * iconsSize + 100); //р-р
        //  frame.setResizable(false); //запретили менять р-р
        frame.setLocationRelativeTo(null); // окно отрыв-ся по центру
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//программа завершается после закрытия
        frame.setVisible(true);
        frame.setIconImage(model.getIcon("icon").getImage());
        frame.setLayout(new FlowLayout());
    }

    private void initializeGameElementsPanel() {
        gameElementsPanel = new JPanel(new GridBagLayout());
        gameElementsPanel.setSize(450, 50);

        JTextField textFieldCountBombs = new JTextField();
        textFieldCountBombs.setPreferredSize(new Dimension(100, 50));
        gameElementsPanel.add(textFieldCountBombs);

        JButton button = new JButton(model.getIcon("smile"));
        button.setPreferredSize(new Dimension(50, 50));
        gameElementsPanel.add(button);

        JTextField textFieldTimer = new JTextField();
        textFieldTimer.setPreferredSize(new Dimension(100, 50));
        gameElementsPanel.add(textFieldTimer);

        frame.add(gameElementsPanel);
    }

    private void initializeCellsPanel() {
        cellsPanel = new JPanel(new GridLayout(model.getRows(), model.getCols(), 0, 0));
        frame.add(cellsPanel);

        paintPanel();
    }

    private void paintPanel() {
        for (int y = 0; y < model.getRows(); y++) {
            for (int x = 0; x < model.getCols(); x++) {
                JButton button = new JButton(model.getUpperMatrix()[y][x]);
                button.setPreferredSize(new Dimension(iconsSize, iconsSize));
                cellsPanel.add(button);

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        SwingUtilities.invokeLater(() -> {
                            int x = button.getX() / iconsSize;
                            int y = button.getY() / iconsSize;
                            Image currentCell = model.getUpperMatrix()[y][x].getImage();

                            if (e.getButton() == MouseEvent.BUTTON1) { // открываем ячейку
                                if (!currentCell.equals(model.getIcon("flag").getImage())) {
                                    button.setEnabled(false);
                                    button.setDisabledIcon(model.getLowerMatrix()[y][x]);
                                }
                            } else if (e.getButton() == MouseEvent.BUTTON3) { //устанавливаем или удаляем флажок
                                if (currentCell.equals(model.getIcon("closed").getImage())) {
                                    model.getUpperMatrix()[y][x] = model.getIcon("flag");
                                } else if (currentCell.equals(model.getIcon("flag").getImage())) {
                                    model.getUpperMatrix()[y][x] = model.getIcon("closed");
                                }

                                button.setIcon(model.getUpperMatrix()[y][x]);
                            }

                            cellsPanel.updateUI();
                        });
                    }
                });
            }
        }
    }
}