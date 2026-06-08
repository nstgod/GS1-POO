package model;

public class SensorPressao extends ComponenteEspacial implements Sensor {

    private double limiteAlerta;   // acima desse valor (em kPa) deve disparar alerta
    private boolean funcionando;   // indica se o sensor está operando corretamente

    public SensorPressao(String id, String nome, double limiteAlerta) {
        // passa a temperatura de operacao do componente pelo construtor da classe mãe
        super(id, nome, 15 + Math.random() * 10);
        this.limiteAlerta = limiteAlerta;
        this.funcionando = true;
    }

    // ----- Métodos do contrato Sensor -----

    @Override
    public double lerValor() {
        // Simula uma leitura de pressão entre 80 kPa e 120 kPa
        return 80 + (Math.random() * 40);
    }

    @Override
    public boolean verificarFuncionamento() {
        return this.funcionando;
    }

    @Override
    public String retornarTipo() {
        return "Pressao";
    }

    // ----- Método abstrato herdado de ComponenteEspacial -----

    @Override
    public String exibirStatus() {
        return "Sensor de " + retornarTipo()
                + " [" + getStatus() + "]"
                + " | Limite de alerta: " + limiteAlerta + " kPa"
                + " | Temp. componente: " + String.format("%.1f", getTemperatura()) + " C";
    }

    // ----- Lógica própria do sensor -----

    // Detecta se um valor lido passou do limite configurado
    public boolean estaAcimaDoLimite(double valor) {
        return valor > this.limiteAlerta;
    }

    public double getLimiteAlerta() {
        return this.limiteAlerta;
    }

    public void setLimiteAlerta(double limiteAlerta) {
        this.limiteAlerta = limiteAlerta;
    }

    // Permite simular falha/recuperacao do sensor (usado no sistema de alertas).
    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }
}

