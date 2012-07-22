package gui;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class EmpleadoSeleccionarFrame extends JFrame {
    
    private JLabel empleadoLabel;
    private JLabel nominaLabel;
    private JLabel nombreLabel;
    private JLabel puestoLabel;
    private JSeparator s1;
    private JSeparator s2;
    private JTextField nombreField;
    private JTextField puestoField;
    private JCheckBox todosBox;
    private JButton buscarButton;
    private JButton agregarButton;
    private JButton consultarButton;
    private JButton editarButton;
    private JButton adeudoButton;
    private JButton nominaButton;
    private JButton consultaNominaButton;
    private JScrollPane empleadosPane;
    private JTable empleadosTable;
    private TableModel tableModel;
    private control.Manager manager;
    private java.awt.Font font;
    
    public void buscar() {
        int i=empleadosTable.getSelectedRow();
        tableModel.setRows(manager.selectEmpleados(nombreField.getText(), puestoField.getText(), todosBox.isSelected()));
        empleadosTable.changeSelection(i, 0, false, false);
    }
    
    private void editar(){
        int row = empleadosTable.getSelectedRow();
        if(row<0)JOptionPane.showMessageDialog(null, "Seleccione un empleado","Error",JOptionPane.ERROR_MESSAGE);
        else new EmpleadoEditarFrame(manager,font,manager.selectEmpleado((Integer)empleadosTable.getValueAt(row,0)),this);
    }
    
    private void adeudo(){
        int row = empleadosTable.getSelectedRow();
        if(row<0)JOptionPane.showMessageDialog(null, "Seleccione un empleado","Error",JOptionPane.ERROR_MESSAGE);
        else new AdeudoCrearFrame(manager,font,manager.selectEmpleado((Integer)empleadosTable.getValueAt(row,0)),this);
    }
    
    private void agregar(){
        new EmpleadoAgregarFrame(manager,font,this);
    }
    
    private void nomina(){
        domain.Nomina nomina=manager.selectNomina(new domain.Nomina().getSemana());
        if(nomina==null) new NominaCrearFrame(manager,font,this);
        else if(JOptionPane.showConfirmDialog(null,"La nómina ya ha sido generada, ¿desea editarla?","Confirmación",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)new NominaEditarFrame(manager,font,this,nomina);
    }
    
    private void consultaNomina(){
        new NominaSeleccionarFrame(manager,this,font);
    }
    
    private void consultar(){
        int row = empleadosTable.getSelectedRow();
        if(row<0)JOptionPane.showMessageDialog(null, "Seleccione un empleado","Error",JOptionPane.ERROR_MESSAGE);
        else new EmpleadoConsultarFrame(manager,font,manager.selectEmpleado((Integer)empleadosTable.getValueAt(row,0)));
    }
    
    public EmpleadoSeleccionarFrame(java.awt.Font font) {
        this.font = font;
        s1=new JSeparator();
        s2=new JSeparator();
        manager = new control.Manager();
        nombreLabel = new JLabel("Nombre");
        nombreLabel.setFont(font);
        empleadoLabel = new JLabel("Empleado");
        empleadoLabel.setFont(font);
        nominaLabel = new JLabel("Nomina");
        nominaLabel.setFont(font);
        puestoLabel = new JLabel("Puesto");
        puestoLabel.setFont(font);
        nombreField = new JTextField();
        nombreField.setFont(font);
        nombreField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreField.selectAll();
            }
        });
        puestoField = new JTextField();
        puestoField.setFont(font);
        puestoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                puestoField.selectAll();
            }
        });
        todosBox = new JCheckBox("mostrar inactivos");
        todosBox.setFont(font);
        buscarButton = new JButton("Buscar");
        buscarButton.setFont(font);
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar();
            }
        });
        agregarButton = new JButton("Agregar");
        agregarButton.setFont(font);
        agregarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar();
            }
        });
        consultarButton = new JButton("Consultar empleado");
        consultarButton.setFont(font);
        consultarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar();
            }
        });
        editarButton = new JButton("Cambios");
        editarButton.setFont(font);
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar();
            }
        });
        adeudoButton = new JButton("Adeudos");
        adeudoButton.setFont(font);
        adeudoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adeudo();
            }
        });
        nominaButton = new JButton("Generar");
        nominaButton.setFont(font);
        nominaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomina();
            }
        });
        consultaNominaButton = new JButton("Consultar nómina");
        consultaNominaButton.setFont(font);
        consultaNominaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaNomina();
            }
        });
        tableModel = new TableModel();
        empleadosTable = new JTable(tableModel);
        empleadosTable.setShowVerticalLines(false);
        empleadosTable.setFont(font);
        empleadosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        empleadosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                if(evt.getClickCount()==2)consultar();
            }
        });
        empleadosPane = new JScrollPane(empleadosTable);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombreLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreField, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(puestoLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(puestoField, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(todosBox)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(empleadosPane, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(s1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                            .addComponent(empleadoLabel)
                            .addComponent(agregarButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                            .addComponent(consultarButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                            .addComponent(editarButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                            .addComponent(adeudoButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                            .addComponent(s2, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                            .addComponent(nominaLabel)
                            .addComponent(nominaButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                            .addComponent(consultaNominaButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap());
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(nombreField)
                    .addComponent(puestoLabel)
                    .addComponent(puestoField)
                    .addComponent(todosBox)
                    .addComponent(buscarButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup()
                    .addComponent(empleadosPane, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(s1, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(empleadoLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agregarButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consultarButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editarButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adeudoButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(s2, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nominaLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nominaButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consultaNominaButton)))
                .addContainerGap());
        empleadosTable.setRowHeight(24);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Properties estacionamiento = new Properties();
        try {
            estacionamiento.load(new FileInputStream("estacionamiento.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle("Sistema de nómina estacionamiento "+estacionamiento.getProperty("nombre",""));
        setExtendedState(MAXIMIZED_BOTH);
        buscar();
        setVisible(true);
    }
    
}
class TableModel extends javax.swing.table.DefaultTableModel {

    public TableModel() {
        super(new String[]{"Empleado", "Nombre", "Puesto","Estado"},0);
    }
    
    public void setRows(List<domain.Empleado> empleados){
        setRowCount(0);
        int i = 0;
        int maxi = empleados.size();
        while(i<maxi){
            addRow(empleados.get(i).getRow());
            i++;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    

}
