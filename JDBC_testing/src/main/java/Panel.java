import java.util.Scanner;

public class Panel {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean exit = false;

        while (!exit) {
            System.out.println("Выберите действие:");
            System.out.println("1. Показать всех студентов");
            System.out.println("2. Добавить студента");
            System.out.println("3. Обновить специализацию студента");
            System.out.println("4. Удалить студента");
            System.out.println("5. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера после nextInt

            switch (choice) {
                case 1 -> showAllStudents();
                case 2 -> addStudent();
                case 3 -> updateStudentSpecialization();
                case 4 -> deleteStudent();
                case 5 -> {
                    exit = true;
                    System.out.println("Выход из программы.");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }

    private void showAllStudents() {
        System.out.println(CRUDUtils.getStudentData("SELECT * FROM students"));
    }

    private void addStudent() {
        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию: ");
        String lastName = scanner.nextLine();
        System.out.print("Введите специализацию: ");
        String specialization = scanner.nextLine();
        CRUDUtils.saveStudent(new Student(firstName, lastName, specialization));
        System.out.println("Студент добавлен.");
    }

    private void updateStudentSpecialization() {
        System.out.print("Введите ID студента для обновления: ");
        int idToUpdate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите новую специализацию: ");
        String newSpec = scanner.nextLine();
        CRUDUtils.updateStudents(idToUpdate, newSpec);
        System.out.println("Специализация обновлена.");
    }

    private void deleteStudent() {
        System.out.print("Введите ID студента для удаления: ");
        int idToDelete = scanner.nextInt();
        scanner.nextLine();
        CRUDUtils.deleteStudent(idToDelete);
        System.out.println("Студент удален.");
    }
}
