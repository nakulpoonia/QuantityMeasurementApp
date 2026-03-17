enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double getToFeetFactor() {
        return toFeetFactor;
    }
}

class Length {

    private final double value;
    private final LengthUnit unit;

    Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private double toFeet() {
        return value * unit.getToFeetFactor();
    }

    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof Length)) return false;

        Length other = (Length) obj;

        return Double.compare(this.toFeet(), other.toFeet()) == 0;
    }


}
public class UC3 {
    public static void main(String[] args) {

        Length l1 = new Length(1, LengthUnit.FEET);
        Length l2 = new Length(12, LengthUnit.INCH);

        System.out.println(l1.equals(l2));
    }
}