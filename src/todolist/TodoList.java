package todolist;

import java.util.*;
import java.io.*;

public class TodoList {
    public ArrayList<Tarefa> todoItems;

    public TodoList() {
        this.todoItems = new ArrayList<>();
    }

    public void addItem(Tarefa item) {
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

    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Tarefa tarefa : this.todoItems) {
                writer.println(tarefa.toFileFormat());
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            this.todoItems.clear();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Tarefa tarefa = Tarefa.fromFileFormat(line);
                this.todoItems.add(tarefa);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }
}
