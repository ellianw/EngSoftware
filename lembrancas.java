public class lembrancas {
  static String senhaCorreta = "panze";
  static int bichinhos = 0, fichas = 0, pescados = 0;
  static Arquivo a = new Arquivo("lembrancas.csv");

  public static void main(String[] args) {
    String menu = "--- Menu de Opções ---\n" +
        "[j] Jogar\n" +
        "[c] Consulta\n" +
        "[r] Reiniciar\n" +
        "[a] Abastecer\n" +
        "[x] Sair";

    char op = ' ';
    op = Character.toLowerCase(Entrada.leiaChar(menu));

    while (op != 'x') {
      if (op == 'j') {
        if (bichinhos > 0) {
          if (Math.random() >= 0.90) {
            Entrada
                .leiaString("************************\nPARABÉNS! VOCÊ PESCOU UMA LEMBRAN�A\n************************");
            bichinhos--;
            pescados++;
          } else {
            Entrada.leiaString("************************\nPOXA! NÃO FOI DESSA VEZ!\n************************");
            fichas++;
          }
          op = Character.toLowerCase(Entrada.leiaChar(menu));
        } else {
          Entrada.leiaString("************************\nNão há lembranças na máquina\n************************");
          op = Character.toLowerCase(Entrada.leiaChar(menu));
        }
      } else if (op == 'c') {
        if (validarSenha()) {
          Entrada.leiaString("Há um total de " + bichinhos + " lembranças na máquina\n" +
              "Foram gastas " + fichas + " fichas\n" +
              "Foram pescadas " + pescados + " lembranças\n" +
              "Lucro de R$" + ((fichas * 5) - (pescados * 5)) + ",00");
        } else {
          Entrada.leiaString("ACESSO NEGADO!");
        }
        op = Character.toLowerCase(Entrada.leiaChar(menu));
      } else if (op == 'r') {
        if (validarSenha()) {
          fichas = pescados = 0;
          Entrada.leiaString("Os dados de fichas usadas e lembran�as pescadas foram zerados!");
        } else {
          Entrada.leiaString("ACESSO NEGADO!");
        }
        op = Character.toLowerCase(Entrada.leiaChar(menu));
      } else if (op == 'a') {
        if (validarSenha())
          bichinhos = bichinhos + Entrada.leiaInt("Insira a quantidade de lembrancinhas que deseja ADICIONAR");
        else {
          Entrada.leiaString("ACESSO NEGADO!");
        }
        op = Character.toLowerCase(Entrada.leiaChar(menu));
      }
    }

    gravarDados(bichinhos, fichas, pescados);
  }

  public static void gravarDados(int bichinhos, int fichas, int pescados) {
    a.abrirEscrita();
    String dados = bichinhos + ";" + fichas + ";" + pescados;
    a.escreverLinha(dados);
    a.fecharArquivo();
  }

  public static void carregarDados() {
    if (a.abrirLeitura()) {
      String dados = a.lerLinha();
      while (dados != null) {
        bichinhos = Integer.parseInt(posicaoCSV(dados, 1)) + 1;
        fichas = Integer.parseInt(posicaoCSV(dados, 2));
        pescados = Integer.parseInt(posicaoCSV(dados, 3));
        dados = a.lerLinha();
      }
    }
    a.fecharArquivo();
  }

  public static boolean validarSenha() {
    String senha = Entrada.leiaString("Insira a senha");
    if (senha.toLowerCase().equals(senhaCorreta))
      return true;
    else
      return false;
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