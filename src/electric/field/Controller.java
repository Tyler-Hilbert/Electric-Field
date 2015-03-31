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
        view.drawCharges(model.getCharges());
    }

    public void drawForce() {
        view.drawForce(model.getForce(), model.getDistanceFormatted());
    }
    
    /**
     * Updates the charges to the model then the view.
     * This is called by the main from an action listener
     * @param index the index of the charge being dragged
     * @param chargeLocation the new location of the charge
     */
    public void updateCharge(int index, Point2D chargeLocation) {
        model.updateCharge(index, chargeLocation);
        drawCharges();
        drawForce();
    }
    
    /**
     * @param x x clicked on
     * @param y y clicked on
     * @return the index of the charge clicked on or -1 if no charge was clicked on
     */
    public int clickedOn(double x, double y) {
        return model.clickedOn(x, y);
    }
}
