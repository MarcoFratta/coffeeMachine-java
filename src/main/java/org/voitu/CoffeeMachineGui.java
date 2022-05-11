package org.voitu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class CoffeeMachineGui implements View{

    private static final String STATUS_PREFIX = "Status: ";
    private static final String TEST_PREFIX = "Self tests: ";
    @FXML
    Label status;

    @FXML
    Label testNumber;

    @FXML
    Button refill;

    @FXML
    Button recover;

    @FXML
    ScrollPane loggerScroll;

    @FXML
    ScrollPane productScroll;


    private final CoffeeMachineController controller;

    public CoffeeMachineGui(final CoffeeMachineController controller){
        this.controller = controller;
    }
    public void init(){
        final VBox loggerBox = new VBox();
        this.loggerScroll.setContent(loggerBox);
        final LoggerUI loggerUI = new LoggerUI(LoggerImpl.getLogger(), loggerBox);

        final VBox productBox =  new VBox();
        final ProductsUI productsUI = new ProductsUI(productBox);
        this.productScroll.setContent(productBox);

        this.controller.registerListener(c -> Platform.runLater(()-> {
            this.controller.getCoffeeMachine().getProductNumber().forEach((k,v)->{
                productsUI.update(k.toString(), v);
            });
            this.recover.setDisable(this.controller.getCoffeeMachine().getStatus() != MachineStatus.RECOVER);
            this.refill.setDisable(this.controller.getCoffeeMachine().getStatus() != MachineStatus.REFILL);
            this.status.setText(STATUS_PREFIX + this.controller.getCoffeeMachine().getStatus());
            this.testNumber.setText(TEST_PREFIX + this.controller.getCoffeeMachine().getPerformedSelfTestNumber());
        }));

        this.refill.setOnAction(ev -> Platform.runLater(this.controller::refill));
        this.recover.setOnAction(ev -> Platform.runLater(this.controller::recover));
    }
}
