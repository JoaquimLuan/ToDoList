package todolist;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TodoList addTask = new TodoList();
        addTask.loadTasksFromFile("todolist.txt");
        String verificador;
        boolean running = true;

        System.out.println("Bom vindo ao programa Lista de Tarefas");

        try(Scanner scanner = new Scanner(System.in)) {
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
                        boolean adicionaTask = true;
                        while (adicionaTask) {
                            Task taskFull = new Task();

                            System.out.println("Digite o nome da tarefa ");
                            taskFull.setNome(scanner.nextLine());

                            System.out.println("Digite a descrição da tarefa ");
                            taskFull.setDescricao(scanner.nextLine());

                            System.out.println("Qual a data de termino da tarefa ?");
                            taskFull.setData(scanner.nextLine());

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
                        break;

                    case "2":
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
                        break;

                    case "3":
                        addTask.printList();
                        System.out.println("Digite o numero da tarefa para removê - la");
                        addTask.removeItem(Integer.parseInt(scanner.nextLine())-1);
                        addTask.printList();
                        addTask.saveTasksToFile("todolist.txt");
                        break;

                    case "4":
                        System.out.println("Agora você pode criar uma nova TODO List");
                        addTask = new TodoList();
                        break;

                    case "5":
                        System.out.println("Aplicação encerrada!");
                        running = false;
                        break;
                    default:
                        System.out.println("Digite um número válido");

                }

            }
        }
    }

}