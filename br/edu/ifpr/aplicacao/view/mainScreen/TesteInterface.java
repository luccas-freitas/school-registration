package br.edu.ifpr.aplicacao.view.mainScreen;

import java.net.URL;

import br.edu.ifpr.aplicacao.view.JpaService;
import br.edu.ifpr.aplicacao.view.alunoScreen.AlunoController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TesteInterface extends Application {
	private static final String CADASTRO_FXML = "Cadastro.fxml";
	private static JpaService	js = new JpaService();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			URL url = getClass().getResource(CADASTRO_FXML);
			FXMLLoader loader = new FXMLLoader(url);
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			BorderPane borderPane = (BorderPane)root.getChildren().get(0);
			borderPane.centerProperty().set(new AlunoController());
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());

			CadastroController controller = loader.getController();
			controller.initState(js);

			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent ev) {
					if(!(CadastroController.showMessage("Sair da aplicação", "Deseja realmente sair ?"))){
						ev.consume();						
					}
				}
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("@@@ INICIALIZANDO O JPA @@@");
		js.openEntityManagerFactory();
		launch(args);
		System.out.println("@@@ ENCERRANDO JPA @@@");
		js.closeEntityManagerFactory();
	}
}
