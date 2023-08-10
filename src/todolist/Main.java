package todolist;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        TodoList adicionaTask = new TodoList();
        String arquivo = "/home/joaquimborges/Documentos/Acelera/ToDoList/TodoList/todolist.txt";
        adicionaTask.loadFromFile(arquivo);
        String verificador;
        boolean running = true;

        System.out.println("Bom vindo ao programa de gerenciamento de tarefas");

        try (Scanner entrada = new Scanner(System.in)) {
            while(running){
                System.out.println("===== TODO List =====\n" +
                        "1 . Adicionar tarefa\n" +
                        "2 . Listar todas as tarefas\n" +
                        "3 . Remover tarefa\n" +
                        "4 . Excluir TODO List\n" +
                        "5 . Encerrar aplicação");
                verificador = entrada.nextLine();
                switch (verificador){
                    case "1":
                        boolean addTask = true;
                        while(addTask){
                            Tarefa tarefaCompleta = new Tarefa();

                            System.out.println("Digite o nome da tarefa ");
                            tarefaCompleta.setNome(entrada.nextLine());

                            System.out.println("Digite a descrição da tarefa ");
                            tarefaCompleta.setDescricao(entrada.nextLine());

                            System.out.println("Qual a data de termino da tarefa ?");
                            tarefaCompleta.setDataTermino(entrada.nextLine());

                            System.out.println("Digite a categoria ");
                            tarefaCompleta.setCategoria(entrada.nextLine());

                            System.out.println("De 1 à 5 qual a prioridade da tarefa ? ");
                            tarefaCompleta.setPrioridade(entrada.nextLine());

                            System.out.println("Qual o Status da tarefa ? \n" +
                                    "1 . Todo \n" +
                                    "2 . Doing \n" +
                                    "3 . Done \n" +
                                    "Digite um número acima para definir o status da tarefa");
                            tarefaCompleta.setStatus(entrada.nextLine());
                            adicionaTask.addItem(tarefaCompleta);
                            adicionaTask.saveToFile(arquivo);

                            System.out.println("Deseja adicionar uma nova tarefa?(Y/N)");
                            boolean verificaTask = true;
                            while(verificaTask){
                                String task = entrada.nextLine();
                                switch (task){
                                    case "Y":
                                        verificaTask = false;
                                        break;
                                    case "N":
                                        addTask = false;
                                        verificaTask = false;
                                        break;
                                    default:
                                        System.out.println("Digite Y para SIM e N para NÃO");
                                }
                            }
                        }
                        break;
                    case "2":
                        adicionaTask.printList();
                        System.out.println("1 . Ver as tarefas por status\n" +
                                "2 . Ver as tarefas por categoria\n" +
                                "3 . Ver as tarefas por prioridade\n" +
                                "4 . Retornar ao menu principal" +
                                "Digite uma das opções acima");
                        String ordenador = entrada.nextLine();
                        switch (ordenador){
                            case "1":
                                adicionaTask.sortByStatus();
                                adicionaTask.printList();
                                break;
                            case "2":
                                adicionaTask.sortByCategory();
                                adicionaTask.printList();
                                break;
                            case "3":
                                adicionaTask.sortByPrioridade();
                                adicionaTask.printList();
                                break;
                            case "4":
                                System.out.println("Retornar ao menu principal");
                                break;
                            default:
                                System.out.println("Digite uma das opções acima");
                        }
                        break;
                    case "3":
                        adicionaTask.printList();
                        System.out.println("Digite o numero da tarefa para removê - la");
                        adicionaTask.removeItem(Integer.parseInt(entrada.nextLine()) - 1);
                        adicionaTask.printList();
                        break;
                    case "4":
                        System.out.println("Agora você pode criar uma nova TODO List");
                        adicionaTask = new TodoList();
                        adicionaTask.saveToFile(arquivo);
                        break;
                    case "5":
                        System.out.println("Aplicação encerrada!");
                        running = false;
                        adicionaTask.saveToFile(arquivo);
                        break;
                    default:
                        System.out.println("Digite um número válido");
                }
            }
        }
        adicionaTask.saveToFile(arquivo);
    }
}