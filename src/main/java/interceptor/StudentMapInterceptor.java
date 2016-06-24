package interceptor;

import com.hazelcast.map.MapInterceptor;
import data.Student;

/**
 * Created by bijoy on 24/6/16.
 */
public class StudentMapInterceptor implements MapInterceptor {
    @Override
    public Object interceptGet(Object value) {
        return value;
    }

    @Override
    public void afterGet(Object value) {

    }

    @Override
    public Object interceptPut(Object oldValue, Object newValue) {
        Student student = (Student)newValue;
        student.setTotalMarks();
        return newValue;
    }

    @Override
    public void afterPut(Object value) {
    }

    @Override
    public Object interceptRemove(Object removedValue) {
        return removedValue;
    }

    @Override
    public void afterRemove(Object value) {

    }
}
