package pgdp.minijvm;

/**
 * JVM: test class
 */
public class JVM {
    public static void main(String[] args) {
        //* instructions
        Instruction[] code = new Instruction[]{
            // Das Programm berechnet die Summe der ganzen Zahlen von 1 bis 5
            new Alloc(2),
            new Const(0),
            new Store(0),
            new Const(5),
            new Store(1),
            new Const(0),
            new Load(1),
            new Less(),
            new FJump(18),
            new Load(0),
            new Load(1),
            new Add(),
            new Store(0),
            new Load(1),
            new Const(1),
            new Sub(),
            new Store(1),
            new Jump(5),
            new Halt()};
Simulator simulator = new Simulator(20, code) ;
simulator.executeInstructions();

        //* result
        System.out.println(simulator.getStack().toString());
    }
    
}