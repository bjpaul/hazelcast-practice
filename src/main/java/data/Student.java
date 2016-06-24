package data;

import java.io.Serializable;

/**
 * Created by bijoy on 24/6/16.
 */
public class Student implements Serializable, Comparable<Student>{
    final static long serialVersionUID = 1l;
    private int rank;
    private String name;
    private int totalMarks;

    public Student(){}

    public Student(String name, int totalMarks) {
        this.name = name;
        this.totalMarks = totalMarks;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }


    @Override
    public int compareTo(Student s) {
        if(this.getRank() == s.getRank()){
            return 0;
        }else if(this.getRank() > s.getRank()){
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "~~~~~~~~~~~~~~~~~~~~~~~" +
               "\nStudent{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", totalMarks=" + totalMarks +
                "}\n";
    }
}
