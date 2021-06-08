package Question2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Miguel Emmara - 18022146
 */
public class LuxuryCruiseCentre {
    private final Map<String, Set<CruiseShip>> portMap;

    public LuxuryCruiseCentre() {
        portMap = new ConcurrentHashMap<>();
    }

    // Add a unique CruiseShip to this map
    // If The departure port is not
    // Exists in the portMap
    public boolean add(CruiseShip ship) {
        if (getPortMap().containsKey(ship.getDepartPort())) {
            return getPortMap().get(ship.getDepartPort()).add(ship);
        } else {
            HashSet<CruiseShip> hashSet = new HashSet<>();
            hashSet.add(ship);
            getPortMap().put(ship.getDepartPort(), hashSet);
            return true;
        }
    }

    // Return list of all the uniquely possible routes from the
    // Start port and date to the end port. It does this by calling the recursive method findPaths which uses
    // A depth first search technique
    public List<CruiseJourney> getPossibleJourneys(String startPort, Calendar startDate, String endPort) {
        List<CruiseJourney> journeyList = new ArrayList<>();
        CruiseJourney currentJourney = new CruiseJourney();

        // Recursive method findPaths
        findPaths(startPort, startDate, endPort, currentJourney, journeyList);
        System.out.println(journeyList.size());
        return journeyList;
    }

    private void findPaths(String departPort, Calendar departDate, String endPort, CruiseJourney currentJourney, List<CruiseJourney> journeyList) {
        if (departPort.equals(endPort)) {
            CruiseJourney cloneJourney = currentJourney.cloneJourney();
            journeyList.add(cloneJourney);
            return;
        }

        HashSet<CruiseShip> cruiseShipHashSet = (HashSet<CruiseShip>) getPortMap().get(departPort);
        CruiseShip cruiseShip;

        for (Object objects : cruiseShipHashSet) {
            cruiseShip = (CruiseShip) objects;

            if (cruiseShip.getDepartDate().compareTo(departDate) >= 0) {
                if (currentJourney.addCruise(cruiseShip)) {
                    findPaths(cruiseShip.getArrivalPort(), cruiseShip.getArrivalDate(), endPort, currentJourney, journeyList);
                    currentJourney.removeLastTrip();
                }
            }
        }
    }

    public Map<String, Set<CruiseShip>> getPortMap() {
        return portMap;
    }
}
