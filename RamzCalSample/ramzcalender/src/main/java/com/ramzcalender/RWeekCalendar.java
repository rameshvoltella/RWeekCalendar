package com.ramzcalender;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ramzcalender.listener.CalenderListener;
import com.ramzcalender.utils.AppController;
import com.ramzcalender.utils.CalUtil;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Weeks;

import java.util.Calendar;

/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 Ramesh M Nair
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class RWeekCalendar extends Fragment {


    //Declaring Variables

    LocalDateTime mStartDate= new LocalDateTime();
    LocalDateTime selectedDate = new LocalDateTime();

    TextView monthView, nowView, sundayTv, mondayTv, tuesdayTv, wednesdayTv, thursdayTv, fridayTv, saturdayTv;
    ViewPager pager;
    LinearLayout mBackground;

    //Calender listener
    CalenderListener calenderListener;

    private static RWeekCalendar mInstance;
    CalenderAdaptor mAdaptor;

    //Bundle Keys
    public static String DATE_SELECTOR_BACKGROUND = "bg:select:date";
    public static String CURRENT_DATE_BACKGROUND = "bg:current:bg";
    public static String CALENDER_BACKGROUND = "bg:cal";
    public static String NOW_BACKGROUND = "bg:now";
    public static String PRIMARY_BACKGROUND = "bg:primary";
    public static String SECONDARY_BACKGROUND = "bg:secondary";
    public static String PACKAGENAME = "package";
    public static String POSITIONKEY = "pos";
    public static String CALENDER_TYPE = "cal.type";


    //NORMAL CALENDER
    public static int NORMAL_CALENDER = 0;

    //FIRST DAY FIRST CALENDER
    public static int FDF_CALENDER = 1;

    //initial values of calender property

    String selectorDateIndicatorValue = "bg_red";
    int currentDateIndicatorValue = Color.BLACK;
    int primaryTextColor = Color.WHITE;

    public static String PAKAGENAMEVALUE = "com.ramzcalender";

    //Current weekpostion of current day
    public static int CURRENT_WEEK_POSITION = 0;

    public static int calenderType = NORMAL_CALENDER;

    //Default will the current date
    DateTime start=new DateTime();
    //Default will be 100 more day from the current day
    DateTime end=start.plusDays(100);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initializing instance

        mInstance = this;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calenderview, container, false);

        pager = (ViewPager) view.findViewById(R.id.vp_pages);
        monthView = (TextView) view.findViewById(R.id.monthTv);
        nowView = (TextView) view.findViewById(R.id.nowTv);
        sundayTv = (TextView) view.findViewById(R.id.week_sunday);
        mondayTv = (TextView) view.findViewById(R.id.week_monday);
        tuesdayTv = (TextView) view.findViewById(R.id.week_tuesday);
        wednesdayTv = (TextView) view.findViewById(R.id.week_wednesday);
        thursdayTv = (TextView) view.findViewById(R.id.week_thursday);
        fridayTv = (TextView) view.findViewById(R.id.week_friday);
        saturdayTv = (TextView) view.findViewById(R.id.week_saturday);
        mBackground = (LinearLayout) view.findViewById(R.id.background);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nowView.setVisibility(View.GONE);

        /**
         * Checking for any customization values
         */

        if (getArguments().containsKey(CALENDER_BACKGROUND)) {

            mBackground.setBackgroundColor(getArguments().getInt(CALENDER_BACKGROUND));

        }

        if (getArguments().containsKey(DATE_SELECTOR_BACKGROUND)) {

            selectorDateIndicatorValue = getArguments().getString(DATE_SELECTOR_BACKGROUND);

        }

        if (getArguments().containsKey(CURRENT_DATE_BACKGROUND)) {

            currentDateIndicatorValue = getArguments().getInt(CURRENT_DATE_BACKGROUND);

        }

        if (getArguments().containsKey(CALENDER_TYPE)) {

            calenderType = getArguments().getInt(CALENDER_TYPE);
        }

        if (getArguments().containsKey(PRIMARY_BACKGROUND)) {

            monthView.setTextColor(getArguments().getInt(PRIMARY_BACKGROUND));
            primaryTextColor = getArguments().getInt(PRIMARY_BACKGROUND);

        }

        if (getArguments().containsKey(SECONDARY_BACKGROUND)) {

            nowView.setTextColor(getArguments().getInt(SECONDARY_BACKGROUND));
            sundayTv.setTextColor(getArguments().getInt(SECONDARY_BACKGROUND));
            mondayTv.setTextColor(getArguments().getInt(SECONDARY_BACKGROUND));
            tuesdayTv.setTextColor(getArguments().getInt(SECONDARY_BACKGROUND));
            wednesdayTv.setTextColor(getArguments().getInt(SECONDARY_BACKGROUND));
            thursdayTv.setTextColor(getArguments().getInt(SECONDARY_BACKGROUND));
            fridayTv.setTextColor(getArguments().getInt(SECONDARY_BACKGROUND));
            saturdayTv.setTextColor(getArguments().getInt(SECONDARY_BACKGROUND));

        }


        if (getArguments().containsKey(PACKAGENAME)) {

            PAKAGENAMEVALUE = getArguments().getString(PACKAGENAME);//its for showing the resource value from the parent package

        }


        if (getArguments().containsKey(NOW_BACKGROUND)) {

            Resources resources = getResources();
            nowView.setBackgroundResource(resources.getIdentifier(getArguments().getString(RWeekCalendar.NOW_BACKGROUND), "drawable",
                    PAKAGENAMEVALUE));

        }

        //----------------------------------------------------------------------------------------------//


        /*If the selected calender is FDF Calender the resent the day names according to the starting days*/
        if (calenderType != NORMAL_CALENDER) {
            int startingDate = new LocalDateTime().dayOfWeek().get();
            if (startingDate == 1) {

                sundayTv.setText("Mon");
                mondayTv.setText("Tue");
                tuesdayTv.setText("Wed");
                wednesdayTv.setText("Thu");
                thursdayTv.setText("Fri");
                fridayTv.setText("Sat");
                saturdayTv.setText("Sun");

            } else if (startingDate == 2) {

                sundayTv.setText("Tue");
                mondayTv.setText("Wed");
                tuesdayTv.setText("Thu");
                wednesdayTv.setText("Fri");
                thursdayTv.setText("Sat");
                fridayTv.setText("Sun");
                saturdayTv.setText("Mon");

            } else if (startingDate == 3) {

                sundayTv.setText("Wed");
                mondayTv.setText("Thu");
                tuesdayTv.setText("Fri");
                wednesdayTv.setText("Sat");
                thursdayTv.setText("Sun");
                fridayTv.setText("Mon");
                saturdayTv.setText("Tue");

            } else if (startingDate == 4) {

                sundayTv.setText("Thu");
                mondayTv.setText("Fri");
                tuesdayTv.setText("Sat");
                wednesdayTv.setText("Sun");
                thursdayTv.setText("Mon");
                fridayTv.setText("Tue");
                saturdayTv.setText("Wed");

            } else if (startingDate == 5) {

                sundayTv.setText("Fri");
                mondayTv.setText("Sat");
                tuesdayTv.setText("Sun");
                wednesdayTv.setText("Mon");
                thursdayTv.setText("Tue");
                fridayTv.setText("Wed");
                saturdayTv.setText("Thu");

            } else if (startingDate == 6) {

                sundayTv.setText("Sat");
                mondayTv.setText("Sun");
                tuesdayTv.setText("Mon");
                wednesdayTv.setText("Tue");
                thursdayTv.setText("Wed");
                fridayTv.setText("Thu");
                saturdayTv.setText("Fri");

            }
        }


        /*Setting Calender Adaptor*/

        mAdaptor = new CalenderAdaptor(getActivity().getSupportFragmentManager());
        pager.setAdapter(mAdaptor);


       /*CalUtil is called*/

        CalUtil mCal = new CalUtil();
        //date calculation called according to the typr
        if (calenderType != NORMAL_CALENDER) {
            mCal.calculate(mStartDate, FDF_CALENDER);
        } else {
            mCal.calculate(mStartDate, NORMAL_CALENDER);
        }

        mStartDate = mCal.getStartDate();//sets start date from CalUtil

        //Setting the month name and selected date listener
        monthView.setText(selectedDate.monthOfYear().getAsShortText() + " " + selectedDate.year().getAsShortText().toUpperCase());
        calenderListener.onSelectDate(selectedDate);

        CURRENT_WEEK_POSITION = Weeks.weeksBetween(mStartDate, selectedDate).getWeeks();

        pager.setCurrentItem(CURRENT_WEEK_POSITION);
        /*Week change Listener*/

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int weekNumber) {

                int addDays = weekNumber * 7;

                selectedDate = mStartDate.plusDays(addDays); //add 7 days to the selected date


                monthView.setText(selectedDate.monthOfYear().getAsShortText() + "-" + selectedDate.year().getAsShortText().toUpperCase());

                if (weekNumber == CURRENT_WEEK_POSITION) {

                    //the first week comes to view
                    nowView.setVisibility(View.GONE);


                } else {

                    //the first week goes from view nowView set visible for Quick return to first week

                    nowView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        /**
         * Change view to  the date of the current week
         */

        nowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calenderListener.onSelectDate(new LocalDateTime());

                pager.setCurrentItem(CURRENT_WEEK_POSITION);


            }
        });

        /**
         * For quick selection of a date.Any picker or custom date picker can de used
         */
        monthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calenderListener.onSelectPicker();


            }
        });


    }

    /**
     * Set set date of the selected week
     *
     * @param calendar
     */
    public void setDateWeek(Calendar calendar) {

        LocalDateTime ldt = LocalDateTime.fromCalendarFields(calendar);

        AppController.getInstance().setSelected(ldt);


        int nextPage = Weeks.weeksBetween(mStartDate, ldt).getWeeks();


        if (nextPage >= 0 && nextPage < getWeekBetweenDates(start, end)) {

            pager.setCurrentItem(nextPage);
            calenderListener.onSelectDate(ldt);
            WeekFragment fragment = (WeekFragment) pager.getAdapter().instantiateItem(pager, nextPage);
            fragment.ChangeSelector(ldt);
        }


    }


    /**
     * Notify the selected date main page
     *
     * @param mSelectedDate
     */
    public void getSelectedDate(LocalDateTime mSelectedDate,boolean fromUservisblity) {
        if (pager.getCurrentItem() == CURRENT_WEEK_POSITION) {
            if(fromUservisblity==true) {
                calenderListener.onSelectDate(new LocalDateTime());
            }
            else
            {
                calenderListener.onSelectDate(mSelectedDate);
            }

        } else {
            calenderListener.onSelectDate(mSelectedDate);
        }

    }

    /*Setting the starting date fom user in put*/
    public void startDate(int year, int month, int date) {
        mStartDate = new LocalDateTime(year, month, date, 0, 0, 0, 0);
        start = new DateTime(year, month, date, 0, 0, 0, 0);
    }

    /*Setting the ending date fom user in put*/
    public void endDate(int year, int month, int date) {
        end = new DateTime(year, month, date, 0, 0, 0, 0);
    }

    /**
     * Adaptor which shows weeks in the view
     */

    private class CalenderAdaptor extends FragmentStatePagerAdapter {

        public CalenderAdaptor(FragmentManager fm) {
            super(fm);
        }

        @Override
        public WeekFragment getItem(int pos) {


            return WeekFragment.newInstance(pos, selectorDateIndicatorValue, currentDateIndicatorValue, primaryTextColor);

        }

        @Override
        public int getCount() {
            return getWeekBetweenDates(start, end);
        }
    }


    /**
     * Set setCalenderListener when user click on a date
     *
     * @param calenderListener
     */
    public void setCalenderListener(CalenderListener calenderListener) {
        this.calenderListener = calenderListener;
    }

    /**
     * creating instance of the calender class
     */
    public static synchronized RWeekCalendar getInstance() {
        return mInstance;
    }

    public int getWeekBetweenDates(DateTime start, DateTime end) {

        int diff = Weeks.weeksBetween(start, end).getWeeks();
        diff = diff + 1;
        return diff;
    }
/*
    public void SetPrimaryTypeFace(Typeface mFont) {
//        monthView.setTypeface(null, Typeface.NORMAL);


    }

    public void SetSecondaryTypeFace(Typeface mFont) {
//        nowView.setTypeface(mFont);
//        sundayTv.setTypeface(mFont);
//        mondayTv.setTypeface(mFont);
//        tuesdayTv.setTypeface(mFont);
//        wednesdayTv.setTypeface(mFont);
//        thursdayTv.setTypeface(mFont);
//        fridayTv.setTypeface(mFont);
//        saturdayTv.setTypeface(mFont);

    }*/


}
