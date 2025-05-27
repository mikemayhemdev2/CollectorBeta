package collector.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import utilityClasses.DFL;

public class DarkLordFormPowerPlus extends AbstractCollectorPower {
    public static final String NAME = "DarkLordFormPlus";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public DarkLordFormPowerPlus() {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, 1);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        for (int i = 0; i < amount; i++) {
            for (AbstractMonster m : DFL.activeMonsterList()){
                addToBot(new ApplyPowerAction(m, this.owner, new DoomPower(m, DFL.pl().exhaustPile.size()), DFL.pl().exhaustPile.size()));
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        for (int i = 0; i < amount; i++) {
            for (AbstractMonster m : DFL.activeMonsterList()){
                addToBot(new ApplyPowerAction(m, this.owner, new DoomPower(m, DFL.pl().exhaustPile.size()), DFL.pl().exhaustPile.size()));
            }
        }
    }
}