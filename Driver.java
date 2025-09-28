import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
class Driver {
	 public static void main(String[] args) {
        //2x + 3x^2 
        double[] coeffs1 = {2, 3};
        int[] exps1 = {1, 2};
        Polynomial p1 = new Polynomial(coeffs1, exps1);

        //1 + 4x 
        double[] coeffs2 = {1, 4};
        int[] exps2 = {0, 1};
        Polynomial p2 = new Polynomial(coeffs2, exps2);

        // p1 + p2 = 1 + 6x + 3x^2
        Polynomial sum = p1.add(p2);
        System.out.println("p1 + p2 = coeffs:" + Arrays.toString(sum.coefficients));
        System.out.println("p1 + p2 = exponents:" + Arrays.toString(sum.exponents));

        //  p1 * p2 = 2x + 11x^2 + 12x^3
        Polynomial product = p1.multiply(p2);
        System.out.println("p1 * p2 = coeffs: " + Arrays.toString(product.coefficients));
        System.out.println("p1 * p2 = exponents: " + Arrays.toString(product.exponents));

        // Test evaluate
        double value = 2.0;
        System.out.println("p1 eval x=" + value + ": " + p1.evaluate(value));
        System.out.println("p2 eval x=" + value + ": " + p2.evaluate(value));
        System.out.println("Sum eval x=" + value + ": " + sum.evaluate(value));
        System.out.println("Product eval x=" + value + ": " + product.evaluate(value));

        // Root test (should be false)
        System.out.println("p1 has root at x=1? " + p1.hasRoot(1.0));
        
        try {
	        //File test
	        String fileName = "polynomial_output.txt";
	        
	        sum.saveToFile(fileName);
	        
	        File file_read = new File(fileName);
	        Polynomial fromFile = new Polynomial(file_read);
	        
	        //1 + 6x + 3x^2
	        System.out.println("\nFile: \ncoeffs: " + Arrays.toString(fromFile.coefficients));
	        System.out.println("exponents: " + Arrays.toString(fromFile.exponents));
        } catch (Exception e) {
        	System.out.println("An error occured");
        }
	       
        

        
    }

	

}