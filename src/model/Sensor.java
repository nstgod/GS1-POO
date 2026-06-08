package model;

public interface Sensor {

    // Devolve o valor lido pelo sensor (pode ser simulado)
    double lerValor();

    // Devolve true se o sensor estiver funcionando corretamente
    boolean verificarFuncionamento();

    // Devolve o tipo do sensor (ex.: "Temperatura")
    String retornarTipo();

    // Valor limite configurado para disparar alerta
    double getLimiteAlerta();

    // Devolve true se o valor lido passou do limite configurado
    boolean estaAcimaDoLimite(double valor);

    // Liga/desliga o estado de funcionamento (permite simular uma falha)
    void setFuncionando(boolean funcionando);
}
