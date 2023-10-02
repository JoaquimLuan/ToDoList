package main.org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                        adicionaTarefa(scanner, addTask);
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

    public static void removeTarefa(TodoList addTask, Scanner scanner) {
        addTask.printList();
        System.out.println("Digite o numero da tarefa para removê - la");
        addTask.removeItem(Integer.parseInt(scanner.nextLine())-1);
        addTask.printList();
        addTask.saveTasksToFile("todolist.txt");
    }

    static void listaTarefas(TodoList addTask, Scanner scanner) {
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

    public static void adicionaTarefa(Scanner scanner, TodoList addTask) {
        boolean adicionaTask = true;
        while (adicionaTask) {
            Task taskFull = new Task();

            System.out.println("Digite o nome da tarefa ");
            String nomeTarefa;
            boolean nomeTarefaValido = false;
            while (!nomeTarefaValido) {
                nomeTarefa = scanner.nextLine();
                String regex = "^.{1,20}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(nomeTarefa);
                if (matcher.matches()) {
                    taskFull.setNome(nomeTarefa);
                    nomeTarefaValido = true;
                } else {
                    System.out.println("Nome de tarefa inválido. Deve ter até 20 caracteres. Tente novamente:");
                }
            }

            System.out.println("Digite a descrição da tarefa ");
            String descricaoTarefa;
            boolean descricaoTarefaValida = false;
            while (!descricaoTarefaValida) {
                descricaoTarefa = scanner.nextLine();
                String regexDescricao = "^.{1,50}$";
                Pattern patternDescricao = Pattern.compile(regexDescricao);
                Matcher matcherDescricao = patternDescricao.matcher(descricaoTarefa);
                if (matcherDescricao.matches()) {
                    taskFull.setDescricao(descricaoTarefa);
                    descricaoTarefaValida = true;
                } else {
                    System.out.println("Descrição de tarefa inválida. Deve ter até 50 caracteres. Tente novamente:");
                }
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
            Date data = null;
            while (data == null) {
                System.out.println("Qual a data de término da tarefa? (dd/MM/yyyy HH:mm)");
                String dataInput = scanner.nextLine();
                try {
                    data = dateFormat.parse(dataInput);
                    taskFull.setData(data);
                } catch (java.text.ParseException e) {
                    System.out.println("Data inválida! Digite a data no formato correto (dd/MM/yyyy HH:mm). Tente novamente:");
                }
            }
            adicionaTask = false;
            Date dataAlarme = null;

            while (dataAlarme == null) {
                System.out.print("Digite a data do alarme (dd/MM/yyyy HH:mm) :");
                String dataAlarmeStr = scanner.nextLine();
                try {
                    dataAlarme = dateFormat.parse(dataAlarmeStr);
                    taskFull.setAlarmeTask(dataAlarme);
                    System.out.println("Alarme configurado com sucesso para " + dateFormat.format(dataAlarme));
                } catch (ParseException e) {
                    System.out.println("Formato de data inválido. Certifique-se de usar o formato dd/MM/yyyy HH:mm. Tente novamente:");
                }
            }

            System.out.println("Digite a categoria ");
            String categoriaTarefa;
            boolean categoriaTarefaValida = false;
            while (!categoriaTarefaValida) {
                categoriaTarefa = scanner.nextLine();
                String regexCategoria = "^.{1,20}$";
                Pattern patternCategoria = Pattern.compile(regexCategoria);
                Matcher matcherCategoria = patternCategoria.matcher(categoriaTarefa);
                if (matcherCategoria.matches()) {
                    taskFull.setCategoria(categoriaTarefa);
                    categoriaTarefaValida = true;
                } else {
                    System.out.println("Categoria de tarefa inválida. Deve ter até 20 caracteres. Tente novamente:");
                }
            }

            System.out.println("De 1 à 5 qual a prioridade da tarefa ? ");
            String prioridadeInput = scanner.nextLine();

            while (!prioridadeInput.matches("^[1-5]$")) {
                System.out.println("Prioridade inválida. Digite um número de 1 a 5.");
                System.out.println("De 1 à 5 qual a prioridade da tarefa ? ");
                prioridadeInput = scanner.nextLine();
            }
            taskFull.setPrioridade(prioridadeInput);

            System.out.println("Qual o Status da tarefa ? \n" +
                    "1 . Todo \n" +
                    "2 . Doing \n" +
                    "3 . Done \n" +
                    "Digite um número acima para definir o status da tarefa");

            String statusInput;
            boolean statusValido = false;
            while (!statusValido) {
                statusInput = scanner.nextLine();
                if (statusInput.matches("[1-3]")) {
                    int status = Integer.parseInt(statusInput);
                    taskFull.setStatus(status);
                    statusValido = true;
                } else {
                    System.out.println("Status inválido. Digite 1, 2 ou 3. Tente novamente:");
                }
            }

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