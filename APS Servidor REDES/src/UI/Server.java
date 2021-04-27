package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;

public class Server {

	private JFrame frmServer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Server");
		frmServer.getContentPane().setBackground(new Color(0, 191, 255));
		frmServer.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(10, 11, 257, 308);
		frmServer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SERVER");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 68, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 72, 237, 225);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 11, 206, 203);
		panel_2.add(textPane);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(220, 0, 17, 225);
		panel_2.add(scrollBar);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(20, 38, 89, 23);
		panel.add(btnIniciar);
		
		JButton btnDesligar = new JButton("Desligar");
		btnDesligar.setBounds(128, 38, 89, 23);
		panel.add(btnDesligar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(0, 191, 255));
		panel_1.setBounds(309, 11, 257, 308);
		frmServer.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIOS ONLINE");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 11, 168, 14);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 78, 237, 159);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(10, 11, 203, 137);
		panel_3.add(textPane_1);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollBar_1.setBounds(220, 0, 17, 159);
		panel_3.add(scrollBar_1);
		
		JButton btnListarUsuarios = new JButton("Listar Usuarios");
		btnListarUsuarios.setBounds(10, 44, 130, 23);
		panel_1.add(btnListarUsuarios);
		frmServer.setBounds(100, 100, 603, 369);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
