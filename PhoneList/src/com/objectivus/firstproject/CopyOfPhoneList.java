package com.objectivus.firstproject;

import com.livescribe.penlet.Penlet;
import com.livescribe.display.Display;
import com.livescribe.ui.ScrollLabel;
import com.livescribe.ui.MediaPlayer;
import com.livescribe.event.StrokeListener;
import com.livescribe.penlet.Region;
import com.livescribe.event.HWRListener;
import com.livescribe.icr.ICRContext;
import com.livescribe.icr.Resource;
import com.livescribe.afp.PageInstance;
import com.livescribe.event.PenTipListener;

/**
 * This Penlet displays "Hello World!" as text when activated by menu.
 */
public class CopyOfPhoneList extends Penlet implements StrokeListener, HWRListener, PenTipListener {
    
    private Display display;
    private ScrollLabel label;
    private MediaPlayer player;
    private ICRContext icrContext;
    int i = 0;
    public CopyOfPhoneList() {   
    }

    /**
     * Invoked when the application is initialized.  This happens once for an application instance.
     */
    public void initApp() {
        this.logger.info("Penlet PhoneList initialized.");
        this.display = this.context.getDisplay();
        this.label = new ScrollLabel();
        this.player = MediaPlayer.newInstance(this);
    }
    
    /**
     * Invoked each time the penlet is activated.  Only one penlet is active at any given time.
     */
    public void activateApp(int reason, Object[] args) {
        this.logger.info("Penlet PhoneList activated.");
        if (reason == Penlet.ACTIVATED_BY_MENU) {
            this.label.draw("Hello world!", true);
            this.display.setCurrent(this.label);
            this.player.play("/audio/helloworld.wav");
        }
        this.context.addStrokeListener(this);

        // Prompt the user for text entry
        this.label.draw("Enter text", true);
        this.display.setCurrent(this.label);
        
        // Configure the ICR context
        try {
            this.icrContext = this.context.getICRContext(1000, this);
            Resource[] resources = {
                this.icrContext.getDefaultAlphabetKnowledgeResource(),
                this.icrContext.createAppResource("/icr/LEX_PhoneList.res"),
                this.icrContext.createSKSystemResource(ICRContext.SYSRES_SK_ALNUM)                                                                      
            };
            this.icrContext.addResourceSet(resources);            
        } catch (Exception e) {
            String msg = "Error initializing handwriting recognition resources: " + e.getMessage();
            this.logger.error(msg);
            this.label.draw(msg, true);
            this.display.setCurrent(this.label);
        }
		context.addStrokeListener(this);
		context.addPenTipListener(this);
    }
    
    /**
     * Invoked when the application is deactivated.
     */
    public void deactivateApp(int reason) {
        this.logger.info("Penlet PhoneList deactivated.");
        this.context.removeStrokeListener(this);
        icrContext.dispose();
        icrContext = null;
		context.removeStrokeListener(this);
		context.removePenTipListener(this);            
    }
    
    /**
     * Invoked when the application is destroyed.  This happens once for an application instance.  
     * No other methods will be invoked on the instance after destroyApp is called.
     */
    public void destroyApp() {
        this.logger.info("Penlet PhoneList destroyed.");
    }

                 
    /**
     * Called when a new stroke is created on the pen. 
     * The stroke information is added to the ICRContext
     */
    public void strokeCreated(long time, Region regionId, PageInstance page) {
        this.icrContext.addStroke(page, time);
    }
    
    /**
     * When the user pauses (pause time specified by the wizard),
     * all strokes in the ICRContext are cleared
     */
    public void hwrUserPause(long time, String result) {
        this.icrContext.clearStrokes();
        this.label.draw("Paused "+result);
    }
    
    /**
     * When the ICR engine detects an acceptable series or strokes,
     * it prints the detected characters onto the Pulse display.
     */
    public void hwrResult(long time, String result) {
    	this.logger.info("hwrResult got: " + result);
    	if(result == null || result.trim().length()<=0){
    		this.label.draw("Zero size");
    	} else {
            

            // Display HWR Engine output in real time
            this.label.draw(result);
            if (this.display.getCurrent() != this.label) {
                this.display.setCurrent(this.label);
            }
    	}
        i++;
    }
    
    /**
     * Called when an error occurs during handwriting recognition 
     */
    public void hwrError(long time, String error) {
    	this.label.draw("Not Recognized: " + error);
    	
    }
    
    /**
     * Called when the user crosses out text
     */
    public void hwrCrossingOut(long time, String result) {}
    
    /**
     * Specifies that the penlet should respond to events
     * related to open paper
     */
    public boolean canProcessOpenPaperEvents () {
        return true;
    }

	public void penUp(long time, Region region, PageInstance page) {
	
	}

	public void penDown(long time, Region region, PageInstance page) {
	
	}

	public void singleTap(long time, int x, int y) {
	
	}

	public void doubleTap(long time, int x, int y) {
	
	}                 
}
