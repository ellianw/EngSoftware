public class testeCafeteira {
  static int dia = 0;
  static String senhaCorreta = "panze";
  static Arquivo insumos = new Arquivo("insumos.csv");
  static Arquivo cafes = new Arquivo("cafes.csv");
  
  static Cafeteira c1 = new Cafeteira();
  
  public static void main (String[] args) {
    lerDados();
    String menu1 = "################MENU################"+
                   "\n[s] Cliente"+
                   "\n[a] Admin"+
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
  
  
  public static void cliente () {
    String menu = "################CAFÉS################\n"+
                  " 1) Café Curto\n"+
                  " 2) Café Longo\n"+
                  " 3) Cappuccino\n"+
                  " 4) Latte Macchiato\n"+
                  " 5) Chocolate Quente\n"+
                  " x) Voltar\n"+
                  "#####################################";
    char op = mostramenu(menu); 
    while (op!='x') {
      if (op == '1') {
        if (!c1.cafeCurto())
          Entrada.leiaString("Insumos insuficientes! Contate a administração.");
        else
          Entrada.leiaString("Café pronto!");
      } else if (op == '2') {
        //Café Longo
        if (!c1.cafeLongo())
          Entrada.leiaString("Insumos insuficientes! Contate a administração.");
        else
          Entrada.leiaString("Café pronto!");
      } else if (op == '3') {
        //Cappuccino
        if (!c1.cappuccino())
          Entrada.leiaString("Insumos insuficientes! Contate a administração.");
        else
          Entrada.leiaString("Café pronto!");
      } else if (op == '4') {
        //Latte Macchiato
        if (!c1.latteMacciato())
          Entrada.leiaString("Insumos insuficientes! Contate a administração.");
        else
          Entrada.leiaString("Café pronto!");
      } else if (op == '5') {
        //Chocolate Quente
        if (!c1.chocolateQuente())
          Entrada.leiaString("Insumos insuficientes! Contate a administração.");
        else
          Entrada.leiaString("Café pronto!");
      }
      op = mostramenu(menu);
    }
  }
  
  
  public static void admin () {
    validarAcesso();
    String menu = "################MENU################\n"+
      " 1) Ajuste de Preço\n"+
      " 2) Abastecimento\n"+
      " 3) Relatório\n"+
      " x) Voltar\n"+
      "####################################";
    char op = mostramenu(menu); 
    while (op!='x') {
      if (op == '1') {
        //Ajuste de preço
        altPreco();
      } else if (op == '2') {
        //Abastecimento
        abastecimento();
      } else if (op == '3') {
        //Relatório
        relatorio();
      }
      op = mostramenu(menu);
    }
  }
  
  public static void altPreco () {
    String menu = "################CAFÉS################\n"+
                  " 1) Café Curto\n"+
                  " 2) Café Longo\n"+
                  " 3) Cappuccino\n"+
                  " 4) Latte Macchiato\n"+
                  " 5) Chocolate Quente\n"+
                  " x) Voltar\n"+
                  "#####################################";
    char op = mostramenu(menu); 
    while (op!='x') {
      if (op == '1') {
        //Café curto
        c1.cafeCurtoPreco(Entrada.leiaDouble("Preço do Café Curto",c1.getCafeCurtoPreco()));
      } else if (op == '2') {
        //Café Longo
        c1.cafeLongoPreco(Entrada.leiaDouble("Preço do Café Longo",c1.getCafeLongoPreco()));
      } else if (op == '3') {
        //Cappuccino
        c1.cappuccinoPreco(Entrada.leiaDouble("Preço do Cappuccino",c1.getCappuccinoPreco()));
      } else if (op == '4') {
        //Latte Macchiato
        c1.latteMacciatoPreco(Entrada.leiaDouble("Preço do Latte Macchiato",c1.getLatteMacciatoPreco()));
      } else if (op == '5') {
        //Chocolate Quente
        c1.chocolateQuentePreco(Entrada.leiaDouble("Preço do Chocolate Quente",c1.getChocolateQuentePreco()));
      }
      op = mostramenu(menu);
    }
  }
  
  public static void abastecimento () {
    c1.abastecer();
    Entrada.leiaString("Cafeteira completamente abastecida!");
    return;
  }
  
  public static void relatorio() {
    double saldoCaixa = c1.saldoCaixa();
    String menu = "############Insumos#############\n"+
                  "Água:                 "+c1.qtdAgua()+"ml\n"+
                  "Café:                 "+c1.qtdCafe()+"g\n"+
                  "Leite em pó:          "+c1.qtdLeiteEmPo()+"g\n"+
                  "Achocolatado:         "+c1.qtdAchocolatado()+"g\n\n"+
                  "##########Cafés Sevidos##########\n"+
                  "Café curto:           "+c1.qtdCafeCurto()+"\n"+
                  "Café longo:           "+c1.qtdCafeLongo()+"\n"+
                  "Cappuccino:           "+c1.qtdCappuccino()+"\n"+
                  "Latte macchiato:      "+c1.qtdLatteMacciato()+"\n"+
                  "Chocolate quente:     "+c1.qtdChocolateQuente()+"\n"+
                  "#################################\n\n"+
                  "Total em caixa:       "+saldoCaixa;
    Entrada.leiaString(menu);
  }
  
  
  public static char mostramenu (String menu) {
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
        int agua = Integer.parseInt(dados.split(";")[0]);
        int cafe = Integer.parseInt(dados.split(";")[1]); 
        int leite_po = Integer.parseInt(dados.split(";")[2]); 
        int achoc = Integer.parseInt(dados.split(";")[3]);
        dia = Integer.parseInt(dados.split(";")[4]);
        c1.setInsumos(agua,cafe,leite_po,achoc);
        dados = insumos.lerLinha();
      }
    }
    if (cafes.abrirLeitura()) {
      String dados = cafes.lerLinha();
      while (dados != null) {
        int cLongo = Integer.parseInt(dados.split(";")[0]);
        c1.cafeLongoPreco(Double.parseDouble(dados.split(";")[1]));
        int cCurto = Integer.parseInt(dados.split(";")[2]);
        c1.cafeCurtoPreco(Double.parseDouble(dados.split(";")[3]));
        int cappuccino = Integer.parseInt(dados.split(";")[4]);
        c1.cappuccinoPreco(Double.parseDouble(dados.split(";")[5]));
        int latteM = Integer.parseInt(dados.split(";")[6]);
        c1.latteMacciatoPreco(Double.parseDouble(dados.split(";")[7]));
        int chocQuente = Integer.parseInt(dados.split(";")[8]);
        c1.chocolateQuentePreco(Double.parseDouble(dados.split(";")[9]));
        c1.setCafes(cCurto,cLongo,cappuccino,latteM,chocQuente);
        dados = cafes.lerLinha();
      }
    }
    insumos.fecharArquivo();
    cafes.fecharArquivo();
  }
  public static void gravarDados() {
    insumos.abrirEscrita();
    cafes.abrirEscrita();
    String strInsumos = c1.qtdAgua()+";"+c1.qtdCafe()+";"+c1.qtdLeiteEmPo()+";"+c1.qtdAchocolatado()+";"+dia++;
    insumos.escreverLinha(strInsumos);
    String strCafes = c1.qtdCafeLongo()+";"+c1.getCafeLongoPreco()+";"+c1.qtdCafeCurto()+";"+c1.getCafeCurtoPreco()+";"+c1.qtdCappuccino()
      +";"+c1.getCappuccinoPreco()+";"+c1.qtdLatteMacciato()+";"+c1.getLatteMacciatoPreco()+";"+c1.qtdChocolateQuente()+";"+c1.getChocolateQuentePreco();
    cafes.escreverLinha(strCafes);
    insumos.fecharArquivo();
    cafes.fecharArquivo();
  }
}