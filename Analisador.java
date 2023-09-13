import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Analisador {

    private static final int NumeroDeVetores = 50;
    private static final int ValorMaximo = 1000000;
    

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a questão do trabalho que deseja executar: ");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                questao1();
                break;
        
            case 2:
                questao2(10000);
                break;

            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static void questao1() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o tamanho do vetor: ");
        
        int tamanhoDoVetor = scanner.nextInt();
        int[][] vetores = geradorDeVetores(tamanhoDoVetor);

        // Medidor de tempo do Quicksort

        long tempoMedioQuicksort = System.currentTimeMillis();
        for (int i = 0; i < NumeroDeVetores; i++) {
            int[] vetor = vetores[i];
            long tempoInicialQuicksort = System.currentTimeMillis();
            quickSort(vetor, 0, tamanhoDoVetor - 1);
            long tempoFinalQuicksort = System.currentTimeMillis();
            System.out.println("Tempo de execução do Quicksort: " + (tempoFinalQuicksort - tempoInicialQuicksort) + " ms");
            
        }
        long tempoTotalQuicksort = (System.currentTimeMillis() - tempoMedioQuicksort);
        tempoMedioQuicksort = (System.currentTimeMillis() - tempoMedioQuicksort)/50;
        
        System.out.println("Tempo total de execução do Quicksort: " + tempoTotalQuicksort + " ms");
        System.out.println("Tempo médio de execução do Quicksort: " + tempoMedioQuicksort + " ms");

        // Medidor de tempo do Selecao
        
        long tempoMedioSelecao = System.currentTimeMillis();
        for (int i = 0; i < NumeroDeVetores; i++) {
            int[] vetor = vetores[i];
            long tempoInicialSelecao = System.currentTimeMillis();
            selecao(vetor);
            long tempoFinalSelecao = System.currentTimeMillis();
            System.out.println("Tempo de execução do Selecao: " + (tempoFinalSelecao - tempoInicialSelecao) + " ms");
            
        }
        long tempoTotalSelecao = (System.currentTimeMillis() - tempoMedioSelecao);
        tempoMedioSelecao = (System.currentTimeMillis() - tempoMedioSelecao)/50;
        
        System.out.println("Tempo total de execução do Selecao: " + tempoTotalSelecao + " ms");
        System.out.println("Tempo médio de execução do Selecao: " + tempoMedioSelecao + " ms");

        salvarTemposEmArquivo(tempoTotalSelecao, tempoMedioSelecao, tempoTotalQuicksort, tempoMedioQuicksort, tamanhoDoVetor);
    }

    public static void questao2(int tamanhoDoVetor) {
    
        Random random = new Random();
        int[][] vetoresAleatorios = new int[10][tamanhoDoVetor];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < tamanhoDoVetor; j++) {
                vetoresAleatorios[i][j] = 1 + random.nextInt(ValorMaximo);
            }
        }

        int[] vetorOrdenado = new int[tamanhoDoVetor];

        for (int i = 0; i < tamanhoDoVetor; i++) {
           vetorOrdenado[i] = i;
        }

        long tempoInicialQuicksort = System.currentTimeMillis();

        for(int i = 0; i < 10; i++){
            int[] vetor = vetoresAleatorios[i];
            quickSort(vetor, 0, tamanhoDoVetor - 1);
            long tempoFinalQuicksort = System.currentTimeMillis();
            System.out.println("Tempo de execução do Quicksort aleatório: " + (tempoFinalQuicksort - tempoInicialQuicksort) + " ms");
        }

        long tempoTotalQuicksort = (System.currentTimeMillis() - tempoInicialQuicksort);
        long tempoMedioQuicksortAleatorio = (System.currentTimeMillis() - tempoInicialQuicksort)/10;
        
        System.out.println("Tempo total de execução do Quicksort aleatório: " + tempoTotalQuicksort + " ms");
        System.out.println("Tempo médio de execução do Quicksort aleatório: " + tempoMedioQuicksortAleatorio + " ms");

        tempoInicialQuicksort = System.currentTimeMillis();

        for(int i = 0; i < 10; i++){
            
            quickSort(vetorOrdenado, 0, tamanhoDoVetor - 1);
            long tempoFinalQuicksort = System.currentTimeMillis();
            System.out.println("Tempo de execução do Quicksort ordenado: " + (tempoFinalQuicksort - tempoInicialQuicksort) + " ms");
        }

        tempoTotalQuicksort = (System.currentTimeMillis() - tempoInicialQuicksort);
        long tempoMedioQuicksortOrdenado = (System.currentTimeMillis() - tempoInicialQuicksort)/10;
        
        System.out.println("Tempo total de execução do Quicksort ordenado: " + tempoTotalQuicksort + " ms");
        System.out.println("Tempo médio de execução do Quicksort ordenado: " + tempoMedioQuicksortOrdenado + " ms");

        salvarTemposEmArquivoQ2(tempoMedioQuicksortAleatorio, tempoMedioQuicksortOrdenado);
    }

    public static int[][] geradorDeVetores(int TamanhoDoVetor) {
        int[][] vetores = new int[NumeroDeVetores][TamanhoDoVetor];
        Random random = new Random();

        for (int i = 0; i < NumeroDeVetores; i++) {
            for (int j = 0; j < TamanhoDoVetor; j++) {
                vetores[i][j] = 1 + random.nextInt(ValorMaximo);
            }
        }
        return vetores;
    }

    public static void  selecao(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[j] < vetor[min]) {
                    min = j;
                }
            }
            int troca = vetor[min];
            vetor[min] = vetor[i];
            vetor[i] = troca;
        }
    }

    public static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    public static int separar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[fim];
        int i = (inicio - 1);
        for (int j = inicio; j < fim; j++) {
            if (vetor[j] <= pivo) {
                i++;
                int troca = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = troca;
            }
        }
        int troca = vetor[i + 1];
        vetor[i + 1] = vetor[fim];
        vetor[fim] = troca;

        return i + 1;
    }

    public static void salvarTemposEmArquivo(long tempoTotalSelecao, long tempoMedioSelecao, long tempoTotalQuicksort, long tempoMedioQuicksort, int tamanhoDoVetor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("temposDeExecução" + tamanhoDoVetor + ".txt", true))) {
            writer.write("Tempo total de execução do Selecao: " + tempoTotalSelecao + " ms");
            writer.newLine();
            writer.write("Tempo médio de execução do Selecao: " + tempoMedioSelecao + " ms");
            writer.newLine();
            writer.write("Tempo total de execução do Quicksort: " + tempoTotalQuicksort + " ms");
            writer.newLine();
            writer.write("Tempo médio de execução do Quicksort: " + tempoMedioQuicksort + " ms");
            writer.newLine();
            System.out.println("Tempos de execução salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os tempos de execução: " + e.getMessage());
        }
    }

        public static void salvarTemposEmArquivoQ2(long tempoMedioQuicksortAleatorio, long tempoMedioQuicksortOrdenado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("temposDeExecuçãoQ2.txt", true))) {
            writer.write("Tempo médio de execução do Quicksort Aleatório: " + tempoMedioQuicksortAleatorio + " ms");
            writer.newLine();
            writer.write("Tempo médio de execução do Quicksort Ordenado: " + tempoMedioQuicksortOrdenado + " ms");
            writer.newLine();
            System.out.println("Tempos de execução salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os tempos de execução: " + e.getMessage());
        }
    }
}