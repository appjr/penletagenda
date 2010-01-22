package com.objectivus.Physics;

public class ElectromagneticPower extends BaseFormula{

	public ElectromagneticPower(){
		formulaName = "Electromagnetic Power: v.c"; //Change here
		fields = new double[2];//Change here
		fields[0] = 0;//Add here
		fields[1] = 0;
		fieldsNames = new String[2];//Change here
		fieldsNames[0] = "v";//Add here
		fieldsNames[1] = "c";
	}


	public double solve() throws InvalidFieldException{
		double solution = 0;
		solution = fields[0]*+fields[1];//change here
		return solution;
	}
}