package com.objectivus.firstproject;

import com.livescribe.penlet.Penlet;
import com.livescribe.display.Display;
import com.livescribe.ui.ScrollLabel;
//import com.livescribe.ui.MediaPlayer;
import com.livescribe.event.PenTipListener;
import com.livescribe.event.StrokeListener;
import com.livescribe.penlet.Region;
import com.livescribe.event.HWRListener;
import com.livescribe.icr.ICRContext;
import com.livescribe.icr.Resource;
import com.livescribe.afp.PageInstance;

/**
 * This Penlet displays "Hello World!" as text when activated by menu.
 */
public class PhoneList extends BasicPenlet{
    
    int doubleTap = 0;
    public PhoneList() {   
    }

    
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
            	this.label.draw("Name: "+this.name);
            } else {
            	this.phone = this.phone + getOnlyNumbers(result);
            	this.label.draw("Phone: "+this.phone);
            }
    	}

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
