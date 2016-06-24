package data;

import java.io.Serializable;

/**
 * Created by bijoy on 24/6/16.
 */
public class Student implements Serializable, Comparable<Student>{
    final static long serialVersionUID = 1l;
    private String name;

    private int marksInMath;
    private int marksInComp;
    private double totalMarks;

    public Student(){}

    public Student(String name, int marksInMath, int marksInComp) {
        this.name = name;
        this.marksInMath = marksInMath;
        this.marksInComp = marksInComp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarksInMath() {
        return marksInMath;
    }

    public void setMarksInMath(int marksInMath) {
        this.marksInMath = marksInMath;
    }

    public int getMarksInComp() {
        return marksInComp;
    }

    public void setMarksInComp(int marksInComp) {
        this.marksInComp = marksInComp;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    @Override
    public int compareTo(Student s) {
        if(this.getTotalMarks() == s.getTotalMarks()){
            return 0;
        }else if(this.getTotalMarks() > s.getTotalMarks()){
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "------------------\n" +
               "Student{" +
                "name='" + name + '\'' +
                ", marksInMath=" + marksInMath +
                ", marksInComp=" + marksInComp +
                ", totalMarks=" + totalMarks +
                "}\n";
    }
}
