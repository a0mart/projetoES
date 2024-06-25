import javax.swing.*;

public class BotaoFornecedor extends JButton {
    private Fornecedor fornecedor;
    private int linha;
    private int coluna;

    public BotaoFornecedor(int linha,int coluna){
        this.fornecedor = null;
        this.linha = linha;
        this.coluna = coluna;
    }

    public void setFornecedor(Fornecedor fornecedor){
        this.fornecedor = fornecedor;
        setText(fornecedor.getNome());
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }
}
