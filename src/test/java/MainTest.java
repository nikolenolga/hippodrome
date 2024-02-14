import org.junit.jupiter.api.*;
import org.mockito.Mockito;

class MainTest {
    @Disabled("Тест для метода main отключен чтобы не занимать время при запуске всех тестов. При необходимости запустить вручную.")
    @Test
    @Timeout(value = 22)
    void givenMainMethod_whenCall_thenLastLessThen22Sec() throws Exception {
        Main.main(Mockito.any());
    }

}