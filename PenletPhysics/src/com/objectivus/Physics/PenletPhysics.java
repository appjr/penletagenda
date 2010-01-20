package com.objectivus.Physics;

import com.livescribe.penlet.Penlet;
import com.livescribe.event.MenuEventListener;
import com.livescribe.event.MenuEvent;

public class PenletPhysics extends Penlet implements MenuEventListener {

    public PenletPhysics() {   
    }

    /**
     * Invoked when the application is initialized.  This happens once for an application instance.
     */
    public void initApp() {
    }
    
    /**
     * Invoked each time the penlet is activated.  Only one penlet is active at any given time.
     */
    public void activateApp(int reason, Object[] args) {
    }
    
    /**
     * Invoked when the application is deactivated.
     */
    public void deactivateApp(int reason) {
    }
    
    /**
     * Invoked when the application is destroyed.  This happens once for an application instance.  
     * No other methods will be invoked on the instance after destroyApp is called.
     */
    public void destroyApp() {
    }

	public boolean handleMenuEvent(MenuEvent menuEvent) {
		return true;
	}
}
