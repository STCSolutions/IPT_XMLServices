/*
 * Main.java
 *
 * Created on 27 octobre 2006, 18:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.Univox.Core;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Date;
import java.text.DateFormat;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author nohat
 */
public class Prayers {
    
    /** Creates a new instance of Main */
    public Prayers() {




    }
    
    private static String omQoraCalc(String marghrib)
    {
        String esha = "";
        DateFormat conFormat = new SimpleDateFormat("HH:mm:ss");
        Date temp = null;
        try
        {
            temp = conFormat.parse(marghrib);
        } catch (ParseException ex)
        {
            Logger.getLogger(Prayers.class.getName()).log(Level.SEVERE, null, ex);
        }
        long delay = new Long(90*60*1000);
        long marhgLong = temp.getTime();
        Date eshaDate = new Date(marhgLong + delay);
        esha = conFormat.format(eshaDate);

        return esha;
    }
//Function getAllPrayerTime return All prayers Times
    public static Vector getAllPrayersTime() {
        
        
        
        //get Current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
        Date d = new Date();
        String date= dateFormat.format(d);
        String [] param= new String[5];
        param=date.split("/",4);
        
        
        
        
        //read location parameters from properties file
        Properties props=new Properties( ) ;
        try {
            
            props.load(  new FileInputStream( new File( "C:\\STCs\\Location.properties" )  )  ) ;
            
        } catch ( IOException ie )  {
            ie.printStackTrace();
          
        }
        
        
        //inserted latitude parameters
        String param1 = "Latitude.Degree";
        String param2 = "Latitude.Minutes";
        String param3 = "Latitude.Seconds";
        String param4 = "Latitude.Direction";
        
        //inserted longitude parameters
        String param5 = "Longitude.Degree";
        String param6 = "Longitude.Minutes";
        String param7 = "Longitude.Seconds";
        String param8 = "Longitude.Direction";
        
        //inserted time zone
        String param9 = "Time.Zone";
        
        //inserted hight from center
        String param10 = "Hight";
        
        //inserted Fajr angle
        String param11= "Fajr";
        
        //inserted Isha angle
        String param12= "Isha";
        
        //inserted Fiqh
        String param13= "Fiqh";
        
        
        
        
        int day=Integer.parseInt(param[2]); //day
        int mon=Integer.parseInt(param[1]); //month
        int year=Integer.parseInt(param[0]); //year
        
        //latitude
        int d2=Integer.parseInt(props.getProperty(param1));  //lat dehree
        int m2=Integer.parseInt(props.getProperty(param2));   //lat minutes
        Float s2=new Float(Integer.parseInt(props.getProperty(param3)));  //lat sec
        char card2=props.getProperty(param4).charAt(0);//latitude N/S
        
        
        //longitude
        int d1=Integer.parseInt(props.getProperty(param5));   //long degree
        int m1=Integer.parseInt(props.getProperty(param6));   //long min
        Float s1=new Float(Integer.parseInt(props.getProperty(param7)));  //long sec
        char card1=props.getProperty(param8).charAt(0);//logitude E/W
        
        
        
        int tz=Integer.parseInt(props.getProperty(param9));//Time Zone
        
        Float p=new Float(1012);//pression
        Float t=new Float(27);//temp
        
        Float h=new Float(Integer.parseInt(props.getProperty(param10)));//hight
        
        int met=Integer.parseInt(props.getProperty(param13));// 1:Shafai, maliki, hnbali  2:hanafi
        int p_faj=0;//avancement ou decalage en mn fajer
        int p_shu=0;//avancement ou decalage en mn shuroq
        int p_dho=0;//avancement ou decalage en mn dho
        int p_ase=0;//avancement ou decalage en mn aser
        int p_mag=0;//avancement ou decalage en mn maghreb
        int p_ich=0;//avancement ou decalage en mn isha
        int a_faj=Integer.parseInt(props.getProperty(param11));//angle fajer
        int a_ich=Integer.parseInt(props.getProperty(param12));//angle Isha
        int pre=1;//0:display in minutes only 1:display seconds
        //summer time ajoute 60 mn ï¿½ chaque priï¿½re p_faj....p_ich
        
        
        
        
        Calcul cal= new Calcul();
        //United Arab Emirates:Abu Dhabi;54;39;0;E;24;26;0;N;4;1010;10;60;?????????: ??? ???
        //Palestine:Behlehem;35;11;3;E;31;46;22;N;2;1010;10;0
        //Zimbabwe:Harare;31;1;48;E;17;30;0;S;2;1010;10;0
        
        cal.trt(year , mon, day, d1, m1, s1, card1,
                d2, m2, s2, card2, tz, p, t, h, met,
                p_faj, p_shu, p_dho, p_ase, p_mag, p_ich, a_faj, a_ich, pre);
        
        
        Vector result = new Vector();
        
        result.add(cal.P_FAJ);
        result.add(cal.P_SOB);
        result.add(cal.P_DHO);
        result.add(cal.P_ASE);
        result.add(cal.P_MAG);
//        KSA UPDATES
        result.add(cal.P_ISH);
//        KSA UPDATES
        
        
        
        return result;
    }
    
    
    
    
    //function getPrayerTime Return nearest prayer time
    public static long[] getPrayerTime() {
        
        //get Current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date d = new Date();
        String date= dateFormat.format(d);
        String [] param= new String[5];
        param=date.split("/",3);
        
        Properties props=new Properties(  ) ;
        try {
           
            props.load(  new FileInputStream( new File("C:\\STCs\\Location.properties")  )  ) ;
            
        } catch ( IOException ie )  {
           
        }
        
        
        //inserted latitude parameters
        String param1 = "Latitude.Degree";
        String param2 = "Latitude.Minutes";
        String param3 = "Latitude.Seconds";
        String param4 = "Latitude.Direction";
        
        //inserted longitude parameters
        String param5 = "Longitude.Degree";
        String param6 = "Longitude.Minutes";
        String param7 = "Longitude.Seconds";
        String param8 = "Longitude.Direction";
        
        //inserted time zone
        String param9 = "Time.Zone";
        
        //inserted hight from center
        String param10 = "Hight";
        
        //inserted Fajr angle
        String param11= "Fajr";
        
        //inserted Isha angle
        String param12= "Isha";
        
        //inserted Fiqh
        String param13= "Fiqh";
        
        
        
        
        int day=Integer.parseInt(param[2]); //day
        int mon=Integer.parseInt(param[1]); //month
        int year=Integer.parseInt(param[0]); //year
        
        //latitude
        //SynchronizedLogger.log(props.getProperty(param1), SynchronizedLogger.PrayerTimes);
        int d2=Integer.parseInt(props.getProperty(param1));  //lat dehree
        int m2=Integer.parseInt(props.getProperty(param2));   //lat minutes
        Float s2=new Float(Integer.parseInt(props.getProperty(param3)));  //lat sec
        char card2=props.getProperty(param4).charAt(0);//latitude N/S
        
        
        //longitude
        int d1=Integer.parseInt(props.getProperty(param5));   //long degree
        int m1=Integer.parseInt(props.getProperty(param6));   //long min
        Float s1=new Float(Integer.parseInt(props.getProperty(param7)));  //long sec
        char card1=props.getProperty(param8).charAt(0);//logitude E/W
        
        
        
        int tz=Integer.parseInt(props.getProperty(param9));//Time Zone
        
        Float p=new Float(1012);//pression
        Float t=new Float(27);//temp
        
        Float h=new Float(Integer.parseInt(props.getProperty(param10)));//hight
        
        int met=Integer.parseInt(props.getProperty(param13));// 1:Shafai, maliki, hnbali  2:hanafi
        int p_faj=0;//avancement ou decalage en mn fajer
        int p_shu=0;//avancement ou decalage en mn shuroq
        int p_dho=0;//avancement ou decalage en mn dho
        int p_ase=0;//avancement ou decalage en mn aser
        int p_mag=0;//avancement ou decalage en mn maghreb
        int p_ich=0;//avancement ou decalage en mn isha
        int a_faj=Integer.parseInt(props.getProperty(param11));//angle fajer
        int a_ich=Integer.parseInt(props.getProperty(param12));//angle Isha
        int pre=1;//0:display in minutes only 1:display seconds
        //summer time ajoute 60 mn ï¿½ chaque priï¿½re p_faj....p_ich
        
        
        
        
        Calcul cal= new Calcul();
        //United Arab Emirates:Abu Dhabi;54;39;0;E;24;26;0;N;4;1010;10;60;?????????: ??? ???
        //Palestine:Behlehem;35;11;3;E;31;46;22;N;2;1010;10;0
        //Zimbabwe:Harare;31;1;48;E;17;30;0;S;2;1010;10;0
        
        cal.trt( year , mon, day, d1, m1, s1, card1,
                d2, m2, s2, card2, tz, p, t, h, met,
                p_faj, p_shu, p_dho, p_ase, p_mag, p_ich, a_faj, a_ich, pre);
        
        
        Vector prayer = new Vector();
        
        prayer.add(cal.P_FAJ);
        prayer.add(cal.P_SOB);
        prayer.add(cal.P_DHO);
        prayer.add(cal.P_ASE);
        prayer.add(cal.P_MAG);
//        KSA UPDATES
        prayer.add(cal.P_ISH);
//        KSA UPDATES
        
        DateFormat nowFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat prayFormat = new SimpleDateFormat("yyyy/MM/dd");
        
        Date now = new Date();
        String nowString = nowFormat.format(now);
        Date finalNow = new Date(nowString);
        
        String prayerString;
        
        long remind;
        long [] result= new long[2];
        
        result[0]=-1;
        result[1]=-1;
      
        
        
        for(int i=0; i<6; i++) {
            prayerString = prayFormat.format(now) + " " + prayer.get(i);  //prayer time with today
            Date finalPray = new Date(prayerString);
            
            if(finalPray.after(finalNow)) {
                if(i==1) continue;
                remind=(finalPray.getTime())-(finalNow.getTime());
                result[0]=remind;
                result[1]=i;
                
                break;
            }
        }
            
            if(result[0]==-1 && result[1]==-1)
            {
                
                Date tom = new Date();
                tom.setDate(tom.getDate() + 1); //day of tomorrow
                
                String tomString = dateFormat.format(tom); //convert to string
               
                
                String [] tomParam= new String[5];
                tomParam=tomString.split("/",3);
                day=Integer.parseInt(tomParam[2]); //day
                mon=Integer.parseInt(tomParam[1]); //month
                year=Integer.parseInt(tomParam[0]); //year
                cal.trt( year , mon, day, d1, m1, s1, card1,
                        d2, m2, s2, card2, tz, p, t, h, met,
                        p_faj, p_shu, p_dho, p_ase, p_mag, p_ich, a_faj, a_ich, pre);
                
                prayerString = prayFormat.format(tom) + " " + cal.P_FAJ;  //next fajr with date of tomorrow
                Date finalPray = new Date(prayerString);  //fajr of tomorrow prayertime
                remind=(finalPray.getTime())-(finalNow.getTime());
                result[0]=remind;
                result[1]=0;
            
            }
            
        
        return result;
    }
    
    public static void main(String[] args) {

        System.out.println(getAllPrayersTime());
//        long [] loo = getPrayerTime();
//
//            for (long g : loo) {
//            System.out.println("g: "+g);
  //          }
        
    }
}
