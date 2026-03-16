 public class UC2{
    static class Feet{
        private final double feet;
        Feet(double feet){
            this.feet=feet;
        }
        public boolean equals(Object obj){
            if(obj==null)return false;

            if(getClass()!=obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.feet, other.feet) == 0;
        }

    }
    static class Inches{
        private final double inch;
        Inches(double inch){
            this.inch=inch;
        }
        public boolean equals(Object obj){
            if(obj==null)return false;

            if(getClass()!=obj.getClass()) return false;
            Inches other = (Inches) obj;
            return Double.compare(this.inch, other.inch) == 0;
        }

    }
     public static void demonstrateFeetEquality(){
         Feet f1=new Feet(5.4);
         Feet f2=new Feet(5.4);
         System.out.print(f1.equals(f2));
     }
     public static void demonstrateInchEquality(){
         Inches f1=new Inches(5.4);
         Inches f2=new Inches(5.4);
         System.out.println(f1.equals(f2));

     }
    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchEquality();


    }
}
