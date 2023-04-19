package GUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import Programa.mainLogic;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Toolkit;


public class Aplicacion {

	private JFrame frmGestionDirectorios;
	private mainLogic program;
	private JTextField textEntrada;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion window = new Aplicacion();
					window.frmGestionDirectorios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Aplicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		program = new mainLogic();
		
		frmGestionDirectorios = new JFrame();
		frmGestionDirectorios.setIconImage(Toolkit.getDefaultToolkit().getImage("\\Proyecto_127278_132735\\Documentacion\\Imagenes\\IconImagen.jpg"));
		frmGestionDirectorios.setResizable(false);
		frmGestionDirectorios.setType(Type.POPUP);
		frmGestionDirectorios.setTitle("Gestion Directorios");
		frmGestionDirectorios.setBounds(100, 100, 708, 393);
		frmGestionDirectorios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDirectorios.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 518, 308);
		frmGestionDirectorios.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		//Creacion botones de los metodos
		JButton btnSetArbol = new JButton("Ingresar Contenido");
		JButton btnObtenerRutaAbsoluta = new JButton("Ruta Absoluta");
		JButton btnAncestroComun = new JButton("Ancestro Comun");
		JButton btnListar = new JButton("Listar");
		JButton btnGradoArbol = new JButton("Grado Arbol");
		JButton btnGradoNodo = new JButton("Grado Nodo");
		JButton btnImprimirSubdirectorios = new JButton("Imprimir SubDirectorios");
		JButton btnAltura = new JButton("Altura del arbol");
		JButton btnProfundidad = new JButton("Profundidad");
		JButton btnImprimirContenido = new JButton("Imprimir Archivos");
		
		//Deshabilitando botones
		btnObtenerRutaAbsoluta.setEnabled(false);
		btnAncestroComun.setEnabled(false);
		btnListar.setEnabled(false);
		btnGradoArbol.setEnabled(false);
		btnGradoNodo.setEnabled(false);
		btnImprimirSubdirectorios.setEnabled(false);
		btnAltura.setEnabled(false);
		btnProfundidad.setEnabled(false);
		btnImprimirContenido.setEnabled(false);
		
		btnSetArbol.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSetArbol.setToolTipText("Ingrese la ruta para formar el arbol");
		btnSetArbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String entrada = null;
				entrada =	textEntrada.getText();
				if(entrada.isEmpty()) {
					textArea.setText("ERROR: Ingrese una ruta valida.");
				}else {
					program.createTree(entrada);
					textArea.setText("Carga exitosa: "+entrada);
					btnObtenerRutaAbsoluta.setEnabled(true);
					btnAncestroComun.setEnabled(true);
					btnListar.setEnabled(true);
					btnGradoArbol.setEnabled(true);
					btnGradoNodo.setEnabled(true);
					btnImprimirSubdirectorios.setEnabled(true);
					btnAltura.setEnabled(true);
					btnProfundidad.setEnabled(true);
					btnImprimirContenido.setEnabled(true);
				}		
			}
		});
		btnSetArbol.setBounds(10, 11, 144, 23);
		frmGestionDirectorios.getContentPane().add(btnSetArbol);
		
		
		textEntrada = new JTextField();
		textEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
			}
		});
		textEntrada.setToolTipText("...Ingrese ruta...");
		textEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		textEntrada.setBounds(163, 11, 519, 21);
		frmGestionDirectorios.getContentPane().add(textEntrada);
		textEntrada.setColumns(10);
		
		//Imprimir archivos
		btnImprimirContenido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImprimirContenido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = JOptionPane.showInputDialog("Ingrese ruta del directorio: ");
				String control = program.printFiles(path);
				if(control.length() > 0) {
					textArea.setText(control);
				}else {
					textArea.setText("No posee archivos para mostrar");
				}

			}
		});
		btnImprimirContenido.setBounds(538, 43, 144, 23);
		frmGestionDirectorios.getContentPane().add(btnImprimirContenido);
		
		//Profundidad
		btnProfundidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnProfundidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = null;
				path = JOptionPane.showInputDialog("Ingrese ruta del directorio/archivo: ");
				
				if(path.isEmpty()) {
					textArea.setText("ERROR: Ingrese una ruta valida.");
				}else {
					textArea.setText("La profundidad del nodo es: "+String.valueOf(program.depth(path)));
				}
			}
		});
		btnProfundidad.setBounds(538, 95, 144, 23);
		frmGestionDirectorios.getContentPane().add(btnProfundidad);
		
		//Altura del arbol
		btnAltura.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAltura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("La altura del arbol es: "+String.valueOf(program.height()));
			}
		});
		btnAltura.setBounds(538, 121, 144, 23);
		frmGestionDirectorios.getContentPane().add(btnAltura);
		
		//Imprimir subDirectorios
		btnImprimirSubdirectorios.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImprimirSubdirectorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = null;
				path = JOptionPane.showInputDialog("Ingrese ruta del directorio: ");
				if(path.isEmpty()) {
					textArea.setText("ERROR: Ingrese una ruta valida.");
				}else {
					textArea.setText(program.printSubDirectory(path));
				}
				
			}
		});
		btnImprimirSubdirectorios.setBounds(538, 69, 144, 23);
		frmGestionDirectorios.getContentPane().add(btnImprimirSubdirectorios);
		
		//Grado del nodo
		btnGradoNodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = null;
				int gNodo = 0;
				path = JOptionPane.showInputDialog("Ingrese ruta del directorio: ");
				if(path.isEmpty()) {
					textArea.setText("ERROR: Ingrese una ruta valida.");
				}else {
					gNodo = program.degreeNode(path);
					textArea.setText("El grado del nodo es: "+String.valueOf(gNodo));
				}
			}
		});
		btnGradoNodo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGradoNodo.setBounds(538, 147, 144, 23);
		frmGestionDirectorios.getContentPane().add(btnGradoNodo);
		
		

		//Grado del arbol
		btnGradoArbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				int gArbol = program.degreeTree(textEntrada.getText());
				textArea.setText("El grado del arbol es: "+String.valueOf(gArbol));
			}
		});
		btnGradoArbol.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGradoArbol.setBounds(538, 173, 144, 23);
		frmGestionDirectorios.getContentPane().add(btnGradoArbol);
		
		//Listar
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					textArea.setText(program.list());	
			}
		});
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnListar.setBounds(538, 197, 144, 23);
		frmGestionDirectorios.getContentPane().add(btnListar);
		
		
		
		//Ancestro comun
		btnAncestroComun.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAncestroComun.setBounds(538, 222, 144, 23);
		frmGestionDirectorios.getContentPane().add(btnAncestroComun);
		
		btnAncestroComun.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ruta1 = null;
						String ruta2 = null;
						ruta1 = JOptionPane.showInputDialog("Ruta del primer directorio: ");
						if(ruta1.isEmpty()) {
							textArea.setText("ERROR primer directorio: Ingrese una ruta valida.");
						}else {
							ruta2 = JOptionPane.showInputDialog("Ruta del segundo directorio: ");
							if(ruta2.isEmpty()) {
								textArea.setText("ERROR segundo directorio: Ingrese una ruta valida");
							}else {
								if(ruta1.equals(textEntrada.getText()) || ruta2.equals(textEntrada.getText())) {
									textArea.setText("ERROR: ancestro propio");
								}else {
									textArea.setText("El ancestro en comun es: "+ program.closeCommonAncestor(ruta1, ruta2).element().getKey());
								}
								
							}
						}
					}
				});
		
		
		//Ruta absoluta
        btnObtenerRutaAbsoluta.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnObtenerRutaAbsoluta.setBounds(538, 247, 144, 23);
        frmGestionDirectorios.getContentPane().add(btnObtenerRutaAbsoluta);
        btnObtenerRutaAbsoluta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String ruta1 = null;
				String ruta2 = null;
				ruta1 = JOptionPane.showInputDialog("Ruta ruta relativa: ");
                if(ruta1.isEmpty()) {
					textArea.setText("ERROR ruta 1: Ingrese una ruta valida.");
				}else {
					ruta2 = JOptionPane.showInputDialog("Ruta absoluta: ");
					if(ruta2.isEmpty()) {
						textArea.setText("ERROR ruta 2: Ingrese una ruta valida");
					}else {
						textArea.setText("La nueva ruta es: "+ program.getAbsoluteAddress(ruta1, ruta2));
					}
				}
        }

    });
	}
}