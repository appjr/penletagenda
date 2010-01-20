package com.objectivus.Physics;

public class EnergyKinetic extends BaseFormula{

	public EnergyKinetic(){
		formulaName = "Kinetic Energy: Ek = m.v.v/2"; //Change here
		fields = new double[2];//Change here
		fields[0] = 0;//Add here
		fields[1] = 0;
		fieldsNames = new String[2];//Change here
		fieldsNames[0] = "m";//Add here
		fieldsNames[1] = "v";
	}


	public double solve(double [] inputData) throws InvalidFieldException{
		double solution = 0;
		setFields(inputData);
		solution = fields[0]*fields[1]*fields[1]/2;//change here
		return solution;
	}
}