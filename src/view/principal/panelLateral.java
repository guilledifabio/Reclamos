package view.principal;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class panelLateral extends JPanel {

	/**
	 * Create the panel.
	 */
	public panelLateral(final JFrame frame) {
		setBounds(0, 0, 99, 300);
		setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnSalir.setBounds(29, 262, 97, 25);
		add(btnSalir);
	}
}
