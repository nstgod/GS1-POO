package main;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe principal: menu interativo que integra sensores, propulsao,
// dados da missao e o sistema de alertas.
public class SistemaMonitoramento {

    private final List<Sensor> sensores = new ArrayList<>();
    private final List<SistemaPropulsao> propulsores = new ArrayList<>();
    private final DadosMissao dadosMissao;
    private final Scanner entrada = new Scanner(System.in);

    public SistemaMonitoramento() {
        // Sensores iniciais (id, nome, limite de alerta)
        sensores.add(new SensorTemperatura("S1", "Sensor de Temperatura", 80.0));
        sensores.add(new SensorPressao("S2", "Sensor de Pressao", 110.0));
        sensores.add(new SensorRadiacao("S3", "Sensor de Radiacao", 70.0));

        // Liga todos os sensores (sao componentes espaciais)
        for (Sensor sensor : sensores) {
            if (sensor instanceof ComponenteEspacial componente) {
                componente.ligar();
            }
        }

        // Propulsores iniciais
        propulsores.add(new PropulsaoQuimica("Motor Principal", 7600.0, "Hidrogenio liquido"));
        propulsores.add(new PropulsaoEletrica("Motor Ionico", 3000.0, 15.0));

        // Dados da missao protegidos por codigo de acesso "1234"
        dadosMissao = new DadosMissao("Lat -23.5 / Lon -46.6 / Alt 408km", "1234");
        dadosMissao.setTrajetoria("Terra -> Lua");
        dadosMissao.setNumeroTripulantes(4);
    }

    public static void main(String[] args) {
        new SistemaMonitoramento().iniciar();
    }

    public void iniciar() {
        System.out.println("=========================================");
        System.out.println("  PLATAFORMA DE MONITORAMENTO ESPACIAL");
        System.out.println("=========================================");

        boolean executando = true;
        while (executando) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opcao: ");
            switch (opcao) {
                case 1 -> verificarSensores();
                case 2 -> controlarPropulsao();
                case 3 -> gerenciarDadosMissao();
                case 4 -> simularAlertas();
                case 5 -> exibirStatusCompleto();
                case 0 -> {
                    System.out.println("Encerrando o sistema. Boa viagem espacial!");
                    executando = false;
                }
                default -> System.out.println("Opcao invalida. Tente novamente.");
            }
        }
        entrada.close();
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("----------------- MENU ------------------");
        System.out.println("1 - Verificar sensores");
        System.out.println("2 - Controlar propulsao");
        System.out.println("3 - Gerenciar dados da missao");
        System.out.println("4 - Simular alertas");
        System.out.println("5 - Exibir status completo");
        System.out.println("0 - Sair");
        System.out.println("-----------------------------------------");
    }

    // ---------------- 1. SENSORES ----------------

    private void verificarSensores() {
        System.out.println("\n=== LEITURA DOS SENSORES ===");
        for (Sensor sensor : sensores) {
            double valor = sensor.lerValor();
            String funcionamento = sensor.verificarFuncionamento() ? "OK" : "FALHA";
            System.out.printf("[%s] Sensor de %s -> valor lido: %.2f | limite: %.2f | estado: %s%n",
                    funcionamento, sensor.retornarTipo(), valor, sensor.getLimiteAlerta(), funcionamento);
            verificarAlerta(sensor, valor);
        }
    }

    // ---------------- 2. PROPULSAO ----------------

    private void controlarPropulsao() {
        System.out.println("\n=== CONTROLE DE PROPULSAO ===");
        for (int i = 0; i < propulsores.size(); i++) {
            System.out.println((i + 1) + " - " + propulsores.get(i).getNome()
                    + " (" + propulsores.get(i).descricaoTecnologia() + ")");
        }
        int escolha = lerInteiro("Escolha o propulsor: ");
        if (escolha < 1 || escolha > propulsores.size()) {
            System.out.println("Propulsor invalido.");
            return;
        }
        SistemaPropulsao propulsor = propulsores.get(escolha - 1);

        System.out.println("\nO que deseja fazer com " + propulsor.getNome() + "?");
        System.out.println("1 - Ligar motor");
        System.out.println("2 - Desligar motor");
        System.out.println("3 - Acelerar");
        System.out.println("4 - Calcular empuxo");
        int acao = lerInteiro("Acao: ");
        switch (acao) {
            case 1 -> propulsor.ligarMotor();
            case 2 -> propulsor.desligarMotor();
            case 3 -> {
                double potencia = lerDouble("Potencia (0-100): ");
                propulsor.acelerar(potencia);
            }
            case 4 -> System.out.printf("Empuxo atual: %.2f kN%n", propulsor.calcularEmpuxo());
            default -> System.out.println("Acao invalida.");
        }
    }

    // ---------------- 3. DADOS DA MISSAO ----------------

    private void gerenciarDadosMissao() {
        System.out.println("\n=== DADOS DA MISSAO ===");
        System.out.println("1 - Ver coordenadas (protegidas por codigo)");
        System.out.println("2 - Definir nivel de combustivel");
        System.out.println("3 - Definir trajetoria");
        System.out.println("4 - Definir numero de tripulantes");
        System.out.println("5 - Resumo dos dados");
        int acao = lerInteiro("Acao: ");
        switch (acao) {
            case 1 -> {
                String codigo = lerTexto("Digite o codigo de acesso: ");
                System.out.println("Coordenadas: " + dadosMissao.getCoordenadas(codigo));
            }
            case 2 -> {
                double nivel = lerDouble("Nivel de combustivel (0-100): ");
                dadosMissao.setNivelCombustivel(nivel);
            }
            case 3 -> {
                String trajetoria = lerTexto("Nova trajetoria: ");
                dadosMissao.setTrajetoria(trajetoria);
            }
            case 4 -> {
                int tripulantes = lerInteiro("Numero de tripulantes: ");
                dadosMissao.setNumeroTripulantes(tripulantes);
            }
            case 5 -> {
                System.out.println("Combustivel: " + dadosMissao.getNivelCombustivel() + "%");
                System.out.println("Trajetoria: " + dadosMissao.getTrajetoria());
                System.out.println("Tripulantes: " + dadosMissao.getNumeroTripulantes());
                System.out.println("Coordenadas: [protegidas — exigem codigo de acesso]");
            }
            default -> System.out.println("Acao invalida.");
        }
    }

    // ---------------- 4. ALERTAS ----------------

    private void simularAlertas() {
        System.out.println("\n=== SIMULACAO DE ALERTAS ===");

        // Permite quebrar/restaurar um sensor para demonstrar o alerta CRITICO de falha.
        System.out.println("Simular falha em um sensor?");
        for (int i = 0; i < sensores.size(); i++) {
            System.out.println((i + 1) + " - Falhar " + sensores.get(i).retornarTipo()
                    + " (atual: " + (sensores.get(i).verificarFuncionamento() ? "OK" : "FALHA") + ")");
        }
        System.out.println("0 - Nenhum (apenas verificar)");
        int alvo = lerInteiro("Opcao: ");
        if (alvo >= 1 && alvo <= sensores.size()) {
            Sensor sensor = sensores.get(alvo - 1);
            sensor.setFuncionando(!sensor.verificarFuncionamento());
            System.out.println("Sensor de " + sensor.retornarTipo() + " agora esta "
                    + (sensor.verificarFuncionamento() ? "OK." : "em FALHA."));
        }

        System.out.println("\nForcando leituras e verificando limites...\n");
        for (Sensor sensor : sensores) {
            double valor = sensor.lerValor();
            System.out.printf("Sensor de %s leu %.2f (limite %.2f)%n",
                    sensor.retornarTipo(), valor, sensor.getLimiteAlerta());
            verificarAlerta(sensor, valor);
        }
        // Alerta de combustivel
        if (dadosMissao.getNivelCombustivel() < 20) {
            System.out.println(">> [CRITICO] Combustivel abaixo de 20%!");
        }
    }

    // Sistema de alertas: classifica em ATENCAO, ALERTA ou CRITICO.
    private void verificarAlerta(Sensor sensor, double valor) {
        double limite = sensor.getLimiteAlerta();
        if (!sensor.verificarFuncionamento()) {
            System.out.println(">> [CRITICO] Sensor de " + sensor.retornarTipo() + " com FALHA!");
            return;
        }
        if (valor > limite * 1.2) {
            System.out.println(">> [CRITICO] " + sensor.retornarTipo()
                    + " muito acima do limite!");
        } else if (sensor.estaAcimaDoLimite(valor)) {
            System.out.println(">> [ALERTA] " + sensor.retornarTipo()
                    + " acima do limite.");
        } else if (valor >= limite * 0.9) {
            System.out.println(">> [ATENCAO] " + sensor.retornarTipo()
                    + " proximo do limite.");
        } else {
            System.out.println("   " + sensor.retornarTipo() + " dentro do normal.");
        }
    }

    // ---------------- 5. STATUS COMPLETO ----------------

    private void exibirStatusCompleto() {
        System.out.println("\n========== STATUS COMPLETO ==========");
        System.out.println("\n-- Sensores --");
        for (Sensor sensor : sensores) {
            if (sensor instanceof ComponenteEspacial componente) {
                System.out.println(componente.exibirStatus());
            }
        }
        System.out.println("\n-- Propulsores --");
        for (SistemaPropulsao propulsor : propulsores) {
            System.out.println(propulsor.exibirStatus());
        }
        System.out.println("\n-- Missao --");
        System.out.println("Combustivel: " + dadosMissao.getNivelCombustivel() + "%");
        System.out.println("Trajetoria: " + dadosMissao.getTrajetoria());
        System.out.println("Tripulantes: " + dadosMissao.getNumeroTripulantes());
        System.out.println("=====================================");
    }

    // ---------------- ENTRADA DO USUARIO ----------------

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!entrada.hasNextInt()) {
            if (!entrada.hasNext()) {  // fim da entrada (EOF): encerra com seguranca
                return 0;
            }
            entrada.next();
            System.out.print("Digite um numero inteiro valido: ");
        }
        int valor = entrada.nextInt();
        entrada.nextLine();
        return valor;
    }

    private double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!entrada.hasNextDouble()) {
            if (!entrada.hasNext()) {  // fim da entrada (EOF)
                return 0;
            }
            entrada.next();
            System.out.print("Digite um numero valido: ");
        }
        double valor = entrada.nextDouble();
        entrada.nextLine();
        return valor;
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        if (!entrada.hasNextLine()) {  // fim da entrada (EOF)
            return "";
        }
        return entrada.nextLine();
    }
}
