import java.util.ArrayList;
import java.util.Collections;

public class JobSequencingProblemWay1 {
    static class Job {
        char jobID;
        int deadLine;
        int Profit;

        Job(char jobID, int deadLine, int Profit) {
            this.jobID = jobID;
            this.deadLine = deadLine;
            this.Profit = Profit;
        }
    }

    static void printJobScheduling(ArrayList<Job> arr, int t) {
        int n = arr.size();

        // Sort all jobs according to decreasing order of profit
        Collections.sort(arr, (a, b) -> b.Profit - a.Profit); // Doubt in understanding comparator of Collections.sort()

        // To keep track of free time slots
        boolean[] result = new boolean[t];

        // To store result (Sequence of jobs)
        char[] job = new char[t];

        for (int i = 0; i < n; i++) {
            // Find a free slot for this job (It should be noted that we start from the last
            // possible slot)
            for (int j = Math.min(t - 1, arr.get(i).deadLine - 1); j >= 0; j--) {
                // Free slot found
                if (result[j] == false) {
                    result[j] = true;
                    job[j] = arr.get(i).jobID;
                    break;
                }
            }
        }

        for (int i = 0; i < job.length; i++) {
            System.out.print(job[i] + " ");
        }
    }

    public static void main(String[] args) {
        ArrayList<Job> arr = new ArrayList<>();

        arr.add(new Job('a', 2, 100));
        arr.add(new Job('b', 1, 19));
        arr.add(new Job('c', 2, 27));
        arr.add(new Job('d', 1, 25));
        arr.add(new Job('e', 3, 15));

        System.out.println("Following is maximum " + "profit sequence of jobs");
        printJobScheduling(arr, 3);
    }
}