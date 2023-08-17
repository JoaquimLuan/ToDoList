package todolist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TodoList addTask = new TodoList();
        addTask.loadTasksFromFile("todolist.txt");
        verificarAlarmes(addTask);
        String verificador;
        boolean running = true;

        System.out.println("Seja bem vindo ao programa Lista de Tarefas");

        Scanner scanner = new Scanner(System.in);

        try {
            while (running) {
                System.out.println("===== TODO List =====\n" +
                        "1 . Adicionar tarefa\n" +
                        "2 . Listar todas as tarefas\n" +
                        "3 . Remover tarefa\n" +
                        "4 . Excluir TODO List\n" +
                        "5 . Encerrar aplicação");
                verificador = scanner.nextLine();
                switch (verificador) {
                    case "1":
                        adicinaTarefa(scanner, addTask);
                        break;
                    case "2":
                        listaTarefas(addTask, scanner);
                        break;

                    case "3":
                        removeTarefa(addTask, scanner);
                        break;

                    case "4":
                        addTask = criarNovoTodoList();
                        break;

                    case "5":
                        running = finalizaAplicacao();
                        break;
                    default:
                        System.out.println("Digite um número válido");

                }

            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        
        scanner.close();
    }

    private static boolean finalizaAplicacao() {
        boolean running;
        System.out.println("Aplicação encerrada!");
        running = false;
        return running;
    }

    private static TodoList criarNovoTodoList() {
        TodoList addTask;
        System.out.println("Agora você pode criar uma nova TODO List");
        addTask = new TodoList();
        return addTask;
    }

    private static void removeTarefa(TodoList addTask, Scanner scanner) {
        addTask.printList();
        System.out.println("Digite o numero da tarefa para removê - la");
        addTask.removeItem(Integer.parseInt(scanner.nextLine())-1);
        addTask.printList();
        addTask.saveTasksToFile("todolist.txt");
    }

    private static void listaTarefas(TodoList addTask, Scanner scanner) {
        addTask.printList();
        System.out.println("1 . Ver as tarefas por categoria\n" +
                "2 . Ver as tarefas por prioridade\n" +
                "3 . Ver as tarefas por status\n" +
                "4 . Retornar ao menu principal" +
                "Digite uma das opções acima");
        String ordenador = scanner.nextLine();
        switch (ordenador) {
            case "1":
                addTask.sortByCategory();
                addTask.printList();
                break;
            case "2":
                addTask.sortByPrioridade();
                addTask.printList();
                break;
            case "3":
                addTask.sortByStatus();
                addTask.printList();
                break;
            case "4":
                System.out.println("Retornar ao menu principal\n");
                break;
            default:
                System.out.println("Digite uma das opções acima");

        }
    }

    private static void adicinaTarefa(Scanner scanner, TodoList addTask) {
        boolean adicionaTask = true;
        while (adicionaTask) {
            Task taskFull = new Task();

            System.out.println("Digite o nome da tarefa ");
            taskFull.setNome(scanner.nextLine());

            System.out.println("Digite a descrição da tarefa ");
            taskFull.setDescricao(scanner.nextLine());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
            System.out.println("Qual a data de término da tarefa? (dd/MM/yyyy HH:mm)");
            String dataInput = scanner.nextLine();
            try {
                Date data = dateFormat.parse(dataInput);
                taskFull.setData(data);
            } catch (java.text.ParseException e) {
                System.out.println("Data inválida! Digite a data no formato correto (dd/MM/yyyy HH:mm).");
                continue;
            }
            adicionaTask = false;

            System.out.print("Digite a data do alarme (dd/MM/yyyy HH:mm) :");
            String dataAlarmeStr = scanner.nextLine();
            try {
                Date dataAlarme = dateFormat.parse(dataAlarmeStr);
                taskFull.setAlarmeTask(dataAlarme);
                System.out.println("Alarme configurado com sucesso para " + dateFormat.format(dataAlarme));
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Certifique-se de usar o formato dd/MM/yyyy HH:mm.");
            }

            System.out.println("Digite a categoria ");
            taskFull.setCategoria(scanner.nextLine());

            System.out.println("De 1 à 5 qual a prioridade da tarefa ? ");
            taskFull.setPrioridade(scanner.nextLine());

            System.out.println("Qual o Status da tarefa ? \n" +
                    "1 . Todo \n" +
                    "2 . Doing \n" +
                    "3 . Done \n" +
                    "Digite um número acima para definir o status da tarefa");
            taskFull.setStatus(scanner.nextLine());
            addTask.addItem(taskFull);
            addTask.saveTasksToFile("todolist.txt");


            System.out.println("Deseja adicionar uma nova tarefa?(Y/N)");
            boolean verificaTask = true;
            while(verificaTask){
                String task = scanner.nextLine();
                switch (task.toUpperCase()){
                    case "Y":
                        verificaTask = false;
                        break;
                    case "N":
                        adicionaTask = false;
                        verificaTask = false;
                        break;
                    default:
                        System.out.println("Digite Y para SIM e N para NÃO");
                }
            }
        }
    }

    private static void verificarAlarmes(TodoList addTask) {
        Date currentDate = new Date();

        for (Task task : addTask.todoItems) {
            Date alarmeDate = task.getAlarmeTask();
            if (alarmeDate != null && alarmeDate.compareTo(currentDate) <= 0) {
                System.out.println("ALERTA: A tarefa '" + task.getNome() + "' está vencendo ou já venceu!");
            }
        }

    }

}