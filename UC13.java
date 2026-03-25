package UC13;




interface IMeasurable
{
    double getConversionFactor();

    default double convertToBaseUnit(double val)
    {
        return val * getConversionFactor();
    }

    default double convertFromBaseUnit(double val)
    {
        return val / getConversionFactor();
    }
}


enum LengthUnit implements IMeasurable
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
}


enum WeightUnit implements IMeasurable
{
    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor()
    {
        return conversionFactor;
    }
}


enum VolumeUnit implements IMeasurable
{
    MILLILITRE(0.001),
    LITRE(1.0),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor()
    {
        return conversionFactor;
    }
}


class Quantity<U extends IMeasurable>
{
    private double val;
    private U unit;

    public Quantity(double val, U unit) {
        this.val = val;
        this.unit = unit;
    }


    private enum ArithmeticOperation
    {
        ADD {
            double compute(double a, double b) {
                return a + b;
            }
        },
        SUBTRACT {
            double compute(double a, double b) {
                return a - b;
            }
        },
        DIVIDE {
            double compute(double a, double b) {
                if (Math.abs(b) < 1e-9) {
                    throw new ArithmeticException("Division by zero is not allowed");
                }
                return a / b;
            }
        };

        abstract double compute(double a, double b);
    }


    private void validateArithmeticOperands(
            Quantity<U> other,
            U target,
            boolean targetRequired)
    {
        if (other == null) {
            throw new IllegalArgumentException("Operand cannot be null");
        }

        if (this.unit == null || other.unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Incompatible unit types");
        }

        if (!Double.isFinite(this.val) || !Double.isFinite(other.val)) {
            throw new IllegalArgumentException("Values must be finite");
        }

        if (targetRequired && target == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }


    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation op)
    {
        double b1 = unit.convertToBaseUnit(val);
        double b2 = other.unit.convertToBaseUnit(other.val);

        return op.compute(b1, b2);
    }


    private double round(double v)
    {
        return Math.round(v * 100.0) / 100.0;
    }


    public Quantity<U> convertTo(U target)
    {
        double base = unit.convertToBaseUnit(val);
        double converted = target.convertFromBaseUnit(base);
        return new Quantity<>(converted, target);
    }


    public Quantity<U> add(Quantity<U> other)
    {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U target)
    {
        validateArithmeticOperands(other, target, true);

        double result = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double finalVal = target.convertFromBaseUnit(result);

        return new Quantity<>(round(finalVal), target);
    }


    public Quantity<U> subtract(Quantity<U> other)
    {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U target)
    {
        validateArithmeticOperands(other, target, true);

        double result = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double finalVal = target.convertFromBaseUnit(result);

        return new Quantity<>(round(finalVal), target);
    }

    public double divide(Quantity<U> other)
    {
        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }


    public boolean compare(Quantity<?> other)
    {
        double ep = 1e-9;

        double b1 = unit.convertToBaseUnit(val);
        double b2 = other.unit.convertToBaseUnit(other.val);

        return Math.abs(b1 - b2) < ep;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quantity<?> other = (Quantity<?>) o;
        return this.compare(other);
    }

    @Override
    public String toString()
    {
        return String.format("%.2f %s", val, unit);
    }
}

public class UC13
{
    public static void main(String[] args)
    {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println(q1.add(q2)); // 2.00 FEET

        Quantity<LengthUnit> q3 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q4 = new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println(q3.subtract(q4)); // 9.50 FEET

        Quantity<LengthUnit> q5 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q6 = new Quantity<>(2.0, LengthUnit.FEET);

        System.out.println(q5.divide(q6)); // 1.0
    }
}
