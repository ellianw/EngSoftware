public class Cafeteira {
  private int agua = 0; //Máximo de 20000ml
  private int cafe = 0, leite_po = 0, achoc = 0; //Máximo de 500g
  private int cLongo = 0, cCurto = 0, cappuccino = 0, latteM = 0, chocQuente = 0;
  private double cLongoPrec = 0.0, cCurtoPrec = 0.0, cappuccinoPrec = 0.0, latteMaccPrec = 0.0, chocQuentePrec = 0.0;  
  
  public int qtdCafe() {
    return cafe;
  }
  public int qtdAgua() {
    return agua;
  }
  public int qtdLeiteEmPo() {
    return leite_po;
  }
  public int qtdAchocolatado() {
    return achoc;
  }
  
  
  public int qtdCafeCurto() {
    return cCurto;
  }
  public int qtdCafeLongo() {
    return cLongo;
  }
  public int qtdCappuccino() {
    return cappuccino;
  }
  public int qtdLatteMacciato() {
    return latteM;
  }
  public int qtdChocolateQuente() {
    return chocQuente;
  }
  
  public boolean setCafes(int cc,int cl,int cpp,int lm,int cq) {
    if (cc<1 && cl<1 && cpp<1 && lm<1 && cq<1)
      return false;
    else {
      cLongo = cl; 
      cCurto = cc; 
      cappuccino = cpp;
      latteM = lm; 
      chocQuente = cq;
      return true;
    }
  }
  
  public boolean setInsumos(int ag, int cf, int lt, int cc){
    if (ag >0 && cf >0 && lt >0 && cc >0) {
      agua = ag;
      cafe = cf;
      leite_po = lt;
      achoc = cc;
      return true;
    } else 
      return false;
  }
  
  public boolean cafeCurto() {
    if (cafe < 8 && agua <25)
      return false;
    else { 
      cafe-=8;
      agua-=25;
      cCurto++;
      return true;
    }
  }
  
  public boolean cafeLongo() {
    if (cafe < 10 && agua <50)
      return false;
    else { 
      cafe-=10;
      agua-=50;
      cLongo++;
      return true;
    }
  }
  
  public boolean cappuccino() {
    if (cafe < 10 && agua <80 && leite_po <20 && achoc<10)
      return false;
    else { 
      cafe-=8;
      agua-=25;
      leite_po-=20;
      achoc-=10;
      cappuccino++;
      return true;
    }
  }
  
  public boolean latteMacciato() {
    if (cafe < 15 && agua <100 && leite_po <40 && achoc<20)
      return false;
    else { 
      cafe-=15;
      agua-=100;
      leite_po-=40;
      achoc-=20;
      latteM++;
      return true;
    }
  }      
  public boolean chocolateQuente() {
    if (agua <100 && leite_po <50 && achoc<30)
      return false;
    else { 
      agua-=100;
      leite_po-=50;
      achoc-=30;
      chocQuente++;
      return true;
    }
  }
  
  public double getCafeCurtoPreco() {
    return cCurtoPrec;
  }
  
  public boolean cafeCurtoPreco(double newPreco) {
    if (newPreco>0) {
      cCurtoPrec = newPreco;
      return true;
    } else 
      return false;
  }
  
  public double getCafeLongoPreco() {
    return cLongoPrec;
  }
  
  public boolean cafeLongoPreco(double newPreco) {
    if (newPreco>0) {
      cLongoPrec = newPreco;
      return true;
    } else 
      return false;
  }
  
  public double getCappuccinoPreco() {
    return cappuccinoPrec;
  }
  
  public boolean cappuccinoPreco(double newPreco) {
    if (newPreco>0) {
      cappuccinoPrec = newPreco;
      return true;
    } else 
      return false;
  }
  
  public double getLatteMacciatoPreco() {
    return latteMaccPrec;
  }
  
  public boolean latteMacciatoPreco(double newPreco) {
    if (newPreco>0) {
      latteMaccPrec = newPreco;
      return true;
    } else 
      return false;
  }
  
  public double getChocolateQuentePreco() {
    return chocQuentePrec;
  }
  
  public boolean chocolateQuentePreco(double newPreco) {
    if (newPreco>0) {
      chocQuentePrec = newPreco;
      return true;
    } else 
      return false;
  }
  
  public void abastecer () {
    agua = 2000; //Máximo de 20000ml
    cafe = 500; 
    leite_po = 500; 
    achoc = 500; //Máximo de 500g
  }
  
  public double saldoCaixa() {
    return (cCurto*cCurtoPrec)+(cLongo*cLongoPrec)+(cappuccino*cappuccinoPrec)+(latteM*latteMaccPrec)+(chocQuente*chocQuentePrec);
  }
}