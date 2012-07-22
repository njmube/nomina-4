package gui;

import domain.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

class NominaCrearFrame extends JFrame {

    private JLabel pesosSueldoLabel;
    private JLabel pesosInfonavitLabel;
    private JLabel pesosSaldoLabel;
    private JLabel pesosDescuentoLabel;
    private JLabel pesosValeLabel;
    private JLabel pesosGolpesLabel;
    private JLabel pesosPrestamosLabel;
    private JLabel pesosTotalLabel;
    private JLabel idLabel;
    private JLabel nombreLabel;
    private JLabel sueldoLabel;
    private JLabel faltasLabel;
    private JLabel infonavitLabel;
    private JLabel saldoLabel;
    private JLabel descuentoLabel;
    private JLabel valeLabel;
    private JLabel golpesLabel;
    private JLabel prestamosLabel;
    private JLabel totalLabel;
    private JLabel idField;
    private JLabel nombreField;
    private JLabel sueldoField;
    private JTextField faltasField;
    private JTextField infonavitField;
    private JLabel saldoField;
    private JTextField descuentoField;
    private JTextField valeField;
    private JLabel golpesField;
    private JLabel prestamosField;
    private JLabel totalField;
    private JButton siguienteButton;
    private JButton anteriorButton;
    private control.Manager manager;
    private Nomina nomina;
    private List<Empleado> empleados;
    private List<Pago> pagos;
    private List<Adeudo> adeudos;
    private EmpleadoSeleccionarFrame frame;
    private Format format;
    private int index;
    private JLabel pesosSaldoNuevoLabel;
    private JLabel saldoNuevoLabel;
    private JLabel saldoNuevoField;
    private JButton adeudoButton;
    private List<Adeudo> adeudos2;
    private Font font;

    void insert(Adeudo adeudo,int i) {
        adeudos2.add(adeudo);
        pagos.get(i).addAdeudo(adeudo);
        if(i==index){
            Pago pago = pagos.get(index);
            saldoField.setText(format.format(pago.getSaldoAnterior()));
            golpesField.setText(format.format(pago.getGolpes()));
            prestamosField.setText(format.format(pago.getPrestamos()));
            try {
                float descuento = Float.parseFloat(descuentoField.getText());
                saldoNuevoField.setText(format.format(pago.getSaldoAnterior()-descuento));
            } catch (NumberFormatException e) {
                saldoNuevoField.setText("");
            }
        }
    }

    private void adeudo(){
        new AdeudoCrearFrame2(manager, font, empleados.get(index),this,index);
    }
    
    private void calcularTotal() {
        int faltas;
        try {
            faltas = Integer.parseInt(faltasField.getText());
        } catch (NumberFormatException e) {
            totalField.setText("");
            return;
        }
        float descuento = 0;
        try {
            descuento = Float.parseFloat(descuentoField.getText());
        } catch (NumberFormatException e) {
            saldoNuevoField.setText("");
            totalField.setText("");
            return;
        }
        float infonavit = 0;
        try {
            infonavit = Float.parseFloat(infonavitField.getText());
        } catch (NumberFormatException e) {
            totalField.setText("");
            return;
        }
        float vale = 0;
        try {
            vale = Float.parseFloat(valeField.getText());
        } catch (NumberFormatException e) {
            totalField.setText("");
            return;
        }
        saldoNuevoField.setText(format.format(pagos.get(index).getSaldoAnterior()-descuento));
        totalField.setText(format.format(empleados.get(index).getSueldo() * ((float) (7 - faltas) / 7) - infonavit - descuento - vale));
    }
    
    private void finish(){
        Properties estacionamiento = new Properties();
        try {
            estacionamiento.load(new FileInputStream("estacionamiento.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s="";
        format = new DecimalFormat(" $ 0.00");
        int i=0;
        int maxi=pagos.size();
        nomina.setTotal(0);
        while(i<maxi){
            Pago pago = pagos.get(i);
            nomina.addTotal(pago.getSubtotal());
            s += nomina.toString() + "\n";
            s += "Empleado      : (" + pago.getEmpleado() + ") - " + empleados.get(i).getNombre() + "\n";
            s += "                                  Sueldo   :" + format.format(pago.getSueldo()) + "\n";
            String temp = format.format(pago.getSaldoAnterior());
            while (temp.length() < 19) {
                temp += " ";
            }
            s += "Saldo anterior:" + temp + "Faltas   :   " + pago.getFaltas() + "\n";
            temp = format.format(pago.getGolpes());
            while (temp.length() < 19) {
                temp += " ";
            }
            s += "Golpes        :" + temp + "Infonavit:" + format.format(pago.getInfonavit()) + "\n";
            temp = format.format(pago.getPrestamos());
            while (temp.length() < 19) {
                temp += " ";
            }
            s += "Prestamos     :" + temp + "Descuento:" + format.format(pago.getDescuento()) + "\n";
            temp = format.format(pago.getSaldoAnterior() - pago.getDescuento());
            while (temp.length() < 19) {
                temp += " ";
            }
            s += "Saldo actual  :" + temp + "Vale     :" + format.format(pago.getVale()) + "\n";
            s += "                                  Total    :" + format.format(pago.getSubtotal()) + "\n";
            s += "--------------------------------------------------------------\n";
            i++;
        }
        s += "Estacionamiento " + estacionamiento.getProperty("nombre", "") + "\n";
        s+="\n";
        s += nomina.toString() + "\n";
        s+="\n";
        Calendar now = Calendar.getInstance();
        s += "Generada el " + util.DateFormat.format(now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH)) + "\n";
        s+="\n";
        s += "Total de la nomina: " + format.format(nomina.getTotal());
        byte[] b = s.getBytes();
        try {
            OutputStream out = new FileOutputStream(nomina.getFile());
            out.write(b);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new PrintNomina(s);
        manager.insertNomina(nomina);
        while(0<adeudos2.size()){
            Adeudo adeudo=adeudos2.remove(0);
            manager.insertAdeudo(adeudo);
            adeudos.add(adeudo);
        }
        manager.insertPagos(nomina.getSemana(), pagos);
        manager.updateAdeudos(nomina.getSemana(), adeudos);
        JOptionPane.showMessageDialog(null, nomina.toString() + ":" + format.format(nomina.getTotal()), "Aviso", JOptionPane.INFORMATION_MESSAGE);
        frame.buscar();
        dispose();
    }

    private void load(int index) {
        if(index==pagos.size()){
            finish();
            return;
        }
        if(index==0)anteriorButton.setVisible(false);
        else anteriorButton.setVisible(true);
        if(index==pagos.size()-1)siguienteButton.setText("Finalizar");
        else siguienteButton.setText("Siguiente");
        Empleado empleado = empleados.get(index);
        Pago pago = pagos.get(index);
        idField.setText(pago.getEmpleado() + "");
        nombreField.setText(empleado.getNombre());
        sueldoField.setText(format.format(pago.getSueldo()));
        faltasField.setText(pago.getFaltas()+"");
        infonavitField.setText(format.format(pago.getInfonavit()));
        saldoField.setText(format.format(pago.getSaldoAnterior()));
        descuentoField.setText(format.format(pago.getDescuento()));
        valeField.setText(format.format(pago.getVale()));
        golpesField.setText(format.format(pago.getGolpes()));
        prestamosField.setText(format.format(pago.getPrestamos()));
        calcularTotal();
    }

    private boolean update(int index) {
        int faltas;
        try {
            faltas = Integer.parseInt(faltasField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Número de faltas inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        float infonavit = 0;
        try {
            infonavit = Float.parseFloat(infonavitField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Infonavit inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        float descuento = 0;
        try {
            descuento = Float.parseFloat(descuentoField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Descuento inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Pago pago = pagos.get(index);
        if (pago.getSaldoAnterior() < descuento) {
            JOptionPane.showMessageDialog(null, "No puedes descontar más del saldo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        float vale = 0;
        try {
            vale = Float.parseFloat(valeField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vale inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        pago.setDescuento(descuento);
        pago.setFaltas(faltas);
        pago.setInfonavit(infonavit);
        pago.setVale(vale);
        return true;
    }

    public NominaCrearFrame(control.Manager manager, Font font, EmpleadoSeleccionarFrame frame) {
        this.frame = frame;
        this.manager = manager;
        this.font = font;
        format=new DecimalFormat("0.00");
        nomina = new Nomina();
        adeudos = new Vector<domain.Adeudo>();
        adeudos2 = new Vector<domain.Adeudo>();
        empleados = manager.selectEmpleados("", "", false);
        pagos = new LinkedList<Pago>();
        int i=0;
        int maxi=empleados.size();
        while(i<maxi){
            Empleado empleado=empleados.get(i);
            Pago pago=new Pago();
            pago.setDescuento(empleado.getDescuento());
            pago.setEmpleado(empleado.getId());
            pago.setFaltas(0);
            List<Adeudo> adeudosTemp;
            float adeudo;
            int j;
            int maxj;
            adeudosTemp = manager.selectAdeudos(empleado.getId(), 0, false);
            j = 0;
            maxj = adeudosTemp.size();
            adeudo = 0;
            while (j < maxj) {
                adeudo += adeudosTemp.get(j).getCantidad();
                j++;
            }
            adeudos.addAll(adeudosTemp);
            pago.setGolpes(adeudo);
            adeudosTemp = manager.selectAdeudos(empleado.getId(), 1, false);
            j = 0;
            maxj = adeudosTemp.size();
            adeudo = 0;
            while (j < maxj) {
                adeudo += adeudosTemp.get(j).getCantidad();
                j++;
            }
            adeudos.addAll(adeudosTemp);
            pago.setPrestamos(adeudo);
            pago.setInfonavit(empleado.getInfonavit());
            pago.setNomina(nomina.getSemana());
            pago.setSueldo(empleado.getSueldo());
            pago.setVale(0);
            pago.setSaldoAnterior(empleado.getSaldo());
            pago.setDescuento(empleado.getSaldo() < empleado.getDescuento() ? empleado.getSaldo() : empleado.getDescuento());
            pagos.add(pago);
            i++;
        }
        JOptionPane.showMessageDialog(null, nomina.toString() + ", " + empleados.size() + " empleados activos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        pesosSueldoLabel = new JLabel("$");
        pesosSueldoLabel.setFont(font);
        pesosInfonavitLabel = new JLabel("$");
        pesosInfonavitLabel.setFont(font);
        pesosSaldoLabel = new JLabel("$");
        pesosSaldoLabel.setFont(font);
        pesosDescuentoLabel = new JLabel("$");
        pesosDescuentoLabel.setFont(font);
        pesosValeLabel = new JLabel("$");
        pesosValeLabel.setFont(font);
        pesosGolpesLabel = new JLabel("$");
        pesosGolpesLabel.setFont(font);
        pesosPrestamosLabel = new JLabel("$");
        pesosPrestamosLabel.setFont(font);
        pesosTotalLabel = new JLabel("$");
        pesosTotalLabel.setFont(font);
        idLabel = new JLabel("Empleado");
        idLabel.setFont(font);
        nombreLabel = new JLabel("Nombre");
        nombreLabel.setFont(font);
        sueldoLabel = new JLabel("Sueldo");
        sueldoLabel.setFont(font);
        faltasLabel = new JLabel("Faltas");
        faltasLabel.setFont(font);
        infonavitLabel = new JLabel("infonavit");
        infonavitLabel.setFont(font);
        saldoLabel = new JLabel("Saldo anterior");
        saldoLabel.setFont(font);
        descuentoLabel = new JLabel("Descuento");
        descuentoLabel.setFont(font);
        valeLabel = new JLabel("Vale");
        valeLabel.setFont(font);
        golpesLabel = new JLabel("Golpes");
        golpesLabel.setFont(font);
        prestamosLabel = new JLabel("Prestamo");
        prestamosLabel.setFont(font);
        totalLabel = new JLabel("Total");
        totalLabel.setFont(font);
        idField = new JLabel();
        idField.setFont(font);
        nombreField = new JLabel();
        Font font2=new Font(font.getFamily(),Font.BOLD,font.getSize());
        nombreField.setFont(font2);
        sueldoField = new JLabel();
        sueldoField.setFont(font);
        faltasField = new JTextField();
        faltasField.setFont(font);
        KeyListener kListener=new KeyAdapter() {
            @Override
            public  void keyReleased(KeyEvent evt) {
                calcularTotal();
            }
        };
        FocusListener fListener=new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                ((JTextField)evt.getSource()).selectAll(); 
            }
        };
        faltasField.addKeyListener(kListener);
        faltasField.addFocusListener(fListener);
        infonavitField = new JTextField();
        infonavitField.addKeyListener(kListener);
        infonavitField.addFocusListener(fListener);
        infonavitField.setFont(font);
        saldoField = new JLabel();
        saldoField.setFont(font);
        descuentoField = new JTextField();
        descuentoField.setFont(font);
        descuentoField.addKeyListener(kListener);
        descuentoField.addFocusListener(fListener);
        valeField = new JTextField();
        valeField.setFont(font);
        valeField.addKeyListener(kListener);
        valeField.addFocusListener(fListener);
        golpesField = new JLabel();
        golpesField.setFont(font);
        prestamosField = new JLabel();
        prestamosField.setFont(font);
        totalField = new JLabel();
        totalField.setFont(font);
        adeudoButton = new JButton("Adeudo");
        adeudoButton.setFont(font);
        adeudoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                adeudo();
            }
        });
        siguienteButton = new JButton("Siguiente");
        siguienteButton.setFont(font);
        siguienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(update(index)){
                    load(++index);
                }
            }
        });
        anteriorButton = new JButton("Anterior");
        anteriorButton.setFont(font);
        anteriorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(update(index)){
                    load(--index);
                }
            }
        });
        pesosSaldoNuevoLabel = new JLabel("$");
        pesosSaldoNuevoLabel.setFont(font);
        saldoNuevoLabel = new JLabel("Saldo actual");
        saldoNuevoLabel.setFont(font);
        saldoNuevoField = new JLabel();
        saldoNuevoField.setFont(font);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                    .addComponent(idLabel)
                    .addComponent(nombreLabel)
                    .addComponent(sueldoLabel)
                    .addComponent(faltasLabel)
                    .addComponent(infonavitLabel)
                    .addComponent(saldoLabel)
                    .addComponent(saldoNuevoLabel)
                    .addComponent(descuentoLabel)
                    .addComponent(valeLabel)
                    .addComponent(golpesLabel)
                    .addComponent(prestamosLabel)
                    .addComponent(totalLabel)
                    .addComponent(anteriorButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(idField)
                    .addComponent(nombreField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosSueldoLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sueldoField))
                    .addComponent(faltasField, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosInfonavitLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infonavitField, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosSaldoLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saldoField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosSaldoNuevoLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saldoNuevoField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosInfonavitLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infonavitField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosDescuentoLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descuentoField, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosValeLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valeField, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosGolpesLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(golpesField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosPrestamosLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prestamosField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pesosTotalLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalField))
                    .addComponent(adeudoButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                    .addComponent(siguienteButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                .addContainerGap());
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel).addComponent(nombreField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sueldoLabel)
                    .addComponent(pesosSueldoLabel)
                    .addComponent(sueldoField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(faltasLabel)
                    .addComponent(faltasField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(infonavitLabel)
                    .addComponent(pesosInfonavitLabel)
                    .addComponent(infonavitField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saldoLabel)
                    .addComponent(pesosSaldoLabel)
                    .addComponent(saldoField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saldoNuevoLabel)
                    .addComponent(pesosSaldoNuevoLabel)
                    .addComponent(saldoNuevoField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(descuentoLabel)
                    .addComponent(pesosDescuentoLabel)
                    .addComponent(descuentoField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(valeLabel)
                    .addComponent(pesosValeLabel)
                    .addComponent(valeField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(golpesLabel)
                    .addComponent(pesosGolpesLabel)
                    .addComponent(golpesField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(prestamosLabel)
                    .addComponent(pesosPrestamosLabel)
                    .addComponent(prestamosField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLabel)
                    .addComponent(pesosTotalLabel)
                    .addComponent(totalField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adeudoButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(anteriorButton)
                    .addComponent(siguienteButton))
                .addContainerGap());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generar nómina");
        pack();
        setLocationRelativeTo(null);
        load(index = 0);
        setVisible(true);
    }
}
