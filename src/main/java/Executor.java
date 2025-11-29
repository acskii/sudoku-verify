// Abdo & Andrew :)

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor {
    public static void start(int[][] board, String mode) {
        // Runs implementations for modes
    }

    // Andrew :)
    /*
        For context, this function is expecting a task list created using Callable interface and expects it to return
        List<String>

        Reason being in the requirements, it was requested to print out invalids for all rows, columns and blocks.
        Meaning a task run may have multiple INVALIDS, therefore it will expect a possibility of multiple results.

        e.g. of a task:
        public class Task implements Callable<List<String>> {
            public Task() { ... }

            @Override
            public List<String> call() {
                // .. code here ..
            }
        }

        Each task will be run by one thread.
        If you have more tasks than threads, ExecutorService will queue tasks until a thread frees.
    */
    private static List<String> runTasks(List<Callable<List<String>>> tasks, int poolSize) {
        /* Create an ExecutorService resource with a specific thread pool size */
        try (ExecutorService executor = Executors.newFixedThreadPool(poolSize)) {
            /* It receives Callable tasks that run a list of string after thread execution */
            List<Future<List<String>>> futures = executor.invokeAll(tasks);
            List<String> all = new ArrayList<>();
            for (Future<List<String>> future : futures) {
                /* Wait for thread to return result */
                List<String> violations = future.get();
                if (violations != null && !violations.isEmpty()) all.addAll(violations);
            }
            /* Return results */
            return all;
        } catch (Exception err) {
            Thread.currentThread().interrupt();
            System.err.println("Execution interrupted: " + err.getMessage());
        }

        return new ArrayList<>();
    }
}
