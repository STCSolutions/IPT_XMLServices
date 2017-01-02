package Com.Stcs.IPPhoneInfo;
import Com.Stcs.IPPhone.Objects.Model.PhoneSeriesModel;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import org.xml.sax.InputSource;

/**
 * This class used to parse "DeviceInformationX" page on IPPhone web Server
 * @author Amonsif
 */
public class IPPhone {

    public static HashMap<String, PhoneSeriesModel> colorPhones=new  HashMap<String, PhoneSeriesModel>();
   public IPPhone(){
    /**
     * Cisco IPPhone 88XX Color and Touch Size 559 x 265
     */
    PhoneSeriesModel cisco88XX=new PhoneSeriesModel();
    cisco88XX.setSeriesName("Cisco_88XX");
    cisco88XX.setPhotoSize("559.265");
    cisco88XX.getPhoneModels().add("8811");
    cisco88XX.getPhoneModels().add("8841");
    cisco88XX.getPhoneModels().add("8845");
    cisco88XX.getPhoneModels().add("8851");
    cisco88XX.getPhoneModels().add("8861");
    cisco88XX.getPhoneModels().add("8865");
        
    colorPhones.put("Cisco_88XX", cisco88XX);
    
     /**
     * Cisco IPPhone 89XX & 99XX Color and Touch Size 498 x 289
     * 8941, 8945,8961, 9951, 9971 
     */
   PhoneSeriesModel cisco89_99XX=new PhoneSeriesModel();
    cisco89_99XX.setSeriesName("Cisco89_99XX");
    cisco89_99XX.setPhotoSize("498.289");
    cisco89_99XX.getPhoneModels().add("8941");
    cisco89_99XX.getPhoneModels().add("8945");
    cisco89_99XX.getPhoneModels().add("8961");
    cisco89_99XX.getPhoneModels().add("9951");
    cisco89_99XX.getPhoneModels().add("9971");
  
   colorPhones.put("Cisco89_99XX", cisco89_99XX);
   
   /**
     * Cisco IPPhone 79XX Color and Touch Size 298 x 144
     * 7941G,  7942G,7961G, ,7962G, 
     */
   PhoneSeriesModel cisco79XXBW=new PhoneSeriesModel();
    cisco79XXBW.setSeriesName("Cisco79XXBW");
    cisco79XXBW.setPhotoSize("298.144");
    cisco79XXBW.getPhoneModels().add("7941");
    cisco79XXBW.getPhoneModels().add("7942");
    cisco79XXBW.getPhoneModels().add("7961");
    cisco79XXBW.getPhoneModels().add("7962");
  
   colorPhones.put("Cisco79XXBW", cisco79XXBW);
     
     
     /**
     * Cisco IPPhone 79XX Color and Touch Size 298 x 156
     * 7945G,7965G
     */
   PhoneSeriesModel cisco79XXA=new PhoneSeriesModel();
    cisco79XXA.setSeriesName("Cisco79XXA");
    cisco79XXA.setPhotoSize("298.156");
    cisco79XXA.getPhoneModels().add("7945");
    cisco79XXA.getPhoneModels().add("7965");
    
   colorPhones.put("Cisco79XXA", cisco79XXA);
 
     
   /**
     * Cisco IPPhone 79XX Color and Touch Size 298 x 168
     * 7970G,7971G-GE, 7975G, IP  Communicator 
     */
   PhoneSeriesModel cisco79XXB=new PhoneSeriesModel();
    cisco79XXB.setSeriesName("Cisco79XXB");
    cisco79XXB.setPhotoSize("298.168");
    cisco79XXB.getPhoneModels().add("7970");
    cisco79XXB.getPhoneModels().add("7971");
    cisco79XXB.getPhoneModels().add("7975");
    cisco79XXB.getPhoneModels().add("Communicator");
   colorPhones.put("Cisco79XXB", cisco79XXB);
     
   /**
     * Cisco IPPhone 792x Color and Touch Size 176 x 140
     */
   PhoneSeriesModel cisco79XXMob=new PhoneSeriesModel();
    cisco79XXB.setSeriesName("Cisco79XXMob");
    cisco79XXB.setPhotoSize("176.140");
    cisco79XXB.getPhoneModels().add("7921");
    cisco79XXB.getPhoneModels().add("7925");
    cisco79XXB.getPhoneModels().add("7926");
    
   colorPhones.put("Cisco79XXMob", cisco79XXMob);
     
   
   
     
   }
    
   
 
     /**
     * Cisco IPPhone  B\W
     */
    public static final int CISCO_7940 = 7940;
    public static final int CISCO_7960 = 7960;
    public static final int CISCO_7985 = 7985;
   
     
     

    /**
     * Use this method to get IPPhone Host Name
     * @return IPPhone Host Name (ex. SEP000DBC7E66F9)
     * @param phoneIP IPPhone IP Address
     * @throws java.lang.Exception Exception
     */
    public static String getPhoneHostName(String phoneIP) throws Exception {

        try {
            String url = "http://" + phoneIP + "/DeviceInformationX";
            Vector v = parseURL(url, "HostName");
            return (String) v.get(0);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Use this method to get IPPhone Directory Number
     * @return IPPhone Phone DN
     * @param phoneIP IPPhone IP Address
     * @throws java.lang.Exception Exception
     */
    public static String getPhoneDN(String phoneIP) throws Exception {

        try {
            String url = "http://" + phoneIP + "/DeviceInformationX";
            Vector v = parseURL(url, "phoneDN");
            return (String) v.get(0);
        } catch (Exception ex) {
            throw ex;
        }

    }

    /**
     * Usd this method to get IPPhone Type
     * @return int represents IPPhone Type
     * @param phoneIP IPPhone IP Address
     * @throws java.lang.Exception Exception
     */
    public static String getPhoneType(String phoneIP) throws Exception {
        try {
            
        
        String url = "http://" + phoneIP + "/DeviceInformationX";
        Vector v = parseURL(url, "modelNumber");
        String model = (String) v.get(0);
   
        Iterator it = colorPhones.entrySet().iterator();
        while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        PhoneSeriesModel modelSeries;
            modelSeries = (PhoneSeriesModel)pair.getValue();
       
        for ( int i=0; i<modelSeries.getPhoneModels().size();i++) {
           if(model.contains(modelSeries.getPhoneModels().get(i)))
           {
               return modelSeries.getPhoneModels().get(i);
           }
            
        }
    }
    } catch (Exception e) {
         return "BWPhone";
        }
    return "BWPhone";
        
    }
    
   

    /**
     * Use this method to get IPPhone Mac Address
     * @param phoneIP IPPhone IP Address
     * @throws java.lang.Exception Exception
     * @return IPPhone Mac Address
     */
    public static String getPhoneMacAddress(String phoneIP) throws Exception {
        try {
            String url = "http://" + phoneIP + "/DeviceInformationX";
            Vector v = parseURL(url, "MACAddress");
            return (String) v.get(0);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Use this method to get IPPhone Service Url
     * @param phoneIP IPPhone IP Address
     * @throws java.lang.Exception Exception
     * @return IPPhone Service Url String
     */
    public static String getPhoneServiceURL(String phoneIP) throws Exception {
        try {
            String url = "http://" + phoneIP + "/NetworkConfigurationX";
            Vector v = parseURL(url, "ServicesURL");
            return (String) v.get(0);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Use this method to check if the IPPhone is colored or not
     * @param phoneIP IPPhone IP Address
     * @return True: if IPPhone is colored
     * False: if else
     * @throws java.lang.Exception Exception
     */
    public static boolean isColorPhone(String phoneIP) throws Exception {

        try {
            
        
        String model = getPhoneType(phoneIP);
         Iterator it = colorPhones.entrySet().iterator();
         while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        PhoneSeriesModel modelSeries;
            modelSeries = (PhoneSeriesModel)pair.getValue();
       
        for ( int i=0; i<modelSeries.getPhoneModels().size();i++) {
           if(model.equals(modelSeries.getPhoneModels().get(i)))
           {
               return true;
           }
            
        }
    }
      } catch (Exception e) {
        }
        return  false;
    }
    
    
     
  /**
     * Use this method to check if the IPPhone is colored or not
     * @param phoneIP IPPhone IP Address
     * @return PhoneSeriesModel:Which Contains Supported Phones Models,ScreenSize,IF Color
     * @throws java.lang.Exception Exception
     */
    public static PhoneSeriesModel getPhoneDetails(String phoneIP) throws Exception {
        
         PhoneSeriesModel bwPhone=new PhoneSeriesModel();
         bwPhone.setIsColorPhone(false);
        try {
            
        
        String model = getPhoneType(phoneIP);
         Iterator it = colorPhones.entrySet().iterator();
         
         while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        PhoneSeriesModel modelSeries;
            modelSeries = (PhoneSeriesModel)pair.getValue();
       
        for ( int i=0; i<modelSeries.getPhoneModels().size();i++) {
           if(model.equals(modelSeries.getPhoneModels().get(i)))
           {
               return modelSeries;
           }
            
        }
    }
         
      } catch (Exception e) {
         
        }
        return  bwPhone;
    }
    private static Vector parseURL(String url, String tagName) throws Exception {
        try {
            CiscoParser xmlParser = new CiscoParser();
            Vector v = xmlParser.list(new InputSource(new URL(url).openStream()), tagName);
            return v;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public static void main(String argv[]) {
        try {

            System.out.println(IPPhone.getPhoneHostName("192.168.1.120"));
            System.out.println(IPPhone.isColorPhone("192.168.1.160"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
