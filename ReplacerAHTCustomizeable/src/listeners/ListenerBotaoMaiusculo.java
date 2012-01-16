package listeners;

import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBotaoMaiusculo implements ActionListener {

	TextArea areaAlvo;
	Button bMaiusculo;

	public ListenerBotaoMaiusculo(TextArea areaAlvo, Button bMaiusculo) {
		this.areaAlvo = areaAlvo;
		this.bMaiusculo = bMaiusculo;
	}

	public void actionPerformed(ActionEvent e) {
		String s = areaAlvo.getText();
		s = s.toUpperCase();
		areaAlvo.setText(s);

	}

}
