package collector.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import collector.effects.PurpleSearingBlowEffect;
import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class Hurting extends AbstractCollectorCard {
    public final static String ID = makeID(Hurting.class.getSimpleName());

    public Hurting() {//Look at the bottom of the next one
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.exhaust = true;

//        tags.add(expansionContentMod.UNPLAYABLE);
        MultiCardPreview.add(this, new GreatestHurting());
        baseMagicNumber = magicNumber = 5;
    }// Prepare maleficence.

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new DoomPower(m, magicNumber));
    }

    @Override
    public void triggerOnExhaust() {
        AbstractCard toAdd = new GreatestHurting();
        if (upgraded) {
            toAdd.upgrade();
        }
        makeInHand(toAdd);
    }

//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        return false;
//    }

    public void upp() {
        uDesc();
//        cardsToPreview.upgrade();
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
//        this.selfRetain = true;
        tags.add(expansionContentMod.KINDLING);
        upgradeMagicNumber(1);
    }
}