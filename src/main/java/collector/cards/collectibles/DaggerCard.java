package collector.cards.collectibles;

import collector.cards.Ember;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class DaggerCard extends AbstractCollectibleCard {
    public final static String ID = makeID(DaggerCard.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 20, 4, , , 3, -1

    public DaggerCard() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 30;
        baseMagicNumber = magicNumber = 5;
        exhaust = true;
//        cardsToPreview = new LethalPlunge();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new DamageAction(p, new DamageInfo(p, magicNumber, DamageInfo.DamageType.THORNS)));
/*
       AbstractCard c = new LethalPlunge();
        if (upgraded) {
            c.upgrade();
        }
        atb(new MakeTempCardInHandAction(c));
 */
    }

    public void upp() {
        upgradeDamage(10);
//        cardsToPreview.upgrade();
    }
}