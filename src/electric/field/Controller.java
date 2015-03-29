package electric.field;

import javafx.geometry.Point2D;


/**
 * The main controller for the project.
 * 
 * @author Tyler
 */
public class Controller {
    Charges model;
    View view;
    
    public Controller(Charges model, View view){
        this.model = model;
        this.view = view;
    }    
    
   
    public void drawCharges() {
        view.drawCharges(model.getLocations());
    }

    public void drawForce() {
        view.drawForce(model.getForce(), model.getDistanceFormatted());
    }
    
    /**
     * Updates the charges to the model then the view.
     * This is called by the main from an action listener
     * @param chargeLocation the new location of the charge
     */
    public void updateCharge(Point2D chargeLocation) {
        model.updateCharge(chargeLocation);
        drawCharges();
        drawForce();
    }
    
    public boolean clickedOn(double x, double y) {
        return model.clickedOn(x, y);
    }
}
