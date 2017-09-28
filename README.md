RWeekCalendar-Android
=================

### Build status

**develop** [![master](https://api.travis-ci.org/rameshvoltella/RWeekCalendar.svg?branch=master)](https://travis-ci.org/rameshvoltella/RWeekCalendar)


This is a sample application which displays Calendar as week view. Each week of the month is displayed. Inspired by ([WeCal App](https://play.google.com/store/apps/details?id=im.ecloud.ecalendar)) 



##Download: 

[![Old Here:](https://raw.githubusercontent.com/rameshvoltella/RWeekCalendar/master/Screens/playstore.png)](https://play.google.com/store/apps/details?id=com.ramzcalender.sample)


* new Apk file


[![New Apk Here:](https://raw.githubusercontent.com/rameshvoltella/RWeekCalendar/master/Screens/playstore.png)](https://raw.githubusercontent.com/rameshvoltella/RWeekCalendar/master/Screens/apk/v4.apk)

##Video

[![Video](https://img.youtube.com/vi/gNlkxo7MfNs/0.jpg)](https://www.youtube.com/watch?v=gNlkxo7MfNs)



# Gradle
--------
[![](https://jitpack.io/v/rameshvoltella/RWeekCalendar.svg)](https://jitpack.io/#rameshvoltella/RWeekCalendar)

/build.gradle
```groovy
allprojects {
	repositories {
		jcenter()
		maven { url "https://jitpack.io" }
	}
}
```

Then add the dependency to your module's build.gradle:

/app/build.gradle
```groovy
compile 'com.github.rameshvoltella:RWeekCalendar:0.1.0'
```


![sample_screen1](https://raw.githubusercontent.com/rameshvoltella/RWeekCalendar/master/Screens/s1.png)

![sample_screen1](https://raw.githubusercontent.com/rameshvoltella/RWeekCalendar/master/Screens/s2.png)





RWeekCalendar: how to use
------------------------

1. Set Container view in xml
  
   ```xml
       <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:id="@+id/container"></LinearLayout>
```


2. Intialize RWeekCalendar
  
    ```java
     RWeekCalendar rCalendarFragment=new RWeekCalendar();
```

3. Set the start date and end date 
  
    ```java
     rCalendarFragment.startDate(1989, 9, 1);//Start date
     rCalendarFragment.endDate(2018, 12, 31);//Ending date
```

4. Setting RWeekCalendar in your view
  
    ```java

       FragmentTransaction t = getSupportFragmentManager().beginTransaction();
       t.replace(R.id.container, rCalendarFragment);
       t.commit();
```

5. Adding Custumizable Attributes(Add this Before Setting RWeekCalendar)
  
    ```java
     Bundle args = new Bundle();

       /*Should add this attribute if you adding  the NOW_BACKGROUND or DATE_SELECTOR_BACKGROUND Attribute*/
       args.putString(RWeekCalendar.PACKAGENAME,getApplicationContext().getPackageName());

       /* IMPORTANT: Customization for the Calendar commenting or un commenting any of the attribute below will reflect change in Calendar*/

//---------------------------------------------------------------------------------------------------------------------//

      args.putInt(RWeekCalendar.Calendar_BACKGROUND, ContextCompat.getColor(this,R.color.md_pink_700));//set background color to Calendar

      args.putString(RWeekCalendar.DATE_SELECTOR_BACKGROUND,"bg_select");//set background to the selected dates

      args.putString(RWeekCalendar.NOW_BACKGROUND,"bg_now");//set background to nowView

      args.putInt(RWeekCalendar.CURRENT_DATE_BACKGROUND,ContextCompat.getColor(this,R.color.md_black_1000));//set color to the currentdate

      args.putInt(RWeekCalendar.PRIMARY_BACKGROUND, ContextCompat.getColor(this,R.color.md_white_1000));//Set color to the primary views (Month name and dates)

      args.putInt(RWeekCalendar.SECONDARY_BACKGROUND, ContextCompat.getColor(this,R.color.md_green_500));//Set color to the secondary views (now view and week names)

//---------------------------------------------------------------------------------------------------------------------//

       rCalendarFragment.setArguments(args);
```				

6. Setting listener of the Calendar
  
    ```java

      CalendarListener listener=new CalendarListener() {
            @Override
           public void onSelectPicker() {

           //Allow user to show picker you can use custum picker or any othe picker library

         


            }

            @Override
            public void onSelectDate(LocalDateTime mSelectedDate) {

                //callback when a date is selcted


            }
        };

        //setting the listener
        rCalendarFragment.setCalendarListener(listener);
```

7. After Date selected from Picker
  
    ```java

        //IMPORTANT: get the year,month and date from picker you using and call setDateWeek method
        Calendar calendar = Calendar.getInstance();

        calendar.set(year, monthOfYear, dayOfMonth);
        rCalendarFragment.setDateWeek(calendar);//Should call setDateWeek after getting date
```
				

    
   





## License

    The MIT License (MIT)

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


 



