package gui;

public class EmpleadoAgregarFrame extends javax.swing.JFrame {
    
    private javax.swing.JLabel pesosLabel;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JLabel direccionLabel;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JLabel puestoLabel;
    private javax.swing.JLabel sueldoLabel;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField direccionField;
    private javax.swing.JTextField telefonoField;
    private javax.swing.JTextField puestoField;
    private javax.swing.JTextField sueldoField;
    private javax.swing.JButton agregarButton;
    private control.Manager manager;
    private EmpleadoSeleccionarFrame frame;
    
    private void agregar(){
        String nombre = nombreField.getText();
        if(nombre.length()==0){
            javax.swing.JOptionPane.showMessageDialog(null, "Nombre vacío","Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        String puesto = puestoField.getText();
        if(puesto.length()==0){
            javax.swing.JOptionPane.showMessageDialog(null, "Puesto vacío","Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        float sueldo;
        try{
            sueldo = Float.parseFloat(sueldoField.getText());
        }catch(NumberFormatException e){
            javax.swing.JOptionPane.showMessageDialog(null, "Sueldo inválido","Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        javax.swing.JOptionPane.showMessageDialog(null, "Empleado registrado con el id " + manager.insertEmpleado(nombre,direccion,telefono,puesto,sueldo),"Éxito",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        frame.buscar();
        dispose();
    }

    public EmpleadoAgregarFrame(control.Manager manager, java.awt.Font font,EmpleadoSeleccionarFrame frame) {
        this.frame = frame;
        this.manager = manager;
        pesosLabel = new javax.swing.JLabel("$");
        pesosLabel.setFont(font);
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
        nombreField = new javax.swing.JTextField();
        nombreField.setFont(font);
        nombreField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreField.selectAll();
            }
        });
        direccionField = new javax.swing.JTextField();
        direccionField.setFont(font);
        direccionField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                direccionField.selectAll();
            }
        });
        telefonoField = new javax.swing.JTextField();
        telefonoField.setFont(font);
        telefonoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                telefonoField.selectAll();
            }
        });
        puestoField = new javax.swing.JTextField();
        puestoField.setFont(font);
        puestoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                puestoField.selectAll();
            }
        });
        sueldoField = new javax.swing.JTextField();
        sueldoField.setFont(font);
        sueldoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sueldoField.selectAll();
            }
        });
        agregarButton = new javax.swing.JButton("Registrar");
        agregarButton.setFont(font);
        agregarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar();
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                    .addComponent(nombreLabel)
                    .addComponent(direccionLabel)
                    .addComponent(telefonoLabel)
                    .addComponent(puestoLabel)
                    .addComponent(sueldoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(direccionField, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(puestoField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sueldoField, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(agregarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap());
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(pesosLabel)
                    .addComponent(sueldoField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agregarButton)
                .addContainerGap());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo empleado");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
