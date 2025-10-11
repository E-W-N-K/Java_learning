import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

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


}
