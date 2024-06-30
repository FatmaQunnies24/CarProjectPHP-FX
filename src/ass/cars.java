package ass;

import java.sql.Connection;
import java.awt.Font;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer.Form;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class cars extends Application {
  
	  private ObservableList<ObservableList> data;
	    private ComboBox<String> tableComboBox;
  String s="";

	    private TableView tableview;
	    Connection con ;
	
    public String buildData(String Query) {
        data = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Query);

            /**
             * ************
             * TABLE COLUMN ADDED DYNAMICALLY *
             ***********
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
            /**
             * **********
             * Data added to ObservableList *
             ***********
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
              
                s+= row;
                data.add(row);

            }
            
            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
		return s;
    }

	
	
	public static void main(String[] args) {
        launch(args);
    }
  
    VBox form = new VBox(5);

   
    public void start(Stage stage) throws Exception { 
    	

        Button address = new Button("Address");
        Button car_part = new Button("Car Part");
        Button car = new Button("Car");
        Button device = new Button("Device");
        Button manufacture = new Button("Manufacture");
        Button orders = new Button("Orders");
        Button exit = new Button("Customer");
        Button Customer = new Button("Close");

        address.setMinSize(100, 30);
        car_part.setMinSize(100, 30);
        car.setMinSize(100, 30);
        Customer.setMinSize(100, 30);
        device.setMinSize(100, 30);
        manufacture.setMinSize(100, 30);
        orders.setMinSize(100, 30);
        exit.setMinSize(100, 30);

        VBox.setMargin(address, new javafx.geometry.Insets(5, 5, 5, 5));
        VBox.setMargin(car_part, new javafx.geometry.Insets(5, 5, 5, 5));
        VBox.setMargin(car, new javafx.geometry.Insets(5, 5, 5, 5));
        VBox.setMargin(Customer, new javafx.geometry.Insets(5, 5, 5, 5));
        VBox.setMargin(device, new javafx.geometry.Insets(5, 5, 5, 5));
        VBox.setMargin(manufacture, new javafx.geometry.Insets(5, 5, 5, 5));
        VBox.setMargin(orders, new javafx.geometry.Insets(5, 5, 5, 5));
        VBox.setMargin(exit, new javafx.geometry.Insets(5, 5, 5, 5));

        VBox box1 = new VBox(address, car_part, car, Customer);
        VBox box2 = new VBox(device, manufacture, orders, exit);

        box1.setTranslateZ(10);
        box2.setTranslateZ(10);


        address.setTranslateX(165); 
        address.setTranslateY(15);
        
        car_part.setTranslateX(165); 
        car_part.setTranslateY(35);
        
        car.setTranslateX(165); 
        car.setTranslateY(55);
        
        Customer.setTranslateX(165); 
        Customer.setTranslateY(72);
        
        device.setTranslateX(165); 
        device.setTranslateY(96);
        
        manufacture.setTranslateX(165); 
        manufacture.setTranslateY(113);
        
        orders.setTranslateX(165); 
        orders.setTranslateY(134);
        
        exit.setTranslateX(165); 
        exit.setTranslateY(150);
        address.setStyle("-fx-background-color: #000184;-fx-text-fill:white;");
        car_part.setStyle("-fx-background-color: #000184;-fx-text-fill:white;");
        car.setStyle("-fx-background-color: #000184;-fx-text-fill:white;");
        Customer.setStyle("-fx-background-color: #000184;-fx-text-fill:white;");
        device.setStyle("-fx-background-color: #000184;-fx-text-fill:white;");
        manufacture.setStyle("-fx-background-color: #000184;-fx-text-fill:white;");
        exit.setStyle("-fx-background-color: #000184;-fx-text-fill:white;");
        orders.setStyle("-fx-background-color: #000184;-fx-text-fill:white;");
        car.setPrefWidth(130);
        car.setPrefHeight(37);
        
        address.setPrefWidth(130);
        address.setPrefHeight(35);
        
        orders.setPrefWidth(130);
        orders.setPrefHeight(37);
        
        manufacture.setPrefWidth(130);
        manufacture.setPrefHeight(35);
        
        Customer.setPrefWidth(130);
        Customer.setPrefHeight(35);
        
        device.setPrefWidth(130);
        device.setPrefHeight(35);
        
        car_part.setPrefWidth(130);
        car_part.setPrefHeight(37);
        
        exit.setPrefWidth(130);
        exit.setPrefHeight(35);
        
        VBox Vbox = new VBox(box1, box2);
        Vbox.setPadding(new Insets(20, 10, 0, 120));

        StackPane root = new StackPane();

        Image backgroundImage = new Image("file:ENGLISH.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(500);
        root.getChildren().add(backgroundImageView);

        root.getChildren().add(Vbox);

        Scene sc = new Scene(root);

        stage.setScene(sc);
        stage.setTitle("Fatma Qunnies");
        stage.setWidth(800);
        stage.setHeight(600);
Label op = new Label("Choose a process before entering data");
op.setStyle("-fx-font-family: Arial; -fx-font-size: 16; -fx-font-weight: bold;");
        
        
           

        stage.show();

        exit.setOnAction(e -> {

        	
        	tableview = new TableView();
   	  	 tableview.setStyle("-fx-control-inner-background: #E89AA3;");
   	  buildData("select * from Customer");
      
      Label id_Label = new Label("ID:");
    TextField textField_id = new TextField();

    Label FName_Label = new Label("First Name:");
    TextField textField_fname = new TextField();

    Label LName_Label = new Label("Last Name:");
    TextField textField_lname = new TextField();

    Label Address_Label = new Label("Address:");
    TextField textField_address = new TextField();

    Label Job_Label = new Label("Job:");
    TextField textField_job = new TextField();
            
   
       TextField test = new TextField();

       
       Button Do = new Button("Do");
       Button back = new Button("Finish");
       Button search = new Button("search");
       Button delete = new Button("delete");
       Button update = new Button("update");
       Button insert = new Button("insert");
       back.setStyle("-fx-background-color: red;");
       Do.setStyle("-fx-background-color: green;");

       HBox h2 = new HBox(5);
       h2.getChildren().addAll(back,Do);
       HBox h = new HBox(5);
       h.setSpacing(90);
	       h.getChildren().addAll( delete,update,insert,search);
     
       
     
       form.setStyle("-fx-padding: 20;"); // add padding to the VBox
     
       ComboBox<String> addresscom=new ComboBox<>();     
//       ComboBox<String> made=new ComboBox<>();     

       ComboBox<String> col=new ComboBox<>();     
       col.getItems().add("id");
   	col.getItems().add("f_name");
   	col.getItems().add("l_name");
   	col.getItems().add("address");
   	col.getItems().add("job");
   		

                    VBox v=new VBox();
       v.getChildren().addAll(id_Label, textField_id, FName_Label, textField_fname, LName_Label, textField_lname,
	             Address_Label, textField_address,addresscom, Job_Label, textField_job);
       if (!form.getChildren().contains(v)&&!form.getChildren().contains(op)&&!form.getChildren().contains(v)) {
       form.getChildren().addAll( h,op,test,v,col,h2);}
       col.setVisible(false);
//com1.setVisible(false);
       addresscom.setVisible(false);
       form.getChildren().addAll(tableview);
  	Scene address_scene = new Scene(form);
    primaryComboBox(addresscom,"address","id");
if( stage.getScene()!=address_scene)
  	    stage.setScene(address_scene);
  	    stage.setWidth(600); 
  	    stage.setHeight(600); 
  	    stage.show();
    
        	back.setOnAction(o->{
        		stage.setScene(sc);
        		  stage.setWidth(800);
        	        stage.setHeight(600); // Set the initial height of the stage
        	    stage.show();
        	});
        	test.setVisible(false);

        	search.setOnAction(o -> {
        	   

//        	    
try {  
	if(!form.getChildren().contains(v)) {                    

        	       form.getChildren().add(2,v);}
	v.setVisible(true);
	textField_address.setVisible(true);
	addresscom.setVisible(false);
	col.setVisible(false);
	test.setVisible(false);

//                  if (textField_id.getText().isEmpty())  
//        	        JOptionPane.showMessageDialog(null, "Choose a process before entering data");

//        	       form.getChildren().remove(col); 
        	       Do.setOnAction(c -> {
        	    	   tableview.getColumns().clear();
        	    String sql = "SELECT * FROM Customer WHERE";
        	    
        	    if (textField_id.getText().isEmpty() && textField_fname.getText().isEmpty()&& textField_lname.getText().isEmpty()
        	    		&& textField_address.getText().isEmpty()&& textField_job.getText().isEmpty()) {
        	    	 tableview.getColumns().clear();
        	    	buildData("SELECT * FROM customer");
        	    } else {                      

        	        if (!textField_id.getText().isEmpty()) {
        	            sql += " id = '" + textField_id.getText() + "'";
        	        }
        	        if (!textField_fname.getText().isEmpty()) {
        	            if (sql.length() > 29)sql += " and f_name = '" + textField_fname.getText() + "'";
        	             else      sql += " f_name = '" + textField_fname.getText() + "'";
        	            
        	        }
        	        if (!textField_lname.getText().isEmpty()) {
        	            if (sql.length() > 29)sql += " and l_name = '" + textField_lname.getText() + "'";
        	             else      sql += " l_name = '" + textField_lname.getText() + "'";
        	            
        	        }
        	        if (!textField_address.getText().isEmpty()) {
        	            if (sql.length() > 29)sql += " and address = '" + textField_address.getText() + "'";
        	             else      sql += " address = '" + textField_address.getText() + "'";
        	            
        	        }
        	        if (!textField_job.getText().isEmpty()) {
        	            if (sql.length() > 29)sql += " and job = '" + textField_job.getText() + "'";
        	             else      sql += " job = '" + textField_job.getText() + "'";
        	            
        	        }
   buildData(sql);
        	    }  });
} catch(Exception b) {
    JOptionPane.showMessageDialog(null, "search Unsuccessfully");
    tableview.getColumns().clear();
    buildData("SELECT * FROM customer");
        	       
}
//        	  form.getChildren().add(1,imageView);
//         	       form.getChildren().remove( v);
//         	       form.getChildren().add(addresscom);
//         	       form.getChildren().add(test);
//         	       form.getChildren().add(col);

        	    
        	});


      	    
        		
        	insert.setOnAction(o -> {
        		if(!form.getChildren().contains(v)) {                      

         	       form.getChildren().add(2,v);}
        		v.setVisible(true);

        	    test.setVisible(true);

        		addresscom.setVisible(true);
     	 		col.setVisible(false);
     	 		test.setVisible(false);
     	 		  textField_address.setVisible(false);

    	 		String s = "INSERT INTO customer (id, f_name, l_name, address, job) VALUES(?,?,?,?,?)";
Do.setOnAction(f->{
	 tableview.getColumns().clear();
    	 		try {                  
    	 			
    	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
    	 		    PreparedStatement gb = con.prepareStatement(s);
    	 		   try {
 		            gb.setInt(1, Integer.parseInt(textField_id.getText()));
    	 		    gb.setString(2, textField_fname.getText());
    	 		    gb.setString(3, textField_lname.getText());
    	 		    gb.setInt(4,Integer.parseInt( addresscom.getValue()));
    	 		    gb.setString(5, textField_job.getText());
                    gb.executeUpdate();
    	 		  } catch (NumberFormatException j ) {
        	 		  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id address is not a number ");

    	 			}
    	 		   tableview.getColumns().clear();
    	 		   buildData("SELECT * FROM customer");
    	 		} catch (SQLException e1) {
    	 		  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id duplicate or is not a number ");
    	 		   tableview.getColumns().clear(); 
    	 		   buildData("SELECT * FROM customer");
    	 		}
//    	 		form.getChildren().remove(imageView);
//      	       form.getChildren().remove(v);
//      	       form.getChildren().remove(address);
//
//      	       form.getChildren().add(textField_address);

    	 		tableview.getColumns().clear();
    	 		 buildData("SELECT * FROM customer"); } );});
        	
        	update.setOnAction(o -> {
        		if(!form.getChildren().contains(v)) {
         	       form.getChildren().add(2,v);}
        		col.setVisible(false);
        		test.setVisible(false);

        		v.setVisible(true);

        		addresscom.setVisible(true);
  	 
  	 		  textField_address.setVisible(false);


      	    String s = "UPDATE customer SET f_name=?, l_name=?, address=?, job=? WHERE id=?";
  	 		Do.setOnAction(d -> { 	    try { tableview.getColumns().clear();
  	 	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
  	 	     PreparedStatement gb = con.prepareStatement(s);
try {
 	        gb.setInt(5, Integer.parseInt(textField_id.getText()));
 	        gb.setString(1, textField_fname.getText());
 	        gb.setString(2, textField_lname.getText());
 	        gb.setString(3, addresscom.getValue());
 	        gb.setString(4, textField_job.getText());
} catch (NumberFormatException j ) {
	  JOptionPane.showMessageDialog(null, "Record update unsuccessfully id is not a number ");

	}

 	        int rowsUpdated = gb.executeUpdate();
 	        int updateCount = gb.getUpdateCount();

 	        if (updateCount >=1) {System.out.println("update successfully");     
             JOptionPane.showMessageDialog(null, "Record updated successfully");
//
 	        }  
 	        else {
                       System.err.println("No such ID found for update");            	        
             JOptionPane.showMessageDialog(null, "No such ID found for update");
}
 	    
 	    }         	 	   
            catch (SQLException e1) {
            	tableview.getColumns().clear();
            	e1.printStackTrace();
 	    }    buildData("SELECT * FROM customer ");
// 	   form.getChildren().remove(imageView);
//	       form.getChildren().remove(v);
//	       form.getChildren().remove(address);
//	       form.getChildren().remove(test);
//	       form.getChildren().remove(col);
//	       form.getChildren().add(textField_address);

 	});
 	
 	
  });
        	delete.setOnAction(x->{
        		if(form.getChildren().contains(v)) {
         	       form.getChildren().remove(v);}
//        		form.getChildren().remove(imageView);
//      	       form.getChildren().remove(v);
//      	       form.getChildren().remove(addresscom);
//      	       form.getChildren().add(1,test);
//      	       form.getChildren().remove(textField_address);
v.setVisible(false);	
    			col.setVisible(true);	
    			test.setVisible(true);
//    			form.getChildren().add(1,test);   
//form.getChildren().add(2,col);

//    			textField_Country.setVisible(true);
    		Do.setOnAction(d->{
    			v.setVisible(false);	
    			col.setVisible(true);	
    			test.setVisible(true);
//    			String temp=getIdFromName("address",col.getValue(),test.getText(),"id");
//    			deleteRecord("customer","address",temp);
    		String temp=	getIdFromName("car",col.getValue(),test.getText(),"name");
    		System.out.print("fffffffffffffffffff"+temp);
    		 tableview.getColumns().clear();
		       //,,
//    		deleteRecord("orders","customer",test.getText());
    		
    			deleteRecord("customer",col.getValue(),test.getText());

    		

    		        	  	
    		});    });  	
        	
});
        
        
        address.setOnAction(e ->{
        	

        	
        	tableview = new TableView();
   	  	 tableview.setStyle("-fx-control-inner-background: #E89AA3;");

            buildData("select * from address");
            
            Label ID_Label = new Label("ID :");  
            TextField textField_ID = new TextField();

           Label Building_Label = new Label("Building :");  
           TextField textField_Building = new TextField();
           
            Label Street_Label = new Label("Street :");  
            TextField textField_Street = new TextField();
            
            Label City_Label = new Label("City :");  
            TextField textField_City = new TextField();
            
             Label Country_Label = new Label("Country :");  
             TextField textField_Country = new TextField();
             TextField test = new TextField();
                
             
             Button Do = new Button("Do");
             Button back = new Button("Finish");
             Button search = new Button("search");
             Button delete = new Button("delete");
             Button update = new Button("update");
             Button insert = new Button("insert");
             back.setStyle("-fx-background-color: red;");
             Do.setStyle("-fx-background-color: green;");

             HBox h2 = new HBox(5);
             h2.getChildren().addAll(back,Do);
             HBox h = new HBox(5);
             h.setSpacing(90);
  	       h.getChildren().addAll( delete,update,insert,search);
           
             
             VBox form = new VBox(5);
             form.setStyle("-fx-padding: 20;"); // add padding to the VBox
           
//             ComboBox<String> com1=new ComboBox<>();     
             ComboBox<String> col=new ComboBox<>();     
             col.getItems().add("id");
         	col.getItems().add("buidling");
         	col.getItems().add("street");
         	col.getItems().add("city");
         	col.getItems().add("country");
                          VBox v=new VBox();
                          
             v.getChildren().addAll(ID_Label ,textField_ID , City_Label , textField_City , Country_Label, textField_Country ,Street_Label, textField_Street,Building_Label, textField_Building);
             form.getChildren().addAll( h,op,test,v,col,h2);
             col.setVisible(false);
             form.getChildren().addAll(tableview);
        	Scene address_scene = new Scene(form);
        	
        	    stage.setScene(address_scene);
        	    stage.setWidth(600); 
        	    stage.setHeight(600); 
        	    stage.show();
        	
        		back.setOnAction(o->{
        	  		stage.setScene(sc);
        	  	  stage.setWidth(800);
        	        stage.setHeight(600); 
        	  	    stage.show();
        	  	});
        	  	
            	test.setVisible(false);
        	  	
        	  	search.setOnAction(o -> {
        	  	  if(!form.getChildren().contains(v)) {
           	       form.getChildren().add(2,v);}
        	  	v.setVisible(true);

        	  	col.setVisible(false);
    	 		  textField_ID.setVisible(true);
    	 		 test.setVisible(false);
        	  		Do.setOnAction(j -> {
        		    tableview.getColumns().clear();
        		    String sql = "SELECT * FROM address WHERE";
        		    
        		    if (textField_ID.getText().isEmpty() && textField_Building.getText().isEmpty()  && textField_Street.getText().isEmpty()   && textField_City.getText().isEmpty()    && textField_Country.getText().isEmpty()) {
        		        buildData("SELECT * FROM address");
        		    } else { 
        		        if (!textField_ID.getText().isEmpty()) {
        		            sql += " id = '" + textField_ID.getText() + "'";
        		        }
        		        if (!textField_Building.getText().isEmpty()) {
        		            if (sql.length() > 28)
        		            	sql += " and buidling = '" + textField_Building.getText() + "'";
        		             else      sql += " buidling = '" + textField_Building.getText() + "'";
        		            
        		        }
        		        
        		        
        		        if (!textField_Street.getText().isEmpty()) {
        		            if (sql.length() > 28)
        		            	sql += " and street = '" + textField_Street.getText() + "'";
        		             else      sql += " street = '" + textField_Street.getText() + "'";
        		            
        		        }
        		        
        		        if (!textField_City.getText().isEmpty()) {
        		            if (sql.length() > 28)
        		            	sql += " and city = '" + textField_City.getText() + "'";
        		             else      sql += " city = '" + textField_City.getText() + "'";
        		            
        		        }
        		        
        		        if (!textField_Country.getText().isEmpty()) {
        		            if (sql.length() > 28)
        		            	sql += " and country = '" + textField_Country.getText() + "'";
        		             else      sql += " country = '" + textField_Country.getText() + "'";
        		            
        		        }

        		        buildData(sql);
        		    }
        		});	});

        	 	insert.setOnAction(o -> {
        	 		if(!form.getChildren().contains(v)) {
              	       form.getChildren().add(2,v);}
        	 		v.setVisible(true);
         	 		test.setVisible(false);

               col.setVisible(false);
      	 		  textField_ID.setVisible(true);
      	  		Do.setOnAction(j -> {
        	 		String s = "INSERT INTO address (id, buidling, street, city, country) VALUES(?,?,?,?,?)";

        	 		try {
        	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
        	 		    PreparedStatement gb = con.prepareStatement(s);
try {
        	 		    gb.setInt(1, Integer.parseInt(textField_ID.getText()));
        	 		    gb.setInt(2, Integer.parseInt(textField_Building.getText()));
        	 		    gb.setString(3, textField_Street.getText());
        	 		    gb.setString(4, textField_City.getText());
        	 		    gb.setString(5, textField_Country.getText());

        	 		    gb.executeUpdate();
        	 		   tableview.getColumns().clear();
        		        } catch (NumberFormatException d ) {   
        		        	buildData("SELECT * FROM address");

	  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id or building is not a number ");

	}
        	 		} catch (SQLException e1) {
        	 		    JOptionPane.showMessageDialog(null, "Record insert  unsuccessfully id dup ");
        	 		   tableview.getColumns().clear();
        	 		  buildData("SELECT * FROM address");
        	 		    
        	 		}

        		   } );});
        	 	
        	 	update.setOnAction(o -> {
        	 		if(!form.getChildren().contains(v)) {
              	       form.getChildren().add(2,v);}
        	 		        	 		 col.setVisible(false);
        	 		        	 		test.setVisible(false);

        	 		           		v.setVisible(true);

                	    String s = "UPDATE address SET buidling=?, street=?, city=?, country=? WHERE id=?";
                	    Do.setOnAction(l->{
                	    	   try {tableview.getColumns().clear();
                	  	 	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
                	  	 	        PreparedStatement gb = con.prepareStatement(s); 
try {
                        	        gb.setInt(5, Integer.parseInt(textField_ID.getText()));
                        	        gb.setString(2, textField_Street.getText());
                        	        gb.setString(3, textField_City.getText());
                        	        gb.setString(4, textField_Country.getText());
                        	        gb.setInt(1, Integer.parseInt(textField_Building.getText()));

                        	        int rowsUpdated = gb.executeUpdate();
                        	        int updateCount = gb.getUpdateCount();


                	      	        if (updateCount >= 1) {System.out.println("successfully");     
                	                  JOptionPane.showMessageDialog(null, "Record updated successfully");

                	      	        } else {
                	System.err.println("unsuccessfully");            	       
                	                  JOptionPane.showMessageDialog(null, "unsuccessfully");
                	                  tableview.getColumns().clear();
                	  	 	        } gb.executeUpdate();
                	  	 	        buildData("SELECT * FROM address ");
} catch (NumberFormatException j ) {
	  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id building  is not a number ");

	}
                	  	 	    } catch (SQLException e1) {
                	  	 	        e1.printStackTrace();
                	  	 	    }
                	  	 	  
                	});});
        		delete.setOnAction(x->{
        			v.setVisible(false);	
        			col.setVisible(true);	
                	test.setVisible(true);
                	if(form.getChildren().contains(v)) {
              	       form.getChildren().remove(v);}
                	if(!form.getChildren().contains(test)) {
        			form.getChildren().add(1,test);}
//        			test.setVisible(true);
        			
        		Do.setOnAction(d->{
        			String temp=getIdFromName("customer",col.getValue(),test.getText(),"id");
        			deleteRecord("orders","customer", temp);
//        		String temp=	getIdFromName("car",col.getValue(),test.getText(),"name");
        		System.out.print("fffffffffffffffffff"+ temp);
        		//..
//        			deleteRecord("customer","address",test.getText());
        		
        			deleteRecord("address",col.getValue(),test.getText());

        		


        		        	  	
        		});    	
            	  	  	
        	  });
        });
        
        
        
        
        
        
        
        
        
        car_part.setOnAction(e ->{
        	

        	
        	tableview = new TableView();
   	  	 tableview.setStyle("-fx-control-inner-background: #E89AA3;");

            buildData("select * from Car_part");
            
            Label car_Label = new Label("Car :");  
          TextField textField_Car = new TextField();
  
         Label part_Label = new Label("Part :");  
         TextField textField_Part = new TextField();
         TextField test = new TextField();

	         
	         Button Do = new Button("Do");
	         Button back = new Button("Finish");
	         Button search = new Button("search");
	         Button delete = new Button("delete");
	         Button update = new Button("update");
	         Button insert = new Button("insert");
	         back.setStyle("-fx-background-color: red;");
	         Do.setStyle("-fx-background-color: green;");

	         HBox h2 = new HBox(5);
	         h2.getChildren().addAll(back,Do);
	         HBox h = new HBox(5);
	         h.setSpacing(90);
		       h.getChildren().addAll( delete,update,insert,search);
	       
	         
	         VBox form = new VBox(5);
	         form.setStyle("-fx-padding: 20;"); // add padding to the VBox
	       
	         ComboBox<String> carCom=new ComboBox<>();     
	         ComboBox<String> partCom=new ComboBox<>();     

	         ComboBox<String> col=new ComboBox<>();     
	         col.getItems().add("car");
	     	col.getItems().add("part");
	     	
	        primaryComboBox(carCom,"car","name");
            primaryComboBox(partCom,"device","no");
	                      VBox v=new VBox();
	         v.getChildren().addAll(car_Label ,textField_Car,carCom , part_Label , textField_Part,partCom);
	         form.getChildren().addAll( h,op,test,v,col,h2);
	         col.setVisible(false);
	carCom.setVisible(false);
	partCom.setVisible(false);
	         form.getChildren().addAll(tableview);
	    	Scene address_scene = new Scene(form);
	    	        	test.setVisible(false);

	    	    stage.setScene(address_scene);
	    	    stage.setWidth(600); 
	    	    stage.setHeight(600); 
	    	    stage.show();
        	back.setOnAction(o->{
        		stage.setScene(sc);
        		  stage.setWidth(800);
        	        stage.setHeight(600);
        	    stage.show();
        	});
        	search.setOnAction(o -> {
        		 if(!form.getChildren().contains(v)) {
          	       form.getChildren().add(2,v);}
  	v.setVisible(true);
  	textField_Car.setVisible(true);
  	textField_Part.setVisible(true);
  	partCom.setVisible(false);
    carCom.setVisible(false);
  	col.setVisible(false);
  	test.setVisible(false);
  	Do.setOnAction(d -> {
        	    tableview.getColumns().clear();
        	    String sql = "SELECT * FROM car_part WHERE";
        	    
        	    if (textField_Car.getText().isEmpty() && textField_Part.getText().isEmpty()) {
        	        buildData("SELECT * FROM car_part");
        	    } else {
        	        if (!textField_Car.getText().isEmpty()) {
        	            sql += " Car = '" + textField_Car.getText() + "'";
        	        }
        	        if (!textField_Part.getText().isEmpty()) {
        	            if (sql.length() > 29)sql += " and Part = '" + textField_Part.getText() + "'";
        	             else      sql += " Part = '" + textField_Part.getText() + "'";
        	            
        	        }  

        	        buildData(sql);
        	    }
        	    
        	    
	});
        	});
        	insert.setOnAction(d -> {
       		 if(!form.getChildren().contains(v)) {
        	       form.getChildren().add(2,v);}
	v.setVisible(true);
	textField_Car.setVisible(false);
	textField_Part.setVisible(false);
	partCom.setVisible(true);
    carCom.setVisible(true);
	col.setVisible(false);
	test.setVisible(false);
      	    
    	 		String s = "INSERT INTO car_part (car, part) VALUES(?,?)";
    	 	
    	 		  Do.setOnAction(r->{
    	 		try {  tableview.getColumns().clear();
    	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
    	 		    PreparedStatement gb = con.prepareStatement(s);

    	 		    gb.setString(1,carCom.getValue());
    	 		    gb.setInt(2, Integer.parseInt(partCom.getValue()));
    	 		

    	 		    gb.executeUpdate();
    	 		   tableview.getColumns().clear();
    		        buildData("SELECT * FROM car_part");
    	 		} catch (SQLException e1) {
    	 		    e1.printStackTrace();
    	 		   tableview.getColumns().clear();
   		        buildData("SELECT * FROM car_part");
    	 		}

        	   } );   } );
    	  	
    	  	

      	  	update.setOnAction(o -> {
        		 if(!form.getChildren().contains(v)) {
          	       form.getChildren().add(2,v);}
  	v.setVisible(true);
  	textField_Car.setVisible(false);
  	textField_Part.setVisible(false);
  	partCom.setVisible(true);
    carCom.setVisible(true);
  	col.setVisible(false);
  	test.setVisible(false);

              primaryComboBox(carCom,"car","name");
              primaryComboBox(partCom,"device","no");
 
             
      	    String s = "UPDATE car_part SET part=? WHERE car=?";
  	 		Do.setOnAction(d -> {	    try { tableview.getColumns().clear();
  	 	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
  	 	     PreparedStatement gb = con.prepareStatement(s);

    	     
 	        gb.setInt(1, Integer.parseInt(partCom.getValue()));
 	   
 	        gb.setString(2, carCom.getValue());

 	        int rowsUpdated = gb.executeUpdate();
 	        int updateCount = gb.getUpdateCount();
//             gb.executeUpdate();

 	        if (updateCount >=1) {System.out.println("update successfully");     
             JOptionPane.showMessageDialog(null, "Record updated successfully");
tableview.getColumns().clear();
             buildData("SELECT * FROM car_part ");
 	        }  
 	        else {
                       System.err.println("No such ID found for update");            	        
             JOptionPane.showMessageDialog(null, "No such ID found for update");
	        
             tableview.getColumns().clear();
             buildData("SELECT * FROM car_part ");
}
 	    
 	    }         	 	   
            catch (SQLException e1) {
 	        e1.printStackTrace();
 	        
 	    }   
});
 	});
        	delete.setOnAction(x->{ 
        		if(form.getChildren().contains(v)) {
           	       form.getChildren().remove(v);}
             	if(!form.getChildren().contains(test)) {
     			form.getChildren().add(1,test);}
    			col.setVisible(true);	
    		
    		  	partCom.setVisible(false);
    		    carCom.setVisible(false);
    		  	col.setVisible(true);
    		  	test.setVisible(true);
//    			textField_Country.setVisible(true);
    			form.getChildren().remove(v);
    		Do.setOnAction(d->{
//    			String temp=getIdFromName("address",col.getValue(),test.getText(),"id");
//    			deleteRecord("customer","address",temp);
//    		String temp=	getIdFromName("car",col.getValue(),test.getText(),"part");
    			deleteRecord("orders","car",test.getText());
    			deleteRecord("car_part","part",test.getText());

//    			String temp2=	getIdFromName("car",col.getValue(),test.getText(),"name");
    			deleteRecord("car_part","car",test.getText());
//    			System.out.print(temp2);

    	

    		        	  	
    		});    });  	
        	
        		
        		
        		
        
        	
        	
        });
        
        
        
        
        
        
    Customer.setOnAction(e ->{
        	
        	stage.close();	
        		
        		
        
        	
        	
        });
        
        
        
        
        
        
        
        
        
        
        
        
  car.setOnAction(e ->{
        	

        	
        	tableview = new TableView();
   	  	 tableview.setStyle("-fx-control-inner-background: #E89AA3;");

            buildData("select * from Car");
            
            Label name_Label = new Label("name :");
          TextField textField_Name = new TextField();
  
          Label model_Label = new Label("model :");
          TextField textField_Model = new TextField();
  
          Label year_Label =  new Label("year :");
          TextField textField_Year = new TextField();
  
          Label made_Label = new Label("made :");
          TextField textField_Made = new TextField();
          TextField test = new TextField();

          
          Button Do = new Button("Do");
          Button back = new Button("Finish");
          Button search = new Button("search");
          Button delete = new Button("delete");
          Button update = new Button("update");
          Button insert = new Button("insert");
          back.setStyle("-fx-background-color: red;");
          Do.setStyle("-fx-background-color: green;");

          HBox h2 = new HBox(5);
          h2.getChildren().addAll(back,Do);
          HBox h = new HBox(5);
          h.setSpacing(90);
	       h.getChildren().addAll( delete,update,insert,search);
        
          
          VBox form = new VBox(5);
          form.setStyle("-fx-padding: 20;"); // add padding to the VBox
        
//          ComboBox<String> com1=new ComboBox<>();     
          ComboBox<String> made=new ComboBox<>();     

          ComboBox<String> col=new ComboBox<>();     
          col.getItems().add("name");
      	col.getItems().add("model");
      	col.getItems().add("year");
      	col.getItems().add("made");
      		
      	
                       VBox v=new VBox();
          v.getChildren().addAll(name_Label, textField_Name, model_Label, textField_Model, year_Label, textField_Year,made_Label,textField_Made,made);
          form.getChildren().addAll( h,op,test,v,col,h2);
          col.setVisible(false);
          test.setVisible(false);
//com1.setVisible(false);
made.setVisible(false);
          form.getChildren().addAll(tableview);
     	Scene address_scene = new Scene(form);
        primaryComboBox(made,"manufacture","name");

     	    stage.setScene(address_scene);
     	    stage.setWidth(600); 
     	    stage.setHeight(600); 
     	    stage.show();
        	
        	back.setOnAction(o->{
        		stage.setScene(sc);
        		  stage.setWidth(800);
        	        stage.setHeight(600);
        	    stage.show();
        	});
        	
        	search.setOnAction(o -> {
        		if(!form.getChildren().contains(v)) {
          	       form.getChildren().add(2,v);}
 	v.setVisible(true);

        		 made.setVisible(false);
        		 textField_Made.setVisible(true);
         	  	col.setVisible(false);
         	 	test.setVisible(false);
               	Do.setOnAction(m -> {
        	    tableview.getColumns().clear();
        	    String sql = "SELECT * FROM car WHERE";
        	    
        	    if (textField_Name.getText().isEmpty() && textField_Model.getText().isEmpty()  && textField_Year.getText().isEmpty()   && textField_Made.getText().isEmpty()) {
        	        buildData("SELECT * FROM car");
        	    } else {
        	        if (!textField_Name.getText().isEmpty()) {
        	            sql += " name = '" + textField_Name.getText() + "'";
        	        }
        	        if (!textField_Model.getText().isEmpty()) {
        	            if (sql.length() > 24)
        	            	sql += " and model = '" + textField_Model.getText() + "'";
        	             else      sql += " model = '" + textField_Model.getText() + "'";
        	            
        	        }
        	        
        	        
        	        if (!textField_Year.getText().isEmpty()) {
        	            if (sql.length() > 24)
        	            	sql += " and year = '" + textField_Year.getText() + "'";
        	             else      sql += " year = '" + textField_Year.getText() + "'";
        	            
        	        }
        	        
        	        if (!textField_Made.getText().isEmpty()) {
        	            if (sql.length() > 24)
        	            	sql += " and made = '" + textField_Made.getText() + "'";
        	             else      sql += " made = '" + textField_Made.getText() + "'";
        	            
        	        }

        	        buildData(sql);
        	    }
            	});
        	});
        	
        	insert.setOnAction(o -> {
        		if(!form.getChildren().contains(v)) {
          	       form.getChildren().add(2,v);}
 	v.setVisible(true);

        		 made.setVisible(true);
        		 textField_Made.setVisible(true);
         	  	col.setVisible(false);
         	 	test.setVisible(false);
    	 		String s = "INSERT INTO car (name, model, year, made) VALUES(?,?,?,?)";
    	 
	 		 
//	 		  textField_Name.setVisible(false);
	 		  textField_Made.setVisible(false);
	 		 Do.setOnAction(r->{
    	 		try { tableview.getColumns().clear();
    	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
    	 		    PreparedStatement gb = con.prepareStatement(s);
try {

    	 		    gb.setString(1,textField_Name.getText());
    	 		    gb.setString(2,textField_Model.getText());
    	 		    gb.setInt(3,  Integer.parseInt(textField_Year.getText()));
    	 		    gb.setString(4, made.getValue());

    	 		    gb.executeUpdate();
} catch (NumberFormatException j ) {
	  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id or year is not a number ");

	}

    	 		   tableview.getColumns().clear();
    		        buildData("SELECT * FROM car");
    	 		} catch (SQLException e1) {
    	 			  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id dup");

    	 		   tableview.getColumns().clear();
   		        buildData("SELECT * FROM car");
    	 		}

	 		  } );  } );
        	update.setOnAction(o -> {
        		if(!form.getChildren().contains(v)) {
          	       form.getChildren().add(2,v);}
 	v.setVisible(true);

        		 made.setVisible(true);
        		 textField_Made.setVisible(false);
         	  	col.setVisible(false);
         	 	test.setVisible(false);
//              primaryComboBox(com1,"car","name");

  	 	    String s = "UPDATE car SET model=?, year=?, made=? WHERE name=?";
  	 		Do.setOnAction(d -> {	    try { tableview.getColumns().clear();
  	 	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
  	 	        PreparedStatement gb = con.prepareStatement(s); 
  	 	        try {
  	 	        gb.setString(4, textField_Name.getText());
  	 	        gb.setString(1, textField_Model.getText());
  	 	        gb.setString(3,made.getValue());
  	 	        gb.setInt(2, Integer.parseInt(textField_Year.getText()));
  	 	    
      	        int rowsUpdated = gb.executeUpdate();
      	        int updateCount = gb.getUpdateCount();

      	        if (updateCount >= 1) {System.out.println("successfully");     
                  JOptionPane.showMessageDialog(null, "Record updated successfully");

      	        } else {
System.err.println("unsuccessfully");            	       
                  JOptionPane.showMessageDialog(null, "unsuccessfully");
                  tableview.getColumns().clear();
  	 	        } } catch (NumberFormatException j ) {
   	 		  JOptionPane.showMessageDialog(null, "Record update  unsuccessfully id or year  is not a number ");

	 			} gb.executeUpdate();
  	 	        buildData("SELECT * FROM car ");
  	 	    } catch (SQLException e1) { buildData("SELECT * FROM car ");
  	 	    }
  	 	});});
        	delete.setOnAction(x->{
        		if(form.getChildren().contains(v)) {
            	       form.getChildren().remove(v);}
              	if(!form.getChildren().contains(test)) {
      			form.getChildren().add(1,test);}
     			col.setVisible(true);	
        		 made.setVisible(false);
        		 textField_Made.setVisible(false);
         	  	col.setVisible(true);
         	 	test.setVisible(true);
    		Do.setOnAction(d->{
//    			String temp=getIdFromName("address",col.getValue(),test.getText(),"id");
//    			deleteRecord("customer","address",temp);
    		String temp=	getIdFromName("car",col.getValue(),test.getText(),"name");
    		System.out.print("fffffffffffffffffff"+temp);
    			deleteRecord("orders","car",test.getText());
    			String temp2=	getIdFromName("car",col.getValue(),test.getText(),"name");
    			deleteRecord("car_part","car",test.getText());
    			System.out.print(temp2);
    			deleteRecord("car",col.getValue(),test.getText());

    			col.setVisible(false);	

    				form.getChildren().remove(test);
//    				 buildData("SELECT * FROM car");
//    			form.getChildren().add(2,v);
//    			form.getChildren().add(2,v);


    		        	  	
    		});    });  	
        	
        	
        });
        
        
  
  
  
  
  
  device.setOnAction(e ->{
	  	

	  	
	  	tableview = new TableView();
	  	 tableview.setStyle("-fx-control-inner-background: #E89AA3;");

	      buildData("select * from Device");
	      
	      Label no_Label = new Label("No :");  
        TextField textField_No = new TextField();

       Label name_Label = new Label("Name :");  
       TextField textField_Name = new TextField();
       
        Label price_Label = new Label("Price :");  
        TextField textField_Price = new TextField();

        
        Label weight_Label = new Label("Weight :");  
        TextField textField_Weight = new TextField();
        
         Label made_Label = new Label("Made :");  
         TextField textField_Made = new TextField();	      
	      	      
	      	         TextField test = new TextField();

	      	         
	      	         Button Do = new Button("Do");
	      	         Button back = new Button("Finish");
	      	         Button search = new Button("search");
	      	         Button delete = new Button("delete");
	      	         Button update = new Button("update");
	      	         Button insert = new Button("insert");
	      	       back.setStyle("-fx-background-color: red;");
	      	       Do.setStyle("-fx-background-color: green;");

	      	         HBox h2 = new HBox(5);
	      	         h2.getChildren().addAll(back,Do);
	      	         HBox h = new HBox(5);
	      	         h.setSpacing(90);
	      		       h.getChildren().addAll( delete,update,insert,search);
	      	       
	      	         
	      	         VBox form = new VBox(5);
	      	         form.setStyle("-fx-padding: 20;"); // add padding to the VBox
	      	       
//	      	         ComboBox<String> com1=new ComboBox<>();     
	      	         ComboBox<String> made=new ComboBox<>();     

	      	         ComboBox<String> col=new ComboBox<>();     
	      	         col.getItems().add("no");
	      	     	col.getItems().add("name");
	      	     	col.getItems().add("price");
	      	     	col.getItems().add("weight");
	      	     	col.getItems().add("made");
	                primaryComboBox(made,"manufacture","name");


	      	                      VBox v=new VBox();
	      	         v.getChildren().addAll(no_Label ,textField_No , name_Label , textField_Name , price_Label, textField_Price , weight_Label, textField_Weight , made_Label, textField_Made,made);
	      	         form.getChildren().addAll( h,op,test,v,col,h2);
	      	         col.setVisible(false);
	             	test.setVisible(false);	    
	             	made.setVisible(false);
	      	         form.getChildren().addAll(tableview);
	      	    	Scene address_scene = new Scene(form);
	      	    	
	      	    	    stage.setScene(address_scene);
	      	    	    stage.setWidth(600); 
	      	    	    stage.setHeight(600); 
	      	    	    stage.show();	  
	      	    	    back.setOnAction(o->{
	  		            stage.setScene(sc);
	                	  stage.setWidth(800);
	                    stage.setHeight(600);
	             	    stage.show();
	  	});
	  	
	  	
	  	

	  	search.setOnAction(o -> {
	  		if(!form.getChildren().contains(v)) {
      	       form.getChildren().add(2,v);}
	  		 made.setVisible(false);
	   		 textField_Made.setVisible(true);
	   	  	v.setVisible(true);
	   	  	col.setVisible(false);
	   	  	test.setVisible(false);
		    Do.setOnAction(m-> {
		    String sql = "SELECT * FROM device WHERE";
	  	 if (textField_No.getText().isEmpty() && textField_Name.getText().isEmpty()  && textField_Price.getText().isEmpty()   && textField_Weight.getText().isEmpty()    && textField_Made.getText().isEmpty()) {
		        buildData("SELECT * FROM device");
		    } else {
		        if (!textField_No.getText().isEmpty()) {
		            sql += " no = '" + textField_No.getText() + "'";
		        }
		        if (!textField_Name.getText().isEmpty()) {
		            if (sql.length() > 27)
		            	sql += " and name = '" + textField_Name.getText() + "'";
		             else      sql += " name = '" + textField_Name.getText() + "'";
		            
		        }
		        
		        
		        if (!textField_Price.getText().isEmpty()) {
		            if (sql.length() > 27)
		            	sql += " and price = '" + textField_Price.getText() + "'";
		             else      sql += " price = '" + textField_Price.getText() + "'";
		            
		        }
		        
		        if (!textField_Weight.getText().isEmpty()) {
		            if (sql.length() > 27)
		            	sql += " and weight = '" + textField_Weight.getText() + "'";
		             else      sql += " weight = '" + textField_Weight.getText() + "'";
		            
		        }
		        
		        if (!textField_Made.getText().isEmpty()) {
		            if (sql.length() > 27)
		            	sql += " and made = '" + textField_Made.getText() + "'";
		             else      sql += " made = '" + textField_Made.getText() + "'";
		            
		        }
			    tableview.getColumns().clear();

		        buildData(sql);
		    }
		    });});
		insert.setOnAction(o -> {
			if(!form.getChildren().contains(v)) {
	      	       form.getChildren().add(2,v);}
		  		 made.setVisible(true);
		   		 textField_Made.setVisible(false);
		   	  	v.setVisible(true);
		   	  	col.setVisible(false);
		   	  	test.setVisible(false);
	 		String s = "INSERT INTO device (no, name, price, weight,made) VALUES(?,?,?,?,?)";
	 		 Do.setOnAction(r->{
	 		try {tableview.getColumns().clear();
	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
	 		    PreparedStatement gb = con.prepareStatement(s);
	 		    try {
                gb.setInt(1,Integer.parseInt(textField_No.getText()));
	 		    gb.setString(2,textField_Name.getText());
	 		    gb.setInt(3,  Integer.parseInt(textField_Price.getText()));
	 		    gb.setInt(4,Integer.parseInt( textField_Weight.getText()));
	 		    gb.setString(5, made.getValue());
	 		   } catch (NumberFormatException j ) {
     	 		  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id or price or weight is not a number ");

 	 			}
	 		      gb.executeUpdate();} catch (SQLException e1) {
   	 		  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id duplicate ");
	 		   tableview.getColumns().clear();
		        buildData("SELECT * FROM device");
	 		}
	 		   tableview.getColumns().clear();
		        buildData("SELECT * FROM device");
	 	
	 		

		 } ); } );
 		update.setOnAction(up -> {
 			if(!form.getChildren().contains(v)) {
	      	       form.getChildren().add(2,v);}
		  		 made.setVisible(true);
		   		 textField_Made.setVisible(false);
		   	  	v.setVisible(true);
		   	  	col.setVisible(false);
		   	  	test.setVisible(false);
             primaryComboBox(made,"manufacture","name");
         
   	    String s = "UPDATE device SET name=?, price=?, weight=?, made=? WHERE no=?";
   	 Do.setOnAction(d -> {
   	 	    try { tableview.getColumns().clear();
	 	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
	 	       PreparedStatement gb = con.prepareStatement(s);
try {

	 	 		  gb.setInt(5, Integer.parseInt(textField_No.getText()));
	 	 		    gb.setString(1, textField_Name.getText());
	 	 		   gb.setInt(2, Integer.parseInt(textField_Price.getText()));
	 	 		  gb.setString(4, made.getValue());
	 	 		 gb.setInt(3, Integer.parseInt(textField_Weight.getText()));
} catch (NumberFormatException j ) {
//	  JOptionPane.showMessageDialog(null, "Record update  unsuccessfully id or price or weight is not a number ");

	}
	   	        int rowsUpdated = gb.executeUpdate();
	   	        int updateCount = gb.getUpdateCount();

	   	        if (updateCount >=1) {System.out.println("update successfully");     
	               JOptionPane.showMessageDialog(null, "Record updated successfully");
	//
	   	        }  
	   	        else {
	                         System.err.println("No such ID found for update");            	        
	               JOptionPane.showMessageDialog(null, "No such ID found for update");
	               tableview.getColumns().clear();
	
	 	        } 
//	   	        gb.executeUpdate();
	 	      
	 	    } catch (SQLException e1) {
	 	    	  JOptionPane.showMessageDialog(null, "Record update unsuccessfully id is not a number ");
	 	    }  buildData("SELECT * FROM device ");
	 	});});
	  	delete.setOnAction(m->{
	  		if(form.getChildren().contains(v)) {
     	       form.getChildren().remove(v);}
       	if(!form.getChildren().contains(test)) {
			form.getChildren().add(1,test);}
		  		 made.setVisible(false);
		   		 textField_Made.setVisible(false);
		   	  	v.setVisible(false);
		   	  	col.setVisible(true);
		   	  	test.setVisible(true);
    		Do.setOnAction(d->{
    	 	  	col.setVisible(true);
		   	  	test.setVisible(true);
//    			String temp=getIdFromName("address",col.getValue(),test.getText(),"id");
//    			deleteRecord("customer","address",temp);
    		String temp=	getIdFromName("car",col.getValue(),test.getText(),"name");
    		System.out.print("fffffffffffffffffff"+temp);
			deleteRecord("car_part","part",test.getText());
    			String temp2=	getIdFromName("car",col.getValue(),test.getText(),"name");
deleteRecord("device",col.getValue(),test.getText());
    			System.out.print(temp2);
    			deleteRecord("device",col.getValue(),test.getText());


//    				 buildData("SELECT * FROM device");
//    			form.getChildren().add(2,v);
//    			form.getChildren().add(2,v);


    		        	  	
    		});    });  	
	  	});
    	
    	
 		  
   		
  
  manufacture.setOnAction(e ->{
	  	

	  	
	  	tableview = new TableView();
	  	 tableview.setStyle("-fx-control-inner-background: #E89AA3;");

	      buildData("select * from Manufacture");
	      
	      Label name_Label = new Label("Name :");  
        TextField textField_Name = new TextField();

       Label type_Label = new Label("Type :");  
       TextField textField_Type = new TextField();
       
        Label city_Label = new Label("City :");  
        TextField textField_City = new TextField();

        Label country_Label = new Label("Country :");  
        TextField textField_Country = new TextField();     

	      	         TextField test = new TextField();

	      	         
	      	         Button Do = new Button("Do");
	      	         Button back = new Button("Finish");
	      	         Button search = new Button("search");
	      	         Button delete = new Button("delete");
	      	         Button update = new Button("update");
	      	         Button insert = new Button("insert");
	      	       back.setStyle("-fx-background-color: red;");
	      	       Do.setStyle("-fx-background-color: green;");

	      	         HBox h2 = new HBox(5);
	      	         h2.getChildren().addAll(back,Do);
	      	         HBox h = new HBox(5);
	      	         h.setSpacing(90);
	      		       h.getChildren().addAll( delete,update,insert,search);
	      	       
	      	         
	      	         VBox form = new VBox(5);
	      	         form.setStyle("-fx-padding: 20;"); // add padding to the VBox
	      	       
//	      	         ComboBox<String> com1=new ComboBox<>();     
//	      	         ComboBox<String> made=new ComboBox<>();     

	      	         ComboBox<String> col=new ComboBox<>();     
	      	         col.getItems().add("name");
	      	     	col.getItems().add("type");
	      	     	col.getItems().add("city");
	      	     	col.getItems().add("country");
	      	     		

	      	                      VBox v=new VBox();
	      	         v.getChildren().addAll(name_Label ,textField_Name , type_Label , textField_Type ,city_Label, textField_City , country_Label, textField_Country);
	      	         form.getChildren().addAll( h,op,test,v,col,h2);
	      	         col.setVisible(false);
	      	test.setVisible(false);
//	      	made.setVisible(false);
	      	         form.getChildren().addAll(tableview);
	      	    	Scene address_scene = new Scene(form);
	      	    	
	      	    	    stage.setScene(address_scene);
	      	    	    stage.setWidth(600); 
	      	    	    stage.setHeight(600); 
	      	    	    stage.show();
	      	    	  back.setOnAction(o->{
		  		            stage.setScene(sc);
		               	  stage.setWidth(800);
		                    stage.setHeight(600);
		             	    stage.show();
		  	});
		  	
	  	search.setOnAction(o -> {
	  		if(!form.getChildren().contains(v)) {
	      	       form.getChildren().add(2,v);}
		  	
		   	  	v.setVisible(true);
		   	  	col.setVisible(false);
		   	  	test.setVisible(false);
		   	  			    Do.setOnAction(j -> {

		    tableview.getColumns().clear();
		    String sql = "SELECT * FROM manufacture WHERE";
	  	 if (textField_Name.getText().isEmpty() && textField_Type.getText().isEmpty()  && textField_City.getText().isEmpty()   && textField_Country.getText().isEmpty()) {
		        buildData("SELECT * FROM manufacture");
		    } else {
		        if (!textField_Name.getText().isEmpty()) {
		            sql += " name = '" + textField_Name.getText() + "'";
		        }
		        if (!textField_Type.getText().isEmpty()) {
		            if (sql.length() > 32)
		            	sql += " and type = '" + textField_Type.getText() + "'";
		             else      sql += " type = '" + textField_Type.getText() + "'";
		            
		        }
		        
		        
		        if (!textField_City.getText().isEmpty()) {
		            if (sql.length() > 32)
		            	sql += " and city = '" + textField_City.getText() + "'";
		             else      sql += " city = '" + textField_City.getText() + "'";
		            
		        }
		        
		        if (!textField_Country.getText().isEmpty()) {
		            if (sql.length() > 32)
		            	sql += " and country = '" + textField_Country.getText() + "'";
		             else      sql += " country = '" + textField_Country.getText() + "'";
		            
		        }
		        

		        buildData(sql);
		    }
			});
		});
		insert.setOnAction(o -> {
			if(!form.getChildren().contains(v)) {
	      	       form.getChildren().add(2,v);}
		  	
		   	  	v.setVisible(true);
		   	  	col.setVisible(false);
		   	  	test.setVisible(false);
	 		String s = "INSERT INTO manufacture (name, type, city, country) VALUES(?,?,?,?)";
	 		  Do.setOnAction(r->{
	 		try {tableview.getColumns().clear();
	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
	 		    PreparedStatement gb = con.prepareStatement(s);
try {
	 		    gb.setString(1,textField_Name.getText());
	 		    gb.setString(2,textField_Type.getText());
	 		    gb.setString(3, textField_City.getText());
	 		    gb.setString(4,textField_Country.getText());
} catch (NumberFormatException j ) {
	  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id is not a number ");

	}
	 		    gb.executeUpdate();
	 		    } catch (SQLException e1) {
	 		    JOptionPane.showMessageDialog(null, "Record insert  unsuccessfully id dup ");
	 		   tableview.getColumns().clear();
	 		  buildData("SELECT * FROM manufacture");
	 		    
	 		}
	 		   tableview.getColumns().clear();
		        buildData("SELECT * FROM manufacture");
	 	

	 		 } );} );
	  	
		
		update.setOnAction(up -> {
			if(!form.getChildren().contains(v)) {
	      	       form.getChildren().add(2,v);}
		  	
		   	  	v.setVisible(true);
		   	  	col.setVisible(false);
		   	  	test.setVisible(false);
	   	
    	    String s = "UPDATE manufacture SET type=?, city=?,  country=? WHERE name=?";
	   	 Do.setOnAction(d -> {
	   	 	    try { tableview.getColumns().clear();
		 	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
		 	       PreparedStatement gb = con.prepareStatement(s);

try {
   	 		    gb.setString(4, textField_Name.getText());
   	 		   gb.setString(1, textField_Type.getText());
   	 		  gb.setString(2, textField_City.getText());
   	 		 gb.setString(3, textField_Country.getText());
   	 		
} catch (NumberFormatException j ) {
	  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id is not a number ");

	}
       	        int rowsUpdated = gb.executeUpdate();
       	        int updateCount = gb.getUpdateCount();

       	        if (updateCount >=1) {System.out.println("update successfully");     
                   JOptionPane.showMessageDialog(null, "Record updated successfully");
//
       	        }  
       	        else {
                             System.err.println("No such ID found for update");            	        
                   JOptionPane.showMessageDialog(null, "No such ID found for update");
                   tableview.getColumns().clear();
                   
}
       	    
       	    }         	 	   
                  catch (SQLException e1) {
       	        e1.printStackTrace();
       	    }    buildData("SELECT * FROM manufacture ");
       	});
       	
   });
		delete.setOnAction(x->{
			if(form.getChildren().contains(v)) {
	     	       form.getChildren().remove(v);}
	       	if(!form.getChildren().contains(test)) {
				form.getChildren().add(1,test);}
			  		
			   	  	v.setVisible(false);
			   	  	col.setVisible(true);
			   	  	test.setVisible(true);
		Do.setOnAction(d->{
			v.setVisible(false);
	   	  	col.setVisible(true);
	   	  	test.setVisible(true);
			try {
//			String temp=getIdFromName("address",col.getValue(),test.getText(),"id");
//			deleteRecord("customer","address",temp);
//		String temp=	getIdFromName("device",col.getValue(),test.getText(),"name");
//		System.out.print("fffffffffffffffffff"+temp);
			deleteRecord("device","made",test.getText());
			String temp2=	getIdFromName("manufacture",col.getValue(),test.getText(),"name");
			deleteRecord("car","made",test.getText());
			System.out.print(temp2);
			deleteRecord("manufacture",col.getValue(),test.getText());

//				 buildData("SELECT * FROM car");
//			form.getChildren().add(2,v);
//			form.getChildren().add(2,v);


			}catch(Exception m) {
				System.out.println(m);
			}
		});    });  	
    	
   });
  
  
  
  
  
  orders.setOnAction(e ->{
	  	

	  	
	  	tableview = new TableView();
	  	 tableview.setStyle("-fx-control-inner-background: #E89AA3;");

	      buildData("select * from Orders");
	      
	      Label id_Label = new Label("ID :");  
        TextField textField_Id = new TextField();

        Label date_Label = new Label("Date :");  
        TextField textField_Date = new TextField();
       
        Label customer_Label = new Label("Customer :");  
        TextField textField_Customer = new TextField();

        Label car_Label = new Label("Car :");  
        TextField textField_Car = new TextField();    
//	     JComboBox g = new JComboBox<>();

	      	         TextField test = new TextField();

	      	         
	      	         Button Do = new Button("Do");
	      	         Button back = new Button("Finish");
	      	         Button search = new Button("search");
	      	         Button delete = new Button("delete");
	      	         Button update = new Button("update");
	      	         Button insert = new Button("insert");
	      	       back.setStyle("-fx-background-color: red;");
	      	       Do.setStyle("-fx-background-color: green;");

	      	         HBox h2 = new HBox(5);
	      	         h2.getChildren().addAll(back,Do);
	      	         HBox h = new HBox(5);
	      	         h.setSpacing(90);
	      		       h.getChildren().addAll( delete,update,insert,search);
	      	       
	      	         
	      	         VBox form = new VBox(5);
	      	         form.setStyle("-fx-padding: 20;"); // add padding to the VBox
	      	       
	      	         ComboBox<String> customer=new ComboBox<>();     
	      	         ComboBox<String> cars=new ComboBox<>();     

	      	         ComboBox<String> col=new ComboBox<>();     
	      	         col.getItems().add("id");
	      	     	col.getItems().add("date");
	      	     	col.getItems().add("customer");
	      	     	col.getItems().add("car");
	      	     		

	      	                      VBox v=new VBox();
	      	         v.getChildren().addAll(id_Label ,textField_Id , date_Label , textField_Date ,customer_Label, textField_Customer,customer , car_Label, textField_Car,cars);
	      	         form.getChildren().addAll( h,op,test,v,col,h2);
	      	         col.setVisible(false);
	      	       customer.setVisible(false);
	      	cars.setVisible(false);
	      	test.setVisible(false);

	      	         form.getChildren().addAll(tableview);
	      	    	Scene address_scene = new Scene(form);
	      	    	 primaryComboBox(cars,"car","name");
	                 primaryComboBox(customer,"customer","id");
	      	    	    stage.setScene(address_scene);
	      	    	    stage.setWidth(600); 
	      	    	    stage.setHeight(600); 
	      	    	    stage.show();	  	back.setOnAction(o->{
	  		stage.setScene(sc);
	  	  stage.setWidth(800);
	        stage.setHeight(600);
	  	    stage.show();
	  	});
	  	
	  	
	  	
	  	search.setOnAction(o -> {
	  		if(!form.getChildren().contains(v)) {
	      	       form.getChildren().add(2,v);}
		  	
		   	  	v.setVisible(true);
		   	  	col.setVisible(false);
		   	  	test.setVisible(false);
	 		 cars.setVisible(false);
 		 customer.setVisible(false);
 		 textField_Customer.setVisible(true);
 		 textField_Car.setVisible(true);
		   
			Do.setOnAction(d->{ tableview.getColumns().clear();
		    String sql = "SELECT * FROM  orders WHERE";
	  	 if (textField_Id.getText().isEmpty() && textField_Date.getText().isEmpty()  && textField_Customer.getText().isEmpty()   && textField_Car.getText().isEmpty()) {
		        buildData("SELECT * FROM  orders");
		    } else {
		        if (!textField_Id.getText().isEmpty()) {
		            sql += " id = '" + textField_Id.getText() + "'";
		        }
		        if (!textField_Date.getText().isEmpty()) {
		            if (sql.length() > 28)
		            	sql += " and date = '" + textField_Date.getText() + "'";
		             else      sql += " date = '" + textField_Date.getText() + "'";
		            
		        }
		        
		        
		        if (!textField_Customer.getText().isEmpty()) {
		            if (sql.length() > 28)
		            	sql += " and customer = '" + textField_Customer.getText() + "'";
		             else      sql += " customer = '" + textField_Customer.getText() + "'";
		            
		        }
		        
		        if (!textField_Car.getText().isEmpty()) {
		            if (sql.length() > 28)
		            	sql += " and car = '" + textField_Car.getText() + "'";
		             else      sql += " car = '" + textField_Car.getText() + "'";
		            
		        }
		        

		        buildData(sql);
		    }
			});
		});
	  	
	  	
		insert.setOnAction(o -> {
	 		String s = "INSERT INTO orders (id, date, customer, car) VALUES(?,?,?,?)";
	 		if(!form.getChildren().contains(v)) {
	      	       form.getChildren().add(2,v);}
		  	
		   	  	v.setVisible(true);
		   	  	col.setVisible(false);
		   	  	test.setVisible(false);
	 		 cars.setVisible(true);
    		 customer.setVisible(true);
    		 textField_Customer.setVisible(false);
    		 textField_Car.setVisible(false);
    		  Do.setOnAction(r->{
	 		try {tableview.getColumns().clear();
	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
	 		    PreparedStatement gb = con.prepareStatement(s);
try {

	 		 	
	 		    gb.setInt(1,Integer.parseInt(textField_Id.getText()));
	 		    gb.setInt(2,Integer.parseInt(textField_Date.getText()));
	 		    gb.setInt(3,  Integer.parseInt(customer.getValue()));
	 		    gb.setString(4,cars.getValue());
} catch (NumberFormatException j ) {
	  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id is not a number ");

	}
	 		    gb.executeUpdate();
	 		    } catch (SQLException e1) {
	 			    JOptionPane.showMessageDialog(null, "Record insert  unsuccessfully id dup ");
	 		   tableview.getColumns().clear();
		        buildData("SELECT * FROM orders");
	 		}
	 		   tableview.getColumns().clear();
		        buildData("SELECT * FROM orders");
	 		

    		   } );   } );
	  	
	  	
		update.setOnAction(up -> {
			if(!form.getChildren().contains(v)) {
	      	       form.getChildren().add(2,v);}
		  	
		   	  	v.setVisible(true);
		   	  	col.setVisible(false);
		   	  	test.setVisible(false);
	 		 cars.setVisible(true);
 		 customer.setVisible(true);
 		 textField_Customer.setVisible(false);
 		 textField_Car.setVisible(false);
    		 
    	    String s = "UPDATE orders SET date=?, customer=?, car=? WHERE id=?";
	   	 Do.setOnAction(d -> {
	   	 	    try { tableview.getColumns().clear();
		 	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
		 	       PreparedStatement gb = con.prepareStatement(s);
       	        try {
       	 		  gb.setInt(4, Integer.parseInt(textField_Id.getText()));
       	 		  gb.setInt(1, Integer.parseInt(textField_Date.getText()));
       	 		  gb.setInt(2, Integer.parseInt(customer.getValue()));
       	 		    gb.setString(3, cars.getValue());
       	     } catch (NumberFormatException j ) {
    	 		  JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id is not a number ");

	 			}
         	        int rowsUpdated = gb.executeUpdate();
         	        int updateCount = gb.getUpdateCount();

         	        if (updateCount >=1) {System.out.println("update successfully");     
                     JOptionPane.showMessageDialog(null, "Record updated successfully");
//
         	        }  
         	        else {
                               System.err.println("No such ID found for update");            	        
                     JOptionPane.showMessageDialog(null, "No such ID found for update");
                     tableview.getColumns().clear();
}
         	    
         	    }         	 	   
                    catch (SQLException e1) {
         	        e1.printStackTrace();
         	    }    buildData("SELECT * FROM orders ");
         	});
         	
         	
     }); 
		delete.setOnAction(x->{
			if(form.getChildren().contains(v)) {
	     	       form.getChildren().remove(v);}
	       	if(!form.getChildren().contains(test)) {
				form.getChildren().add(1,test);}
			  	
			   	  	col.setVisible(true);
			   	  	test.setVisible(true);
			
		Do.setOnAction(d->{
			col.setVisible(true);
	   	  	test.setVisible(true);
//			String temp=getIdFromName("address",col.getValue(),test.getText(),"id");
//			deleteRecord("customer","address",temp);

			deleteRecord("orders",col.getValue(),test.getText());
			
//			form.getChildren().add(2,v);
//			form.getChildren().add(2,v);


		        	  	
		});    });    	  		
  
  });  
  
  
  
  
  
  
  
  
  
        
        
        
    }
    
 
    private void primaryComboBox(ComboBox<String> comboBox, String tableName, String columnName) {
        comboBox.getItems().clear();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + columnName + " FROM " + tableName);
              ObservableList<String> madeList = FXCollections.observableArrayList();
         
            while (rs.next()) {
                madeList.add(rs.getString(columnName));
            }  comboBox.setItems(madeList);
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
    }
    
    public void deleteRecord(String table,String columnName, String columnValue) {

    	    String deleteQuery = "DELETE FROM " + table + " WHERE " + columnName + " = ? ";
    	    
    	    try { tableview.getColumns().clear();
	   
    	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
    	        PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);

    	        deleteStatement.setString(1, columnValue);

    	        int rowsAffected = deleteStatement.executeUpdate();

    	        tableview.getColumns().clear();
    	        buildData("SELECT * FROM " + table);
    	    } catch (SQLException e1) {
    	        e1.printStackTrace();
    	        tableview.getColumns().clear();
    	        buildData("SELECT * FROM " + table);
    	    }
    	

    }
    public void updateTable (String tableName,String sql ) {
        try {
            Statement statement = con.createStatement();
            int rowsUpdated = statement.executeUpdate(sql);
            
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Record updated successfully");
                buildData(tableName);
            } else {
                JOptionPane.showMessageDialog(null, "No such ID found for update");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating record: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error updating record");
        }
    }
    
    
    
    public String getIdFromName(String tableName, String columnName, String name,String key) {
        String id = ""; // قيمة افتراضية إذا لم يتم العثور على السجل

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");

            // استعلام SQL للحصول على الـ id باستخدام الـ name
            String selectQuery = "SELECT id FROM " + tableName + " WHERE " + columnName + "=?";
            PreparedStatement selectStatement = con.prepareStatement(selectQuery);
            selectStatement.setString(1, name);

            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(resultSet.getString(i));
                }
             id=row.get(0);

            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		System.out.print("fffffffffffffffffff"+id);

        return id;
    }
//    public String getIdFromName(String tableName, String columnName, String name,String key) {
//        data = FXCollections.observableArrayList(); 
//        String id="";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
//            Statement stmt = con.createStatement();
//            String selectQuery = "SELECT id FROM " + tableName + " WHERE " + columnName + "=?";
// 
//            ResultSet rs = stmt.executeQuery(selectQuery);
//
//            /**
//             * ************
//             * TABLE COLUMN ADDED DYNAMICALLY *
//             ***********
//             */
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                //We are using non property style for making dynamic table
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
//                        return new SimpleStringProperty(param.getValue().get(j).toString());
//                    }
//                });
//
//                tableview.getColumns().addAll(col);
//                System.out.println("Column [" + i + "] ");
//            }
//
//            /**
//             * **********
//             * Data added to ObservableList *
//             ***********
//             */	
//       
//            while (rs.next()) {
//                //Iterate Row
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                    //Iterate Column
//                
//                	 if (rs.getString(i).contains(name)) {
//                    row.add(rs.getString(i));
//                    id=rs.getString(i);
//                	 }
//                }
//                System.out.println("Row [1] added " + row);
//                data.add(row);
//
//            }
//            
//            //FINALLY ADDED TO TableView
//            tableview.setItems(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//		
//		return id;
//    }

}
