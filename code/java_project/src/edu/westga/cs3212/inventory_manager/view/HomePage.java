package edu.westga.cs3212.inventory_manager.view;
import edu.westga.cs3212.inventory_manager.viewmodel.HomePageViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

public class HomePage {

	@FXML
    private Button adminButton;

    @FXML
    private TextArea componentSummaryTextArea;

    @FXML
    private Tab componentTab;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button logOutButton;

    @FXML
    private TextArea orderSummaryTextArea;

    @FXML
    private Tab orderTab;

    @FXML
    private Button ordersButton;

    @FXML
    private TextArea productSummaryTextArea;

    @FXML
    private Tab productTab;
    
    private HomePageViewModel viewModel;

    @FXML
    public void initialize() {
        this.bindToViewModel();
        this.viewModel.getComponentSummary();
        this.viewModel.getProductSummary();
        this.viewModel.getOrderSummary();
        
    }

	private void bindToViewModel() {
		this.viewModel = new HomePageViewModel();
		this.componentSummaryTextArea.textProperty().bind(this.viewModel.getComponentSumarryTextArea());
		this.productSummaryTextArea.textProperty().bind(this.viewModel.getProductSumarryTextArea());
		this.orderSummaryTextArea.textProperty().bind(this.viewModel.getOrderSumarryTextArea());
	}
    
}
