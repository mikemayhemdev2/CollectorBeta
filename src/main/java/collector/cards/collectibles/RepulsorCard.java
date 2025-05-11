package collector.cards.collectibles;

import automaton.cards.goodstatus.Daze;
import collector.actions.DrawAllShapesFromCollectionAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.SHAPESWARM;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class RepulsorCard extends AbstractCollectibleCard {
    public final static String ID = makeID(RepulsorCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 3, 1

    public RepulsorCard() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        //isPyre();
        this.tags.add(SHAPESWARM);
        //exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        atb(new MakeTempCardInDrawPileAction(new Daze(),1, true, true));
    }


    @Override
    public void triggerWhenDrawn() {
        atb(new DrawAllShapesFromCollectionAction());
    }
    public void upp() {
        upgradeMagicNumber(1);
    }
}