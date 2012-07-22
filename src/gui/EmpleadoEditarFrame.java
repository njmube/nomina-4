package gui;

import util.DateFormat;

public class EmpleadoEditarFrame extends javax.swing.JFrame {
    
    private javax.swing.JLabel pesosSueldoLabel;
    private javax.swing.JLabel pesosInfonavitLabel;
    private javax.swing.JLabel pesosDescuentoLabel;
    private javax.swing.JLabel pesosSaldoLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JLabel direccionLabel;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JLabel puestoLabel;
    private javax.swing.JLabel sueldoLabel;
    private javax.swing.JLabel infonavitLabel;
    private javax.swing.JLabel descuentoLabel;
    private javax.swing.JLabel saldoLabel;
    private javax.swing.JLabel altaLabel;
    private javax.swing.JLabel estadoLabel;
    private javax.swing.JLabel idField;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField direccionField;
    private javax.swing.JTextField telefonoField;
    private javax.swing.JTextField puestoField;
    private javax.swing.JTextField sueldoField;
    private javax.swing.JTextField infonavitField;
    private javax.swing.JTextField descuentoField;
    private javax.swing.JLabel saldoField;
    private javax.swing.JLabel altaField;
    private javax.swing.JLabel estadoField;
    private javax.swing.JCheckBox accionBox;
    private javax.swing.JButton editarButton;
    private control.Manager manager;
    private domain.Empleado empleado;
    private EmpleadoSeleccionarFrame frame;
    
    private void editar(){
        boolean accion=accionBox.isSelected();
        if(accion&&javax.swing.JOptionPane.showConfirmDialog(null, "Está realizando un cambio de estado en el empleado "+empleado.getNombre()+" (id "+empleado.getId()+"), ¿está seguro de continuar?", "Confirmación", javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.WARNING_MESSAGE)!=javax.swing.JOptionPane.YES_OPTION)return;
        String temp;
        temp = nombreField.getText();
        if(temp.length()==0){
            javax.swing.JOptionPane.showMessageDialog(null, "Nombre vacío","Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        empleado.setNombre(temp);
        temp = puestoField.getText();
        if(temp.length()==0){
            javax.swing.JOptionPane.showMessageDialog(null, "Puesto vacío","Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        empleado.setPuesto(temp);
        try{
            empleado.setSueldo(Float.parseFloat(sueldoField.getText()));
        }catch(NumberFormatException e){
            javax.swing.JOptionPane.showMessageDialog(null, "Sueldo inválido","Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            empleado.setInfonavit(Float.parseFloat(infonavitField.getText()));
        }catch(NumberFormatException e){
            javax.swing.JOptionPane.showMessageDialog(null, "Infonavit inválido","Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            empleado.setDescuento(Float.parseFloat(descuentoField.getText()));
        }catch(NumberFormatException e){
            javax.swing.JOptionPane.showMessageDialog(null, "Descuento inválido","Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        empleado.setDireccion(direccionField.getText());
        empleado.setTelefono(telefonoField.getText());
        manager.updateEmpleado(empleado,accion);
        javax.swing.JOptionPane.showMessageDialog(null, "Cambios guardados","Éxito",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        frame.buscar();
        dispose();
    }

    public EmpleadoEditarFrame(control.Manager manager, java.awt.Font font, domain.Empleado empleado,EmpleadoSeleccionarFrame frame) {
        this.frame = frame;
        this.manager = manager;
        this.empleado = empleado;
        pesosSueldoLabel = new javax.swing.JLabel("$");
        pesosSueldoLabel.setFont(font);
        pesosInfonavitLabel = new javax.swing.JLabel("$");
        pesosInfonavitLabel.setFont(font);
        pesosDescuentoLabel = new javax.swing.JLabel("$");
        pesosDescuentoLabel.setFont(font);
        pesosSaldoLabel = new javax.swing.JLabel("$");
        pesosSaldoLabel.setFont(font);
        idLabel = new javax.swing.JLabel("Empleado");
        idLabel.setFont(font);
        nombreLabel = new javax.swing.JLabel("Nombre");
        nombreLabel.setFont(font);
        direccionLabel = new javax.swing.JLabel("Dirección");
        direccionLabel.setFont(font);
        telefonoLabel = new javax.swing.JLabel("Teléfono");
        telefonoLabel.setFont(font);
        puestoLabel = new javax.swing.JLabel("Puesto");
        puestoLabel.setFont(font);
        sueldoLabel = new javax.swing.JLabel("Sueldo");
        sueldoLabel.setFont(font);
        infonavitLabel = new javax.swing.JLabel("Infonavit");
        infonavitLabel.setFont(font);
        descuentoLabel = new javax.swing.JLabel("Descuento");
        descuentoLabel.setFont(font);
        saldoLabel = new javax.swing.JLabel("Saldo");
        saldoLabel.setFont(font);
        altaLabel = new javax.swing.JLabel("Alta");
        altaLabel.setFont(font);
        estadoLabel = new javax.swing.JLabel("Estado");
        estadoLabel.setFont(font);
        idField = new javax.swing.JLabel(empleado.getId()+"");
        idField.setFont(font);
        nombreField = new javax.swing.JTextField(empleado.getNombre());
        nombreField.setFont(font);
        nombreField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreField.selectAll();
            }
        });
        direccionField = new javax.swing.JTextField(empleado.getDireccion());
        direccionField.setFont(font);
        direccionField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                direccionField.selectAll();
            }
        });
        telefonoField = new javax.swing.JTextField(empleado.getTelefono());
        telefonoField.setFont(font);
        telefonoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                telefonoField.selectAll();
            }
        });
        puestoField = new javax.swing.JTextField(empleado.getPuesto());
        puestoField.setFont(font);
        puestoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                puestoField.selectAll();
            }
        });
        sueldoField = new javax.swing.JTextField(empleado.getSueldo()+"");
        sueldoField.setFont(font);
        sueldoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sueldoField.selectAll();
            }
        });
        infonavitField = new javax.swing.JTextField(empleado.getInfonavit()+"");
        infonavitField.setFont(font);
        infonavitField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                infonavitField.selectAll();
            }
        });
        descuentoField = new javax.swing.JTextField(empleado.getDescuento()+"");
        descuentoField.setFont(font);
        descuentoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descuentoField.selectAll();
            }
        });
        saldoField = new javax.swing.JLabel(empleado.getSaldo()+"");
        saldoField.setFont(font);
        altaField = new javax.swing.JLabel(DateFormat.format(empleado.getAlta()));
        altaField.setFont(font);
        estadoField = new javax.swing.JLabel(empleado.getEstado());
        estadoField.setFont(font);
        accionBox = new javax.swing.JCheckBox("Cambiar estado");
        accionBox.setFont(font);
        editarButton = new javax.swing.JButton("Guardar");
        editarButton.setFont(font);
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar();
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                    .addComponent(idLabel)
                    .addComponent(nombreLabel)
                    .addComponent(direccionLabel)
                    .addComponent(telefonoLabel)
                    .addComponent(puestoLabel)
                    .addComponent(sueldoLabel)
                    .addComponent(infonavitLabel)
                    .addComponent(descuentoLabel)
                    .addComponent(saldoLabel)
                    .addComponent(altaLabel)
                    .addComponent(estadoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idField)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(direccionField, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(puestoField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosSueldoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sueldoField, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosInfonavitLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infonavitField, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosDescuentoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descuentoField, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosSaldoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saldoField))
                    .addComponent(altaField)
                    .addComponent(estadoField)
                    .addComponent(accionBox)
                    .addComponent(editarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap());
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(nombreField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(direccionLabel)
                    .addComponent(direccionField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefonoLabel)
                    .addComponent(telefonoField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(puestoLabel)
                    .addComponent(puestoField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sueldoLabel)
                    .addComponent(pesosSueldoLabel)
                    .addComponent(sueldoField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(infonavitLabel)
                    .addComponent(pesosInfonavitLabel)
                    .addComponent(infonavitField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descuentoLabel)
                    .addComponent(pesosDescuentoLabel)
                    .addComponent(descuentoField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saldoLabel)
                    .addComponent(pesosSaldoLabel)
                    .addComponent(saldoField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(altaLabel)
                    .addComponent(altaField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estadoLabel)
                    .addComponent(estadoField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accionBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarButton)
                .addContainerGap());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
