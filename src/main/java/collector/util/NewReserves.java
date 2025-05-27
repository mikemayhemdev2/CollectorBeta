package collector.util;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class NewReserves {
    private static int curReserves = 0;

    public static void addReserves(int amount) {
        curReserves += amount;
        curReserves = Math.min(curReserves, AbstractDungeon.player.energy.energyMaster);
        if (amount > 0) {
            DoubleEnergyOrb.secondVfxTimer = 2.0F;
        }
    }

    public static int reserveCount() {
        return curReserves;
    }

    public static void resetReserves() {
        curReserves = 0;
    }
}