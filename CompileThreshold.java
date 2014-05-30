/*
/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/bin/java -XX:+UnlockDiagnosticVMOptions -XX:+PrintCompilation -Dloop=9999 CompileThreshold
*/
public class CompileThreshold {
    public static void main(String[] args) {
        int loop = Integer.getInteger("loop", 10000);
        int result = 0;
        for (int i = 0; i < loop; i++) { 
            result = adder(i, 1);
        }
        System.out.println(result);
    }

    static int adder(int a, int b) {
        return a + b;
    }
}
