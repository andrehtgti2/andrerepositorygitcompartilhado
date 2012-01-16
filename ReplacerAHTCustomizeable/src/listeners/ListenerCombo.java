package listeners;

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import util.FileNames;

public class ListenerCombo implements ActionListener {
	
	
	
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
	
	public ListenerCombo(TextArea textAreaIn, TextArea textAreaOut, JComboBox comboBox, TextField textField, TextField textFieldComecoDaLinha,
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
		int numeroDoProximoElemento = Integer.parseInt(propComboList.getProperty("NumeroDoProximoElemento"));		
		int itemSelecionado;
		//se nao tiver valor na combo, procura o template com maior numero até encontrar
		if(comboBox.getSelectedIndex()==-1){
			itemSelecionado = numeroDoProximoElemento - 1;
			File file = new File(FileNames.IN_TEMPLATE_NAME + (itemSelecionado) +".aht");
			while(!file.exists()){
				itemSelecionado = itemSelecionado -1;
				file = new File(FileNames.IN_TEMPLATE_NAME + (itemSelecionado) +".aht");
			}
		}else{//se tiver valor na combo			
			String itemStringInteiraSelecionada = (String)comboBox.getItemAt(comboBox.getSelectedIndex());
			//System.out.println(itemStringSelecionada);
			String[] itemSeparado = itemStringInteiraSelecionada.split("-");
			itemSelecionado = Integer.parseInt(itemSeparado[0]);//pega só o número do item selecionado
		}
		
		File fInTemplates = new File(FileNames.IN_TEMPLATE_NAME + (itemSelecionado) +".aht");
		File fOutTemplates = new File(FileNames.OUT_TEMPLATE_NAME + (itemSelecionado) +".aht");
		
		FileReader inTemplatesReader = null;
		FileReader outTemplatesReader = null;
		try {
			//abre arquivos templates IN e OUT para leitura
			inTemplatesReader = new FileReader(fInTemplates);
			outTemplatesReader = new FileReader(fOutTemplates);
		} catch (Throwable e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
		}
		
		//seta as textAreas e textFields com os valores dos arquivos de properties
		textAreaIn.setText(leFileReaderDevolveString(inTemplatesReader));
		textAreaOut.setText(leFileReaderDevolveString(outTemplatesReader));
		textField.setText(propEndLineTemplates.getProperty(Integer.toString(itemSelecionado)));
		textFieldComecoDaLinha.setText(propBeginLineTemplates.getProperty(Integer.toString(itemSelecionado)));
		
		try {
			//fecha arquivos de leitura
			inTemplatesReader.close();
			outTemplatesReader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
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
				//System.out.println(caracter);
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
