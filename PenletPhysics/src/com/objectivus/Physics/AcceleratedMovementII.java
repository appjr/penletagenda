package com.objectivus.Physics;

public class AcceleratedMovementII extends BaseFormula{

	public AcceleratedMovementII(){
		formulaName = "Velocity - Accelerated Movement: v = + v0.t + a.t";
		fields = new double[]{0,0,0};
		fieldsNames = new String[]{"v0","a","t"};
	}

	public double solve(double [] inputData) throws InvalidFieldException{
		double solution = 0;
		setFields(inputData);
		solution = fields[0]+(fields[1]*fields[2])+(0.5*fields[3]*fields[2]*fields[2]);
		return solution;
	}
}
