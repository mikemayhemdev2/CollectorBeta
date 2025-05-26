package collector.cards.collectibles;

import collector.actions.DrawAllShapesFromCollectionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import sneckomod.SneckoMod;

import static collector.CollectorMod.SHAPESWARM;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class SpikerCard extends AbstractCollectibleCard {
    public final static String ID = makeID(SpikerCard.class.getSimpleName());
    // intellij stuff power, self, common, , , , , 5, 3

    public SpikerCard() {
        super(ID, 1, CardType.POWER, CardRarity.COMMON, CardTarget.SELF);
        this.tags.add(SHAPESWARM);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ThornsPower(p, p.hand.size()));
    }


    @Override
    public void triggerWhenDrawn() {
        atb(new DrawAllShapesFromCollectionAction());
    }
    public void upp() {
        //upgradeBaseCost(0);
        this.selfRetain = true;
        uDesc();
    }
}