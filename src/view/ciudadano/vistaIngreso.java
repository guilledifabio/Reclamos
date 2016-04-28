package view.ciudadano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.Controlador;
import view.principal.vistaPrincipal;

public class vistaIngreso extends JPanel {
	
	private JTextField textDni;
	private JLabel labelDNI;
	
	/**
	 * Create the panel.
	 */
	public vistaIngreso(final Controlador controlador, final JPanel panelCentral,final JPanel panelLateral, final JFrame frame) {
		setLayout(null);
		setBounds(98, 0, 430, 300);
		textDni = new JTextField();
		textDni.setBounds(80, 94, 116, 22);
		add(textDni);
		textDni.setColumns(10);
		
		labelDNI = new JLabel("Ingrese DNI:");
		labelDNI.setBounds(80, 65, 72, 16);
		add(labelDNI);
		
		JButton botonIngresar = new JButton("Ingresar");
		botonIngresar.setBounds(223, 93, 91, 25);
		botonIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dni = textDni.getText();
				if (dni.length() == 0) {
					JOptionPane.showMessageDialog(frame, "Ingrese un DNI");
				}
				System.out.println("DNI : "+dni);
				
				if (controlador.Ingresar(Integer.parseInt(dni))) {
					System.out.println("entro DNI : "+dni);
					JOptionPane.showMessageDialog(frame, "El usuario con dni existe");
					//usuario existe cambiar vistas
				} else {
					JOptionPane.showMessageDialog(frame, "El usuario con dni ingresado no existe");
				}
			}
		});
		add(botonIngresar);
		
		JButton botonRegistrar = new JButton("Registrar");
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				vistaRegistro vRegistro = new vistaRegistro(controlador, panelCentral, panelLateral, frame);
				panelCentral.removeAll();
				panelCentral.add(vRegistro);
				panelCentral.updateUI();
				panelCentral.repaint();
			}
		});
		botonRegistrar.setBounds(80, 229, 99, 25);
		add(botonRegistrar);
	}

}
