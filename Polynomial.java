public class Polynomial {
	public double [] polynomial;
	
	public Polynomial() {
		polynomial = new double[1];
	}
	

	public Polynomial(double[] input_Array){

		polynomial = new double[input_Array.length];

		for (int i = 0; i < input_Array.length; i++){

			this.polynomial[i] = input_Array[i];
		} 
	}
	

	public Polynomial add(Polynomial polynomial_input){

		Polynomial addedPolynomial;
		double length;

		if (this.polynomial.length >= polynomial_input.polynomial.length){

			addedPolynomial = new Polynomial(this.polynomial);
			length = polynomial_input.polynomial.length;

			for (int i = 0; i  < length; i++)
			addedPolynomial.polynomial[i] += polynomial_input.polynomial[i];

		} else {

			addedPolynomial = new Polynomial(polynomial_input.polynomial);
			length = this.polynomial.length;

			for (int i = 0; i  < length; i++)
			addedPolynomial.polynomial[i] += this.polynomial[i];

		}
		
		return addedPolynomial;
	}
	

	public double evaluate(double num){
		double result = 0;

		for(int i = 0; i < polynomial.length; i++){

			result += this.polynomial[i]*(Math.pow(num, i));
		}

		return result;
	}


	public boolean hasRoot(double num){

		double result;
		result = evaluate(num);

		return result == 0; 

	}

}