import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;

public class HomePage {

    @FXML
    private Button adminButton;

    @FXML
    private BarChart<String, Number> commonComponentsBarChart;

    @FXML
    private BarChart<String, Number> commonProductBarChart;

    @FXML
    private ComboBox<String> componentCountComboBox;

    @FXML
    private Tab componentTab;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button logOutButton;

    @FXML
    private ComboBox<String> orderFrequencyComboBox;

    @FXML
    private Tab orderTab;

    @FXML
    private BarChart<String, Number> ordersBarChart;

    @FXML
    private Button ordersButton;

    @FXML
    private Tab productTab;

    @FXML
    private ComboBox<String> productsCountComboBox;

    @FXML
    public void initialize() {
        this.bindToViewModel();
        
    }

	private void bindToViewModel() {
		
	}
    
}
