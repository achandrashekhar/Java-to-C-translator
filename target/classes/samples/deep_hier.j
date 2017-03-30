class Employee {
    int number;
    int getID() { return 123; }
    int something() {
      this.getID();
      return 99;
    }
}
class Mgr extends Employee {
    int level;
    Employee other;
}
class Coder extends Employee {
    Mgr boss;
}
class Pro extends Coder {
  Coder getCoder() {
      Coder c;
      c = new Coder();
      c.boss = new Mgr();
      c.boss.other = new Employee();
      return c;
    }
}

Pro pro;
pro = new Pro();

pro.boss = new Mgr();
pro.boss.other = new Employee();
pro.boss.other.number = pro.getCoder().boss.other.getID();

printf("%d\n", pro.boss.other.number);
printf("%d\n", pro.boss.getID());
printf("%d\n", pro.boss.something());
