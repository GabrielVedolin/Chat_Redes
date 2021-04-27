package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.TextArea;
import javax.swing.JScrollBar;

public class Principal {

	private JFrame frmChat;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChat = new JFrame();
		frmChat.setTitle("Chat");
		frmChat.getContentPane().setBackground(new Color(0, 191, 255));
		frmChat.setBounds(100, 100, 567, 376);
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(10, 11, 531, 57);
		frmChat.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP:");
		lblNewLabel.setBounds(10, 11, 21, 14);
		panel.add(lblNewLabel);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(181, 11, 39, 14);
		panel.add(lblPorta);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 36, 39, 14);
		panel.add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(30, 8, 141, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(216, 8, 86, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(50, 33, 252, 20);
		panel.add(textField_2);
		
		JButton btnLoginAnonimo = new JButton("Login Anonimo");
		btnLoginAnonimo.setBounds(351, 11, 141, 39);
		panel.add(btnLoginAnonimo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(0, 191, 255));
		panel_1.setBounds(10, 78, 531, 169);
		frmChat.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 354, 147);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(10, 11, 334, 125);
		panel_2.add(textPane);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(394, 33, 113, 23);
		panel_1.add(btnConectar);
		
		JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(394, 91, 113, 23);
		panel_1.add(btnDesconectar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(new Color(0, 191, 255));
		panel_3.setBounds(10, 258, 531, 75);
		frmChat.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(394, 14, 113, 23);
		panel_3.add(btnEnviar);
		
		TextArea txtaTexto = new TextArea();
		txtaTexto.setBounds(10, 7, 318, 61);
		panel_3.add(txtaTexto);
	}
}
