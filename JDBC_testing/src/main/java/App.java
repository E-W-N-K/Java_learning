import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        System.out.println(CRUDUtils.getStudentData("SELECT * FROM students"));

        CRUDUtils.saveStudent(new Student("Xuy", "Raspizday", "gluboki_minet"));
        CRUDUtils.saveStudent(new Student("Vitaly", "Podushka", "Son_v_obnimku"));
        CRUDUtils.saveStudent(new Student("Marshall", "Gukov", "Politika"));

        System.out.println(CRUDUtils.getStudentData("SELECT * FROM students"));

        CRUDUtils.updateStudents(3, "Economy");

        System.out.println(CRUDUtils.getStudentData("SELECT * FROM students"));

        CRUDUtils.deleteStudent(1);

        System.out.println(CRUDUtils.getStudentData("SELECT * FROM students"));

        CRUDUtils.saveStudent(new Student("Mixail", "Zubenko", "Mafiozy"));

        System.out.println(CRUDUtils.getStudentData("SELECT * FROM students"));
    }
}
