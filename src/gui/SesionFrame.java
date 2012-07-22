package gui;
import javax.swing.*;

public class SesionFrame extends JFrame {

    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField userField;
    private JPasswordField passField;
    private JButton okButton;
    private java.awt.Font font;

    private void iniciar() {
        if(
                (userField.getText().equals("choche")&&passField.getText().equals("pfmja"))||
                (userField.getText().equals("alfonso")&&passField.getText().equals("dacca"))||
                (userField.getText().equals("quique")&&passField.getText().equals("qq"))||
                (userField.getText().equals("admin")&&passField.getText().equals("bavs0212"))){
            new EmpleadoSeleccionarFrame(font);
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public SesionFrame(java.awt.Font font) {
        this.font=font;
        userLabel=new JLabel("Usuario");
        userLabel.setFont(font);
        passLabel = new JLabel("Contraseña");
        passLabel.setFont(font);
        userField = new JTextField("admin");
        userField.setFont(font);
        userField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userField.selectAll();
            }
        });
        userField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciar();
            }
        });
        passField = new JPasswordField();
        passField.setFont(font);
        passField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciar();
            }
        });
        okButton = new JButton("Enviar");
        okButton.setFont(font);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciar();
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                    .addComponent(userLabel)
                    .addComponent(passLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(userField)
                    .addComponent(passField)
                    .addComponent(okButton))
                .addContainerGap());
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(userField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passLabel)
                    .addComponent(passField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Iniciar sesión");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
