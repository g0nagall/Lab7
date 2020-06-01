package com.company;

public final class Utils {
    public static String getTheBestSubject(Student[] students) {
        double[] sumMarks = new double[students.length];
        for (Student student : students) {
            for (int i = 0; i < student.getSubjects().length; i++) {
                sumMarks[i]+=student.getSubjects()[i].getMark();
            }
        }
        double avrMark = sumMarks[0] / students.length;
        int count = 0;
        for(int i = 1; i < sumMarks.length; i++) {
            if(avrMark < (sumMarks[i] / students.length)) {
                avrMark = sumMarks[i] / students.length;
                count = i;
            }
        }
        return System.out.printf("Subject: %s has the best average mark - %.1f\n",students[0].getSubjects()[count].getNameSubject(),avrMark).toString();
    }
    public static Student getTheBestStudent(Student[] students) {
        Student buffStudent = students[0];
        for(int i = 1; i < students.length;i++) {
            if(buffStudent.averageMark() < students[i].averageMark()) {
                buffStudent = students[i];
            }
        }
        return buffStudent;
    }
}