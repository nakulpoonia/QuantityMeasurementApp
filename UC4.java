package UC4;

public class UC4 {
    public static void main(String[] args) {
        System.out.println(domonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 1.0, Length.LengthUnit.YARDS));
    }

    public static boolean demonstrateLengthEquality(Length l1, Length l2){
        return l1.equals(l2);
    }

    public static boolean domonstrateLengthComparison(double v1,Length.LengthUnit unit1,double v2,Length.LengthUnit unit2){
        Length l1 = new Length(v1, unit1);
        Length l2 = new Length(v2, unit2);
        return demonstrateLengthEquality(l1, l2);
    }

}



class Length {
    private double val;
    private LengthUnit unit;

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);


        public final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor=conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }
    }

    public Length(double val,LengthUnit unit){
        this.val = val;
        this.unit=unit;
    }

    private double convertToBaseUnit(){
        return val*unit.getConversionFactor();
    }



    @Override
    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null || getClass()!=o.getClass())return false;
        Length l = (Length)o;
        return Double.compare(this.convertToBaseUnit(), l.convertToBaseUnit())==0;
    }

}
