public class Main {
    public static void main(String[] args) {
        GestorBaseDados gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        Funcionario f = new Funcionario("func",117,"rua sfsdf",978654123,"func@mail.pt","123");
        Socio socio3 = new Socio("Manel", 123, "Casa", 345, "Jaaa@");
        Socio socio1 = new Socio("Fernardo", 324, "Casa", 345, "Jaaa@");
        Socio socio2 = new Socio("Jeremias", 983, "Casa", 345, "Jaaa@");
        Fornecedor fornecedor1 = new Fornecedor("Porto Editora", 456, "Porto, rua bla bla bla nº87", 933333333, "emailluis@teste.pt",TipoFornecedor.EditoraIndependente);
        Fornecedor fornecedor2 = new Fornecedor("Lisboa Editora Distribuição", 789, "Lisboa, rua bla bla bla nº14", 922222222, "emailjoao@teste.pt",TipoFornecedor.DistribuidorNacional);
        fornecedor1.setLivrosDisponiveis(gestorBaseDados.getLivros());
        fornecedor2.setLivrosDisponiveis(gestorBaseDados.getLivros());
        gestorBaseDados.addFornecedor(fornecedor1);
        gestorBaseDados.addFornecedor(fornecedor2);
        gestorBaseDados.addFuncionario(f);
        gestorBaseDados.addSocio(socio3);
        gestorBaseDados.addSocio(socio1);
        gestorBaseDados.addSocio(socio2);
        gestorBaseDados.criarStockeLivros("Cinderela", "Joaquim", Genero.tecnico, SubGenero.informatica, 213, 345, 1222, 2);
        gestorBaseDados.criarStockeLivros("O autocarro", "Luis", Genero.ficcao, SubGenero.terror, 333, 476, 2024, 1);
        gestorBaseDados.criarStockeLivros("Nabos", "Esmeralda", Genero.ficcao, SubGenero.suspense, 546, 874, 1880, 4);

        new LoginForm("Login").setVisible(true);
        //new MenuPrincipal("Menu Principal").setVisible(true);
        //new MenuPaginaInicialSocio("Menu Principal").setVisible(true);
    }
}

