package com.objectivus.Physics;

import com.livescribe.penlet.Penlet;
import com.livescribe.event.MenuEventListener;
import com.livescribe.event.MenuEvent;

public class PenletPhysics extends BasicPenlet{

    int doubleTap = 0;
    int menuLevel = 0;
    int currentFormula = 0;
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
    	this.logger.info("hwrResult got: " + result);
        this.display.setCurrent(this.label);
    	if(result != null){
           // Display HWR Engine output in real time
            if(doubleTap==0){
            	this.name =  result;
            	this.label.draw("Value: "+this.name);
            } else {
            }
    	}

    }
    
    public void activateApp(int reason, Object[] args) {
        super.activateApp(reason, args);
        this.display.setCurrent(this.label);
        this.label.draw(bf[currentFormula].getFormulaName(),true);
        
    }
    
    public String getOnlyNumbers(String raw){
    	String ret = "";
    	int i=0;
    	while(raw!=null && i<raw.length()){
    		if((int)raw.charAt(i)>=(int)'0' && (int)raw.charAt(i)<=(int)'9'){
    			ret=ret+raw.charAt(i);
    		}
    		i++;
    	}
    	return ret;
    }
    

	public void doubleTap(long time, int x, int y) {
		this.doubleTap++;
		if(doubleTap==1){
			this.label.draw("Phone:");
		} else if(doubleTap==2){
			this.label.draw("Contact: "+this.name+ " " +this.phone, true);
		}  else if(doubleTap>=3){
			storeDataAndReset();
			try{
				this.doubleTap = 0;
				resetApplication();
			} catch(Exception e){
	            String msg = "Error reseting app: " + e.getMessage();
	            this.logger.error(msg);
	            this.label.draw(msg, true);
	            this.display.setCurrent(this.label);				
			}
		}
	}
	
	private void storeDataAndReset(){
		addToFile(this.name, this.phone);
		this.name = "";
		this.phone = "";		
	}
	
	private void addToFile(String name, String phone){
		
	}
}
