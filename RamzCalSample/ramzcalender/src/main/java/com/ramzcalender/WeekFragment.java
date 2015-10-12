package com.ramzcalender;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramzcalender.utils.AppController;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
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
 SOFTWARE..
 */
public class WeekFragment extends Fragment {

    //Declaring Variables

    LocalDateTime mSelectedDate,startDate,mCurrentDate;

    TextView sundayTV, mondayTv,tuesdayTv, wednesdayTv, thursdayTv,fridayTv,saturdayTv;
    TextView[] textViewArray = new TextView[7];

    int datePosition=0,selectorDateIndicatorValue=0,currentDateIndicatorValue=0;
    ArrayList<LocalDateTime>dateInWeekArray=new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weekcell, container, false);

        sundayTV=(TextView)view.findViewById(R.id.sun_txt);
        mondayTv=(TextView)view.findViewById(R.id.mon_txt);
        tuesdayTv=(TextView)view.findViewById(R.id.tue_txt);
        wednesdayTv=(TextView)view.findViewById(R.id.wen_txt);
        thursdayTv=(TextView)view.findViewById(R.id.thu_txt);
        fridayTv=(TextView)view.findViewById(R.id.fri_txt);
        saturdayTv=(TextView)view.findViewById(R.id.sat_txt);

        /*Adding WeekViews to array for background changing purpose*/
        textViewArray[0]=sundayTV;
        textViewArray[1]=mondayTv;
        textViewArray[2]=tuesdayTv;
        textViewArray[3]=wednesdayTv;
        textViewArray[4]=thursdayTv;
        textViewArray[5]=fridayTv;
        textViewArray[6]=saturdayTv;



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*Setting the date info in the Application class*/

        startDate= AppController.getInstance().getDate();
        mCurrentDate=AppController.getInstance().getDate();


        /*Setting the Resources values and Customization values to the views*/

        Resources resources = getActivity().getResources();

        selectorDateIndicatorValue = resources.getIdentifier(getArguments().getString(RWeekCalender.DATE_SELECTOR_BACKGROUND), "drawable",
                RWeekCalender.PAKAGENAMEVALUE);

        currentDateIndicatorValue=getArguments().getInt(RWeekCalender.CURRENT_DATE_BACKGROUND);


        datePosition=getArguments().getInt(RWeekCalender.POSITIONKEY);
        int addDays=datePosition*7;

        startDate = startDate.plusDays(addDays);//Adding the 7days to the previous week

        mSelectedDate=AppController.getInstance().getSelected();


        textViewArray[0].setBackgroundResource(selectorDateIndicatorValue);//Setting the first days of the week as selected


      /*Fetching the data's for the week to display*/

        for (int i = 0; i < 7; i++) {

            if(mSelectedDate!=null) {

                if (mSelectedDate.getDayOfMonth() == startDate.getDayOfMonth()) {

                   /*Indicate  if the day is selected*/

                    textViewArray[i].setBackgroundResource(selectorDateIndicatorValue);

                    mDateSelectedBackground(i);//Changing View selector background

                    AppController.getInstance().setSelected(null);//null the selected date

                }
            }

            dateInWeekArray.add(startDate);//Adding the days in the selected week to list


            startDate = startDate.plusDays(1); //Next day


        }

        /*Setting color in the week views*/

        sundayTV.setTextColor(getArguments().getInt(RWeekCalender.PRIMARY_BACKGROUND));
        mondayTv.setTextColor(getArguments().getInt(RWeekCalender.PRIMARY_BACKGROUND));
        tuesdayTv.setTextColor(getArguments().getInt(RWeekCalender.PRIMARY_BACKGROUND));
        wednesdayTv.setTextColor(getArguments().getInt(RWeekCalender.PRIMARY_BACKGROUND));
        thursdayTv.setTextColor(getArguments().getInt(RWeekCalender.PRIMARY_BACKGROUND));
        fridayTv.setTextColor(getArguments().getInt(RWeekCalender.PRIMARY_BACKGROUND));
        saturdayTv.setTextColor(getArguments().getInt(RWeekCalender.PRIMARY_BACKGROUND));

        /*Displaying the days in the week views*/

        sundayTV.setText(Integer.toString(dateInWeekArray.get(0).getDayOfMonth()));
        mondayTv.setText(Integer.toString(dateInWeekArray.get(1).getDayOfMonth()));
        tuesdayTv.setText(Integer.toString(dateInWeekArray.get(2).getDayOfMonth()));
        wednesdayTv.setText(Integer.toString(dateInWeekArray.get(3).getDayOfMonth()));
        thursdayTv.setText(Integer.toString(dateInWeekArray.get(4).getDayOfMonth()));
        fridayTv.setText(Integer.toString(dateInWeekArray.get(5).getDayOfMonth()));
        saturdayTv.setText(Integer.toString(dateInWeekArray.get(6).getDayOfMonth()));

        /*if the selected week is the current week indicates the current day*/

        if(datePosition==0)
       {


           for(int i=0;i<7;i++)
           {

               if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)==dateInWeekArray.get(i).getDayOfMonth())
               {
                   textViewArray[i].setTextColor(currentDateIndicatorValue);
               }
           }

       }

        /**
         * Click listener of all week days with the indicator change and passing listener info.
         */

        sundayTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                mSelectedDateInfo(0);
                sundayTV.setBackgroundResource(selectorDateIndicatorValue);
                mDateSelectedBackground(0);


            }
        });

        mondayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mSelectedDateInfo(1);
                mondayTv.setBackgroundResource(selectorDateIndicatorValue);
                mDateSelectedBackground(1);



            }
        });

        tuesdayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedDateInfo(2);
                tuesdayTv.setBackgroundResource(selectorDateIndicatorValue);
                mDateSelectedBackground(2);



            }
        });


        wednesdayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedDateInfo(3);
                wednesdayTv.setBackgroundResource(selectorDateIndicatorValue);
                mDateSelectedBackground(3);



            }
        });


        thursdayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedDateInfo(4);
                thursdayTv.setBackgroundResource(selectorDateIndicatorValue);
                mDateSelectedBackground(4);



            }
        });


        fridayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedDateInfo(5);
                fridayTv.setBackgroundResource(selectorDateIndicatorValue);
                mDateSelectedBackground(5);



            }
        });


        saturdayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedDateInfo(6);
                saturdayTv.setBackgroundResource(selectorDateIndicatorValue);
                mDateSelectedBackground(6);




            }
        });

//---------------------------------------------------------------------------------------//


    }



    /**
     * Set Values including customizable info
     *
     * @param position
     * @param selectorDateIndicatorValue
     * @param currentDateIndicatorValue
     * @param primaryTextColor
     */
    public static WeekFragment newInstance(int position,String selectorDateIndicatorValue,int currentDateIndicatorValue,int primaryTextColor) {

        WeekFragment f = new WeekFragment();
        Bundle b = new Bundle();

        b.putInt(RWeekCalender.POSITIONKEY, position);
        b.putString(RWeekCalender.DATE_SELECTOR_BACKGROUND, selectorDateIndicatorValue);
        b.putInt(RWeekCalender.CURRENT_DATE_BACKGROUND, currentDateIndicatorValue);
        b.putInt(RWeekCalender.PRIMARY_BACKGROUND, primaryTextColor);
        f.setArguments(b);

        return f;
    }

    /**Passing the selected date info
     *
     * @param position
     */
    public void mSelectedDateInfo(int position)
    {
        RWeekCalender.getInstance().getSelectedDate(dateInWeekArray.get(position));

        mSelectedDate=dateInWeekArray.get(position);

        AppController.getInstance().setSelected(mSelectedDate);


    }

    /**Changing backgrounds of unselected views
     *
     * @param position
     */
    public void mDateSelectedBackground(int position)
    {
        for(int i=0;i<textViewArray.length;i++)
        {
            if(position!=i)
            {

                    textViewArray[i].setBackgroundColor(Color.TRANSPARENT);



            }

        }

    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        /**
         * Set setCalenderListener when user click on a date
         */

        if (isVisibleToUser) {

            if(dateInWeekArray.size()>0)
            RWeekCalender.getInstance().getSelectedDate(dateInWeekArray.get(0));

        }


            if(mSelectedDate!=null) {

                    textViewArray[0].setBackgroundResource(selectorDateIndicatorValue);
                    textViewArray[1].setBackgroundColor(Color.TRANSPARENT);

                    textViewArray[2].setBackgroundColor(Color.TRANSPARENT);
                    textViewArray[3].setBackgroundColor(Color.TRANSPARENT);
                    textViewArray[4].setBackgroundColor(Color.TRANSPARENT);
                    textViewArray[5].setBackgroundColor(Color.TRANSPARENT);
                    textViewArray[6].setBackgroundColor(Color.TRANSPARENT);
                }


    }


    /**
     * Setting date when selected form picker
     *
     * @param mSelectedDate
     */

    public void ChangeSelector(LocalDateTime mSelectedDate) {

        LocalDateTime startDate=AppController.getInstance().getDate();
        int addDays=datePosition*7;


        startDate = startDate.plusDays(addDays);

        for (int i = 0; i < 7; i++) {

            if (mSelectedDate.getDayOfMonth() == startDate.getDayOfMonth()) {
                textViewArray[i].setBackgroundResource(selectorDateIndicatorValue);
                mDateSelectedBackground(i);


            }
            startDate=startDate.plusDays(1);

        }
    }
}
