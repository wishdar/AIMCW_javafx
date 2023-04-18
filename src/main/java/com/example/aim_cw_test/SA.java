package com.example.aim_cw_test;

import java.util.*;

public class SA {

    static Double[] solutions;
    static Double[] temps;
    private static double currentTemp;
    private static double currentSol;

    public static Double[] getTemps() {
        return temps;
    }

    public static Double[] getSolutions() {
        return solutions;
    }

    public static double getCurrentTemp() {
        return currentTemp;
    }

    public static void setCurrentTemp(double currentTemp){
        SA.currentTemp = currentTemp;
    }

    public static double getCurrentSol() {
        return currentSol;
    }

    public static void setCurrentSol(double currentSol) {
        SA.currentSol = currentSol;
    }


    public static List<City> solveTSP(Map<Integer, City> cities, int initialTemp, double coolingRate) {

        // Generate an initial solution
        List<City> currentSolution = TourGenerator.generateRandomTour(cities);

        //use 2-opt solution as initial solution
        //List<City> currentSolution = TwoOpt.twoOpt(cities);
        //System.out.println("Improved tour distance (2-opt): " + Main.calculateTourDistance(currentSolution));


        // Initialize the best solution as the 2-opt solution
        List<City> twooptsolution = TwoOpt.twoOpt(cities);
        System.out.println("Improved tour distance (2-opt): " + Main.calculateTourDistance(twooptsolution));
        List<City> bestSolution = new ArrayList<>(twooptsolution);

        // Set the initial temperature
        double temperature = initialTemp;

        // Iterate until the temperature reaches 1
        int i = 0;
        while (temperature > 1) {
            i += 1;
            // Generate a new solution by swapping two cities
            List<City> newSolution = new ArrayList<>(currentSolution);
            int index1 = (int) (newSolution.size() * Math.random());
            int index2 = (int) (newSolution.size() * Math.random());
            Collections.swap(newSolution, index1, index2);

            // Calculate the energy (total distance) of the new solution
            double currentEnergy = calculateEnergy(currentSolution);
            double newEnergy = calculateEnergy(newSolution);

            // Calculate the acceptance probability
            double acceptanceProbability = calculateAcceptanceProbability(currentEnergy, newEnergy, temperature);

            // Decide whether to accept the new solution
            if (acceptanceProbability > Math.random()) {
                currentSolution = newSolution;
                double currentSol = Main.calculateTourDistance(currentSolution);
                setCurrentSol(currentSol);
                setCurrentTemp(temperature);        //*****************************************************************************************************************************************
                solutions[i] = getCurrentSol();
                temps[i] = getCurrentTemp();
            }

            // Update the best solution if the new solution is better
            if (calculateEnergy(currentSolution) < calculateEnergy(bestSolution)) {
                bestSolution = new ArrayList<>(currentSolution);
            }

            // Reduce the temperature
            temperature *= 1 - coolingRate;
        }

        // Return the best solution
        return bestSolution;
    }

    // Calculate the total distance (energy) of a tour
    private static double calculateEnergy(List<City> tour) {
        double distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            distance += tour.get(i).distanceTo(tour.get(i + 1));
        }
        distance += tour.get(tour.size() - 1).distanceTo(tour.get(0));
        return distance;
    }

    // Calculate the acceptance probability for a new solution
    private static double calculateAcceptanceProbability(double currentEnergy, double newEnergy, double temperature) {
        if (newEnergy < currentEnergy) {
            return 1;
        } else {
            return Math.exp((currentEnergy - newEnergy) / temperature);
        }
    }
}
