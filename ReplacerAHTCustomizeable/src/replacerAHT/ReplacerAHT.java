package replacerAHT;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import listeners.ListenerBotaoColocarNoComecoDeCadaLinha;
import listeners.ListenerBotaoColocarNoFinalDeCadaLinha;
import listeners.ListenerBotaoConverte;
import listeners.ListenerBotaoExcluir;
import listeners.ListenerBotaoLimpaTextArea4;
import listeners.ListenerBotaoMaiusculo;
import listeners.ListenerBotaoMinusculo;
import listeners.ListenerBotaoOrdenaAscOrdenaDes;
import listeners.ListenerBotaoSalvar;
import listeners.ListenerCombo;
import util.AtualizaCombo;
import util.FileNames;


public class ReplacerAHT extends JFrame{
	
	
	//ALTERADO PELO ANDRE
	
	
	
	//Adicinado de novo pelo André
	
	
//------------------------------------------------------------------------------------------------------------------------------------------	
	//constantes
	public static final String tituloTela = "ReplacerAHT v7.0 - Java6 - Customizable ... André Hiroshi Tanaka - Ænd®& - 08/02/2010 - (tamanhoInicial.conf) - Combo";
	public static final int larguraMaior = 58;		//larguraMenor = 50		//larguraMaior = 58
	public static final int alturaMaior = 15;		//alturaMenor  = 15		//alturaMaior = 18
	public static final int larguraMenor = 50;		//larguraMenor = 50		//larguraMaior = 58
	public static final int alturaMenor = 12;		//alturaMenor  = 15		//alturaMaior = 18
	public static final String finalDeLinha = "\r\n";
	public static final String tabulacao = "\t";
	
	//Botoes
	public static Button bAdicionaNoComecoDasLinhas = new Button("Adiciona no começo das linhas da TextArea4 a String: ");
	public static Button bLimpaTextArea4 = new Button("Limpa TextArea4");
	public static Button bMinusculo = new Button("Converter Tudo da TextArea3 para Minusculo");
	public static Button bOrdenaCrescente = new Button("Ordena as linhas da TextArea3 em ordem crescente");
	public static Button bOrdenaDecrescente = new Button("Ordena as linhas da TextArea3 em ordem decrescente");
	public static Button bInverteString = new Button("Inverte String");
	public static Button bConverte = new Button("Clique Aqui para Substituir Tudo o que está na TextArea1 pelo conteúdo da TextArea2");
	public static Button bMaiusculo = new Button("Converter Tudo da TextArea3 para Maiusculo");
	public static Button bAdicionaNoFinalDasLinhas = new Button("Adiciona no final das linhas da TextArea4 a String: ");
	public static Button bSave = new Button("Salvar como Template");
	public static Button bDelete = new Button("Excluir Template da Combo");
	public static Button bResize = new Button("Resize"); 
	public static Button bSeparador = new Button("");
	

	//TextFields
	public static TextField textFieldComecoDaLinha = new TextField("");
	public static TextField textField = new TextField("");
	
	//															 ____________	
	//TextAreas													|	  |		 |
	public static TextArea areaFrom;		//TextArea1			|  1  |  2   |
	public static TextArea areaTo;			//TextArea2			|-----|------|
	public static TextArea areaAlvo;		//TextArea3			|  3  |  4   |
	public static TextArea areaDestino;		//TextArea4			|_____|______|
	
	//Paines
	public static Panel pTextAreasELabelsDescritivos = new Panel(new GridLayout(2,2));
	public static Panel pAreaFromEtitulo1 = new Panel(new FlowLayout());
	public static Panel pAreaToEtitulo1 = new Panel(new FlowLayout());
	public static Panel pAreaAlvoEtitulo1 = new Panel(new FlowLayout());
	public static Panel pAreaDestinoEtitulo1 = new Panel(new FlowLayout());
	public static Panel pBotoes = new Panel(new GridLayout(8,1));
	public static Panel pBotoesInternosMaiusculoComeco = new Panel(new GridLayout(1,3));	
	public static Panel pBotoesSubstituiLimpa = new Panel(new GridLayout(1,2));	
	public static Panel pBotoesOrdenar = new Panel(new GridLayout(1,2));		
	public static Panel pBotoesInternosMinusculoFinal = new Panel(new GridLayout(1,3));
	public static Panel pBotoesSaveLoad = new Panel(new GridLayout(1,4));
	public static Panel pSeparador = new Panel();

	//Labels
	public static Label lFrom = new Label("TextArea 1 - Substituir todas as palavras abaixo: ... ");
	public static Label lTo = new Label("TextArea 2 - ...por essas palavras abaixo: ... ");
	public static Label lAlvo = new Label("TextArea 3 - Texto onde as modificações serão aplicadas...");
	public static Label lDestino = new Label("TextArea 4 - Resultado de todas as substituições...");
	public static Label lSalvar = new Label("Escolha o Template:    ");
	
	//Combo
	public static JComboBox combobox = new JComboBox();
	
	//LookAndFeel
	private UIManager.LookAndFeelInfo[] looks =   UIManager.getInstalledLookAndFeels();
//------------------------------------------------------------------------------------------------------------------------------------------

	public ReplacerAHT() throws HeadlessException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
//##########################################################################################################################################
//##########################################################################################################################################	
//##########################################################################################################################################	
	
	
	public static void main(String[] args) throws Exception {
		
		
		//------------------------------------------------------------------------------------------------------------------------------------------		
		
		Properties propComboList = new Properties();
		Properties propEndLineTemplate = new Properties();
		Properties propBeginLineTemplate = new Properties();	
		
		//------------------------------------------------------------------------------------------------------------------------------------------	
		
		File fPropComboList = new File(FileNames.COMBO_LIST_NAME + FileNames.EXTENSAO_PROPERTIES);		
		File fPropEndLineTemplates = new File(FileNames.END_LINE_TEMPLATE + FileNames.EXTENSAO_PROPERTIES);
		File fPropBeginLineTemplates = new File(FileNames.BEGIN_LINE_TEMPLATE + FileNames.EXTENSAO_PROPERTIES);
		
		//------------------------------------------------------------------------------------------------------------------------------------------		
		
		//caso nao exista, cria o arquivo que contem os valores da Combo de Templates
		geraArquivoCombo();
		
		//caso nao exista, cria os arquivos que contem todos os começos e fins de linhas que serão utilizados para preencher as textField de textField e textFieldComecoDaLinha
		geraArquivosFimEComecodeLinha();
		
		//caso nao exista, cria os arquivos templates para a TextArea1 e TextArea2 sereme preenchidas
		geraArquivosTemplatesInOut();	
						
		//------------------------------------------------------------------------------------------------------------------------------------------
		
		//abre os arquivos de properties da combo e dos textField e textFieldComedoDaLinha e carrega na memoria
		try {
			propComboList.load(new FileInputStream(FileNames.COMBO_LIST_NAME + FileNames.EXTENSAO_PROPERTIES));
			propEndLineTemplate.load(new FileInputStream(FileNames.END_LINE_TEMPLATE + FileNames.EXTENSAO_PROPERTIES));
			propBeginLineTemplate.load(new FileInputStream(FileNames.BEGIN_LINE_TEMPLATE + FileNames.EXTENSAO_PROPERTIES));
		} catch (Throwable e3) {
			e3.printStackTrace();
			JOptionPane.showMessageDialog(null, e3.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
		}
		
		//------------------------------------------------------------------------------------------------------------------------------------------		
		
		//preencheACombo
		ArrayList lista = AtualizaCombo.converteElementosDoPropertieEmArrayList(propComboList);
		combobox.removeAllItems();
		for (int i = 0; i < lista.size(); i++) {
			combobox.addItem(lista.get(i));
		}
		//combobox.setEnabled(true);
		combobox.setMaximumRowCount(4);
		combobox.setSelectedIndex(0);
		
		//------------------------------------------------------------------------------------------------------------------------------------------		

		//carrega e le arquivo de configuração inicial para inicializar a tela com tamanho 900x800 ou 800x700
		File fileLer = new File("tamanhoInicial_900x800_ou_800x700.conf");		
		FileReader leitor;
		int caracter=0;
		String lido ="";
		char c;
		String concatenado = "";
		String[] vetorParametros = new String[2];	
		
		//verificando se existe arquivo de configuração de tamanho de tela, cria
		if(!fileLer.exists()){
			fileLer.createNewFile();
			FileWriter fileWriter = new FileWriter(fileLer);
			fileWriter.write("900,800");
			fileWriter.flush();
			fileWriter.close();
		}	
		
		//lê arquivo
		try{
			leitor = new FileReader(fileLer);
			caracter = leitor.read();
			if(caracter != -1){
				c=(char)caracter;
				concatenado += c;
			}			
			while(caracter!=-1){
				lido+=(char)caracter;					
				caracter= leitor.read();
				//System.out.println(caracter);
				c=(char)caracter;
				if(caracter!=-1){
					concatenado += c;
				}				
			}
			vetorParametros[0] = "";
			vetorParametros[1] = "";
			
			vetorParametros = concatenado.split(",");
			if(vetorParametros.length!=2){
				vetorParametros = concatenado.split("x");
			}
			leitor.close();

			System.out.println(concatenado);
			
			ReplacerAHT tela = new ReplacerAHT();
			
			if(vetorParametros.length != 2 || vetorParametros[0] == null ||  vetorParametros[1] == null || vetorParametros[0].trim().equals("")  || vetorParametros[1].trim().equals("")){
				JOptionPane.showMessageDialog(null, "Faltando parametros do tamanho da tela no arquivo tamanhoInicial_900x800_ou_800x700.conf \nArquivo deve conter uma string no formato '900,800' ou '800,700' !");
				throw new Exception("Faltando parametros do tamanho da tela no arquivo tamanhoInicial_900x800_ou_800x700.conf \nArquivo deve conter uma string no formato '900,800' ou '800,700' !");
			}
			
			//seta as telas e os componetes com um dos dois tamanhos padrões
			tela.setTitle(tituloTela);
			tela.setSize(Integer.parseInt(vetorParametros[0]), Integer.parseInt(vetorParametros[1]));
			
			if(tela.getSize().height == 700){
				areaFrom = new TextArea(alturaMenor,larguraMenor);
				areaTo = new TextArea(alturaMenor, larguraMenor);
				areaAlvo = new TextArea(alturaMenor, larguraMenor);
				areaDestino = new TextArea(alturaMenor, larguraMenor);
			}else{
				areaFrom = new TextArea(alturaMaior,larguraMaior);
				areaTo = new TextArea(alturaMaior,larguraMaior);
				areaAlvo = new TextArea(alturaMaior,larguraMaior);
				areaDestino = new TextArea(alturaMaior,larguraMaior);
			}
			System.out.println("if -> " + tela.getSize().height);
			
			
	
			//------------------------------------------------------------------------------------------------------------------------------------------			
			//------------------------------------------------------------------------------------------------------------------------------------------
			//------------------------------------------------------------------------------------------------------------------------------------------			
			
			//montando tela
			
			pBotoesInternosMaiusculoComeco.add(bMaiusculo);
			pBotoesInternosMaiusculoComeco.add(bAdicionaNoComecoDasLinhas);
			pBotoesInternosMaiusculoComeco.add(textFieldComecoDaLinha);
			bMaiusculo.setForeground(Color.BLUE);

			pBotoesInternosMinusculoFinal.add(bMinusculo);
			pBotoesInternosMinusculoFinal.add(bAdicionaNoFinalDasLinhas);
			pBotoesInternosMinusculoFinal.add(textField);
			bMinusculo.setForeground(Color.BLUE);
			
			lSalvar.setAlignment(Label.RIGHT);
			pBotoesSaveLoad.add(bSave);
			pBotoesSaveLoad.add(bDelete);
			pBotoesSaveLoad.add(lSalvar);
			pBotoesSaveLoad.add(combobox);
			
			pBotoesSubstituiLimpa.add(bConverte);
			bConverte.setForeground(Color.RED);
			bConverte.setBackground(Color.BLACK);
			//pBotoesSubstituiLimpa.add(cabe mais um botao aqui);
			
			pBotoesOrdenar.add(bOrdenaCrescente);
			pBotoesOrdenar.add(bOrdenaDecrescente);

			pBotoes.add(bSeparador);
			pBotoes.add(bLimpaTextArea4);
			pBotoes.add(pBotoesInternosMaiusculoComeco);			
			pBotoes.add(pBotoesInternosMinusculoFinal);
			pBotoes.add(pBotoesSaveLoad);
			pBotoes.add(pBotoesSubstituiLimpa);
			pBotoes.add(pBotoesOrdenar);
			
			//para colocar scroolBar no JTextArea areaFrom (no TextArea já vem por padrão com ScroolBar)
			//JScrollPane scrollPane = new JScrollPane(areaFrom);
			pAreaFromEtitulo1.add(lFrom);
			pAreaFromEtitulo1.add(areaFrom);//TextArea1
			
			pAreaToEtitulo1.add(lTo);
			pAreaToEtitulo1.add(areaTo);//TextArea2
			
			pAreaAlvoEtitulo1.add(lAlvo);
			pAreaAlvoEtitulo1.add(areaAlvo);//TextArea3
			
			pAreaDestinoEtitulo1.add(lDestino);
			pAreaDestinoEtitulo1.add(areaDestino);//TextArea4
			
			//															 ____________	
			//TextAreas													|	  |		 |
			//public static TextArea areaFrom;		//TextArea1			|  1  |  2   |
			//public static TextArea areaTo;		//TextArea2			|-----|------|
			//public static TextArea areaAlvo;		//TextArea3			|  3  |  4   |
			//public static TextArea areaDestino;	//TextArea4			|_____|______|
							
			pTextAreasELabelsDescritivos.add(pAreaFromEtitulo1);
			pTextAreasELabelsDescritivos.add(pAreaToEtitulo1);
			pTextAreasELabelsDescritivos.add(pAreaAlvoEtitulo1);
			pTextAreasELabelsDescritivos.add(pAreaDestinoEtitulo1);
		
			tela.getContentPane().setLayout(new BorderLayout());
			tela.getContentPane().add(pTextAreasELabelsDescritivos,BorderLayout.CENTER);
			tela.getContentPane().add(pBotoes,BorderLayout.SOUTH);
			tela.getContentPane().add(pSeparador, BorderLayout.NORTH);
			
			//tela.setSize(900,800);		//larguraMaior = 900, alturaMaior = 800		//larguraMenor = 800, alturaMenor = 700			

			tela.setResizable(false);
			areaDestino.setEditable(false);
	
			tela.mudaLookAndFeel(1);
			
			//------------------------------------------------------------------------------------------------------------------------------------------
			//------------------------------------------------------------------------------------------------------------------------------------------
			//------------------------------------------------------------------------------------------------------------------------------------------
//##################################################################################################################################################################
//##################################################################################################################################################################
//##################################################################################################################################################################
//##################################################################################################################################################################
//##################################################################################################################################################################
//##################################################################################################################################################################			
			//PREENCHE PRE
			//Inicializa Campos		
			
			areaFrom.setText(
				"A" + finalDeLinha +
				"a" + finalDeLinha +
				"E" + finalDeLinha +
				"e" + finalDeLinha +
				"I" + finalDeLinha +
				"i" + finalDeLinha +
				"O" + finalDeLinha +
				"o" + finalDeLinha +
				"U" + finalDeLinha +
				"U" + finalDeLinha +
				"B" + finalDeLinha +
				"b" + finalDeLinha +
				"S" + finalDeLinha +
				"s" + finalDeLinha +
				"T" + finalDeLinha +
				"t"
			);
			
				
			areaTo.setText(
				"4" + finalDeLinha +
				"4" + finalDeLinha +
				"3" + finalDeLinha +
				"3" + finalDeLinha +
				"1" + finalDeLinha +
				"1" + finalDeLinha +
				"0" + finalDeLinha +
				"0" + finalDeLinha +
				"U" + finalDeLinha +
				"U" + finalDeLinha +
				"8" + finalDeLinha +
				"8" + finalDeLinha +
				"5" + finalDeLinha +
				"5" + finalDeLinha +
				"7" + finalDeLinha +
				"7"
			);
						
			areaAlvo.setText("");
			areaDestino.setText("");
			combobox.setSelectedIndex(10);
			
//##################################################################################################################################################################
//##################################################################################################################################################################
//##################################################################################################################################################################			
//##################################################################################################################################################################
//##################################################################################################################################################################
//##################################################################################################################################################################
			//------------------------------------------------------------------------------------------------------------------------------------------
			
			//associa botoes a listeners
			bConverte.addActionListener(new ListenerBotaoConverte(areaFrom,areaTo,areaAlvo,areaDestino));			
			bMaiusculo.addActionListener(new ListenerBotaoMaiusculo(areaAlvo,bMaiusculo));			
			bMinusculo.addActionListener(new ListenerBotaoMinusculo(areaAlvo,bMaiusculo));
			bAdicionaNoFinalDasLinhas.addActionListener(new ListenerBotaoColocarNoFinalDeCadaLinha(areaDestino,textField));
			bAdicionaNoComecoDasLinhas.addActionListener(new  ListenerBotaoColocarNoComecoDeCadaLinha(areaDestino,textFieldComecoDaLinha));
			bDelete.addActionListener(new ListenerBotaoExcluir(areaFrom, areaTo,combobox,  textField, textFieldComecoDaLinha, fPropComboList, propComboList, fPropEndLineTemplates, propEndLineTemplate, fPropBeginLineTemplates, propBeginLineTemplate));
			bLimpaTextArea4.addActionListener(new ListenerBotaoLimpaTextArea4(areaDestino));
			bOrdenaCrescente.addActionListener(new ListenerBotaoOrdenaAscOrdenaDes(areaAlvo, areaAlvo, "ASC"));
			bOrdenaDecrescente.addActionListener(new ListenerBotaoOrdenaAscOrdenaDes(areaAlvo, areaAlvo, "DES"));
			bSave.addActionListener(new ListenerBotaoSalvar(areaFrom, areaTo,combobox, textField, textFieldComecoDaLinha, fPropComboList, propComboList, fPropEndLineTemplates, propEndLineTemplate, fPropBeginLineTemplates, propBeginLineTemplate));		
			combobox.addActionListener(new ListenerCombo(areaFrom, areaTo,combobox,  textField, textFieldComecoDaLinha, fPropComboList, propComboList, fPropEndLineTemplates, propEndLineTemplate, fPropBeginLineTemplates, propBeginLineTemplate));
			
			//------------------------------------------------------------------------------------------------------------------------------------------
		
	
			tela.show();
		}catch(Exception e){
			Exception E = new Exception("Erro ! Favor comunicar esse erro ao AndréHT " + e.getMessage());
			JOptionPane.showMessageDialog(null, e.getMessage());
			E.printStackTrace();
			throw e;
		}
	}
	
	
	
//##########################################################################################################################################	
//##########################################################################################################################################
//##########################################################################################################################################
//##########################################################################################################################################

	
	
	
	
	
	
	
	public void mudaLookAndFeel(int i) throws Exception{   
        try {   
        System.out.println("Quantidade de LookAndFeel instalados= " + looks.length);
        if(i<looks.length){
        	UIManager.setLookAndFeel(looks[i].getClassName());
        }
        else{
        	UIManager.setLookAndFeel(looks[looks.length].getClassName());
        }                  
        SwingUtilities.updateComponentTreeUI(this);   
        }catch(Exception e) {  
        	JOptionPane.showMessageDialog(null, e.getMessage());
        	e.printStackTrace();   
        	throw e;
        }   
	}
	
	
//------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public static void escreveArquivo(String nomeDoArquivo, String conteudo){
		try {
			File file = new File(nomeDoArquivo);
			if(!file.exists()){
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(conteudo);
				fileWriter.flush();
				fileWriter.close();
			}			
		} catch (Throwable e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
//------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public static void geraArquivosFimEComecodeLinha(){
		Properties propEndLineTemplate = new Properties();
		Properties propBeginLineTemplate = new Properties();
		File fPropEndLineTemplates = new File(FileNames.END_LINE_TEMPLATE + FileNames.EXTENSAO_PROPERTIES);
		File fPropBeginLineTemplates = new File(FileNames.BEGIN_LINE_TEMPLATE + FileNames.EXTENSAO_PROPERTIES);
		
		//caso nao exista, cria os arquivos que contem todos os começos e fins de linhas que serão utilizados para preencher as textField de nomes: textField e textFieldComecoDaLinha
		if(!fPropEndLineTemplates.exists()){
			try {
				fPropEndLineTemplates.createNewFile();
				propEndLineTemplate.setProperty("0","");			//propComboList.setProperty("0", "Escolha um padrão aqui");
				propEndLineTemplate.setProperty("1","\"),");		//propComboList.setProperty("1", "GeraITO-PreencheITO");
				propEndLineTemplate.setProperty("2"," //Vogal para numero");	//propComboList.setProperty("2", "Vogal -----> Número");
				propEndLineTemplate.setProperty("3"," //Numero para vogal");	//propComboList.setProperty("3", "Número -----> Vogal");
				propEndLineTemplate.setProperty("4"," //Apaga algumas vogais");		//propComboList.setProperty("4", "Apaga Algumas Vogais");
				propEndLineTemplate.setProperty("5"," //Retira quebra de linhas");	//propComboList.setProperty("5", "Retira Quebra de Linhas");
				propEndLineTemplate.setProperty("6"," //Retira tabulacoes");		//propComboList.setProperty("6", "Retira Tabulações");
				propEndLineTemplate.setProperty("7","\" + \"\\r\\n\" + ");		//propComboList.setProperty("7", "Transforma em String Java 1");
				propEndLineTemplate.setProperty("8","\" + \"\\r\\n\" + ");		//propComboList.setProperty("8", "Transforma em String Java 2");
				propEndLineTemplate.setProperty("9","");						//propComboList.setProperty("9", "GeradoPeloSQL --> ScriptSQLLimpo");
				propEndLineTemplate.setProperty("10","");						//propComboList.setProperty("10", "Letra -----> Numero")
				propEndLineTemplate.setProperty("11","");						//propComboList.setProperty("11", "Numero ----> Letra");
				propEndLineTemplate.store(new FileOutputStream(fPropEndLineTemplates),"EndLine");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(!fPropBeginLineTemplates.exists()){
			try {
				fPropBeginLineTemplates.createNewFile();
				propBeginLineTemplate.setProperty("0","");		//propComboList.setProperty("0", "Escolha um padrão aqui");
				propBeginLineTemplate.setProperty("1","//");	//propComboList.setProperty("1", "GeraITO-PreencheITO");
				propBeginLineTemplate.setProperty("2","123 ");	//propComboList.setProperty("2", "Vogal -----> Número");
				propBeginLineTemplate.setProperty("3","abc ");	//propComboList.setProperty("3", "Número -----> Vogal");
				propBeginLineTemplate.setProperty("4","eioEIO ");	//propComboList.setProperty("4", "Apaga Algumas Vogais");
				propBeginLineTemplate.setProperty("5","queb ");		//propComboList.setProperty("5", "Retira Quebra de Linhas");
				propBeginLineTemplate.setProperty("6","tab ");		//propComboList.setProperty("6", "Retira Tabulações");
				propBeginLineTemplate.setProperty("7","\"");		//propComboList.setProperty("7", "Transforma em String Java 1");
				propBeginLineTemplate.setProperty("8","\"");		//propComboList.setProperty("8", "Transforma em String Java 2");
				propBeginLineTemplate.setProperty("9","");			//propComboList.setProperty("9", "GeradoPeloSQL --> ScriptSQLLimpo");
				propBeginLineTemplate.setProperty("10","");						//propComboList.setProperty("10", "Letra -----> Numero")
				propBeginLineTemplate.setProperty("11","");						//propComboList.setProperty("11", "Numero ----> Letra");
				propBeginLineTemplate.store(new FileOutputStream(fPropBeginLineTemplates),"BeginLine");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
			}
		}				
	}
	

//------------------------------------------------------------------------------------------------------------------------------------------	
	
	
	public static void geraArquivoCombo(){
		Properties propComboList = new Properties();
		File fPropComboList = new File(FileNames.COMBO_LIST_NAME + FileNames.EXTENSAO_PROPERTIES);
		
		//caso nao exista, cria o arquivo que contem os valores da Combo de Templates
		if(!fPropComboList.exists()){
			try {				
				fPropComboList.createNewFile();
				propComboList.setProperty("NumeroDoProximoElemento","12");
				propComboList.setProperty("0", "Escolha um padrão aqui");	//propComboList.setProperty("0", "Escolha um padrão aqui");
				propComboList.setProperty("1", "GeraITO-PreencheITO");		//propComboList.setProperty("1", "GeraITO-PreencheITO");
				propComboList.setProperty("2", "Vogal -----> Número");		//propComboList.setProperty("2", "Vogal -----> Número");
				propComboList.setProperty("3", "Número -----> Vogal");		//propComboList.setProperty("3", "Número -----> Vogal");
				propComboList.setProperty("4", "Apaga Algumas Vogais");		//propComboList.setProperty("4", "Apaga Algumas Vogais");
				propComboList.setProperty("5", "Retira Quebra de Linhas");	//propComboList.setProperty("5", "Retira Quebra de Linhas");
				propComboList.setProperty("6", "Retira Tabulações");		//propComboList.setProperty("6", "Retira Tabulações");
				propComboList.setProperty("7", "Transforma em String Java 1");	//propComboList.setProperty("7", "Transforma em String Java 1");
				propComboList.setProperty("8", "Transforma em String Java 2");	//propComboList.setProperty("8", "Transforma em String Java 2");
				propComboList.setProperty("9", "GeradoPeloSQL --> ScriptSQLLimpo");	//propComboList.setProperty("9", "GeradoPeloSQL --> ScriptSQLLimpo");
				propComboList.setProperty("10", "Letra -----> Numero");	//propComboList.setProperty("10", "Letra -----> Numero")
				propComboList.setProperty("11", "Numero ----> Letra");	//propComboList.setProperty("11", "Numero ----> Letra");
				propComboList.store(new FileOutputStream(fPropComboList),"ComboList");				
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
			}
		}		
	}
	

//------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	public static void geraArquivosTemplatesInOut(){
		//caso nao exista, cria os arquivos templates para a TextArea1 e TextArea2 sereme preenchidas
		try {
			//----------------------------------------------------------------------------
			//propComboList.setProperty("0", "Escolha um padrão aqui");
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "0" +FileNames.EXTENSAO_AHT, "");
			//----------------------------------------------------------------------------
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "0" +FileNames.EXTENSAO_AHT, "");
			//##################################################################################
			//"1", "GeraITO-PreencheITO"
			String texto =
				"NULL, //STRING " + finalDeLinha +
				"0, //INT " + finalDeLinha +
				"NULL, //JAVA.UTIL.DATE " + finalDeLinha +
				"NULL, //JAVA.MATH.BIGDECIMAL " + finalDeLinha +
				"NULL //STRING " + finalDeLinha +
				"0 //INT " + finalDeLinha +
				"NULL //JAVA.UTIL.DATE " + finalDeLinha +
				"NULL //JAVA.MATH.BIGDECIMAL ";			
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "1" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------			
			texto = 
				"facadeEBDataBase.getString(rs,\"" +  finalDeLinha +
				"facadeEBDataBase.getInt(rs,\"" +  finalDeLinha +
				"facadeEBDataBase.getDate(rs,\"" + finalDeLinha +
				"facadeEBDataBase.getBigDecimal(rs,\"" + finalDeLinha +
				"facadeEBDataBase.getString(rs,\"" + finalDeLinha +
				"facadeEBDataBase.getInt(rs,\"" + finalDeLinha +
				"facadeEBDataBase.getDate(rs,\"" + finalDeLinha +
				"facadeEBDataBase.getBigDecimal(rs,\"";	
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "1" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
			//"2", "Vogal -----> Número"
			texto = 
					"a" + finalDeLinha +
					"e" + finalDeLinha +
					"i" + finalDeLinha +
					"o" + finalDeLinha +
					"u" + finalDeLinha +
					"A" + finalDeLinha +
					"E" + finalDeLinha +
					"I" + finalDeLinha +
					"O" + finalDeLinha +
					"U";	
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "2" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------	
			texto = 
					"1" + finalDeLinha +
					"2" + finalDeLinha +
					"3" + finalDeLinha +
					"4" + finalDeLinha +
					"5" + finalDeLinha +
					"6" + finalDeLinha +
					"7" + finalDeLinha +
					"8" + finalDeLinha +
					"9" + finalDeLinha +
					"0";	
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "2" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
			//"3", "Número -----> Vogal"
			texto = "1" + finalDeLinha +
					"2" + finalDeLinha +
					"3" + finalDeLinha +
					"4" + finalDeLinha +
					"5" + finalDeLinha +
					"6" + finalDeLinha +
					"7" + finalDeLinha +
					"8" + finalDeLinha +
					"9" + finalDeLinha +
					"0";
						
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "3" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------	
			texto = "a" + finalDeLinha +
					"e" + finalDeLinha +
					"i" + finalDeLinha +
					"o" + finalDeLinha +
					"u" + finalDeLinha +
					"A" + finalDeLinha +
					"E" + finalDeLinha +
					"I" + finalDeLinha +
					"O" + finalDeLinha +
					"U";
						
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "3" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
			//"4", "Apaga Algumas Vogais"
			texto = "a" + finalDeLinha +
					"e" + finalDeLinha +
					"i" + finalDeLinha +
					"o" + finalDeLinha +
					"u" + finalDeLinha +
					"A" + finalDeLinha +
					"E" + finalDeLinha +
					"I" + finalDeLinha +
					"O" + finalDeLinha +
					"U";
						
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "4" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------	
			texto = "4" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"u" + finalDeLinha +
					"4" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"w";						
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "4" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
			//"5", "Retira Quebra de Linhas"
			texto = "\\r\\n";						
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "5" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------	
			texto = "{ FM DE LINHA }";						
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "5" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
			//"6", "Retira Tabulações"
			texto = "\\t";						
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "6" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------	
			texto = "{ TABULACAO }";						
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "6" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
			//"7", "Transforma em String Java 1"
			texto = "\"";						
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "7" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------	
			texto = "\\\\\"";						
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "7" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
			//"8", "Transforma em String Java 2"
			texto = "\"";						
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "8" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------	
			texto = "'";						
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "8" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
			//"9", "GeradoPeloSQL --> ScriptSQLLimpo"
			texto = "\\[" + finalDeLinha +
					"\\]" + finalDeLinha +
					"SET ANSI_NULLS ON" + finalDeLinha +
					"SET QUOTED_IDENTIFIER ON" + finalDeLinha +
					"SET ANSI_PADDING ON" + finalDeLinha +
					"COLLATE SQL_Latin1_General_CP1_CI_AS" + finalDeLinha +
					"ON PRIMARY" + finalDeLinha +
					"SET ANSI_PADDING OFF" + finalDeLinha +
					"COLLATE Latin1_General_CI_AS" + finalDeLinha +
					"WITH" + finalDeLinha +
					"IGNORE_DUP_KEY = OFF" + finalDeLinha +
					"\\(\\)";
					
						
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "9" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------	
			texto = "" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"" + finalDeLinha +
					"";
											
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "9" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
//			"10", "Letra ----> Numero");
			texto = "A" + finalDeLinha +
					"a" + finalDeLinha +
					"E" + finalDeLinha +
					"e" + finalDeLinha +
					"I" + finalDeLinha +
					"i" + finalDeLinha +
					"O" + finalDeLinha +
					"o" + finalDeLinha +
					"U" + finalDeLinha +
					"U" + finalDeLinha +
					"B" + finalDeLinha +
					"b" + finalDeLinha +
					"S" + finalDeLinha +
					"s" + finalDeLinha +
					"T" + finalDeLinha +
					"t";
					
						
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "10" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------	
			texto = "4" + finalDeLinha +
					"4" + finalDeLinha +
					"3" + finalDeLinha +
					"3" + finalDeLinha +
					"1" + finalDeLinha +
					"1" + finalDeLinha +
					"0" + finalDeLinha +
					"0" + finalDeLinha +
					"U" + finalDeLinha +
					"U" + finalDeLinha +
					"8" + finalDeLinha +
					"8" + finalDeLinha +
					"5" + finalDeLinha +
					"5" + finalDeLinha +
					"7" + finalDeLinha +
					"7";											
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "10" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
//			"11", "Numero ----> Letra");
			texto = "4" + finalDeLinha +
					"4" + finalDeLinha +
					"3" + finalDeLinha +
					"3" + finalDeLinha +
					"1" + finalDeLinha +
					"1" + finalDeLinha +
					"0" + finalDeLinha +
					"0" + finalDeLinha +
					"U" + finalDeLinha +
					"U" + finalDeLinha +
					"8" + finalDeLinha +
					"8" + finalDeLinha +
					"5" + finalDeLinha +
					"5" + finalDeLinha +
					"7" + finalDeLinha +
					"7";
					
						
			escreveArquivo(FileNames.IN_TEMPLATE_NAME + "11" +FileNames.EXTENSAO_AHT, texto);
			//----------------------------------------------------------------------------	
			texto = "A" + finalDeLinha +
					"a" + finalDeLinha +
					"E" + finalDeLinha +
					"e" + finalDeLinha +
					"I" + finalDeLinha +
					"i" + finalDeLinha +
					"O" + finalDeLinha +
					"o" + finalDeLinha +
					"U" + finalDeLinha +
					"U" + finalDeLinha +
					"B" + finalDeLinha +
					"b" + finalDeLinha +
					"S" + finalDeLinha +
					"s" + finalDeLinha +
					"T" + finalDeLinha +
					"t";
											
			escreveArquivo(FileNames.OUT_TEMPLATE_NAME + "11" +FileNames.EXTENSAO_AHT, texto);
			//##################################################################################
			
		} catch (Throwable e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro !", JOptionPane.ERROR_MESSAGE);
		}
	}
	

}
