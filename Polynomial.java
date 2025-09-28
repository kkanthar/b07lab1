import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;



public class Polynomial {
	public double [] coefficients;
	public int [] exponents;
	
	
	public Polynomial() {
		coefficients = new double[1];
		exponents = new int[1];
	}
	

	public Polynomial(double[] coefficients, int[] exponents){
		
		if (coefficients.length != exponents.length) {
			System.out.println("Coefficient and Exponents are not of same length!");
			return;
		}

		this.coefficients = new double[coefficients.length];
		this.exponents = new int[exponents.length];

		for (int i = 0; i < coefficients.length; i++){

			this.coefficients[i] = coefficients[i];
			this.exponents[i] = exponents[i];
		} 
	}
	
	
	public Polynomial (File inputFile) {
		
		try {
			Scanner input = new Scanner(inputFile);
			String contents = input.nextLine();
			input.close();
			String remove = "(?=[+-])";
			String [] terms = contents.split(remove);
			//{5,+5x2,-3x2,+7x8}
			
			int count = terms.length;
			
			this.coefficients = new double[count];
			this.exponents = new int[count];
			
			int index = 0;
						

			
			for (String term : terms) {
				
			
				if (term.contains("x")) {
					//Left side coefficient, right side exponent
					String [] tempTerm = term.split("x");
					
					//If left side is just negative or positive sign
					if (tempTerm[0].equals("+")|| tempTerm[0].isEmpty()) {
						coefficients[index] = 1;
					} else if (tempTerm[0].equals("-")) { 
						coefficients[index] = -1;
					} else {
						coefficients[index] = Double.parseDouble(tempTerm[0]);
					}
					
					if (tempTerm.length == 1 || tempTerm[1].isEmpty()) {
						exponents[index] = 1;
					} else {
						exponents[index] = Integer.parseInt(tempTerm[1]);
					}
					
					
					
				} else {
					//Just an exponent = 0, coefficient = term
					coefficients[index] = Double.parseDouble(term);
					exponents[index] = 0;
					
					
				}
				
				index++; 
			}
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occured.");
		}
		
	}
	

	
	//This should now compare exponents, if they are same then add coefficients together. 
	public Polynomial add(Polynomial polynomial_input){


		
		//Starting off with all array lengths combined
		
		double [] tempCoefficients = new double[this.coefficients.length + this.exponents.length];
		int [] tempExponents = new int [this.exponents.length + this.coefficients.length];
		
		double tempArrayInputCurrent = 0;
		int tempExponentsInputCurrent = 0;
		
		int counter = 0;
		
		int found = 0;
		
		//copy arrays over
		for (int i = 0; i < this.coefficients.length; i++) {
			tempCoefficients[i] = this.coefficients[i];
			tempExponents[i] = this.exponents[i];
			counter++;
		}
		
		
		
		
		//Now compare between the two
		for (int i = 0; i < polynomial_input.coefficients.length; i++) {
			found = 0;

			tempArrayInputCurrent = polynomial_input.coefficients[i];
			tempExponentsInputCurrent = polynomial_input.exponents[i];
			
			for(int j = 0; j < this.coefficients.length; j++) {
				
				if (tempExponentsInputCurrent == tempExponents[j]) {
					tempCoefficients[j] += tempArrayInputCurrent;
					found = 1;
					break;
					
				}
			}
			
			//If not found add to end
			if (found != 1) {
				tempCoefficients[counter] = tempArrayInputCurrent;
				tempExponents[counter] = tempExponentsInputCurrent;
				counter++;
								
				
			}
			
			
		}
		
		//Clean and add
		int nonZeroCounter = 0;
		
		for(int i = 0; i < counter; i++) {
			if (tempCoefficients[i] != 0) {
				nonZeroCounter++;
			}
			
		}
		
		
		double [] copyCoefficients = new double[nonZeroCounter];
		int [] copyExponents = new int[nonZeroCounter];
		
		int insertIndex = 0;
		
		for(int i = 0; i < counter; i++) {
			if (tempCoefficients[i] != 0) {
				copyCoefficients[insertIndex] = tempCoefficients[i];
				copyExponents[insertIndex] = tempExponents[i];
				insertIndex++;
			}
		}
		
		return new Polynomial(copyCoefficients, copyExponents);
		
	}
	
	

	public double evaluate(double num){
		double result = 0;

		for(int i = 0; i < coefficients.length; i++){

			result += this.coefficients[i]*(Math.pow(num, this.exponents[i]));
		}

		return result;
	}
	
	public Polynomial multiply(Polynomial polynomial_input) {
		//Create result polynomial to store results
		Polynomial result_polynomial = new Polynomial();

	
		Polynomial temp_result_polynomial = new Polynomial();

		
		
				
		for(int i = 0; i < this.coefficients.length; i++) {
			for (int j = 0; j < polynomial_input.coefficients.length; j++) {
				//Use add function to add the result of multiplying coefficients
				//and exponents together to the polynomial result
				temp_result_polynomial.coefficients[0] = this.coefficients[i] * polynomial_input.coefficients[j];
				temp_result_polynomial.exponents[0] = this.exponents[i] + polynomial_input.exponents[j];
				result_polynomial = result_polynomial.add(temp_result_polynomial);
			}
		}
		
		
		return result_polynomial;
		
		
	}



	public boolean hasRoot(double num){

		double result;
		result = evaluate(num);

		return result == 0; 

	}
	
	public void saveToFile(String fileName){
		
		String addPolynomial = toFileFormat();
		
		try {
			FileWriter fWriter = new FileWriter(fileName);
			fWriter.write(addPolynomial);
			fWriter.close();
		} catch (IOException e) {
			System.out.println("An error occured.");
		}
		
		
		
		
		
	}
	
	public String toFileFormat() {
		String result = "";
		String sign = "";
		
		for (int i = 0; i < this.coefficients.length; i++) {
			
			if (this.coefficients[i] > 0 && i > 0) {
				sign = "+";
			} else {
				sign = "";
			}
			
			if (this.exponents[i] == 0) {
				result += sign + this.coefficients[i];
				
			} else if (this.exponents[i] == 1) {
				result += sign + this.coefficients[i] + "x";
			} else {
				result += sign + this.coefficients[i] + "x" + this.exponents[i];
			}
		}
		
		return result;
	}
	
	

}