

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Frame extends JFrame {

    JLabel imageLabel;
    ButtonClickListener listener;
    public Frame(){
        super("Files Collector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(500,500);
    }


    public void showImage(String path){
        if(this.imageLabel != null){
            remove(imageLabel);
        }
        ImageIcon image = new ImageIcon(path);
        this.imageLabel = new JLabel(image);
        add(this.imageLabel);
        this.imageLabel.setBounds(0, 0, 500, 500);
        this.imageLabel.setVisible(true);
        setSize(image.getIconWidth() + 100, image.getIconHeight());
        repaint();
        revalidate();

    }

    public void close(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void generateButtons(String[] categories){
        int index = 0;
        setLayout(new GridBagLayout());
        JPanel btnPanel = new JPanel(new GridLayout(categories.length, 1, 10, 5));
        for(String c : categories){

            JButton btn = new JButton();
            btn.setText(c);

            index += 1;
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(listener != null){
                        listener.onButtonClick(c);
                    }
                }
            });
            btnPanel.add(btn);

            repaint();
            revalidate();
        }
            this.add(btnPanel);

        repaint();
        revalidate();
    }

    public ButtonClickListener getListener() {
        return listener;
    }

    public void setListener(ButtonClickListener listener) {
        this.listener = listener;
    }
}
