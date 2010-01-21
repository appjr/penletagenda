package com.objectivus.Physics;

public abstract class BaseFormula {

		String formulaName = "";
		public double fields[] = {0};
		public String fieldsNames[] = {""};
		
		public String getFormulaName() { 
			return formulaName;
		}

		
		public void setField(double field, int idx) throws InvalidFieldException{
			this.fields[idx] = field;
		}
		abstract public double solve() throws InvalidFieldException;
}
