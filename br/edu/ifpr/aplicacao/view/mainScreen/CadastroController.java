package br.edu.ifpr.aplicacao.view.mainScreen;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import br.edu.ifpr.aplicacao.view.JpaService;
import br.edu.ifpr.aplicacao.view.alunoScreen.AlunoController;
import br.edu.ifpr.aplicacao.view.alunoScreen.AlunoTable;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class CadastroController implements Initializable{
	private static final Logger LOGGER = Logger.getLogger(CadastroController.class.getSimpleName());
	
	private enum DataEntryState{
		INIT, INSERTING, SEARCHING, VIEWING, DELETING, EDITING;
	}
	
	@FXML private MenuItem miAluno;
	@FXML private MenuItem miInstrutor;
	@FXML private MenuItem miCurso;
	@FXML private MenuItem miMatricula;
	@FXML private MenuItem miModulo;
	
	@FXML private MenuItem miIncluir;
	@FXML private MenuItem miAlterar;
	@FXML private MenuItem miExcluir;
	@FXML private MenuItem miGravar;
	@FXML private MenuItem miCancelar;
	@FXML private MenuItem miProcurar;
	@FXML private MenuItem miSair;
	
	@FXML private Button btnIncluir;
	@FXML private Button btnAlterar;
	@FXML private Button btnExcluir;
	@FXML private Button btnGravar;
	@FXML private Button btnCancelar;
	@FXML private Button btnProcurar;
	
	@FXML BorderPane borderPane;
	
	private DataEntryState state;

	private AlunoController cadastro;
	private AlunoTable table;
	private JpaService js;
	
	@FXML
	void onMenuItemButtonAction(ActionEvent ev){
		//System.out.println(ev.getSource());
		Object ob = ev.getSource();
		
		if(ob instanceof MenuItem){
			onMenuItemAction((MenuItem) ob);
		}else{
			if(ob instanceof Button){
				onButtonAction((Button) ob);
			}
		}
	}
	
	void onMenuItemAction(MenuItem menuItem){
		if(menuItem == miIncluir){
			LOGGER.info("miIncluir");
			doInsert();
		}else if(menuItem == miAlterar){
			LOGGER.info("miAlterar");
			doEdit();
		}else if(menuItem == miExcluir){
			LOGGER.info("miExcluir");
			doDelete();
		}else if(menuItem == miGravar){
			LOGGER.info("miGravar");
			doSave();
		}else if(menuItem == miCancelar){
			LOGGER.info("miCancelar");
			doCancel();
		}else if(menuItem == miProcurar){
			LOGGER.info("miProcurar");
			doFind();
		}else if(menuItem == miSair){
			LOGGER.info("miSair");
			if(showMessage("Sair da aplicação", "Deseja realmente sair ?"))
				System.exit(0);
		}
	}
	
	void onButtonAction(Button button){
		if(button == btnIncluir){
			LOGGER.info("btnIncluir");
			doInsert();
		}else if(button == btnAlterar){
			LOGGER.info("btnAlterar");
			doEdit();
		}else if(button == btnExcluir){
			LOGGER.info("btnExcluir");
			doDelete();
		}else if(button == btnGravar){
			LOGGER.info("btnGravar");
			doSave();
		}else if(button == btnCancelar){
			LOGGER.info("btnCancelar");
			doCancel();
		}else if(button == btnProcurar){
			LOGGER.info("btnProcurar");
			doFind();
		}
	}
	
	public void initDataEntry(){
		ObjectProperty<Node> node = borderPane.centerProperty();
		
		if(node.get() != null){
			cadastro = (AlunoController)node.get();
			cadastro.initDataEntry(true);
			cadastro.initState(js);
		}
	}
	
	public void initDataView() {
		ObjectProperty<Node> node = borderPane.centerProperty();
		
		if(node.get() != null){
			table = (AlunoTable) node.get();
			table.initState(js);
		}
	}
	
	public void initState(JpaService js){
		this.js = js;
		this.cadastro = null;
		initBindings();
		initDataEntry();
		initDataView();
		changeState(DataEntryState.INIT);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	private void initBindings(){
		btnIncluir.disableProperty().bindBidirectional(miIncluir.disableProperty());
		btnAlterar.disableProperty().bindBidirectional(miAlterar.disableProperty());
		btnExcluir.disableProperty().bindBidirectional(miExcluir.disableProperty());
		btnGravar.disableProperty().bindBidirectional(miGravar.disableProperty());
		btnCancelar.disableProperty().bindBidirectional(miCancelar.disableProperty());
		btnProcurar.disableProperty().bindBidirectional(miProcurar.disableProperty());
	}
	
	public void doInsert(){
		changeState(DataEntryState.INSERTING);
		cadastro.initDataEntry(false);
	}
	
	public void doFind(){
		changeState(DataEntryState.SEARCHING);
		changeState(DataEntryState.VIEWING);
		borderPane.centerProperty().set(new AlunoTable());
		table.initView();
	}
	
	public void doSave(){
		if(cadastro.checkDataEntry()){
			changeState(DataEntryState.INIT);
			cadastro.initDataEntry(true);
			cadastro.insert();
			cadastro.clear();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
		}
	}
	
	public void doCancel(){
		cadastro.initDataEntry(true);
		if(state == DataEntryState.INSERTING || state == DataEntryState.EDITING){
			if((showMessage("Pedido de Cancelamento", "Deseja cancelar a edição de dados ?"))){
				cadastro.clear();
				changeState(DataEntryState.INIT);
			}
		}
	}
	
	public void doDelete(){
		changeState(DataEntryState.DELETING);
		if(!(showMessage("Exclusão", "Deseja realmente excluir ?")))
			changeState(DataEntryState.INIT);

	}
	
	public void doEdit(){
		changeState(DataEntryState.EDITING);
	}
	
	private void changeState(DataEntryState newState){
		state = newState;
		
		switch(state){
			case INIT:
				changeStateInit();
				break;
			case INSERTING:
				changeStateInsert();
				break;
			case SEARCHING:
				changeStateSearch();
				break;
			case VIEWING:
				changeStateView();
				break;
			case DELETING:
				changeStateDelete();
				break;
			case EDITING:
				changeStateEdit();
				break;
		}
	}
	
	private void changeStateInit(){
		miIncluir.disableProperty().set(false);
		miAlterar.disableProperty().set(true);
		miExcluir.disableProperty().set(true);
		miGravar.disableProperty().set(true);
		miCancelar.disableProperty().set(true);
		miProcurar.disableProperty().set(false);
		miSair.disableProperty().set(false);
	}
	private void changeStateInsert(){
		miIncluir.disableProperty().set(true);
		miAlterar.disableProperty().set(true);
		miExcluir.disableProperty().set(true);
		miGravar.disableProperty().set(false);
		miCancelar.disableProperty().set(false);
		miProcurar.disableProperty().set(true);
		miSair.disableProperty().set(false);
	}
	private void changeStateSearch(){
		miIncluir.disableProperty().set(true);
		miAlterar.disableProperty().set(false);
		miExcluir.disableProperty().set(false);
		miGravar.disableProperty().set(true);
		miCancelar.disableProperty().set(false);
		miProcurar.disableProperty().set(false);
		miSair.disableProperty().set(false);
	}
	private void changeStateView(){
		miIncluir.disableProperty().set(false);
		miAlterar.disableProperty().set(false);
		miExcluir.disableProperty().set(false);
		miGravar.disableProperty().set(true);
		miCancelar.disableProperty().set(true);
		miProcurar.disableProperty().set(false);
		miSair.disableProperty().set(false);
		System.out.println("Vizualizar");
	}
	private void changeStateDelete(){
		miIncluir.disableProperty().set(true);
		miAlterar.disableProperty().set(true);
		miExcluir.disableProperty().set(true);
		miGravar.disableProperty().set(true);
		miCancelar.disableProperty().set(true);
		miProcurar.disableProperty().set(true);
		miSair.disableProperty().set(true);
	}
	private void changeStateEdit(){
		miIncluir.disableProperty().set(true);
		miAlterar.disableProperty().set(true);
		miExcluir.disableProperty().set(true);
		miGravar.disableProperty().set(false);
		miCancelar.disableProperty().set(false);
		miProcurar.disableProperty().set(true);
		miSair.disableProperty().set(false);
	}
	
	static boolean showMessage(String title, String message){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.headerTextProperty().set(null);
		alert.titleProperty().set(title);
		alert.contentTextProperty().set(message);
		
		alert.getButtonTypes().clear(); //limpa os botoes defaults
		alert.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.YES);
		
		//ativa o botao no como default
		Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
		noButton.setDefaultButton(true);

		//desabililta o botao yes como default
		Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.YES);
		yesButton.setDefaultButton(false);
		
		
	    Optional<ButtonType> result = alert.showAndWait();
	    if(result.get() == ButtonType.CANCEL){
	    	return false;
	    }else{
	    	return true;
	    }
	}
}
