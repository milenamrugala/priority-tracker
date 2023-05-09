package pl.milenamrugala;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriorityTracker {

    private static final String[] OPTIONS = {"list", "add", "remove", "exit"};
    private static final String FILENAME = "priority-tracker.txt";
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        boolean running = true;
        while (running) {
            printOptions(OPTIONS);
            String input = scanner.nextLine();
            switch (input) {
                case "list":
                    listTasks();
                    break;
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask();
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        saveTasks();
        System.out.println("Exiting...");
    }

    public static void printOptions(String[] array) {
        System.out.println("Please select an option:");
        for (String option : array) {
            System.out.println(option);
        }
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
            return;
        }
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + ". " + task.toString());
            index++;
        }
    }

    public static void addTask() {
        System.out.println("Enter task description:");
        String description = scanner.nextLine();
        System.out.println("Enter priority (very high, high, medium, low):");
        String priority = scanner.nextLine();
        System.out.println("Enter start date (YYYY-MM-DD):");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter end date (YYYY-MM-DD):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        Task task = new Task(description, priority, startDate, endDate);
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    public static void removeTask() {
        System.out.println("Enter task index:");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.remove(index);
            System.out.println("Task removed: " + task);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public static void loadTasks() {
        try {
            File file = new File(FILENAME);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String description = Color.BLUE + parts[0] + Color.RESET;
                    String priority = parts[1];
                    LocalDate startDate = LocalDate.parse(parts[2]);
                    LocalDate endDate = LocalDate.parse(parts[3]);

                    Task task = new Task(description, priority, startDate, endDate);
                    tasks.add(task);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTasks() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
            for (Task task : tasks) {
                writer.write(task.getDescription() + "," + task.getPriority() + "," + task.getStartDate() + "," +
                        task.getEndDate() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

