import main.org.example.Main;
import main.org.example.Task;
import main.org.example.TodoList;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class RemoveTest {

    @Mock
    private TodoList addTask;

    @Mock
    private Scanner scanner;


    public RemoveTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRemoveTarefa() {
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task();
        task1.setNome("Tarefa 1");
        Task task2 = new Task();
        task2.setNome("Tarefa 2");
        tasks.add(task1);
        tasks.add(task2);

        doReturn(tasks).when(addTask).getTodoItems(); // Usando doReturn() para retornar a lista de tarefas

        when(scanner.nextLine()).thenReturn("1"); // Simula a entrada do usuário

        Main.removeTarefa(addTask, scanner);

        verify(addTask, times(2)).printList(); // Verifica duas chamadas ao método printList()
        verify(addTask, times(1)).removeItem(0);
        verify(addTask, times(1)).saveTasksToFile("todolist.txt");
    }

    private VerificationMode times(int i) {
        return null;
    }

}