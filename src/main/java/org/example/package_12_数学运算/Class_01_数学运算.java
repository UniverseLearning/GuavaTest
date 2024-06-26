package org.example.package_12_数学运算;

import com.google.common.math.BigIntegerMath;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:47
 * @Version v2.0
 */

/**
 * 为什么使用 Guava Math
 * Guava Math 针对各种不常见的溢出情况都有充分的测试；对溢出语义，Guava 文档也有相应的说明；如果运算的溢出检查不能通过，将导致快速失败；
 * Guava Math 的性能经过了精心的设计和调优；虽然性能不可避免地依据具体硬件细节而有所差异，但 Guava Math 的速度通常可以与 Apache Commons 的 MathUtils 相比，在某些场景下甚至还有显著提升；
 * Guava Math 在设计上考虑了可读性和正确的编程习惯；IntMath.log2(x, CEILING) 所表达的含义，即使在快速阅读时也是清晰明确的。而 32-Integer.numberOfLeadingZeros(x – 1)对于阅读者来说则不够清晰。
 */
public class Class_01_数学运算 {

    public static void main(String[] args) {

//        int logFloor = LongMath.log2(n, FLOOR);
//        int mustNotOverflow = IntMath.checkedMultiply(x, y);
//        long quotient = LongMath.divide(knownMultipleOfThree, 3, RoundingMode.UNNECESSARY); // fail fast on non-multiple of 3
//        BigInteger nearestInteger = DoubleMath.roundToBigInteger(d, RoundingMode.HALF_EVEN);
//        BigInteger sideLength = BigIntegerMath.sqrt(area, CEILING);


//        test1();
        test2();
        test3();
    }

    private static void test1() {
        /**
         * 整数运算
         * Guava Math 主要处理三种整数类型：int、long 和 BigInteger。这三种类型的运算工具类分别叫做 IntMath、LongMath 和 BigIntegerMath。
         */

        /**
         * 有溢出检查的运算
         * Guava Math 提供了若干有溢出检查的运算方法：结果溢出时，这些方法将快速失败而不是忽略溢出
         *
         *  IntMath.checkedAdd	        LongMath.checkedAdd
         *  IntMath.checkedSubtract	    LongMath.checkedSubtract
         *  IntMath.checkedMultiply	    LongMath.checkedMultiply
         *  IntMath.checkedPow	        LongMath.checkedPow
         */

        final int i = IntMath.checkedAdd(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private static void test2() {

        /**
         * 实数运算
         * IntMath、LongMath 和 BigIntegerMath 提供了很多实数运算的方法，并把最终运算结果舍入成整数。这些方法接受一个 java.math.RoundingMode 枚举值作为舍入的模式：
         *
         *      DOWN：向零方向舍入（去尾法）
         *      UP：远离零方向舍入
         *      FLOOR：向负无限大方向舍入
         *      CEILING：向正无限大方向舍入
         *      UNNECESSARY：不需要舍入，如果用此模式进行舍入，应直接抛出 ArithmeticException
         *      HALF_UP：向最近的整数舍入，其中 x.5 远离零方向舍入
         *      HALF_DOWN：向最近的整数舍入，其中 x.5 向零方向舍入
         *      HALF_EVEN：向最近的整数舍入，其中 x.5 向相邻的偶数舍入
         *
         * 这些方法旨在提高代码的可读性，例如，divide(x, 3, CEILING) 即使在快速阅读时也是清晰。此外，这些方法内部采用构建整数近似值再计算的实现，除了在构建 sqrt（平方根）运算的初始近似值时有浮点运算，其他方法的运算全过程都是整数或位运算，因此性能上更好。
         *
         *      运算	            IntMath	                            LongMath	                        BigIntegerMath
         *      除法	            divide(int, int, RoundingMode)	    divide(long, long, RoundingMode)	divide(BigInteger, BigInteger, RoundingMode)
         *      2为底的对数	    log2(int, RoundingMode)	            log2(long, RoundingMode)	        log2(BigInteger, RoundingMode)
         *      10为底的对数	    log10(int, RoundingMode)	        log10(long, RoundingMode)	        log10(BigInteger, RoundingMode)
         *      平方根	        sqrt(int, RoundingMode)	            sqrt(long, RoundingMode)	        sqrt(BigInteger, RoundingMode)
         */

        System.out.println(BigInteger.TEN.pow(99));

        // returns 31622776601683793319988935444327185337195551393252
        System.out.println(BigIntegerMath.sqrt(BigInteger.TEN.pow(99), RoundingMode.HALF_EVEN));

        System.out.println(IntMath.divide(10, 6, RoundingMode.DOWN));
        System.out.println(IntMath.divide(10, 6, RoundingMode.UP));
        System.out.println(IntMath.divide(10, 6, RoundingMode.FLOOR));
        System.out.println(IntMath.divide(10, 6, RoundingMode.CEILING));
//        System.out.println(IntMath.divide(10, 6, RoundingMode.UNNECESSARY));
        System.out.println(IntMath.divide(10, 6, RoundingMode.HALF_UP));
        System.out.println(IntMath.divide(10, 6, RoundingMode.HALF_DOWN));
        System.out.println(IntMath.divide(10, 6, RoundingMode.HALF_EVEN));
    }

    private static void test3() {
        /**
         * 附加功能
         * Guava 还另外提供了一些有用的运算函数
         *
         *      运算	            IntMath	            LongMath	        BigIntegerMath*
         *      最大公约数	    gcd(int, int)	    gcd(long, long)	    BigInteger.gcd(BigInteger)
         *      取模	            mod(int, int)	    mod(long, long)	    BigInteger.mod(BigInteger)
         *      取幂	            pow(int, int)	    pow(long, int)	    BigInteger.pow(int)
         *      是否 2的幂	    isPowerOfTwo(int)	isPowerOfTwo(long)	isPowerOfTwo(BigInteger)
         *      阶乘*	        factorial(int)	    factorial(int)	    factorial(int)
         *      二项式系数*	    binomial(int, int)	binomial(int, int)	binomial(int, int)
         */
    }

    private static void test4() {
        /**
         * 浮点数运算
         * JDK 比较彻底地涵盖了浮点数运算，但 Guava 在 DoubleMath 类中也提供了一些有用的方法。
         *
         *  isMathematicalInteger(double)	        判断该浮点数是不是一个整数
         *  roundToInt(double, RoundingMode)	    舍入为 int；对无限小数、溢出抛出异常
         *  roundToLong(double, RoundingMode)	    舍入为 long；对无限小数、溢出抛出异常
         *  roundToBigInteger(double, RoundingMode)	舍入为 BigInteger；对无限小数抛出异常
         *  log2(double, RoundingMode)	            2 的浮点对数，并且舍入为 int，比 JDK 的 Math.log(double) 更快
         */
    }
}
