import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cluster {
    String nameCluster;
    Point2d centroid;
    List<Point2d> clusterPoints = new ArrayList<>() {
        public boolean add(Point2d point2d) {
            super.add(point2d);
            clusterPoints.sort(Comparator.comparing(Point2d::getName));
            return true;
        }
    };

    public Cluster(String nameCluster) {
        this.nameCluster = nameCluster;
    }

    public void addPoint(Point2d point) {
        clusterPoints.add(point);
        calculateCentroid();
    }

    public List<Point2d> getClusterPoints() {
        return clusterPoints;
    }

    public Point2d getCentroid() {
        return centroid;
    }

    public String printCentroidCalculation() {
        calculateCentroid();
        StringBuilder sb = new StringBuilder();
        if (clusterPoints.isEmpty()) {  // ERROR Case
            sb.append(nameCluster).append("= NO POINTS AVAILABLE");
            return sb.toString();
        }
        double sumX = 0, sumY = 0;
        sb.append("x").append(nameCluster.charAt(1)).append("=( (");
        if (clusterPoints.get(0).getX() % 1 == 0) {     // Print Centroid for X
            sb.append((int) clusterPoints.get(0).getX());
        } else {
            sb.append(clusterPoints.get(0).getX());
        }
        for (int i = 1; i < clusterPoints.size(); i++) {
            if (clusterPoints.get(i).getX() % 1 == 0) {
                sb.append("+").append((int) clusterPoints.get(i).getX());
            } else {
                sb.append("+").append(clusterPoints.get(i).getX());
            }
        }
        sb.append(")/").append(clusterPoints.size()).append("), (");
        if (clusterPoints.get(0).getY() % 1 == 0) {     // Print Centroid for Y
            sb.append((int) clusterPoints.get(0).getY());
        } else {
            sb.append(clusterPoints.get(0).getY());
        }
        for (int i = 1; i < clusterPoints.size(); i++) {
            if (clusterPoints.get(i).getY() % 1 == 0) {
                sb.append("+").append((int) clusterPoints.get(i).getY());
            } else {
                sb.append("+").append(clusterPoints.get(i).getY());
            }
        }
        sb.append(")/").append(clusterPoints.size()).append(") ) = ").append(centroid).append("\n");
        return sb.toString();
    }

    private void calculateCentroid() {
        if (clusterPoints.isEmpty()) return;
        double sumX = 0, sumY = 0;
        for (Point2d p : clusterPoints) {
            sumY += p.getY();
            sumX += p.getX();
        }
        this.centroid = new Point2d("x" + nameCluster.charAt(1), this,
                sumX / clusterPoints.size(), sumY / clusterPoints.size());
    }

    @Override
    public String toString() {
        if (clusterPoints.size() == 0) return "Empty Set";
        StringBuilder stringBuilder = new StringBuilder(nameCluster + "={");
        for (int i = 0; i < clusterPoints.size() - 1; i++) {
            stringBuilder.append(clusterPoints.get(i).toString() + ",");
        }
        stringBuilder.append(clusterPoints.get(clusterPoints.size() - 1).toString() + "} ");
        return stringBuilder.toString();
    }
}