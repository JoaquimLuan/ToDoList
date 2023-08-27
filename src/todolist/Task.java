package todolist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task {

    private String nome;
    private String descricao;
    private Date data;
    private Date alarmeTask;
    private String prioridade;
    private String categoria;
    private int status;

    public String toFileFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return nome + "|" + descricao + "|" + dateFormat.format(data) + "|" + (alarmeTask != null ? dateFormat.format(alarmeTask) : "") + "|" + prioridade + "|" + categoria + "|" + status;
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
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public Date getAlarmeTask() {
        return alarmeTask; }
    public void setAlarmeTask(Date alarmeTask) {
        this.alarmeTask = alarmeTask;
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
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        return "Tarefa{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataTermino='" + dateFormat.format(data) + '\'' +
                ", dataAlarme='" + dateFormat.format(alarmeTask) + '\'' +
                ", prioridade='" + prioridade + '\'' +
                ", categoria='" + categoria + '\'' +
                ", Status='" + status + '\'' +
                '}';
    }

}