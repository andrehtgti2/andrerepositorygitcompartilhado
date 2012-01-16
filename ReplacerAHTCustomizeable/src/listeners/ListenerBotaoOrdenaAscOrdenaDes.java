package listeners;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListenerBotaoOrdenaAscOrdenaDes implements ActionListener {

	TextArea areaDestino;
	TextArea areaFonte;
	String ascDes;
	
	public ListenerBotaoOrdenaAscOrdenaDes(TextArea areaFonte, TextArea areaDestino, String ascDes) {
		this.areaDestino = areaDestino;
		this.areaFonte = areaFonte;
		this.ascDes = ascDes;
	}

	public void actionPerformed(ActionEvent e) {
		String lido = areaFonte.getText();
		areaDestino.setText(ordenaStringAreaRetornaEntrada(lido, ascDes));
	}
	
	public String ordenaStringAreaRetornaEntrada(String stringArea, String ascDes){
		String concatenado = "";
		String linha = "";
				
		//converte a String para ArrayList
		List lista = new ArrayList();
		for (int j = 0; j < stringArea.length(); j++) {
			if(stringArea.charAt(j)=='\r'){
				lista.add(linha);
				linha = "";
			}
			if(stringArea.charAt(j)!='\r' && stringArea.charAt(j)!='\n')
			linha += stringArea.charAt(j);
		}	
		lista.add(linha);
				
		Collections.sort(lista);	//ordena por ordem alfabetica
		String[] array = (String[]) lista.toArray(new String[0]);
		
		//transforma o array para uma String unica
		if(ascDes.trim().equalsIgnoreCase("ASC")){
			//coloca em orderm crescente
			for (int i = 0; i < array.length; i++) {
				concatenado += array[i] + "\r\n";
			}
		}else{
			//colcoa em ordem decrescente
			for (int i = array.length - 1; i >= 0 ; i--) {
				concatenado += array[i] + "\r\n";
			}
		}
		return concatenado;
	} 


}
