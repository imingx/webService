package server;

import javax.jws.WebService;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebService
public class Student {

    public boolean dropCourse(String []dropArg) {
        String stu_id = dropArg[0];
        String course_id = dropArg[1];
        MySQL sql = new MySQL();

        return sql.sql_dropCourse(stu_id, course_id);
    }

    public boolean changeStudentPassword(String []chanPassArg) {
        String stu_id = chanPassArg[0];
        String oldPassword = chanPassArg[1];
        String newPassword = chanPassArg[2];
        MySQL sql = new MySQL();
        return sql.sql_changeStudentPassword(stu_id, oldPassword, newPassword);
    }

    public boolean selectCourse(String []selcetArg) {
        String stu_id = selcetArg[0];
        String course_id = selcetArg[1];
        MySQL sql = new MySQL();

        return sql.sql_selectCourse(stu_id, course_id);
    }

    public String queryStudentCourse(String student_id) {
        MySQL sql = new MySQL();
        ResultSet rs = sql.sql_queryStudentCourse(student_id);

        String ans = "";

        try {
            while (rs.next()) {
                String course_id = rs.getString("id");
                String name = rs.getString("name");
                ans += "," + course_id;
                ans += "," + name;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String getName(String id) {
        MySQL sql = new MySQL();
        ResultSet rs = sql.sql_findStudent(id);
        try {
            if (rs.next()) {
                String name = rs.getString("name");
                return name;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String queryAllCourse(int nothing) {
        MySQL sql = new MySQL();
        ResultSet rs = sql.sql_queryAllCourse();

        String ans = "";

        try {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String teacherName = rs.getString("tn");
                ans += "," + id;
                ans += "," + name;
                ans += "," + teacherName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public boolean studentLogin(String logArg[]) {
        String id = logArg[0];
        String password = logArg[1];
        MySQL sql = new MySQL();
        ResultSet rs = sql.sql_findStudent(id);
        try {
            if (rs.next()) {
                String pw = rs.getString("password");
                if (!password.equals(pw)) {
                    System.out.println("????????????");
                    return false;
                }
            } else {
                System.out.println("????????????");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("????????????");
        return true;
    }

    public String studentQuery() {
        MySQL sql = new MySQL();
        ResultSet rs = sql.sql_queryStudent();
        String ans = "";

        try {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                ans += "," + id;
                ans += "," + name;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("????????????????????????" + ans);
        return ans;
    }


    public boolean studentRegister(String regArg[]) {
        String id = regArg[0];
        String password = regArg[1];
        String name = regArg[2];

        MySQL sql = new MySQL();
        ResultSet rs = sql.sql_findStudent(id);
        try {
            if (rs.next()) {
                System.out.println("????????????:" + id);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.sql_registerStudent(id, password, name);
        System.out.println("????????????:" + id);
        return true;
    }

}
