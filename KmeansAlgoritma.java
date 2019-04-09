package kmeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Danar
 */
public class KmeansAlgoritma {

    public static ArrayList klaster(ArrayList<Data> data, int k) {
        ArrayList<ArrayList<Data>> klas = new ArrayList<ArrayList<Data>>(k);
        for (int i = 0; i < k; i++) {
            klas.add(new ArrayList<Data>());
        }
        for (Data x : data) {
            klas.get(x.getKlaster() - 1).add(x);
        }
        return klas;
    }

    public static ArrayList<Data> NormaliZedBright(ArrayList<Data> data) {
        int n = data.size();
        Double minValueBrigth = data.get(1).getBrightness();
        Double MaxValueBrigth = data.get(1).getBrightness();
        for (int i = 0; i < n; i++) {
            if (data.get(i).getBrightness() < minValueBrigth) {
                minValueBrigth = data.get(i).getBrightness();
            }
        }
        for (int i = 0; i < n; i++) {
            if (data.get(i).getBrightness() > MaxValueBrigth) {
                MaxValueBrigth = data.get(i).getBrightness();
            }
        }
        for (int i = 0; i < n; i++) {
                data.get(i).setBrightness((data.get(i).getBrightness()- minValueBrigth) / (MaxValueBrigth - minValueBrigth));
        }
        return data;
    }
    public static ArrayList<Data> NormaliZedlatitude(ArrayList<Data> data) {
        int n = data.size();
        Double minValueBrigth = data.get(1).getLatitude();
        Double MaxValueBrigth = data.get(1).getLatitude();
        for (int i = 0; i < n; i++) {
            if (data.get(i).getLatitude() < minValueBrigth) {
                minValueBrigth = data.get(i).getLatitude();
            }

        }
        for (int i = 0; i < n; i++) {
            if (data.get(i).getLatitude() > MaxValueBrigth) {
                MaxValueBrigth = data.get(i).getLatitude();
            }
        }
        for (int i = 0; i < n; i++) {
                data.get(i).setLatitude((data.get(i).getLatitude()- minValueBrigth) / (MaxValueBrigth - minValueBrigth));
        }
        return data;
    }
    public static ArrayList<Data> NormaliZedConf(ArrayList<Data> data) {
        int n = data.size();
        Double minValueBrigth = data.get(0).getConfidance();
        Double MaxValueBrigth = data.get(0).getConfidance();
        for (int i = 0; i < n; i++) {
            if (data.get(i).getConfidance() < minValueBrigth) {
                minValueBrigth = data.get(i).getConfidance();
            }

        }
        for (int i = 0; i < n; i++) {
            if (data.get(i).getConfidance() > MaxValueBrigth) {
                MaxValueBrigth = data.get(i).getConfidance();
            }
        }
        for (int i = 0; i < n; i++) {
                data.get(i).setConfidance((data.get(i).getConfidance()- minValueBrigth) / (MaxValueBrigth - minValueBrigth));
        }
        return data;
    }
    public static ArrayList<Data> NormaliZedlongitude(ArrayList<Data> data) {
        int n = data.size();
        Double minValueBrigth = data.get(0).getLongitude();
        Double MaxValueBrigth = data.get(0).getLongitude();
        for (int i = 0; i < n; i++) {
            if (data.get(i).getLongitude() < minValueBrigth) {
                minValueBrigth = data.get(i).getLongitude();
            }

        }
        for (int i = 0; i < n; i++) {
            if (data.get(i).getLongitude() > MaxValueBrigth) {
                MaxValueBrigth = data.get(i).getLongitude();
            }
        }
        for (int i = 0; i < n; i++) {
                data.get(i).setLongitude((data.get(i).getLongitude()- minValueBrigth) / (MaxValueBrigth - minValueBrigth));
        }
        return data;
    }
    //Untuk menghitung jumlah data yang ada di klaster
    public static double[][] sumOfFeaturesForCentroid(ArrayList data) {
        //plus 1 untuk nyimpen jumlah data yang ada diklaster
        double sumOfFeatures[][] = new double[data.size()][5];
        for (int ii = 0; ii < data.size(); ii++) {
            ArrayList<Data> x = (ArrayList<Data>) data.get(ii);
            for (int i = 0; i < x.size(); i++) {
                sumOfFeatures[ii][0] += x.get(i).getLatitude();
                sumOfFeatures[ii][1] += x.get(i).getLongitude();
                sumOfFeatures[ii][2] += x.get(i).getBrightness();
                sumOfFeatures[ii][3] += x.get(i).getConfidance();
                //hitung jumlah data yang ada di klaster
                sumOfFeatures[ii][4] += 1;
            }
        }
        return sumOfFeatures;
    }

    /*
    *   [klaster keberapa][fitur keberapa]
     */
    public static double[][] findCentroid(double[][] sumOfFeatures, int k) {
        double[][] centroid = new double[k][sumOfFeatures[0].length - 1];
        for (int i = 0; i < sumOfFeatures.length; i++) {
            for (int ii = 0; ii < sumOfFeatures[0].length - 1; ii++) {
                centroid[i][ii] = sumOfFeatures[i][ii] / sumOfFeatures[i][sumOfFeatures[i].length-1];
            }
        }
        return centroid;
    }

    /* urutan data sesuai dengan yang ada di ArrayList<Data> data
    * [data][klaster] = [data 1 klaster 1] [data 1 klaster 2] [data 1 klaster 3] [...] 
    *                   [data 2 klaster 1] [data 2 klaster 2] [data 2 klaster 3] [...]
    *                   [data 3 klaster 1] [data 3 klaster 2] [data 3 klaster 3] [...]
    *                   [..]               [..]               [..]               [...] 
     */
    
    //Manhattan
    public static double[][] calculateDistance(ArrayList<Data> data, double[][] centroid) {
        double[][] distance = new double[data.size()][centroid.length];
        for (int i = 0; i < data.size(); i++) {
            Data x = data.get(i);
            for (int ii = 0; ii < centroid.length; ii++) {
                double fitur[] = centroid[ii];
                // menggunakan manhattan distance                
                double dist = abs(x.getLatitude() - fitur[0]);
                dist += abs(x.getLongitude() - fitur[1]);
                dist += abs(x.getBrightness() - fitur[2]);
                dist += abs(x.getConfidance() - fitur[3]);
                // masukin ke array disrance
                distance[i][ii] = dist;
            }
        }
        return distance;
    }

    public static int[] renewCluster(ArrayList<Data> data, double[][] distance) {
        int klaster[] = new int[data.size()];
        for (int i = 0; i < distance.length; i++) {
            double min = distance[i][0];
            int idx = 0;
            for (int ii = 0; ii < distance[i].length; ii++) {
                if (distance[i][ii] < min) {
                    min = distance[i][ii];
                    idx = ii;
                }
            }
            data.get(i).setKlaster(idx + 1);
            //kenapa plus 1 karena index dimulai dari 0 sedangkan kita mau klaster dimulai dari 1
            klaster[i] = idx + 1;
        }
        return klaster;
    }

    public static double findFunction(double[][] distance, int[] renewCluster) {
        double sum[] = new double[distance[0].length];
        for (int i = 0; i < distance.length; i++) {
            for (int ii = 0; ii < renewCluster.length; ii++) {
                // kenapa renew cluster -1 karena saat di renewcuster,klasternya itu dimulai dari 1 bukand ari 0 jadi harus dikurang 1                
                sum[renewCluster[i] - 1] += distance[i][renewCluster[i] - 1];
            }
        }
        return Arrays.stream(sum).sum();
    }

    public static void KMeans(ArrayList<Data> data, int k, double threshold, double function) {
        ArrayList normalisasi = KmeansAlgoritma.NormaliZedBright(data);
        normalisasi = KmeansAlgoritma.NormaliZedlatitude(data);
        normalisasi = KmeansAlgoritma.NormaliZedlongitude(data);
        normalisasi = KmeansAlgoritma.NormaliZedConf(data);
        ArrayList groupedData = KmeansAlgoritma.klaster(normalisasi, k);
        double sumOfFeatures[][] = KmeansAlgoritma.sumOfFeaturesForCentroid(groupedData);
        double centroid[][] = KmeansAlgoritma.findCentroid(sumOfFeatures, k);
        double distance[][] = KmeansAlgoritma.calculateDistance(data, centroid);
        int[] renewCluster = KmeansAlgoritma.renewCluster(data, distance);
        double newFunction = KmeansAlgoritma.findFunction(distance, renewCluster);
        double delta = abs(newFunction - function);
        if (delta > threshold) {
            KMeans(data, k, threshold, newFunction);
        }
    }
}
