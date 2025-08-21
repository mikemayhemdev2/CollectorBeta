package collector.cards.collectibles;



import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class RepulsorCard extends AbstractCollectibleCard {
    public final static String ID = makeID(RepulsorCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 3, 1

    public RepulsorCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        atb(new MakeTempCardInDrawPileAction(new Dazed(), secondMagic, true, true));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}