package model;

public abstract class ComponenteEspacial {

    private String id;
    private String nome;
    private boolean status;
    private double temperatura;

    public ComponenteEspacial(String id, String nome) {
        this(id, nome, 0);
    }

    public ComponenteEspacial(String id, String nome, double temperatura) {
        this.id = id;
        this.nome = nome;
        this.status = false;
        this.temperatura = temperatura;
    }

    public void ligar() {
        this.status = true;
        System.out.println(nome + " foi ligado.");
    }

    public void desligar() {
        this.status = false;
        System.out.println(nome + " foi desligado.");
    }

    public abstract String exibirStatus();

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getStatus() {
        return this.status ? "Ligado" : "Desligado";
    }

    public double getTemperatura() {
        return this.temperatura;
    }

    // protected: so as subclasses podem registrar a temperatura do componente.
    protected final void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
}
