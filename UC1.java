

public class UC1{
    static class Feet{
        private final double feet;
        Feet(double feet){
            this.feet=feet;
        }
        public boolean equals(Object obj){
            if(obj==null)return false;
            //if(!(obj instanceof Feet))return false;
            if(getClass()!=obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.feet, other.feet) == 0;
        }
    }
    public static void main(String[] args) {
        Feet f1=new Feet(5.4);
        Feet f2=new Feet(5.4);
        System.out.print(f1.equals(f2));

    }
}


