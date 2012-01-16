package listeners;

import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListenerBotaoColocarNoFinalDeCadaLinha implements ActionListener {


	TextArea areaDestino;
	TextField textField;
	public ListenerBotaoColocarNoFinalDeCadaLinha(TextArea areaDestino, TextField textField) {
		this.areaDestino = areaDestino;
		this.textField = textField;
	}

	public void actionPerformed(ActionEvent e) {
		String lido = areaDestino.getText();
		String entrada = textField.getText();
		areaDestino.setText(adicionaNoFinalDeCadaLinha(lido, entrada));
	}
	
	public String adicionaNoFinalDeCadaLinha(String stringArea, String entrada){
		String concatenado = "";
		for (int j = 0; j < stringArea.length(); j++) {
			if(stringArea.charAt(j)=='\r'){
				concatenado += entrada;	//se proximo caracter eh \r, entao antes desse \r\n, adiciona a string entrada
			}
			concatenado += stringArea.charAt(j);//concatena tudo, inclusive o \r\n que vem depois de ter concatenado a string entrada
		}	
		concatenado += entrada;	//depois de concatenar o texto todo, adiciona a string entrada no final do arquivo
		return concatenado;
	} 


}
