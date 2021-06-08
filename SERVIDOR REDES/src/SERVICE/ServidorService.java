package SERVICE;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import APLICATION.ChatMessage;
import APLICATION.ChatMessage.Action;

public class ServidorService {

	private ServerSocket serverSocket;
	private Socket socket;
	private Map<String, ObjectOutputStream> mapOnlines = new HashMap<String, ObjectOutputStream>();

	public ServidorService() {
		try {
			int porta = 5555;
			serverSocket = new ServerSocket(porta);

			System.out.println("Servidor on!");

			while (true) {
				socket = serverSocket.accept();

				new Thread(new ListenerSocket(socket)).start();
			}

		} catch (Exception e) {
			Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private class ListenerSocket implements Runnable {

		private ObjectOutputStream output;
		private ObjectInputStream input;

		public ListenerSocket(Socket socket) {

			try {
				this.output = new ObjectOutputStream(socket.getOutputStream());
				this.input = new ObjectInputStream(socket.getInputStream());

			} catch (IOException e) {
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
						boolean isConnect = connect(message, output);
						if (isConnect) {
							mapOnlines.put(message.getName(), output);
							sendOnlines();
						}
					} else if (action.equals(Action.DISCONNECT)) {
						disconnect(message, output);
						sendOnlines();
						return;
					} else if (action.equals(Action.SEND_ONE)) {
						sendOne(message);
					} else if (action.equals(Action.SEND_ALL)) {
						sendAll(message);
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				
				ChatMessage cm = new ChatMessage();
				cm.setName(message.getName());
				disconnect(cm, output);
				sendOnlines();
				
				System.out.println(message.getName() + " deixou o chat!");
				e.printStackTrace();
			}
		}

	}

	private boolean connect(ChatMessage message, ObjectOutputStream output) {
		if (mapOnlines.size() == 0) {
			message.setText("Conectou");
			send(message, output);
			return true;
		}

		for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
			if (kv.getKey().equals(message.getName())) {
				message.setText("Não Conectou");
				send(message, output);
				return false;
			} else {
				message.setText("Conectou");
				send(message, output);
				return true;
			}
		}

		return false;
	}

	private void disconnect(ChatMessage message, ObjectOutputStream output) {
		mapOnlines.remove(message.getName());

		message.setText("Até logo !");

		message.setAction(Action.SEND_ONE);

		sendAll(message);

		System.out.println("User: " + message.getName() + " saiu da sala");
	}

	private void sendAll(ChatMessage message) {
		for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
			if (!kv.getKey().equals(message.getName())) {

				message.setAction(Action.SEND_ONE);
				try {
					kv.getValue().writeObject(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void send(ChatMessage message, ObjectOutputStream output) {
		try {
			output.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void sendOne(ChatMessage message) {
		for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
			if (kv.getKey().equals(message.getNameReserved())) {
				try {
					kv.getValue().writeObject(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	private void sendOnlines() {
		Set<String> setNames = new HashSet<String>();
		for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
			setNames.add(kv.getKey());
		}

		ChatMessage message = new ChatMessage();
		message.setAction(Action.USERS_ONLINE);
		message.setSetOnlines(setNames);

		for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {

			message.setName(kv.getKey());
			
			try {
				
				kv.getValue().writeObject(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
