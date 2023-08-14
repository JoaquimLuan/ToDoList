package todolist;

import java.util.ArrayList;
import java.util.Collections;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class TodoList {

    ArrayList<Task> todoItems;

    public TodoList() {

        this.todoItems = new ArrayList<>();
    }

    public void addItem(Task item) {
        this.todoItems.add(item);
    }

    public void removeItem(int index) {

        this.todoItems.remove(index);
    }

    public void sortByStatus() {
        Collections.sort(this.todoItems, (t1, t2) -> t1.getStatus().compareTo(t2.getStatus()));
        ;
    }

    public void sortByPrioridade() {
        Collections.sort(this.todoItems, (t1, t2) -> t1.getPrioridade().compareTo(t2.getPrioridade()));
        ;
    }

    public void sortByCategory() {
        Collections.sort(this.todoItems, (t1, t2) -> t1.getCategoria().compareTo(t2.getCategoria()));
    }

    public void printList() {
        for (int i = 0; i < this.todoItems.size(); i++) {
            System.out.println((i + 1) + ". " + this.todoItems.get(i));
        }
    }

    public void saveTasksToFile(String fileName) {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter writer = new PrintWriter(new FileWriter(file));

            for (Task task : this.todoItems) {
                writer.println(task.toFileFormat());
            }

            writer.close();
            System.out.println("Tarefas salvas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar as tarefas: " + e.getMessage());
        }
    }

    public void loadTasksFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            this.todoItems.clear();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Task.fromFileFormat(line);
                this.todoItems.add(task);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

}
