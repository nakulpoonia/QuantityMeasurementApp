package UC10;

interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double v);
    double convertFromBaseUnit(double v);
    String getUnitName();
}



enum LengthUnit implements IMeasurable{
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}



class Quantity<U extends IMeasurable> {
    private final double v;
    private final U unit;

    public Quantity(double v, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.v = v;
        this.unit = unit;
    }

    public double getValue() {
        return v;
    }
    public U getUnit() {
        return unit;
    }

    public double convertTo(U targetUnit) {
        if (!unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Incompatible units");
        }

        double baseValue = this.unit.convertToBaseUnit(this.v);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return Math.round(converted * 100.0) / 100.0;
    }

    public Quantity<U> add(Quantity<U> v) {
        return add(v, this.unit);
    }

    public Quantity<U> add(Quantity<U> v2, U targetUnit) {
        if (!this.unit.getClass().equals(v2.unit.getClass())) {
            throw new IllegalArgumentException("Cannot add different unit types");
        }

        double base1 = this.unit.convertToBaseUnit(this.v);
        double base2 = v2.unit.convertToBaseUnit(v2.v);

        double sumBase = base1 + base2;

        double ans = targetUnit.convertFromBaseUnit(sumBase);
        ans = Math.round(ans * 100.0) / 100.0;

        return new Quantity<>(ans, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?>)) return false;

        Quantity<?> that = (Quantity<?>) obj;

        double base1 = this.unit.convertToBaseUnit(this.v);
        double base2 = that.unit.convertToBaseUnit(that.v);

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(this.v);
        long rounded = Math.round(baseValue * 100);
        return Long.hashCode(rounded);
    }

    @Override
    public String toString(){
        return String.format("%.2f %s", v, unit);
    }

}



public class UC10{
    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        double convertedValue = quantity.convertTo(targetUnit);
        return new Quantity<>(convertedValue, targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.add(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        return quantity1.add(quantity2, targetUnit);
    }

    public static void main(String[] args) {

        Quantity<WeightUnit> w1 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        System.out.println("Equality = " + demonstrateEquality(w1, w2));

        System.out.println("Conversion = " + demonstrateConversion(w1, WeightUnit.KILOGRAM));

        System.out.println("Addition = " + demonstrateAddition(w2, w1));

        System.out.println("Addition = " + demonstrateAddition(w1, w2, WeightUnit.POUND));
    }
}


enum WeightUnit implements IMeasurable {
    MILLIGRAM(0.000001),
    GRAM(0.001),
    KILOGRAM(1.0),
    POUND(0.453592),
    TONNE(1000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}
