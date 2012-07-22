package gui;

import util.DateFormat;

public class EmpleadoConsultarFrame extends javax.swing.JFrame {
    
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
    private javax.swing.JLabel nombreField;
    private javax.swing.JLabel direccionField;
    private javax.swing.JLabel telefonoField;
    private javax.swing.JLabel puestoField;
    private javax.swing.JLabel sueldoField;
    private javax.swing.JLabel infonavitField;
    private javax.swing.JLabel descuentoField;
    private javax.swing.JLabel saldoField;
    private javax.swing.JLabel altaField;
    private javax.swing.JLabel estadoField;
    
    public EmpleadoConsultarFrame(control.Manager manager, java.awt.Font font, domain.Empleado empleado) {
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
        nombreField = new javax.swing.JLabel(empleado.getNombre());
        nombreField.setFont(font);
        direccionField = new javax.swing.JLabel(empleado.getDireccion());
        direccionField.setFont(font);
        telefonoField = new javax.swing.JLabel(empleado.getTelefono());
        telefonoField.setFont(font);
        puestoField = new javax.swing.JLabel(empleado.getPuesto());
        puestoField.setFont(font);
        sueldoField = new javax.swing.JLabel(empleado.getSueldo()+"");
        sueldoField.setFont(font);
        infonavitField = new javax.swing.JLabel(empleado.getInfonavit()+"");
        infonavitField.setFont(font);
        descuentoField = new javax.swing.JLabel(empleado.getDescuento()+"");
        descuentoField.setFont(font);
        saldoField = new javax.swing.JLabel(empleado.getSaldo()+"");
        saldoField.setFont(font);
        altaField = new javax.swing.JLabel(DateFormat.format(empleado.getAlta()));
        altaField.setFont(font);
        estadoField = new javax.swing.JLabel(empleado.getEstado());
        estadoField.setFont(font);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idField)
                    .addComponent(nombreField)
                    .addComponent(direccionField)
                    .addComponent(telefonoField)
                    .addComponent(puestoField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosSueldoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sueldoField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosInfonavitLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infonavitField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosDescuentoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descuentoField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosSaldoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saldoField))
                    .addComponent(altaField)
                    .addComponent(estadoField))
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
                .addContainerGap());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
