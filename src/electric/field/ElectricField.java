package electric.field;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Main class of the program
 */
public class ElectricField extends Application {
    final static int SCALE = 100; // The scale of which the px is drawn to m
   
    @Override
    public void start(Stage primaryStage) {
        Charges charges = new Charges();
        View view = new View(primaryStage, charges);
        view.update();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
