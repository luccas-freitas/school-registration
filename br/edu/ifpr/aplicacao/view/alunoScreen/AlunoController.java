package br.edu.ifpr.aplicacao.view.alunoScreen;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

import br.edu.ifpr.aplicacao.processors.AlunoProcessor;
import br.edu.ifpr.aplicacao.view.JpaService;
import br.edu.ifpr.modelo.Aluno;
import br.edu.ifpr.modelo.Endereco;
import br.edu.ifpr.modelo.Telefone;
import br.edu.ifpr.modelo.types.EnderecoType;
import br.edu.ifpr.modelo.types.SexoType;
import br.edu.ifpr.modelo.types.TelefoneType;
import br.edu.ifpr.modelo.types.UF;
import br.edu.ifpr.utils.date.DateTimeUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class AlunoController extends AnchorPane implements Initializable{
	private static final String CADASTRO_FXML = "InsertAluno.fxml";
	
	@FXML private GridPane gridPane;
	@FXML private Label lRg;
	@FXML private TextField txRg;
	@FXML private Label lNome;
	@FXML private TextField txNome;
	@FXML private Label lCpf;
	@FXML private TextField txCpf;
	@FXML private Label lNascimento;
	@FXML private DatePicker dtNascimento;
	@FXML private Label lSexo;
	@FXML private RadioButton rbM;
	@FXML private RadioButton rbF;
	@FXML private ToggleGroup tgSexo;
	@FXML private Label lEmail;
	@FXML private TextField txEmail;
	
	@FXML private Label lEndereco;
	@FXML private TextField txEndereco;
	@FXML private Label lNumero;
	@FXML private TextField txNumero;
	@FXML private Label lComplemento;
	@FXML private TextField txComplemento;
	@FXML private Label lBairro;
	@FXML private TextField txBairro;
	@FXML private Label lCep;
	@FXML private TextField txCep;
	@FXML private Label lTipoEnd;
	@FXML private ComboBox<EnderecoType> cbTipo;
	@FXML private Label lCidade;
	@FXML private TextField txCidade;
	@FXML private Label lUf;
	@FXML private ComboBox<UF> cbUf;
	
	@FXML private Label lDdd;
	@FXML private TextField txDdd;
	@FXML private Label lTel;
	@FXML private TextField txTel;
	@FXML private Label lTipoTel;
	@FXML private ComboBox<TelefoneType> cbTel;
	
	@FXML private Label lRegistro;
	@FXML private TextField txRegistro;
	
	private AlunoProcessor alunoProcessor;
	private JpaService js;
	
	public AlunoController(){
		loadFXML();
	}
	
	private void loadFXML(){
		URL url = getClass().getResource(CADASTRO_FXML);
		FXMLLoader loader = new FXMLLoader(url);
		loader.setRoot(this);
		loader.setController(this);
		
		try{
			loader.load();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void initState(JpaService js){
		this.js = js;
		initLabels();
		initNascimento();
		initSexo();
		initUpdate();
		initComboBox();
		alunoProcessor = new AlunoProcessor(js.getEntityManagerFactory());
	}
	
	private void initLabels(){
		lNome.setLabelFor(txNome);
		lRg.setLabelFor(txRg);
		lCpf.setLabelFor(txCpf);
		lNascimento.setLabelFor(dtNascimento);
		lEmail.setLabelFor(txEmail);
		lDdd.setLabelFor(txDdd);
		lTel.setLabelFor(txTel);
		
		lEndereco.setLabelFor(txEndereco);
		lNumero.setLabelFor(txNumero);
		lComplemento.setLabelFor(txComplemento);
		lBairro.setLabelFor(txBairro);
		lCep.setLabelFor(txCep);
		lCidade.setLabelFor(txCidade);
	}
	
	private void initNascimento(){
		dtNascimento.setDayCellFactory(new NascimentoDatePickerDayCellFactory());
	}
	
	private void initComboBox(){
		cbTipo.setItems(FXCollections.observableArrayList(EnderecoType.values()));
		cbTipo.setValue(EnderecoType.RESIDENCIAL);
		cbUf.setItems(FXCollections.observableArrayList(UF.values()));
		cbUf.setValue(UF.ACRE);
		cbTel.setItems(FXCollections.observableArrayList(TelefoneType.values()));
		cbTel.setValue(TelefoneType.CELULAR);
	}
		
	private void initSexo(){
		rbM.onActionProperty().set(new SexoRadioButtonAction());
		rbF.onActionProperty().set(new SexoRadioButtonAction());
	}
	
	public void initDataEntry(boolean active){
		if(gridPane != null){
			gridPane.disableProperty().set(active);
			gridPane.visibleProperty().set(!active);
			txNome.requestFocus();
		}
	}
	
	public boolean checkDataEntry(){
		if(txNome.getText().isEmpty() || !(rbF.isSelected() || rbM.isSelected())){
			if(txNome.getText().isEmpty()){
				System.out.println("POE O NOME AI CARAI");
				txNome.requestFocus();
			}
			else if(!rbF.isSelected() || !rbM.isSelected()){
				System.out.println("POE O SEXO AI PORRA");
				rbF.requestFocus();
			}
			return false;
		}
		System.out.println("AE CARAI");
		return true;
	}
	
	public void initInsert(){
		setupGrid(false);
		txNome.requestFocus();
	}
	
	public void initUpdate(){
		setupGrid(false);
		dtNascimento.requestFocus();
	}
	
	public void insert() {
		Aluno aluno = new Aluno();
		aluno.setRg(txRg.getText());
		aluno.setNome(txNome.getText());
		aluno.setCpf(txCpf.getText());
		aluno.setNascimento(DateTimeUtils.getDate());
		aluno.setSexo(SexoType.MASCULINO);
		aluno.setEmail(txEmail.getText());
		aluno.setEndereco(new Endereco(
				txEndereco.getText(), Integer.parseInt(txNumero.getText()), txComplemento.getText(),
				txBairro.getText(), txCep.getText(), EnderecoType.COMERCIAL, txCidade.getText(), UF.ACRE));
		aluno.setFones(Arrays.asList(new Telefone(Integer.parseInt(txDdd.getText()), txTel.getText(), TelefoneType.CELULAR)));
		aluno.setRegistro(txRegistro.getText());
		
		alunoProcessor.insert(aluno);
	}
	
	public void clear(){
		txNome.clear();
		txRg.clear();
		txCpf.clear();
		txRegistro.clear();
		dtNascimento.valueProperty().set(LocalDate.now());
		rbM.selectedProperty().set(false);
		rbF.selectedProperty().set(false);
		txEmail.clear();
		txDdd.clear();
		txTel.clear();
		cbTipo.valueProperty().set(EnderecoType.RESIDENCIAL);
		
		txEndereco.clear();
		txNumero.clear();
		txComplemento.clear();
		txBairro.clear();
		txCep.clear();
		txCidade.clear();
		cbTel.valueProperty().set(TelefoneType.CELULAR);
		cbUf.valueProperty().set(UF.ACRE);
	}
	
	public void setupGrid(boolean disable){
		gridPane.disableProperty().set(disable);
	}	
	
	//----------------------------CLASSES DE SUPORTE
	private class NascimentoDatePickerDayCellFactory implements Callback<DatePicker, DateCell>{
		@Override
		public DateCell call(DatePicker param) {
			return new NascimentoDatePickerDateCell();
		}
	}
	
	private class NascimentoDatePickerDateCell extends DateCell{
		@Override
		public void updateItem(LocalDate item, boolean empty){
			super.updateItem(item, empty);
			//destacar sabado e domingo
			DayOfWeek day = DayOfWeek.from(item);
			if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
				this.setStyle("-fx-background-color: LightSalmon");
			//desabilitar os proximos dias
			if(item.isAfter(LocalDate.now()))
				this.setDisable(true);
		}
	}

	private class SexoRadioButtonAction implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent ev){
			RadioButton rb = (RadioButton) ev.getSource();
			rb.selectedProperty().set(true);
		}
	}

	
}
