public class GameStats {

    // Initial stats
    private int strength = 21;
    private int dexterity = 24;
    private int wisdom = 10;
    private int perception = 10;

    // Base stats
    private double baseMinDamage = 100;
    private double baseMaxDamage = 199;
    private double baseCritChance = 1053;
    private double baseEvasion = 708;
    private double baseHeavyAttackChance = 100;
    private double baseAttackSpeed = 5.84;

    // Current values (modified by attributes and breakpoints)
    private double minDamage;
    private double maxDamage;
    private double critChance;
    private double evasion;
    private double heavyAttackChance;
    private double attackSpeed;

    public GameStats() {
        applyBaseStats();
        applyAttributes();
    }

    // Apply base stats to current values
    private void applyBaseStats() {
        minDamage = baseMinDamage;
        maxDamage = baseMaxDamage;
        critChance = baseCritChance;
        evasion = baseEvasion;
        heavyAttackChance = baseHeavyAttackChance;
        attackSpeed = baseAttackSpeed;
    }

    // Apply the effects of all attribute points
    private void applyAttributes() {
        applyStrength();
        applyDexterity();
        applyWisdom();
        applyPerception();
    }

    // Apply strength effects
    private void applyStrength() {
        // Strength adds +1 to both min and max damage per point
        minDamage += strength;
        maxDamage += strength;

        // Breakpoint at 50 points in Strength for heavy attack bonus
        if (strength >= 50) {
            heavyAttackChance = 200;
        }
    }

    // Apply dexterity effects
    private void applyDexterity() {
        // Dexterity effects per point
        maxDamage += dexterity; // +1 to max damage per point
        evasion += dexterity * 2; // +2 to evasion per point
        critChance += dexterity * 5; // +5 to crit chance per point
        attackSpeed -= dexterity * 0.002; // -0.002 attack speed per point

        // Breakpoints for Dexterity
        if (dexterity >= 30) {
            critChance += 50; // Additional bonus to crit chance at 30 Dex
        }
        if (dexterity >= 40) {
            minDamage += 15; // +15 min and max damage at 40 Dex
            maxDamage += 15;
        }
    }

    // Apply wisdom effects
    private void applyWisdom() {
        maxDamage += wisdom; // +1 to max damage per point of Wisdom
    }

    // Apply perception effects
    private void applyPerception() {
        double perceptionDamageBonus = perception * 1.2;
        minDamage += perceptionDamageBonus;
        maxDamage += perceptionDamageBonus;
    }

    // Function to calculate total DPS
    public double calculateDPS() {
        double critMultiplier = calculateCritChance() / 100;
        double heavyAttackMultiplier = calculateHeavyAttackChance() / 50;

        // Average damage with crit and heavy attack
        double averageDamage = ((minDamage + maxDamage) / 2) * (1 + critMultiplier + heavyAttackMultiplier);

        // DPS calculation
        return averageDamage * attackSpeed;
    }

    // Helper functions for crit and heavy attack chances
    private double calculateCritChance() {
        return critChance / (1000 + critChance) * 100;
    }

    private double calculateHeavyAttackChance() {
        return heavyAttackChance / (1000 + heavyAttackChance) * 100;
    }

    // Evasion chance calculation
    public double calculateEvasionChance() {
        return evasion / (1000 + evasion) * 100;
    }

    // Method to allocate points, adjusting cost per stat as described
    public boolean allocatePoints(String stat, int points, int pointsAllocated) {
        if (points <= 0) return false;
        
        if (points < pointsAllocated) {
            switch (stat) {
                case "Strength" -> strength += points;
                case "Dexterity" -> dexterity += points;
                case "Wisdom" -> wisdom += points;
                case "Perception" -> perception += points;
            }
            applyAttributes(); // Reapply attributes to update stats
            return true;
        }
        return false; // Insufficient points
    }

    // Display method for testing purposes
    public void displayStats() {
        System.out.println();
        System.out.println("Min Damage: " + minDamage);
        System.out.println("Max Damage: " + maxDamage);
        System.out.println("Crit Chance: " + calculateCritChance() + "%");
        System.out.println("Heavy Attack Chance: " + calculateHeavyAttackChance() + "% " + heavyAttackChance);
        System.out.println("Evasion Chance: " + calculateEvasionChance() + "%");
        System.out.println("Attack Speed: " + attackSpeed);
        System.out.println("DPS: " + calculateDPS());
        // System.out.println("Points Remaining: " + pointsRemaining);
        System.out.println("strength: " + strength);
        System.out.println("dex: " + dexterity);
        System.out.println("wisdom: " + wisdom);
        System.out.println("perception: " + perception);
        System.out.println();
    }
}
