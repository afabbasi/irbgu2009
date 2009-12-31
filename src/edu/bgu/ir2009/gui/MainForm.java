package edu.bgu.ir2009.gui;

import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

/**
 * User: Henry Abravanel 310739693 henrya@bgu.ac.il
 * Date: 29/12/2009
 * Time: 23:53:01
 */
public class MainForm {

    private JPanel root;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem indexDirMenuItem;

    public MainForm() {
        initMenuBar();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Information Retrieval");
        MainForm form = new MainForm();
        frame.setJMenuBar(form.menuBar);
        frame.setContentPane(form.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();

        //Create file menu
        fileMenu = new JMenu("File");
        indexDirMenuItem = new JMenuItem("Index Directory...");
        fileMenu.add(indexDirMenuItem);
        menuBar.add(fileMenu);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        root = new JPanel();
        root.setLayout(new FormLayout("", ""));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }
}