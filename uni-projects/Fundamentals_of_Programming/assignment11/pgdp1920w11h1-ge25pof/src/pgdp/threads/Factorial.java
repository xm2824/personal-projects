package pgdp.threads;

import java.math.BigInteger;

public class Factorial {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(facParallel(10,20));
	}


	/**
	 * calculate the factorial using sequential implementation<p>
	 * for negative number n an IllegalArgumentException should be thrown
	 */
	public static BigInteger facSequential(int n) {
		//!!! if n is negative, throw an exception
		if(n<0){
			throw new IllegalArgumentException();
		}

		//!!! 0! = 1
		if(n==0 || n ==1){
			return BigInteger.ONE;
		}

		//* for positive n
		else{
			BigInteger ACC = BigInteger.ONE;
			for (int i = n; i >1; i--) {
				// 0. first transform i to BigInteger
				BigInteger from_i = BigInteger.valueOf(i);
				
				// 1. multiply i to ACC
				ACC = ACC.multiply(from_i);
			}

			// return
			return ACC;
		}
	}


	/**
	 * implement factorial using parallel implementation, i.e. distribute the multiplications uniformly to each thread
	 * <p>
	 * if n< 0 or threadCount <= 0, an exception should be thrown  
	 * @param n
	 * @param threadCount
	 * @return
	 * @throws InterruptedException
	 */
	public static BigInteger facParallel(int n, int threadCount) throws InterruptedException {
		//!!! check the validity of arguments
		if(n<0 || threadCount<=0){
			throw new IllegalArgumentException();
		}

		//* else
		//* 0. first create an array of threads
        //*  and the results of each threads
		Thread threads[] = new Thread[threadCount];
		BigInteger[] results = new BigInteger[threadCount];

		//!!! the average work load
        int work_load = n>threadCount? n/threadCount : 1;

		//* 1. initialize first $(threadCount)-1 threads to do the separate multiplications,
        //* since the work load for the last thread might differ
        //* for each thread (threads[i]): i*work_load+1 ~(i+1)*work_load
		{
			for (int i = 0; i < threads.length-1; i++) {
                int finalI = i;
                threads[i] = new Thread(()->{
				    BigInteger ACC = BigInteger.ONE;

                    for (int j = finalI*work_load+1; j <= (finalI+1)*work_load   ; j++) {
                        // 0. transform j to big integer
                        BigInteger tmp = BigInteger.valueOf(j);

                        // 1. multiply it to ACC
                        ACC = ACC.multiply(tmp);
                    }

                    results[finalI] = ACC;

                });
			}
		}

		//* 2. initialize the last thread
        //* let i = treadCount-1
        //* the range is i*work_load+1 ~ n
        {
            int i = threadCount-1;
            threads[i] = new Thread(()->{
                BigInteger ACC = BigInteger.ONE;

                for (int j = i *work_load+1; j <= n   ; j++) {
                    // 0. transform j to big integer
                    BigInteger tmp = BigInteger.valueOf(j);

                    // 1. multiply it to ACC
                    ACC = ACC.multiply(tmp);
                }
                results[i] = ACC;
            });
        }

        //* 3. start and join all threads
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        //* 4. multiply all results sequentially
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i <(n>threadCount? threadCount : n); i++) {
            result = result.multiply(results[i]);
        }

        //* return
        return result;

	}

}
