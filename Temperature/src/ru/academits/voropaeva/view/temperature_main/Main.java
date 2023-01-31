package ru.academits.voropaeva.view.temperature_main;

import ru.academits.voropaeva.model.converter.ConvertTemperature;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Конвертер температур");

            frame.setSize(500, 350);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);

            // задали тип сетки
            GridBagLayout gridBagLayout = new GridBagLayout();
            JPanel panel = new JPanel(gridBagLayout);

            // СТРОКА 1
            GridBagConstraints gridBagConstraintsLineOne = new GridBagConstraints();
            gridBagConstraintsLineOne.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLineOne.gridy = 0;

            JLabel labelOne = new JLabel("Конвертировать из:");
            gridBagLayout.setConstraints(labelOne, gridBagConstraintsLineOne);

            // СТРОКА 2
            GridBagConstraints gridBagConstraintsLineTwo = new GridBagConstraints();
            gridBagConstraintsLineTwo.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLineTwo.gridy = 1;
            gridBagConstraintsLineTwo.insets = new Insets(10, 10, 40, 10);

            JTextField textFieldInput = new JTextField(10);
            textFieldInput.setPreferredSize(new Dimension(15, 30));
            textFieldInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));

            ButtonGroup buttonGroupFrom = new ButtonGroup();
            JRadioButton celsiusButtonFrom = new JRadioButton("°C", true);
            buttonGroupFrom.add(celsiusButtonFrom);
            JRadioButton fahrenheitButtonFrom = new JRadioButton("°F");
            buttonGroupFrom.add(fahrenheitButtonFrom);
            JRadioButton kelvinButtonFrom = new JRadioButton("°К");
            buttonGroupFrom.add(kelvinButtonFrom);

            gridBagLayout.setConstraints(textFieldInput, gridBagConstraintsLineTwo);
            gridBagLayout.setConstraints(celsiusButtonFrom, gridBagConstraintsLineTwo);
            gridBagLayout.setConstraints(fahrenheitButtonFrom, gridBagConstraintsLineTwo);
            gridBagLayout.setConstraints(kelvinButtonFrom, gridBagConstraintsLineTwo);

            //СТРОКА 3
            GridBagConstraints gridBagConstraintsLineThree = new GridBagConstraints();
            gridBagConstraintsLineThree.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLineThree.gridy = 2;

            JLabel labelTwo = new JLabel("Конвертировать в:");
            gridBagLayout.setConstraints(labelTwo, gridBagConstraintsLineThree);

            //СТРОКА 4
            GridBagConstraints gridBagConstraintsLineFour = new GridBagConstraints();
            gridBagConstraintsLineFour.insets = new Insets(10, 0, 0, 0);
            gridBagConstraintsLineFour.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLineFour.gridy = 3;

            ButtonGroup buttonGroupIn = new ButtonGroup();
            JRadioButton celsiusButtonIn = new JRadioButton("°C", true);
            buttonGroupIn.add(celsiusButtonIn);
            JRadioButton fahrenheitButtonIn = new JRadioButton("°F");
            buttonGroupIn.add(fahrenheitButtonIn);
            JRadioButton kelvinButtonIn = new JRadioButton("°К");
            buttonGroupIn.add(kelvinButtonIn);

            JTextField textFieldOutput = new JTextField(10);
            textFieldOutput.setPreferredSize(new Dimension(15, 30));
            textFieldOutput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
            textFieldOutput.setEditable(false);  // запретили редактирование

            gridBagLayout.setConstraints(textFieldOutput, gridBagConstraintsLineFour);
            gridBagLayout.setConstraints(celsiusButtonIn, gridBagConstraintsLineFour);
            gridBagLayout.setConstraints(fahrenheitButtonIn, gridBagConstraintsLineFour);
            gridBagLayout.setConstraints(kelvinButtonIn, gridBagConstraintsLineFour);

            // СТРОКА 5
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.gridx = 1;
            gridBagConstraints3.gridy = 4;
            gridBagConstraints3.insets = new Insets(30, 0, 0, 0);

            JButton button = new JButton("Рассчитать");
            gridBagLayout.setConstraints(button, gridBagConstraints3);

            panel.add(labelOne);
            panel.add(textFieldInput);
            panel.add(celsiusButtonFrom);
            panel.add(fahrenheitButtonFrom);
            panel.add(kelvinButtonFrom);
            panel.add(labelTwo);
            panel.add(textFieldOutput);
            panel.add(celsiusButtonIn);
            panel.add(fahrenheitButtonIn);
            panel.add(kelvinButtonIn);
            panel.add(button);
            frame.add(panel);

            button.addActionListener(e -> {
                try {
                    double result = new ConvertTemperature().getConversion(
                            textFieldInput.getText(),
                            buttonGroupFrom.getElements(),
                            buttonGroupIn.getElements()
                    );

                    textFieldOutput.setText(String.format("%.3f", result));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frame, "Введите число!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }
}