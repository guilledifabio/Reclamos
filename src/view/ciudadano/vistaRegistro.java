package view.ciudadano;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.Controlador;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vistaRegistro extends JPanel {
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCorreo;
	private JTextField textDni;

	/**
	 * Create the panel.
	 */
	public vistaRegistro(final Controlador controlador, final JPanel panelCentral, final JPanel panelLateral, final JFrame frame) {
		setLayout(null);
		setBounds(98, 0, 430, 300);
		
		textNombre = new JTextField();
		textNombre.setBounds(139, 36, 116, 22);
		add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(139, 83, 116, 22);
		add(textApellido);
		textApellido.setColumns(10);
		
		textCorreo = new JTextField();
		textCorreo.setBounds(139, 130, 116, 22);
		add(textCorreo);
		textCorreo.setColumns(10);
		
		textDni = new JTextField();
		textDni.setBounds(139, 179, 116, 22);
		add(textDni);
		textDni.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(50, 243, 97, 25);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Calcelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vistaIngreso vistaI = new vistaIngreso(controlador, panelCentral, panelLateral, frame);
				panelCentral.removeAll();
				panelCentral.add(vistaI);
				panelCentral.updateUI();
				panelCentral.repaint();
				
			}
		});
		btnNewButton_1.setBounds(247, 243, 97, 25);
		add(btnNewButton_1);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(50, 39, 56, 16);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(50, 86, 56, 16);
		add(lblApellido);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(50, 133, 56, 16);
		add(lblCorreo);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(50, 182, 56, 16);
		add(lblDni);

	}
}
