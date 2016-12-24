package com.ramzcalender.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ramzcalender.RWeekCalendar;

/**
 * Created by munnaz on 24/12/16.
 */

public class SelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_row);

    }

    /*Select the normal calender*/
    public void normalCalender(View v)
    {
        startActivity(new Intent(SelectionActivity.this,Sample.class).putExtra(RWeekCalendar.CALENDER_TYPE,RWeekCalendar.NORMAL_CALENDER));
    }

    /*Select the fdf calender*/
    public void dayCalender(View v)
    {
        startActivity(new Intent(SelectionActivity.this,Sample.class).putExtra(RWeekCalendar.CALENDER_TYPE,RWeekCalendar.FDF_CALENDER));
    }
}
