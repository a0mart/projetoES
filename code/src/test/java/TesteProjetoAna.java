import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteProjetoAna {
    private Funcionario funcionario1;
    private Funcionario funcionario2;

    @BeforeEach
    void setUp() {
        funcionario1 = new Funcionario("João Silva", 123456789, "Rua A, 123", 912345678, "joao@example.com", "password123");
        funcionario2 = new Funcionario("Maria Santos", 987654321, "Rua B, 456", 987654321, "maria@example.com", "password456");
    }

    @Test
    void testFuncionarioCreation() {
        assertEquals("João Silva", funcionario1.getNome());
        assertEquals(123456789, funcionario1.getNif());
        assertEquals("Rua A, 123", funcionario1.getMorada());
        assertEquals(912345678, funcionario1.getTelefone());
        assertEquals("joao@example.com", funcionario1.getEmail());
        assertEquals("password123", funcionario1.getPassword());
        assertEquals(1, funcionario1.getId());

        assertEquals("Maria Santos", funcionario2.getNome());
        assertEquals(987654321, funcionario2.getNif());
        assertEquals("Rua B, 456", funcionario2.getMorada());
        assertEquals(987654321, funcionario2.getTelefone());
        assertEquals("maria@example.com", funcionario2.getEmail());
        assertEquals("password456", funcionario2.getPassword());
        assertEquals(2, funcionario2.getId());
    }

    @Test
    void testPasswordChange() {
        funcionario1.setPassword("newpassword123");
        assertEquals("newpassword123", funcionario1.getPassword());
    }
}
