package br.edu.ifpr.aplicacao.view.alunoScreen;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpr.aplicacao.processors.AlunoProcessor;
import br.edu.ifpr.aplicacao.view.JpaService;
import br.edu.ifpr.modelo.Aluno;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AlunoTable extends AnchorPane implements Initializable{
	private static final String VIEW_FXML = "ViewAluno.fxml";
	
	@FXML private TableView<Aluno> table = new TableView<Aluno>();
	@FXML TableColumn<Aluno, String> colCod = new TableColumn<>("COD.");
	@FXML TableColumn<Aluno, String> colAluno = new TableColumn<>("Aluno");
	@FXML TableColumn<Aluno, String> colRg = new TableColumn<>("RG");
	@FXML TableColumn<Aluno, String> colCpf = new TableColumn<>("CPF");
	@FXML TableColumn<Aluno, String> colEmail = new TableColumn<>("E-mail");
	@FXML TableColumn<Aluno, String> colTel = new TableColumn<>("Tel.");
	@FXML TableColumn<Aluno, String> colEnd = new TableColumn<>("Endereço");
	@FXML TableColumn<Aluno, String> colNum = new TableColumn<>("Nº.");
	
	private AlunoProcessor alunoProcessor;
	private JpaService js;
	
	public  AlunoTable(){
		loadFXML();
	}
	
	private void loadFXML() {
		URL url = getClass().getResource(VIEW_FXML);
		FXMLLoader loader = new FXMLLoader(url);
		loader.setRoot(this);
		loader.setController(this);
		
		try{
			loader.load();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initState(JpaService js){
		this.js = js;
		alunoProcessor = new AlunoProcessor(js.getEntityManagerFactory());
	}
	
	public void initView() {
		colCod.setCellValueFactory(new PropertyValueFactory<>("registro"));
		colAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
		colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colTel.setCellValueFactory(new PropertyValueFactory<>("numero"));
		colEnd.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
		colNum.setCellValueFactory(new PropertyValueFactory<>("numero"));
		
		List<Aluno> alunos  = alunoProcessor.select();
		table.setItems(FXCollections.observableArrayList(alunos));
		table.getColumns().addAll(colCod, colAluno, colRg, colCpf, colEmail, colTel, colEnd, colNum);
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
