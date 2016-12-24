package com.ramzcalender.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.datetimepicker.date.DatePickerDialog;
import com.ramzcalender.RWeekCalendar;
import com.ramzcalender.listener.CalenderListener;
import com.ramzcalender.utils.CalUtil;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadWritableDateTime;
import org.joda.time.Weeks;

import java.util.Calendar;


/**
 * Created by rameshvoltella on 11/10/15.
 */

//## Licence of Date picker using in this sample

/**
 * Copyright 2014 Paul Stöhr
 * Copyright 2013 The Android Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Sample extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    RWeekCalendar rCalendarFragment;
    TextView mDateSelectedTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);

        mDateSelectedTv = (TextView) findViewById(R.id.txt_date);



        rCalendarFragment = new RWeekCalendar();

        /*Define you startDate and end Date*/
        rCalendarFragment.startDate(1989, 9, 1);//Start date
        rCalendarFragment.endDate(2018, 12, 31);//Ending date

        Bundle args = new Bundle();

       /*Should add this attribute if you adding  the NOW_BACKGROUND or DATE_SELECTOR_BACKGROUND Attribute*/
        args.putString(RWeekCalendar.PACKAGENAME, getApplicationContext().getPackageName());

       /* IMPORTANT: Customization for the calender commenting or un commenting any of the attribute below will reflect change in calender*/

//---------------------------------------------------------------------------------------------------------------------//



        //set Calender type you want if you don't set any normal calender will be set
        if (getIntent().getExtras().getInt(RWeekCalendar.CALENDER_TYPE) == RWeekCalendar.FDF_CALENDER) {

            /** Set Calender type to FIRSTDAYFIRST (FDF_CALENDER)here
             * the week days will start as current day as first entry
             * eg if current day is friday calender start with fri,sat,etc
             * */
            args.putInt(RWeekCalendar.CALENDER_TYPE, RWeekCalendar.FDF_CALENDER);
        } else {

            /**
             * set Calender type to normal here the week days will
             * start as normal  be like Sun,Mon etc
             * */
            args.putInt(RWeekCalendar.CALENDER_TYPE, RWeekCalendar.NORMAL_CALENDER);
        }


//      args.putInt(RWeekCalender.CALENDER_BACKGROUND, ContextCompat.getColor(this,R.color.md_pink_700));//set background color to calender

        args.putString(RWeekCalendar.DATE_SELECTOR_BACKGROUND, "bg_select");//set background to the selected dates

//        args.putString(RWeekCalender.NOW_BACKGROUND,"bg_now");//set background to nowView

//        args.putInt(RWeekCalender.CURRENT_DATE_BACKGROUND,ContextCompat.getColor(this,R.color.md_black_1000));//set color to the currentdate

//        args.putInt(RWeekCalender.PRIMARY_BACKGROUND, ContextCompat.getColor(this,R.color.md_white_1000));//Set color to the primary views (Month name and dates)

//        args.putInt(RWeekCalender.SECONDARY_BACKGROUND, ContextCompat.getColor(this,R.color.md_green_500));//Set color to the secondary views (now view and week names)

//---------------------------------------------------------------------------------------------------------------------//


        rCalendarFragment.setArguments(args);

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.container, rCalendarFragment);
        t.commit();

        CalenderListener listener = new CalenderListener() {
            @Override
            public void onSelectPicker() {

                //User can use any type of pickers here the below picker is only Just a example

                DatePickerDialog.newInstance(Sample.this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");


            }

            @Override
            public void onSelectDate(LocalDateTime mSelectedDate) {

                //callback when a date is selcted

                mDateSelectedTv.setText("" + mSelectedDate.getDayOfMonth() + "-" + mSelectedDate.getMonthOfYear() + "-" + mSelectedDate.getYear());
            }
        };

        //setting the listener
        rCalendarFragment.setCalenderListener(listener);

    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {

//This is the call back from picker used in the sample you can use custom or any other picker

        //IMPORTANT: get the year,month and date from picker you using and call setDateWeek method
        Calendar calendar = Calendar.getInstance();

        calendar.set(year, monthOfYear, dayOfMonth);
        rCalendarFragment.setDateWeek(calendar);//Sets the selected date from Picker


    }


}
