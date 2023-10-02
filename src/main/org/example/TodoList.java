package main.org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

public class TodoList {

    public ArrayList<Task> getTodoItems() {
        return todoItems;
    }

    public ArrayList<Task> todoItems;

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
        Collections.sort(this.todoItems, (t1, t2) -> Integer.compare(t1.getStatus(), t2.getStatus()));
    }

    public void sortByPrioridade() {
        Collections.sort(this.todoItems, (t1, t2) -> t1.getPrioridade().compareTo(t2.getPrioridade()));
    }

    public void sortByCategory() {
        Collections.sort(this.todoItems, (t1, t2) -> t1.getCategoria().compareTo(t2.getCategoria()));
    }

    public void printList() {
        int index = 1;
        for (Task task : this.todoItems) {
            System.out.println(index + ". " + task);
            index++;
        }
    }

    public static Task fromFileFormat(String line) {

        String[] parts = line.split("\\|");
        Task tarefa = new Task();
        tarefa.setNome(parts[0]);
        tarefa.setDescricao(parts[1]);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
        try {
            Date data = dateFormat.parse(parts[2]);
            tarefa.setData(data);
        } catch (ParseException e) {
            System.out.println("Erro ao fazer o parsing da data: " + e.getMessage());
        }
        try {
            Date dataAlarme = dateFormat.parse(parts[3]);
            tarefa.setAlarmeTask(dataAlarme);
        } catch (ParseException e) {
            System.out.println("Erro ao fazer o parsing da data de alarme: " + e.getMessage());
        }
        tarefa.setCategoria(parts[4]);
        tarefa.setPrioridade(parts[5]);
        try {
            int status = Integer.parseInt(parts[6]);
            tarefa.setStatus(status);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter o status para inteiro: " + e.getMessage());
        }

        return tarefa;
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
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            this.todoItems.clear();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = fromFileFormat(line);
                this.todoItems.add(task);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }


}