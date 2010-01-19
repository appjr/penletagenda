
public class Force extends BaseFormula{

	public Force(){
		formulaName = "Force: F = m.a"; //Change here
		fields = new double[2];//Change here
		fields[0] = 0;//Add here
		fields[1] = 0;
		fieldsNames = new String[2];//Change here
		fieldsNames[0] = "mass";//Add here
		fieldsNames[1] = "acceleration";
	}

	public double solve(double [] inputData) throws InvalidFieldException{
		double solution = 0;
		setFields(inputData);
		solution = fields[0]*fields[1];//change here
		return solution;
	}
}
