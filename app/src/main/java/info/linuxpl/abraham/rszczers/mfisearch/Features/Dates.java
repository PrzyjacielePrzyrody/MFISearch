package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.util.Log;
import org.joda.time.Interval;
import org.joda.time.Period;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Edyta Pawlak on 20.01.15.
 */
public class Dates {

    public static String[] timeBetween(PlanedActivity pa) {

        Calendar start = Dates.stringToCalendar(pa.getDate());
        Calendar now = Calendar.getInstance();
        Date date1;
        Date date2 ;
        String info;
    if(now.after(start)){
         date1 = Dates.calendarToSimpledateFormat(start);
         date2 = Dates.calendarToSimpledateFormat(now);
        info="Spóźnienie";

    }else {

         date1 = Dates.calendarToSimpledateFormat(now);
         date2 = Dates.calendarToSimpledateFormat(start);
        info="Pozostały czas";
    }

            Interval interval =
                    new Interval(date1.getTime(), date2.getTime());
            Period period = interval.toPeriod();
            String[] output=new String[2];
             output[0] = period.getMonths()+"miesięcy"+period.getDays() +"dni"+ period.getHours()+"godzin "+ period.getMinutes()+"minut ";
             output[1]=info;
        return output;
    }

    /**
     * Zwraca tablice zawierającą tablice DATE i TIME
     * @param date
     * @return
     */

    public static int[][] getDateTime(String date){

        int[] data=new int[3];
        int[] tim=new int[3];
        int[][] output =new int[2][];

        if(date.contains(" ")){
            String[] datetime=date.split(" ");
            String[] dat= datetime[0].split("-");
            String[] time=datetime[1].split(":");
            for(int i=0; i<dat.length; i++){
                data[i]=Integer.parseInt(dat[i]);
            }
            for(int i=0; i<time.length; i++){
                tim[i]=Integer.parseInt(time[i]);
            }

            output[0]=data;
            output[1]=tim;
        }
        else{
            String[] dat= date.split("-");
            int[] datee=new int[3];
            for(int i=0; i<dat.length; i++){
                datee[i]=Integer.parseInt(dat[i]);
            }
            output[0]=datee;
            output[1]=null;
        }
        return output;
    }

    public static int[] getDate(String date){
        String[] dat= date.split("-");
        int[] data=new int[3];
        for(int i=0; i<dat.length; i++){
            data[i]=Integer.parseInt(dat[i]);
        }
        return data;
    }

    /**
     * Usuwa niepotrzebne zera przy podanej dacie (Data podana razem z godziną). (Jeszcze w sumie nie wiem czy to potrzebne)
     * @param date
     * @return
     */

    public static String dateTimeToString(String date){
        int[][] d=Dates.getDateTime(date);
        String minutes;
        String seconds;
        if(d[1][1]<10){
            minutes="0"+d[1][1];
        }else{
            minutes=""+d[1][1];
        }
        if(d[1][2]<10){
            seconds="0"+d[1][2];
        }else{
            seconds=""+d[1][2];
        }
        String output=d[0][0]+"-"+d[0][1]+"-"+d[0][2]+" "+d[1][0]+":"+minutes+":"+seconds;
        return output;
    }

    /**
     * Usuwa niepotrzebne zera w samej dacie. Przyda się przy wyszukiwaniu.
     * @param date
     * @return
     */
    public static String dateToString(String date){
        String[] dat;
        String output;
        if(date.contains(" ")){
            String[] datetime=date.split(" ");
            dat= datetime[0].split("-");
        }
        else {
            dat = date.split("-");
        }
        int[] d=new int[3];

        for(int i=0; i<dat.length; i++){
            d[i]=Integer.parseInt(dat[i]);
        }
        return output=d[0]+"-"+d[1]+"-"+d[2];
    }

    /**
     * Tworzy Calendar z podanej daty.
     * @param date
     * @return
     */
    public static Calendar stringToCalendar(String date){
        GregorianCalendar calendar;
        int[][] dt;//=new int[2][];
        if(date.contains(" ")){
            dt= Dates.getDateTime(date);
            calendar=new GregorianCalendar(dt[0][0], dt[0][1]-1, dt[0][2], dt[1][0], dt[1][1], dt[1][2]);

        }
        else {
            dt=Dates.getDateTime(date);
            calendar=new GregorianCalendar(dt[0][0], dt[0][1]-1, dt[0][2]);
        }

        return calendar;
    }

    public static String getClassesTime(String date){
        int[][] dateTime=Dates.getDateTime(date);
        String time=dateTime[1][0]+":"+dateTime[1][1]+":"+dateTime[1][2];
        return time;
    }

    public static String calendarToString(Calendar day){
        int month=day.get(Calendar.MONTH)+1;
        return ""+day.get(Calendar.YEAR)+"-"+month+"-"+day.get(Calendar.DAY_OF_MONTH)+" "+day.get(Calendar.HOUR_OF_DAY)+":"+day.get(Calendar.MINUTE)+":"+day.get(Calendar.SECOND);
    }

    public static String weekDayName(Calendar cal){
        String name="";
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1: name="Niedziela";
                break;
            case 2:name="Poniedziałek";
                break;
            case  3: name= "Wtorek";
                break;
            case 4: name="Środa";
                break;
            case 5 :name="Czwartek";
                break;
            case  6: name= "Piątek";
                break;
            case 7:name="Sobota";
        }
        return name;
    }

    public static Date calendarToSimpledateFormat(Calendar calendar){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int month=calendar.get(Calendar.MONTH)+1;
        String mon=""+month;
        String day=""+calendar.get(Calendar.DAY_OF_MONTH);
        String hour=""+calendar.get(Calendar.HOUR_OF_DAY);
        String minute=""+calendar.get(Calendar.MINUTE);
        String second=""+calendar.get(Calendar.SECOND);
        Date output;

        if(calendar.get(Calendar.MONTH)+1<10){
            mon= ""+0+month;
        }
        if(calendar.get(Calendar.DAY_OF_MONTH)<10){
            day= ""+0+ calendar.get(Calendar.DAY_OF_MONTH);
        }
        if(calendar.get(Calendar.HOUR_OF_DAY)<10){
            hour= ""+0+ calendar.get(Calendar.HOUR_OF_DAY);
        }
        if(calendar.get(Calendar.MINUTE)<10){
            minute= ""+0+ calendar.get(Calendar.MINUTE);
        }
        if(calendar.get(Calendar.SECOND)<10){
            second= ""+0+ calendar.get(Calendar.SECOND);
        }

        try {
            output = simpleDateFormat.parse(calendar.get(Calendar.YEAR) + "-" + mon + "-" + day + " " + hour + ":" + minute + ":" + second);
        }catch (Exception e){
            output=null;
        }
        return output;
    }

    public static String fromToTime(String from, String minutesDuration){
        Calendar cal=Dates.stringToCalendar(from);
        int duration=Integer.parseInt(minutesDuration);
        cal.add(Calendar.MINUTE, duration);
        String fromTime=Dates.getClassesTime(from);
        String toTime=Dates.getClassesTime(Dates.calendarToString(cal));
        return fromTime+"-"+toTime;
    }
}
