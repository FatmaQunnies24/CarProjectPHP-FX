//package ass; 
//
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.TextFieldTableCell;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//import javafx.util.Pair;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
////import com.mysql.cj.conf.ConnectionUrlParser.Pair;
//
//public class Assignment extends Application {
//
//    private ObservableList<ObservableList<String>> data;
//    private TableView tableView;
//    private ComboBox<String> tableComboBox;
//    private List<Node> inputFields;  // Modified to hold both TextField and ComboBox
//    private VBox searchBox;
//    private Button addButton;
//    private Button deleteButton;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//    private void addInputFields(List<String> columnNames) {
//        inputFields = new ArrayList<>();
//        for (String columnName : columnNames) {
//            TextField textField = new TextField();
//            textField.setPromptText(columnName);
//            inputFields.add(textField);
//        }
//    }
//
//
//    private VBox createSearchBox() {
//        tableComboBox = new ComboBox<>();
//        tableComboBox.setPromptText("Select Table");
//        tableComboBox.setOnAction(e -> {
//            String selectedTable = tableComboBox.getSelectionModel().getSelectedItem();
//            if (selectedTable != null) {
//                buildData(selectedTable, "SELECT * FROM " + selectedTable, false);
//                showAddDeleteButtons(selectedTable);
//            }
//        });
//
//        searchBox = new VBox(10, tableComboBox);
//
//        return searchBox;
//    }
//    private void showDeleteDialog(String tableName) {
//        Dialog<Pair<String, String>> dialog = new Dialog<>();
//        dialog.setTitle("Delete Record");
//        dialog.setHeaderText("Please enter the column name and value for deletion:");
//
//        TextField columnNameField = new TextField();
//        columnNameField.setPromptText("Column Name");
//
//        TextField valueField = new TextField();
//        valueField.setPromptText("Value");
//
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//
//        grid.add(new Label("Column Name:"), 0, 0);
//        grid.add(columnNameField, 1, 0);
//        grid.add(new Label("Value:"), 0, 1);
//        grid.add(valueField, 1, 1);
//
//        dialog.getDialogPane().setContent(grid);
//
//        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);
//
//        Node deleteButton = dialog.getDialogPane().lookupButton(deleteButtonType);
//        deleteButton.setDisable(true);
//
//        columnNameField.textProperty().addListener((observable, oldValue, newValue) ->
//                deleteButton.setDisable(newValue.trim().isEmpty() || valueField.getText().trim().isEmpty())
//        );
//
//        valueField.textProperty().addListener((observable, oldValue, newValue) ->
//                deleteButton.setDisable(columnNameField.getText().trim().isEmpty() || newValue.trim().isEmpty())
//        );
//
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == deleteButtonType) {
//                String columnName = columnNameField.getText();
//                String value = valueField.getText();
//                handleDeletion(tableName, columnName, value, false);
//            }
//            return null;
//        });
//
//        dialog.showAndWait();
//    }
//
//
//    private void showAddDeleteButtons(String tableName) {
//        // Clear existing buttons before adding new ones
//        searchBox.getChildren().removeAll(addButton, deleteButton);
//
//        addButton = new Button("Add");
//        addButton.setOnAction(e -> showAddDialog(tableName));
//
//        deleteButton = new Button("Delete");
//        deleteButton.setOnAction(e -> showDeleteDialog(tableName));
//
//        searchBox.getChildren().addAll(addButton, deleteButton);
//    }
//
//    private void hideAddDeleteButtons() {
//        Platform.runLater(() -> {
//            searchBox.getChildren().removeAll(addButton, deleteButton);
//        });
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        tableView = new TableView();
//        searchBox = createSearchBox();
//
//        searchBox.setPadding(new Insets(10));
//
//        Scene scene = new Scene(new VBox(10, searchBox, tableView));
//
//        stage.setScene(scene);
//        stage.setOnCloseRequest(event -> {
//            Platform.exit();
//            System.exit(0);
//        });
//        stage.show();
//
//        // Load table names into the ComboBox
//        loadTableNames();
//    }
//    
//    private List<String> getColumnNames(String tableName) {
//        List<String> columnNames = new ArrayList<>();
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsproject", "root", "");
//            ResultSet resultSet = connection.getMetaData().getColumns(null, null, tableName, null);
//
//            while (resultSet.next()) {
//                String columnName = resultSet.getString("COLUMN_NAME");
//                columnNames.add(columnName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return columnNames;
//    }
//
//    private void loadTableNames() {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsproject", "root", "");
//            ResultSet tables = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
//
//            while (tables.next()) {
//                String tableName = tables.getString("TABLE_NAME");
//                tableComboBox.getItems().add(tableName);
//
//                loadForeignKeys(tableName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void loadForeignKeys(String tableName) {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsproject", "root", "");
//            ResultSet foreignKeys = connection.getMetaData().getImportedKeys(null, null, tableName);
//
//            while (foreignKeys.next()) {
//                String foreignKeyColumnName = foreignKeys.getString("FKCOLUMN_NAME");
//                tableComboBox.getItems().add(foreignKeyColumnName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void showAddDialog(String tableName) {
//        Dialog<List<String>> dialog = new Dialog<>();
//        dialog.setTitle("Add Record");
//        dialog.setHeaderText("Please enter values for each column:");
//
//        inputFields = new ArrayList<>();
//
//        for (String columnName : getColumnNames(tableName)) {
//            if (isForeignKey(tableName, columnName)) {
//                // If the column is a foreign key, show a combo box with values
//                ComboBox<String> foreignKeyComboBox = new ComboBox<>();
//                List<String> foreignKeyValues = getForeignKeyValues(tableName, columnName);
//                foreignKeyComboBox.getItems().addAll(foreignKeyValues);
//                inputFields.add(foreignKeyComboBox);
//            } else {
//                // If not a foreign key, show a regular text field
//                TextField textField = new TextField();
//                textField.setPromptText(columnName);
//                inputFields.add(textField);
//            }
//        }
//
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//
//        for (int i = 0; i < inputFields.size(); i++) {
//            grid.add(new Label(getColumnNames(tableName).get(i) + ":"), 0, i);
//            grid.add(inputFields.get(i), 1, i);
//        }
//
//        dialog.getDialogPane().setContent(grid);
//
//        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
//
//        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
//        addButton.setDisable(true);
//
//        for (Node field : inputFields) {
//            if (field instanceof TextField) {
//                // For text field, disable the add button if it's empty
//                TextField textField = (TextField) field;
//                textField.textProperty().addListener((observable, oldValue, newValue) ->
//                        addButton.setDisable(inputFields.stream().anyMatch(f ->
//                                (f instanceof TextField && ((TextField) f).getText().isEmpty()) ||
//                                        (f instanceof ComboBox && ((ComboBox<?>) f).getSelectionModel().isEmpty())
//                        ))
//                );
//            } else if (field instanceof ComboBox) {
//                // For combo box, disable the add button if no value is selected
//                ComboBox<String> comboBox = (ComboBox<String>) field;
//                comboBox.valueProperty().addListener((observable, oldValue, newValue) ->
//                        addButton.setDisable(newValue == null || newValue.trim().isEmpty())
//                );
//            }
//        }
//
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == addButtonType) {
//                List<String> values = new ArrayList<>();
//                for (Node field : inputFields) {
//                    if (field instanceof TextField) {
//                        values.add(((TextField) field).getText());
//                    } else if (field instanceof ComboBox) {
//                        values.add((String) ((ComboBox<?>) field).getSelectionModel().getSelectedItem());
//                    }
//                }
//                handleAddition(tableName, getColumnNames(tableName), values, false);
//            }
//            return null;
//        });
//
//        dialog.showAndWait();
//    }
//
//    private boolean isForeignKey(String tableName, String columnName) {
//        
//        return tableComboBox.getItems().contains(columnName);
//    }
//
//    private List<String> getForeignKeyValues(String tableName, String columnName) {
//        
//        List<String> values = new ArrayList<>();
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsproject", "root", "");
//            Statement statement = connection.createStatement();
//            String query = "SELECT DISTINCT " + columnName + " FROM " + tableName;
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                values.add(resultSet.getString(1));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return values;
//    }
//
//    private void handleDeletion(String tableName, String columnName, String value,boolean hideButtons) {
//        try {
//            if (tableName == null || tableName.trim().isEmpty()) {
//                System.out.println("Error: Table name is not specified.");
//                return;
//            }
//
//            if (columnName == null || columnName.trim().isEmpty()) {
//                System.out.println("Error: Column name for deletion is not specified.");
//                return;
//            }
//
//            if (value == null || value.trim().isEmpty()) {
//                System.out.println("Error: Value for deletion is not specified.");
//                return;
//            }
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsproject", "root", "");
//            Statement statement = connection.createStatement();
//
//            String query = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";
//
//            // Use PreparedStatement to safely handle values
//            try (java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, value);
//                preparedStatement.executeUpdate();
//            }
//
//            buildData(tableName, "SELECT * FROM " + tableName, hideButtons);
//
//            if (hideButtons) {
//                hideAddDeleteButtons();
//            } else {
//                showAddDeleteButtons(tableName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void handleAddition(String tableName, List<String> columnNames, List<String> values, boolean hideButtons) {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsproject", "root", "");
//            Statement statement = connection.createStatement();
//
//            StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
//            queryBuilder.append(tableName).append(" (");
//            queryBuilder.append(String.join(", ", columnNames)).append(") VALUES (");
//
//            // Use PreparedStatement to safely handle values
//            for (int i = 0; i < values.size(); i++) {
//                queryBuilder.append("?");
//                if (i < values.size() - 1) {
//                    queryBuilder.append(", ");
//                }
//            }
//            queryBuilder.append(")");
//
//            // Use PreparedStatement to safely handle values
//            try (java.sql.PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
//                for (int i = 0; i < values.size(); i++) {
//                    preparedStatement.setString(i + 1, values.get(i));
//                }
//                preparedStatement.executeUpdate();
//            }
//
//            // Optionally, refresh the displayed data after addition
//            buildData(tableName, "SELECT * FROM " + tableName, false);
//            if (hideButtons) {
//                hideAddDeleteButtons();
//            } else {
//                showAddDeleteButtons(tableName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Implement the rest of the methods...
//
//    public void buildData(String tableName, String query, boolean hideButtons) {
//        try {
//            data = FXCollections.observableArrayList();
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsproject", "root", "");
//
//            tableView.getItems().clear();
//            tableView.getColumns().clear();
//
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//
//            List<String> columnNames = new ArrayList<>();
//            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                columnNames.add(rs.getMetaData().getColumnName(i));
//            }
//
//            addInputFields(columnNames);
//
//            for (int i = 0; i < columnNames.size(); i++) {
//                final int j = i;
//                TableColumn col = new TableColumn(columnNames.get(i));
//                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
//                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
//                        return new SimpleStringProperty(param.getValue().get(j));
//                    }
//                });
//                tableView.getColumns().add(col);
//            }
// 
//            while (rs.next()) {
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                    row.add(rs.getString(i));
//                }
//                data.add(row);
//            }
//
//            tableView.setItems(data);
//
//            if (hideButtons) {
//                hideAddDeleteButtons(); // Hide buttons after search
//            } else {
//                showAddDeleteButtons(tableName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//    }
//}