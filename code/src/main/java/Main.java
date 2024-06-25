public class Main {
    public static void main(String[] args) {
        GestorBaseDados gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        Socio socio3 = new Socio("Manel", 123, "Casa", 345, "Jaaa@");
        Fornecedor fornecedor1 = new Fornecedor("luis", 456, "lisboa", 933333333, "email@teste.pt");
        gestorBaseDados.addFornecedor(fornecedor1);
        gestorBaseDados.addSocio(socio3);
        gestorBaseDados.criarStockeLivros("Cinderela", "Joaquim", Genero.tecnico, SubGenero.informatica, 213, 345, 1222, 3);
        gestorBaseDados.criarStockeLivros("O autocarro", "Luis", Genero.ficcao, SubGenero.terror, 333, 476, 2024, 2);
        gestorBaseDados.criarStockeLivros("Nabos", "Esmeralda", Genero.ficcao, SubGenero.suspense, 546, 874, 1880, 4);

        new MenuPrincipal("Menu Principal").setVisible(true);
    }
}

