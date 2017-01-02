/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.Univox.pushtophone;

/**
 * The <code>Text2Base64</code> class converts a standard ASCII text string into a Base64-encoded string.
 * Base64 encoding is used for many purposes, especially by HTTP. One common use is encoding usernames and
 * passwords for HTTP Basic Authentication.
 *
 * @author  kstearns
 * @version 1.0  (March 2002)
 */
public class Text2Base64
{

    public static String getBase64Sun(String text)
    {
        // Convert a byte array to base64 string
        //byte[] buf = new byte[]{0x12, 0x23};
        //String s = new sun.misc.BASE64Encoder().encode(buf);

        // Convert base64 string to a byte array
        String out = "";
        try
        {
        byte[] buf = new sun.misc.BASE64Decoder().decodeBuffer(text);
        
        out = new String(buf);
        }catch(Exception ex)
        {
            return "";
        }
        return out;
    }

    /**
     * This method accepts an ASCII text string and returns the Base64-encoded version of that string.
     * This method is static which allows the encoding to be performed without instantiating a <code>Text2Base64</code> object.
     * @param text The ASCII text string to be Base64 encoded
     * @return The Base64-encoded string
     */
    public static String getBase64Arr(String text)
    {
        int i = 0;
        int j = 0;

        String base64key = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        //StringBuffer base64string = new StringBuffer();
        Character[] test = new Character[50];
        char text0, text1, text2;

        for (i = 0; i < text.length();)
        {
            text0 = text.charAt(i);

            //test = new Character[j + 4];
            //base64string.setLength(j+4);

            test[j] = base64key.charAt((text0 & 252) >> 2);
            //base64string.setCharAt(j, base64key.charAt((text0 & 252) >> 2));
            if ((i + 1) < text.length())
            {
                text1 = text.charAt(i + 1);
                test[j + 1] = base64key.charAt(((text0 & 3) << 4) | ((text1 & 240) >> 4));
                //base64string.setCharAt(j+1, base64key.charAt(((text0 & 3) << 4) | ((text1 & 240) >> 4)));
                if ((i + 2) < text.length())
                {
                    text2 = text.charAt(i + 2);
                    test[j + 2] = base64key.charAt(((text1 & 15) << 2) | ((text2 & 192) >> 6));
                    //base64string.setCharAt(j+2, base64key.charAt(((text1 & 15) << 2) | ((text2 & 192) >> 6)));

                    test[j + 3] = base64key.charAt((text2 & 63));
                //base64string.setCharAt(j+3, base64key.charAt((text2 & 63)));
                }
                else
                {
                    test[j + 2] = base64key.charAt(((text1 & 15) << 2));
                    //base64string.setCharAt(j+2, base64key.charAt(((text1 & 15) << 2)));

                    test[j + 3] = (char) 61;
                //base64string.setCharAt(j+3, (char)61);
                }
            }
            else
            {
                test[j + 3] = base64key.charAt(((text0 & 3) << 4));
                //base64string.setCharAt(j+1, base64key.charAt(((text0 & 3) << 4)));

                test[j + 2] = (char) 61;
                //base64string.setCharAt(j+2, (char)61);

                test[j + 3] = (char) 61;
            //base64string.setCharAt(j+3, (char)61);
            }

            i += 3;
            j += 4;

        }
        System.out.println(test.length);
        String output = "";
        for (int k = 0; k < test.length; k++)
        {
            Character character = test[k];
            if(character!= null)
            output += character;

        }

        return output;
    //return (base64string.toString());
    }

    /**
     * This method accepts an ASCII text string and returns the Base64-encoded version of that string.
     * This method is static which allows the encoding to be performed without instantiating a <code>Text2Base64</code> object.
     * @param text The ASCII text string to be Base64 encoded
     * @return The Base64-encoded string
     */
    public static String getBase64(String text)
    {
        int i = 0;
        int j = 0;

        String base64key = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        StringBuffer base64string = new StringBuffer();
        char text0, text1, text2;

        for (i = 0; i < text.length();)
        {
            text0 = text.charAt(i);

            base64string.setLength(j + 4);
            base64string.setCharAt(j, base64key.charAt((text0 & 252) >> 2));
            if ((i + 1) < text.length())
            {
                text1 = text.charAt(i + 1);
                base64string.setCharAt(j + 1, base64key.charAt(((text0 & 3) << 4) | ((text1 & 240) >> 4)));
                if ((i + 2) < text.length())
                {
                    text2 = text.charAt(i + 2);
                    base64string.setCharAt(j + 2, base64key.charAt(((text1 & 15) << 2) | ((text2 & 192) >> 6)));
                    base64string.setCharAt(j + 3, base64key.charAt((text2 & 63)));
                }
                else
                {
                    base64string.setCharAt(j + 2, base64key.charAt(((text1 & 15) << 2)));
                    base64string.setCharAt(j + 3, (char) 61);
                }
            }
            else
            {
                base64string.setCharAt(j + 1, base64key.charAt(((text0 & 3) << 4)));
                base64string.setCharAt(j + 2, (char) 61);
                base64string.setCharAt(j + 3, (char) 61);
            }

            i += 3;
            j += 4;

        }

        return (base64string.toString());
    }
}
