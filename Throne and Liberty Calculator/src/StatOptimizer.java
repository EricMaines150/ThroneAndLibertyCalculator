public class StatOptimizer {

  private static final int TOTAL_POINTS = 50;

  public static void main(String[] args) {
    GameStats bestDpsStats = new GameStats(); // Initialize with base stats
    GameStats bestEvasionStats = new GameStats(); // Initialize with base stats
    double maxDPS = bestDpsStats.calculateDPS();
    double maxEvasion = bestEvasionStats.calculateEvasionChance();

    System.out.println("Base DPS: " + maxDPS);
    System.out.println("Base Evasion: " + maxEvasion + "%");
    System.out.println();

    // Iterate through possible allocations of 49 points across four stats, loop
    // startes at 34 and maxes at 34 due to double cost of investing more than 20
    // points.
    for (int strPoints = 0; strPoints <= 34; strPoints++) {
      for (int dexPoints = 0; dexPoints <= 34; dexPoints++) {
        for (int wisPoints = 0; wisPoints <= 34; wisPoints++) {
          for (int perPoints = 34; perPoints >= 0; perPoints--) { // Start at max and decrement until within point limit

            // Calculate total cost of this allocation
            int pointsAllocated = calculateCost(strPoints) + calculateCost(dexPoints) +
                calculateCost(wisPoints) + calculateCost(perPoints);
            // System.out.println("pointsAllocated: " + pointsAllocated);

            // Check if total cost to allocate points matches or exceeds available points
            if (TOTAL_POINTS <= pointsAllocated) {
              continue;
            }

            // Debugging statements
            // System.out.println("total points" + TOTAL_POINTS);
            // System.out.println("pointsAllocated: " + pointsAllocated);

            // Initialize stats with base values
            GameStats stats = new GameStats();

            // Allocate points to each stat
            stats.allocatePoints("Strength", strPoints, pointsAllocated);
            stats.allocatePoints("Dexterity", dexPoints, pointsAllocated);
            stats.allocatePoints("Wisdom", wisPoints, pointsAllocated);
            stats.allocatePoints("Perception", perPoints, pointsAllocated);

            // Calculate DPS and evasion for the current stat distribution
            double dps = stats.calculateDPS();
            double evasion = stats.calculateEvasionChance();

            // Print calculated values for debugging
            // System.out.println("DPS: " + dps + ", Evasion: " + evasion + "% for
            // allocation - "
            // + "StrengthPoints: " + strPoints + ", DexterityPoints: " + dexPoints
            // + ", WisdomPoints: " + wisPoints + ", PerceptionPoints: " + perPoints);
            // System.out.println();
            // stats.displayStats();
            // System.out.println();

            // Track best DPS configuration
            if (dps > maxDPS) {
              maxDPS = dps;
              bestDpsStats = stats;
              // System.out.println("New Best DPS Found: " + dps);
              // stats.displayStats();
              // System.out.println("total points: " + TOTAL_POINTS);
              // System.out.println("pointsAllocated: " + pointsAllocated);
              // System.out.println("DPS: " + dps + ", Evasion: " + evasion + "% for
              // allocation - "
              // + "StrengthPoints: " + strPoints + ", DexterityPoints: " + dexPoints
              // + ", WisdomPoints: " + wisPoints + ", PerceptionPoints: " + perPoints);
              // System.out.println();
            }

            // Track best Evasion configuration
            if (evasion > maxEvasion) {
              maxEvasion = evasion;
              bestEvasionStats = stats;
              // System.out.println("New Best Evasion Found: " + evasion + "%");
            }
          }
        }
      }
    }

    // Display the best configurations
    System.out.println("Best DPS Configuration:");
    if (bestDpsStats != null)
      bestDpsStats.displayStats();

    System.out.println("\nBest Evasion Configuration:");
    if (bestEvasionStats != null)
      bestEvasionStats.displayStats();
  }

  // Helper function to calculate the true cost of allocating points, accounting
  // for cost doubling
  private static int calculateCost(int points) {
    if (points <= 20)
      return points; // No doubling for first 20 points
    return 20 + (points - 20) * 2; // Double cost for points beyond 20
  }
}
