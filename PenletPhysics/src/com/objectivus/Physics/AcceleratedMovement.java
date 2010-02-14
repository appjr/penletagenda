package com.objectivus.Physics;

public class AcceleratedMovement  extends BaseFormula{

	public AcceleratedMovement(){
		formulaName = "Accelerated Movement: s = s0 + v0.t + a.t.t/2";
		fields = new double[4];
		fields[0] = 0;
		fields[1] = 0;
		fields[2] = 0;
		fields[3] = 0;
		fieldsNames = new String[4];
		fieldsNames[0] = "s0";
		fieldsNames[1] = "v0";
		fieldsNames[2] = "t";
		fieldsNames[3] = "a";
	}

	public double solve() throws InvalidFieldException{
		double solution = 0;
		solution = fields[0]+(fields[1]*fields[2])+(0.5*fields[3]*fields[2]*fields[2]);//change here
		return solution;
	}
}
