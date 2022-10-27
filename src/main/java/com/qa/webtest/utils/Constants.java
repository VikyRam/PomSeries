package com.qa.webtest.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final String LOGIN_PAGE_TITLE="Account Login";
    public static final String ACCOUNTS_PAGE_TITLE="My Account";
    public static final String ACCOUNTS_PAGE_HEADER="My Accountwe";
    public static final String LOGIN_PAGE_URL="route=account/login";

    public static final String ERROR_MESG_TEXT=" Warning: No match for E-Mail Address and/or Password.";

    public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";

    public static final String RESGISTRATION_SHEET_NAME ="Registration";

    public static final int IMAC_IMAGE_COUNT=3;
    public static final int MACBOOKPRO_IMAGE_COUNT=3;
    public static final int MACBOOKAIR_IMAGE_COUNT=3;

    public static final int DEFAULT_TIME_OUT=7;

    public static List<String> getExpectedSectList(){
        List<String> expectedSectionList=new ArrayList<String>();
        expectedSectionList.add("My Account");
        expectedSectionList.add("My Orders");
        expectedSectionList.add("My Affiliate Account");
        expectedSectionList.add("Newsletter");
        return expectedSectionList;
    }


}
