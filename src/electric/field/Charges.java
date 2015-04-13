package electric.field;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 * The main model for the all the charges.
 * 
 * @author Tyler
 */
public class Charges {
    final static double k = 8.9875517873681764 * Math.pow(10, 9); // Constant k, Coulomb's constant, in electrostatics formulas
    Charge c1, c2; // The 2 charges
    
    public Charges() {
        c1 = new Charge(-2.5 * Math.pow(10, -5), 300, 300);
        c2 = new Charge(2.5 * Math.pow(10, -5), 400, 400);
    }
        
        
    /**
     * Calculates the electrostatic force between the charges
     * uses formula electrostatic force = k * charge1 * charge2 / distance^2 
     *  or fe = k*q1*q2/r^2
     * 
     * @return the electrostatic force between the charges formatted to 3 decimal places
     */
    public String getForce () {
        double distance = getDistance();
        double force = Math.abs(k * c1.getCharge() * c2.getCharge() / Math.pow(distance, 2));
        
        DecimalFormat decimalFormat = new DecimalFormat(".###");
        String formatted = decimalFormat.format(force) + "N ";
        if (c1.isPositive() != c2.isPositive())
            formatted += "towards";
        else 
            formatted += "away";
        return formatted;
    }
    
    public ArrayList<Charge> getCharges() {
        ArrayList<Charge> charges = new ArrayList<Charge>();
        charges.add(c1);
        charges.add(c2);
        return charges;
    }
    
    /**
     * @return The distance between the points in meters
     */
    private double getDistance() {
        return c1.getLocation().distance(c2.getLocation()) / ElectricField.SCALE; 
    }
    
    /**
     * @return The distance between the charges formatted
     */
    public String getDistanceFormatted() {
        DecimalFormat decimalFormat = new DecimalFormat(".###");
        return decimalFormat.format(getDistance());
    }
    
    public boolean isPositive(int index) {
        if (index == 0) 
            return c1.isPositive();
        else
            return c2.isPositive();        
    }
    
    /**
     * Updates the charge to the location it is being dragged to
     * 
     * @param index index of the charge
     * @param newLocation new location of the charge
     */
    public void setChargeLocation(int index, Point2D newLocation) {
        if (index == 0) 
            c1.setLocation(newLocation);
        else
            c2.setLocation(newLocation);
    }
    
    public void setCharge(int index, double c) {
        if (index == 0) 
            c1.setCharge(c);
        else
            c2.setCharge(c);
    }
    

    
    /**
     * @return the index of the charge clicked on or -1 if no charge was clicked on
     */
    public int clickedOn (double x, double y) {
        if (c1.clickedOn(x, y)) 
            return 0;
        if (c2.clickedOn(x, y))
            return 1;
        return -1;
    }
}
