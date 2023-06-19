package excel;

public class Planilha {
    private String nome;
    private String email;
    private String path;

    public Planilha(String nome, String email, String path) {
        this.nome = nome;
        this.email = email;
        this.path = path;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
