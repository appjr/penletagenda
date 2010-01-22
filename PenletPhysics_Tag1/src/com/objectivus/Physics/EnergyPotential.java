package com.objectivus.Physics;

public class EnergyPotential extends BaseFormula{

	public EnergyPotential(){
		formulaName = "Potential Energy: Ep = m.a.h"; //Change here
		fields = new double[3];//Change here
		fields[0] = 0;//Add here
		fields[1] = 0;
		fields[2] = 0;
		fieldsNames = new String[3];//Change here
		fieldsNames[0] = "m";//Add here
		fieldsNames[1] = "a";
		fieldsNames[2] = "h";
	}


	public double solve() throws InvalidFieldException{
		double solution = 0;
		solution = fields[0]*fields[1]*fields[2];//change here
		return solution;
	}
}