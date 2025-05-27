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
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Blightning();
        exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new Blightning();
        if (upgraded) q.upgrade();
        makeInHand(q);

        int cardsToGen;
        if (p.drawPile.size() >= 5) {
            cardsToGen = p.drawPile.size() / 5;
            shuffleIn(q, cardsToGen);
        }

    }

    public void upp() {
        uDesc();
        cardsToPreview.upgrade();
    }
}