/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */import java.awt.HeadlessException;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author doğukan
 */
public class Frame extends JFrame{
    public static void main(String[] args) {
        Frame oyun=new Frame("Dinazor");
        
        oyun.setResizable(false);
        oyun.setFocusable(false);
        oyun.setSize(800,700);
        oyun.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel=new Panel();
        panel.requestFocus();
        panel.addKeyListener(panel);
        panel.setFocusable(true);
        panel.setFocusTraversalKeysEnabled(false);
        oyun.add(panel);
        oyun.setVisible(true);
    }

    public Frame(String title) throws HeadlessException {
        super(title);
    }
    
}

