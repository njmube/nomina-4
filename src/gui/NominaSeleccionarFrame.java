package gui;
import javax.swing.*;

public class NominaSeleccionarFrame extends JFrame {
    
    JList nominaBox;
    JScrollPane nominaPane;
    JButton okButton;
    java.awt.Font font;
    control.Manager manager;
    EmpleadoSeleccionarFrame principal;
    
    void consultar(){
        domain.Nomina nomina=(domain.Nomina)nominaBox.getSelectedValue();
        if(nomina!=null){
            new NominaConsultarFrame(manager,font,principal,nomina);
            dispose();
        }else JOptionPane.showMessageDialog(null,"Seleccione una nómina","Error",JOptionPane.ERROR_MESSAGE);
    }

    public NominaSeleccionarFrame(control.Manager manager,EmpleadoSeleccionarFrame principal,java.awt.Font font){
        this.font=font;
        this.principal=principal;
        this.manager=manager;
        nominaBox = new JList(new java.util.Vector<domain.Nomina>(manager.selectNominas()));
        nominaBox.setFont(font);
        nominaBox.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        nominaBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                if(evt.getClickCount()==2)consultar();
            }
        });
        nominaPane=new JScrollPane(nominaBox);
        okButton = new JButton("Consultar");
        okButton.setFont(font);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar();
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(nominaPane)
                    .addComponent(okButton,GroupLayout.PREFERRED_SIZE,128,GroupLayout.PREFERRED_SIZE))
                .addContainerGap());
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nominaPane,GroupLayout.DEFAULT_SIZE,128,Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccionar nómina");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
