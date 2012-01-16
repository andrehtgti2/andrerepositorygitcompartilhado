package listeners;

import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ListenerBotaoConverte implements ActionListener{
	
	TextArea areaFrom;
	TextArea areaTo; 
	TextArea areaAlvo; 
	TextArea areaDestino; 
	
	
	public ListenerBotaoConverte(TextArea areaFrom, TextArea areaTo, TextArea areaAlvo, TextArea areaDestino) {
		this.areaFrom = areaFrom;
		this.areaTo = areaTo;
		this.areaAlvo = areaAlvo;
		this.areaDestino = areaDestino;
	}
	

	public void actionPerformed(ActionEvent e) {
		boolean erro = false;
		areaDestino.setText("");
		String stringAreaFrom = areaFrom.getText();
		String stringAreaTo = areaTo.getText();
		ArrayList listaFrom = transformaStringEmArrayList(stringAreaFrom);
		ArrayList listaTo = transformaStringEmArrayList(stringAreaTo);
		if(listaFrom.size()!=listaTo.size()){
			erro = true;
			JOptionPane.showMessageDialog(null,"       ERRO ! O número de elementos substituidos (TextArea1)\n" +
												"deve ser igual ao número de elementos a serem substituidos (TextArea2)\n " +
												" (TextArea 1 deve ter a mesma quantidade de linhas que a TextArea 2)", "Erro !", 
			JOptionPane.ERROR_MESSAGE);
		}
		if(!erro){
			String stringAlvo = areaAlvo.getText();
			for (int i = 0; i < listaFrom.size(); i++) {
				if((String)listaTo.get(i)==""){
						for (int j = 0; j < stringAlvo.length(); j++) {
							stringAlvo = stringAlvo.replaceAll((String)listaFrom.get(i), "");
					}
				}else{
					stringAlvo = stringAlvo.replaceAll((String)listaFrom.get(i), (String)listaTo.get(i));
				}
			}
			areaDestino.setText(stringAlvo);
			
		}
	}
	
	
	private ArrayList transformaStringEmArrayList(String stringArea){
		String linha = "";
		boolean passou = false;
		ArrayList lista = new ArrayList();
		for (int j = 0; j < stringArea.length(); j++) {
			if(stringArea.charAt(j)!='\r' && stringArea.charAt(j)!='\n'){//esse if para nao armazernar os caracteres de controle de fim de arquivo (\r\n)
				linha += stringArea.charAt(j);	
			}			
			if(stringArea.charAt(j)=='\r'){
				lista.add(linha);
				linha = "";
			}
			passou=true;
		}
		if(passou){
			lista.add(linha);		//para adicionar o ultimo elemento quando o caracter lido acima for fim do arquivo 
		}		
		return lista;
	} 
	
	
	
	   

	


}
