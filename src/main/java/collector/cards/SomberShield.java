package collector.cards;

import collector.powers.AddCopyNextTurnPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import static collector.CollectorMod.makeID;


public class SomberShield extends AbstractCollectorCard implements OnPyreCard {
    public final static String ID = makeID(SomberShield.class.getSimpleName());
    // intellij stuff skill, self, common, , , 7, 3, , 

    public SomberShield() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 3;
        baseMagicNumber = 2;
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void onPyred(AbstractCard card) {
        if (card.tags.contains(expansionContentMod.KINDLING)){
            blck();
        }
        AbstractCard clone = card.makeStatEquivalentCopy();
        applyToSelf(new AddCopyNextTurnPower(clone));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}