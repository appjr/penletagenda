package com.objectivus.Physics;

public class UniformMovement extends BaseFormula{

	public UniformMovement(){
		formulaName = "Uniform Movement: s = s0 + v.t"; //Change here
		fields = new double[3];//Change here
		fields[0] = 0;//Add here
		fields[1] = 0;
		fields[2] = 0;
		fieldsNames = new String[3];//Change here
		fieldsNames[0] = "s0";//Add here
		fieldsNames[1] = "v";
		fieldsNames[2] = "t";
	}


	public double solve(double [] inputData) throws InvalidFieldException{
		double solution = 0;
		setFields(inputData);
		solution = fields[0]+fields[1]*fields[2];//change here
		return solution;
	}
}
