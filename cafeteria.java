public class cafeteria {
  static int agua = 0; // Máximo de 20000ml
  static int cafe = 0, leite_po = 0, achoc = 0; // Máximo de 500g
  static int cLongo = 0, cCurto = 0, cappuccino = 0, latteM = 0, chocQuente = 0, dia = 0;
  static double cLongoPrec = 0.0, cCurtoPrec = 0.0, cappuccinoPrec = 0.0, latteMaccPrec = 0.0, chocQuentePrec = 0.0;
  static String senhaCorreta = "panze";
  static Arquivo insumos = new Arquivo("insumos.csv");
  static Arquivo cafes = new Arquivo("cafes.csv");

  public static void main(String[] args) {
    lerDados();
    String menu1 = "################MENU################" +
        "\n[s] Cliente" +
        "\n[a] Admin" +
        "\n[x] Sair";

    char menuOp = mostramenu(menu1);

    while (menuOp != 'x') {
      if (menuOp == 's') {
        cliente();
      } else if (menuOp == 'a') {
        admin();
      }

      menuOp = mostramenu(menu1);
    }
    gravarDados();
  }

  public static void cliente() {
    String menu = "################CAFÉS################\n" +
        " 1) Café Curto\n" +
        " 2) Café Longo\n" +
        " 3) Cappuccino\n" +
        " 4) Latte Macchiato\n" +
        " 5) Chocolate Quente\n" +
        " x) Voltar\n" +
        "#####################################";
    char op = mostramenu(menu);
    while (op != 'x') {
      if (op == '1') {
        // Café curto
        if (agua >= 25 && cafe >= 8) {
          agua -= 25;
          cafe -= 8;
          cCurto++;
          Entrada.leiaString("Café pronto!");
        } else {
          Entrada.leiaString("Insumos insuficientes! Contate a administração.");
        }
      } else if (op == '2') {
        // Café Longo
        if (agua >= 50 && cafe >= 10) {
          agua -= 50;
          cafe -= 10;
          cLongo++;
          Entrada.leiaString("Café pronto!");
        } else {
          Entrada.leiaString("Insumos insuficientes! Contate a administração.");
        }
      } else if (op == '3') {
        // Cappuccino
        if (agua >= 80 && cafe >= 10 && leite_po >= 20 && achoc >= 10) {
          agua -= 80;
          cafe -= 10;
          leite_po -= 20;
          achoc -= 10;
          cappuccino++;
          Entrada.leiaString("Café pronto!");
        } else {
          Entrada.leiaString("Insumos insuficientes! Contate a administração.");
        }
      } else if (op == '4') {
        // Latte Macchiato
        if (agua >= 100 && cafe >= 15 && leite_po >= 40 && achoc >= 20) {
          agua -= 100;
          cafe -= 15;
          leite_po -= 40;
          achoc -= 20;
          latteM++;
          Entrada.leiaString("Café pronto!");
        } else {
          Entrada.leiaString("Insumos insuficientes! Contate a administração.");
        }
      } else if (op == '5') {
        // Chocolate Quente
        if (agua >= 100 && leite_po >= 50 && achoc >= 30) {
          agua -= 100;
          leite_po -= 50;
          achoc -= 30;
          chocQuente++;
          Entrada.leiaString("Café pronto!");
        } else {
          Entrada.leiaString("Insumos insuficientes! Contate a administração.");
        }
      }
      op = mostramenu(menu);
    }
  }

  public static void admin() {
    validarAcesso();
    String menu = "################MENU################\n" +
        " 1) Ajuste de Preço\n" +
        " 2) Abastecimento\n" +
        " 3) Relatório\n" +
        " x) Voltar\n" +
        "####################################";
    char op = mostramenu(menu);
    while (op != 'x') {
      if (op == '1') {
        // Ajuste de Preço
        altPreco();
      } else if (op == '2') {
        // Abastecimento
        abastecimento();
      } else if (op == '3') {
        // Relatório
        relatorio();
      }
      op = mostramenu(menu);
    }
  }

  public static void altPreco() {
    String menu = "################CaféS################\n" +
        " 1) Café Curto\n" +
        " 2) Café Longo\n" +
        " 3) Cappuccino\n" +
        " 4) Latte Macchiato\n" +
        " 5) Chocolate Quente\n" +
        " x) Voltar\n" +
        "#####################################";
    char op = mostramenu(menu);
    while (op != 'x') {
      if (op == '1') {
        // Café curto
        cCurtoPrec = Entrada.leiaDouble("Preço do Café Curto", cCurtoPrec);
      } else if (op == '2') {
        // Café Longo
        cLongoPrec = Entrada.leiaDouble("Preço do Café Longo", cLongoPrec);
      } else if (op == '3') {
        // Cappuccino
        cappuccinoPrec = Entrada.leiaDouble("Preço do Cappuccino", cappuccinoPrec);
      } else if (op == '4') {
        // Latte Macchiato
        latteMaccPrec = Entrada.leiaDouble("Preço do Latte Macchiato", latteMaccPrec);
      } else if (op == '5') {
        // Chocolate Quente
        chocQuentePrec = Entrada.leiaDouble("Preço do Chocolate Quente", chocQuentePrec);
      }
      op = mostramenu(menu);
    }
  }

  public static void abastecimento() {
    String menu = "################INSUMOS################\n" +
        " 1) Café\n" +
        " 2) Leite em Pó\n" +
        " 3) Achocolatado\n" +
        " 4) Água\n" +
        " x) Voltar\n" +
        "#######################################";
    char op = mostramenu(menu);
    while (op != 'x') {
      if (op == '1') {
        // Café
        if (Entrada.leiaBoolean("Deseja completar?", "Sim", "Não")) {
          cafe = 500;
        } else {
          int qtd = Entrada.leiaInt("Insira a quantidade");
          if (cafe + qtd >= 500)
            cafe = 500;
          else
            cafe += qtd;
        }
      } else if (op == '2') {
        // Leite em Pó
        if (Entrada.leiaBoolean("Deseja completar?", "Sim", "Não")) {
          leite_po = 500;
        } else {
          int qtd = Entrada.leiaInt("Insira a quantidade");
          if (leite_po + qtd >= 500)
            leite_po = 500;
          else
            leite_po += qtd;
        }
      } else if (op == '3') {
        // Achocolatado
        if (Entrada.leiaBoolean("Deseja completar?", "Sim", "Não")) {
          achoc = 500;
        } else {
          int qtd = Entrada.leiaInt("Insira a quantidade");
          if (achoc + qtd >= 500)
            achoc = 500;
          else
            achoc += qtd;
        }
      } else if (op == '4') {
        // Água
        if (Entrada.leiaBoolean("Deseja completar?", "Sim", "Não")) {
          agua = 500;
        } else {
          int qtd = Entrada.leiaInt("Insira a quantidade");
          if (agua + qtd >= 20000)
            agua = 20000;
          else
            agua += qtd;
        }
      }
      op = mostramenu(menu);
    }
  }

  public static void relatorio() {
    double saldoCaixa = (cCurto * cCurtoPrec) + (cLongo * cLongoPrec) + (cappuccino * cappuccinoPrec)
        + (latteM * latteMaccPrec) + (chocQuente * chocQuentePrec);
    String menu = "############Insumos#############\n" +
        "Água:                 " + agua + "ml\n" +
        "Café:                 " + cafe + "g\n" +
        "Leite em Pó:          " + leite_po + "g\n" +
        "Achocolatado:         " + achoc + "g\n\n" +
        "##########Cafés Sevidos##########\n" +
        "Café curto:           " + cCurto + "\n" +
        "Café longo:           " + cLongo + "\n" +
        "Cappuccino:           " + cappuccino + "\n" +
        "Latte macchiato:      " + latteM + "\n" +
        "Chocolate quente:     " + chocQuente + "\n" +
        "#################################\n\n" +
        "Total em caixa:       " + saldoCaixa;
    Entrada.leiaString(menu);
  }

  public static char mostramenu(String menu) {
    char op;
    op = Entrada.leiaChar(menu);
    op = Character.toLowerCase(op);
    return op;
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

  public static void lerDados() {
    if (insumos.abrirLeitura()) {
      String dados = insumos.lerLinha();
      while (dados != null) {
        agua = Integer.parseInt(dados.split(";")[0]);
        cafe = Integer.parseInt(dados.split(";")[1]);
        leite_po = Integer.parseInt(dados.split(";")[2]);
        achoc = Integer.parseInt(dados.split(";")[3]);
        dia = Integer.parseInt(dados.split(";")[4]);
        dados = insumos.lerLinha();
      }
    }
    /*
     * static int cLongo = 0, cCurto = 0, cappuccino = 0, latteM = 0, chocQuente =
     * 0, dia =0;
     * static double cLongoPrec = 0.0, cCurtoPrec = 0.0, cappuccinoPrec = 0.0,
     * latteMaccPrec = 0.0, chocQuentePrec = 0.0;
     */
    if (cafes.abrirLeitura()) {
      String dados = cafes.lerLinha();
      while (dados != null) {
        cLongo = Integer.parseInt(dados.split(";")[0]);
        cLongoPrec = Integer.parseInt(dados.split(";")[1]);
        cCurto = Integer.parseInt(dados.split(";")[2]);
        cCurtoPrec = Integer.parseInt(dados.split(";")[3]);
        cappuccino = Integer.parseInt(dados.split(";")[4]);
        cappuccinoPrec = Integer.parseInt(dados.split(";")[5]);
        latteM = Integer.parseInt(dados.split(";")[6]);
        latteMaccPrec = Integer.parseInt(dados.split(";")[7]);
        chocQuente = Integer.parseInt(dados.split(";")[8]);
        chocQuentePrec = Integer.parseInt(dados.split(";")[9]);
        dados = cafes.lerLinha();
      }
    }
    insumos.fecharArquivo();
    cafes.fecharArquivo();
  }

  public static void gravarDados() {
    insumos.abrirEscrita();
    cafes.abrirEscrita();
    String strInsumos = agua + ";" + cafe + ";" + leite_po + ";" + achoc + ";" + dia++;
    insumos.escreverLinha(strInsumos);
    String strCafes = cLongo + ";" + cLongoPrec + ";" + cCurto + ";" + cCurtoPrec + ";" + cappuccino + ";"
        + cappuccinoPrec + ";" + latteM + ";" + latteMaccPrec + ";" + chocQuente + ";" + chocQuentePrec;
    cafes.escreverLinha(strCafes);
    insumos.fecharArquivo();
    cafes.fecharArquivo();
  }
}