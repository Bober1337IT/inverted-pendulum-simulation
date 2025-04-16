package edu.lab13;

import observer.CConsoleObserver;
import observer.CFileObserver;
import observer.CJListObserver;
import solver.CSolver;
import solver.CSolverCreator;
import solver.CStepData;
import solver.ESolverType;

import javax.swing.*;
import java.awt.*;

public class CMainForm extends javax.swing.JFrame {
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuOptions;
    private JMenuItem itemExit;
    private JMenuItem itemSolve;
    private JMenuItem itemAbout;
    private JRadioButton rbFirst;
    private JRadioButton rbSecond;
    private JRadioButton rbFourth;
    private JTextField tkTextField;
    private JTextField alphaTextField;
    private JTextField omegaTextField;
    private JCheckBox cbPanel;
    private JCheckBox cbConsole;
    private JCheckBox cbFile;
    private JList list1;
    private JPanel mainPanel;

    protected DefaultListModel<Object> model;

    public CMainForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        list1.setModel(model);

        itemExit.addActionListener(actionEvent -> CMainForm.this.dispose());
        itemAbout.addActionListener(actionEvent -> JOptionPane.showMessageDialog(
                CMainForm.this, "Programowanie obiektowe\nlaboratorium 11",
                "O programie", JOptionPane.INFORMATION_MESSAGE));
        itemSolve.addActionListener(actionEvent -> solveActionPerformed());
    }

    private void solveActionPerformed() {
        CSolverCreator sc = new CSolverCreator();

        ESolverType st = ESolverType.FIRST_ORDER;
        if (rbSecond.isSelected()) st = ESolverType.SECOND_ORDER;
        else if (rbFourth.isSelected()) st = ESolverType.FOURTH_ORDER;

        CStepData init;
        try {
            double tk = Double.parseDouble(tkTextField.getText().trim());
            double alpha = Double.parseDouble(alphaTextField.getText().trim());
            double omega = Double.parseDouble(omegaTextField.getText().trim());

            init = new CStepData(tk, alpha, omega);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Błąd danych wejściowych",
                    "",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        CSolver solverObj = sc.getSolver(st, init);

        if (cbPanel.isSelected()) {
            solverObj.addObserver(new CJListObserver(model));
        }
        if (cbConsole.isSelected()) {
            solverObj.addObserver(new CConsoleObserver());
        }
        if (cbFile.isSelected()) {
            solverObj.addObserver(new CFileObserver());
        }

        solverObj.solve();

    }


}
