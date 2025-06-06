package heap;

import java.util.*;

public class MinimumCostToHireKWorkers {
    // brute force approach o(n * (n + nlogk))
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        //choose one manager and check of all workers can be given wages based on both condotions.
        int n = quality.length;
        double leastWage = Double.MAX_VALUE;
        // o(n)
        for(int manager = 0; manager < n; manager++){
            double wageRatio = (double) wage[manager]/quality[manager];
            List<Integer> groupOfWorker = new ArrayList<>();
            // o(n)
            for (int worker = 0; worker < n; worker++){
                double givenWage = (double) wageRatio * quality[worker];
                if(givenWage >= wage[worker]){
                    groupOfWorker.add(worker);
                }
            }
            // now select k workers and check least total wage
            if(groupOfWorker.size() < k) continue;
            // find k minimum wages
            PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
            double sumWage = 0.0;
            //o(nlogk)
            for (Integer i : groupOfWorker){
                sumWage = sumWage + wageRatio*quality[i];
                pq.offer(wageRatio*quality[i]);
                if (pq.size() > k){
                    sumWage -= pq.poll();
                }
            }
            if (sumWage < leastWage) leastWage = sumWage;
        }
        return leastWage;
    }

    // slightly better brute force o(n + nlogn + n*nlogk)
    public double mincostToHireWorkers1(int[] quality, int[] wage, int k) {
        //choose one manager and check of all workers can be given wages based on both condotions.
        int n = quality.length;
        double leastWage = Double.MAX_VALUE;
        // a worker can be manager if and only if it's wage to priority ratio is greater than atleast k-1 workers.
        List<double[]> workers = new ArrayList<>();
        //o(n)
        for(int i = 0; i<n; i++){
            double[] arr = new double[2];
            arr[0] = (double) wage[i]/quality[i];
            arr[1] = quality[i];
            workers.add(arr);
        }
        // sort based on wage ratio
        // o(nlogn)
        workers.sort(Comparator.comparingDouble(p -> p[0]));
        // o(n)
        for(int manager = k-1; manager < n; manager++){
            double wageRatio = workers.get(manager)[0];
            // find k minimum wages
            PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
            double sumWage = 0.0;
            // o(nlogk)
            for (int i = 0; i<=manager; i++){
                double givenWage = wageRatio*workers.get(i)[1];
                sumWage += givenWage;
                pq.offer(givenWage);
                if (pq.size() > k){
                    sumWage -= pq.poll();
                }
            }
            if (sumWage < leastWage) leastWage = sumWage;
        }
        return leastWage;
    }

    //optimal approach
    public double mincostToHireWorkers2(int[] quality, int[] wage, int k) {
        //choose one manager and check of all workers can be given wages based on both condotions.
        int n = quality.length;
        double leastWage = Double.MAX_VALUE;
        // a worker can be manager if and only if it's wage to priority ratio is greater than atleast k-1 workers.
        List<double[]> workers = new ArrayList<>();
        //o(n)
        for(int i = 0; i<n; i++){
            double[] arr = new double[2];
            arr[0] = (double) wage[i]/quality[i];
            arr[1] = quality[i];
            workers.add(arr);
        }
        // sort based on wage ratio
        // o(nlogn)
        workers.sort(Comparator.comparingDouble(p -> p[0]));
        // only storing priority in queue to find least k priority and then multiply it with wage_to_quality_ratio of manager
        // to get least total wage for a manager we need k smallest quality so using max heap for it.
        PriorityQueue<Double> pq = new PriorityQueue<>(Comparator.reverseOrder());
        double totalQuality = 0.0;
        // iterate over worker list and make a manager (manager>=k) and find total wage
        //0(nlogk)
        for(int manager = 0; manager < n;manager++){
            double qual = workers.get(manager)[1];
            double wageQualityRatio = workers.get(manager)[0];
            pq.add(qual);
            totalQuality+=qual;
            if (pq.size() > k){
                totalQuality-=pq.poll();
            }
            if(pq.size() == k){
                double totalWage = totalQuality * wageQualityRatio;
                if(totalWage < leastWage) leastWage = totalWage;
            }

        }
        return leastWage;
    }

}

