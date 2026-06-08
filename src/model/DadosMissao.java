package model;

public class DadosMissao {

    private String coordenadas;
    private String codigoAcesso;
    private double nivelCombustivel;
    private String trajetoria;
    private int numeroTripulantes;

    public DadosMissao(String coordenadas, String codigoAcesso){
        this.coordenadas = coordenadas;
        this.codigoAcesso = codigoAcesso;
        this.nivelCombustivel = 100;
        this.trajetoria = "Indefinida";
        this.numeroTripulantes = 0;
    }

    public double getNivelCombustivel() {
        return this.nivelCombustivel;
    }

    public String getTrajetoria(){
        return this.trajetoria;
    }

    public int getNumeroTripulantes(){
        return this.numeroTripulantes;
    }

    public String getCoordenadas(String senha) {
        if (this.codigoAcesso.equals(senha)) {
            return this.coordenadas;
        }
        return "ACESSO NEGADO";
    }

    public void setTrajetoria(String trajetoria){
        if (trajetoria == null || trajetoria.isBlank()) {
            System.out.println("Trajetoria invalida!");
            return;
        }
        this.trajetoria = trajetoria;
    }

    public void setNumeroTripulantes(int numeroTripulantes){
        if (numeroTripulantes < 0){
            System.out.println("Numero de tripulantes invalido!");
            return;
        }
        this.numeroTripulantes = numeroTripulantes;
    }

    public void setNivelCombustivel(double nivel) {
        if(nivel < 0 || nivel > 100) {
            System.out.println("Valor invalido! Deve ser entre 0 e 100.");
            return;
        }
        this.nivelCombustivel = nivel;
        if (nivel < 20) {
            System.out.println("ALERTA: combustivel abaixo de 20%!");
        }
    }
}
