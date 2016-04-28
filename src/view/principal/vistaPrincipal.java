package view.principal;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import api.Reclamos;
import modelo.Ciudadano;
import view.Controlador;
import view.ciudadano.vistaIngreso;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class vistaPrincipal {

	private JFrame frame;
	private Controlador controlador = new Controlador();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaPrincipal window = new vistaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public vistaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controlador.ConectarAPI();
		frame = new JFrame();
		frame.setBounds(100, 100, 617, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelLateral = new JPanel();
		panelLateral.setBounds(0, 0, 145, 300);
		frame.getContentPane().add(panelLateral);
		panelLateral.setLayout(new CardLayout());
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBounds(157, 0, 430, 300);
		frame.getContentPane().add(panelCentral);
		panelCentral.setLayout(new CardLayout());
		
		panelLateral salir = new panelLateral(frame);
		panelLateral.removeAll();
		panelLateral.add(salir);
		panelLateral.updateUI();
		panelLateral.repaint();
		
		vistaIngreso vistaI = new vistaIngreso(controlador, panelCentral, panelLateral, frame);
		panelCentral.removeAll();
		panelCentral.add(vistaI);
		panelCentral.updateUI();
		panelCentral.repaint();
		
		
	}
}
