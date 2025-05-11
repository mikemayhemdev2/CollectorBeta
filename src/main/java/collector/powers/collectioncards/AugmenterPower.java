package collector.powers.collectioncards;

import basemod.helpers.CardModifierManager;
import collector.powers.AbstractCollectorPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.JAX;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import expansioncontent.cardmods.EtherealMod;

import static utilityClasses.Wiz.atb;

public class AugmenterPower extends AbstractCollectorPower {
    public static final String NAME = "AugmenterPower";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public AugmenterPower(int amount) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        for (int i = 0; i < amount; i++) {
            AbstractCard c = new JAX();
            CardModifierManager.addModifier(c, new EtherealMod());
            atb(new MakeTempCardInHandAction(c));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}