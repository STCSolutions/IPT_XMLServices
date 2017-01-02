/*
 * Calcul.java
 *
 * Created on 25 ao�t 2005, 23:57
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package Com.Univox.Core;


/**
 *
 * @author Administrateur
 */
public class Calcul {

// #define M_PI 3.141593
    int a_faj2, a_ich2, pre2;
    Float n, l, g, lem, ep, sig, E, R, sem_dia, a;
    String P_FAJ, P_SOB, P_DHO, P_ASE, P_MAG, P_ISH;
    public int met_sum;
//char[] res;
//************para_time************
    StringBuffer he, mn, se;
//char[] he=new char[2],mn=new char[2],se=new char[2];
    int h, m, s;
    char[] inter22;

    /** Creates a new instance of Calcul */
    public Calcul() {
    }
    //++++++++++++++++++to_str+++++++++++++++++++

    public String to_str(char[] inter) {
        StringBuffer inter2 = new StringBuffer();
        inter2.append(inter);
        return (inter2.toString());
    }
    //++++++++++++++++++float_ex++++++++++++++++

    boolean float_ex(String s) {

        int c, ii = 0;

        while (ii < s.length()) {

            c = s.charAt(ii);
            if (c == '.') {
                return true;
            }//if
            ii++;
        }//while
        return false;
    }//float_ex
    //+++++++++++++++++separe+++++++++++++++++++

    public String separe(String texte, char sep, int nb) {
        int ii = 0, i = 0, c, pp = 0;
        int tt = 1;
        char inter33[] = new char[texte.length()];
        StringBuffer inter44 = new StringBuffer();

        inter33[0] = '\0';

        while (ii < texte.length()) {

            c = texte.charAt(ii);
            if (!((c == sep) || (c == '\n') || (c == '\r'))) {
                inter44.append(texte.charAt(ii));
                i++;
            }//if

// System.out.println("inter="+inter44);
            ii++;
            if ((c == sep)) {
                //**********
                if (pp == nb) {
                    tt = 1;
                    return inter44.toString();
                }//if

                inter44.delete(0, texte.length());
                pp++;
                i = 0;

            }//if

        }//while

        tt = 0;

        return inter44.toString();


    }//separe
    //+++++++++++add_0+++++++++++++++++
    String add_0(int inter) {
        StringBuffer s = new StringBuffer();
        if (inter < 10) {
            s.append("0");
        }//if
        s.append(inter);
        return s.toString();
    }//add_0
//+++++++++++red_ang(radium)+++++++++++
    Float red_ang(Float g) {
//g=Float.toDegrees(g);    
        Float p2 = new Float(Float.PI.Mul(2));

        Float int4 = new Float(new Float(to_int(g.Div(p2))));

        if (g.Great(new Float(10))) {
            g = new Float(g.Sub(((Float.abs(int4).Mul(p2)))));
        } else {
            g = new Float(g.Add(((Float.abs((new Float(int4)).Add(new Float(1))).Mul(p2)))));
        }
        return g;

    }
    //************para_time************

    char[] para_time(char[] time, int nb) {
        he = new StringBuffer();
        mn = new StringBuffer();
        se = new StringBuffer();
        he.append(time, 0, 2);
        mn.append(time, 3, 2);
        se.append(time, 6, 2);


        h = Integer.parseInt(he.toString());
        m = Integer.parseInt(mn.toString());

//------------av----------------------
        if (nb > 0) {
            m = m + nb;

            if (m >= 60) {
                h = (m / 60) + h;
                m = m % 60;
            }//if
            if (h >= 24) {
                h = h % 24;
            }
        }//if
        else {

//------------re----------------------
            m = m + nb;
            if (m < 0) {
                m = Math.abs(m);
                h = h - ((m / 60) + 1);
                m = 60 - (m % 60);
            }//if

            if (h < 0) {
                h = Math.abs(h);
                h = 24 - h;
            }//if

        }//else
        StringBuffer inter33 = new StringBuffer();
        inter33.append(add_0(h));
        inter33.append(':');
        inter33.append(add_0(m));
        inter33.append(':');
        inter33.append(se);

        inter22 = inter33.toString().toCharArray();

        return inter22;

    }//para_time
//=================to_deg==================================================
    Float to_deg(int d, int m, Float s) {
       // Float inter1 = new Float(16667, -6L);
       // Float inter2 = new Float(278217, -9L);

        Float inter1=new Float(1667,-5);
        Float inter2=new Float(28,-5);

        // inter1=inter1.toString();
        //inter2=inter2.toString();

        Float inter_min = new Float((new Float(m).Mul(inter1)));

        Float inter_sec = new Float((s.Mul(inter2)));
        // inter_min=cut_flo(inter_min.toString());
        // inter_sec=cut_flo(inter_sec.toString());

        Float result = new Float((new Float(d).Add(inter_min).Add(inter_sec)));
        // result=cut_flo(result.toString());
        return result;

    }//to_deg
//=========================acot==========================================

    Float acot(Float d) {
        return (new Float(2).Mul(Float.atan(new Float(1)))).Sub(Float.atan(d));
    }//acot
//==========================get_moy_time_zone=========================================
    int get_moy_time_zone(int tz) {
        switch (tz) {

            case 0:
                return 0;
            case 1:
                return 15;
            case 2:
                return 30;
            case 3:
                return 45;
            case 4:
                return 60;
            case 5:
                return 75;
            case 6:
                return 90;
            case 7:
                return 105;
            case 8:
                return 120;
            case 9:
                return 135;
            case 10:
                return 150;
            case 11:
                return 165;
            case 12:
                return 180;

//--

            case -1:
                return -15;
            case -2:
                return -30;
            case -3:
                return -45;
            case -4:
                return -60;
            case -5:
                return -75;
            case -6:
                return -90;
            case -7:
                return -105;
            case -8:
                return -120;
            case -9:
                return -135;
            case -10:
                return -150;
            case -11:
                return -165;
            case -12:
                return -180;

            default:
                return 0;
        }//switch
    }//get_moy_time_zone
//=============================pri3======================================
    Float pri3(Float t_zone, Float E, Float lon, char card) {
        Float int1 = new Float(get_moy_time_zone(Integer.parseInt(t_zone.toString())));
        if (card == 'W') {
            lon = lon.Neg();
        }
        return ((new Float(15 * 12).Add(int1.Sub(lon))).Sub(E.Div(4)));
    }//pri3
//===================================================================
/*Float cut_flo(String s){
    //String s=new String(Float.to;
    Float int1=new Float();
    StringBuffer inter44 = new StringBuffer();
    inter44.append(separe(s, '.', 0));
    // int1.m_E=-inter44.length();
    //  System.out.println("reeeesult="+int1.m_E);
    inter44.append(separe(s, '.', 1).toCharArray(), 0, 4);
    
    //
    int1.m_Val=Integer.parseInt(inter44.toString());
    int1.m_E=-4;
    return int1;
    }
     */
//=======================to_flo============================================
    Float to_flo(String s) {

        Float int1 = new Float();
        StringBuffer inter44 = new StringBuffer(), inter55 = new StringBuffer();

        inter44.append(separe(s, '.', 0));
        if (!float_ex(s)) {
            return new Float(Integer.parseInt(inter44.toString()));
        }
        inter55.append(separe(s, '.', 1).toCharArray());
        inter44.append(separe(s, '.', 1).toCharArray());

        int1.m_E = -inter55.length();

        int1.m_Val = Integer.parseInt(inter44.toString());

        return int1;
    }//to_flo
    Float func3(Float tet, Float phi, Float sig) {
//return rad_deg(acos(   (cos(tet)- (sin(sig)*sin(phi))) /  (cos(phi)*cos(sig))    ));
        tet = new Float(Float.toRadians(tet));
        phi = new Float(Float.toRadians(phi));
        sig = Float.toRadians(sig);
        Float int1 = Float.sin(sig).Mul((Float.sin(phi)));
        Float int2 = new Float((Float.cos(tet)).Sub((int1)));
        Float int3 = new Float((Float.cos(phi)).Mul((Float.cos(sig))));
        Float int4 = new Float(Float.toDegrees(Float.acos(int2.Div(int3))));
        return int4;
    }//func3
//===========================pri4========================================

    Float pri4(Float pri3, Float phi, Float sig, int methode, char card) {
        Float a, tet, inter;

        phi = new Float(Float.toRadians(phi));
        sig = new Float(Float.toRadians(sig));
        //phi=phi.toString();
        //sig=sig.toString();

        if (card == 'S') {
            phi = phi.Neg();
//phi=cut_flo(phi.toString());
        }

        // a=asinl( (sinl(phi)*sinl(sig))+(cosl(phi)*cosl(sig))  );

        Float int1 = Float.sin(phi).Mul((Float.sin(sig)));
        Float int2 = Float.cos(phi).Mul((Float.cos(sig)));
        a = new Float(Float.asin(int1.Add(int2)));

//a=(a.toString());  
//tet= rad_deg((deg_rad(90)-(acot( (methode+(1/tanl(a))))  )));

        int1 = Float.toRadians(new Float(90));
//int1=cut_flo(int1.toString());

        //int2=cut_flo(acot(new Float(methode).Add(new Float(1).Div(Float.tan(a)))).toString());
        //Float int9=new Float (cut_flo((new Float(1).Div(Float.tan(a))).toString()));
        Float int9 = new Float(((new Float(1).Div(Float.tan(a)))));
        //int9=cut_flo(int9.toString());
        Float int91 = ((new Float(methode).Add(int9)));
        int2 = (acot((int91)));


        tet = new Float(Float.toDegrees(int1.Sub(int2)));
        //tet=cut_flo(tet.toString());
//return pri3+(func3(fabsl(tet),rad_deg(phi),rad_deg(sig)));

        int1 = new Float(Float.abs(tet));

        int2 = new Float(Float.toDegrees(phi));
        Float int3 = new Float(Float.toDegrees(sig));
        Float int4 = new Float(func3((int1), (int2), (int3)));
//func3(int1, int2, int3));
        Float int5 = new Float(pri3.Add(int4));
//int5=cut_flo(int5.toString()); 
        return int5;
    }//pri4
    int to_int(Float f) {
        //long inter;
        return Integer.parseInt(separe(f.toString(), '.', 0));
    }//to_int
//===================================================================
    Float get_Julian_date(int year, int mon, int day, int hr, int min, int sec) {
        Float Julian_date_0hrUTptr;

        long Y = 0, M = 0, A = 0, B = 0, C = 0, D = 0, inter = 0;                     // intermediate calculated results
        Float cal_date, // calendar date (YYYYMMDD)
                GREG_DATE = new Float(15821015);            // start of Gregorian calendar,ie,
        // 1582 AD, Oct 15
        Y = year;
        M = mon;
        if (M == 1 || M == 2) {
            Y -= 1;
            M += 12;
        }
        A = (long) (Y / 100);
        B = 0;

        cal_date = new Float(year).Mul(10000).Add(new Float(mon).Mul(100)).Add(new Float(day));
        if (cal_date.Great(GREG_DATE)) {
            B = 2 - A + (long) (A / 4);
        }

        C = to_int(new Float(36525, -2).Mul(Y));
        D = to_int(new Float(306001, -4).Mul(M + 1));
        inter = (B + C + D + day);
        Julian_date_0hrUTptr = new Float(new Float(17209945, -1).Add(new Float(inter)));

        Float int1 = new Float(min).Div(24 * 60);
        Float int2 = new Float(sec).Div(24 * 60 * 60);

        return (Julian_date_0hrUTptr.Add(new Float(hr).Div(24)).Add(new Float(int1)).Add(new Float(int2)));

    }//get_Julian_date
//===================================================================

    void get_sigma(Float JD) {

        n = new Float(JD.Sub(new Float(2451545)));
        l = new Float(new Float(280466, -3).Add(new Float(9856474, -7).Mul(n)));
        Float int4 = new Float(new Float(to_int(l.Div(360))));

        if (l.Great(new Float(0))) {
            l = new Float(l.Sub(((Float.abs(int4).Mul(360)))));
        } else {
            l = new Float(l.Add(((Float.abs((int4).Add(new Float(1))).Mul(360)))));
        }


        g = new Float(new Float(357528, -3).Add(new Float(9856003, -7).Mul(n)));
        int4 = new Float(new Float(to_int(g.Div(360))));

        if (g.Great(new Float(0))) {
            g = new Float(g.Sub(((Float.abs(int4).Mul(360)))));
        } else {
            g = new Float(g.Add(((Float.abs((int4).Add(new Float(1))).Mul(360)))));
        }



        Float int1 = new Float(Float.toRadians((new Float(1915, -3)).Mul(Float.sin(Float.toRadians(g)))));
        Float int2 = new Float(Float.toRadians((new Float(2, -2)).Mul(Float.sin(Float.toRadians(g.Mul(2))))));

        lem = new Float(l.Add(Float.toDegrees(int1.Add(int2))));


        ep = new Float(new Float(23440, -3).Sub(new Float(4, -7).Mul(n)));


        int1 = new Float(Float.cos(Float.toRadians(ep)));
        int2 = new Float(Float.tan(Float.toRadians(lem)));
        a = new Float(Float.toDegrees(Float.atan(int1.Mul(int2))));


        int1 = new Float(Float.sin(Float.toRadians(ep)));
        int2 = new Float(Float.sin(Float.toRadians(lem)));
        sig = new Float(Float.toDegrees(Float.asin(int1.Mul(int2))));


        E = new Float((l.Sub(a)).Mul(4));
        int4 = new Float(new Float(to_int(E.Div(360))));

        if (E.Great(new Float(30))) {
            E = new Float(E.Sub(((Float.abs(int4).Mul(360)))));
        }
        if (E.Great(new Float(30))) {
            E = new Float(E.Sub(new Float(360)));
        }


        int1 = new Float(Float.toRadians(new Float(1671, -5)).Mul(Float.cos(Float.toRadians(g))));
        int2 = new Float(Float.toRadians(new Float(14, -5)).Mul(Float.cos((Float.toRadians(g)).Mul(2))));
        R = new Float(Float.toDegrees(Float.toRadians(new Float(100014, -5)).Sub(int1).Sub(int2)));


        sem_dia = new Float(new Float(2666, -4).Div(R));
//
    }//get_sigma
//=====================pri1==============================================
    Float pri1(Float pri3, Float lat, char card) {


        if (card == 'S') {
            lat = lat.Neg();
        }
//return pri3-(func3(a_faj2,lat,sig));
        return pri3.Sub(func3(new Float(a_faj2), lat, sig));
    }//pri1
//=====================pri6==============================================
    Float pri6(Float pri3, Float lat, char card) {


        if (card == 'S') {
            lat = lat.Neg();
        }
//return pri3-(func3(a_faj2,lat,sig));
        return pri3.Add(func3(new Float(a_ich2), lat, sig));
    }//pri6
//============================pri2=======================================
    Float pri2(Float pri3, Float lat, char card, Float p, Float t, Float h) {

        Float tet;

        Float int1 = new Float((new Float(28, -2).Mul(p)).Div(t.Add(new Float(273))));
        Float int2 = new Float(new Float(35333, -6).Mul(Float.sqrt(h)));
        tet = new Float(new Float(90).Add(sem_dia).Add((new Float(569333, -6).Mul(int1))).Add(int2).Sub(new Float(24, -4)));
        if (card == 'S') {
            lat = lat.Neg();
        }

        return pri3.Sub(func3(tet, lat, sig));

    }//pri2
//============================pri5=======================================
    Float pri5(Float pri3, Float lat, char card, Float p, Float t, Float h) {

        Float tet;



        Float int1 = new Float((new Float(28, -2).Mul(p)).Div(t.Add(new Float(273))));
        Float int2 = new Float(new Float(35333, -6).Mul(Float.sqrt(h)));
        tet = new Float(new Float(90).Add(sem_dia).Add((new Float(569333, -6).Mul(int1))).Add(int2).Sub(new Float(24, -4)));
        if (card == 'S') {
            lat = lat.Neg();
        }

        return pri3.Add(func3(tet, lat, sig));

    }//pri5
//========================to_time===========================================
    String to_time(Float time) {
        Float f;
        int h, m, s;

        StringBuffer h2 = new StringBuffer(), m2 = new StringBuffer(), s2 = new StringBuffer();
        time = new Float(time.Div(new Float(15)));
        h = to_int(time);
        f = new Float((time.Sub(new Float(h))).Mul(60));
        m = to_int(f);
        s = to_int((f.Sub(new Float(m))).Mul(60));

        if (pre2 == 0) {
            if (s > 30) {
                m++;

                if (m == 60) {
                    m = 0;
                    h++;
                }//if
                if (h == 24) {
                    h = 0;
                }//if
                if (h == 25) {
                    h = 1;
                }//if
            }//if

            s = 0;
        }//if

        if (h < 10) {
            h2.append('0');
            h2.append(h);
        }//if
        else {
            h2.append(h);
        }//else
        if (m < 10) {
            m2.append('0');
            m2.append(m);
        }//if
        else {
            m2.append(m);
        }//else


        if (s < 10) {
            s2.append('0');
            s2.append(s);
        }//if
        else {
            s2.append(s);
        }//else




        h2.append(':');
        h2.append(m2);
        h2.append(':');
        h2.append(s2);
        return h2.toString();
    }//to_time

    String to_time(int h, int m, int s, char sep) {
        StringBuffer h2 = new StringBuffer(), m2 = new StringBuffer(), s2 = new StringBuffer();

        if (h < 10) {
            h2.append('0');
            h2.append(h);
        }//if
        else {
            h2.append(h);
        }//else
        if (m < 10) {
            m2.append('0');
            m2.append(m);
        }//if
        else {
            m2.append(m);
        }//else


        if (s < 10) {
            s2.append('0');
            s2.append(s);
        }//if
        else {
            s2.append(s);
        }//else




        h2.append(sep);
        h2.append(m2);
        h2.append(sep);
        h2.append(s2);
        return h2.toString();

    }//to_time
//===================================================================
    void trt(int year, int mon, int day, int d1, int m1, Float s1, char card1,
            int d2, int m2, Float s2, char card2, int tz, Float p, Float t, Float h, int met,
            int p_faj, int p_shu, int p_dho, int p_ase, int p_mag, int p_ich, int a_faj, int a_ich, int pre) {

        a_faj2 = a_faj;
        a_ich2 = a_ich;
        pre2 = pre;
        Float pri11, pri22, pri33, pri44, pri55, pri66;
//char res[55];
        StringBuffer res = new StringBuffer();

        //Float Julian_date_0hrUTptr=new Float();


        get_sigma(get_Julian_date(year, mon, day, 12, 0, 0));


        pri33 = new Float(pri3(new Float(tz), E, to_deg(d1, m1, s1), card1));
//System.out.println("\npri4=%lf\n"+ pri33);
        //pri33=cut_flo(pri33.toString());
// sig=cut_flo(sig.toString());
// E=cut_flo(E.toString());
// R=cut_flo(R.toString());
        pri44 = new Float(pri4(pri33, to_deg(d2, m2, s2), sig, met, card2));

//cout<<"\np4PP="<<pri33<<endl;
        pri11 = new Float(pri1(pri33, to_deg(d2, m2, s2), card2));
        pri22 = new Float(pri2(pri33, to_deg(d2, m2, s2), card2, p, t, h));
        pri55 = new Float(pri5(pri33, to_deg(d2, m2, s2), card2, p, t, h));
        pri66 = new Float(pri6(pri33, to_deg(d2, m2, s2), card2));

        if (met_sum == 1) {
            p_faj = p_faj + 60;
            p_shu = p_shu + 60;
            p_dho = p_dho + 60;
            p_ase = p_ase + 60;
            p_mag = p_mag + 60;
            p_ich = p_ich + 60;
        }//if met_sum



        P_FAJ = (res.append(para_time(to_time(pri11).toCharArray(), p_faj))).toString();


        res.delete(0, 10);

        P_SOB = (res.append(para_time(to_time(pri22).toCharArray(), p_shu))).toString();
        res.delete(0, 10);

        P_DHO = (res.append(para_time(to_time(pri33).toCharArray(), p_dho))).toString();
        res.delete(0, 10);

        P_ASE = (res.append(para_time(to_time(pri44).toCharArray(), p_ase))).toString();
        res.delete(0, 10);

        P_MAG = (res.append(para_time(to_time(pri55).toCharArray(), p_mag))).toString();
        res.delete(0, 10);

        P_ISH = (res.append(para_time(to_time(pri66).toCharArray(), p_ich))).toString();

    }//trt
    public static String to_abs(String s) {
        if (s == "") {
            return "0";
        }

        int i;

        StringBuffer sb = new StringBuffer();
        i = Integer.parseInt(s);

        i = Math.abs(i);

        sb.append(i);
        return (sb.toString());
    }//to_abs
}
