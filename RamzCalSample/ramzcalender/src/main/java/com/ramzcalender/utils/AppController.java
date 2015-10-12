package com.ramzcalender.utils;

import android.app.Application;


import org.joda.time.LocalDateTime;


public class AppController extends Application {

	public static final String TAG = AppController.class
			.getSimpleName();



    private static AppController mInstance;

    public LocalDateTime setDate,selectedDate;


    @Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;

	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

    /**
     * Set the current week date
     *
     * @param setDate
     */
    public void setDate(LocalDateTime setDate)
    {
       this.setDate=setDate;
    }

    /*getting the current week*/
    public LocalDateTime getDate()
    {
        return setDate;
    }

    /*getting the selected week*/

    public LocalDateTime getSelected()
    {
        return selectedDate;
    }

    /**
     * Setting selected week
     *
     * @param selectedDate
     */
    public void setSelected(LocalDateTime selectedDate)
    {
        this.selectedDate=selectedDate;
    }


}
