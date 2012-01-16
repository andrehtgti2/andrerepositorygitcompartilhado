package listeners;

import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListenerBotaoColocarNoComecoDeCadaLinha implements ActionListener{

	TextArea areaDestino;
	TextField textField;
	public ListenerBotaoColocarNoComecoDeCadaLinha(TextArea areaDestino, TextField textField) {
		this.areaDestino = areaDestino;
		this.textField = textField;
	}
	
	public static String adicionaNoComecoDaLinha(String stringArea, String entrada){
		String concatenado = "";
		String linha = "";
		for (int j = 0; j < stringArea.length(); j++) {
			if(stringArea.charAt(j)=='\r'){
				concatenado += entrada + linha + "\r\n";	//monta novo texto com a string entrada no comeco dessa nova linha mais a linha que foi concatenada e os caracteres de fim de linha no final dessa linha
				linha = "";	//limpa variavel linha, inicia uma nova linha
			}
			if(stringArea.charAt(j)!='\r' && stringArea.charAt(j)!='\n')
				linha += stringArea.charAt(j);	//vai concatenando a linha toda, nao concatena \r\n
		}	
		concatenado += entrada + linha;//monta novo texto com a string entrada no comeco dessa nova linha mais a linha que foi concatenada
		return concatenado;
	}



	public void actionPerformed(ActionEvent e) {
			String lido = areaDestino.getText();
			String entrada = textField.getText();
			areaDestino.setText(ListenerBotaoColocarNoComecoDeCadaLinha.adicionaNoComecoDaLinha(lido, entrada));
	}
		



}
