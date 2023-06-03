/*
 * 1-Validar se o copo está no lugar
 * 2-Cada suco feito consome 5 laranjas(validar quantidade antes de fazer);
 * 3-Relatório de suco servido e dinheiro
 * 4-Relatório soma total dos últmos dias
*/

public class suco {

  static String senhaCorreta = "panze";
  static int laranjas = 0, sucosProd = 0, dia = 1, totDias = 1, sucosHoje = 0;
  static Arquivo a = new Arquivo("sucos.csv");

  public static void main(String[] args) {
    carregarDados();
    String menu = "--- Menu de Opções ---\n" +
        "[s] Servir Suco\n" +
        "[c] Consulta\n" +
        "[a] Abastecer\n" +
        "[r] Relatório total\n" +
        "[x] Sair";

    char op = ' ';
    op = Character.toLowerCase(Entrada.leiaChar(menu));

    while (op != 'x') {
      if (op == 's') {
        if (laranjas >= 5) {
          boolean copo = Entrada.leiaBoolean("Seu copo está no lugar?", "Sim", "Não");
          while (!copo) {
            Entrada.leiaString("Por favor, coloque o copo no local indicado");
            copo = Entrada.leiaBoolean("Seu copo está no lugar?", "Sim", "Não");
          }
          laranjas = laranjas - 5;
          sucosHoje++;
          Entrada.leiaString("############\nSuco pronto!\n############");
        } else {
          Entrada.leiaString(
              "################################\nSem laranjas suficientes na máquina!\n################################");
        }
      } else if (op == 'c') {
        if (validarAcesso()) {
          Entrada.leiaString("#################DIA#################" +
              "\nLaranjas na maquina: " + laranjas +
              "\nSucos servidos hoje: " + sucosHoje +
              "\nLucro total: R$" + (double) (sucosHoje * 5.5));
        }
      } else if (op == 'a') {
        if (validarAcesso()) {
          laranjas += Entrada.leiaInt("Insira a quantidade de laranjas que serão abastecidas");
        }
      } else if (op == 'r') {
        if (validarAcesso()) {
          Entrada.leiaString("##############TOTAL##############" +
              "\nTotal de dias trabalhados: " + totDias +
              "\nSucos servidos: " + (sucosProd + sucosHoje) +
              "\nLaranhas consumidas: " + ((sucosProd + sucosHoje) * 5) +
              "\nLucro total: R$" + (double) (sucosProd + sucosHoje) * 5.5);
        }
      }

      op = Character.toLowerCase(Entrada.leiaChar(menu));
    }
    gravarDados(laranjas, sucosHoje);
  }

  public static void gravarDados(int laranjas, int sucosHoje) {
    a.abrirEscrita();
    String dados = laranjas + ";" + (sucosProd + sucosHoje) + ";" + dia++;
    a.escreverLinha(dados);
    a.fecharArquivo();
  }

  public static void carregarDados() {
    if (a.abrirLeitura()) {
      String dados = a.lerLinha();
      while (dados != null) {
        laranjas = Integer.parseInt(posicaoCSV(dados, 1));
        sucosProd = Integer.parseInt(posicaoCSV(dados, 2));
        dia = Integer.parseInt(posicaoCSV(dados, 3));
        totDias++;
        dados = a.lerLinha();
      }
    }
    a.fecharArquivo();
  }

  public static boolean validarAcesso() {
    String senha = Entrada.leiaString("Insira a senha administrativa!");
    boolean senhaVal = senha.toLowerCase().equals(senhaCorreta);
    while (!senhaVal && !senha.toLowerCase().equals("fim")) {
      senha = Entrada.leiaString("Insira a senha administrativa!\n\n'Ou fim' para sair");
      senhaVal = senha.toLowerCase().equals(senhaCorreta);
    }
    return senhaVal;
  }

  public static String posicaoCSV(String str, int pos) {
    String retorno = str;
    int i = 0, f;
    f = str.indexOf(";");

    for (int x = 1; x < pos; x++) {
      i = f + 1;
      f = str.indexOf(";", i);
    }
    if (f >= 0)
      retorno = str.substring(i, f);
    else
      retorno = str.substring(i);
    return retorno;
  }
}