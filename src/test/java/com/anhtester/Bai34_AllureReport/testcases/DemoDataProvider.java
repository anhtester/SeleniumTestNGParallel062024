package com.anhtester.Bai34_AllureReport.testcases;

import com.anhtester.dataproviders.DataProviderFactory;
import org.testng.annotations.Test;

public class DemoDataProvider {

    @Test(dataProvider = "data_provider_01", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider01(String param1, String param2, String param3) {
        System.out.println(param1 + " -- " + param2 + " -- " + param3);
    }

    @Test(dataProvider = "data_provider_02", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider02(String param1, String param2, String param3) {
        System.out.println(param1 + " -- " + param2 + " -- " + param3);
    }

    @Test(dataProvider = "data_provider_03", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider03(String param1, int param2, int param3) {
        System.out.println(param1 + " -- " + param2 + " -- " + param3);
    }

}
