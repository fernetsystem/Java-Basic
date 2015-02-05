import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.util.Date;
import java.util.Calendar;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class log implements ActionListener{
	JFrame f;
	JLabel l1,l2,l3,l4,l5,l6,l7;
	JTextField text1,text2,text3,text4,text5,text6,text7;
	JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7;
	JComboBox cmb1;
	Icon icono_limpiar,icono_grabar,icono_modificar,icono_eliminar,icono_consultar,icono_salir,icono_buscar;
	static File file = null;
	static File file2 = null; //arch2
	static Calendar calendario = new GregorianCalendar();
	static String idtr;
	static String hora;
	static String idaf;
	static String Accion;
	static String vold;
	static String vnew;
	static String info;
	static String separa = "|";
	static Date fechaActual = null;
	static SimpleDateFormat formato = null;
	static  String cadenafecha = null;
	static String busqueda = null;
	static int hora1,minutos,segundos;
	String datosold[] = new String[6];	
	JLabel back;
	public log() {
		f = new JFrame();
		l1 = new JLabel("ID Empleado"); 
		l2 = new JLabel("     Nombre"); 
		l3 = new JLabel("Ap. Paterno");
		l4 = new JLabel("Ap. Materno");
		l5 = new JLabel("  Direccion"); 
		l6 = new JLabel("   Telefono"); l7 = new JLabel("Volver a buscar");
		text1 = new JTextField(20);	text2 = new JTextField(20);
		text3 = new JTextField(20);	text4 = new JTextField(20);
		text5 = new JTextField(20);	text6 = new JTextField(20);
		text7 = new JTextField(25);
		Icon icono_limpiar = new ImageIcon(getClass().getResource("icono1.png")); 
		Icon icono_grabar = new ImageIcon(getClass().getResource("icono2.png"));
		Icon icono_modificar = new ImageIcon(getClass().getResource("icono3.png"));
		Icon icono_eliminar = new ImageIcon(getClass().getResource("icono4.png"));
		Icon icono_consultar = new ImageIcon(getClass().getResource("iconox2.png"));
		Icon icono_salir = new ImageIcon(getClass().getResource("icono6.png"));
		Icon icono_buscar = new ImageIcon(getClass().getResource("29.png"));
		back = new JLabel(new ImageIcon("112.jpg"));
		btn1 = new JButton(icono_limpiar);		btn1.setBackground(new Color(111,135,143));		btn1.setForeground(Color.WHITE);
		btn2 = new JButton(icono_grabar);		btn2.setBackground(new Color(58,85,94));		btn2.setForeground(Color.WHITE);
		btn3 = new JButton(icono_modificar);	btn3.setBackground(new Color(123,160,173));		btn3.setForeground(Color.WHITE); 
		btn4 = new JButton(icono_eliminar);	    btn4.setBackground(new Color(49,74,82));		btn4.setForeground(Color.WHITE);
		btn5 = new JButton(icono_consultar);	btn5.setBackground(new Color(119,136,153));		btn5.setForeground(Color.WHITE);
		btn6 = new JButton(icono_salir);
		btn7 = new JButton(icono_buscar);		btn7.setBackground(new Color(49,74,82));		btn7.setForeground(Color.WHITE);
		cmb1 = new JComboBox();	
	}
	public void usar(){
		f.add(back);
		back.setLayout(new FlowLayout(3,18,37));
	
		back.add(l1); back.add(text1); 
		back.add(l2); back.add(text2); 
		back.add(l3); back.add(text3); 
		back.add(l4); back.add(text4);
		back.add(l5); back.add(text5); 
		back.add(l6); back.add(text6); 
		
		back.add(btn1); back.add(btn2); back.add(btn3); 
		back.add(btn4);	back.add(btn5); //back.add(btn6);
		back.add(l7); back.add(text7); back.add(btn7);
		back.add(cmb1); 
	
		btn1.setText("Limpiar  "); btn1.setToolTipText("Limpia las cajas de texto");
		btn2.setText("Agregar  "); btn2.setToolTipText("Agrega un empleado");
		btn3.setText("Modificar"); btn3.setToolTipText("Altera los datos de un empleado");
		btn4.setText("Eliminar "); btn4.setToolTipText("Da de baja un empleado");
		btn5.setText("Consultar"); btn5.setToolTipText("Busca el empleado por el id");
		btn6.setText("Salir    "); btn6.setToolTipText("Salir del programa");
		btn7.setText("Buscar"); btn7.setToolTipText("Busca el nombre del empleado");	
		cmb1.addItem("Da click aqui"); //cmb1.addItem("prueba");
		llena();
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn7.addActionListener(this);
		text1.addActionListener(this);
		text2.addActionListener(this);
		text3.addActionListener(this);
		text4.addActionListener(this);
		text5.addActionListener(this);
		text7.addActionListener(this);
		 cmb1.addItemListener( new ItemListener(){
		 	public void itemStateChanged(ItemEvent e) {
		 		int  indice = cmb1.getSelectedIndex();
		 		String dato = (String) cmb1.getItemAt(indice);
		 		//JOptionPane.showMessageDialog(null,dato);
		 		text1.setText(dato);
		 		consultar();
		 	}
		 });
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		f.setVisible(true);
		f.setSize(740,464);
		}
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == btn1){limpiar();}
			if(e.getSource() == btn2){grabar(); }
			if(e.getSource() == btn3){modificar(); }
			if(e.getSource() == btn4){eliminar(); }
			if(e.getSource() == btn5){consultar();}
			if(e.getSource() == btn7){buscar();}
		
			if(e.getSource() == text1){
				String nulo="";
				if(text1.getText().equals(nulo)){
				JOptionPane.showMessageDialog(null,"*El ID es obligatorio*");}
				else { text2.requestFocusInWindow();}}
			if(e.getSource() == text2){
				String nulo="";
				if(text2.getText().equals(nulo)){
				JOptionPane.showMessageDialog(null,"*El nombre es obligatorio*");}
				else { text3.requestFocusInWindow();}}
			if(e.getSource() == text3){
				String nulo="";
				if(text3.getText().equals(nulo)){
				JOptionPane.showMessageDialog(null,"*El apellido paterno es obligatorio*");}
				else { text4.requestFocusInWindow();}}
			if(e.getSource() == text4){
				String nulo="";
				if(text4.getText().equals(nulo)){
				JOptionPane.showMessageDialog(null,"*El apellido materno es obligatorio*");}
				else { text5.requestFocusInWindow();}}
			if(e.getSource() == text5){
				String nulo="";
				if(text5.getText().equals(nulo)){
				JOptionPane.showMessageDialog(null,"*La direccion es obligatorio*");}
				else { text6.requestFocusInWindow();}}
			if(e.getSource() == text6){
				String nulo="";
				if(text6.getText().equals(nulo)){
				JOptionPane.showMessageDialog(null,"*El telefono es obligatorio*");}
				else { btn2.requestFocusInWindow();}}
			
		}
		public void limpiar(){
			text1.setText("");
			text2.setText("");
			text3.setText("");
			text4.setText("");
			text5.setText("");
			text6.setText("");
			text7.setText("");
		}	
		public void llena(){
			cmb1.removeAllItems();
			//cmb1.updateUI();
			cmb1.repaint();
			try{ Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/log?user=root&password=w9w9dorotea");
			Statement instruccion = conexion.createStatement();
			ResultSet tabla = instruccion.executeQuery("Select * from empleados;");
			while(tabla.next()){ cmb1.addItem(tabla.getString(1));}
			} catch(ClassNotFoundException e) { JOptionPane.showMessageDialog(null,e);}
			catch(SQLException e) { System.out.println(e);/*JOptionPane.showMessageDialog(null,e);*/}
		
		}
		public void modificar(){
    	String id = text1.getText();
    	String nombre = text2.getText();
    	String paterno = text3.getText();
    	String materno = text4.getText();
    	String direccion = text5.getText();
    	String telefono = text6.getText();
    		String datos[] = new String[6];
			String nombres[] = new String[6];
			datos[0] = text1.getText();
			datos[1] = text2.getText();
			datos[2] = text3.getText();
			datos[3] = text4.getText();
			datos[4] = text5.getText();
			datos[5] = text6.getText();
			nombres[0] = "IdEmpleado";
			nombres[1] = "Nombre";
			nombres[2] = "Appat";
			nombres[3] = "Apmat";
			nombres[4] = "Direc";
			nombres[5] = "Telefono"; 
    	PreparedStatement stmt = null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/log?user=root&password=w9w9dorotea");
    		String cadena = "UPDATE empleados SET nombre=?,";
    		cadena = cadena +"appa=?,apma=?,";
    		cadena = cadena +"direccion=?,telefono=? ";
    		cadena = cadena +"WHERE id_empleado=?;";
    		stmt = conexion.prepareStatement(cadena);
    		stmt.setString(6,id);
    		stmt.setString(1,nombre);
    		stmt.setString(2,paterno);
    		stmt.setString(3,materno);
    		stmt.setString(4,direccion);
    		stmt.setString(5,telefono);
    		int retorno = stmt.executeUpdate();
    		if(retorno == 1){JOptionPane.showMessageDialog(null,"Registro modificado");}
    		if(retorno == 0){JOptionPane.showMessageDialog(null,"Fracaso la modificacion");}
    	}catch(ClassNotFoundException e){JOptionPane.showMessageDialog(null,e);}
    		catch(SQLException e){ JOptionPane.showMessageDialog(null,e);}
    		catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    		modificarlog(datos,nombres);
    		llena();
    		limpiar();
    	
    	}
    	public void modificarlog(String datos[],String nombres[]){
			String s = null;
			String s1 = null;
			int longitud = datos.length;
			int cont = 0;
			while (cont < longitud){
				idtr = "700";
				idaf = nombres[cont]; //identificador de registro afectado
				Accion = "update";
				vold =datosold[cont];
				vnew = datos[cont];
				info = "Prueba log";
				separa = "|";
				hora1 = calendario.get(Calendar.HOUR_OF_DAY);
				minutos = calendario.get(Calendar.MINUTE);
				segundos = calendario.get(Calendar.SECOND);
				fechaActual = new Date();
				formato = new SimpleDateFormat("yyyyMMdd");
				cadenafecha=formato.format(fechaActual);
				s=cadenafecha+separa+hora1+":"+minutos+":"+segundos+separa+idtr+separa+idaf+separa+Accion+separa+vnew+separa+info;
				s1=cadenafecha+separa+hora1+":"+minutos+":"+segundos+separa+idtr+separa+idaf+separa+Accion+separa+vold+separa+info;
				
				//s=cadenafecha+separa+hora1+":"+minutos+":"+segundos+separa+idtr+separa+idaf+separa+Accion+separa+vold+separa+vnew+separa+info;
				try{ BufferedWriter out = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
					out.write(s);
					out.newLine();
					out.close();
					BufferedWriter out1 = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(file2,true),"UTF-8"));
					out1.write(s1);
					out1.newLine();
					out1.close();
				}catch(FileNotFoundException e1){
					JOptionPane.showMessageDialog(null,e1);
					System.err.println("File not found:"+file);
				}catch(IOException e2){
					JOptionPane.showMessageDialog(null,e2);
					e2.printStackTrace();
				} cont++;
				}	
		}		
		public void eliminar(){
    	String id = text1.getText();
    		String datos[] = new String[6];
			String nombres[] = new String[6];
			datos[0] = text1.getText();
			datos[1] = text2.getText();
			datos[2] = text3.getText();
			datos[3] = text4.getText();
			datos[4] = text5.getText();
			datos[5] = text6.getText();
			nombres[0] = "IdEmpleado";
			nombres[1] = "Nombre";
			nombres[2] = "Appat";
			nombres[3] = "Apmat";
			nombres[4] = "Direc";
			nombres[5] = "Telefono"; 
    	PreparedStatement stmt = null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/log?user=root&password=w9w9dorotea");
    		stmt = conexion.prepareStatement("delete from empleados where id_empleado=?;");
    		stmt.setString(1,id);
    		int retorno = stmt.executeUpdate();
    		if(retorno == 1){JOptionPane.showMessageDialog(null,"Borrado con exito");}
    		if(retorno == 0){JOptionPane.showMessageDialog(null,"Fracaso en el borrado");}
    	}catch(ClassNotFoundException e){JOptionPane.showMessageDialog(null,e);}
    		catch(SQLException e){ JOptionPane.showMessageDialog(null,e);}
    		catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    		eliminarlog(datos,nombres);
    		llena();
    		limpiar();
    	}
    	public void eliminarlog(String datos[],String nombres[]){
			String s = null;
			String s1 = null;
			int longitud = datos.length;
			int cont = 0;
			while (cont < longitud){
				idtr = "800";
				idaf = nombres[cont]; //identificador de registro afectado
				Accion = "delete";
				vold =datosold[cont];
				vnew = datos[cont];
				info = "Prueba log";
				separa = "|";
				hora1 = calendario.get(Calendar.HOUR_OF_DAY);
				minutos = calendario.get(Calendar.MINUTE);
				segundos = calendario.get(Calendar.SECOND);
				fechaActual = new Date();
				formato = new SimpleDateFormat("yyyyMMdd");
				cadenafecha=formato.format(fechaActual);
				s=cadenafecha+separa+hora1+":"+minutos+":"+segundos+separa+idtr+separa+idaf+separa+Accion+separa+vnew+separa+info;
				s1=cadenafecha+separa+hora1+":"+minutos+":"+segundos+separa+idtr+separa+idaf+separa+Accion+separa+vold+separa+info;
				//s=cadenafecha+separa+hora1+":"+minutos+":"+segundos+separa+idtr+separa+idaf+separa+Accion+separa+vold+separa+vnew+separa+info;
				try{ BufferedWriter out = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
					out.write(s);
					out.newLine();
					out.close();
				BufferedWriter out1 = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(file2,true),"UTF-8"));
					out1.write(s1);
					out1.newLine();
					out1.close();
				}catch(FileNotFoundException e1){
					JOptionPane.showMessageDialog(null,e1);
					System.err.println("File not found:"+file);
				}catch(IOException e2){
					JOptionPane.showMessageDialog(null,e2);
					e2.printStackTrace();
				} cont++;
				}	
		}		
		public void consultar(){
			String id = text1.getText();		
			try{ Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/log?user=root&password=w9w9dorotea");
			Statement instruccion = conexion.createStatement();
			ResultSet tabla = instruccion.executeQuery("Select * from empleados where id_empleado="+id+";");
			cmb1.removeAll();
			cmb1.repaint();
			while(tabla.next()){ 
				text1.setText(tabla.getString(1));
				text2.setText(tabla.getString(2));
				text3.setText(tabla.getString(3));
				text4.setText(tabla.getString(4));
				text5.setText(tabla.getString(5));
				text6.setText(tabla.getString(6));
				datosold[0] = text1.getText();
				datosold[1] = text2.getText();
				datosold[2] = text3.getText();
				datosold[3] = text4.getText();
				datosold[4] = text5.getText();
				datosold[5] = text6.getText();
			}
			} catch(ClassNotFoundException e) { JOptionPane.showMessageDialog(null,e);}
			catch(SQLException e) { System.out.println(e);/*JOptionPane.showMessageDialog(null,e);*/}
		}
		public void buscar(){
			String nombre = text7.getText();
						
			try{ Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/log?user=root&password=w9w9dorotea");
			Statement instruccion = conexion.createStatement();
			ResultSet tabla = instruccion.executeQuery("Select * from empleados where nombre like '%"+nombre+"%';");
			//cmb1.removeAll();
			//cmb1.repaint();
			while(tabla.next()){ 
				text1.setText(tabla.getString(1));
			}
			} catch(ClassNotFoundException e) { JOptionPane.showMessageDialog(null,e);}
			catch(SQLException e) { JOptionPane.showMessageDialog(null,e);}
			consultar();
		}
		public void grabar(){
			String datos[] = new String[6];
			String nombres[] = new String[6];
			datos[0] = text1.getText();		
			datos[1] = text2.getText();		
			datos[2] = text3.getText();		
			datos[3] = text4.getText();		
			datos[4] = text5.getText();		
			datos[5] = text6.getText();		
			nombres[0] = "IdEmpleado";
			nombres[1] = "Nombre";
			nombres[2] = "Appat";
			nombres[3] = "Apmat";
			nombres[4] = "Direc";
			nombres[5] = "Telefono";
				String id= text1.getText(); 
				String nombre= text2.getText();
				String appa = text3.getText();
				String apma = text4.getText();
				String direccion= text5.getText(); 
				String telefono= text6.getText(); 
		
			PreparedStatement stmt = null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/log?user=root&password=w9w9dorotea");
    		stmt = conexion.prepareStatement("INSERT INTO empleados VALUES(?,?,?,?,?,?)");
    		stmt.setString(1,id);
		  	stmt.setString(2,nombre);
		  	stmt.setString(3,appa);
		  	stmt.setString(4,apma);
		  	stmt.setString(5,direccion);
		  	stmt.setString(6,telefono);
    		int retorno = stmt.executeUpdate();
    		if(retorno == 1){JOptionPane.showMessageDialog(null,"Registro dado de alta");}
    		if(retorno == 0){JOptionPane.showMessageDialog(null,"Fracaso la alta");}
    	}catch(ClassNotFoundException e){JOptionPane.showMessageDialog(null,e);}
    		catch(SQLException e){ JOptionPane.showMessageDialog(null,e);}
    		catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    		grabalog(datos,nombres);
			llena();
			limpiar();
		}
		public void grabalog(String datos[],String nombres[]){
			String s = null;
			int longitud = datos.length;
			int cont = 0;
			while (cont < longitud){
				idtr = "900";
				idaf = nombres[cont]; //identificador de registro afectado
				Accion = "insert";
				//vold ="0";
				vnew = datos[cont];
				info = "Prueba log";
				separa = "|";
				hora1 = calendario.get(Calendar.HOUR_OF_DAY);
				minutos = calendario.get(Calendar.MINUTE);
				segundos = calendario.get(Calendar.SECOND);
				fechaActual = new Date();
				formato = new SimpleDateFormat("yyyyMMdd");
				cadenafecha=formato.format(fechaActual);
				s=cadenafecha+separa+hora1+":"+minutos+":"+segundos+separa+idtr+separa+idaf+separa+Accion+separa+vnew+separa+info;
				//s=cadenafecha+separa+hora1+":"+minutos+":"+segundos+separa+idtr+separa+idaf+separa+Accion+separa+vold+separa+vnew+separa+info;
				try{ BufferedWriter out = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
					out.write(s);
					out.newLine();
					out.close();
				}catch(FileNotFoundException e1){
					JOptionPane.showMessageDialog(null,e1);
					System.err.println("File not found:"+file);
				}catch(IOException e2){
					JOptionPane.showMessageDialog(null,e2);
					e2.printStackTrace();
				} cont++;
				}	
		}
	public static void main(String[] args) {
		String archivo = "F:\\BDD_LOG\\log.txt";
		file = new File(archivo);
		String archivo2 = "F:\\BDD_LOG\\log2.txt";
		file2 = new File(archivo2);
		hora1 = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		log mylog = new log();
		mylog.usar();
	}
}    

