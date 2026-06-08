package model;

// Herda de SistemaPropulsao. Propulsao quimica: alto empuxo, queima de combustivel.
public class PropulsaoQuimica extends SistemaPropulsao {

    private double empuxoMaximo;     // empuxo maximo em kN (atributo especifico)
    private String tipoCombustivel;  // ex.: "Hidrogenio liquido" (atributo especifico)

    public PropulsaoQuimica(String nome, double empuxoMaximo, String tipoCombustivel) {
        super(nome);   // chama o construtor da classe mae
        this.empuxoMaximo = empuxoMaximo;
        this.tipoCombustivel = tipoCombustivel;
    }

    // Sobrescreve acelerar() reaproveitando a validacao da classe mae com super().
    @Override
    public void acelerar(double potencia) {
        super.acelerar(potencia);
        if (isMotorLigado() && potencia > 0) {
            System.out.println("Queimando " + tipoCombustivel
                    + " - empuxo intenso e rapido!");
        }
    }

    // Empuxo proporcional a potencia atual sobre o empuxo maximo do motor.
    @Override
    public double calcularEmpuxo() {
        return (getPotenciaAtual() / 100.0) * empuxoMaximo;
    }

    @Override
    public String descricaoTecnologia() {
        return "Propulsao Quimica (" + tipoCombustivel
                + ") - alto empuxo, ideal para decolagem.";
    }

    public double getEmpuxoMaximo() {
        return this.empuxoMaximo;
    }

    public String getTipoCombustivel() {
        return this.tipoCombustivel;
    }
}
