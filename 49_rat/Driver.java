public class Driver {
  public static void main(String[] args) {
    Rational a = new Rational(3,5);
    Rational b = new Rational(6,10);
    Integer c = new Integer(5); 
    
    // This should run
    System.out.println(a.compareTo(b)); 
    
    // This should return error
    System.out.println(b.compareTo(c)); 
    
  }
  
  
  
  
}