package collector.powers;

import collector.actions.GainReservesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CracklePower extends AbstractCollectorPower {
    public static final String NAME = "Crackle";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public CracklePower(int addAmount) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, addAmount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        if (!AbstractDungeon.player.hand.isEmpty()) {
            addToBot(new ExhaustAction(1, false, false, false));
        }
        addToBot(new GainReservesAction(amount));
    }
}