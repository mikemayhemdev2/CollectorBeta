package collector.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.makeInHand;
import static utilityClasses.Wiz.shuffleIn;

public class Darkstorm extends AbstractCollectorCard {
    public final static String ID = makeID(Darkstorm.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , 4, 2

    public Darkstorm() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Blightning();
        exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new Blightning();
        makeInHand(q, magicNumber);
//        shuffleIn(q, magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}