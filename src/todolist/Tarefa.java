package todolist;

import java.util.Date;
public class Tarefa {

    private String nome;
    private String descricao;
    private String dataTermino;
    private String prioridade;
    private String categoria;
    private String Status;


    public String toFileFormat() {
        return nome + "|" + descricao + "|" + dataTermino + "|" + prioridade + "|" + categoria + "|" + Status;
    }

    public static Tarefa fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        Tarefa tarefa = new Tarefa();
        tarefa.setNome(parts[0]);
        tarefa.setDescricao(parts[1]);
        tarefa.setDataTermino(parts[2]);
        tarefa.setPrioridade(parts[3]);
        tarefa.setCategoria(parts[4]);
        tarefa.setStatus(parts[5]);
        return tarefa;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataTermino='" + dataTermino + '\'' +
                ", prioridade='" + prioridade + '\'' +
                ", categoria='" + categoria + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
