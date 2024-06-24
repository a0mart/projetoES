public abstract class DetalhesLivro {
    protected String titulo;
    protected String autor;
    protected Genero genero;
    protected SubGenero subGenero;
    protected int numeroEdicao;
    protected int isbn;

    protected int ano;

    public DetalhesLivro(String titulo, String autor, Genero genero, SubGenero subGenero, int numeroEdicao, int isbn, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.subGenero = subGenero;
        this.numeroEdicao = numeroEdicao;
        this.isbn = isbn;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public SubGenero getSubGenero() {
        return subGenero;
    }

    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getAno() {
        return ano;
    }
}
