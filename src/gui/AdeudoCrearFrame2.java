package gui;

import control.Manager;
import domain.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;

public class AdeudoCrearFrame2 extends JFrame{
    
    private JLabel pesosLabel;
    private JLabel idLabel;
    private JLabel nombreLabel;
    private JLabel conceptoLabel;
    private JLabel cantidadLabel;
    private JLabel idField;
    private JLabel nombreField;
    private JComboBox conceptoBox;
    private JTextField cantidadField;
    private JButton agregarButton;
    private Manager manager;
    private Empleado empleado;
    private NominaCrearFrame frame;
    int index;
    
    private void agregar(){
        float cantidad;
        try{
            cantidad = Float.parseFloat(cantidadField.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Cantidad inválida","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        Adeudo adeudo=new Adeudo();
        adeudo.setCantidad(cantidad);
        adeudo.setConcepto(conceptoBox.getSelectedItem()+"");
        adeudo.setEmpleado(empleado.getId());
        JOptionPane.showMessageDialog(null, "Adeudo registrado","Éxito",JOptionPane.INFORMATION_MESSAGE);
        frame.insert(adeudo,index);
        dispose();
    }

    AdeudoCrearFrame2(Manager manager, Font font, Empleado empleado,NominaCrearFrame frame,int index) {
        this.index=index;
        this.frame=frame;
        this.manager=manager;
        this.empleado=empleado;
        pesosLabel=new JLabel("$");
        pesosLabel.setFont(font);
        idLabel=new JLabel("Empleado");
        idLabel.setFont(font);
        nombreLabel=new JLabel("Nombre");
        nombreLabel.setFont(font);
        conceptoLabel=new JLabel("Concepto");
        conceptoLabel.setFont(font);
        cantidadLabel=new JLabel("Monto");
        cantidadLabel.setFont(font);
        idField=new JLabel(empleado.getId()+"");
        idField.setFont(font);
        nombreField=new JLabel(empleado.getNombre()+"");
        nombreField.setFont(font);
        conceptoBox=new JComboBox(new String[]{"Golpe","Préstamo"});
        conceptoBox.setFont(font);
        cantidadField=new JTextField();
        cantidadField.setFont(font);
        cantidadField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                cantidadField.selectAll();
            }
        });
        agregarButton = new JButton("Registrar");
        agregarButton.setFont(font);
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                agregar();
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                    .addComponent(idLabel)
                    .addComponent(nombreLabel)
                    .addComponent(conceptoLabel)
                    .addComponent(cantidadLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(idField)
                    .addComponent(nombreField)
                    .addComponent(conceptoBox, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadField, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                    .addComponent(agregarButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                .addContainerGap());
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(nombreField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(conceptoLabel)
                    .addComponent(conceptoBox))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(pesosLabel)
                    .addComponent(cantidadLabel)
                    .addComponent(cantidadField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agregarButton)
                .addContainerGap());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo adeudo");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
