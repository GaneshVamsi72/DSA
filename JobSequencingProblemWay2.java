// Does not handle all cases !!!!!!!!!
import java.util.ArrayList;
import java.util.Collections;

public class JobSequencingProblemWay2 {

    static class Job {

        int deadLine;
        int profit;
        int id;

        public Job(int deadLine, int profit, int id) {
            this.deadLine = deadLine;
            this.profit = profit;
            this.id = id;
        }
    }

    public static void main(String[] args) {
        int[][] jobsInfo = {{4, 20}, {1, 20}, {1, 40}, {1, 30}};

        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 0; i < jobsInfo.length; i++) {
            jobs.add(new Job(jobsInfo[i][0], jobsInfo[i][1], i));
        }

        Collections.sort(jobs, (obj1, obj2) -> obj2.profit - obj1.profit); // Descending Order

        ArrayList<Integer> sequence = new ArrayList<>();

        int time = 0;
        for (int i = 0; i < jobs.size(); i++) {
            Job currJob = jobs.get(i);
            if (currJob.deadLine > time) {
                sequence.add(currJob.id);
                time++;
            }
        }

        System.out.println("Sequence of Jobs for Max Profit : ");
        for (int i = 0; i < sequence.size(); i++) {
            System.out.print((sequence.get(i) + 1) + " ");
        }
    }
}
