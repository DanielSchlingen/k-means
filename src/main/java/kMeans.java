import java.util.ArrayList;

public class kMeans {
    ArrayList<Cluster> clusters = new ArrayList<>();
    String calculationRule = "m";

    public void init() {

        /*
        // Beispiel 1
        clusters.add(new Cluster("k1"));
        clusters.add(new Cluster("k2"));
        //clusters.add(new Cluster("k3"));
        clusters.get(0).addPoint(new Point2d("a", clusters.get(0), 3, 1));
        clusters.get(0).addPoint(new Point2d("e", clusters.get(0), 7, 5));
        clusters.get(0).addPoint(new Point2d("f", clusters.get(0), 8, 6));
        clusters.get(1).addPoint(new Point2d("b", clusters.get(1), 3, 3));
        clusters.get(1).addPoint(new Point2d("c", clusters.get(1), 5, 2));
        clusters.get(1).addPoint(new Point2d("d", clusters.get(1), 7, 1));
        */


        // Beispiel 2
        clusters.add(new Cluster("k1"));
        clusters.add(new Cluster("k2"));
        //clusters.add(new Cluster("k3"));
        clusters.get(0).addPoint(new Point2d("a", clusters.get(0), 0, 3));
        clusters.get(0).addPoint(new Point2d("b", clusters.get(0), 7, 5));

        clusters.get(1).addPoint(new Point2d("c", clusters.get(1), 2, 1));
        clusters.get(1).addPoint(new Point2d("d", clusters.get(1), 3, 4));
        clusters.get(1).addPoint(new Point2d("e", clusters.get(1), 8, 4));

        /*
        // Beispiel 3
        clusters.add(new Cluster("k1"));
        clusters.add(new Cluster("k2"));
        clusters.get(0).addPoint(new Point2d("a", clusters.get(0), 5, 6));
        clusters.get(0).addPoint(new Point2d("b", clusters.get(0), 3, 0));
        clusters.get(1).addPoint(new Point2d("c", clusters.get(1), 1, 2));
        clusters.get(1).addPoint(new Point2d("d", clusters.get(1), 3, 5));
        clusters.get(1).addPoint(new Point2d("e", clusters.get(1), 5, 4));
        clusters.get(1).addPoint(new Point2d("f", clusters.get(1), 3, 1));
         */

    }

    public kMeans() {
        init();
        int i = 1;
        while (i < 10) {
            System.out.println("---------------------------------" + i + "-Iteration---------------------------------");
            System.out.print("Klassen: ");  // print classes
            for (Cluster cluster : clusters) {
                System.out.print(cluster.toString());
            }
            System.out.println();
            for (Cluster cluster : clusters) {  // print centroids
                System.out.print(cluster.printCentroidCalculation());
            }
            if (calculateDistances()) break;    // stop iteration
            i++;
        }
        System.out.println("Gestoppt nach " + i + ". Iteration");
    }

    private boolean calculateDistances() {
        boolean finished = true;
        System.out.println();
        ArrayList<Point2d> allCentroidsList = new ArrayList<>();
        ArrayList<Point2d> allPointsList = new ArrayList<>();
        for (Cluster c : clusters) {    // Filling allCentroidsList
            allCentroidsList.add(c.getCentroid());
        }
        for (Cluster c : clusters) {    // Filling allPointsList
            allPointsList.addAll(c.getClusterPoints());
        }

        for (Point2d p : allPointsList) {
            Cluster currentSmallestCentroidCluster = allCentroidsList.get(0).belongsToCluster;
            for (Point2d c : allCentroidsList) {
                if (isManhattan(calculationRule)) {
                    if (p.getManhattanDistanceAsString(c) < p.getDistanceManhattan(currentSmallestCentroidCluster.getCentroid())) {
                        currentSmallestCentroidCluster = c.belongsToCluster;
                    }
                } else {
                    if (p.getEuklidianDistanceAsString(c) < p.getDistanceEuklid(currentSmallestCentroidCluster.getCentroid())) {
                        currentSmallestCentroidCluster = c.belongsToCluster;
                    }
                }
            }

            if (currentSmallestCentroidCluster.getClusterPoints().contains(p)) {
                System.out.println("Punkt " + p.getName() + p + " verbleibt im Cluster " +
                        currentSmallestCentroidCluster.nameCluster);
            } else {
                p.belongsToCluster.clusterPoints.remove(p);
                System.out.println("==> Punkt " + p.getName() + p + " wird verschoben in Cluster "
                        + currentSmallestCentroidCluster.nameCluster);
                currentSmallestCentroidCluster.addPoint(p);
                p.belongsToCluster = currentSmallestCentroidCluster;
                finished = false;

            }
            System.out.println();
        }
        return finished;
    }

    private boolean isManhattan(String calculationRule) {
        return calculationRule.equals("m") | calculationRule.equals("Manhattan") | calculationRule.equals("manhattan");
    }

    public static void main(String[] args) {
        kMeans kMeans = new kMeans();
    }
}
