package UC5;

class Length
{
    private double val;
    private LengthUnit unit;


    enum LengthUnit
    {
        FEET(12.0),
        INCHES(1.0),
        YARD(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor)
        {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor()
        {
            return conversionFactor;
        }

    }

    public Length(double val,LengthUnit unit) {
        if(!Double.isFinite(val)){
            throw new IllegalArgumentException("Value should be finite");
        }
        if(unit == null)
        {
            throw new IllegalArgumentException("Unit can't be null");
        }
        this.val = val;
        this.unit = unit;
    }

    public double convertTobaseUnit()
    {
        double ans = val * unit.getConversionFactor();

        return ans;
    }

    private boolean compare(Length thatLength)
    {
        return Double.compare(this.convertTobaseUnit(), thatLength.convertTobaseUnit()) == 0;
    }

    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        Length a = (Length)o;
        return this.compare(a);
    }

    public double convert(double val,Length.LengthUnit from,Length.LengthUnit to)
    {
        if(!Double.isFinite(val))
        {
            throw new IllegalArgumentException("Value should be finite");
        }
        if(from == null || to == null)
        {
            throw new IllegalArgumentException("Unit can't be null");
        }

        double base = val * from.getConversionFactor();
        double ans = base / to.getConversionFactor();

        return ans;

    }

    public Length convertTo(LengthUnit targetUnit)
    {
        if(targetUnit == null)
        {
            throw new IllegalArgumentException("Target is null");
        }

        double ans = convert(this.val, this.unit, targetUnit);
        
        return new Length(ans, targetUnit);
    }
    public String toString()
    {
        String ans = String.format("%.2f",val);
        return ans + " " + unit;
    }

}

public class UC5 {

    public static Length demonstrateLengthConversion(double val,Length.LengthUnit from,Length.LengthUnit to)
    {
        Length l1 = new Length(val, from);
        return l1.convertTo(to);
    }

    public static Length demonstrateLengthConversion(Length l1,Length.LengthUnit to)
    {
        return l1.convertTo(to);
    }

    public static boolean demonstrateLengthEquality(Length l1, Length l2)
    {
        return l1.equals(l2);
    }
    public static boolean demonstrateLengthComparison(double v1, Length.LengthUnit u1, double v2,Length.LengthUnit u2)
    {
        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);

        return l1.equals(l2);
    }
    public static void main(String[] args) {

        System.out.println(demonstrateLengthConversion(3.0, Length.LengthUnit.FEET,Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(2.0, Length.LengthUnit.YARD,Length.LengthUnit.INCHES));


        Length l = new Length(1.0, Length.LengthUnit.FEET);
        System.out.println(demonstrateLengthConversion(l, Length.LengthUnit.INCHES));

        System.out.println(demonstrateLengthComparison(12.0, Length.LengthUnit.INCHES, 1.0, Length.LengthUnit.FEET));

    }
}