package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class DaggerCard extends AbstractCollectibleCard {
    public final static String ID = makeID(DaggerCard.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 20, 4, , , 3, -1

    private boolean noHover = false;
    public DaggerCard(boolean noHover) {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 10;
       // baseMagicNumber = magicNumber = 3;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        exhaust = true;
        this.noHover = noHover;
        if (!this.noHover) cardsToPreview = new LethalPlunge(true);
    }

    public DaggerCard(){
        this(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        AbstractCard c = new LethalPlunge();
        if (upgraded) c.upgrade();
        atb(new MakeTempCardInHandAction(c));
    }

    public void upp() {
        upgradeDamage(3);
        cardsToPreview.upgrade();
    }
}