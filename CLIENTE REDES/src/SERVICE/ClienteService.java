package SERVICE;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import APLICATION.ChatMessage;

public class ClienteService {

	private Socket socket;
	private ObjectOutputStream output;

	public Socket connect(String ip) {

		try { // *******para usar na rede coloque o ip da maquina que esta o servidor
			int porta = 5555; // 127.0.0.1
			this.socket = new Socket(ip, porta);
			this.output = new ObjectOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return socket;
	}

	// Envia mensagem para usuario
	public void send(ChatMessage message) {
		try {
			output.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
