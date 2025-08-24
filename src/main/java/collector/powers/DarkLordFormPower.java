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
    private int secondAmount = 0;

    public DarkLordFormPower(int firstAmt, int secondAmt) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, firstAmt);
        secondAmount += secondAmt;
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
//        for (int i = 0; i < amount; i++) {
            for (AbstractMonster m : DFL.activeMonsterList()){
                addToBot(new ApplyPowerAction(m, this.owner, new DoomPower(m, this.amount), this.amount));
            }
//        }
        this.amount += secondAmount;
        updateDescription();
    }

    public void stackCorrectly(int first, int second){
        super.stackPower(first);
        this.secondAmount += second;
        updateDescription();
    }

    @Override
    public void updateDescription() {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + secondAmount + DESCRIPTIONS[3];
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