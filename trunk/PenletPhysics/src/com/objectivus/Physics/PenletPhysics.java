package com.objectivus.Physics;

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
    String formulaAudio [] ={"/audio/uniformmovement.wav",
    						 "/audio/acceleratedmovement.wav",    		
    						 "/audio/acceleratedmovementvelocity.wav",    		
    						 "/audio/electromagneticpower.wav",    		
    						 "/audio/electromagneticw.wav",    		
    						 "/audio/kineticenergy.wav",    		
    						 "/audio/potentialenergy.wav",    		
    						 "/audio/force.wav",    		
    						 "/audio/momentum.wav",    		
    						 "/audio/mechanicalpower.wav"    		
    						};
    		
    
    int maxFormulaIdx = bf.length;
    
    
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
        setCurrent();       
    }
    
    public double getOnlyNumbers(String raw){
    	String ret = "0";
    	int i=0;
    	boolean decimalPoint = false;
    	while(raw!=null && i<raw.length()){
    		if(((int)raw.charAt(i)>=(int)'0' && (int)raw.charAt(i)<=(int)'9') || (raw.charAt(i)=='.' && decimalPoint==false)){
    			ret=ret+raw.charAt(i);
    			if(raw.charAt(i)=='.'){
    				decimalPoint=true;
    			}
    		}
    		i++;
    	}
    	if(raw.length()>0 && raw.charAt(0)=='-'){
    		ret = "-"+ret;
    	}
    	return Double.parseDouble(ret);
    }
    

	public void doubleTap(long time, int x, int y) {
		curFieldIndex++;
		hwrResult(0, "");
	}
	
	private void setInitialMsg(){
		setCurrent();
	}
	
	public boolean handleMenuEvent(MenuEvent event) {
		if(event.eventId == MenuEvent.MENU_RIGHT){
			curFieldIndex = 0;
			//setInitialMsg();
			hwrResult(0, "");

		} else if(event.eventId == MenuEvent.MENU_LEFT){
	        //this.display.setCurrent(this.label);
	        //this.label.draw(bf[currentFormula].getFormulaName(),true);
		} else if(event.eventId == MenuEvent.MENU_DOWN){
			if(currentFormula + 1 < maxFormulaIdx){
				currentFormula++;
			}
			setCurrent();
		} else if(event.eventId == MenuEvent.MENU_UP){
			if(currentFormula - 1 >= 0){
				currentFormula--;
			}
			setCurrent();
		}
		return false;
	}
	
	private void setCurrent(){
        this.display.setCurrent(this.label);
        this.label.draw(bf[currentFormula].getFormulaName(),true);
        this.player.play(formulaAudio[currentFormula]);
	}
}
