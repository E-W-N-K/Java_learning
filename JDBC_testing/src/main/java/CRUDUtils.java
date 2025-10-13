import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

    private static final String INSERT_STUDENT = "INSERT INTO students (name, surname, course_name) VALUES (?, ?, ?);";
    private static final String UPDATE_STUDENT = "UPDATE students SET course_name = ? WHERE id = ?;";
    private static final String DELETE_STUDENT = "DELETE FROM students WHERE id = ?;";


    public static List<Student> getStudentData(String query){
        List<Student> students = new ArrayList<Student>();

        /*
        try{
        открыть поток
        взять данные
        закрыть поток
        } catch {
        ловим ошибку.
        если она появляется, то поток может не закрыться
        для этого используется блок файнали
        } finally{
        закрываем поток в люьом случае
        }

        этот вариант хороший, но более краткий с таким же функционалом реализовани ниже
         */
        try (Connection connection = DBUtils.getConnection();
        /*   Statement - можно отправить запрос, но без параметров (не подходит)
             PreparedStatement - можно добавить параметры
             CallableStatement - есть возможность получить значение из хранимых процедур (запрос в ии есть) (тоже нам не подходит)
        */
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ) {
            ResultSet resultSet = preparedStatement.executeQuery(); //отправляя запрос мы должны сохранять в переменную типа ResulSet
            //ResultSet - коллекция

            //получаем данные и работаем с ними
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String course_name = resultSet.getString("course_name");

                students.add(new Student(id, name, surname, course_name));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    //этот метод должен быть void. возвращает коллекцию студентов для проверки самого себя
    public static List<Student> saveStudent(Student student){
        List<Student> students = new ArrayList<>();


        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
        ) {
            //установим значения через препаретСтэйтмент для вопросительных знаков
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCourse_name());

            preparedStatement.executeUpdate(); //надо, чтобы значения в ПрепСт обновились



            //далее необязательная часть кода
            //делается для просмотра результата
            //убирая эту часть кода, метод надо делать void
            PreparedStatement allStudents = connection.prepareStatement("SELECT * FROM students");
            ResultSet resultSet = allStudents.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String course_name = resultSet.getString("course_name");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public static void updateStudents(int student_id, String course_name){

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT);
        ) {
            //установим значения через препаретСтэйтмент для вопросительных знаков
            preparedStatement.setString(1, course_name);
            preparedStatement.setInt(2, student_id);

            preparedStatement.executeUpdate(); //надо, чтобы значения в ПрепСт обновились

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteStudent(int student_id){

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT);
        ) {
            preparedStatement.setInt(1, student_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
