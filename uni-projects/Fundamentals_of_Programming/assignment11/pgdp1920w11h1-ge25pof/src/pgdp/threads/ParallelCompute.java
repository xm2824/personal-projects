package pgdp.threads;

import java.math.BigInteger;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class ParallelCompute {


	/**
	 * apply each function to its corresponding data, e.g. functions[2] on data[2] <p>
	 * Using parallel implementation
	 * @param data
	 * @param functions
	 * @param threadCount
	 * @return
	 * @throws InterruptedException
	 */
	public static BigInteger[] parallelComputeFunctions(BigInteger[] data, Function<BigInteger, BigInteger>[] functions,
			int threadCount) throws InterruptedException {
		//!!! check the validity of the arguments
		if(data==null || functions == null) throw new NullPointerException();
		if(functions.length == 0 || data.length != functions.length || threadCount <= 0) throw new IllegalArgumentException();

		//* else
        //* 0. preparations
        int n = data.length;
        Thread threads[] = new Thread[threadCount];
        int work_load = n > threadCount? n/threadCount : 1;
        BigInteger[] results = new BigInteger[n];

        //!!! if n <= threadCount
        if(n<=threadCount){
            // then only the first n threads have work to do
            for (int i = 0; i < n; i++) {
                int finalI = i;
                threads[i] = new Thread(()->{
                    results[finalI] = functions[finalI].apply(data[finalI]);
                });
            }

            //??? still have to initialize the back threads to pass the test ???
            for (int i = n; i <threadCount ; i++) {
                threads[i] = new Thread();
            }



        }

        //!!! if n > threadCount
        else{
            // then each thread has to do a work of size $(work_load)
            // the last thread might differ

            for (int i = 0; i < threadCount-1; i++) {
                //* starting index
                int j = i*work_load;

                threads[i] = new Thread(()->{
                    for (int k = 0; k < work_load; k++) {
                        results[j+k] = functions[j+k].apply(data[j+k]);
                    }
                });

            }

            // for the last thread
            threads[threadCount-1] = new Thread(()->{
               int start_index = (threadCount-1) * work_load;
                for (int i = start_index; i <data.length  ; i++) {
                    results[i] = functions[i].apply(data[i]);
                }
            });




        }

        //* start and join all the threads
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        //* return
        return results;
	}

	/*
	reduce array w.r.t. @{code binOp} using parallel implementation
	 */
	public static BigInteger parallelReduceArray(BigInteger[] data, BinaryOperator<BigInteger> binOp, int threadCount)
			throws InterruptedException {
	    //!!! check the validity of the arguments
        if(data==null || binOp==null) throw new NullPointerException();
        if(data.length == 0 ||  threadCount <= 0) throw new IllegalArgumentException();

        //* preparations
        Thread threads[] = new Thread[threadCount];
        //!!! since the binOp is a general binary operation, i.e. we don't know the common initial value for it
        //!!! so we can only apply the binOp on data themselves and cannot define an initial value on our own
        //!!! that implies that each thread has to work for at least 2 data to complete the operation
        //!!! so we have to define a max number of working thread, here it's data.length /2
        int max_num_workingThread = data.length/2;
        int actual_num_workingThread = threadCount < max_num_workingThread? threadCount : max_num_workingThread;
        int work_load = data.length / actual_num_workingThread; //!!! work_load here is >= 2

        BigInteger[] results =new BigInteger[actual_num_workingThread];


        //!!! if threadcount > max_num_workingThread => the remaining threads do nothing
        if(threadCount>=max_num_workingThread){
            // 0. for the working threads
            for (int i = 0; i < actual_num_workingThread-1; i++) {
                int finalI = i;
                threads[i] = new Thread(()->{
                    //* starting index
                    int start_index = finalI *work_load;

                    BigInteger ACC = data[start_index];

                    for (int j = 1; j < work_load; j++) {
                        int index = start_index+j;
                        ACC = binOp.apply(ACC,data[index]);
                    }

                    //* store the subresult
                    results[finalI] = ACC;
                });
            }
            threads[actual_num_workingThread-1] = new Thread(()->{
                //* starting index
                int start_index = (actual_num_workingThread-1)*work_load;
                BigInteger ACC = data[start_index];
                for (int i = start_index+1; i <data.length ; i++) {
                    ACC = binOp.apply(ACC,data[i]);
                }
                //* store the subresult
                results[actual_num_workingThread-1] = ACC;
            });

            // 1. for the idle threads
            for (int i = actual_num_workingThread; i <threadCount ; i++) {
                threads[i] = new Thread();
            }
        }

        //* else => no idle thread
        else{
            // 0. for the working threads except the last one
            for (int i = 0; i < actual_num_workingThread-1; i++) {
                int finalI = i;
                threads[i] = new Thread(()->{
                    //* starting index
                    int start_index = finalI *work_load;

                    BigInteger ACC = data[start_index];

                    for (int j = 1; j < work_load; j++) {
                        int index = start_index+j;
                        ACC = binOp.apply(ACC,data[index]);
                    }

                    //* store the subresult
                    results[finalI] = ACC;
                });
            }

            // 1. for the last thread
            threads[actual_num_workingThread-1] = new Thread(()->{
                //* starting index
                int start_index = (actual_num_workingThread-1)*work_load;
                BigInteger ACC = data[start_index];
                for (int i = start_index+1; i <data.length ; i++) {
                    ACC = binOp.apply(ACC,data[i]);
                }
                //* store the subresult
                results[actual_num_workingThread-1] = ACC;
            });
        }

        //* start and join all threads
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        //* then recuce the subresults
        BigInteger ACC = results[0];
        for (int i = 1; i < actual_num_workingThread; i++) {
            ACC = binOp.apply(ACC,results[i]);
        }

        //* return
        return ACC;


	}

}
