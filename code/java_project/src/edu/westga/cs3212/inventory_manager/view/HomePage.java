package edu.westga.cs3212.inventory_manager.view;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

public class HomePage {

    @FXML
    private Button adminButton;

    @FXML
    private Tab componentTab;

    @FXML
    private TextArea componentSumarryTextArea;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Tab orderTab;

    @FXML
    private TextArea orderSumarryTextArea;

    @FXML
    private Button ordersButton;

    @FXML
    private Tab productTab;

    @FXML
    private TextArea productSumarryTextArea;

    @FXML
    public void initialize() {
        this.bindToViewModel();
        
    }

	private void bindToViewModel() {
		
	}
    
}
