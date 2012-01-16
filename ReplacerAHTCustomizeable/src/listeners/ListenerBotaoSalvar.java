package listeners;

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import util.AtualizaCombo;
import util.FileNames;

public class ListenerBotaoSalvar implements ActionListener {

	TextArea textAreaIn;
	TextArea textAreaOut;
	JComboBox comboBox;
	TextField textField;
	TextField textFieldComecoDaLinha; 
	File fPropComboList;
	Properties propComboList;
	File fPropEndLineTemplates;
	Properties propEndLineTemplates;
	File fPropBeginLineTemplates;
	Properties propBeginLineTemplates;
	
	public ListenerBotaoSalvar(TextArea textAreaIn, TextArea textAreaOut, JComboBox comboBox, TextField textField, TextField textFieldComecoDaLinha, 
			File fPropComboList, Properties propComboList, File fPropEndLineTemplates, Properties propEndLineTemplates, File fPropBeginLineTemplates, Properties propBeginLineTemplates) {
		this.textAreaIn = textAreaIn;
		this.textAreaOut = textAreaOut;
		this.comboBox = comboBox;
		this.textField = textField;
		this.textFieldComecoDaLinha = textFieldComecoDaLinha;
		this.fPropComboList = fPropComboList;
		this.propComboList = propComboList;
		this.fPropEndLineTemplates = fPropEndLineTemplates;
		this.propEndLineTemplates = propEndLineTemplates;
		this.fPropBeginLineTemplates = fPropBeginLineTemplates;
		this.propBeginLineTemplates = propBeginLineTemplates;
	}

	public void actionPerformed(ActionEvent e) {
		
		String nomeItemCombo = "";		
		ArrayList listaFrom = transformaStringEmArrayList(textAreaIn.getText());
		ArrayList listaTo = transformaStringEmArrayList(textAreaOut.getText());
		
		//verifica se ambas textArea1 e 2 possuem a mesma quantidade de elementos
		if(listaFrom.size()!=listaTo.size()){
			JOptionPane.showMessageDialog(null,"       ERRO ! O número de elementos substituidos (TextArea1)\n" +
												"deve ser igual ao número de elementos a serem substituidos (TextArea2)\n " +
												" (TextArea 1 deve ter a mesma quantidade de linhas que a TextArea 2)", "Erro !", JOptionPane.ERROR_MESSAGE);
			nomeItemCombo = null;
		}else{
			//pergunta o nome do novo template - esse nome será um elemento novo na combo
			nomeItemCombo = JOptionPane.showInputDialog("Qual o nome do template a ser salvo ?");
		}
		
		if(nomeItemCombo != null){
			
			//pega elemento NumeroDoProximoElemento, se nao achar, retorna 0
			int numeroDoProximoElemento = Integer.parseInt(propComboList.getProperty("NumeroDoProximoElemento","0"));		
			
			File fInTemplates = new File(FileNames.IN_TEMPLATE_NAME + (numeroDoProximoElemento) + FileNames.EXTENSAO_AHT);
			File fOutTemplates = new File(FileNames.OUT_TEMPLATE_NAME + (numeroDoProximoElemento) + FileNames.EXTENSAO_AHT);
			
			
			//seta os novos valores nos arquivos logicos de properties
			//seta numeroDoProximoElemento
			propComboList.setProperty("NumeroDoProximoElemento",String.valueOf(numeroDoProximoElemento+1));
			//seta o nome do novo item na combo
			propComboList.setProperty(Integer.toString(numeroDoProximoElemento),nomeItemCombo);
			//seta as Strings de begin e endLine
			propEndLineTemplates.setProperty(String.valueOf(numeroDoProximoElemento), textField.getText());
			propBeginLineTemplates.setProperty(String.valueOf(numeroDoProximoElemento), textFieldComecoDaLinha.getText());
			
			
			try {
				//grava os arquivos logicos de properties nos arquivos fisicos
				propComboList.store(new FileOutputStream(fPropComboList),"ComboList");
				propEndLineTemplates.store(new FileOutputStream(fPropEndLineTemplates),"EndLine");
				propBeginLineTemplates.store(new FileOutputStream(fPropBeginLineTemplates),"BeginLine");
				
			} catch (Throwable e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
			}
		
			
			try {							
				//grava o novo arquivo de template IN
				FileWriter inTemplatesWriter = new FileWriter(fInTemplates);
				inTemplatesWriter.write(textAreaIn.getText());
				inTemplatesWriter.flush();
				inTemplatesWriter.close();
				
				//grava o novo arquivo de template OUT
				FileWriter outTemplatesWriter = new FileWriter(fOutTemplates);
				outTemplatesWriter.write(textAreaOut.getText());
				outTemplatesWriter.flush();
				outTemplatesWriter.close();
				
			} catch (Throwable e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
			}
						
			//remove todos os elementos da combo
			comboBox.removeAllItems();
			//carrega os novos valores da combo utilizando o arquivo de properties da combo
			ArrayList lista = AtualizaCombo.converteElementosDoPropertieEmArrayList(propComboList);
			for (int i = 0; i < lista.size(); i++) {
				comboBox.addItem(lista.get(i));
				
			}
			comboBox.setSelectedIndex(lista.size()-1);		
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














