
public class ElectromagneticVoltage extends BaseFormula{

	public ElectromagneticVoltage(){
		formulaName = "Uniform Movement: v = r.i"; //Change here
		fields = new double[3];//Change here
		fields[0] = 0;//Add here
		fields[1] = 0;
		fieldsNames = new String[2];//Change here
		fieldsNames[0] = "r";//Add here
		fieldsNames[1] = "i";
	}

	public double solve(double [] inputData) throws InvalidFieldException{
		double solution = 0;
		setFields(inputData);
		solution = fields[0]*fields[1];//change here
		return solution;
	}
}