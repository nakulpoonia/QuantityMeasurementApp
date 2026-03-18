package UC6;

public class UC6 {
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        if (length == null || toUnit == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2){
        if(l1 == null || l2 == null){
            throw new IllegalArgumentException("Length cannot be null");
        }

        return l1.add(l2);
    }

    public static void main(String[] args) {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        System.out.println(demonstrateLengthAddition(l1, l2));

        Length l3 = new Length(1, Length.LengthUnit.YARDS);
        Length l4 = new Length(18, Length.LengthUnit.INCHES);

        System.out.println(demonstrateLengthAddition(l3, l4));
    }
}

class Length{
    private double v;
    private LengthUnit unit;

    public enum LengthUnit{
        INCHES(1),
        FEET(12),
        YARDS(36),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor=conversionFactor;
        }

        public double getConversionFactor(){
            return this.conversionFactor;
        }
    }

    public Length(double v, LengthUnit unit){
        this.v=v;
        this.unit=unit;
    }

    public double convertToBaseUnit(){
        return v * unit.getConversionFactor();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass()) return false;

        Length l = (Length) obj;
        return Double.compare(this.convertToBaseUnit() , l.convertToBaseUnit()) == 0;
    }

    public Length convertTo(LengthUnit targetUnit){
        if(targetUnit == null){
            throw new IllegalArgumentException("Target Unit cannot be null");
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = convertFromBaseToTargetUnit(baseValue, targetUnit);

        return new Length(convertedValue, targetUnit);
    }

    public Length add(Length thatLength){
        if(thatLength == null){
            throw new IllegalArgumentException("Length cannot be null");
        }
        double i1 = this.convertToBaseUnit();
        double i2 = thatLength.convertToBaseUnit();

        double sum = i1 + i2;

        double ans = sum / this.unit.getConversionFactor();
        return new Length(ans, this.unit);
    }

    public double convertFromBaseToTargetUnit(double baseValue, LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target Unit cannot be null");
        }
        return baseValue / targetUnit.getConversionFactor();
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", v, unit);
    }
}
