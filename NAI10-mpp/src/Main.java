import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<City> cities = generateRandomCities(50);

        LocalSearchTSPSolver solver = new LocalSearchTSPSolver(cities);

        TSPSolution bestSwap = solver.localSearch(1000, false);
        System.out.println("Best using Swap:");
        System.out.println("Tour: " + bestSwap.tour.stream().map(c -> String.valueOf(c.id)).toList());
        System.out.println("Distance: " + bestSwap.totalDistance);
        TSPVisualizer.showFrame(cities, bestSwap.tour);

        TSPSolution best2Opt = solver.localSearch(1000, true);
        System.out.println("Best using 2-Opt:");
        System.out.println("Tour: " + best2Opt.tour.stream().map(c -> String.valueOf(c.id)).toList());
        System.out.println("Distance: " + best2Opt.totalDistance);
        TSPVisualizer.showFrame(cities, best2Opt.tour);
    }

    public static List<City> generateRandomCities(int count) {
        Random rand = new Random();
        List<City> cityList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double x = rand.nextDouble() * 100;
            double y = rand.nextDouble() * 100;
            cityList.add(new City((char) ('A' + i), x, y));
        }
        return cityList;
    }
}