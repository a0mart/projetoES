import javax.swing.*;

public class BotaoLivro extends JButton {

    private Livro livro;
    private int linha;
    private int coluna;

    public BotaoLivro(int linha,int coluna){
        this.livro = null;
        this.linha = linha;
        this.coluna = coluna;
    }

    public void setLivro(Livro livro){
        this.livro = livro;
        setText(livro.getTitulo());
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public Livro getLivro() {
        return livro;
    }
}
