package todolist;

public class Task {

    private String nome;
    private String descricao;
    private String data;
    private String prioridade;
    private String categoria;
    private String status;

    public String toFileFormat() {
        return nome + "|" + descricao + "|" + data + "|" + prioridade + "|" + categoria + "|" + status;
    }

    public static Task fromFileFormat(String line) {

        String [] parts = line.split("\\|");

        Task tarefa = new Task();
        tarefa.setNome(parts[0]);
        tarefa.setDescricao(parts[1]);
        tarefa.setData(parts[2]);
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
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
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
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataTermino='" + data + '\'' +
                ", prioridade='" + prioridade + '\'' +
                ", categoria='" + categoria + '\'' +
                ", Status='" + status + '\'' +
                '}';
    }

}