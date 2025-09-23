public class Polynomial {
	public double [] coefficients;
	public int [] exponents;
	
	
	public Polynomial() {
		coefficients = new double[1];
		exponenents = new int[1];
	}
	

	public Polynomial(double[] coefficients, int[] exponents){
		
		if (coefficients.length != exponents.length) {
			return null; 
		}

		coefficients = new double[coefficients.length];

		for (int i = 0; i < coefficients.length; i++){

			this.coefficients[i] = coefficients[i];
			this.exponents[i] = exponents[i];
		} 
	}
	

	
	//This should now compare exponenents, if they are same then add coefficients together. 
	public Polynomial add(Polynomial polynomial_input){

		Polynomial addedPolynomial = new Polynomial(this.coefficients, this.exponents);
		
		double length_input = polynomial_input.coefficients.length;
		double length_this = this.coefficients.length;
		
		for (double exponent:this.exponents) {
			for (int j = 0; j < length_input; j++) {
				if (exponent == polynomial_input.exponents[j]) {
					addedPolynomial.coefficients += polynomial_input.coefficients[j]; 
					
				}
			}
		}
		
			

		
		return addedPolynomial;
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
		Polynomial result_polynomial;
		result_polynomial.coefficients = new double[this.coefficients.length];
		result_polynomial.exponents = new int[this.coefficients.length];
		
		Polynomial temp_result_polynomial;
		temp_result_polynomial.coefficients = new double[1];
		temp_result_polynomial.exponents = new int[1];
		
		
		int product = 0;
		
		for(int i = 0; i < this.coefficients.length; i++) {
			for (int j = 0; j < polynomial_input.coefficients.length; j++) {
				//Use add function to add the result of multiplying coefficients
				//and exponents together to the polynomail result
				temp_result_polynomial.coefficients[0] = this.coefficients[i] * polynomial_input.coefficients[j];
				temp_result_polynomial.exponents[0] = this.exponents[i] + polynomial_input
				result_polynomial = result_polynomial.add(temp_result_polynomial);
			}
		}
		
		
	}



	public boolean hasRoot(double num){

		double result;
		result = evaluate(num);

		return result == 0; 

	}

}