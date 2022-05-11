package org.voitu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(final Stage stage) throws Exception {
        final String portName = "COM5";
        final int rate = 9600;
        final CoffeeMachine machine = new CoffeeMachineImpl();
        final CoffeeMachineController controller = new CoffeeManagerControllerImpl(machine, portName, rate);
        machine.setController(controller);
        final FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("gui.fxml"));
        final CoffeeMachineGui gui = new CoffeeMachineGui(controller);
        fxmlLoader.setController(gui);
        final Parent root = fxmlLoader.load();
        gui.init();
        controller.getCoffeeMachine().setStatus(MachineStatus.WORKING);


        stage.setTitle("Coffee machine");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(final String[] args) {
        launch();
    }

}