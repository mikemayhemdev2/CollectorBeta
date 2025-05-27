package collector.cards;

import collector.powers.AddCopyNextTurnPower;
import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToSelfTop;

public class SomberShield extends AbstractCollectorCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(SomberShield.class.getSimpleName());
    // intellij stuff skill, self, common, , , 7, 3, , 

    public SomberShield() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       if (pyredKindling) blck();
    }


    boolean pyredKindling = false;

    @Override
    public void onPyred(AbstractCard card) {
        pyredKindling = card.tags.contains(expansionContentMod.KINDLING);
        applyToSelfTop(new AddCopyNextTurnPower(card));
    }

    public void upp() {
        upgradeBlock(3);
    }
}