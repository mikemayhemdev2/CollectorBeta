package collector.powers;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DemisePower extends AbstractCollectorPower {
    public static final String NAME = "DemisePower";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.DEBUFF;
    public static final boolean TURN_BASED = false;

    // private boolean instakilled = false;

    public DemisePower(AbstractMonster target, int amount) {
        super(NAME, TYPE, TURN_BASED, target, null, amount);
        priority = 99;
    }


    @Override
    public void updateDescription() {
        if (this.amount == 1){

            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

}