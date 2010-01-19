
public abstract class BaseFormula {

		String formulaName = "";
		public double fields[] = {0};
		public String fieldsNames[] = {""};
		
		public String getFormulaName() { 
			return formulaName;
		}

		
		public void setFields(double fields[]) throws InvalidFieldException{
			int i=0;
			if(fields!=null){
				while(i<fields.length){
					this.fields[i] = fields[i];
				}
			}
		}
		abstract public double solve(double [] inputData) throws InvalidFieldException;
}
