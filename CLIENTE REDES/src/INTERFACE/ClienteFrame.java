package INTERFACE;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import APLICATION.ChatMessage;
import APLICATION.ChatMessage.Action;
import SERVICE.ClienteService;

public class ClienteFrame {

	private Socket socket;
	private ChatMessage message;
	private ClienteService service;

	public JFrame frmChat;
	private JTextField txtNome;
	private JTextArea txtAreaSend;
	private JTextArea txtAreaReceive;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private JButton btnEnviar;
	private JPanel panel_3;
	private JList listOnlines;
	private JTextField txtIP;

	/**
	 * Create the application.
	 */
	public ClienteFrame() {
		initialize();
	}

	private class ListenerSocket implements Runnable {

		private ObjectInputStream input;

		public ListenerSocket(Socket socket) {
			try {
				this.input = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			ChatMessage message = null;

			try {
				while ((message = (ChatMessage) input.readObject()) != null) {
					Action action = message.getAction();

					if (action.equals(Action.CONNECT)) {
						connected(message);
					} else if (action.equals(Action.DISCONNECT)) {
						disconnected();
						socket.close();
					} else if (action.equals(Action.SEND_ONE)) {
						receive(message);
					} else if (action.equals(Action.USERS_ONLINE)) {
						refreshOnlines(message);
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void connected(ChatMessage message) {
		if (message.getText().equals("Não Conectou")) {
			this.txtNome.setText("");
			JOptionPane.showMessageDialog(frmChat, "Conexão não realizada !! \n Tente outro nome");
			return;

		}

		this.message = message;
		this.btnConectar.setEnabled(false);
		this.txtNome.setEditable(false);
		this.txtIP.setEnabled(false);
		this.btnDesconectar.setEnabled(true);
		this.txtAreaSend.setEnabled(true);
		this.btnEnviar.setEnabled(true);

		JOptionPane.showMessageDialog(btnConectar, "Você se conectou ao chat");
		// this.txtAreaReceive.append(message.getName() + "\n");
	}

	private void disconnected() {

		this.btnConectar.setEnabled(true);
		this.txtNome.setEditable(true);
		this.txtIP.setEnabled(true);
		this.btnDesconectar.setEnabled(false);
		this.txtAreaSend.setEnabled(false);
		this.btnEnviar.setEnabled(false);

		this.txtAreaReceive.setText("");
		this.txtAreaSend.setText("");

		JOptionPane.showMessageDialog(btnConectar, "Você saiu do chat");

	}

	private void receive(ChatMessage message) {
		this.txtAreaReceive.append(message.getName() + " diz >> " + message.getText() + "\n");
	}

	private void refreshOnlines(ChatMessage message) {
		System.out.println(message.getSetOnlines().toString());
		
		Set<String> names = message.getSetOnlines();

		names.remove(message.getName());
		
		String[] array = (String[]) names.toArray(new String[names.size()]);

		this.listOnlines.setListData(array);
		this.listOnlines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listOnlines.setLayoutOrientation(JList.VERTICAL);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChat = new JFrame();
		frmChat.setTitle("Chat");
		frmChat.getContentPane().setBackground(new Color(0, 191, 255));
		frmChat.setBounds(100, 100, 567, 423);
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(10, 11, 414, 75);
		frmChat.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 47, 39, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(50, 44, 166, 20);
		panel.add(txtNome);

		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botaoConectar();
			}

		});
		btnConectar.setBounds(247, 11, 113, 23);
		panel.add(btnConectar);

		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setEnabled(false);
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botaoDesconectar();
			}
		});
		btnDesconectar.setBounds(247, 41, 113, 23);
		panel.add(btnDesconectar);
		
		txtIP = new JTextField();
		txtIP.setColumns(10);
		txtIP.setBounds(33, 12, 183, 20);
		panel.add(txtIP);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(10, 14, 25, 14);
		panel.add(lblIp);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(0, 191, 255));
		panel_1.setBounds(10, 97, 414, 169);
		frmChat.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtAreaReceive = new JTextArea();
		txtAreaReceive.setEditable(false);
		txtAreaReceive.setBounds(10, 11, 371, 147);
		panel_1.add(txtAreaReceive);

		JScrollPane spReceive = new JScrollPane(txtAreaReceive, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spReceive.setBounds(10, 11, 394, 147);
		panel_1.add(spReceive);
		// scrollPane_1.setViewportView(txtAreaReceved);
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(new Color(0, 191, 255));
		panel_3.setBounds(10, 277, 414, 96);
		frmChat.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		btnEnviar = new JButton("Enviar");
		btnEnviar.setEnabled(false);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botaoEnviar();
			}
		});
		btnEnviar.setBounds(314, 64, 73, 21);
		panel_3.add(btnEnviar);

		txtAreaSend = new JTextArea();
		txtAreaSend.setEnabled(false);
		txtAreaSend.setBounds(10, 11, 394, 42);
		panel_3.add(txtAreaSend);

		JScrollPane spSend = new JScrollPane(txtAreaSend, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spSend.setBounds(10, 11, 394, 42);
		panel_3.add(spSend);
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(434, 11, 107, 362);
		frmChat.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Onlines", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.WHITE));
		panel_4.setBackground(new Color(0, 191, 255));

		listOnlines = new JList();
		listOnlines.setBounds(10, 27, 87, 324);
		panel_4.add(listOnlines);
	}

	private void botaoConectar() {
		String name = this.txtNome.getText();
		String ip = txtIP.getText();
		
		if (!name.isEmpty() && !ip.isEmpty()) {
			this.message = new ChatMessage();
			this.message.setAction(Action.CONNECT);
			this.message.setName(name);

			this.service = new ClienteService();
			this.socket = this.service.connect(ip);

			new Thread(new ListenerSocket(this.socket)).start();

			this.service.send(message);
		}
	}

	private void botaoDesconectar() {
		ChatMessage message = new ChatMessage();
		message.setName(this.message.getName());
		message.setAction(Action.DISCONNECT);
		this.service.send(message);
		disconnected();
	}

	private void botaoEnviar() {
		String text = this.txtAreaSend.getText();
		String name = this.message.getName();

		this.message = new ChatMessage();

		if (!text.isEmpty()) {

			this.message.setName(name);
			this.message.setText(text);

			if (this.listOnlines.getSelectedIndex() > -1) {
				this.message.setNameReserved((String) this.listOnlines.getSelectedValue());
				this.message.setAction(Action.SEND_ONE);
				this.listOnlines.clearSelection();
			} else {
				this.message.setAction(Action.SEND_ALL);
			}


			this.txtAreaReceive.append("Você disse: " + text + "\n");

			this.service.send(this.message);
		}

		this.txtAreaSend.setText("");

	}
}
