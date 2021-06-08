package Question2;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Miguel Emmara - 18022146
 */
public class CruiseJourney {
    private final List<CruiseShip> shipList;

    public CruiseJourney() {
        shipList = new LinkedList<>();
    }

    public CruiseJourney(List<CruiseShip> list) {
        this();
        for (CruiseShip ship : list) {
            this.addCruise(ship);
        }
    }

    public boolean addCruise(CruiseShip ship) {
        if (getEndPort() == null) {
            getShipList().add(ship);
            return true;
        } else if (getEndPort().equals(ship.getDepartPort()) && getEndDate().compareTo(ship.getDepartDate()) < 1 && !containsPort(ship.getArrivalPort())) {
            getShipList().add(ship);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeLastTrip() {
        if (shipIsEmpty())
            return false;

        // Remove Last Trip on the index
        getShipList().remove(getShipList().size() - 1);
        return true;
    }

    public boolean containsPort(String port) {
        for (int i = 0; i < getShipList().size(); i++) {
            if (getShipList().get(i).getDepartPort().equalsIgnoreCase(port)) {
                return true;
            }
        }

        return false;
    }

    public String getStartPort() {
        if (shipIsEmpty())
            return null;
        else
            // Return depart port at first index
            return getShipList().get(0).getDepartPort();
    }

    public String getEndPort() {
        if (shipIsEmpty())
            return null;
        else
            // Return arrival port at last index
            return getShipList().get(getShipList().size() - 1).getArrivalPort();
    }

    public Calendar getStartDate() {
        if (shipIsEmpty())
            return null;
        else
            // Return depart date at first index
            return getShipList().get(0).getDepartDate();
    }

    public Calendar getEndDate() {
        if (shipIsEmpty())
            return null;
        else
            // Return arrival date date at last index
            return getShipList().get(getShipList().size() - 1).getArrivalDate();
    }

    public CruiseJourney cloneJourney() {
        return new CruiseJourney(getShipList());
    }

    public int getNumberOfTrips() {
        return getShipList().size();
    }

    public double getTotalCost() {
        double totalCost = 0D;
        for (int i = 0; i < getShipList().size(); i++) {
            totalCost += getShipList().get(i).getCost();
        }
        return totalCost;
    }

    private boolean shipIsEmpty() {
        if (getShipList().isEmpty()) {
            //System.out.println("List is empty");
            return true;
        }
        return false;
    }

    public List<CruiseShip> getShipList() {
        return shipList;
    }

    @Override
    public String toString() {
        int counter = 1;
        StringBuilder str = new StringBuilder("Journey " + counter + " Total Cost "+ getTotalCost() + "!!!\n");

        for (int i = 0; i < getShipList().size(); i++) {
            String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
                    "August", "September", "October", "November", "December" };

            String departMonth = monthName[getShipList().get(i).getDepartDate().get(Calendar.MONTH)];
            String arrivalMonth = monthName[getShipList().get(i).getArrivalDate().get(Calendar.MONTH)];

            str
                    .append("- ")
                    .append(getShipList().get(i).getBoatName())
                    .append(": LEAVING ")
                    .append(getShipList().get(i).getDepartPort())
                    .append(" (").append(getShipList().get(i).getDepartDate().get(Calendar.DAY_OF_MONTH)).append("-").append(departMonth)
                    .append(") and ARRIVING ")
                    .append(getShipList().get(i).getArrivalPort())
                    .append(" (").append(getShipList().get(i).getArrivalDate().get(Calendar.DAY_OF_MONTH)).append("-").append(arrivalMonth)
                    .append(")\n");
            counter++;
        }
        return str.toString();
    }
}
