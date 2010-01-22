package com.objectivus.Physics;

public class MechanicsPower extends BaseFormula{

	public MechanicsPower(){
		formulaName = "Mechanical Power: W = F . (d-d0)"; //Change here
		fields = new double[3];//Change here
		fields[0] = 0;//Add here
		fields[1] = 0;
		fields[2] = 0;
		fieldsNames = new String[3];//Change here
		fieldsNames[0] = "F";//Add here
		fieldsNames[1] = "d";
		fieldsNames[2] = "d0";
	}


	public double solve() throws InvalidFieldException{
		double solution = 0;
		solution = fields[0]*(fields[2]-fields[1]);//change here
		return solution;
	}
}