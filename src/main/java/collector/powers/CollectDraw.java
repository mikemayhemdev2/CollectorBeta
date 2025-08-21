package collector.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import utilityClasses.DFL;

public class CollectDraw extends AbstractCollectorPower {
    public static final String NAME = "CollectDraw";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public CollectDraw(int addAmount) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, addAmount);
    }

    @Override
    public void atStartOfTurn() {
        DFL.atb(new DrawCardAction(1));
        flashWithoutSound();
        if (this.amount > 1) {
            DFL.atb(new ReducePowerAction(this.owner, this.owner, this, 1));
        }else{
            DFL.atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }
}