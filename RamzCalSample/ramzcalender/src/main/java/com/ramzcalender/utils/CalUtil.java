package com.ramzcalender.utils;

import android.content.Context;
import android.util.Log;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.LocalDateTime;

import java.util.Calendar;


/**
 * The MIT License (MIT)

 Copyright (c) 2015 Ramesh M Nair

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
public class CalUtil {

    LocalDateTime mStartDate,selectedDate;


    /**
     * Get the day difference in the selected day and the first day in the week
     *
     * @param dayName
     */
    public static int mDateGap(String dayName) {

        if (dayName.equals("mon")) {
            return 1;
        } else if (dayName.equals("tue")) {
            return 2;
        } else if (dayName.equals("wen")) {
            return 3;
        } else if (dayName.equals("thu")) {
            return 4;
        } else if (dayName.equals("fri")) {
            return 5;
        } else if (dayName.equals("sat")) {
            return 6;
        }
        else
        {
            return 0;
        }


    }


    /**
     * Initial calculation of the week
     *
     * @param mContext
     */
    public void calculate(Context mContext)
    {

        //Initializing JodaTime
        JodaTimeAndroid.init(mContext);

        //Initializing Start with current month
        final LocalDateTime currentDateTime = new LocalDateTime();

        setStartDate(currentDateTime.getYear(), currentDateTime.getMonthOfYear(), currentDateTime.getDayOfMonth());

        int weekGap = CalUtil.mDateGap(currentDateTime.dayOfWeek().getAsShortText().substring(0, 3).toLowerCase());


        if (weekGap != 0) {
            //if the current date is not the first day of the week the rest of days is added

            //Calendar set to the current date
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DAY_OF_YEAR, -weekGap);

            //now the date is weekGap days back
            Log.i("weekGap", "" + calendar.getTime().toString());

            LocalDateTime ldt = LocalDateTime.fromCalendarFields(calendar);

            setStartDate(ldt.getYear(), ldt.getMonthOfYear(), ldt.getDayOfMonth());



        }
    }


/*Set The Start day (week)from calender*/
    private void setStartDate(int year, int month, int day) {

        mStartDate = new LocalDateTime(year, month, day, 0, 0, 0);
        selectedDate = mStartDate;

        AppController.getInstance().setDate(selectedDate);

    }



    public LocalDateTime getSelectedDate()
    {
        return selectedDate;
    }


    public LocalDateTime getStartDate()
    {
        return mStartDate;
    }
}
