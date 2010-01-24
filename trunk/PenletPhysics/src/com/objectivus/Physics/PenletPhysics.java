package com.objectivus.Physics;

import com.livescribe.penlet.Penlet;
import com.livescribe.event.MenuEventListener;
import com.livescribe.event.MenuEvent;

public class PenletPhysics extends BasicPenlet{

    int doubleTap = 0;
    int menuLevel = 0;
    int currentFormula = 0;
    int curFieldIndex = 0;
    BaseFormula bf [] = {new UniformMovement(), 
    					 new AcceleratedMovement(), 
    					 new AcceleratedMovementII(),
    					 new ElectromagneticPower(),
    					 new ElectromagneticVoltage(),
    					 new EnergyKinetic(),
    					 new EnergyPotential(),
    					 new Force(),
    					 new MechanicsMomentum(),
    					 new MechanicsPower()
    					};
    int maxFormulaIdx = bf.length-1;
    
    
    /**
     * When the ICR engine detects an acceptable series or strokes,
     * it prints the detected characters onto the Pulse display.
     */
    public void hwrResult(long time, String result) {
    	String toShow = "";
    	this.logger.info("hwrResult got: " + result);
    	if(result.trim().length()==0){
    		result = "0";
    	}
    	if(curFieldIndex<bf[currentFormula].fields.length){
	    	String fieldName = bf[currentFormula].fieldsNames[curFieldIndex];
	    	bf[currentFormula].fields[curFieldIndex] = getOnlyNumbers(result);
	    	toShow = fieldName+": "+bf[currentFormula].fields[curFieldIndex];
    	} else {
    		try{
    			toShow = "Result: "+bf[currentFormula].solve();
    		} catch(Exception e){
    			this.logger.info(e.getMessage());
    		}
    	}
    	this.display.setCurrent(this.label);
        this.label.draw(toShow,true);

    }
    
    public void activateApp(int reason, Object[] args) {
        super.activateApp(reason, args);
        this.display.setCurrent(this.label);
        this.label.draw(bf[currentFormula].getFormulaName(),true);
        
    }
    
    public double getOnlyNumbers(String raw){
    	String ret = "0";
    	int i=0;
    	while(raw!=null && i<raw.length()){
    		if((int)raw.charAt(i)>=(int)'0' && (int)raw.charAt(i)<=(int)'9'){
    			ret=ret+raw.charAt(i);
    		}
    		i++;
    	}
    	if(raw.charAt(i)=='-'){
    		raw = "-"+raw;
    	}
    	return Double.parseDouble(raw);
    }
    

	public void doubleTap(long time, int x, int y) {
		curFieldIndex++;
		hwrResult(0, "");
	}
	
	private void setInitialMsg(){
        this.display.setCurrent(this.label);
        this.label.draw(bf[currentFormula].fieldsNames[0],true);
		
	}
	
	public boolean handleMenuEvent(MenuEvent event) {
		if(event.eventId == MenuEvent.MENU_RIGHT){
			curFieldIndex = 0;
			setInitialMsg();
		} else if(event.eventId == MenuEvent.MENU_LEFT){
	        //this.display.setCurrent(this.label);
	        //this.label.draw(bf[currentFormula].getFormulaName(),true);
		} else if(event.eventId == MenuEvent.MENU_DOWN){
			if(currentFormula + 1 < maxFormulaIdx){
				currentFormula++;
			}
	        this.display.setCurrent(this.label);
	        this.label.draw(bf[currentFormula].getFormulaName(),true);
		} else if(event.eventId == MenuEvent.MENU_UP){
			if(currentFormula - 1 >= 0){
				currentFormula--;
			}
	        this.display.setCurrent(this.label);
	        this.label.draw(bf[currentFormula].getFormulaName(),true);
		}
		return false;
	}
}
