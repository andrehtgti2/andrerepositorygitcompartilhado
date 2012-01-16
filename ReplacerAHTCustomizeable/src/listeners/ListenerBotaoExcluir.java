package listeners;

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import util.AtualizaCombo;
import util.FileNames;


public class ListenerBotaoExcluir implements ActionListener {

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
	
	public ListenerBotaoExcluir(TextArea textAreaIn, TextArea textAreaOut, JComboBox comboBox, TextField textField, TextField textFieldComecoDaLinha, 
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
		
		int resposta = 1;
		
		switch (comboBox.getSelectedIndex()) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			JOptionPane.showMessageDialog(null, "Não é permitido excluir Template Modelo número 0, 1, 2, 3, 4, 5, 6, 7 e 8 !", "Operação não permitida", JOptionPane.ERROR_MESSAGE);
			break;
		default:			
			resposta = JOptionPane.showConfirmDialog(null, "Confirma Exclusão ?¿");
			break;
		}
		

		if(resposta==0){
			
			int itemSelecionado;
			if(comboBox.getSelectedIndex()!=0){
				
				String itemStringSelecionada = (String)comboBox.getItemAt(comboBox.getSelectedIndex());
				String[] itemSeparado = itemStringSelecionada.split("-");
				
				//pega o numero do elemento selecionado
				itemSelecionado = Integer.parseInt(itemSeparado[0]);
									
				File fInTemplates = new File(FileNames.IN_TEMPLATE_NAME + (itemSelecionado) +".aht");
				File fOutTemplates = new File(FileNames.OUT_TEMPLATE_NAME + (itemSelecionado) +".aht");
				
				//apaga os arquivos de template
				if(!fInTemplates.delete()){
					JOptionPane.showMessageDialog(null, "Erro ao excluir arquivo " + FileNames.IN_TEMPLATE_NAME + (itemSelecionado) +".aht");
				}
				if(!fOutTemplates.delete()){
					JOptionPane.showMessageDialog(null, "Erro ao excluir arquivo " + FileNames.OUT_TEMPLATE_NAME + (itemSelecionado) +".aht");
				}
				
				//remove dos arquivos de properties da combo e do beginLine e endLine os valores referentes ao valor da combo selecionado para ser excluido
				propComboList.remove(String.valueOf(itemSelecionado));
				propEndLineTemplates.remove(String.valueOf(itemSelecionado));
				propBeginLineTemplates.remove(String.valueOf(itemSelecionado));
				
								
				try {
					//grava os arquivos logicos de properties no arquivo fisico
					propComboList.store(new FileOutputStream(fPropComboList),"ComboList");
					propEndLineTemplates.store(new FileOutputStream(fPropEndLineTemplates),"EndLine");
					propBeginLineTemplates.store(new FileOutputStream(fPropBeginLineTemplates),"BeginLine");
				} catch (Throwable e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
				}
								
				//limpa a combo
				comboBox.removeAllItems();
			
				//coloca na combo os valores do arquivo de properties da combo
				ArrayList lista = AtualizaCombo.converteElementosDoPropertieEmArrayList(propComboList);
				for (int i = 0; i < lista.size(); i++) {
					comboBox.addItem(lista.get(i));					
				}
				

			
				FileReader inTemplatesReader = null;
				FileReader outTemplatesReader = null;
				try {
					fInTemplates = new File(FileNames.IN_TEMPLATE_NAME + 0 +".aht");
					fOutTemplates = new File(FileNames.OUT_TEMPLATE_NAME + 0 +".aht");
					inTemplatesReader = new FileReader(fInTemplates);
					outTemplatesReader = new FileReader(fOutTemplates);
				} catch (Throwable e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
				}
				
				//seta todas as textAreas e textFields com o template de numero zero (o primeiro da lista da combo)
				comboBox.setSelectedIndex(0);
				textAreaIn.setText(leFileReaderDevolveString(inTemplatesReader));
				textAreaOut.setText(leFileReaderDevolveString(outTemplatesReader));
				textField.setText(propEndLineTemplates.getProperty(Integer.toString(0)));
				textFieldComecoDaLinha.setText(propBeginLineTemplates.getProperty(Integer.toString(0)));
				
				
				try {
					//fecha FileReader
					inTemplatesReader.close();
					outTemplatesReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
				}
				

			}
			
		}

	}
	
	private String leFileReaderDevolveString(FileReader fileReader){
		String concatenado = "";
		String lido = "";
		char c;
		int caracter;
		try {
			caracter = fileReader.read();		
			if(caracter != -1){
				c=(char)caracter;
				concatenado += c;
			}
			while(caracter!=-1){
				lido+=(char)caracter;					
				caracter= fileReader.read();
				c=(char)caracter;
				if(caracter!=-1){
					concatenado += c;
				}				
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
		}
		return concatenado;
	}

}
