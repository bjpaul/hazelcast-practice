package instance;

import com.hazelcast.map.AbstractEntryProcessor;
import data.Student;

import java.util.Map;

/**
 * Created by bijoy on 24/6/16.
 */
public class EmployeeRaiseEntryProcessor extends AbstractEntryProcessor<String, Student> {
    @Override
    public Object process(Map.Entry<String, Student> entry) {
        Student value = entry.getValue();
        value.setTotalMarks((value.getMarksInComp()+value.getMarksInMath())/2);
        entry.setValue(value);
        return value;
    }
}
