package instance;


import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import data.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Created by bijoy on 15/6/16.
 */
public class Client {
    public static void main(String[] args){
        Random random = new Random();
        HazelcastInstance hazelcastInstance = ClientInstance.instance();
        IMap<Integer, Student> studentIMap = hazelcastInstance.getMap(System.getProperty("user.name"));
        Student student;
        for(int i = 0; i < 30; i++ ){
            student = new Student("NAME "+i, random.nextInt(100), random.nextInt(100));
            studentIMap.put(i, student);
        }
        Collection<Student> students = studentIMap.values();
        Collections.sort((List)students);
        System.out.println(students);
    }

}
