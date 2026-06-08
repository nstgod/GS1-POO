package model;

// Herda de SistemaPropulsao. Propulsao eletrica: baixo empuxo, alta eficiencia.
public class PropulsaoEletrica extends SistemaPropulsao {

    private double impulsoEspecifico;  // eficiencia (segundos) — atributo especifico
    private double potenciaPainel;     // potencia eletrica disponivel em kW — especifico

    public PropulsaoEletrica(String nome, double impulsoEspecifico, double potenciaPainel) {
        super(nome);   // chama o construtor da classe mae
        this.impulsoEspecifico = impulsoEspecifico;
        this.potenciaPainel = potenciaPainel;
    }

    // Sobrescreve acelerar() reaproveitando a validacao da classe mae com super().
    @Override
    public void acelerar(double potencia) {
        super.acelerar(potencia);
        if (isMotorLigado() && potencia > 0) {
            System.out.println("Ionizando gas com energia solar — empuxo suave e continuo.");
        }
    }

    // Empuxo eletrico e baixo: depende da potencia dos paineis e da eficiencia.
    @Override
    public double calcularEmpuxo() {
        // Modelo simplificado: empuxo cresce com potencia aplicada, painel e eficiencia.
        return (getPotenciaAtual() / 100.0) * potenciaPainel * (impulsoEspecifico / 10000.0);
    }

    @Override
    public String descricaoTecnologia() {
        return "Propulsao Eletrica (impulso especifico " + impulsoEspecifico
                + "s) - baixo empuxo, alta eficiencia para viagens longas.";
    }

    public double getImpulsoEspecifico() {
        return this.impulsoEspecifico;
    }

    public double getPotenciaPainel() {
        return this.potenciaPainel;
    }
}
