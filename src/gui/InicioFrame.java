package gui;
import javax.swing.*;

public class InicioFrame extends JFrame {

    private JButton iniciarButton;
    private JLabel label0;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel logo;
    java.awt.Font font;

    private void iniciar() {
        new SesionFrame(font);
        dispose();
    }
    
    public InicioFrame() {
        font=new java.awt.Font("Arial",java.awt.Font.PLAIN,12);
        label0 = new JLabel("Bienvenidos al Sistema de Nómina");
        label1 = new JLabel("Copyrights 2009 V 1.5");
        label2 = new JLabel("Derechos Reservados");
        label3 = new JLabel("Lic. José Salvador Barba Vargas");
        iniciarButton = new JButton();
        iniciarButton.setText("Iniciar");
        iniciarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciar();
            }
        });
        label0.setFont(font);
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        iniciarButton.setFont(font);
        logo = new JLabel(new ImageIcon("logo.JPG"));
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(label0)
                    .addComponent(label1)
                    .addComponent(label2)
                    .addComponent(label3)
                    .addComponent(iniciarButton))
                .addContainerGap());
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(logo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label0)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iniciarButton)))
                .addContainerGap());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenido");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String args[]) {
        new InicioFrame();
    }
    
}
