import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class LocalSearchTSPSolver {
    List<City> cities;

    public LocalSearchTSPSolver(List<City> cities) {
        this.cities = cities;
    }

    public TSPSolution generateInitialSolution() {
        List<City> shuffled = new ArrayList<>(cities);
        Collections.shuffle(shuffled);
        return new TSPSolution(shuffled);
    }

    public List<TSPSolution> getNeighborsSwap(TSPSolution current) {
        List<TSPSolution> neighbors = new ArrayList<>();
        for (int i = 0; i < current.tour.size(); i++) {
            for (int j = i + 1; j < current.tour.size(); j++) {
                TSPSolution neighbor = current.clone();
                Collections.swap(neighbor.tour, i, j);
                neighbor.evaluate();
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public List<TSPSolution> getNeighbors2Opt(TSPSolution current) {
        List<TSPSolution> neighbors = new ArrayList<>();
        for (int i = 1; i < current.tour.size() - 2; i++) {
            for (int j = i + 1; j < current.tour.size() - 1; j++) {
                TSPSolution neighbor = current.clone();
                Collections.reverse(neighbor.tour.subList(i, j + 1));
                neighbor.evaluate();
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public TSPSolution localSearch(int maxIterations, boolean use2Opt) {
        TSPSolution current = generateInitialSolution();
        for (int i = 0; i < maxIterations; i++) {
            List<TSPSolution> neighbors = use2Opt ? getNeighbors2Opt(current) : getNeighborsSwap(current);
            TSPSolution bestNeighbor = neighbors.stream()
                    .min(Comparator.comparingDouble(sol -> sol.totalDistance))
                    .orElse(current);
            if (bestNeighbor.totalDistance < current.totalDistance) {
                current = bestNeighbor;
            } else {
                break;
            }
        }
        return current;
    }
}