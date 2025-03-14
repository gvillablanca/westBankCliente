package com.bank.ui;

import com.bank.negocio.Cliente;
import com.bank.negocio.Cuenta;
import com.bank.operaciones.FuncionesMenu;
import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

public class HistorialUI extends javax.swing.JFrame {
    Cliente cliente;
    public HistorialUI(Cliente cliente) {
        this.cliente = cliente;
        initComponents();
        Date fecha = new Date();
        lb_fechaSys.setText(fecha.toString());
        cliente.getCliente(cliente.getRut(), cliente.getDv(), cliente.getClave());
        Cuenta cuenta = new Cuenta();
        cuenta.obtener(cliente.getNumeroCuenta());  
        getHistorial(cliente.getNumeroCuenta());
    }
    
    public void getHistorial (int numeroCuenta){
        try{
            DefaultTableModel tablahistorial = (DefaultTableModel)tbl_historial.getModel();
            InputStream entrada = new FileInputStream("../WestBankCliente/src/com/bank/accesoDatos/app.properties");
            Properties propiedades = new Properties();
            propiedades.load(entrada);
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = propiedades.getProperty("url");
            String user = propiedades.getProperty("user");
            String pass = propiedades.getProperty("pass");
            Connection conn = DriverManager.getConnection(url, user, pass);
        
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT COD_TRX, NUMERO_CUENTA, MONTO, NUMERO_CUENTA_DES, FECHA_TRX FROM HISTORIAL_CLIENTE WHERE NUMERO_CUENTA =" + numeroCuenta);
            
            while(rs.next()){
                Object[] datosFila = {rs.getInt(1) ,rs.getInt(2),rs.getInt(3),rs.getInt(4), rs.getString(5)};
                tablahistorial.addRow(datosFila);
            }
           
        }
        catch(ClassNotFoundException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        catch(SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        } 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btn_inicio = new javax.swing.JButton();
        btn_transferencia = new javax.swing.JButton();
        btn_historial = new javax.swing.JButton();
        btn_cerrarSesion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lb_fechaSys = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_historial = new javax.swing.JTable();
        btn_actualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        btn_inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bank/img/home.png"))); // NOI18N
        btn_inicio.setText("INICIO");
        btn_inicio.setFocusable(false);
        btn_inicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_inicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicioActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_inicio);

        btn_transferencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bank/img/transferir.png"))); // NOI18N
        btn_transferencia.setText("TRANSFERENCIA");
        btn_transferencia.setFocusable(false);
        btn_transferencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_transferencia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_transferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transferenciaActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_transferencia);

        btn_historial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bank/img/gestionarcuenta.png"))); // NOI18N
        btn_historial.setText("HISTORIAL");
        btn_historial.setFocusable(false);
        btn_historial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_historial.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_historialActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_historial);

        btn_cerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bank/img/exit.png"))); // NOI18N
        btn_cerrarSesion.setText("CERRAR SESIÓN");
        btn_cerrarSesion.setFocusable(false);
        btn_cerrarSesion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cerrarSesion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarSesionActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_cerrarSesion);

        jLabel1.setText("Fecha de ultima actualización :");

        lb_fechaSys.setText("jLabel2");

        tbl_historial.setAutoCreateRowSorter(true);
        tbl_historial.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbl_historial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Transacción", "Cuenta Origen", "Monto operación", "Cuenta Destino", "Fecha y Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_historial);

        btn_actualizar.setBackground(new java.awt.Color(0, 153, 255));
        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bank/img/refresh.png"))); // NOI18N
        btn_actualizar.setText("Actualizar");
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_fechaSys, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lb_fechaSys))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_actualizar)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inicioActionPerformed
        FuncionesMenu menu = new FuncionesMenu();
        menu.inicio(cliente);
        this.dispose(); 
    }//GEN-LAST:event_btn_inicioActionPerformed

    private void btn_transferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transferenciaActionPerformed
        FuncionesMenu menu = new FuncionesMenu();
        menu.transferencia(cliente);
        this.dispose(); 
    }//GEN-LAST:event_btn_transferenciaActionPerformed

    private void btn_historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_historialActionPerformed
        FuncionesMenu menu = new FuncionesMenu();
        menu.historial(cliente);
        this.dispose(); 
    }//GEN-LAST:event_btn_historialActionPerformed

    private void btn_cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarSesionActionPerformed
        FuncionesMenu menu = new FuncionesMenu();
        menu.login();
        this.dispose(); 
    }//GEN-LAST:event_btn_cerrarSesionActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
        DefaultTableModel tableModel = (DefaultTableModel) tbl_historial.getModel();
        tableModel.setRowCount(0);
        getHistorial(cliente.getNumeroCuenta());
    }//GEN-LAST:event_btn_actualizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_cerrarSesion;
    private javax.swing.JButton btn_historial;
    private javax.swing.JButton btn_inicio;
    private javax.swing.JButton btn_transferencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lb_fechaSys;
    private javax.swing.JTable tbl_historial;
    // End of variables declaration//GEN-END:variables
}
