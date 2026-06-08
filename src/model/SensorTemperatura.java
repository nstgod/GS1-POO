package model;

// Herda de ComponenteEspacial (classe abstrata) e implementa o contrato Sensor.
public class SensorTemperatura extends ComponenteEspacial implements Sensor {

    private double limiteAlerta;   // acima desse valor (em °C) deve disparar alerta
    private boolean funcionando;   // indica se o sensor está operando corretamente

    public SensorTemperatura(String id, String nome, double limiteAlerta) {
        // passa a temperatura de operacao do componente pelo construtor da classe mãe
        super(id, nome, 20 + Math.random() * 10);
        this.limiteAlerta = limiteAlerta;
        this.funcionando = true;
    }

    // ----- Métodos do contrato Sensor -----

    @Override
    public double lerValor() {
        // Simula uma leitura de temperatura entre -50°C e 100°C
        return -50 + (Math.random() * 150);
    }

    @Override
    public boolean verificarFuncionamento() {
        return this.funcionando;
    }

    @Override
    public String retornarTipo() {
        return "Temperatura";
    }

    // ----- Método abstrato herdado de ComponenteEspacial -----

    @Override
    public String exibirStatus() {
        return "Sensor de " + retornarTipo()
                + " [" + getStatus() + "]"
                + " | Limite de alerta: " + limiteAlerta + " C"
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
