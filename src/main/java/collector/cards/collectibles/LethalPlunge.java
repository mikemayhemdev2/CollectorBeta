package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import guardian.cards.SentryWave;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class LethalPlunge extends AbstractCollectibleCard {
    public final static String ID = makeID(LethalPlunge.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 20, 4, , , 3, -1

    public LethalPlunge() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = 23;
        isEthereal = true;
        exhaust = true;
        baseMagicNumber = magicNumber = 2;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
//        cardsToPreview = new DaggerCard();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new LoseHPAction(p, p, magicNumber));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        AbstractCard c = new DaggerCard();
        if (upgraded) c.upgrade();
        atb(new MakeTempCardInDrawPileAction(c, 1, true, true));
    }

    public void upp() {
        upgradeDamage(8);
        upgradeMagicNumber(-1);
//        cardsToPreview.upgrade();
    }
}