import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;

public class HomePage {

    @FXML
    private Button adminButton;

    @FXML
    private BarChart<?, ?> commonComponentsBarChart;

    @FXML
    private BarChart<?, ?> commonProductBarChart;

    @FXML
    private ComboBox<?> componentCountComboBox;

    @FXML
    private Tab componentTab;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button logOutButton;

    @FXML
    private ComboBox<?> orderFrequencyComboBox;

    @FXML
    private Tab orderTab;

    @FXML
    private BarChart<?, ?> ordersBarChart;

    @FXML
    private Button ordersButton;

    @FXML
    private Tab productTab;

    @FXML
    private ComboBox<?> productsCountComboBox;

}
