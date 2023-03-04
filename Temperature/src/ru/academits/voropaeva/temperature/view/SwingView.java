package ru.academits.voropaeva.temperature.view;

import ru.academits.voropaeva.temperature.model.*;

import javax.swing.*;
import java.awt.*;

import static ru.academits.voropaeva.temperature.model.TemperatureScale.*;

public class SwingView implements View {
    private TemperatureScale model;
    private JFrame frame;
    private JTextField textFieldInput;

    public SwingView(TemperatureScale model) {
        this.model = model;
    }

    @Override
    public void initialize() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Конвертер температур");
            frame.setSize(500, 350);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(400, 280));
            frame.setVisible(true);

            JLabel labelConvertFrom = new JLabel("Конвертировать из:");
            textFieldInput = new JTextField(10);
            ButtonGroup buttonGroupFrom = new ButtonGroup();
            JRadioButton celsiusButtonFrom = new JRadioButton(CELSIUS_SCALE.toString(), true);
            JRadioButton fahrenheitButtonFrom = new JRadioButton(FAHRENHEIT_SCALE.toString());
            JRadioButton kelvinButtonFrom = new JRadioButton(KELVIN_SCALE.toString());
            JLabel labelConvertIn = new JLabel("Конвертировать в:");
            ButtonGroup buttonGroupIn = new ButtonGroup();
            JRadioButton celsiusButtonIn = new JRadioButton(CELSIUS_SCALE.toString(), true);
            JRadioButton fahrenheitButtonIn = new JRadioButton(FAHRENHEIT_SCALE.toString());
            JRadioButton kelvinButtonIn = new JRadioButton(KELVIN_SCALE.toString());
            JTextField textFieldOutput = new JTextField(10);
            JButton calculateButton = new JButton("Рассчитать");

            GridBagLayout gridBagLayout = new GridBagLayout();
            JPanel panel = new JPanel(gridBagLayout);

            // СТРОКА 1
            GridBagConstraints gridBagConstraintsLineOne = new GridBagConstraints();
            gridBagConstraintsLineOne.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLineOne.gridy = 0;
            gridBagLayout.setConstraints(labelConvertFrom, gridBagConstraintsLineOne);

            // СТРОКА 2
            GridBagConstraints gridBagConstraintsLineTwo = new GridBagConstraints();
            gridBagConstraintsLineTwo.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLineTwo.gridy = 1;
            gridBagConstraintsLineTwo.insets = new Insets(10, 10, 40, 10);

            textFieldInput.setPreferredSize(new Dimension(15, 30));
            textFieldInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));

            buttonGroupFrom.add(celsiusButtonFrom);
            buttonGroupFrom.add(fahrenheitButtonFrom);
            buttonGroupFrom.add(kelvinButtonFrom);

            gridBagLayout.setConstraints(textFieldInput, gridBagConstraintsLineTwo);
            gridBagLayout.setConstraints(celsiusButtonFrom, gridBagConstraintsLineTwo);
            gridBagLayout.setConstraints(fahrenheitButtonFrom, gridBagConstraintsLineTwo);
            gridBagLayout.setConstraints(kelvinButtonFrom, gridBagConstraintsLineTwo);

            //СТРОКА 3
            GridBagConstraints gridBagConstraintsLineThree = new GridBagConstraints();
            gridBagConstraintsLineThree.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLineThree.gridy = 2;

            gridBagLayout.setConstraints(labelConvertIn, gridBagConstraintsLineThree);

            //СТРОКА 4
            GridBagConstraints gridBagConstraintsLineFour = new GridBagConstraints();
            gridBagConstraintsLineFour.insets = new Insets(10, 0, 0, 0);
            gridBagConstraintsLineFour.gridx = GridBagConstraints.RELATIVE;
            gridBagConstraintsLineFour.gridy = 3;

            buttonGroupIn.add(celsiusButtonIn);
            buttonGroupIn.add(fahrenheitButtonIn);
            buttonGroupIn.add(kelvinButtonIn);

            textFieldOutput.setPreferredSize(new Dimension(15, 30));
            textFieldOutput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 17));
            textFieldOutput.setEditable(false);  // запретили редактирование

            gridBagLayout.setConstraints(textFieldOutput, gridBagConstraintsLineFour);
            gridBagLayout.setConstraints(celsiusButtonIn, gridBagConstraintsLineFour);
            gridBagLayout.setConstraints(fahrenheitButtonIn, gridBagConstraintsLineFour);
            gridBagLayout.setConstraints(kelvinButtonIn, gridBagConstraintsLineFour);

            // СТРОКА 5
            GridBagConstraints gridBagConstraintsFive = new GridBagConstraints();
            gridBagConstraintsFive.gridy = 4;
            gridBagConstraintsFive.gridwidth = 4;
            gridBagConstraintsFive.insets = new Insets(30, 0, 0, 0);

            gridBagLayout.setConstraints(calculateButton, gridBagConstraintsFive);

            panel.add(labelConvertFrom);
            panel.add(textFieldInput);
            panel.add(celsiusButtonFrom);
            panel.add(fahrenheitButtonFrom);
            panel.add(kelvinButtonFrom);
            panel.add(labelConvertIn);
            panel.add(textFieldOutput);
            panel.add(celsiusButtonIn);
            panel.add(fahrenheitButtonIn);
            panel.add(kelvinButtonIn);
            panel.add(calculateButton);
            frame.add(panel);

            calculateButton.addActionListener(e -> {
                if (celsiusButtonFrom.isSelected()) {
                    model = CELSIUS_SCALE;
                } else if (fahrenheitButtonFrom.isSelected()) {
                    model = FAHRENHEIT_SCALE;
                } else {
                    model = KELVIN_SCALE;
                }

                try {
                    model.setTemperature(Double.parseDouble(textFieldInput.getText()));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frame, "Введите число!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                model.convertToCelsius();

                if (celsiusButtonIn.isSelected()) {
                    model = CELSIUS_SCALE.convertToCelsius();
                } else if (fahrenheitButtonIn.isSelected()) {
                    model = CELSIUS_SCALE.convertToFahrenheit();
                } else {
                    model = CELSIUS_SCALE.convertToKelvin();
                }

                textFieldOutput.setText(String.format("%.3f", model.getTemperature()));
            });
        });
    }
}