package edu.westga.cs3212.inventory_manager.view;

import edu.westga.cs3212.inventory_manager.viewmodel.AddComponentViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddComponent {
	@FXML
    private TextField cost;

    @FXML
    private TextField name;

    @FXML
    private TextField quantity;
    
    private AddComponentViewModel addComponentVM;
   
    @FXML
    void add(ActionEvent event) {
    	boolean result;
    	
    	result = this.addComponentVM.add();
 
    	if (result) {
    		Alert successPopup = new Alert(AlertType.CONFIRMATION);
    		successPopup.setContentText("Component Added Successfully");
    		successPopup.showAndWait();
        	this.closeWindow(event);
    	} else {
    		Alert errorPopup = new Alert(AlertType.ERROR);
    		errorPopup.setContentText("Unable to add component, please check component information and try again");
    		errorPopup.showAndWait();
    	}
    }

    @FXML
    void cancel(ActionEvent event) {
    	this.closeWindow(event);
    }

	private void closeWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	stage.close();
	}
    
    @FXML
    void initialize() {
        assert this.cost != null : "fx:id=\"cost\" was not injected: check your FXML file 'AddComponentPage.fxml'.";
        assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'AddComponentPage.fxml'.";
        assert this.quantity != null : "fx:id=\"quantity\" was not injected: check your FXML file 'AddComponentPage.fxml'.";
        
        this.addComponentVM = new AddComponentViewModel();

        this.cost.textProperty().bindBidirectional(this.addComponentVM.getCost());
        this.name.textProperty().bindBidirectional(this.addComponentVM.getName());
        this.quantity.textProperty().bindBidirectional(this.addComponentVM.getQuantity());
    }
}
