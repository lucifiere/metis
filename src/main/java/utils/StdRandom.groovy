package utils

/**
 *  Created by Created by XD.Wang on 2016/9/1.
 *  随机数工具类，对Random做一层薄包装
 */
class StdRandom {

    private static Random random
    private static long seed

    /**
     * 初始化
     */
    static {
        seed = System.currentTimeMillis()
        random = new Random(seed)
    }

    private StdRandom(){}

    /**
     * 修改随机种子
     */
    public static void setSeed(long s){
        seed = s
        random = new Random(seed)
    }

    /**
     * 获取当前随机种子
     */
    public static long getSeed(){
        seed
    }

    /**
     * 获取随机数，范围在[0,1）
     */
    public static double uniform(){
        random.nextDouble()
    }

    /**
     * 获取随机数，返回随机整数
     */
    public static int uniform(int limit){
        if(limit <= 0) throw new IllegalArgumentException('上限只能是正整数')
        random.nextInt(limit)
    }

    /**
     * 获取随机数，返回范围内的随机整数
     */
    public static int uniform(int a, int b){
        if(b <= a){
            int tmp = b;
            b = a;
            a= tmp;
        }
        if(b - a >= Integer.MAX_VALUE)
            throw new IllegalArgumentException("范围太大");
        a + uniform(b - a)
    }

    /**
     * 获取随机数，返回范围内的随机小数
     */
    public static double uniform(double a, double b){
        a + uniform()*(b-a)
    }

    /**
     * 伯努力分布
     */
    public static boolean bernoulli(){
        bernoulli(0.50F)
    }

    /**
     * 符合期望几率的伯努利分布
     */
    public static boolean bernoulli(float probability){
        if(probability > 1.00F || probability < 0.00F)
            throw new IllegalArgumentException("概率值不能大于100%或小于0%")
        uniform() < probability
    }

    /**
     * 高斯分布
     */
    public static double gaussian(){
        double r, x, y
        while(true){
            x = uniform(-1.0, 1.0)
            y = uniform(-1.0, 1.0)
            r = x*x + y*y
            if (r < 1.00d && r != 0.00D) break
        }
        x * Math.sqrt(-2 * Math.log(r) / r)
    }

    public static double gaussian(double mu, double sigma) {
        mu + sigma * gaussian()
    }

    /**
     * 几何分布
     */
    public static int geometric(double p) {
        if (!(p >= 0.0 && p <= 1.0))
            throw new IllegalArgumentException("概率值不能大于100%或小于0%")
        (int) Math.ceil(Math.log(uniform()) / Math.log(1.0 - p))
    }

    /**
     * 泊松分布
     */
    public static int poisson(double lambda) {
        if (!(lambda > 0.0))
            throw new IllegalArgumentException("lambda只能为正")
        if (Double.isInfinite(lambda))
            throw new IllegalArgumentException("lambda不能取无限")
        int k = 0
        double p = 1.0
        double expLambda = Math.exp(-lambda)
        while(true){
            k++
            p *= uniform()
            if(p < expLambda) break
        }
        k - 1
    }

    /**
     * 帕累托分布
     */
    public static double pareto(double alpha) {
        if (!(alpha > 0.0))
            throw new IllegalArgumentException("alpha只能为正")
        Math.pow(1 - uniform(), -1.0/alpha) - 1.0
    }

    /**
     * 柯西分布
     */
    public static double cauchy() {
        Math.tan(Math.PI * (uniform() - 0.5))
    }

    public static void shuffle(Object[] a) {
        if (a == null) throw new NullPointerException("传参为空")
        int n = a.length
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n-i);
            Object temp = a[i]
            a[i] = a[r]
            a[r] = temp
        }
    }

    /**
     * 数组洗排
     */
    public static void shuffle(double[] a) {
        if (a == null) throw new NullPointerException("传参为空")
        int n = a.length
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i)
            double temp = a[i]
            a[i] = a[r]
            a[r] = temp
        }
    }

    public static void shuffle(int[] a) {
        if (a == null) throw new NullPointerException("传参为空")
        int n = a.length
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n-i)
            int temp = a[i]
            a[i] = a[r]
            a[r] = temp
        }
    }

    /**
     * 数组按范围洗排
     */
    public static void shuffle(Object[] a, int lo, int hi) {
        if (a == null) throw new NullPointerException("传参为空")
        if (lo < 0 || lo > hi || hi >= a.length) {
            throw new IndexOutOfBoundsException("数组越界")
        }
        for (int i = lo; i <= hi; i++) {
            int r = i + uniform(hi-i+1);
            Object temp = a[i]
            a[i] = a[r]
            a[r] = temp
        }
    }

    public static void shuffle(double[] a, int lo, int hi) {
        if (a == null) throw new NullPointerException("传参为空")
        if (lo < 0 || lo > hi || hi >= a.length) {
            throw new IndexOutOfBoundsException("数组越界")
        }
        for (int i = lo; i <= hi; i++) {
            int r = i + uniform(hi-i+1)
            double temp = a[i]
            a[i] = a[r]
            a[r] = temp
        }
    }

    public static void shuffle(int[] a, int lo, int hi) {
        if (a == null) throw new NullPointerException("传参为空")
        if (lo < 0 || lo > hi || hi >= a.length) {
            throw new IndexOutOfBoundsException("数组越界")
        }
        for (int i = lo; i <= hi; i++) {
            int r = i + uniform(hi-i+1)
            int temp = a[i]
            a[i] = a[r]
            a[r] = temp
        }
    }

}
