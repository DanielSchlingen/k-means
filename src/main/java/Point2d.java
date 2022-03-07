import java.util.Locale;

public class Point2d {
    public String name;
    public double x, y;
    Cluster belongsToCluster;

    public Point2d(String name, Cluster belongsToCluster, double x, double y) {
        this.belongsToCluster = belongsToCluster;
        this.name = name.toUpperCase(Locale.ROOT);
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public String returnNumberAsString(double number) {
        if (number % 1 == 0) {
            return String.valueOf((int) number);
        } else {
            return String.valueOf(round(number, 2));
        }
    }

    public double getDistanceManhattan(Point2d p) {
        return Math.abs(this.getX() - p.getX()) +
                Math.abs(this.getY() - p.getY());
    }

    public double getManhattanDistanceAsString(Point2d p) {
        double betrag = getDistanceManhattan(p);
        System.out.println("d(" + this.getName() + "," + p.getName() + ")= " +
                "|" + returnNumberAsString(this.getX()) + "-" + returnNumberAsString(p.getX()) + "|+|"
                + returnNumberAsString(this.getY()) + "-" +
                returnNumberAsString(p.getY()) + "| = " + returnNumberAsString(betrag));
        return betrag;
    }

    public double getDistanceEuklid(Point2d p) {
        return Math.sqrt(Math.pow(this.getX() - p.getX(), 2) +
                Math.pow(this.getY() - p.getY(), 2));
    }

    public double getEuklidianDistanceAsString(Point2d p) {
        double betrag = getDistanceEuklid(p);
        System.out.println("d(" + this.getName() + "," + p.getName() + ")= " +
                "(" + returnNumberAsString(this.getX()) + "-" + returnNumberAsString(p.getX()) + ")^2+("
                + returnNumberAsString(this.getY()) + "-" +
                returnNumberAsString(p.getY()) + ")^2 = " + returnNumberAsString(betrag));
        return betrag;
    }

    @Override
    public String toString() {
        return "(" + returnNumberAsString(x) + "," + returnNumberAsString(y) + ")";
    }
}
