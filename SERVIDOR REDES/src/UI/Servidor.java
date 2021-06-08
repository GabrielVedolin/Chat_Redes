package UI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


import SERVICE.ServidorService;

public class Servidor {

	
	public static void main(String[] args) {
	   
		int val = JOptionPane.showConfirmDialog(null, "INICIAR SERVIDOR ?","SERVIDOR",JOptionPane.OK_CANCEL_OPTION);
		if(val == 0) {

			JLabel lblMessage = new JLabel("INICIANDO SERVIDOR");
		    Object[] texts = {lblMessage};
		    JOptionPane.showMessageDialog(null, texts);
		    
			new ServidorService();
			
		}else {
			JOptionPane.showMessageDialog(null, "Fechando servidor...");
		}

	}

}
