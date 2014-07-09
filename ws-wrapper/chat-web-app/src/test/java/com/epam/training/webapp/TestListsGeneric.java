package com.epam.training.webapp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by mr.zoom on 05.04.14.
 */
public class TestListsGeneric {

    @Test
    public void testGetLastId() {
        ListsGeneric<Integer> testList = new ListsGeneric<Integer>();
        Integer lastId = Integer.valueOf(777);
        testList.setLastId(lastId);
        Integer expected = Integer.valueOf(777);
        Integer result = testList.getLastId();
        if(!result.equals(expected)){
            fail("Returned not last id!");
        }
    }

    @Test
    public  void  testGetValues(){
        ListsGeneric<String> testList = new ListsGeneric<String>();
        String name1 = "Jhon Doe";
        String name2 = "Jane Doe";
        testList.getValues().add(name1);
        testList.getValues().add(name2);
        assertNotNull(testList.getValues());
        List<String> expectList = new ArrayList<String>();
        expectList.add(name1);
        expectList.add(name2);
        if(expectList.containsAll(testList.getValues())){
            assert true;
        }

    }
}
