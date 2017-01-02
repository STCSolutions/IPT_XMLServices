package Com.Stcs.IPPhone.Objects;

/**
 * Class represent InputItem in CiscoIPPhoneInput XML object
 * @author Amonsif
 */
public class InputItem {

    /**
     * InputFlag: Plain ASCII text—use the DTMF keypad to enter text that consists of uppercase and lowercase letters, numbers, and special characters.
     */
    public static String ASCII = "A";
    /**
     * InputFlag:Telephone number—enter only DTMF digits for this field. The acceptable input includes numbers, #, and *.
     */
    public static String TELEPHONE = "T";
    /**
     * InputFlag:Numeric—enter numbers as the only acceptable input.
     */
    public static String NUMERIC = "N";
    /**
     * InputFlag:Password field—enter individual characters using the
     * standard keypad-repeat entry mode. The system automatically converts
     * accepted characters into an asterisk, keeping the entered value private.
     */
    public static String PASSWORD = "AP";
    /**
     * InputFlag:Uppercase—enter uppercase letters as the only acceptable input.
     */
    public static String UPPERCASE = "U";
    /**
     * InputFlag:Lowercase—enter lowercase letters as the only acceptable input.
     */
    public static String LOWERCASE = "L";
    /**
     * InputFlag:Equation—enter numbers and special math symbols.
     */
    public static String EQUATION = "E";
    private String displayName;
    private String queryStringParam;
    private String inputFlags;
    private String defaultValue;

    /**
     * Create a new instance of Input class
     * @param displayName Display name of the input item
     * @param queryStringParam QueryStringParam which well passed in submit url.
     * @param inputFlags InputFlag of the input character in input item
     * @param defaultValue Default value of the input character of the input item
     */
    public InputItem(String displayName, String queryStringParam, String inputFlags, String defaultValue) {
        setDisplayName(displayName);
        setQueryStringParam(queryStringParam);
        setInputFlags(inputFlags);
        setDefaultValue(defaultValue);
    }

    /**
     * Get DisplayName
     * @return DisplayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * set DisplayName
     * @param displayName DisplayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Get QueryStringParm
     * @return QueryStringParm
     */
    public String getQueryStringParam() {
        return queryStringParam;
    }

    /**
     * Set QueryStringParm
     * @param queryStringParam QueryStringParm
     */
    public void setQueryStringParam(String queryStringParam) {
        this.queryStringParam = queryStringParam;
    }

    /**
     * Get inputFlags
     * @return inputFlags
     */
    public String getInputFlags() {
        return inputFlags;
    }

    /**
     * Set inputFlags
     * @param inputFlags inputFlags
     */
    public void setInputFlags(String inputFlags) {
        this.inputFlags = inputFlags;
    }

    /**
     * Get DefaultValue
     * @return DefaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Set DefaultValue
     * @param defaultValue DefaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
