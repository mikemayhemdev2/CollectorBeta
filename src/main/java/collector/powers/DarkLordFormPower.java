package collector.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import utilityClasses.DFL;

public class DarkLordFormPower extends AbstractCollectorPower {
    public static final String NAME = "DarkLordFormPower";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public DarkLordFormPower() {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, 1);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        for (int i = 0; i < amount; i++) {
            for (AbstractMonster m : DFL.activeMonsterList()){
                addToBot(new ApplyPowerAction(m, this.owner, new DoomPower(m, DFL.pl().exhaustPile.size() * this.amount), DFL.pl().exhaustPile.size()  * this.amount));
            }
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + DFL.pl().exhaustPile.size() + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + DFL.pl().exhaustPile.size() + DESCRIPTIONS[1] + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
        }
    }

    @Override
    public void onExhaust(AbstractCard card) {
        this.updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.updateDescription();
    }
}