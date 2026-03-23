package UC8;

enum LengthUnit
{
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor()
    {
        return conversionFactor;
    }

    public double convertToBaseUnit(double val)
    {
        return val * conversionFactor;
    }

    public double convertFromBaseUnit(double val)
    {
        return val / conversionFactor;
    }

}

class Length
{
    private double val;
    private LengthUnit unit;

    public Length(double val,LengthUnit unit) {
        if (!Double.isFinite(val)) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.val = val;
        this.unit = unit;
    }

    private boolean compare(Length that)
    {
        double ep = 1e-9;
        return Math.abs(this.val - that.val) < ep;
    }

    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }

        Length i = (Length) o;
        return this.compare(i);
    }

    public Length convertTo(LengthUnit target)
    {
        double base = unit.convertToBaseUnit(val);
        double ans = target.convertFromBaseUnit(base);
        return new Length(ans, target);
    }

    public Length add(Length that)
    {
        double val1 = unit.convertToBaseUnit(val);
        double val2 = that.unit.convertToBaseUnit(that.val);

        double sum = val1 + val2;
        double ans = unit.convertFromBaseUnit(sum);

        return new Length(ans, unit);
    }

    public Length addAndConvert(Length length,LengthUnit target)
    {
        if(length == null)
        {
            throw new IllegalArgumentException("Can't be null");
        }
        if(target == null)
        {
            throw new IllegalArgumentException("Unit can't be null");

        }
        double val1 = unit.convertToBaseUnit(val);
        double val2 = length.unit.convertToBaseUnit(length.val);

        double sum = val1 + val2;
        double ans = target.convertFromBaseUnit(sum);

        return new Length(ans, target);
    }


    public String toString()
    {
        String ans = String.format("%.2f", val);
        return ans + " " + unit;
    }

}

public class UC8 {
    public static void main(String[] args) {
        Length q1 = new Length(1.0, LengthUnit.FEET);
        Length q2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println(q1.convertTo(LengthUnit.INCHES));
        System.out.println(q1.equals(q2));
        System.out.println(q1.add(q2));
    }


}
