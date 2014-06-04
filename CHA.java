/*
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining CHA
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintCompilation CHA

Assembly:
The libhsdis disassembler library:
https://kenai.com/projects/base-hsdis/downloads/directory/gnu-versions

Java flags:
http://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html

Print (Intel) assembly for a specific method (instead of -XX:+PrintAssembly):
java -XX:+UnlockDiagnosticVMOptions -XX:PrintAssemblyOptions=intel -XX:CompileCommand="print AddOperation perform" CHA
*/
public class CHA {

    static Operation operation;
    static int result;

    public static void main(String[] args) throws Exception {
        operation = new AddOperation();
        benchmark();
    }

    public static void benchmark() throws Exception {
        for (int i = 0; i < 10; i++) {

            if (i == 7) Class.forName("SubstractOperation");

            long start = System.nanoTime();
            for (int j = 0; j < 1000000; j++) {
                result = operation.perform(j, 1);
            }
            long end = System.nanoTime();
            System.out.println((end - start) + " ns");
        }
        System.out.println(result);
    }
}

interface Operation {
    int perform(int a, int b);
}

class AddOperation implements Operation {

    static {
        System.out.println("AddOperation loaded");
    }

    @Override
    public int perform(int a, int b) {
        return a + b;
    }
}

class SubstractOperation implements Operation {

    static {
        System.out.println("SubstractOperation loaded");
    }

    @Override
    public int perform(int a, int b) {
        return a - b;
    }
}
