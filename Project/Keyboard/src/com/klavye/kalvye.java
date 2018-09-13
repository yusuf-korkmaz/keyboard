package com.klavye;

import com.klavye.util.ArtificialNeuronNetworkUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by yusufkorkmaz on 31.11.2017.
 */
public class kalvye extends JFrame{
    ArtificialNeuronNetworkUtils a = new ArtificialNeuronNetworkUtils();


    private JTextField textField1;
    private JPanel rootpanel;
    private JButton button1;
    private JTextField textField2;
    private JButton logInButton;
    private Long current =System.currentTimeMillis();
    String currentString;
    private String cümle;
    private char karakter_birinci;
    private char karakter_ikinci;

    kalvye(){
        super("Learning Sentence");
        setContentPane(rootpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        cümle = "" ;

        textField1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyChar() == KeyEvent.VK_ENTER )
                {

                    currentString = textField1.getText();
                    textField1.setText("");

                    karakter_birinci=' ';
                    karakter_ikinci=' ';

                    try{Test();}catch(Exception ex){};
                    System.out.println(a.getNeuronNetwork().getThreshold() + "--" + a.getThresholdForThisString(cümle));
                    if(a.getNeuronNetwork().getThreshold() < a.getThresholdForThisString(cümle)){
                        JOptionPane.showMessageDialog(rootPane,"Successful \nThreshold must be " +a.getNeuronNetwork().getThreshold() +" but your treshold is" + a.getThresholdForThisString(cümle));
                        System.out.println("Başarılı");
                    }else {
                        JOptionPane.showMessageDialog(rootPane,"!!!! Unfortunate !!!! \nThreshold must be" +a.getNeuronNetwork().getThreshold() +" but your treshold is " + a.getThresholdForThisString(cümle));
                    }

                    cümle += "\n";
                    a.dataWrite(currentString,cümle);

                    cümle = "" ;

                }
                else {
                    karakter_birinci = karakter_ikinci;
                    karakter_ikinci = e.getKeyChar();
                }
                if(textField1.getText().length()==0)
                {
                    current = System.currentTimeMillis();
                    karakter_birinci=e.getKeyChar();
                }
                else {
                    cümle += karakter_birinci + "-" + karakter_ikinci + "-" + (System.currentTimeMillis() - current) + "/";
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                current = System.currentTimeMillis();
                karakter_birinci=e.getKeyChar();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Test();


                /*
                if(a.getNeuronNetwork().getThreshold() < a.getThresholdForThisString(cümle)){
                    JOptionPane.showMessageDialog(rootPane,"Olması gerek Threshold " +a.getNeuronNetwork().getThreshold() +" sizin thresholdunuz " + a.getThresholdForThisString(cümle));
                    System.out.println("Başarılı");
                }
                */
            }
        });

    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public void Test(){
        a.getNeuronNetwork().getNeurons().clear();
        a.toString(currentString);
        System.out.println(" ");
        a.calculateTime();
        System.out.println("Threshold");
        a.calculateTreshold(currentString);

    }
}
