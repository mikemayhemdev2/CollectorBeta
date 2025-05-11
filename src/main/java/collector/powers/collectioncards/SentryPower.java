package collector.powers.collectioncards;

import collector.powers.AbstractCollectorPower;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SentryPower extends AbstractCollectorPower {
    public static final String NAME = "SentryPower";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public SentryPower() {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, 1);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}