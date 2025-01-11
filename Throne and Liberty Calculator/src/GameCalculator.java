// public class GameCalculator {

//   public static double calculateEvasionChance(double evasion) {
//       return (evasion / (1000 + evasion)) * 100;
//   }

//   public static double calculateCritChance(double crit) {
//       return (crit / (1000 + crit)) * 100;
//   }

//   public static double calculateHeavyAttackChance(double heavyAttack) {
//       return (heavyAttack / (1000 + heavyAttack)) * 100;
//   }

//   public static double calculateAverageDamage(double minDamage, double maxDamage, double critChance, double heavyAttackChance) {
//       // Calculate base average damage
//       double averageBaseDamage = (minDamage + maxDamage) / 2;

//       // Calculate the average damage accounting for crit and heavy attack
//       double critMultiplier = critChance / 100;
//       double heavyAttackMultiplier = heavyAttackChance / 100;

//       return averageBaseDamage * (1 + critMultiplier + heavyAttackMultiplier);
//   }

//   public static double calculateDPS(double minDamage, double maxDamage, double attackSpeed, double crit, double heavyAttack) {
//       // Calculate crit chance and heavy attack chance
//       double critChance = calculateCritChance(crit);
//       double heavyAttackChance = calculateHeavyAttackChance(heavyAttack);

//       // Calculate average damage
//       double averageDamage = calculateAverageDamage(minDamage, maxDamage, critChance, heavyAttackChance);

//       // Calculate DPS as average damage times attack speed
//       return averageDamage * attackSpeed;
//   }

//   public static void main(String[] args) {
//       // Input parameters
//       double minDamage = 132;
//       double maxDamage = 251;
//       double attackSpeed = 0.582;
//       double crit = 1058; // e.g., 200 crit rating
//       double heavyAttack = 200; // e.g., 150 heavy attack rating
//       double evasion = 710; // e.g., 300 evasion rating

//       // Calculate Evasion Chance
//       double evasionChance = calculateEvasionChance(evasion);
//       System.out.println("Evasion Chance: " + evasionChance + "%");

//       // Calculate DPS
//       double dps = calculateDPS(minDamage, maxDamage, attackSpeed, crit, heavyAttack);
//       System.out.println("Damage per Second: " + dps);
//   }
// }

