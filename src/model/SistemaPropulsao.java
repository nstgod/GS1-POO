package model;

// Classe abstrata que define o comportamento comum a todos os tipos de propulsao.
// Demonstra HERANCA: PropulsaoQuimica e PropulsaoEletrica herdam dela.
public abstract class SistemaPropulsao {

    private String nome;
    private boolean motorLigado;
    private double potenciaAtual;   // porcentagem de potencia (0 a 100)

    public SistemaPropulsao(String nome) {
        this.nome = nome;
        this.motorLigado = false;
        this.potenciaAtual = 0;
    }

    public void ligarMotor() {
        this.motorLigado = true;
        System.out.println("Motor de " + nome + " LIGADO.");
    }

    public void desligarMotor() {
        this.motorLigado = false;
        this.potenciaAtual = 0;
        System.out.println("Motor de " + nome + " DESLIGADO.");
    }

    // Metodo base que valida a potencia (0-100). As subclasses sobrescrevem
    // e chamam super.acelerar() para reaproveitar a validacao.
    public void acelerar(double potencia) {
        if (!motorLigado) {
            System.out.println("Nao e possivel acelerar: o motor esta desligado.");
            return;
        }
        if (potencia < 0 || potencia > 100) {
            System.out.println("Potencia invalida! Use um valor entre 0 e 100.");
            return;
        }
        this.potenciaAtual = potencia;
        System.out.println(nome + " acelerando a " + potencia + "% de potencia.");
    }

    // Cada tipo de propulsao calcula o empuxo de forma diferente.
    public abstract double calcularEmpuxo();

    // Cada tipo descreve seu funcionamento.
    public abstract String descricaoTecnologia();

    public String exibirStatus() {
        String estado = motorLigado ? "Ligado" : "Desligado";
        return "Propulsao " + nome
                + " [" + estado + "]"
                + " | Potencia: " + potenciaAtual + "%"
                + " | Empuxo: " + String.format("%.2f", calcularEmpuxo()) + " kN";
    }

    public String getNome() {
        return this.nome;
    }

    public boolean isMotorLigado() {
        return this.motorLigado;
    }

    public double getPotenciaAtual() {
        return this.potenciaAtual;
    }
}
