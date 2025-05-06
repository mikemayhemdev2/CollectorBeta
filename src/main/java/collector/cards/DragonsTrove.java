package collector.cards;

import collector.actions.DrawCardFromCollectionAction;
import collector.actions.GainReservesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class DragonsTrove extends AbstractCollectorCard {
    public final static String ID = makeID(DragonsTrove.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , 2, 1

    public DragonsTrove() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 2;
        exhaust = true;
        isPyre();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("MAW_DEATH");
        for (int i = 0; i < secondMagic; i++) {
            atb(new DrawCardFromCollectionAction());
        }
        atb(new GainReservesAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);

    }
}