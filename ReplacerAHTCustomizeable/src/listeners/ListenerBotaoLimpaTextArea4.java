package listeners;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBotaoLimpaTextArea4 implements ActionListener {

	TextArea textArea4;

	public ListenerBotaoLimpaTextArea4(TextArea textArea4) {
		this.textArea4 = textArea4;
	}

	public void actionPerformed(ActionEvent e) {
		textArea4.setText("");

	}

}
