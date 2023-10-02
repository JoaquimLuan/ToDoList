import main.org.example.Main;
import main.org.example.Task;
import main.org.example.TodoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.text.ParseException;
import java.util.Scanner;

import static org.mockito.Mockito.*;


public class MainTest {

    @Mock
    private TodoList addTask;

    @Mock
    private Scanner scanner;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdicionaTarefa() throws ParseException {
        when(scanner.nextLine())
                .thenReturn("Tarefa Teste")
                .thenReturn("Descrição Teste")
                .thenReturn("25/12/2023 10:00")
                .thenReturn("25/01/2024 09:00")
                .thenReturn("Categoria Teste")
                .thenReturn("3")
                .thenReturn("1")
                .thenReturn("N");

        Main.adicionaTarefa(scanner, addTask);

        // Verifique se o addItem foi chamado uma vez com o objeto Task correto
        verify(addTask, times(1)).addItem(any(Task.class));
    }


}