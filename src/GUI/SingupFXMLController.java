/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static javax.management.remote.JMXConnectorFactory.connect;
import util.MyConnection;
import Services.userService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;
import org.mindrot.jbcrypt.BCrypt;


 
public class SingupFXMLController implements Initializable {
 private PreparedStatement prepare ;
 private ResultSet result ;
  private Statement statement;
  private Connection connect;
    @FXML
    private TextField inputFirstname;
    @FXML
    private TextField inputLastname;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputLocation;
    @FXML
    private ComboBox<String> COMBOXclass;
    @FXML
    private PasswordField inputPassword;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Button buttoncreate;
    @FXML
    private PasswordField inputcPassword;
    @FXML
    private AnchorPane signup;
 MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
     private double x=0;
    private double y=0;
    @FXML
    private Button buttonRETURN;

    /**
     * Initializes the controller class.
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> subjects = FXCollections.observableArrayList(
        "1ére année",
        "2éme année",
        "3éme année",
        "4éme année",
        "5éme année"
        );
        COMBOXclass.setItems(subjects);
    }    

    @FXML
 private void Create() throws SQLException {
    Alert alert;
  
    if (inputEmail.getText().isEmpty() || inputFirstname.getText().isEmpty() || inputLastname.getText().isEmpty()
            || inputPassword.getText().isEmpty() || inputcPassword.getText().isEmpty()
            || inputLocation.getText().isEmpty() ) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR Message ");
        alert.setHeaderText(null);
        alert.setContentText("All fields are necessary to be filled");
        alert.showAndWait();

    } else if (!inputPassword.getText().equals(inputcPassword.getText())) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR Message ");
        alert.setHeaderText(null);
        alert.setContentText("Password does not match");
        alert.showAndWait();
    } else if (inputPassword.getText().length() < 8) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR Message ");
        alert.setHeaderText(null);
        alert.setContentText("Invalid Password, at least 8 characters needed");
        alert.showAndWait();
    } else if (!isValidEmail(inputEmail.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR Message ");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email Format");
            alert.showAndWait();
        }else {
      String checkUsername = "SELECT * FROM user2 WHERE prenomuser = '"
             + inputFirstname.getText() + "'";
                prepare = cnx.prepareStatement(checkUsername);
                result = prepare.executeQuery();
                
        try {
            statement = cnx.createStatement();
            result = statement.executeQuery(checkUsername);

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR Message ");
                alert.setHeaderText(null);
                alert.setContentText("user name is already taken");
                alert.showAndWait();
            } else {

              String insertData = "INSERT INTO user2 "
        + "(nomuser, prenomuser, mailuser, mdpuser, adressuser, classeuser, roleuser) "
        + "VALUES(?,?,?,?,?,?,?)";

    prepare = cnx.prepareStatement(insertData);

    prepare.setString(1, inputFirstname.getText());
    prepare.setString(2, inputLastname.getText());
    prepare.setString(3, inputEmail.getText());
    prepare.setString(4, inputPassword.getText());
    prepare.setString(5, inputLocation.getText());
    prepare.setString(6, (String)COMBOXclass.getSelectionModel().getSelectedItem());
    prepare.setString(7, "some_role"); 
    prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMATION Message ");
                alert.setHeaderText(null);
                alert.setContentText("Registered Successfully!");
                alert.showAndWait();

                Clear();

                signup.setVisible(false);
                Parent root = FXMLLoader.load(getClass().getResource("AuthentificationInterface.fxml"));
                Stage myWindow = (Stage) inputEmail.getScene().getWindow();
                Scene sc = new Scene(root);
                myWindow.setScene(sc);
                myWindow.setTitle("page name");
                myWindow.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    @FXML
  private void retu(){ 
    try {      buttonRETURN.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("AuthentificationInterface.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
                 
                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                     });
                      root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
        });
                
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        
        
        stage.show();
    } catch(Exception e){
        e.printStackTrace();
    }
    



}

    public void Clear() {
                inputEmail.setText("");
             inputFirstname.setText("");
                 inputLastname.setText("");
             inputLocation.setText("");
                 COMBOXclass.getSelectionModel().clearSelection();
           inputPassword.setText("");
          inputcPassword.setText("");
}

    private void Create(MouseEvent event) throws SQLException {
         Create();
    }

    private void Create(ActionEvent event) throws SQLException {
         Create();
    }

    private boolean isValidEmail(String email) {
       String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        Matcher mat = pat.matcher(email);
        return mat.matches();
    }

    @FXML
    private void initialize(ActionEvent event) {
    }


   
  
}
    

