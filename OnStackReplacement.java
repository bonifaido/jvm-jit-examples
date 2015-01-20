/**
 * OnStackReplacement
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
 * -XX:-TieredCompilation
 */
public class OnStackReplacement {

    static int absSum(int a, int b) {
        return Math.abs(a) + Math.abs(b);
    }

    // Math.abs will be compiled (actually it's an intrinsic)
    // absSum will be compiled next
    // Math.abs will be inlined into absSum
    // than absSum will be inlined into main,
    // however main was started in interpreted mode.
    // see logs.
    // However, if you add -XX:-TieredCompilation to the mix,
    // the compilation and inlining around Math::abs and
    // OnStackReplacement::absSum will be stacked into one.

    public static void main(String[] args) throws Exception {
        for (long i = 0; ; i++) {
            int absSum = absSum(2, 3);
            if (i % 10000 == 0) {
                System.out.println("after " + i + " calls: " + absSum);
            }
        }
    }
}
