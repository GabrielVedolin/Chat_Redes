package UI;

import java.awt.EventQueue;

import INTERFACE.ClienteFrame;

public class Cliente {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteFrame window = new ClienteFrame();
					
					window.frmChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
