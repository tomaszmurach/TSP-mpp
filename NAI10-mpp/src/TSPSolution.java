import java.util.ArrayList;
import java.util.List;

public class TSPSolution implements Cloneable {
    List<City> tour;
    double totalDistance;

    public TSPSolution(List<City> tour) {
        this.tour = new ArrayList<>(tour);
        evaluate();
    }

    public void evaluate() {
        totalDistance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            totalDistance += tour.get(i).distanceTo(tour.get(i + 1));
        }
        totalDistance += tour.get(tour.size() - 1).distanceTo(tour.get(0));
    }

    public TSPSolution clone() {
        return new TSPSolution(new ArrayList<>(this.tour));
    }
}
