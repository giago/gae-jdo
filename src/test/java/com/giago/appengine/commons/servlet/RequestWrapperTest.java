package com.giago.appengine.commons.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @author luigi.agosti
 *
 */
public class RequestWrapperTest {


    @Test
    public void shouldGetBooleanValueFromRequestParametersWhenPresent() {
        String testParamName1 = "myParam1";
        Map<String, String[]> parameterMap = new HashMap<String, String[]>();
        parameterMap.put(testParamName1, Arrays.asList("true").toArray(new String[1]));
        String testParamName2 = "myParam2";
        parameterMap.put(testParamName2, Arrays.asList("false").toArray(new String[1]));

        RequestWrapper req = new RequestWrapper(parameterMap);

        assertTrue(req.getParameterAsBoolean(testParamName1, false));
        assertFalse(req.getParameterAsBoolean(testParamName2, true));
    }

    @Test
    public void shouldGetLongValueFromRequestParametersWhenPresent() {
    	String testParamName1 = "param";
    	Map<String, String[]> parameterMap = new HashMap<String, String[]>();
    	parameterMap.put(testParamName1, Arrays.asList("1").toArray(new String[1]));
    	
    	RequestWrapper req = new RequestWrapper(parameterMap);
    	assertEquals(Long.valueOf(1), req.getParameterAsLong(testParamName1));
    }

    @Test
    public void shouldGetLongValueIfTheValueIsNotCorrrect() {
    	String testParamName1 = "param";
    	Map<String, String[]> parameterMap = new HashMap<String, String[]>();
    	parameterMap.put(testParamName1, Arrays.asList("q1e").toArray(new String[1]));
    	
    	RequestWrapper req = new RequestWrapper(parameterMap);
    	
    	assertNull(req.getParameterAsLong(testParamName1));
    }

    @Test
    public void shouldGetDefaultBooleanValueWhenNotPresent() {
    	Map<String, String[]> parameterMap = new HashMap<String, String[]>();
    	RequestWrapper req = new RequestWrapper(parameterMap);
        assertFalse(req.getParameterAsBoolean("myparam", false));
    }

    @Test
    public void shouldGetStringValueFromRequestParametersWhenPresent() {
    	Map<String, String[]> parameterMap = new HashMap<String, String[]>();
        String testParamName1 = "myParam1";
        parameterMap.put(testParamName1, Arrays.asList("String1").toArray(new String[1]));
        String testParamName2 = "myParam2";
        parameterMap.put(testParamName2, Arrays.asList("String2").toArray(new String[1]));

        RequestWrapper req = new RequestWrapper(parameterMap);

        assertEquals("String1", req.getParameterAsString(testParamName1));
        assertEquals("String2", req.getParameterAsString(testParamName2));
    }

    @Test
    public void shouldGetStringValueFromRequestParametersWhenPresentDefaultValue() {
    	Map<String, String[]> parameterMap = new HashMap<String, String[]>();
    	RequestWrapper req = new RequestWrapper(parameterMap);
        assertEquals("String2", req.getParameterAsString("noparam", "String2"));
    }

    @Test
    public void shouldGetStringValueFromRequestParametersWhenPresentDefaultNullValue() {
    	Map<String, String[]> parameterMap = new HashMap<String, String[]>();
    	RequestWrapper req = new RequestWrapper(parameterMap);
        assertNull(req.getParameterAsString("noparam"));
    }
    
    @Test
    public void shouldGetStringsFromACommaSeparatedParameter() {
    	Map<String, String[]> parameterMap = new HashMap<String, String[]>();
    	parameterMap.put("commaParameter", Arrays.asList("String1,String2,String3").toArray(new String[1]));
    	RequestWrapper req = new RequestWrapper(parameterMap);
        assertEquals(Arrays.asList("String1", "String2", "String3"), req.getParameterAsStrings("commaParameter"));
    }

    @Test
    public void shouldGetDateFromParameters() {
    	Map<String, String[]> parameterMap = new HashMap<String, String[]>();
    	parameterMap.put("date", Arrays.asList("10/01/2010").toArray(new String[1]));
    	RequestWrapper req = new RequestWrapper(parameterMap);
        Date date = req.getParameterAsDate("date");
        
        assertNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.format(date);
        
        assertEquals(sdf.format(date), "10/01/2010");
    }
    

}